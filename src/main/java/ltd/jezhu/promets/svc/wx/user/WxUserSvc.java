package ltd.jezhu.promets.svc.wx.user;

import com.alibaba.fastjson.JSON;
import ltd.jezhu.promets.annotation.JwtTokenValidate;
import ltd.jezhu.promets.common.util.IdUtils;
import ltd.jezhu.promets.common.util.PropertyUtils;
import ltd.jezhu.promets.common.util.SpringContextUtils;
import ltd.jezhu.promets.common.util.WxBizDataCrypt;
import ltd.jezhu.promets.conf.wx.WxConfig;
import ltd.jezhu.promets.dao.wx.user.WxUserInfoDao;
import ltd.jezhu.promets.dto.base.io.InParam;
import ltd.jezhu.promets.dto.base.io.OutParam;
import ltd.jezhu.promets.dto.base.io.Response;
import ltd.jezhu.promets.dto.wx.user.WxUserInfoDTO;
import ltd.jezhu.promets.svc.wx.jwt.JwtService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信登录控制器
 * @author ymzhu
 * @date 2019/3/22 16:10
 */
@RestController
@RequestMapping("/wx/userinfo")
public class WxUserSvc {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 注入微信用户信息dao
     */
    private WxUserInfoDao wxUserInfoDao;
    /**
     * 微信配置
     */
    private WxConfig wxConfig;
    /**
     * jwt令牌服务
     */
    private JwtService jwtService;

    @Autowired
    public WxUserSvc(WxUserInfoDao wxUserInfoDao, WxConfig wxConfig, JwtService jwtService) {
        Assert.notNull(wxUserInfoDao, "wxUserInfoDao must not be null!");
        this.wxUserInfoDao = wxUserInfoDao;
        Assert.notNull(wxConfig, "wxConfig must not be null!");
        this.wxConfig = wxConfig;
        Assert.notNull(jwtService, "jwtService must not be null!");
        this.jwtService = jwtService;
    }

    /**
     * 同步微信用户信息
     * @param inParam inParam
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/3/23 1:52
     */
    @JwtTokenValidate
    @RequestMapping("/synchronize")
    public OutParam<WxUserInfoDTO> synchronize(@RequestBody InParam<WxUserInfoDTO> inParam) {
        WxUserInfoDTO in = inParam.getBody();
        if (null == in || StringUtils.isBlank(in.getOpenId())) {
            return Response.invalid();
        }
        // 用户数据解密
        if (StringUtils.isNoneBlank(in.getEncryptedData(), in.getIv())) {
            // 获取请求中包含的jwt令牌
            String token = SpringContextUtils.getToken();
            WxBizDataCrypt biz = new WxBizDataCrypt(wxConfig.getAppId(), jwtService.getWxSessionKey(token));
            String userInfoStr = biz.decryptData(in.getEncryptedData(), in.getIv());
            logger.debug("用户信息同步，获取unionid等信息结果为：" + userInfoStr);
            WxUserInfoDTO userInfo = JSON.parseObject(userInfoStr, WxUserInfoDTO.class);
            if (null != userInfo && StringUtils.isNotBlank(userInfo.getUnionId())) {
                in.setUnionId(userInfo.getUnionId());
            }
        }
        WxUserInfoDTO wxUserInfoDTO = wxUserInfoDao.getWxUserInfoByOpenid(in.getOpenId());
        if (null == wxUserInfoDTO) {
            // 首次保存
            in.setUserId(IdUtils.getUUID());
            if (wxUserInfoDao.saveWxUserInfo(in) != 1) {
                return Response.error("微信用户信息保存失败！");
            }
            return Response.success(in);
        } else {
            // 如果用户id为空，则重新设置用户id
            if (StringUtils.isBlank(wxUserInfoDTO.getUserId())) {
                in.setUserId(IdUtils.getUUID());
            }
            // 同步微信用户信息
            WxUserInfoDTO result = PropertyUtils.compareProperties(wxUserInfoDTO, in);
            if (null == result) {
                return Response.success(wxUserInfoDTO);
            }
            // 需要更新
            if (wxUserInfoDao.updateWxUserInfo(result) != 1) {
                return Response.error("微信用户信息更新失败！");
            }
            return Response.success(result);
        }
    }

}

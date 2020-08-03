package ltd.jezhu.promets.wx.svc;

import com.alibaba.fastjson.JSON;
import ltd.jezhu.promets.annotation.JwtTokenValidate;
import ltd.jezhu.promets.base.dto.InParam;
import ltd.jezhu.promets.base.dto.OutParam;
import ltd.jezhu.promets.base.dto.Response;
import ltd.jezhu.promets.base.service.JwtService;
import ltd.jezhu.promets.base.util.IdUtils;
import ltd.jezhu.promets.base.util.InjectUtils;
import ltd.jezhu.promets.base.util.PropertyUtils;
import ltd.jezhu.promets.base.util.SpringContextUtils;
import ltd.jezhu.promets.generated.entity.WxUserInfo;
import ltd.jezhu.promets.generated.service.IWxUserInfoService;
import ltd.jezhu.promets.wx.conf.WxConfig;
import ltd.jezhu.promets.wx.dto.WxUserInfoDTO;
import ltd.jezhu.promets.wx.util.WxBizDataCrypt;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信登录控制器
 * @author ymzhu
 * @date 2019/3/22 16:10
 */
@RestController
@RequestMapping("/wx/user_info")
public class WxUserSvc {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 注入微信用户信息dao
     */
    private final IWxUserInfoService userInfoService;
    /**
     * 微信配置
     */
    private final WxConfig wxConfig;
    /**
     * jwt令牌服务
     */
    private final JwtService jwtService;

    @Autowired
    public WxUserSvc(IWxUserInfoService userInfoService, WxConfig wxConfig, JwtService jwtService) {
        this.userInfoService = InjectUtils.check(userInfoService);
        this.wxConfig = InjectUtils.check(wxConfig);
        this.jwtService = InjectUtils.check(jwtService);
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
            logger.debug("用户信息同步，获取unionId等信息结果为：" + userInfoStr);
            WxUserInfoDTO userInfo = JSON.parseObject(userInfoStr, WxUserInfoDTO.class);
            if (null != userInfo && StringUtils.isNotBlank(userInfo.getUnionId())) {
                in.setUnionId(userInfo.getUnionId());
            }
        }
        WxUserInfo wxUserInfo = userInfoService.getById(in.getOpenId());
        if (null == wxUserInfo) {
            // 首次保存
            wxUserInfo = createWxUserInfo(in);
            if (!userInfoService.save(wxUserInfo)) {
                return Response.error("微信用户信息保存失败！");
            }
        } else {
            // 如果用户id为空，则重新设置用户id
            if (StringUtils.isBlank(wxUserInfo.getUserId())) {
                in.setUserId(IdUtils.uuid());
            }
            // 同步微信用户信息
            WxUserInfo result = PropertyUtils.compareProperties(wxUserInfo, in);
            if (null == result) {
                return Response.success(in);
            }
            // 需要更新
            if (!userInfoService.updateById(result)) {
                return Response.error("微信用户信息更新失败！");
            }
        }
        return Response.success(in);
    }

    private WxUserInfo createWxUserInfo(WxUserInfoDTO in) {
        in.setUserId(IdUtils.uuid());
        WxUserInfo wxUserInfo = new WxUserInfo();
        BeanUtils.copyProperties(in, wxUserInfo);
        return wxUserInfo;
    }

}

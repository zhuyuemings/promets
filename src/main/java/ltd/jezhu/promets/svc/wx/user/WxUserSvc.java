package ltd.jezhu.promets.svc.wx.user;

import ltd.jezhu.promets.exception.SystemException;
import ltd.jezhu.promets.dto.base.io.InParam;
import ltd.jezhu.promets.dto.base.io.OutParam;
import ltd.jezhu.promets.dto.base.io.Response;
import ltd.jezhu.promets.common.util.PropertyUtils;
import ltd.jezhu.promets.dao.wx.user.WxUserInfoDao;
import ltd.jezhu.promets.dto.wx.user.WxUserInfoDTO;
import org.apache.commons.lang3.StringUtils;
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
     * 注入微信用户信息dao
     */
    private WxUserInfoDao wxUserInfoDao;

    @Autowired
    public WxUserSvc(WxUserInfoDao wxUserInfoDao) {
        Assert.notNull(wxUserInfoDao, "wxUserInfoDao must not be null!");
        this.wxUserInfoDao = wxUserInfoDao;
    }

    /**
     * 同步微信用户信息
     * @param inParam inParam
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/3/23 1:52
     */
    @RequestMapping("/synchronize")
    public OutParam<WxUserInfoDTO> synchronize(@RequestBody InParam<WxUserInfoDTO> inParam) {
        WxUserInfoDTO in = inParam.getBody();
        if (null == in || StringUtils.isBlank(in.getOpenid())) {
            return Response.invalid();
        }
        try {
            WxUserInfoDTO wxUserInfoDTO = wxUserInfoDao.getWxUserInfoByOpenid(in.getOpenid());
            if (null == wxUserInfoDTO) {
                // 首次保存
                if (wxUserInfoDao.saveWxUserInfo(in) != 1) {
                    return Response.error("微信用户信息保存失败！");
                }
                return Response.success(in);
            } else {
                // 同步微信用户信息
                wxUserInfoDTO = PropertyUtils.compareProperties(wxUserInfoDTO, in);
                if (null == wxUserInfoDTO) {
                    return Response.success(in);
                }
                // 需要更新
                if (wxUserInfoDao.updateWxUserInfo(wxUserInfoDTO) != 1) {
                    return Response.error("微信用户信息更新失败！");
                }
                return Response.success(wxUserInfoDTO);
            }
        } catch (SystemException e) {
            e.printStackTrace();
            return Response.exception();
        }
    }

}

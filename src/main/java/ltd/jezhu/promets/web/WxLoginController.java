package ltd.jezhu.promets.web;

import ltd.jezhu.promets.dao.user.WxUserInfoDao;
import ltd.jezhu.promets.dto.user.WxUserInfoDTO;
import ltd.jezhu.promets.system.exception.SystemException;
import ltd.jezhu.promets.system.in.InParam;
import ltd.jezhu.promets.system.out.OutParam;
import ltd.jezhu.promets.system.out.Response;
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
@RequestMapping("/wx")
public class WxLoginController {

    private WxUserInfoDao wxUserInfoDao;

    @Autowired
    public WxLoginController(WxUserInfoDao wxUserInfoDao) {
        Assert.notNull(wxUserInfoDao, "wxUserInfoDao must not be null!");
        this.wxUserInfoDao = wxUserInfoDao;
    }

    @RequestMapping("/login")
    public OutParam<WxUserInfoDTO> login(@RequestBody InParam<WxUserInfoDTO> inParam) {
        WxUserInfoDTO in = inParam.getBody();
        if (null == in || StringUtils.isBlank(in.getOpenid())) {
            return Response.invalid();
        }
        try {
            if (wxUserInfoDao.saveWxUserInfo(in) != 1) {
                return Response.error("微信用户信息保存失败！");
            }
            return Response.success(in);
        } catch (SystemException e) {
            e.printStackTrace();
            return Response.exception();
        }
    }

}

package ltd.jezhu.promets.svc.wx.login;

import ltd.jezhu.promets.dto.base.io.InParam;
import ltd.jezhu.promets.dto.base.io.OutParam;
import ltd.jezhu.promets.dto.base.io.Response;
import ltd.jezhu.promets.svc.wx.api.WxApiService;
import ltd.jezhu.promets.dto.wx.api.WxSessionDTO;
import ltd.jezhu.promets.dto.wx.login.WxLoginDTO;
import ltd.jezhu.promets.dto.wx.user.WxUserInfoDTO;
import ltd.jezhu.promets.svc.wx.jwt.JwtService;
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
@RequestMapping("/wx/login")
public class WxLoginSvc {
    /**
     * 微信接口服务
     */
    private WxApiService wxApiService;
    /**
     * jwt令牌服务
     */
    private JwtService jwtService;

    @Autowired
    public WxLoginSvc(WxApiService wxApiService, JwtService jwtService) {
        Assert.notNull(wxApiService, "wxApiService must not be null!");
        this.wxApiService = wxApiService;
        Assert.notNull(jwtService, "jwtService must not be null!");
        this.jwtService = jwtService;
    }

    /**
     * 微信登录凭证校验
     * @param inParam inParam
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/3/23 1:15
     */
    @RequestMapping("/jscode2session")
    public OutParam<String> jscode2session(@RequestBody InParam<WxLoginDTO> inParam) {
        WxLoginDTO in = inParam.getBody();
        if (null == in || StringUtils.isBlank(in.getJscode())) {
            return Response.invalid();
        }
        try {
            WxSessionDTO wxSessionDTO = wxApiService.jscode2session(in.getJscode());
            if (null == wxSessionDTO) {
                return Response.error("微信登录凭证校验失败！");
            }
            if (null != wxSessionDTO.getErrcode() && !wxSessionDTO.getErrcode().equals(WxSessionDTO.ErrCode.SUCCESS.getCode())) {
                return Response.error(StringUtils.isBlank(wxSessionDTO.getErrmsg()) ? "微信登录凭证校验失败！" : wxSessionDTO.getErrmsg());
            }

            String token = jwtService.createToken(wxSessionDTO.getOpenid(), wxSessionDTO.getSessionKey());
            return Response.success(token);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.exception();
        }
    }


    public OutParam<WxUserInfoDTO> decrypt(@RequestBody InParam<WxLoginDTO> inParam) {
        return null;
    }

}

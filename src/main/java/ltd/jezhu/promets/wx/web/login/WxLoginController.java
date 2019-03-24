package ltd.jezhu.promets.wx.web.login;

import ltd.jezhu.promets.base.io.InParam;
import ltd.jezhu.promets.base.io.OutParam;
import ltd.jezhu.promets.base.io.Response;
import ltd.jezhu.promets.wx.api.WxApiService;
import ltd.jezhu.promets.wx.api.WxSessionDTO;
import ltd.jezhu.promets.wx.dto.login.WxLoginDTO;
import ltd.jezhu.promets.wx.dto.user.WxUserInfoDTO;
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
public class WxLoginController {

    /**
     * 微信接口服务
     */
    private WxApiService wxApiService;

    @Autowired
    public WxLoginController(WxApiService wxApiService) {
        Assert.notNull(wxApiService, "wxApiService must not be null!");
        this.wxApiService = wxApiService;
    }

    /**
     * 微信登录凭证校验
     * @param inParam inParam
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/3/23 1:15
     */
    @RequestMapping("/jscode2session")
    public OutParam<WxSessionDTO> jscode2session(@RequestBody InParam<WxLoginDTO> inParam) {
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
            return Response.success(wxSessionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.exception();
        }
    }


    public OutParam<WxUserInfoDTO> decrypt(@RequestBody InParam<WxLoginDTO> inParam) {

        return null;
    }

}

package ltd.jezhu.promets.wx.svc;

import ltd.jezhu.promets.base.util.InjectUtils;
import ltd.jezhu.promets.wx.dao.WxUserInfoDao;
import ltd.jezhu.promets.base.dto.InParam;
import ltd.jezhu.promets.base.dto.OutParam;
import ltd.jezhu.promets.base.dto.Response;
import ltd.jezhu.promets.wx.dto.WxSessionDTO;
import ltd.jezhu.promets.wx.dto.WxLoginDTO;
import ltd.jezhu.promets.wx.service.WxApiService;
import ltd.jezhu.promets.base.service.JwtService;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/wx/login")
public class WxLoginSvc {
    /**
     * 微信接口服务
     */
    private final WxApiService wxApiService;
    /**
     * jwt令牌服务
     */
    private final JwtService jwtService;
    /**
     * 注入微信用户信息dao
     */
    private final WxUserInfoDao wxUserInfoDao;

    @Autowired
    public WxLoginSvc(WxApiService wxApiService, JwtService jwtService, WxUserInfoDao wxUserInfoDao) {
        this.wxApiService = InjectUtils.check(wxApiService);
        this.jwtService = InjectUtils.check(jwtService);
        this.wxUserInfoDao = InjectUtils.check(wxUserInfoDao);
    }

    /**
     * 微信登录凭证校验
     * @param inParam inParam
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/3/23 1:15
     */
    @RequestMapping("/jscode2session")
    public OutParam<WxLoginDTO> jscode2session(@RequestBody InParam<WxLoginDTO> inParam) {
        WxLoginDTO in = inParam.getBody();
        if (null == in || StringUtils.isBlank(in.getJscode())) {
            return Response.invalid();
        }
        WxSessionDTO wxSessionDTO = wxApiService.jscode2session(in.getJscode());
        if (null == wxSessionDTO) {
            return Response.error("微信登录凭证校验失败！");
        }
        if (null != wxSessionDTO.getErrcode() && !wxSessionDTO.getErrcode().equals(WxSessionDTO.ErrCode.SUCCESS.getCode())) {
            return Response.error(StringUtils.isBlank(wxSessionDTO.getErrmsg()) ? "微信登录凭证校验失败！" : wxSessionDTO.getErrmsg());
        }
        String token = jwtService.createToken(wxSessionDTO.getOpenid(), wxSessionDTO.getSessionKey());
        if (StringUtils.isNotBlank(wxSessionDTO.getUnionid())) {
            if (!wxUserInfoDao.saveUnionid(wxSessionDTO.getOpenid(), wxSessionDTO.getUnionid())) {
                return Response.error("用户信息更新失败！");
            }
        }
        return Response.success(new WxLoginDTO(token, wxSessionDTO.getOpenid()));
    }


}

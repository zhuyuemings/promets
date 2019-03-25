package ltd.jezhu.promets.svc.wx.api;

import ltd.jezhu.promets.dto.wx.api.WxSessionDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 微信Retrofit接口
 * @author ymzhu
 * @date 2019/3/22 23:34
 */
public interface WxApi {

    /**
     * 登录凭证校验。通过 wx.login() 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
     * @param appid     appid
     * @param secret    secret
     * @param jsCode    jsCode
     * @param grantType grantType
     * @return {@link Call}
     * @author ymzhu
     * @date 2019/3/23 0:12
     */
    @GET("/sns/jscode2session")
    Call<WxSessionDTO> jscode2session(
            @Query("appid") String appid,
            @Query("secret") String secret,
            @Query("js_code") String jsCode,
            @Query("grant_type") String grantType);

}

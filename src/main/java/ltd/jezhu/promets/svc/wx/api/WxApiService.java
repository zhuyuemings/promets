package ltd.jezhu.promets.svc.wx.api;

import ltd.jezhu.promets.conf.wx.WxConfig;
import ltd.jezhu.promets.dto.wx.api.WxSessionDTO;
import ltd.jezhu.promets.dto.wx.login.WxLoginDTO;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

/**
 * 微信Retrofit接口服务
 * @author ymzhu
 * @date 2019/3/23 0:14
 */
@Component
public class WxApiService {

    /**
     * 注入微信配置
     */
    private WxConfig wxConfig;
    private Retrofit retrofit;

    public WxApiService(WxConfig wxConfig) {
        Assert.notNull(wxConfig, "wxConfig must not be null!");
        this.wxConfig = wxConfig;
        retrofit = new Retrofit.Builder()
                .baseUrl(wxConfig.getCode2SessionBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor((new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)))
                        .build()).build();
    }

    /**
     * 请求结果处理
     * @param call call
     * @return T
     * @author ymzhu
     * @date 2019/1/24 14:48
     */
    private <T> T callExec(Call<T> call) {
        Response<T> retrofitResponse = null;
        try {
            retrofitResponse = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == retrofitResponse || !retrofitResponse.isSuccessful()) {
            throw new RuntimeException("发起模拟车订单创建请求失败");
        }
        return retrofitResponse.body();
    }

    /**
     * 登录凭证校验。通过 wx.login() 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
     * @param jsCode jsCode
     * @return {@link WxSessionDTO}
     * @author ymzhu
     * @date 2019/3/23 0:46
     */
    public WxSessionDTO jscode2session(String jsCode) {
        Call<WxSessionDTO> call = retrofit.create(WxApi.class).jscode2session(
                wxConfig.getAppId(), wxConfig.getAppSecret(), jsCode, WxLoginDTO.GRANT_TYPE);
        return callExec(call);
    }

}

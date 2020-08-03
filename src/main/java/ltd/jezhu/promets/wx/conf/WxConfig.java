package ltd.jezhu.promets.wx.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * 微信配置类
 * @author ymzhu
 * @date 2019/3/18 21:56
 */
@Component
@ConfigurationProperties(prefix = "wx.config")
@Validated
public class WxConfig {
    /**
     * 小程序appid
     */
    @NotEmpty
    private String appId;
    /**
     * 小程序秘钥
     */
    @NotEmpty
    private String appSecret;
    /**
     * 小程序code2session地址
     */
    @NotEmpty
    private String code2SessionBaseUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getCode2SessionBaseUrl() {
        return code2SessionBaseUrl;
    }

    public void setCode2SessionBaseUrl(String code2SessionBaseUrl) {
        this.code2SessionBaseUrl = code2SessionBaseUrl;
    }
}

package ltd.jezhu.promets.wx.config;

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

    @NotEmpty
    private String appId;
    @NotEmpty
    private String appSecret;
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

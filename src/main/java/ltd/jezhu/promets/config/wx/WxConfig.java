package ltd.jezhu.promets.config.wx;

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
}

package ltd.jezhu.promets.conf.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * JSON Web Tokens 属性配置
 * @author ymzhu
 * @date 2019/3/25 16:39
 */
@Component
@ConfigurationProperties(prefix = "jwt.config")
@Validated
public class JwtConfig {
    /**
     * jwt生成秘钥
     */
    @NotEmpty
    private String secret;
    /**
     * jwt令牌超时时间(单位:秒)
     */
    @NotNull
    private Long expires;
    /**
     * jwt令牌签发者
     */
    @NotEmpty
    private String iss;
    /**
     * jwt令牌持有者
     */
    @NotEmpty
    private String aud;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }
}

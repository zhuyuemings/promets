package ltd.jezhu.promets.svc.wx.jwt;

import ltd.jezhu.promets.common.util.InjectUtils;
import ltd.jezhu.promets.common.util.JwtUtils;
import ltd.jezhu.promets.conf.jwt.JwtConfig;
import org.springframework.stereotype.Component;

/**
 * jwt令牌服务
 * @author ymzhu
 * @date 2019/3/25 15:02
 */
@Component
public class JwtService {
    /**
     * jwt配置
     */
    private final JwtConfig jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = InjectUtils.check(jwtConfig);
    }

    /**
     * 生成令牌
     * @param openid     微信小程序用户openid
     * @param sessionKey 微信小程序用户session_key
     * @return {@link String} 参数为空或发生异常都会返回null
     * @author ymzhu
     * @date 2019/3/25 17:17
     */
    public String createToken(String openid, String sessionKey) {
        return JwtUtils.createToken(jwtConfig.getSecret(), jwtConfig.getExpires(),
                jwtConfig.getIss(), jwtConfig.getAud(), openid, sessionKey);
    }

    /**
     * 校验jwt令牌
     * @param token jwt令牌
     * @return {@link boolean}
     * @author ymzhu
     * @date 2019/3/25 17:18
     */
    public boolean verifyToken(String token) {
        return JwtUtils.verifyToken(jwtConfig.getSecret(), token);
    }

    /**
     * 获取令牌内包含的微信小程序用户session_key
     * @param token jwt令牌
     * @return {@link String}
     * @author ymzhu
     * @date 2019/3/25 17:19
     */
    public String getWxSessionKey(String token) {
        return JwtUtils.getWxSessionKey(jwtConfig.getSecret(), token);
    }

}

package ltd.jezhu.promets.svc.wx.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import ltd.jezhu.promets.conf.jwt.JwtConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JSON Web Tokens 令牌工具
 * @author ymzhu
 * @date 2019/3/25 15:02
 */
@Component
public class JwtService {
    /**
     * jwt配置
     */
    private JwtConfig jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        Assert.notNull(jwtConfig, "jwtConfig must not be null!");
        this.jwtConfig = jwtConfig;
    }

    /**
     * payload中包含的jwt签发者键值
     */
    private final static String KEY_ISS = "iss";
    /**
     * payload中包含的jwt接收者键值
     */
    private final static String KEY_AUD = "aud";
    /**
     * payload中包含的微信小程序用户openid键值
     */
    private final static String KEY_OPENID = "openid";
    /**
     * payload中包含的微信小程序用户session_key键值
     */
    private final static String KEY_SESSION_KEY = "session_key";

    /**
     * 生成令牌
     * @param openid     微信小程序用户openid
     * @param sessionKey 微信小程序用户session_key
     * @return {@link String} 参数为空或发生异常都会返回null
     * @author ymzhu
     * @date 2019/3/25 17:17
     */
    public String createToken(String openid, String sessionKey) {
        return createToken(jwtConfig.getSecret(), jwtConfig.getExpires(),
                jwtConfig.getIss(), jwtConfig.getAud(), openid, sessionKey);
    }

    /**
     * 生成令牌
     * @param secret  令牌秘钥
     * @param expires 令牌有效期(单位：毫秒)
     * @param iss     jwt签发者
     * @param aud     jwt接收者
     * @param openid  微信小程序用户openid
     * @return {@link String} 参数为空或发生异常都会返回null
     * @author ymzhu
     * @date 2019/3/25 15:06
     */
    private static String createToken(String secret, Long expires, String iss, String aud, String openid, String sessionKey) {
        if (StringUtils.isAnyBlank(secret, iss, aud, openid, sessionKey)) {
            return null;
        }
        // 头部信息
        Map<String, Object> header = new HashMap<>(2);
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        // jwt的签发时间
        Date now = new Date();
        // 签名过期的时间
        Date expire = new Date(now.getTime() + expires * 1000);
        try {
            return JWT.create().withHeader(header).withIssuedAt(now).withExpiresAt(expire)
                    .withClaim(KEY_ISS, iss)
                    .withClaim(KEY_AUD, aud)
                    .withClaim(KEY_OPENID, openid)
                    .withClaim(KEY_SESSION_KEY, sessionKey)
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 校验jwt令牌
     * @param secret 令牌秘钥
     * @param token  jwt令牌
     * @return {@link Map} 令牌包含的信息
     * @author ymzhu
     * @date 2019/3/25 17:14
     */
    private static Map<String, Claim> verify(String secret, String token) {
        if (StringUtils.isAnyBlank(secret, token)) {
            return null;
        }
        DecodedJWT jwt;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // jwt令牌校验失败, 抛出jwt令牌验证非法异常
            e.printStackTrace();
            return null;
        }
        return jwt.getClaims();
    }


    /**
     * 校验jwt令牌
     * @param token  jwt令牌
     * @param openid 微信小程序用户openid
     * @return {@link boolean}
     * @author ymzhu
     * @date 2019/3/25 17:18
     */
    public boolean verifyToken(String token, String openid) {
        return verifyToken(jwtConfig.getSecret(), token, openid);
    }

    /**
     * 校验jwt令牌
     * @param secret 令牌秘钥
     * @param token  jwt令牌
     * @param openid 微信小程序用户openid
     * @return {@link boolean} 是否校验通过
     * @author ymzhu
     * @date 2019/3/25 17:16
     */
    private static boolean verifyToken(String secret, String token, String openid) {
        if (StringUtils.isAnyBlank(secret, token, openid)) {
            return false;
        }
        Map<String, Claim> claims = verify(secret, token);
        if (null == claims || !claims.containsKey(KEY_OPENID)) {
            return false;
        }
        return openid.equals(claims.get(KEY_OPENID).asString());
    }


    /**
     * 获取令牌内包含的微信小程序用户session_key
     * @param token jwt令牌
     * @return {@link String}
     * @author ymzhu
     * @date 2019/3/25 17:19
     */
    public String getWxSessionKey(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Claim> claims = verify(jwtConfig.getSecret(), token);
        if (null == claims || !claims.containsKey(KEY_SESSION_KEY)) {
            return null;
        }
        return claims.get(KEY_SESSION_KEY).asString();
    }


    public static void main(String[] args) {
        String sec = UUID.randomUUID().toString();
        System.out.println(sec);
        System.out.println(sec.length());
        String token = createToken(sec, 200L, "iss", "aud", "ymzhu", "key");
        System.out.println(token);
        System.out.println(verifyToken(sec, token, "ymzhu"));
    }

}

package ltd.jezhu.promets.base.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import ltd.jezhu.promets.base.consts.JwtConsts;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * jwt令牌工具
 * @author ymzhu
 * @date 2019/3/26 11:07
 */
public class JwtUtils {
    /**
     * 生成令牌
     * @param secret  令牌秘钥
     * @param expires 令牌有效期(单位：毫秒)
     * @param iss     jwt签发者
     * @param aud     jwt接收者
     * @param openId  微信小程序用户openId
     * @return {@link String} 参数为空或发生异常都会返回null
     * @author ymzhu
     * @date 2019/3/25 15:06
     */
    public static String createToken(String secret, Long expires, String iss, String aud, String openId, String sessionKey) {
        if (StringUtils.isAnyBlank(secret, iss, aud, openId, sessionKey)) {
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
                    .withClaim(JwtConsts.KEY_ISS, iss)
                    .withClaim(JwtConsts.KEY_AUD, aud)
                    .withClaim(JwtConsts.KEY_OPENID, openId)
                    .withClaim(JwtConsts.KEY_SESSION_KEY, sessionKey)
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException e) {
            e.printStackTrace();
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
            return jwt.getClaims();
        } catch (JWTVerificationException e) {
            // jwt令牌校验失败, 抛出jwt令牌验证非法异常
            e.printStackTrace();

        }
        return null;
    }

    /**
     * 校验jwt令牌
     * @param secret 令牌秘钥
     * @param token  jwt令牌
     * @return {@link boolean} 是否校验通过
     * @author ymzhu
     * @date 2019/3/25 17:16
     */
    public static boolean verifyToken(String secret, String token) {
        if (StringUtils.isAnyBlank(secret, token)) {
            return false;
        }
        Map<String, Claim> claims = verify(secret, token);
        return null != claims && claims.containsKey(JwtConsts.KEY_OPENID) && claims.containsKey(JwtConsts.KEY_SESSION_KEY);
    }

    /**
     * 获取令牌内包含的微信小程序用户session_key
     * @param secret secret
     * @param token  token
     * @return {@link String}
     * @author ymzhu
     * @date 2019/3/26 11:05
     */
    public static String getWxSessionKey(String secret, String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Claim> claims = JwtUtils.verify(secret, token);
        if (null == claims || !claims.containsKey(JwtConsts.KEY_SESSION_KEY)) {
            return null;
        }
        return claims.get(JwtConsts.KEY_SESSION_KEY).asString();
    }

    public static void main(String[] args) {
        String sec = UUID.randomUUID().toString();
        System.out.println(sec);
        System.out.println(sec.length());
        String token = createToken(sec, 200L, "iss", "aud", "ymzhu", "key");
        System.out.println(token);
        System.out.println(verifyToken(sec, token));
    }

}

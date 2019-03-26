package ltd.jezhu.promets.common.consts;

/**
 * 基于JWT的鉴权机制涉及的常量
 * @author ymzhu
 * @date 2019/3/26 15:04
 */
public class JwtConsts {
    /**
     * payload中包含的jwt签发者键值
     */
    public final static String KEY_ISS = "iss";
    /**
     * payload中包含的jwt接收者键值
     */
    public final static String KEY_AUD = "aud";
    /**
     * payload中包含的微信小程序用户openid键值
     */
    public final static String KEY_OPENID = "openid";
    /**
     * payload中包含的微信小程序用户session_key键值
     */
    public final static String KEY_SESSION_KEY = "session_key";
    /**
     * jwt令牌位于请求头部的名称
     */
    public final static String JWT_TOKEN_IN_HEADER = "token";
}

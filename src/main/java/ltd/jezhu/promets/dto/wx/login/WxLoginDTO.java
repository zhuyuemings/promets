package ltd.jezhu.promets.dto.wx.login;

/**
 * 微信登录凭证校验dto
 * @author ymzhu
 * @date 2019/3/23 15:16
 */
public class WxLoginDTO {

    /**
     * 登录凭证校验的授权类型，code2Session只需填写 authorization_code
     */
    public static final String GRANT_TYPE = "authorization_code";
    /**
     * 登录时获取的 code
     */
    private String jscode;
    /**
     * jwt令牌
     */
    private String token;
    /**
     * 微信小程序用户openid
     */
    private String openId;

    public WxLoginDTO() {
    }

    public WxLoginDTO(String token, String openId) {
        this.token = token;
        this.openId = openId;
    }

    public String getJscode() {
        return jscode;
    }

    public void setJscode(String jscode) {
        this.jscode = jscode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "WxLoginDTO{" +
                "jscode='" + jscode + '\'' +
                ", token='" + token + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}

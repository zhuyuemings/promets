package ltd.jezhu.promets.dto.wx.login;

import ltd.jezhu.promets.dto.wx.user.WxUserInfoDTO;

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
     * 用户信息对象，不包含 openid 等敏感信息
     */
    private WxUserInfoDTO userInfo;
    /**
     * 不包括敏感信息的原始数据字符串，用于计算签名
     */
    private String rawData;
    /**
     * 使用 sha1( rawData + sessionkey ) 得到字符串，用于校验用户信息，详见 用户数据的签名验证和加解密
     */
    private String signature;
    /**
     * 包括敏感数据在内的完整用户信息的加密数据，详见 用户数据的签名验证和加解密
     */
    private String encryptedData;
    /**
     * 加密算法的初始向量，详见 用户数据的签名验证和加解密
     */
    private String iv;


    public String getJscode() {
        return jscode;
    }

    public void setJscode(String jscode) {
        this.jscode = jscode;
    }

    public WxUserInfoDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WxUserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    @Override
    public String toString() {
        return "WxLoginDTO{" +
                "jscode='" + jscode + '\'' +
                ", userInfo=" + userInfo +
                ", rawData='" + rawData + '\'' +
                ", signature='" + signature + '\'' +
                ", encryptedData='" + encryptedData + '\'' +
                ", iv='" + iv + '\'' +
                '}';
    }
}

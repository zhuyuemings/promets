package ltd.jezhu.promets.wx.dto;

/**
 * 微信用户信息dto
 * @author ymzhu
 * @date 2019/3/18 20:48
 */
public class WxUserInfoDTO {

    /**
     * 微信用户openId
     */
    private String openId;
    /**
     * 微信用户unionId
     */
    private String unionId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像图片的 URL。URL
     * 最后一个数值代表正方形头像大小
     * （有 0、46、64、96、132 数值可选，0 代表 640x640 的正方形头像，46 表示 46x46 的正方形头像，剩余数值以此类推。默认132），
     * 用户没有头像时该项为空。若用户更换头像，原有头像 URL 将失效。
     */
    private String avatarUrl;
    /**
     * 用户性别(0未知，1男性，2女性)
     */
    private Integer gender;
    /**
     * 用户所在国家
     */
    private String country;
    /**
     * 用户所在省份
     */
    private String province;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 显示 country，province，city 所用的语言(en 英文，zh_CN 中文，zh_TW 繁体中文)
     */
    private String language;
    /**
     * 系统用户ID
     */
    private String userId;
    /**
     * 包括敏感数据在内的完整用户信息的加密数据，详见 用户数据的签名验证和加解密
     */
    private String encryptedData;
    /**
     * 加密算法的初始向量，详见 用户数据的签名验证和加解密
     */
    private String iv;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return "WxUserInfoDTO{" +
                "openId='" + openId + '\'' +
                ", unionId='" + unionId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gender=" + gender +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", language='" + language + '\'' +
                ", userId='" + userId + '\'' +
                ", encryptedData='" + encryptedData + '\'' +
                ", iv='" + iv + '\'' +
                '}';
    }
}

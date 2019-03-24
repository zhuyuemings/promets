package ltd.jezhu.promets.wx.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * code2Session接口dto
 * @author ymzhu
 * @date 2019/3/23 0:36
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxSessionDTO {
    /**
     * 用户唯一标识
     */
    private String openid;
    /**
     * 会话密钥
     */
    @JsonProperty("session_key")
    private String sessionKey;
    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回
     */
    private String unionid;
    /**
     * 错误码
     */
    private Integer errcode;
    /**
     * 错误信息
     */
    private String errmsg;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "WxSessionDTO{" +
                "openid='" + openid + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", unionid='" + unionid + '\'' +
                ", errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }

    public enum ErrCode {
        /**
         * 系统繁忙，此时请开发者稍候再试
         */
        BUSY(-1, "系统繁忙，此时请开发者稍候再试"),
        /**
         * 请求成功
         */
        SUCCESS(0, "请求成功"),
        /**
         * code 无效
         */
        INVALID(40029, "code 无效"),
        /**
         * 频率限制，每个用户每分钟100次
         */
        LIMITED(45011, "频率限制，每个用户每分钟100次");

        private int code;
        private String name;

        ErrCode(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

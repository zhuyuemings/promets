package ltd.jezhu.promets.exception;

/**
 * 微信aes加密解密异常
 * @author ymzhu
 * @date 2019/4/9 9:17
 */
public class WxAesException extends ServiceException {
    /**
     * 错误代码
     */
    private String code;

    public WxAesException(String message, String code) {
        super(message);
        this.code = code;
    }

    public WxAesException() {
        super();
    }

    public WxAesException(String message) {
        super(message);
    }

    public WxAesException(String message, Throwable cause) {
        super(message, cause);
    }

    public WxAesException(Throwable cause) {
        super(cause);
    }

    protected WxAesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package ltd.jezhu.promets.base.exception;

/**
 * jwt令牌校验失败的异常
 * @author ymzhu
 * @date 2019/3/21 14:56
 */
public class InvalidTokenException extends SystemException {
    public InvalidTokenException() {
        super();
    }

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTokenException(Throwable cause) {
        super(cause);
    }

    protected InvalidTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

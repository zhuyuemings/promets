package ltd.jezhu.promets.system.exception;

/**
 * 系统自定义异常
 * @author ymzhu
 * @date 2019/3/22 12:03
 */
public class SystemException extends Exception {
    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    protected SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

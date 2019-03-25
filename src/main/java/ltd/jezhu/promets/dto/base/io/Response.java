package ltd.jezhu.promets.dto.base.io;


/**
 * 系统请求返回封装处理
 * @author ymzhu
 * @date 2019/1/25 10:36
 */
public class Response {

    enum ResponseCode {
        /**
         * 成功
         */
        SUCCESS("0", "SUCCESS"),
        /**
         * 错误
         */
        ERROR("1", "ERROR"),
        /**
         * 异常
         */
        EXCEPTION("3", "EXCEPTION");
        private String code;
        private String name;

        ResponseCode(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static final String MSG_INVALID = "入参不合法！";

    private static final String MSG_EXCEPTION = "服务发生异常！";

    /**
     * 入参不合法
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:39
     */
    public static <T> OutParam<T> invalid() {
        return error(MSG_INVALID);
    }

    /**
     * 入参不合法
     * @param t t
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:53
     */
    public static <T> OutParam<T> invalid(T t) {
        return error(MSG_INVALID, t);
    }

    /**
     * 错误返回
     * @param msg msg
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:32
     */
    public static <T> OutParam<T> error(String msg) {
        return OutParam.builder().returnCode(ResponseCode.ERROR.getCode()).returnMsg(msg).build();
    }

    /**
     * 错误返回
     * @param msg msg
     * @param t   t
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:53
     */
    public static <T> OutParam<T> error(String msg, T t) {
        return OutParam.builder().returnCode(ResponseCode.ERROR.getCode()).returnMsg(msg).build(t);
    }

    /**
     * 异常返回
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:52
     */
    public static <T> OutParam<T> exception() {
        return exception(MSG_EXCEPTION);
    }

    /**
     * 异常返回
     * @param t t
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:55
     */
    public static <T> OutParam<T> exception(T t) {
        return exception(MSG_EXCEPTION, t);
    }

    /**
     * 异常返回
     * @param msg msg
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:32
     */
    public static <T> OutParam<T> exception(String msg) {
        return OutParam.builder().returnCode(ResponseCode.EXCEPTION.getCode()).returnMsg(msg).build();
    }

    /**
     * 异常返回
     * @param msg msg
     * @param t   t
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:53
     */
    public static <T> OutParam<T> exception(String msg, T t) {
        return OutParam.builder().returnCode(ResponseCode.EXCEPTION.getCode()).returnMsg(msg).build(t);
    }

    /**
     * 成功返回
     * @param t t
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:36
     */
    public static <T> OutParam<T> success(T t) {
        return OutParam.builder().build(t);
    }

}

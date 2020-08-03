package ltd.jezhu.promets.base.dto;


import ltd.jezhu.promets.base.consts.ResponseConsts;
import ltd.jezhu.promets.base.enums.ResponseCode;

/**
 * 系统请求返回封装处理
 * @author ymzhu
 * @date 2019/1/25 10:36
 */
public class Response {


    /**
     * 入参不合法
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:39
     */
    public static <T> OutParam<T> invalid() {
        return error(ResponseConsts.MSG_INVALID);
    }

    /**
     * 入参不合法
     * @param t t
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:53
     */
    public static <T> OutParam<T> invalid(T t) {
        return error(ResponseConsts.MSG_INVALID, t);
    }

    /**
     * 错误返回
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/3/26 15:22
     */
    public static <T> OutParam<T> error() {
        return OutParam.builder().returnCode(ResponseCode.Error.getCode()).returnMsg(ResponseConsts.MSG_ERROR).build();
    }

    /**
     * 错误返回
     * @param msg msg
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:32
     */
    public static <T> OutParam<T> error(String msg) {
        return OutParam.builder().returnCode(ResponseCode.Error.getCode()).returnMsg(msg).build();
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
        return OutParam.builder().returnCode(ResponseCode.Error.getCode()).returnMsg(msg).build(t);
    }

    /**
     * 异常返回
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:52
     */
    public static <T> OutParam<T> exception() {
        return exception(ResponseConsts.MSG_EXCEPTION);
    }

    /**
     * 异常返回
     * @param t t
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:55
     */
    public static <T> OutParam<T> exception(T t) {
        return exception(ResponseConsts.MSG_EXCEPTION, t);
    }

    /**
     * 异常返回
     * @param msg msg
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/1/25 15:32
     */
    public static <T> OutParam<T> exception(String msg) {
        return OutParam.builder().returnCode(ResponseCode.Exception.getCode()).returnMsg(msg).build();
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
        return OutParam.builder().returnCode(ResponseCode.Exception.getCode()).returnMsg(msg).build(t);
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


    /**
     * 访问受限
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/3/26 10:41
     */
    public static <T> OutParam<T> unauthorized() {
        return OutParam.builder().returnCode(ResponseCode.Unauthorized.getCode()).returnMsg(ResponseConsts.UNAUTHORIZED).build();
    }

    /**
     * 访问受限
     * @param msg msg
     * @return {@link OutParam}
     * @author ymzhu
     * @date 2019/3/26 10:41
     */
    public static <T> OutParam<T> unauthorized(String msg) {
        return OutParam.builder().returnCode(ResponseCode.Unauthorized.getCode()).returnMsg(msg).build();
    }

}

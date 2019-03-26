package ltd.jezhu.promets.dto.base.io;

import ltd.jezhu.promets.enums.ResponseCode;

/**
 * 出参
 * @author ymzhu
 * @date 2019/1/10 17:58
 **/
public class OutParam<T> {
    /**
     * 返回码
     */
    private String code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 包裹数据
     */
    private T data;

    private OutParam() {
        this.code = ResponseCode.Success.getCode();
        this.message = ResponseCode.Success.getName();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OutParam{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


    static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private OutParam out = new OutParam<>();

        Builder() {
        }

        Builder returnCode(String code) {
            this.out.setCode(code);
            return this;
        }

        Builder returnMsg(String message) {
            this.out.setMessage(message);
            return this;
        }

        @SuppressWarnings("unchecked")
        <T> OutParam<T> build() {
            return (OutParam<T>) this.out;
        }

        @SuppressWarnings("unchecked")
        <T> OutParam<T> build(T data) {
            this.out.setData(data);
            return this.out;
        }

    }
}

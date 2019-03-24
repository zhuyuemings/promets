package ltd.jezhu.promets.base.io;

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

    OutParam() {
        this.code = Response.ResponseCode.SUCCESS.getCode();
        this.message = Response.ResponseCode.SUCCESS.getName();
    }

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    void setData(T data) {
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


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private OutParam out = new OutParam<>();

        Builder() {
        }

        public Builder returnCode(String code) {
            this.out.setCode(code);
            return this;
        }

        public Builder returnMsg(String message) {
            this.out.setMessage(message);
            return this;
        }

        @SuppressWarnings("unchecked")
        public <T> OutParam<T> build() {
            return (OutParam<T>) this.out;
        }

        @SuppressWarnings("unchecked")
        public <T> OutParam<T> build(T data) {
            this.out.setData(data);
            return this.out;
        }

    }
}

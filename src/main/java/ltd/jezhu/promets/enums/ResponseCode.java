package ltd.jezhu.promets.enums;

/**
 * 服务器响应结果代码
 * @author ymzhu
 * @date 2019/3/26 14:39
 */
public enum ResponseCode {
    /**
     * 成功
     */
    Success("0", "Success"),
    /**
     * 错误
     */
    Error("1", "Error"),
    /**
     * 没有权限
     */
    Exception("3", "Exception"),
    /**
     * 异常
     */
    Unauthorized("401", "Unauthorized");

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
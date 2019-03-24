package ltd.jezhu.promets.base.io;

/**
 * 入参
 * @author ymzhu
 * @date 2019/1/11 11:05
 **/
public class InParam<T> {

    private T body;

    public InParam() {
    }

    public T getBody() {
        return this.body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "InParam{" +
                "body=" + body +
                '}';
    }
}

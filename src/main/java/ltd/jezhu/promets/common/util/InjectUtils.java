package ltd.jezhu.promets.common.util;

import org.springframework.util.Assert;

/**
 * 注入工具类
 * @author ymzhu
 * @since 2020/1/16 15:28
 */
public class InjectUtils {
    public static <T> T check(T t) {
        Assert.notNull(t, t.getClass() + " must not be null! please check your project!");
        return t;
    }
}

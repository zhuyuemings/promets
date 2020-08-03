package ltd.jezhu.promets.base.util;

import java.util.UUID;

/**
 * 序列工具
 * @author ymzhu
 * @date 2019/3/26 16:26
 */
public class IdUtils {
    /**
     * 生成uuid序列
     * @return {@link String}
     * @author ymzhu
     * @date 2019/3/26 16:26
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

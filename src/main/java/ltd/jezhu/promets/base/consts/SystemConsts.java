package ltd.jezhu.promets.base.consts;

/**
 * 系统级常量（请勿修改）
 * @author ymzhu
 * @date 2019/11/29 11:56
 */
public interface SystemConsts {

    /**
     * 默认字符集
     */
    String DEFAULT_CHARSET = "UTF-8";
    /**
     * 默认日期格式
     */
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 是
     */
    int YES = 1;
    /**
     * 否
     */
    int NO = 0;

    /**
     * 标识开发环境，分布式id生成器通过该标识判断是否是开发环境，若是开发环境，则不会初始化分布式生成器
     */
    String DEV_ENV = "dev";

    String ERR_LOAD_FAIL = "Load balancer does not have available server for client:";
}

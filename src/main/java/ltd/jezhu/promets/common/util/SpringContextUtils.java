package ltd.jezhu.promets.common.util;

import ltd.jezhu.promets.common.consts.JwtConsts;
import ltd.jezhu.promets.common.consts.SystemConsts;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Spring上下文工具,通过该工具可以用静态方法的方式获取Spring容器中的Bean,Request以及 Session
 * @author ymzhu
 * @date 2019/3/26 10:11
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Assert.notNull(applicationContext, "applicationContext must not be null!");
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 获取当前请求的Request对象
     * @return {@link HttpServletRequest}
     * @author ymzhu
     * @date 2019/3/26 10:15
     */
    private static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            return null;
        }
        return requestAttributes.getRequest();
    }

    /**
     * 获取当前请求的session对象
     * @return {@link HttpSession}
     * @author ymzhu
     * @date 2019/3/26 10:16
     */
    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        if (null == request) {
            return null;
        }
        return request.getSession();
    }

    /**
     * 获取当前请求头部的jwt令牌
     * @return {@link String}
     * @author ymzhu
     * @date 2019/3/26 10:20
     */
    public static String getToken() {
        HttpServletRequest request = getRequest();
        if (null == request) {
            return null;
        }
        return request.getHeader(JwtConsts.JWT_TOKEN_IN_HEADER);
    }

}

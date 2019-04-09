package ltd.jezhu.promets.aspect;

import ltd.jezhu.promets.annotation.JwtTokenValidate;
import ltd.jezhu.promets.common.util.SpringContextUtils;
import ltd.jezhu.promets.exception.InvalidTokenException;
import ltd.jezhu.promets.svc.wx.jwt.JwtService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 此切面用于验证请求客户端持有的jwt令牌，jwt令牌位于客户端请求头部，
 * 任何满足public * ltd.jezhu.promets.svc.*.*.*.*(..)
 * 且拥有@JwtTokenValidate{@link JwtTokenValidate}注解的方法将会进入此切面
 * @author ymzhu
 * @date 2019/3/26 10:04
 */
@Order(1)
@Aspect
@Component
public class JwtTokenAspect {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * jwt令牌服务
     */
    private JwtService jwtService;

    public JwtTokenAspect(JwtService jwtService) {
        Assert.notNull(jwtService, "jwtService must not be null!");
        this.jwtService = jwtService;
    }

    /**
     * 定义切点
     */
    @Pointcut("execution(public * ltd.jezhu.promets.svc.*.*.*.*(..)) && @annotation(ltd.jezhu.promets.annotation.JwtTokenValidate)")
    public void tokenValidation() {
    }

    @Before("tokenValidation()")
    public void validation(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        logger.debug("进入切面，代理：" + signature);
        // 获取请求中包含的jwt令牌
        String token = SpringContextUtils.getToken();
        if (!jwtService.verifyToken(token)) {
            throw new InvalidTokenException("令牌校验失败！");
        }
    }

}

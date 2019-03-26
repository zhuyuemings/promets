package ltd.jezhu.promets.annotation;

import ltd.jezhu.promets.aspect.JwtTokenAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此注解标记需要验证jwt令牌的接口，验证过程在切面{@link JwtTokenAspect}中进行
 * @author ymzhu
 * @date 2019/3/26 14:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtTokenValidate {
}

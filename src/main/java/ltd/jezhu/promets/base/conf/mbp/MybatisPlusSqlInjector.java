package ltd.jezhu.promets.base.conf.mbp;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteByIdWithFill;

import java.util.List;

/**
 * 自定义 mybatis-plus sql注入器
 * @author ymzhu
 * @since 2020/1/8 16:42
 */
public class MybatisPlusSqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methods = super.getMethodList(mapperClass);
        //逻辑删除填充值
        methods.add(new LogicDeleteByIdWithFill());
        return methods;
    }
}

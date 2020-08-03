package ltd.jezhu.promets.base.conf.mbp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.log4j.Log4j2;
import ltd.jezhu.promets.base.consts.SystemConsts;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义mybatis-plus自动填充
 * @author ymzhu
 * @since 2020/1/8 17:35
 */
@Component
@Log4j2
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setIfNotExist(MybatisPlusConsts.CREATED_AT, new Date(), metaObject);
        setIfNotExist(MybatisPlusConsts.DELETED, SystemConsts.NO, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新
        setIfNotExist(MybatisPlusConsts.MODIFIED_AT, new Date(), metaObject);
        //删除，通过删除人判断
        Object object = metaObject.getValue(MybatisPlusConsts.DELETED_BY);
        if (object instanceof String && StringUtils.isNotBlank(object.toString())) {
            setIfNotExist(MybatisPlusConsts.DELETED_AT, new Date(), metaObject);
        }

    }

    private void setIfNotExist(String name, Object value, MetaObject metaObject) {
        if (null == metaObject.getValue(name)) {
            setFieldValByName(name, value, metaObject);
        }
    }
}

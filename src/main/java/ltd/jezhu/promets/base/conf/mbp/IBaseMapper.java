package ltd.jezhu.promets.base.conf.mbp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 自定义 mybatis-plus 基本mapper
 * @author ymzhu
 * @since 2020/1/8 16:40
 */
public interface IBaseMapper<T> extends BaseMapper<T> {

    /**
     * 根据 id 逻辑删除数据,并带字段填充功能
     * @param entity entity
     * @return {@link int}
     * @author ymzhu
     * @since 2020/1/8 16:46
     */
    int deleteByIdWithFill(T entity);

}

package ltd.jezhu.promets.base.conf.mbp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;

/**
 * 自定义 mybatis-plus 基本服务接口实现类
 * @author ymzhu
 * @since 2020/1/8 17:16
 */
@Log4j2
public class BaseServiceImpl<M extends IBaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    /**
     * 根据 id 逻辑删除数据,并带字段填充功能
     * @param entity entity
     * @return {@link int}
     * @author ymzhu
     * @since 2020/1/8 16:46
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteByIdWithFill(T entity) {
        return SqlHelper.retBool(baseMapper.deleteByIdWithFill(entity));
    }

}

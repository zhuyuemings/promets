package ltd.jezhu.promets.base.conf.mbp;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 自定义 mybatis-plus 基本服务接口
 * @author ymzhu
 * @since 2020/1/8 17:13
 */
public interface IBaseService<T> extends IService<T> {
    /**
     * 根据 id 逻辑删除数据,并带字段填充功能
     * @param entity entity
     * @return {@link int}
     * @author ymzhu
     * @since 2020/1/8 16:46
     */
    @Transactional(rollbackFor = Exception.class)
    boolean deleteByIdWithFill(T entity);
}

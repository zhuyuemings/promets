package ltd.jezhu.promets.generated.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户绑定配置表
 * </p>
 *
 * @author ymzhu
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_bound_config")
public class UserBoundConfig implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId("id")
    private String id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 绑定用户id
     */
    @TableField("bound_user")
    private String boundUser;

    /**
     * 绑定用户所在城市名称（用于获取天气信息）
     */
    @TableField("city_name")
    private String cityName;

    /**
     * 相识日期（用于计算在一起天数）
     */
    @TableField("meet_date")
    private Date meetDate;


}

package ltd.jezhu.promets.generated.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户信息表
 * </p>
 *
 * @author ymzhu
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_info")
public class SysUserInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 系统用户ID
     */
    @TableId("user_id")
    private String userId;

    /**
     * 用户手机号
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 是否绑定（0否，1是）
     */
    @TableField("bound")
    private Boolean bound;

    /**
     * 绑定用户
     */
    @TableField("bound_user")
    private String boundUser;


}

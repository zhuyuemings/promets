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
 * 微信用户表
 * </p>
 *
 * @author ymzhu
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("wx_user_info")
public class WxUserInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * openid
     */
    @TableId("open_id")
    private String openId;

    /**
     * 微信用户unionId
     */
    @TableField("union_id")
    private String unionId;

    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 用户头像图片的 URL。URL 最后一个数值代表正方形头像大小（有 0、46、64、96、132 数值可选，0 代表 640x640 的正方形头像，46 表示 46x46 的正方形头像，剩余数值以此类推。默认132），用户没有头像时该项为空。若用户更换头像，原有头像 URL 将失效。
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 用户性别(0未知，1男性，2女性)
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 用户所在国家
     */
    @TableField("country")
    private String country;

    /**
     * 用户所在省份
     */
    @TableField("province")
    private String province;

    /**
     * 用户所在城市
     */
    @TableField("city")
    private String city;

    /**
     * 显示 country，province，city 所用的语言(en 英文，zh_CN 中文，zh_TW 繁体中文)
     */
    @TableField("language")
    private String language;

    /**
     * 系统用户ID
     */
    @TableField("user_id")
    private String userId;


}

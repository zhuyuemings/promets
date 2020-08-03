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
 * 用户日记数据表
 * </p>
 *
 * @author ymzhu
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_diary_data")
public class UserDiaryData implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId("id")
    private String id;

    /**
     * 天气
     */
    @TableField("weather")
    private String weather;

    /**
     * 地点
     */
    @TableField("location")
    private String location;

    /**
     * 心情
     */
    @TableField("mood")
    private String mood;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 正文
     */
    @TableField("content")
    private String content;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


}

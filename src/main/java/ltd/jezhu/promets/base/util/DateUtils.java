package ltd.jezhu.promets.base.util;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author ymzhu
 * @date 2018/12/13 14:59
 **/
public class DateUtils {

    /**
     * 时间文本
     */
    interface TimeText {
        String FUTURE = "未来";
        String JUST_NOW = "刚刚";
        String MIN = "分钟前";
        String HOUR = "小时前";
        String YESTERDAY = "昨天";
        String BEFORE_YESTERDAY = "前天";
        String SEPERATOR = " ";
    }

    /**
     * 时间计量
     */
    interface TimeMeasure {
        int JUST_NOW = 0;
        int MIN = 60;
        int HOUR = 60 * 24;
        int YESTERDAY = 60 * 24 * 2;
        int BEFORE_YESTERDAY = 60 * 24 * 3;
    }

    /**
     * 获取时间差值文本
     * @param start start
     * @param end   end
     * @author ymzhu
     * @date 2018/12/13 15:59
     */
    public static String getTimeDiffText(Date start, Date end) {
        if (end.getTime() < start.getTime()) {
            return TimeText.FUTURE;
        }
        long diffMin = (end.getTime() - start.getTime()) / (1000 * 60);
        if (diffMin == TimeMeasure.JUST_NOW) {
            return TimeText.JUST_NOW;
        }
        if (diffMin < TimeMeasure.MIN) {
            return diffMin + TimeText.MIN;
        }
        if (diffMin < TimeMeasure.HOUR) {
            return diffMin / 60 + TimeText.HOUR;
        }
        if (diffMin < TimeMeasure.YESTERDAY) {
            SimpleDateFormat hm = new SimpleDateFormat("HH:mm");
            return TimeText.YESTERDAY + TimeText.SEPERATOR + hm.format(start);
        }
        if (diffMin < TimeMeasure.BEFORE_YESTERDAY) {
            SimpleDateFormat hm = new SimpleDateFormat("HH:mm");
            return TimeText.BEFORE_YESTERDAY + TimeText.SEPERATOR + hm.format(start);
        }
        SimpleDateFormat y = new SimpleDateFormat("yyyy");
        if (y.format(start).equals(y.format(end))) {
            // 同一年
            SimpleDateFormat mdhm = new SimpleDateFormat("MM-dd HH:mm");
            return mdhm.format(start);
        } else {
            // 非今年显示全部
            SimpleDateFormat ymdhm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return ymdhm.format(start);
        }
    }
}

package hry.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class APPDateUtil {

    /**
     * 处理APP时间显示
     *
     * @param time
     * @return
     */
    public static String appDate(Date time) {
        String appTime = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date now = new Date();
            Date today = format.parse(format.format(now));
            long days = 0;
            long hour = 0;
            long mins = 0;
            long todayNum = today.getTime();
            long timeNum = time.getTime();
            long nowNum = now.getTime();
            long l1 = todayNum - timeNum;
            long l2 = nowNum - timeNum;
            if (l1 <= 0) {//今天的
                hour = l2 / (1000 * 60 * 60);
                if (hour < 1) {
                    mins = l2 / (1000 * 60);
                    if (mins < 1) {
                        appTime = "刚刚";
                    } else {
                        appTime = mins + "分钟前";
                    }
                } else {
                    appTime = hour + "小时前";
                }
            } else {
                days = l1 / (1000 * 60 * 60 * 24);

                if (days < 1) {
                    appTime = "昨天";
                } else {
                    appTime = (days + 1) + "天前";
                }
            }
        } catch (Exception e) {

        }
        return appTime;
    }


    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date d1 = format.parse("2018-12-12 00:00:00");
            String s = appDate(d1);
            System.out.println(s);
        } catch (Exception e) {

        }

    }

}

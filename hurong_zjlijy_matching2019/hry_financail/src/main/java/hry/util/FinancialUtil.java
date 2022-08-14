package hry.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/4/1 10:09
 * @Description:
 */
public class FinancialUtil {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            System.out.println(getTimeDate(5,10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Date getTimeDate(Integer dayOrHour, Integer num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dayOrHour, +num);
        date = calendar.getTime();
        return date;
    }



    private static boolean getYear(Date date) {
        Calendar c=Calendar.getInstance();
        c.setTime(date);//设置日期
        Integer yearStr = c.get(Calendar.YEAR);//获取年份
        c.set(yearStr,2,1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.get(Calendar.DAY_OF_MONTH)==29;
    }
}

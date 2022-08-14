package hry.financail.financing;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.core.constant.StringConstant;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;

import java.math.BigDecimal;
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

    //分页数
    public static final int PAGE_SIZE = 5000;
    //达到最大值时分页
    public static final int MAX_SIZE = 10000;
    //
    public static final int ORDER_SIZE = 3;

    public static final BigDecimal HUNDRED = new BigDecimal(100);


    /**
     * 获取时间
     * 1代表的是对年份操作，2是对月份操作，3是对星期操作，5是对日期操作，11是对小时操作，12是对分钟操作，13是对秒操作，14是对毫秒操作
     * @param dayOrHour
     * @param num
     * @return
     */
    public static String getTime(Integer dayOrHour, Integer num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dayOrHour, +num);
        date = calendar.getTime();
        return sdf.format(date);
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

    public static int calcPage(int list_count,int page_size){
        return list_count % page_size > 0 ? list_count
                / page_size + 1 : list_count / page_size;
    }


    public static String getConfigAll(String configKey){
        RedisService redisService = (RedisService) SpringUtil.getBean("redisService");
        String config = redisService.get(StringConstant.CONFIG_CACHE+":financialRecommend");
        JSONArray obj = JSON.parseArray(config);
        for (Object o : obj) {
            JSONObject parseObject = JSONObject.parseObject(o.toString());
            String configKeys = parseObject.getString("configkey");
            if(configKeys.equals(configKey)){
                return parseObject.getString("value");
            }
        }

        return null;
    }
}

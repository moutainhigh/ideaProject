package hry.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.redis.common.utils.RedisService;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 调用汇率
 *
 * @author Administrator
 *
 */
@Service
@Scope
public class HuiLvUtil {
    private static final Logger log = Logger.getLogger(HuiLvUtil.class);
 
    /**
     *            :请求接口
     *            :参数
     * @return 返回结果
     */
    public static void getHuilvData() {
        log.info("-----进入汇率定时器-----");
         
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        JSONArray obj= JSON.parseArray(redisService.get("configCache:financeConfig"));
        for(Object o:obj){
            JSONObject   oo=JSON.parseObject(o.toString());
            if("ChooseRate".equals(oo.getString("configkey"))){
                String value =oo.getString("value");
                if("1".equals(value)){
                    redisService.save("USDCNY", "0");
                    log.info("读取后台手动配置的USDCNY汇率");
                    return;
                }
            }
        }
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        long currentTime=System.currentTimeMillis();
        String httpUrl = "http://hq.sinajs.cn/";
        String httpArg = "rn="+currentTime+"list=fx_susdcny";
        httpUrl = httpUrl + "?" + httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "GBK"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            String [] arr = result.split(",");
            redisService.hset("hry:coinPrice", "USDT", arr[8]);
            log.info("最新USDCNY汇率：" +new BigDecimal(arr[8]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * getHuilvData 定时器
     * <p>
     * TODO
     * </p>
     *
     */
    public static void getHuilvDataJob() {
        ScheduleJob job = new ScheduleJob();
        job.setBeanClass("hry.util.HuiLvUtil");
        job.setMethodName("getHuilvData");
        QuartzManager.addJob("huilvData", job, QuartzJob.class, "0 0/5 * * * ?");//5分钟
    }
    public static void main(String[] args) {
        String httpUrl = "http://hq.sinajs.cn/";
        String httpArg = "rn=1526351165800list=fx_susdcny";
        long currentTime=System.currentTimeMillis();
        System.out.println(currentTime);
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "GBK"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            String [] arr = result.split(",");
            log.info("最新USDCNY汇率：" +new BigDecimal(arr[8]).setScale(4,BigDecimal.ROUND_HALF_UP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
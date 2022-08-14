package hry.mq.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.bean.JsonResult;
import hry.redis.common.utils.RedisService;
import hry.util.sms.HrySmsSendUtils;
import hry.util.sys.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/9/7 14:40
 * @Description:
 */
public class SendCaution implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SendCaution.class);

    private String queue;

    private Integer messageCount;

    private static RedisService redisService = (RedisService) ContextUtil.getBean("redisService");

    public SendCaution(String queue, Integer messageCount) {
        this.queue = queue;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        try {
            redisService.save("deal:stop","1");
            String msg = redisService.get("warning:" + queue);
            if (StringUtils.isEmpty(msg)) {
                informAdmin();
                //30s通知一次
                redisService.save("warning:" + queue, "1", 60);
            }
        } catch (Exception e) {
            log.error("[系统严重错误]redis save error");
        }
        log.warn("[系统严重警告]队列：{} 超长，当前队列数量：{}",queue,messageCount);
    }

    /**
     * 通知管理员
     */
    private void informAdmin() {
        String configAllStr = redisService.get("configCache:all");
        JSONObject configAll = JSON.parseObject(configAllStr);
        Object phones = configAll.get("sms_admin_phone");
        Object apiKey = configAll.get("sms_apiKey");
        Object queueTemplate = configAll.get("sms_queue_stop");
        if(null!=phones&&null!=apiKey&&null!=queueTemplate){
            sendMessage(apiKey.toString(),phones.toString(),queueTemplate.toString());
        }
        Object emails = configAll.get("sms_admin_email");
        if(null!=emails) {
            sendEmail(emails.toString());
        }
    }

    /**
     * 发送邮件
     */
    public void sendEmail(String emailStr) {/*
        String[] emails = emailStr.split(",");
        for (String email : emails) {
            StringBuilder msg = new StringBuilder("当前堵塞队列情况：<br/>");
            try {
                msg.append("队列：").append(queue).append("    当前队列数量：")
                        .append("<font color='red'>").append(messageCount).append("</font>");
                EmailUtil.sendMail(email, "紧急预警", msg.toString());
            } catch (Exception e) {
                log.error("[系统严重错误]堵塞邮件发送异常：{}", e);
            }
        }
    */}

    /**
     * 发送消息
     */
    public void sendMessage(String apiKey, String phoneStr,String template) {
        String[] phones = phoneStr.split(",");
        for (String phone : phones) {
           // ThreadPool.exe(new SendRunnable(phone, TemplateUtil.getTemplate(template,queue,messageCount.toString()),apiKey));
            Map<String,String> map = new HashMap<>();
            map.put("msg","trade");
         //   JsonResult jsonResult = HrySmsSendUtils.send(map,phone,"sms_server_down");
            JsonResult jsonResult = HrySmsSendUtils.send(phone,"sms_server_down","trade");
        }

    }
}
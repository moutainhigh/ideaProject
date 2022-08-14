package hry.licqb.thread;

import hry.licqb.award.service.OutInfoService;
import hry.licqb.record.model.CustomerFreeze;
import hry.licqb.record.service.CustomerFreezeService;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.RedisServer;

import java.util.Map;

/**
 * @author zhouming
 * @date 2021/5/11 15:17
 * @Version 1.0
 */
public class AgainPutIntoRunnable implements Runnable{
    private final Logger log = Logger.getLogger(AgainPutIntoRunnable.class);
    private Long customerId;
    public AgainPutIntoRunnable(Long customerId) {
        super();
        this.customerId = customerId;
    }

    @Override
    public void run() {
        try {
            RedisService redisService =  SpringUtil.getBean("redisService");
            boolean flag = redisService.lock("AGIANPUT:"+customerId);
            if (flag) {
                // 5s后在执行操作，防止脏数据
                Thread.sleep(5000);
                CustomerFreezeService freezeService = SpringUtil.getBean("customerFreezeService");
                freezeService.againPutIntoFreeze(customerId);
                redisService.unLock("AGIANPUT:"+customerId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("再次扣除失败");
        }
    }
}

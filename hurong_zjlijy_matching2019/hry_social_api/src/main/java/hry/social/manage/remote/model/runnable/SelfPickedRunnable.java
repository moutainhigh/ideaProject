package hry.social.manage.remote.model.runnable;

import hry.redis.common.utils.RedisService;
import hry.util.SocialUtil;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;

import java.util.Random;

/**
 * 加载果子信息到REDIS中
 */
public class SelfPickedRunnable implements Runnable {

    private final Logger log = Logger.getLogger(SelfPickedRunnable.class);

    private String id;  //用户ID
    private Long pid;   //果园奖励ID

    private SelfPickedRunnable() {
    }

    public SelfPickedRunnable(String id, Long pid) {
        this.id = id;
        this.pid = pid;
    }

    @Override
    public void run() {
        Random random = new Random();
        String cacheKey = SocialUtil.SOCIAL_GATHERED_CACHE + id;
        RedisService redisService = SpringUtil.getBean("redisService");
        try {
            System.out.println("自己收取果园奖励异步处理 ===>>> userId : " + id + " pickId : " + pid);
            boolean lock = redisService.lock(cacheKey);
            while (!lock) {
                // 短暂休眠后轮询获取锁，避免可能的活锁
                System.out.println("get lock " + cacheKey + " waiting...");
                Thread.sleep(30, random.nextInt(30));
                lock = redisService.lock(cacheKey);
            }
            if (lock) {
                String key = pid.toString();
                redisService.hdel(cacheKey, key);
                redisService.delete(SocialUtil.GATHERED_CACHE + ":" + pid.toString() + ":" + id.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("自己收取果园奖励异步处理失败 ===>>> userId : " + id + " pickId : " + pid);
        } finally {
            redisService.unLock(cacheKey);
        }
    }

}

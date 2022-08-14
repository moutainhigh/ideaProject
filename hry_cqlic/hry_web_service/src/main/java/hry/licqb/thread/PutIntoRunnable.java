package hry.licqb.thread;

import hry.licqb.award.service.OutInfoService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * @author zhouming
 * @date 2019/8/13 14:39
 * @Version 1.0
 */
public class PutIntoRunnable implements Runnable {
    private final Logger log = Logger.getLogger(PutIntoRunnable.class);

    private Map<String, Object> paramMap;

    public PutIntoRunnable(Map<String, Object> paramMap) {
        super();
        this.paramMap = paramMap;
    }

    @Override
    public void run() {
        try {
            // 因redis服务2秒才会将账户金额增减信息写入数据库，所以延迟5秒执行投入存储量操作
            Thread.sleep(5000);
            OutInfoService outInfoService = SpringUtil.getBean("outInfoService");
            outInfoService.putIntoStorageMoney(paramMap);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.info("投入存储量失败");
        }
    }
}

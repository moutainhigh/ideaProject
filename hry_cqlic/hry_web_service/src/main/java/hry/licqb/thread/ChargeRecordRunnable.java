package hry.licqb.thread;

import hry.licqb.award.service.OutInfoService;
import hry.licqb.record.service.DealRecordService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * @author zhouming
 * @date 2019/9/17 15:55
 * @Version 1.0
 */
public class ChargeRecordRunnable implements Runnable{
    private final Logger log = Logger.getLogger(ChargeRecordRunnable.class);

    private Map<String, Object> paramMap;

    public ChargeRecordRunnable(Map<String, Object> paramMap) {
        super();
        this.paramMap = paramMap;
    }

    @Override
    public void run() {
        try {
            // 因redis服务2秒才会将账户金额增减信息写入数据库，所以延迟5秒执行投入存储量操作
            Thread.sleep(5000);
            DealRecordService dealRecordService = SpringUtil.getBean("dealRecordService");
            dealRecordService.addChargeRecord(paramMap);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.info("充值记录添加失败");
        }
    }
}

package hry.ico.service.impl;

import com.alibaba.fastjson.JSON;
import hry.calculate.util.DateUtil;
import hry.ico.model.IcoLockRecord;
import hry.ico.service.IDelayedOrderService;
import hry.ico.service.IcoLockRecordService;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.util.StringUtil;
import org.apache.http.client.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import util.ItemVO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

/**
 * lzy
 */
@Service("iDelayedOrderService")
public class IDelayedOrderServiceImpl implements IDelayedOrderService {
    @Resource
    private IcoLockRecordService icoLockRecordService;
    @Resource
    private MessageProducer messageProducer;

    private Logger logger=Logger.getLogger(IDelayedOrderServiceImpl.class);
    //检测到期订单
    private  Thread takeOrder;

    //延时队列
    private  static DelayQueue<ItemVO<IcoLockRecord>> delayQueue
            =new DelayQueue<>();
    /**
     * 校验释放重复入队
     */
    private static Map<String,String> validateRepeat=new ConcurrentHashMap();
    @Resource
    private RedisService redisService;

    @Override
    public void orderDelay(IcoLockRecord lockRecord) {
      //  if(lockRecord!=null&&lockRecord.getState()==0){
            long now =System.currentTimeMillis();//当前日期
            long expireTime=lockRecord.getReleaseDate().getTime();//释放日期
            if(now>expireTime){//超过释放日期没释放
                // 通过消息去释放订单
                messageProducer.toReleaseOrder(JSON.toJSONString(lockRecord));
            }else{
                String key=lockRecord.getId().toString();
                boolean isRepeat= isRepeat(key);
                if(isRepeat){
                    ItemVO<IcoLockRecord> itemVO=new ItemVO<>(expireTime,lockRecord);
                    delayQueue.put(itemVO);
                    validateRepeat.put(key,key);
                    logger.info(delayQueue.size()+"订单超时时间"+expireTime+"======="+lockRecord.getId());
                }
            }
      //  }
    }
    private boolean isRepeat(String key){
         String value=validateRepeat.get(key);
          if(StringUtil.isNull(value)){
                    return false;
          }
         return true;
    }
    private class TaskOrder implements Runnable{
        @Override
        public void run() {
                while (!Thread.currentThread().isInterrupted()){
                        try {
                            //take() 阻塞方法，直到从延迟队列获取到元素才走
                            ItemVO<IcoLockRecord> itemOrder=delayQueue.take();
                            if(itemOrder!=null){
                                logger.info("处理到期订单");
                                IcoLockRecord icoLockRecord=itemOrder.getData();
                                validateRepeat.remove(icoLockRecord.getId().toString());
                                messageProducer.toReleaseOrder(JSON.toJSONString(icoLockRecord));
                            }
                        }catch (Exception e){
                            logger.error("异常订单");
                        }
                }
        }
    }
    @PostConstruct//初始化调用一次
    public void init(){
        takeOrder=new Thread(new TaskOrder());
        takeOrder.start();
    }
    @PreDestroy  //销毁调用一次
    public void close(){
        takeOrder.interrupt();
    }

    /**
     * 每次初始化需要当天释放订单到内存
     */
    @Override
    public void initDelayOrder(){
        List<IcoLockRecord> list=icoLockRecordService.getWaitReleaseList();
        logger.info(list.size()+"待释放订单");
        if(list!=null&&list.size()>0){
            for (IcoLockRecord lockRecord :list) {
                     orderDelay(lockRecord);
            }
        }
    }

}

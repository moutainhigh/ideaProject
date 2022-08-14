package hry.mq.licqb;

import hry.core.thread.ThreadPool;
import hry.licqb.remote.RemoteLevelService;
import hry.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author zhouming
 * @date 2019/8/21 14:21
 * @Version 1.0
 */
public class MessageUserUpTeamGrade implements MessageListener {
    private Logger log = LoggerFactory.getLogger(MessageUserUpTeamGrade.class);
    @Override
    public void onMessage(Message message){
        String customerId = new String(message.getBody());
        RemoteLevelService remoteLevelService = SpringUtil.getBean("remoteLevelService");
        remoteLevelService.upgradeUserTeamGrade(Long.parseLong(customerId));
        log.info("升级团队等级队列消费成功");
    }
}

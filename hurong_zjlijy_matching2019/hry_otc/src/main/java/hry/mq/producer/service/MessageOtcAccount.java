package hry.mq.producer.service;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import hry.app.account.service.OtcAccountService;
import hry.util.SpringUtil;
import hry.util.dto.Accountadd;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.util.List;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/20 13:18
 * @Description:
 */
public class MessageOtcAccount implements ChannelAwareMessageListener {


    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            System.out.println("====================进入mq消息处理==================");
            List<Accountadd> accountaddlist= JSON.parseArray(new String(message.getBody()), Accountadd.class);
            OtcAccountService otcAccountService= SpringUtil.getBean("otcAccountService");
            otcAccountService.saveAccount(accountaddlist);

        } catch (Exception e) {
           e.printStackTrace();
        }
    }


}

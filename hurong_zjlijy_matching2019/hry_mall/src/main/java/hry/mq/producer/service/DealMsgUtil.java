package hry.mq.producer.service;

import java.util.List;

import com.alibaba.fastjson.JSON;

import hry.util.SpringUtil;

public class DealMsgUtil {



    /**
	 * Cm消费账户操作信息
	 * @param accountaddlist
	 */
    public static void  sendSonAccountaddList(List<hry.cmson.dto.Accountadd> accountaddlist){
        CmSonMessageProducer messageProducer=(CmSonMessageProducer) SpringUtil.getBean("cmSonMessageProducer");
        messageProducer.toCmSonAccount(JSON.toJSONString(accountaddlist));

    }
    
    
}

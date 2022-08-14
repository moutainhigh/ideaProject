package hry.mq.producer;

import java.util.List;

import com.alibaba.fastjson.JSON;

import hry.cm.dto.Accountadd;
import hry.cm.mq.service.CmMessageProducer;
import hry.cmson.mq.service.CmSonMessageProducer;
import hry.util.SpringUtil;

public class DealMsgUtil {

	/**
	 * Cm账户操作信息
	 * @param accountaddlist
	 */
    public static void  sendAccountaddList(List<Accountadd> accountaddlist){
        CmMessageProducer messageProducer=(CmMessageProducer) SpringUtil.getBean("cmMessageProducer");
        messageProducer.toCmAccount(JSON.toJSONString(accountaddlist));

    }

    /**
	 * Cm消费账户操作信息
	 * @param accountaddlist
	 */
    public static void  sendSonAccountaddList(List<hry.cmson.dto.Accountadd> accountaddlist){
        hry.cmson.mq.service.CmSonMessageProducer messageProducer=(CmSonMessageProducer) SpringUtil.getBean("cmSonMessageProducer");
        messageProducer.toCmSonAccount(JSON.toJSONString(accountaddlist));

    }
    
    
}

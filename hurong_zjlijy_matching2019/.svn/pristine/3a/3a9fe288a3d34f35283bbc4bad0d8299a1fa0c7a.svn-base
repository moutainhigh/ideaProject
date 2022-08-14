package hry.mq.producer;

import com.alibaba.fastjson.JSON;
import hry.cm2.dto.Accountadd;
import hry.cm2.mq.service.CmMessageProducer;
import hry.util.SpringUtil;

import java.util.List;

public class DealMsgUtil {

	/**
	 * Cm账户操作信息
	 * @param accountaddlist
	 */
    public static void  sendAccountaddList(List<Accountadd> accountaddlist){
        CmMessageProducer messageProducer=(CmMessageProducer) SpringUtil.getBean("cmMessageProducer");
        messageProducer.toCmAccount(JSON.toJSONString(accountaddlist));

    }
    
    
}

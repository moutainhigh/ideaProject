package hry.mq.producer;

import com.alibaba.fastjson.JSON;
import hry.klg.level.model.KlgCustomerLevel;
import hry.klg.level.service.KlgCustomerLevelService;
import hry.manage.remote.RemoteInterfaceCallbackService;
import hry.manage.remote.model.LmcTransfer;
import hry.util.SpringUtil;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 修改用户级差{升级降级触发}
 * @author CHINA_LSL
 *
 */
public class MessageKlgGradation implements MessageListener {
	private Logger logger = Logger.getLogger(MessageKlgGradation.class);

	@Override
	public void onMessage(Message message) {
		String str = new String(message.getBody());
		logger.info("修改用户级差 rabbitmq message======="+str);
		KlgCustomerLevelService klgCustomerLevelService=SpringUtil.getBean("klgCustomerLevelService");
		try {
			klgCustomerLevelService.updateCustomerRewardConfig(Long.parseLong(str));
		}catch (Exception e){
			logger.info("修改用户级差 异常");
			e.printStackTrace();
		}
	}

}

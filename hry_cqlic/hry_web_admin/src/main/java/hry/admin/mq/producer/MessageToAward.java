package hry.admin.mq.producer;

import hry.bean.JsonResult;
import hry.ico.remote.RemoteIcoRewardService;
import hry.klg.remote.RemoteKlgRewardService;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import javax.annotation.Resource;

/**
 * KLG发奖励
 */
public class MessageToAward implements MessageListener {
	private Logger logger = Logger.getLogger(MessageToAward.class);

	@Resource
	private RemoteKlgRewardService remoteKlgRewardService;
	@Override
	public void onMessage(Message message) {
		String msgText=new String(message.getBody());
		JsonResult isSend=remoteKlgRewardService.sellRewardMQ(msgText);
		if(isSend.getSuccess()){
			logger.info("发奖励完成==========================="+msgText+"==================");
		}
	}
}

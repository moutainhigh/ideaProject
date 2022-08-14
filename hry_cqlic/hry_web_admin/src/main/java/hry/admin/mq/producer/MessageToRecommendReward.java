package hry.admin.mq.producer;

import hry.bean.JsonResult;
import hry.ico.remote.RemoteIcoRewardService;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import javax.annotation.Resource;

/**
 * 推荐奖励发送
 */
public class MessageToRecommendReward implements MessageListener {
	private Logger logger = Logger.getLogger(MessageToRecommendReward.class);

	@Resource
	private RemoteIcoRewardService remoteIcoRewardService;
	@Override
	public void onMessage(Message message) {
		String msgText=new String(message.getBody());
		JsonResult jsonResult=remoteIcoRewardService.recommendReward(msgText);
		if(!jsonResult.getSuccess()){
			logger.info("推荐奖励发送异常==========================="+msgText+"==================");
		}
	}

}

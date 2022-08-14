package hry.admin.mq.producer;

import hry.bean.JsonResult;
import hry.ico.remote.RemoteIcoService;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import javax.annotation.Resource;

/**
 * 释放操作队列
 */
public class MessageToReleaseOrder implements MessageListener {
	private Logger logger = Logger.getLogger(MessageToReleaseOrder.class);

	@Resource
	private RemoteIcoService remoteIcoService;
	@Override
	public void onMessage(Message message) {
		String msgText=new String(message.getBody());
		JsonResult jsonResult=remoteIcoService.releaseMQ(msgText);
		if(!jsonResult.getSuccess()){
			logger.info("定时释放操作异常==========================="+msgText+"==================");
		}
	}

}

package hry.web.message.service.impl;

import hry.util.sys.ContextUtil;
import hry.web.message.service.MessageAsCustomerService;

public class MessageToAllRunnable implements Runnable {
	private Long id;
	

	private  MessageToAllRunnable() {
	}
	
	public MessageToAllRunnable(Long id) {
		this.id = id;
	}

	@Override
	public void run() {
		MessageAsCustomerService messageAsCustomerService =  (MessageAsCustomerService) ContextUtil.getBean("messageAsCustomerService");
		messageAsCustomerService.sendAll(id);
	}

}

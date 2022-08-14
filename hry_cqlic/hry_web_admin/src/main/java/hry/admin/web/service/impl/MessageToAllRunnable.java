package hry.admin.web.service.impl;

import hry.admin.web.service.MessageAsCustomerService;
import hry.util.sys.ContextUtil;

public class MessageToAllRunnable implements Runnable {
	private Long id;
	

	private MessageToAllRunnable () {
	}
	
	public MessageToAllRunnable (Long id) {
		this.id = id;
	}

	@Override
	public void run() {
		MessageAsCustomerService messageAsCustomerService =  (MessageAsCustomerService) ContextUtil.getBean("messageAsCustomerService");
		messageAsCustomerService.sendAll(id);
	}

}

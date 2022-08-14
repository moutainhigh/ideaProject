package hry.util.smsUtil;

import hry.manage.remote.RemoteSmsService;
import hry.util.common.SpringUtil;
import org.apache.log4j.Logger;

/**
 * 发送短信线程
 */
public class SmsSendRunable implements Runnable {

	private final static Logger log = Logger.getLogger(SmsSendRunable.class);

	private SmsSendParam smsSendParam; // 请求短信参数

	public SmsSendRunable (SmsSendParam smsSendParam) {
		this.smsSendParam = smsSendParam;
	}

	@Override
	public void run() {
		RemoteSmsService remoteSmsService = SpringUtil.getBean("remoteSmsService");
		remoteSmsService.sendSms(smsSendParam.toJson());
	}
}

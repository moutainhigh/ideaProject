/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月24日 上午9:36:36
 */
package hry.util.sms;

import hry.manage.remote.RemoteSmsService;
import hry.util.common.SpringUtil;
import org.apache.log4j.Logger;

/**
 * <p>
 * 发送短信线程
 * </p>
 * 
 * @author: Liu Shilei
 * @Date : 2016年5月24日 上午9:36:36
 */
public class SmsRunable implements Runnable {

	private final static Logger log = Logger.getLogger(SmsRunable.class);

	private String url; // 请求地址
	private SmsParam smsParam; // 请求参数

	private String phoneType;

	private String result; // 返回值

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResult() {
		return result;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public SmsRunable(String url, SmsParam smsParam, String phoneType, String phone) {
		this.url = url;
		this.smsParam = smsParam;
		this.phoneType = phoneType;
		this.phone = phone;
	}

	public SmsRunable(String url, SmsParam smsParam, String phone) {
		this.url = url;
		this.smsParam = smsParam;
		this.phone = phone;
	}

	@Override
	public void run() {
		RemoteSmsService remoteSmsService = SpringUtil.getBean("remoteSmsService");
		if (phoneType == null || phoneType.equals("+86")) {
			remoteSmsService.sendsms(smsParam.toJson(), phone);
		} else {
			remoteSmsService.sendsmsHai(smsParam.toJson(), phoneType, phone);
		}
		log.info(result);
	}
}

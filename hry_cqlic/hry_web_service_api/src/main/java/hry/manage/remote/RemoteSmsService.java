package hry.manage.remote;

import java.util.Map;

public interface RemoteSmsService {
	
	/**
	 * 发送短信-新版
	 * @param param
	 */
	void sendSms(String param);
	
	/**
	 * 发送短信
	 * @param
	 */
	public void sendsms(String params, String phone);
	
	
	public void sendsmsHai(String params, String phoneType, String phone);
	/**
	 * 发短信最新2018年11月21日
	 * @param telephone
	 * @param key
	 * @param map
	 */
	public void sendSmsRemind(String telephone, String key, Map<String, String> map, String locale);

}

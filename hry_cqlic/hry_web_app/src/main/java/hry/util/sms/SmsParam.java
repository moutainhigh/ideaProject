/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年9月6日 下午4:01:38
 */
package hry.util.sms;

import com.alibaba.fastjson.JSON;

/**
 * <p>短信参数</p>
 * @author:         Liu Shilei 
 * @Date :          2016年9月6日 下午4:01:38 
 */
public class SmsParam {
	
	
	private Long sendId;//发送ID
	
	private String smsKey;//内部系统调用认证Key;
	
	private String hryCode = "";//短信验证码
	
	private String hryMobilephone = "";//接收人的手机号
	
	private String hrySmstype = "";//短信类型     注册，.....
	
	public SmsParam(){//初始化smsKey
		this.smsKey = "hurongyuseen";
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSmsKey() {
		return smsKey;
	}


	/** 
	 * <p>不用设置，自动附值</p>
	 * @return: String
	 */
	public void setSmsKey(String smsKey) {
		this.smsKey = smsKey;
	}




	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getHryCode() {
		return hryCode;
	}




	/** 
	 * <p>不用设置，自动附值</p>
	 * @return: String
	 */
	public void setHryCode(String hryCode) {
		this.hryCode = hryCode;
	}




	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getHryMobilephone() {
		return hryMobilephone;
	}




	/** 
	 * <p>接收人手机号码</p>
	 * @return: String
	 */
	public void setHryMobilephone(String hryMobilephone) {
		this.hryMobilephone = hryMobilephone;
	}




	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getHrySmstype() {
		return hrySmstype;
	}




	/** 
	 * <p>类型模版类型  静态常量在SmsSendUtil 中</p>
	 * @return: String
	 */
	public void setHrySmstype(String hrySmstype) {
		this.hrySmstype = hrySmstype;
	}




	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getSendId() {
		return sendId;
	}


	/** 
	 * <p>不用设置，自动附值</p>
	 * @return: Long
	 */
	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}


	/**
	 * 转JSON
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年9月6日 下午4:18:25   
	 * @throws:
	 */
	public String toJson(){
		return JSON.toJSONString(this);
	}
	
	
	public static void main(String[] args){
		
		SmsParam smsParam = new SmsParam();
		System.out.println(smsParam.toJson());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

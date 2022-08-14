package hry.manage.remote.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 短信参数类
 */
public class SmsSendParam implements Serializable {

	private String smsKey; // 内部系统调用认证Key;

	private String hryCountry = ""; // 国际区号，以+号开头

	private String hrySmsPhoneNum = ""; // 手机号

	private String hrySmstype = ""; // 短信类型  注册，找回密码.....

	private String hryCode = ""; // 短信验证码

	private String hrySmsLang = ""; // 短信语种

	private String hryParams = ""; // 模板中参数，按模板顺序，用逗号分隔

	public SmsSendParam () {
		this.smsKey = "hurongyuseen";
	}

	public String getSmsKey () {
		return smsKey;
	}

	public void setSmsKey (String smsKey) {
		this.smsKey = smsKey;
	}

	public String getHryCountry () {
		return hryCountry;
	}

	public void setHryCountry (String hryCountry) {
		this.hryCountry = hryCountry;
	}

	public String getHrySmsPhoneNum () {
		return hrySmsPhoneNum;
	}

	public void setHrySmsPhoneNum (String hrySmsPhoneNum) {
		this.hrySmsPhoneNum = hrySmsPhoneNum;
	}

	public String getHrySmstype () {
		return hrySmstype;
	}

	public void setHrySmstype (String hrySmstype) {
		this.hrySmstype = hrySmstype;
	}

	public String getHryCode () {
		return hryCode;
	}

	public void setHryCode (String hryCode) {
		this.hryCode = hryCode;
	}

	public String getHrySmsLang () {
		return hrySmsLang;
	}

	public void setHrySmsLang (String hrySmsLang) {
		this.hrySmsLang = hrySmsLang;
	}

	public String getHryParams () {
		return hryParams;
	}

	public void setHryParams (String hryParams) {
		this.hryParams = hryParams;
	}

	/**
	 * 转json字符串
	 * @return
	 */
	public String toJson(){
		return JSON.toJSONString(this);
	}

	public static void main (String[] args) {
		System.out.println(new SmsSendParam().toJson());
	}
}

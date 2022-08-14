/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月18日 下午3:33:45
 */
package hry.util.sms;

import hry.util.PropertiesUtils;
import hry.util.ThreadPool;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * <p>短信发送工具类</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月18日 下午3:33:45 
 */
public class SmsSendUtil {
	
	/**
	 * 注册
	 */
	public final static String REGIST = "sms_regist";
	
	/**
	 * 找回登录密码
	 */
	public final static String FIND_LOGIN_PW = "sms_find_login_pw";
	
	/**
	 * 修改登录密码
	 */
	public final static String MODIFY_LOGIN_PW = "sms_modify_login_pw";
	
	/**
	 * 设置交易密码
	 */
	public final static String SET_CHANGE_PW = "sms_set_change_pw";
	
	/**
	 * 重置交易密码
	 */
	public final static String RESET_CHANGE_PW = "sms_reset_change_pw";
	
	/**
	 * 取币  提币
	 */
	public final static String TAKE_COIN = "sms_take_coin";
	
	/**
	 * 取钱  提现
	 */
	public final static String TAKE_MONEY = "sms_take_money";
	
	/**
	 * 平仓
	 */
	public final static String CLOSE_POSITION = "sms_close_position";
	
	/**
	 * 平仓警告
	 */
	public final static String CLOSE_POSITION_TIPS = "sms_close_position_tips";
	/**
	 * 修改手机号码
	 */
	public final static String SET_CHANGE_MOBILE = "sms_set_change_mobile";
	
	/**
	 * 提现或提币(后台确认)
	 */
	public final static String WITHDRAW_RMBORCOIN = "sms_withdraw_rmbOrCoin";
	/**
	 * 提现或提币（前台提交）
	 */
	public final static String WITHDRAW_RMBORCOIN_FRONT = "sms_withdraw_rmbOrCoin_front";
	/**
	 * 发送人民币提现驳回短信通知
	 */
	public final static String SMS_RMBWITHDRAW_INVALID = "sms_rmbwithdraw_invalid";
	/**
	 * 发送人民币充值驳回短信通知
	 */
	public final static String SMS_RMBDEPOSIT_INVALID = "sms_rmbdeposit_invalid";
	/**
	 * 发送币提现驳回短信通知
	 */
	public final static String SMS_COINWITHDRAW_INVALID = "sms_coinwithdraw_invalid";
	
	
	/**
	 * 手机认证
	 */
	public final static String TAKE_PHONE = "sms_take_phone";
	
	
	public final static String TAKE_ENPHONE = "sms_en_phone";
	
	public final static String IPANDBROWER = "ipandbrower";

	
	
	/**
	 * 获得smsKey参数
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年5月24日 下午2:54:27   
	 * @throws:
	 */
	public static String getLoginParam(){
		String param = "smsKey="+PropertiesUtils.APP.getProperty("app.smsKey")+"&";
		return param;
	}
	
	/**
	 * 发送注册验证码
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param: mobilePhone  手机号    
	 * @param: length  验证码长度 默认6
	 * @return: 返回验证码,请存放到session中 
	 * @Date :          2016年5月18日 下午3:36:08   
	 * @throws:
	 */
	public static String sendSmsCodeHai(SmsParam smsParam,String phoneType,String phone){
		int length = 6;//6位短信验证码
		//请求路径
		String url = PropertiesUtils.APP.getProperty("app.smsUrl")+"/sdk/send";
		//生成随机码
		String code = RandomStringUtils.random(length, false, true);
		
		
		//如果是提现或提币，则传递另一个币种的参数
		if(SmsSendUtil.WITHDRAW_RMBORCOIN.equals(smsParam.getHrySmstype()) || SmsSendUtil.WITHDRAW_RMBORCOIN_FRONT.equals(smsParam.getHrySmstype())
			|| SmsSendUtil.SMS_RMBWITHDRAW_INVALID.equals(smsParam.getHrySmstype()) || SmsSendUtil.SMS_COINWITHDRAW_INVALID.equals(smsParam.getHrySmstype())	
			|| SmsSendUtil.SMS_RMBDEPOSIT_INVALID.equals(smsParam.getHrySmstype())	){
			//设置随机码
			smsParam.setHryCode(smsParam.getHryCode());
		}else{
			//设置随机码
			smsParam.setHryCode(code);
		}
		
		smsParam.setHryMobilephone(phone);
		
		SmsRunable smsRunable = new SmsRunable(url,smsParam,phoneType,phone);
		ThreadPool.exe(smsRunable);
		
		return code;
	}
	
	/**
	 * 发送注册验证码
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param: mobilePhone  手机号    
	 * @param: length  验证码长度 默认6
	 * @return: 返回验证码,请存放到session中 
	 * @Date :          2016年5月18日 下午3:36:08   
	 * @throws:
	 */
	public static String sendSmsCode(SmsParam smsParam){
		int length = 6;//6位短信验证码
		//请求路径
		String url = PropertiesUtils.APP.getProperty("app.smsUrl")+"/sdk/send";
		//生成随机码
		String code = RandomStringUtils.random(length, false, true);
		
		
		//如果是提现或提币，则传递另一个币种的参数
		if(SmsSendUtil.WITHDRAW_RMBORCOIN.equals(smsParam.getHrySmstype()) || SmsSendUtil.WITHDRAW_RMBORCOIN_FRONT.equals(smsParam.getHrySmstype())
			|| SmsSendUtil.SMS_RMBWITHDRAW_INVALID.equals(smsParam.getHrySmstype()) || SmsSendUtil.SMS_COINWITHDRAW_INVALID.equals(smsParam.getHrySmstype())	
			|| SmsSendUtil.SMS_RMBDEPOSIT_INVALID.equals(smsParam.getHrySmstype())	){
			//设置随机码
			smsParam.setHryCode(smsParam.getHryCode());
		}else{
			//设置随机码
			smsParam.setHryCode(code);
		}
		
		SmsRunable smsRunable = new SmsRunable(url,smsParam,smsParam.getHryMobilephone());
		ThreadPool.exe(smsRunable);
		
		return code;
	}
	
	/**
	 * 替换HRY_
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param content
	 * @return: void 
	 * @Date :          2016年9月6日 下午6:53:28   
	 * @throws:
	 */
	public static String replaceKey(String content,SmsParam smsParam){
		return content.replace("HRY_CODE", smsParam.getHryCode());
	}
	
	
	public static void main(String[] args){
		System.out.println(RandomStringUtils.random(6, false, true));
	}

	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param hrySmstype
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年9月6日 下午7:19:53   
	 * @throws:
	 */
	public static String getSendTypeValue(String hrySmstype) {
		if(REGIST.equals(hrySmstype)){
			return "注册";
		}else if(FIND_LOGIN_PW.equals(hrySmstype)){
			return "找回登录密码";
		}else if(MODIFY_LOGIN_PW.equals(hrySmstype)){
			return "修改登录密码";
		}else if(SET_CHANGE_PW.equals(hrySmstype)){
			return "设置交易密码";
		}else if(RESET_CHANGE_PW.equals(hrySmstype)){
			return "重置交易密码";
		}else if(TAKE_COIN.equals(hrySmstype)){
			return "取币提币";
		}else if(TAKE_MONEY.equals(hrySmstype)){
			return "取钱 提现";
		}else if(CLOSE_POSITION.equals(hrySmstype)){
			return "平仓";
		}else if(CLOSE_POSITION_TIPS.equals(hrySmstype)){
			return "平仓警告";
		}else if(SET_CHANGE_MOBILE.equals(hrySmstype)){
			return "修改手机号码";
		}else if(WITHDRAW_RMBORCOIN.equals(hrySmstype)){
			return "提现或提币(后台)";
		}else if(WITHDRAW_RMBORCOIN_FRONT.equals(hrySmstype)){
			return "提现或提币(前台)";
		}else if(TAKE_PHONE.equals(hrySmstype)){
			return "手机认证";
		}else if(TAKE_ENPHONE.equals(hrySmstype)){
			return "海外手机认证";
		}
		return "";
	}
	
}

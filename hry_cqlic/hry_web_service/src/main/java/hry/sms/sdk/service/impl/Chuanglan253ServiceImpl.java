package hry.sms.sdk.service.impl;

import hry.bean.JsonResult;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.manage.remote.model.SmsSendParam;
import hry.util.sys.ContextUtil;
import hry.sms.sdk.service.SdkService;
import hry.sms.send.model.AppSmsSend;
import hry.sms.utils.hx.HttpSend;
import hry.web.remote.RemoteAppConfigService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
/**
 * 创蓝253短信接口
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年2月27日 上午10:25:11
 */
@Service("Chuanglan253Service")
public class Chuanglan253ServiceImpl implements SdkService{
	private static Logger logger = Logger.getLogger(Chuanglan253ServiceImpl.class);
	//提交地址
	private static final String INTERNATIONAL_URL="http://sms.253.com/msg/send";
	
	//返回的错误码信息
	@SuppressWarnings("serial")
	public final static Map<String,String> resmap = new HashMap<String,String>() {{    
	    put("101", "无此用户");    
	    put("102", "密码错误");    
	    put("103", "提交过快（提交速度超过流速限制）");    
	    put("104", "系统忙（因平台侧原因，暂时无法处理提交的短信）");    
	    put("105", "敏感短信（短信内容包含敏感词）");    
	    put("106", "消息长度错（>536或<=0）");    
	    put("107", "包含错误的手机号码");    
	    put("108", "手机号码个数错（群发>50000或<=0）");    
	    put("109", "无发送额度（该用户可用短信数已使用完）");    
	    put("110", "不在发送时间内");    
	    put("113", "extno格式错（非数字或者长度不对）");    
	    put("116", "签名不合法或未带签名（用户必须带签名的前提下）");    
	    put("117", "IP地址认证错,请求调用的IP地址不是系统登记的IP地址");    
	    put("118", "用户没有相应的发送权限（账号被禁止发送）");    
	    put("119", "用户已过期");    
	    put("120", "违反放盗用策略(日发限制) --自定义添加");    
	    put("121", "必填参数。是否需要状态报告，取值true或false");    
	    put("122", "5分钟内相同账号提交相同消息内容过多");    
	    put("123", "发送类型错误");    
	    put("124", "白模板匹配错误");    
	    put("125", "匹配驳回模板，提交失败");    
	    put("126", "审核通过模板匹配错误");    
	}}; 
	
	@Override
	public boolean sendSms(AppSmsSend appSmsSend,SmsParam smsParam,String phone) {
		//根据短信类型获得短信模板
		RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
		String hxrt_username = remoteAppConfigService.getValueByKey("sms_username");
		String hxrt_password = remoteAppConfigService.getValueByKey("sms_password");
		if("0".equals(smsOpen)){//接口是否开启
			String value = remoteAppConfigService.getValueByKey(smsParam.getHrySmstype());
			String sendContent = SmsSendUtil.replaceKey(value, smsParam);
			String encodedContent = null;
			try {
				encodedContent = URLEncoder.encode(sendContent, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("创蓝253短信接口,获取替换短信内容异常！！！");
				e.printStackTrace();
				return false;
			}
			
			StringBuffer strBuf = new StringBuffer();
			strBuf.append("un=").append(hxrt_username);
			strBuf.append("&pw=").append(hxrt_password);
			strBuf.append("&phone=").append(smsParam.getHryMobilephone().replaceAll(" ", ""));
			strBuf.append("&msg=").append(encodedContent);
			strBuf.append("&rd=0");
			logger.error("创蓝253接口参数==="+strBuf.toString());
			String sendSms=HttpSend.postSend(INTERNATIONAL_URL, strBuf.toString());
			//logger.error(sendSms);
			//20170227113026,017022711302622800逗号后第一位为0，代表成功，0后面的属于流水号吧
			//20170227113026,101  失败101为失败原因
			
			if(sendSms!=null){
				//截取字符串
				String[] ress=sendSms.split(",");
				if(ress[1].startsWith("0")){
					appSmsSend.setThirdPartyResult("发送成功！");
					appSmsSend.setSendContent(sendContent);
					return true;
				}else{
					if (resmap.containsKey(ress[1])) {
						appSmsSend.setThirdPartyResult(resmap.get(ress[1]));
					}else{
						appSmsSend.setThirdPartyResult("发送失败，创蓝返回："+ress);
					}
					appSmsSend.setSendContent(sendContent);
					return false;
				}
			}else{
				appSmsSend.setThirdPartyResult("发送失败，调用接口异常！");
				appSmsSend.setSendContent(sendContent);
				return false;
			}
		}else{
			appSmsSend.setThirdPartyResult("【系统短信功能未开启】");
			return false;
		}
		
		
	}

	@Override
	public JsonResult checkCard(String name, String idCard) {
		return null;
	}

	@Override
	public boolean sendSmsHai(AppSmsSend appSmsSend, SmsParam smsParam, String Phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendSms(AppSmsSend appSmsSend, SmsSendParam smsSendParam) {
		// TODO Auto-generated method stub
		return false;
	}
	

	
}

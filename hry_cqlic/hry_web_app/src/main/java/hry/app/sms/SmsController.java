/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: Liu Shilei
 * @version: V1.0
 * @Date: 2016年8月25日 下午3:21:39
 */
package hry.app.sms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hry.app.jwt.TokenUtil;
import hry.app.thread.EmailRunnable;
import hry.bean.ApiJsonResult;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.message.model.MessageType;
import hry.redis.common.utils.RedisService;
import hry.util.IpUtil;
import hry.util.ThreadPool;
import hry.util.common.SpringUtil;
import hry.util.sms.SmsParam;
import hry.util.sms.SmsSendUtil;
import hry.util.smsUtil.SmsSendParam;
import hry.util.smsUtil.SmsSendUtils;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Liu Shilei
 * @Date : 2016年8月25日 下午3:21:39
 */
@Controller
@RequestMapping("/sms")
@Api(value= "消息类（必须传token）", description ="消息类（必须传token）", tags = "消息类（必须传token）")
public class SmsController {

	private final static Logger log = Logger.getLogger(SmsController.class);

	//短信限流次数
	private static final Integer LIMIT_SMS = 3;

	private String tel = "tel:";

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());

		/**
		 * 防止XSS攻击，并且带去左右空格功能
		 */
		binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
	}

	@Resource
	private RedisService redisService;

	@Resource
	private RemoteManageService remoteManageService;

	/**
	 * 个人中心-安全设置-绑定邮箱-发送邮件验证码
	 *
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "个人中心-安全设置-绑定邮箱-发送邮件验证码", httpMethod = "POST", notes = "个人中心-安全设置-绑定邮箱-发送邮件验证码")
	@RequestMapping("/user/sendEmailValidCode")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
	@ResponseBody
	@RequiresAuthentication
	public ApiJsonResult sendEmailValidCode (
			@ApiParam(name = "email", value = "邮箱账号", required = true) @RequestParam("email") String email,
			@ApiParam(name = "loginPwd", value = "登录密码", required = true) @RequestParam("loginPwd") String loginPwd,
			@ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
			HttpServletRequest request) {
		ApiJsonResult jsonResult = new ApiJsonResult();
		//邮箱不能为空
		if (StringUtils.isEmpty(email)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("emailisnull"));
			return jsonResult;
		}
		email = email.trim().replace(" ", "");

		// 验证登录密码不能为空
		if (StringUtils.isEmpty(loginPwd)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
			return jsonResult;
		}

		User user = TokenUtil.getUser(request);
		if (user != null) {
			RemoteResult swapEmail = remoteManageService.swapEmail(user.getCustomerId(), email, loginPwd);
			if (!swapEmail.getSuccess()) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff(swapEmail.getMsg()));
				return jsonResult;
			}

			// 发送邮件
			StringBuffer sb = new StringBuffer();
			String code = RandomStringUtils.random(6, false, true);
			sb.append(SpringUtil.diff("youjianyanzhengma") + ":");
			sb.append(code);
			log.info(sb.toString());
			redisService.save("Valid:" + email + ":" + "setEmailCode", code);
			String type = "2";
			String url = code;
			ThreadPool.exe(new EmailRunnable(email, type, langCode, url));
			jsonResult.setObj(code);
			jsonResult.setSuccess(true);
		} else {
			jsonResult.setSuccess(false);
		}
		return jsonResult;
	}

	/**
	 * 个人中心-安全设置-绑定手机-发送手机验证码
	 *
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "个人中心-安全设置-绑定手机-发送手机验证码", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-安全设置-绑定手机-发送手机验证码")
	@RequestMapping("/user/sendPhoneValidCode")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
	@ResponseBody
	@RequiresAuthentication
	public ApiJsonResult sendPhoneValidCode (
			@ApiParam(name = "mobile", value = "手机号", required = true) @RequestParam("mobile") String mobile,
			@ApiParam(name = "country", value = "国家", required = true) @RequestParam("country") String country,
			@ApiParam(name = "passWord", value = "登录密码", required = true) @RequestParam("passWord") String passWord,
			HttpServletRequest request) {
		//限流
		ApiJsonResult smsResult = checkSmsSend(request);
		if (smsResult.getSuccess().equals(false)) {
			return smsResult;
		}
		if (!verificationOrder(request).getSuccess()) {
			return verificationOrder(request);
		}
		ApiJsonResult jsonResult = new ApiJsonResult();

		if (StringUtils.isEmpty(passWord)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
			return jsonResult;
		}
		if (StringUtils.isEmpty(mobile)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("telphone_is_not_null"));
			return jsonResult;
		}

		User user = TokenUtil.getUser(request);
		if (user != null) {
			RemoteResult result = remoteManageService.setVailPassWord(user.getUsername(), passWord);
			if (result != null) {
				if (result.getSuccess()) {
					//判断手机号是否被注册
					RemoteResult remoteResult = remoteManageService.selectPhone(mobile);
					if (remoteResult.getSuccess()) {
						jsonResult.setSuccess(false);
						jsonResult.setMsg(SpringUtil.diff("phonechongfu"));
						return jsonResult;
					} else {
						// 设置短信验证码到session中
						/*SmsParam smsParam = new SmsParam();
						smsParam.setHryMobilephone(mobile);
						smsParam.setHrySmstype(SmsSendUtil.TAKE_ENPHONE);
						if ("+86".equals(country)) {
							smsParam.setHrySmstype(SmsSendUtil.TAKE_PHONE);
							String sendSmsCodeHai = SmsSendUtil.sendSmsCodeHai(smsParam, country, mobile);
							System.out.println("sendSmsCodeHai = " + sendSmsCodeHai);
							// 保存手机验证码
							redisService.save("SMS:setPhone:" + mobile, sendSmsCodeHai, 120);
						} else {
							smsParam.setHrySmstype(SmsSendUtil.TAKE_ENPHONE);
							String sendSmsCodeHai = SmsSendUtil.sendSmsCodeHai(smsParam, country, mobile);
							System.out.println("sendSmsCodeHai = " + sendSmsCodeHai);
							redisService.save("SMS:setPhone:" + mobile, sendSmsCodeHai, 120);
						}*/
						/**替换新版短信发送*/
						// 发送短信验证码
						Locale locale = LocaleContextHolder.getLocale();
						SmsSendParam smsSendParam = new SmsSendParam();
						smsSendParam.setHryCountry(country);
						smsSendParam.setHrySmsLang(StringUtils.isEmpty(user.getCommonLanguage())?locale.toString():user.getCommonLanguage());
						smsSendParam.setHrySmsPhoneNum(mobile);
						smsSendParam.setHrySmstype(MessageType.MESSAGE_VERIFICATION_CODE.getKey());
						String sendSmsCode = SmsSendUtils.sendSms(smsSendParam);
						redisService.save("SMS:setPhone:" + mobile, sendSmsCode, 120);
						
						jsonResult.setSuccess(true);
						jsonResult.setMsg(SpringUtil.diff("sms_success"));
						return jsonResult;
					}
				} else {
					jsonResult.setSuccess(false);
					jsonResult.setMsg(SpringUtil.diff(result.getMsg()));
					return jsonResult;
				}
			} else {
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff("sms_error"));
				return jsonResult;
			}
		} else {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("before_login"));
			return jsonResult;
		}
	}

	/**
	 * 获得手机二次认证短信
	 *
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "获得手机二次认证短信", httpMethod = "POST", response = ApiJsonResult.class, notes = "获得手机二次认证短信")
	@PostMapping("/smsSecondMobile")
	@ResponseBody
	public ApiJsonResult smsSecondMobile(
			@ApiParam(name = "mobile", value = "手机号", required = true) @RequestParam("mobile") String mobile,
			@ApiParam(name = "country", value = "国家", required = true) @RequestParam("country") String country,
			HttpServletRequest request) {
		ApiJsonResult jsonResult = new ApiJsonResult();
		if (!verificationOrder(request).getSuccess()) {
			return verificationOrder(request);
		}
		//限流
		ApiJsonResult smsResult = checkSmsSend(request);
		if(smsResult.getSuccess().equals(false)){
			return smsResult;
		}
		//手机号，国家不能为空
		if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(country)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("withdrawal_no_null"));
			return jsonResult;
		}


		/*// 设置短信验证码到redis中
		SmsParam smsParam = new SmsParam();
		smsParam.setHryMobilephone(mobile);
		if (country.equals("+86")) {
			smsParam.setHrySmstype(SmsSendUtil.TAKE_PHONE);
			String sendSmsCodeHai = SmsSendUtil.sendSmsCodeHai(smsParam, country, mobile);
			redisService.save("SMS:smsSecondMobile:" + mobile, sendSmsCodeHai, 120);
		} else {
			smsParam.setHrySmstype(SmsSendUtil.TAKE_ENPHONE);
			String sendSmsCodeHai = SmsSendUtil.sendSmsCodeHai(smsParam, country, mobile);
			redisService.save("SMS:smsSecondMobile:" + mobile, sendSmsCodeHai, 120);
		}*/
		
		Locale locale = LocaleContextHolder.getLocale();
		// 发送短信验证码
		SmsSendParam smsSendParam = new SmsSendParam();
		smsSendParam.setHryCountry(country);
		smsSendParam.setHrySmsLang(locale.toString());
		smsSendParam.setHrySmsPhoneNum(mobile);
		smsSendParam.setHrySmstype(MessageType.MESSAGE_VERIFICATION_CODE.getKey());
		String sendSmsCode = SmsSendUtils.sendSms(smsSendParam);
		redisService.save("SMS:smsSecondMobile:" + mobile, sendSmsCode, 120);
		
		jsonResult.setSuccess(true);
		jsonResult.setMsg(SpringUtil.diff("sms_success"));
		return jsonResult;
	}

	/**
	 * 个人中心-设置资金密码-获得手机二次认证短信
	 *
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "个人中心-设置资金密码-获得手机二次认证短信", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-设置资金密码-获得手机二次认证短信")
	@RequestMapping("/user/getApwSmsCode")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
	@ResponseBody
	@RequiresAuthentication
	public ApiJsonResult getApwSmsCode (
			@ApiParam(name = "accountPassWord", value = "交易密码", required = true) @RequestParam("accountPassWord") String accountPassWord,
			@ApiParam(name = "reaccountPassWord", value = "确认密码", required = true) @RequestParam("reaccountPassWord") String reaccountPassWord,
			HttpServletRequest request) {

		if (!verificationOrder(request).getSuccess()) {
			return verificationOrder(request);
		}
		//限流
		ApiJsonResult smsResult = checkSmsSend(request);
		if (smsResult.getSuccess().equals(false)) {
			return smsResult;
		}
		ApiJsonResult jsonResult = new ApiJsonResult();
		User user = TokenUtil.getUser(request);
		if (user != null) {
			if (StringUtils.isEmpty(accountPassWord)) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff("jypwd_no_null"));
				return jsonResult;
			}
			if (StringUtils.isEmpty(reaccountPassWord)) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff("okpwd_no_null"));
				return jsonResult;
			}

			if (!accountPassWord.equals(reaccountPassWord)) {//两次密码不一致
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff("twopwd_is_diff"));
				return jsonResult;
			} else {
				/*// 设置短信验证码到session中
				SmsParam smsParam = new SmsParam();
				smsParam.setHryMobilephone(user.getMobile());
				String sendSmsCodeHai = "";
				if ("+86".equals(user.getCountry())) {
					smsParam.setHrySmstype(SmsSendUtil.TAKE_PHONE);
					sendSmsCodeHai = SmsSendUtil.sendSmsCodeHai(smsParam, user.getCountry(), user.getMobile());
				} else {
					smsParam.setHrySmstype(SmsSendUtil.TAKE_ENPHONE);
					sendSmsCodeHai = SmsSendUtil.sendSmsCodeHai(smsParam, user.getCountry(), user.getMobile());
				}
				redisService.save("Valid:accountpwSmsCode" + user.getMobile(), sendSmsCodeHai);*/
				
				// 发送短信验证码
				Locale locale = LocaleContextHolder.getLocale();
				SmsSendParam smsSendParam = new SmsSendParam();
				smsSendParam.setHryCountry(user.getCountry());
				smsSendParam.setHrySmsLang(StringUtils.isEmpty(user.getCommonLanguage())?locale.toString():user.getCommonLanguage());
				smsSendParam.setHrySmsPhoneNum(user.getMobile());
				smsSendParam.setHrySmstype(MessageType.MESSAGE_VERIFICATION_CODE.getKey());
				String sendSmsCode = SmsSendUtils.sendSms(smsSendParam);
				redisService.save("Valid:accountpwSmsCode" + user.getMobile(), sendSmsCode);

				jsonResult.setSuccess(true);
				jsonResult.setMsg(SpringUtil.diff("sms_success"));
				return jsonResult;
			}
		} else {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("before_login"));
			return jsonResult;
		}
	}

	/**
	 * 获得手机找回密码短信验证码
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "获得手机找回密码短信验证码", httpMethod = "POST", response = ApiJsonResult.class, notes = "获得手机找回密码短信验证码")
	@RequestMapping("/smsForgetMobile")
	@ResponseBody
	public ApiJsonResult smsForgetMobile (
			@ApiParam(name = "mobile", value = "手机号", required = true) @RequestParam("mobile") String mobile,
			@ApiParam(name = "country", value = "国家", required = true) @RequestParam("country") String country,
			HttpServletRequest request) {
		ApiJsonResult jsonResult = new ApiJsonResult();

		if (!verificationOrder(request).getSuccess()) {
			return verificationOrder(request);
		}

		//手机号，国家不能为空
		if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(country)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("withdrawal_no_null"));
			return jsonResult;
		}

		//验证极验
		/*boolean b = GTvalidate.validateGT(request);
		if(!b){
			return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("gt_valid_fail"));
		}*/

		String ip = IpUtil.getIp(request);
		Integer resetPasswordCount = 0;
		String i = redisService.get("SMS:smsForgetCount:" + ip);
		if (!StringUtils.isEmpty(i)) {
			resetPasswordCount = Integer.parseInt(i);
		}
		if (resetPasswordCount >= 5) {
			redisService.setTime("SMS:smsForgetCount:" + ip, 1 * 60 * 60);
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("smscodesend5"));
			return jsonResult;
		}

		/*// 设置短信验证码到redis中
		SmsParam smsParam = new SmsParam();
		smsParam.setHryMobilephone(mobile);
		if (country.equals("+86")) {
			smsParam.setHrySmstype(SmsSendUtil.TAKE_PHONE);
			String sendSmsCodeHai = SmsSendUtil.sendSmsCodeHai(smsParam, country, mobile);
			redisService.save("SMS:smsForgetMobile:" + mobile, sendSmsCodeHai, 120);
		} else {
			smsParam.setHrySmstype(SmsSendUtil.TAKE_ENPHONE);
			String sendSmsCodeHai = SmsSendUtil.sendSmsCodeHai(smsParam, country, mobile);
			redisService.save("SMS:smsForgetMobile:" + mobile, sendSmsCodeHai, 120);
		}*/
		
		Locale locale = LocaleContextHolder.getLocale();
		// 发送短信验证码
		SmsSendParam smsSendParam = new SmsSendParam();
		smsSendParam.setHryCountry(country);
		smsSendParam.setHrySmsLang(locale.toString());
		smsSendParam.setHrySmsPhoneNum(mobile);
		smsSendParam.setHrySmstype(MessageType.MESSAGE_VERIFICATION_CODE.getKey());
		String sendSmsCode = SmsSendUtils.sendSms(smsSendParam);
		redisService.save("SMS:smsForgetMobile:" + mobile, sendSmsCode, 120);
		
		resetPasswordCount++;
		redisService.save("SMS:smsForgetCount:" + ip, String.valueOf(resetPasswordCount), 1 * 60 * 60);
		redisService.save("SMS:forgetCountry", country);
		redisService.save("SMS:forgetMobile", mobile);

		jsonResult.setSuccess(true);
		jsonResult.setMsg(SpringUtil.diff("sms_success"));
		return jsonResult;
	}

	/**
	 * 短信限流
	 * @param request
	 * @return
	 */
	private ApiJsonResult checkSmsSend (HttpServletRequest request) {
		ApiJsonResult jsonResult = new ApiJsonResult();
		String ipAdrress = getIpAdrress(request);
		SimpleDateFormat sp = new SimpleDateFormat("HHmm");
		String fdate = sp.format(new Date());
		String count = redisService.get("resetPwd:" + ipAdrress + ":" + fdate);
		if (count == null) {
			redisService.save("resetPwd:" + ipAdrress + ":" + fdate, "1", 60);
		} else {
			if (Integer.valueOf(count) > LIMIT_SMS) {
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff("smssendpf"));
				return jsonResult;
			} else {
				redisService.save("resetPwd:" + ipAdrress + ":" + fdate, (Integer.valueOf(count) + 1) + "", 120);
			}
		}
		jsonResult.setSuccess(true);
		return jsonResult;
	}

	/**
	 * 获取Ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAdrress (HttpServletRequest request) {
		String Xip = request.getHeader("X-Real-IP");
		String XFor = request.getHeader("X-Forwarded-For");
		if (com.alibaba.dubbo.common.utils.StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = XFor.indexOf(",");
			if (index != -1) {
				return XFor.substring(0, index);
			} else {
				return XFor;
			}
		}
		XFor = Xip;
		if (com.alibaba.dubbo.common.utils.StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
			return XFor;
		}
		if (com.alibaba.dubbo.common.utils.StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("Proxy-Client-IP");
		}
		if (com.alibaba.dubbo.common.utils.StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("WL-Proxy-Client-IP");
		}
		if (com.alibaba.dubbo.common.utils.StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("HTTP_CLIENT_IP");
		}
		if (com.alibaba.dubbo.common.utils.StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (com.alibaba.dubbo.common.utils.StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getRemoteAddr();
		}
		return XFor;
	}

	private ApiJsonResult verificationOrder (HttpServletRequest request) {
		ApiJsonResult jsonResult = new ApiJsonResult();
		String ip = IpUtil.getIp(request);

		Integer telTime = 5;
		Integer telCount = 3;
		String telValue = redisService.get(tel + ip);
		if (telValue == null || "".equals(telValue)) {
			redisService.save(tel + ip, "1", telTime);
		} else {
			Integer num = Integer.valueOf(telValue);
			if (num >= telCount) {
				jsonResult.setCode("0000");
				jsonResult.setMsg(SpringUtil.diff("sms_tooMuch"));
				jsonResult.setSuccess(false);
				return jsonResult;
			}
			num++;
			redisService.save(tel + ip, String.valueOf(num), telTime);
		}
		jsonResult.setSuccess(true);
		return jsonResult;
	}

	/**
	 * 用户注册发送验证码
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "用户注册手机发送验证码", httpMethod = "POST", response = ApiJsonResult.class, notes = "用户注册手机发送验证码")
	@RequestMapping("/registSmsCode")
	@ResponseBody
	public ApiJsonResult registSmsCode(
			@ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
			@ApiParam(name = "mobile", value = "手机号", required = true) @RequestParam("mobile") String mobile,
			@ApiParam(name = "country", value = "国家", required = true) @RequestParam("country") String country,
			@ApiParam(name = "geetest_challenge", value = "极验验证返回参数", required = false) @RequestParam(value = "geetest_challenge",required = false) String geetest_challenge,
			@ApiParam(name = "geetest_validate", value = "极验验证返回参数", required = false) @RequestParam(value = "geetest_validate",required = false) String geetest_validate,
			@ApiParam(name = "geetest_seccode", value = "极验验证返回参数", required = false) @RequestParam(value = "geetest_seccode", required = false) String geetest_seccode,
			HttpServletRequest request) {
		if (!verificationOrder(request).getSuccess()) {
			return verificationOrder(request);
		}
		//限流
		ApiJsonResult smsResult = checkSmsSend(request);
		if(smsResult.getSuccess().equals(false)){
			return smsResult;
		}
		ApiJsonResult jsonResult = new ApiJsonResult();

		//验证极验
		/*boolean b = GTvalidate.validateGT(request);
		if(!b){
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("gt_valid_fail"));
			return jsonResult;
		}*/

		if (StringUtils.isEmpty(mobile)) {// 手机号判空
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("tel_is_not_null"));
			return jsonResult;
		}  else {
			//判断手机号是否被注册
			RemoteResult remoteResult = remoteManageService.selectPhone(mobile);
			if (remoteResult.getSuccess()){
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff("user_reg"));
				return jsonResult;
			}else{
				Integer registSmsCodeCount=0;
				String i = redisService.get("registSmsCodeCount:"+mobile);
				if(!StringUtils.isEmpty(i)){
					registSmsCodeCount = Integer.parseInt(i);
				}
				if(registSmsCodeCount >= 5){
					redisService.setTime("registSmsCodeCount:"+mobile, 60*60);
					jsonResult.setSuccess(false);
					jsonResult.setMsg(SpringUtil.diff("smscodesend5"));
					return jsonResult;
				}
				/*// 设置短信验证码到session中
				SmsParam smsParam = new SmsParam();
				String sendSmsCodeHai = "";
				if (country.equals("+86")) {
					smsParam.setHrySmstype(SmsSendUtil.TAKE_PHONE);
					sendSmsCodeHai  = SmsSendUtil.sendSmsCodeHai(smsParam, country, mobile);
				} else {
					smsParam.setHrySmstype(SmsSendUtil.TAKE_ENPHONE);
					sendSmsCodeHai = SmsSendUtil.sendSmsCodeHai(smsParam, country, mobile);
				}*/
				
				// 发送短信验证码
				SmsSendParam smsSendParam = new SmsSendParam();
				smsSendParam.setHryCountry(country);
				smsSendParam.setHrySmsLang(langCode);
				smsSendParam.setHrySmsPhoneNum(mobile);
				smsSendParam.setHrySmstype(MessageType.MESSAGE_VERIFICATION_CODE.getKey());
				String sendSmsCode = SmsSendUtils.sendSms(smsSendParam);
				
				registSmsCodeCount++;
				redisService.save("registSmsCodeCount:"+mobile, String.valueOf(registSmsCodeCount), 120);
				redisService.save("SMS:registSmsCode:" + mobile, sendSmsCode);
				/*
				request.getSession().removeAttribute("registCode");
				request.getSession().removeAttribute("telCodeError" + mobile);*/
				jsonResult.setSuccess(true);
				jsonResult.setMsg(SpringUtil.diff("sms_success"));
				return jsonResult;
			}
		}
	}


	/**
	 * 用户注册发送验证码（有图形验证码）
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "用户注册手机发送验证码（有图形验证码）", httpMethod = "POST", response = ApiJsonResult.class, notes = "用户注册手机发送验证码（有图形验证码）")
	@RequestMapping("/registSmsCodeByImgCode")
	@ResponseBody
	public ApiJsonResult registSmsCodeByImgCode(
			@ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
			@ApiParam(name = "mobile", value = "手机号", required = true) @RequestParam("mobile") String mobile,
			@ApiParam(name = "country", value = "国家", required = true) @RequestParam("country") String country,
			@ApiParam(name = "imgCode", value = "图形验证码", required = true) @RequestParam(value = "imgCode") String imgCode,
			HttpServletRequest request) {
		if (!verificationOrder(request).getSuccess()) {
			return verificationOrder(request);
		}
		//限流
		ApiJsonResult smsResult = checkSmsSend(request);
		if(smsResult.getSuccess().equals(false)){
			return smsResult;
		}
		ApiJsonResult jsonResult = new ApiJsonResult();

		// 图形验证码不能为空
		if (StringUtils.isEmpty(imgCode)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("tuxingyanzhengmaweikong"));
			return jsonResult;
		}
		String session_registcode =request.getSession().getAttribute("mobileRegist")==null?null:request.getSession().getAttribute("mobileRegist").toString();
		if (StringUtils.isEmpty(session_registcode)) {
			return jsonResult.setSuccess(false).setObj(imgCode).setMsg("验证码已失效");

		}
		if (!imgCode.equalsIgnoreCase(session_registcode)) {
			return jsonResult.setSuccess(false).setObj(imgCode).setMsg(SpringUtil.diff("tx_error"));
		}

		if (StringUtils.isEmpty(mobile)) {// 手机号判空
			jsonResult.setSuccess(false);
			jsonResult.setMsg(SpringUtil.diff("tel_is_not_null"));
			return jsonResult;
		}  else {
			//判断手机号是否被注册
			RemoteResult remoteResult = remoteManageService.selectPhone(mobile);
			if (remoteResult.getSuccess()){
				jsonResult.setSuccess(false);
				jsonResult.setMsg(SpringUtil.diff("user_reg"));
				return jsonResult;
			}else{
				Integer registSmsCodeCount=0;
				String i = redisService.get("registSmsCodeCount:"+mobile);
				if(!StringUtils.isEmpty(i)){
					registSmsCodeCount = Integer.parseInt(i);
				}
				if(registSmsCodeCount >= 5){
					redisService.setTime("registSmsCodeCount:"+mobile, 60*60);
					jsonResult.setSuccess(false);
					jsonResult.setMsg(SpringUtil.diff("smscodesend5"));
					return jsonResult;
				}
				/*// 设置短信验证码到session中
				SmsParam smsParam = new SmsParam();
				String sendSmsCodeHai = "";
				if (country.equals("+86")) {
					smsParam.setHrySmstype(SmsSendUtil.TAKE_PHONE);
					sendSmsCodeHai  = SmsSendUtil.sendSmsCodeHai(smsParam, country, mobile);
				} else {
					smsParam.setHrySmstype(SmsSendUtil.TAKE_ENPHONE);
					sendSmsCodeHai = SmsSendUtil.sendSmsCodeHai(smsParam, country, mobile);
				}*/
				
				// 发送短信验证码
				SmsSendParam smsSendParam = new SmsSendParam();
				smsSendParam.setHryCountry(country);
				smsSendParam.setHrySmsLang(langCode);
				smsSendParam.setHrySmsPhoneNum(mobile);
				smsSendParam.setHrySmstype(MessageType.MESSAGE_VERIFICATION_CODE.getKey());
				String sendSmsCode = SmsSendUtils.sendSms(smsSendParam);
				
				registSmsCodeCount++;
				redisService.save("registSmsCodeCount:"+mobile, String.valueOf(registSmsCodeCount), 120);
				redisService.save("SMS:registSmsCode:" + mobile, sendSmsCode);
				/*
				request.getSession().removeAttribute("registCode");
				request.getSession().removeAttribute("telCodeError" + mobile);*/
				jsonResult.setSuccess(true);
				jsonResult.setMsg(SpringUtil.diff("sms_success"));
				return jsonResult;
			}
		}
	}

}

package hry.oauth.index.controller;

import com.alibaba.fastjson.JSONObject;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.exchange.service.ExOrderInfoService;
import hry.admin.web.service.AppConfigService;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.NoLogin;
import hry.core.annotation.base.MethodName;
import hry.core.exception.IncorrectCaptchaException;
import hry.oauth.authorization.service.AuthorizationService;
import hry.oauth.user.model.AppUser;
import hry.oauth.user.service.AppRoleMenuService;
import hry.oauth.user.service.AppUserService;
import hry.redis.common.utils.RedisService;
import hry.reward.model.Timer;
import hry.util.DrawPictureUtil;
import hry.util.GoogleAuthenticatorUtil;
import hry.util.QueryFilter;
import hry.util.file.FileUtil;
import hry.util.md5.EncryptUtils;
import hry.util.properties.PropertiesUtils;
import hry.util.rsa.RSAUtils;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class IndexController {

	private static Logger logger = Logger.getLogger(IndexController.class);

	@Resource
	private AppConfigService appConfigService;

	@Resource(name = "appDicService")
	private AppDicService appDicService;
	/**
	 * 注册类型属性编辑器
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {

		// 系统注入的只能是基本类型，如int，char，String

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
	private AuthorizationService authorizationService;

	@Resource
	private AppUserService appUserService;
	@Resource
	private RedisService redisService;
	@Resource
	private AppRoleMenuService appRoleMenuService;
	@Resource
    private AppCustomerService appCustomerService;
	@Resource
    private ExOrderInfoService exOrderInfoService;
	@Resource
	private ExDmTransactionService exDmTransactionService;
	/**
	 * 后台主页
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("index");
		// 默认生成html静态页，配置false不生成
		mav.addObject("CREATE_HTML", false);
        mav.addObject("managerName",appConfigService.getBykeyfromRedis("managerName"));
        mav.addObject("siteCopyright",appConfigService.getBykeyfromRedis("siteCopyright"));
        mav.addObject("siteIcon",appConfigService.getBykeyfromRedis("siteIcon"));
		return mav;
	}

	/**
	 * 个人桌面
	 * 
	 * @return
	 */
	@RequestMapping("center")
	public ModelAndView center() {
		ModelAndView mav = new ModelAndView("base/center");
		// 昨日成交交易量
		String yesTrades = redisService.get("HomePage:yesterday_trade");
		if (!StringUtils.isEmpty(yesTrades)) {
			// 对数据进行千分位处理
			yesTrades = addZeroForNum(yesTrades, 12);
			String regex = "(.{3})";
			yesTrades = yesTrades.replaceAll(regex, "$1,");
			yesTrades = yesTrades.substring(0, yesTrades.length()-1);
			mav.addObject("yesterdayTrade", yesTrades);
		} else {
			mav.addObject("yesterdayTrade", "000,000,000,000");
		}

		// 昨日新增用户
        String customers = redisService.get("HomePage:new_customer");
        if (StringUtils.isEmpty(customers)) customers = "0";
        mav.addObject("new_customer", customers);

		// 昨日新增交易量
        String trades = redisService.get("HomePage:new_trade");
        if (StringUtils.isEmpty(trades)) trades = "0";
        mav.addObject("new_trade", trades);

		// 昨日充币数
        String post = redisService.get("HomePage:new_postCoin");
        if (StringUtils.isEmpty(post)) post = "0";
        mav.addObject("new_postCoin", post);

		// 昨日提币数
        String get = redisService.get("HomePage:new_getCoin");
        if (StringUtils.isEmpty(get)) get = "0";
        mav.addObject("new_getCoin", get);

        // 昨日挖矿数
        String yesterMiningNum = redisService.get("HomePage:new_mining");
        if (StringUtils.isEmpty(yesterMiningNum)) yesterMiningNum = "0";
        mav.addObject("new_mining", yesterMiningNum);

		// 昨日分红数
        String yesterdayNum = redisService.get("HomePage:new_dividend");
        if (StringUtils.isEmpty(yesterdayNum)) yesterdayNum = "0";
        mav.addObject("new_dividend", yesterdayNum);
		return mav;
	}

	/**
	 * 不足用0在左边补位
	 * @param str
	 * @param strLength
	 * @return
	 */
	private String addZeroForNum (String str, int strLength) {
		int strLen = str.length();
		StringBuffer sb = null;
		while (strLen < strLength) {
			sb = new StringBuffer();
			sb.append("0").append(str);// 左补0
			str = sb.toString();
			strLen = str.length();
		}
		return str;
	}

	@RequestMapping("getPieData")
	@ResponseBody
	public JsonResult getPieData() {
		Map<String, Object> map = new HashMap<>();
		JsonResult jsonResult = new JsonResult();
		try {
			// 行情
			Map<String, String> klineData = appConfigService.getQuotaData("configCache:klinedataconfig", "app.tradingmarket.quota.interface");
			map.put("klineData", klineData);
			// 实名认证
			Map<String, String> trData = appConfigService.getQuotaData("configCache:juheConfig", "app.truename.quota.interface");
			map.put("trData", trData);
			//短信
			Map<String, String> smsData = appConfigService.getQuotaData("configCache:smsConfig", "app.sms.quota.interface");
			map.put("smsData", smsData);

			jsonResult.setSuccess(true);
			jsonResult.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
		}
		return jsonResult;
	}

    /**
	 * 通用的返回静态资源方法,用户菜单点击时切换页面
	 * 
	 * @param u
	 * @return
	 */
	@RequestMapping("v")
	public ModelAndView v(String u) {
		ModelAndView mav = new ModelAndView();
		if (!StringUtils.isEmpty(u)) {
			mav.setViewName(u);
		} else {
			mav.setViewName("index");
		}
		// 默认生成html静态页，配置false不生成

		return mav;
	}
	/**
	 * 通用的返回静态资源方法,用户菜单点击时切换页面
	 *	查询了语言栏
	 * @param u
	 * @return
	 */
	@RequestMapping("language")
	public ModelAndView language(String u) {
		ModelAndView mav = new ModelAndView();
		if (!StringUtils.isEmpty(u)) {
			mav.setViewName(u);
			QueryFilter filter = new QueryFilter(AppDic.class);
			filter.addFilter("pkey=", "sysLanguage");
			List<AppDic> keyList = appDicService.find(filter);
			mav.addObject("keyList", keyList);
		} else {
			mav.setViewName("index");
		}
		// 默认生成html静态页，配置false不生成

		return mav;
	}
	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("googleLogin",PropertiesUtils.APP.getProperty("app.google.login"));

		try {
			Map<String, Object> keyMap = RSAUtils.genKeyPair();
			//私钥存入session7
			String privateKey = RSAUtils.getPrivateKey(keyMap);
			SecurityUtils.getSubject().getSession().setAttribute("RSA_privateKey_login",privateKey);
//			String uuids=UUID.randomUUID().toString();
//			redisService.save("Admin:RSA_privateKey_login_"+request.getSession().getId(),privateKey);
//			SecurityUtils.getSubject().getSession().setAttribute("RSA_uuids",uuids);
//			logger.error("进入页面时sessionId = " + request.getSession().getId() );
//			logger.error("进入页面时RSA_privateKey = " + SecurityUtils.getSubject().getSession().getAttribute("RSA_privateKey_login") );
//			logger.error("进入页面时RSA_uuids = " + request.getSession().getAttribute("RSA_uuids") );
			//公钥返回给业务
			String publicKey = RSAUtils.getPublicKey(keyMap);
			mav.addObject("RSA_publicKey",publicKey);
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return mav;
	}

	@RequestMapping(value = "/checklogin",method = RequestMethod.POST)
	@ResponseBody
	@NoLogin
	public JsonResult checklogin(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		String mobileImgCode = request.getParameter("mobileImgCode");
		String googleCode = request.getParameter("google_code");
//		logger.error("进入页面时RSA_uuids= " + request.getSession().getAttribute("RSA_uuids") );
//		logger.error("登录时的sessionId = " + request.getSession().getId() );
//		logger.error("登录时RSA_privateKey = " +SecurityUtils.getSubject().getSession().getAttribute("RSA_privateKey_login") );
		// session图形验证码
		String session_registcode = (String) request.getSession().getAttribute("registCode");
		if (!mobileImgCode.equalsIgnoreCase(session_registcode)) {
			return new JsonResult().setObj(username).setMsg("图形验证码错误");
		}


		try {

			if("true".equals(PropertiesUtils.APP.getProperty("app.google.login"))) {
				if (StringUtils.isEmpty(googleCode)) {
					return new JsonResult().setObj(username).setMsg("谷歌验证码错误");
				}
				long t = System.currentTimeMillis();
				GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
				long code = Long.parseLong(googleCode);
				String googleKey = FileUtil.getGoogleKey();
				boolean r = ga.check_code(googleKey, code, t);
				JsonResult jsonResult = new JsonResult();
				if (!r) {
					jsonResult.setSuccess(false);
					jsonResult.setMsg("谷歌验证码错误");
					jsonResult.setObj(username);
					return jsonResult;
				}
			}

			//获得rsa私钥
			String RSA_privateKey = (String) SecurityUtils.getSubject().getSession().getAttribute("RSA_privateKey_login");
//			String RSA_privateKey = redisService.get("Admin:RSA_privateKey_login_"+request.getSession().getId());
			if(StringUtils.isEmpty(RSA_privateKey)){
				return new JsonResult().setMsg("登录超时，刷新页面!").setCode("1000").setObj(username);
			}

			//解密
			//password = RSAUtils.decryptDataOnJava(password,RSA_privateKey);



			BaseManageUser _user = appUserService.checkUsername(username);
			if (_user != null) {
				UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				Subject currentUser = SecurityUtils.getSubject();
				if (!currentUser.isAuthenticated()) {
					// 使用shiro来验证
					//token.setRememberMe(true);
					currentUser.login(token);// 验证角色和权限
					logger.info("登录成功.....");

					//登录成功后删除私钥
//					request.getSession().removeAttribute("RSA_privateKey");
					redisService.delete("Admin:RSA_privateKey_login");
					SecurityUtils.getSubject().getSession().setAttribute("user", _user);
					SecurityUtils.getSubject().getSession().setAttribute("userNameForLog", _user.getUsername());

					// 查询账户权限
					Set<String> permissions = authorizationService.findPermissions(_user.getUsername());
					StringBuffer sb = new StringBuffer();
					if (permissions != null && permissions.size() > 0) {
						Iterator<String> it = permissions.iterator();
						while (it.hasNext()) {
							sb.append(it.next());
							sb.append(",");
						}
                        String HRY_permissions = sb.toString().substring(0, sb.toString().length() - 1);
                        logger.error(HRY_permissions);
						SecurityUtils.getSubject().getSession().setAttribute("HRY_permissions", EncryptUtils.aesEncrypt(HRY_permissions,EncryptUtils.KEY).replaceAll("\n",""));
						logger.error(SecurityUtils.getSubject().getSession().getAttribute("HRY_permissions"));
					}

					// 查询用户菜单角色集合
					if (!"1".equals(_user.getId().toString())) {
						List<String> roleList = appRoleMenuService.findUserMenuRoleByuserId(_user.getId().toString());
						if (roleList != null && roleList.size() > 0) {
							if (roleList.contains("admin")) {
								SecurityUtils.getSubject().getSession().setAttribute("menukey", "admin");
							} else {
								SecurityUtils.getSubject().getSession().setAttribute("menukey", roleList.get(0));
							}
						}
					} else {
						SecurityUtils.getSubject().getSession().setAttribute("menukey", "admin");
					}
					return new JsonResult().setSuccess(true);
				} else {
					return new JsonResult().setSuccess(true);
				}
			}
		} catch (Exception ex) {
			logger.error("登录失败.....");
 			logger.error(ex);
		}

		return new JsonResult().setObj(username).setMsg("用户名/密码错误!");
	}

	@MethodName(name = "用户登录操作")
	@RequestMapping(value="/loginService",method = RequestMethod.POST)
	public String loginService(AppUser appUser, HttpServletRequest request, HttpServletResponse response) {

		JsonResult jsonResult = new JsonResult();
		Subject subject = SecurityUtils.getSubject();
		String backUrl = request.getContextPath();

		String loginHtml = backUrl + "/login";
		String indexHtml = backUrl + "/#/index";
		String url = "";

		// 如果认证信息存在，则直接返回主页，防止认证后再次返回登录页登录不成功的问题
		if (subject.isAuthenticated()) {
			jsonResult.setSuccess(true);
			jsonResult.setMsg("已经登录了");
			url = indexHtml;
		}
		String exceptionClassName = null;
		exceptionClassName = (String) request.getAttribute("shiroLoginFailure");

		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("用户名/密码错误");
			url = loginHtml + "&error=1";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("用户名/密码错误");
			url = loginHtml + "&error=1";
		} else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("登录次数过多,请1分钟后再试");
			url = loginHtml + "&error=3";
		} else if (IncorrectCaptchaException.class.getName().equals(exceptionClassName)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("验证码错误");
			url = loginHtml + "&error=2";
		} else if (exceptionClassName != null) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("用户名/密码错误");
			url = loginHtml + "&error=1";
		}
		request.setAttribute("message", error);
		if (request.getParameter("forceLogout") != null) {
			request.setAttribute("message", "您已经被管理员强制退出，请重新登录");
			url = loginHtml;
		}

		if (StringUtils.isEmpty(url)) {
			url = loginHtml;
		}

		if (!jsonResult.getSuccess()) {// 登录失败时，增加失败日志
		}

		return "redirect:" + url;
	}

	/**
	 * 注销方法
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		return;
	}

	/**
	 * 注册验证码
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("registcode")
	public void registcode(HttpServletResponse response, HttpServletRequest request) {
		DrawPictureUtil drawPictureUtil = new DrawPictureUtil("registCode", 100, 30);
		drawPictureUtil.darw(request, response);
	}

	/**
	 * 查询定时任务监控列表
	 * @param request
	 * @return
	 */
	@RequestMapping("findTimerTask")
	@ResponseBody
	public PageResult findTimerTask (HttpServletRequest request) {
		List<Map<String, Object>> timerList = new ArrayList<>();
		// 查询挖矿分红的定时任务
		String holdBonusStr = redisService.getMap("Mining:Exception","holdBonus");//持币定时器状态
		mapHandler(timerList, holdBonusStr, "holdBonus");

		String grantBonusStr = redisService.getMap("Mining:Exception","grantBonus");//分红发放定时器状态
		mapHandler(timerList, grantBonusStr, "grantBonus");

		String returnMiningStr = redisService.getMap("Mining:Exception","returnMining");//挖矿返还定时器状态
		mapHandler(timerList, returnMiningStr, "returnMining");

		String avgPriceMiningStr = redisService.getMap("Mining:Exception","avgPriceMining");//计算均价定时器状态
		mapHandler(timerList, avgPriceMiningStr, "avgPriceMining");

		String coinUnlock = redisService.getMap("Unlock:Exception","coinUnlock"); // 解锁仓定时器状态
		mapHandler(timerList, coinUnlock, "coinUnlock");

		PageResult pageResult = new PageResult();
		pageResult.setRows(timerList);
		pageResult.setTotal(new Long(timerList.size()));
		return pageResult;
	}

	/**
	 * 封装定时任务信息
	 * @param timerList
	 * @param content
	 * @param type
	 */
	private void mapHandler(List<Map<String, Object>> timerList, String content, String type){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> timerMap = new HashMap<>();
		timerMap.put("id", type);
		timerMap.put("isOpen", "0");
		String miningTimer = "";
		Timer timer = null;
		// 初始定时监控内容
		switch (type) {
			case "holdBonus":
				timerMap.put("timerType", "交易挖矿");
				timerMap.put("timerName", "持币记录");
				miningTimer = redisService.get("Mining:Timer1");
				if (!StringUtils.isEmpty(miningTimer)) {
					timer = JSONObject.parseObject(miningTimer, Timer.class);
					if("0".equals(timer.getBonusIfStart())){
						timerMap.put("isOpen",1 );
					}else{
						timerMap.put("isOpen",0 );
					}
				}
				break;
			case "grantBonus":
				timerMap.put("timerType", "交易挖矿");
				timerMap.put("timerName", "分红奖励");
				miningTimer = redisService.get("Mining:Timer1");
				if (!StringUtils.isEmpty(miningTimer)) {
					timer = JSONObject.parseObject(miningTimer, Timer.class);
					if("0".equals(timer.getBonusReturnType())){
						timerMap.put("isOpen",1 );
					}else{
						timerMap.put("isOpen",0 );
					}
				}
				break;
			case "returnMining":
				timerMap.put("timerType", "交易挖矿");
				timerMap.put("timerName", "挖矿返还");
				miningTimer = redisService.get("Mining:Timer0");
				if (!StringUtils.isEmpty(miningTimer)) {
					timer = JSONObject.parseObject(miningTimer, Timer.class);
					if("0".equals(timer.getPlatformType())){
						timerMap.put("isOpen",1 );
					}else{
						timerMap.put("isOpen",0 );
					}
				}
				break;
			case "avgPriceMining":
				timerMap.put("timerType", "交易挖矿");
				timerMap.put("timerName", "计算均价");
				miningTimer = redisService.get("Mining:Timer0");
				if (!StringUtils.isEmpty(miningTimer)) {
					timer = JSONObject.parseObject(miningTimer, Timer.class);
					if("0".equals(timer.getMiningIfStart())){
						timerMap.put("isOpen",1 );
					}else{
						timerMap.put("isOpen",0 );
					}
				}
				break;
			case "coinUnlock":
				timerMap.put("timerType", "解仓");
				timerMap.put("timerName", "解仓监控");
				miningTimer = redisService.get("Mining:Timer2");
				if (!StringUtils.isEmpty(miningTimer)) {
					timer = JSONObject.parseObject(miningTimer, Timer.class);
					timerMap.put("isOpen", timer.getUnlockIfStart());
				}
				break;
		}
		if (!StringUtils.isEmpty(content)) {
			timerMap.put("created", content.split("#")[2]);
			String ifSuccess = content.split("#")[1];
			if("success".equals(ifSuccess)){//判断上次运行是否成功，页面现在未使用
				timerMap.put("isSuccess", 1);
			} else {
				timerMap.put("isSuccess", 0);
			}

		}
		timerList.add(timerMap);
	}

	@RequestMapping("timerOpenOrClose")
	@ResponseBody
	public JsonResult timerOpenOrClose(HttpServletRequest request){
		String timerType = request.getParameter("id");
		String optType = request.getParameter("optType");
		JsonResult jsonResult = new JsonResult();
		try {
			String miningTimer = "";
			String json = "";
			Timer timer = null;
			switch (timerType) {
                case "holdBonus":
					miningTimer = redisService.get("Mining:Timer1");
					if (!StringUtils.isEmpty(miningTimer)) {
						timer = JSONObject.parseObject(miningTimer, Timer.class);
						timer.setBonusIfStart(optType);
						json = JSONObject.toJSONString(timer);
						redisService.delete("Mining:Timer1");
						redisService.save("Mining:Timer1", json);
					}
                    break;
                case "grantBonus":
					miningTimer = redisService.get("Mining:Timer1");
					if (!StringUtils.isEmpty(miningTimer)) {
						timer = JSONObject.parseObject(miningTimer, Timer.class);
						timer.setBonusReturnType(optType);
						json = JSONObject.toJSONString(timer);
						redisService.delete("Mining:Timer1");
						redisService.save("Mining:Timer1", json);
					}
                    break;
                case "returnMining":
					miningTimer = redisService.get("Mining:Timer0");
					if (!StringUtils.isEmpty(miningTimer)) {
						timer = JSONObject.parseObject(miningTimer, Timer.class);
						timer.setPlatformType(optType);
						json = JSONObject.toJSONString(timer);
						redisService.delete("Mining:Timer0");
						redisService.save("Mining:Timer0", json);
					}
                    break;
                case "avgPriceMining":
					miningTimer = redisService.get("Mining:Timer0");
					if (!StringUtils.isEmpty(miningTimer)) {
						timer = JSONObject.parseObject(miningTimer, Timer.class);
						timer.setMiningIfStart(optType);
						json = JSONObject.toJSONString(timer);
						redisService.delete("Mining:Timer0");
						redisService.save("Mining:Timer0", json);
					}
                    break;
                case "coinUnlock":
                    redisService.delete("Unlock:isOpen");
                    redisService.save("Unlock:isOpen", optType);
                    break;
            }
			jsonResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
		}
		return jsonResult;
	}
}

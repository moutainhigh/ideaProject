/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 12:06:01 
 */
package hry.admin.c2c.controller;


import com.alibaba.fastjson.JSONObject;
import hry.admin.web.service.AppConfigService;
import hry.bean.JsonResult;
import hry.core.annotation.CommonLog;
import hry.oauth.index.controller.IndexController;
import hry.redis.common.utils.RedisService;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 12:06:01 
 */
@Controller
@RequestMapping("/c2c/c2cconfig")
public class C2cConfigController  {

	private final static Logger log = Logger.getLogger(IndexController.class);

	@Resource
	private AppConfigService appConfigService;

	@Resource
	private RedisService redisService;
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


	/**
	 * 交易规则配置页面
	 * @return
	 */
	@RequestMapping(value="/rulepage")
	public ModelAndView rulepage(){
		ModelAndView mav = new ModelAndView("/admin/c2c/c2cconfigrulepage");
		String str = redisService.get("cn:c2crule");
		if(!StringUtils.isEmpty(str)){
			mav.addObject("c2crule",JSONObject.parseObject(str));
		}
		return mav;
	}

	/**
	 * 交易超时配置页面
	 * @return
	 */
	@RequestMapping(value="/timeoutpage")
	public ModelAndView timeoutpage(){
		ModelAndView mav = new ModelAndView("/admin/c2c/c2cconfigtimeoutpage");
		String c2cTimeOut = redisService.get("cn:c2cTimeOut");
		mav.addObject("c2cTimeOut",c2cTimeOut);
		return mav;
	}

	/**
	 * 设置超时时间
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/timeoutsubmit")
	@ResponseBody
	@CommonLog(name = "C2C-交易超时设置")
	public JsonResult timeoutsubmit(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		String c2cTimeOut = request.getParameter("c2cTimeOut");
		redisService.save("cn:c2cTimeOut", c2cTimeOut);
		jsonResult.setSuccess(true);
		return jsonResult;
	}
	/**
	 * 设置规则
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/rulesubmit")
	@ResponseBody
	@CommonLog(name = "C2C-交易匹配规则")
	public JsonResult rulesubmit(HttpServletRequest request){

		/**
		 * 0指定匹配
		 * 1随机匹配
		 * 2价格匹配
		 */
		String c2crule = request.getParameter("c2crule");
		/**
		 * 指定匹配中的A类,B类商户
		 */
		String businessmanType = request.getParameter("businessmanType");
		/**
		 * 价格匹配中的A类，B类商户
		 */
		String businessmanType2 = request.getParameter("businessmanType2");//价格匹配商户类型
		JSONObject obj = new JSONObject();
		obj.put("c2crule", c2crule);
		if(!StringUtils.isEmpty(businessmanType)){
			obj.put("businessmanType", businessmanType);
		}
		if(!StringUtils.isEmpty(businessmanType2)){
			obj.put("businessmanType2", businessmanType2);
		}
		redisService.save("cn:c2crule", obj.toJSONString());

		JsonResult jsonResult = new JsonResult();
		jsonResult.setSuccess(true);

		return jsonResult;
	}



}

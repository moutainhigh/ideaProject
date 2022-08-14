/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 12:06:01 
 */
package hry.oauth.index.controller;


import hry.admin.web.service.AppConfigService;
import hry.bean.JsonResult;
import hry.core.shiro.PasswordHelper;
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
@RequestMapping("/sys/config")
public class ConfigController {

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
     * 审核密码设置页面
	 * @return
     */
	//@RequestMapping(value="/auditpasswordpage")
	public ModelAndView auditpasswordpage(){
		ModelAndView mav = new ModelAndView("/oauth/sys/auditpasswordpage");
		String auditpassword = redisService.get("auditpassword");
		if(!StringUtils.isEmpty(auditpassword)){
			mav.addObject("auditpassword",auditpassword);
		}
		return mav;
	}

	/**
	 * 设置审核密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/auditpasswordsubmit")
	@ResponseBody
	public JsonResult auditpasswordsubmit(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		//审核密码
		String setAuditpassword = request.getParameter("auditpassword");
		if(StringUtils.isEmpty(setAuditpassword)) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("密码不能为空");
			return jsonResult;
		}

		PasswordHelper passwordHelper = new PasswordHelper();
		String encryString = passwordHelper.encryString(setAuditpassword, "setAuditpassword");
		redisService.save("auditpassword", encryString);

		jsonResult.setSuccess(true);
		jsonResult.setMsg("设置成功");
		return jsonResult;
	}




}

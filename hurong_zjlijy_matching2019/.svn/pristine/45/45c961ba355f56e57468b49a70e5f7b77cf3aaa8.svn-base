package hry.sys.serverManagement.controller;

import hry.core.mvc.model.page.JsonResult;
import hry.redis.common.utils.RedisService;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ServerManagement")
public class ServerManagementController {
	private final static Logger log = Logger.getLogger(ServerManagementController.class);
	@Resource
	public RedisService redisService;
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
	 * 心跳机制
	 *
	 */
	@RequestMapping(value = "/heartbeatConnection", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public JsonResult heartbeatConnection(HttpServletRequest request) {
			Map<String,Boolean> map=new HashMap<>();
			String config = redisService.get("configCache:all");
			if(config !=null){
				map.put("redis",true);
			}else{
				map.put("redis",false);
			}
			JsonResult jsonResult=new JsonResult();
			jsonResult.setSuccess(true);
			jsonResult.setObj(map);
			return jsonResult;

	}

}

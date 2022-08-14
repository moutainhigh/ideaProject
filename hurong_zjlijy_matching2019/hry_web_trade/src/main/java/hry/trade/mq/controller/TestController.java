package hry.trade.mq.controller;

import hry.core.mvc.model.log.AppException;
import hry.core.mvc.model.page.JsonResult;
import hry.trade.mq.service.AppExceptionService;
import hry.trade.mq.service.MessageProducer;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/test")
public class TestController {

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
	private MessageProducer messageProducer;
	
	
	
	@RequestMapping("sell")
	@ResponseBody
	public JsonResult sell(HttpServletRequest request, HttpServletResponse response) {

		AppException exceptionLog = new AppException();
		exceptionLog.setName("mq==消息报错体==");
		AppExceptionService appExceptionService=(AppExceptionService) ContextUtil.getBean("appExceptionService");
		appExceptionService.save(exceptionLog);
		return new JsonResult();
		
	}

	

}

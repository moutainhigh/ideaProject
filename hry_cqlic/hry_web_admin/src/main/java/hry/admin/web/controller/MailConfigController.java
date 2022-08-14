/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 15:39:55 
 */
package hry.admin.web.controller;


import hry.admin.web.model.MailConfig;
import hry.admin.web.model.MailTemplate;
import hry.admin.web.service.MailTemplateService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 15:39:55 
 */
@Controller
@RequestMapping("/web/mailconfig")
public class MailConfigController extends BaseController<MailConfig, Long> {

	@Resource(name = "mailTemplateService")
	private MailTemplateService mailTemplateService;
	@Resource(name = "mailConfigService")
	@Override
	public void setService(BaseService<MailConfig, Long> service) {
		super.service = service;
	}

	@MethodName(name = "查看MailConfig")
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		MailConfig mailConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/mailconfigsee");
		mav.addObject("model", mailConfig);
		return mav;
	}

	@MethodName(name="增加MailConfig")
	@RequestMapping(value="/add")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult add(HttpServletRequest request,MailConfig mailConfig){
		return super.save(mailConfig);
	}
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		MailConfig mailConfig = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/mailconfigmodify");
		mav.addObject("model", mailConfig);
		return mav;
	}
	@MethodName(name="修改MailConfig")
	@RequestMapping(value="/modify")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,MailConfig mailConfig){
		return super.update(mailConfig);
	}

	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove( String ids){
		JsonResult jsonResult = new JsonResult();
		Boolean flag=true;
		MailTemplate mailTemplate=new MailTemplate();
		String[] split = ids.split(",");
		for (String string : split) {
			mailTemplate.setMailConfigId(Long.valueOf(string));
			Long count = mailTemplateService.count(mailTemplate);
			if(count>0) {
				flag=false;
				break;
			}
		}
		if(flag) {
			jsonResult=super.deleteBatch(ids);
		}
		else {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("该类型被使用，无法删除");
		}

		return jsonResult;
	}

	@MethodName(name = "列表MailConfig")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(MailConfig.class,request);
		filter.setOrderby("sort  asc");
		return super.findPage(filter);
	}
	@MethodName(name = "正在使用的邮箱查询")
	@RequestMapping("/mailConfigList")
	@ResponseBody
	public List<MailConfig> mailConfigList(HttpServletRequest request) {
		//不要加request，否则前台使用Hrysselect标签的时候，filter中会传过来一个过滤条件导致sql报错
		QueryFilter filter = new QueryFilter(MailConfig.class);
//		filter.addFilter("state!=", 0);
		filter.addFilter("status=", 1);
		List<MailConfig> list = super.find(filter);
		return list;
	}
	
	
}

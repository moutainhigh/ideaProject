/**
 * Copyright:
 * @author:      zhouming
 * @version:     V1.0
 * @Date:        2018-10-25 17:56:24
 */
package hry.admin.web.controller;


import hry.admin.web.model.AppSmsTemplate;
import hry.admin.web.service.AppSmsTemplateService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
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
import java.util.Date;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      zhouming
 * @version:     V1.0
 * @Date:        2018-10-25 17:56:24
 */
@Controller
@RequestMapping("/web/appsmstemplate")
public class AppSmsTemplateController extends BaseController<AppSmsTemplate, Long> {

	@Resource(name = "appSmsTemplateService")
	@Override
	public void setService(BaseService<AppSmsTemplate, Long> service) {
		super.service = service;
	}


	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppSmsTemplate appSmsTemplate = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appsmstemplatesee");
		mav.addObject("model", appSmsTemplate);
		return mav;
	}


	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppSmsTemplate appSmsTemplate){
		QueryFilter filter = new QueryFilter(AppSmsTemplate.class,request);
		filter.addFilter("mkey=",appSmsTemplate.getMkey().trim());
		filter.addFilter("messageCategory=",appSmsTemplate.getMessageCategory());
		List<AppSmsTemplate> appSmsTemplateList =super.find(filter);
		if (appSmsTemplateList != null && appSmsTemplateList.size() > 0){
			return new JsonResult().setSuccess(false).setMsg("同语种模板key已存在");
		}
		return super.save(appSmsTemplate);
	}

	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppSmsTemplate appSmsTemplate = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appsmstemplatemodify");
		mav.addObject("model", appSmsTemplate);
		return mav;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppSmsTemplate appSmsTemplate){
		QueryFilter filter = new QueryFilter(AppSmsTemplate.class,request);
		filter.addFilter("id=",appSmsTemplate.getId());
		List<AppSmsTemplate> list = super.find(filter);
		AppSmsTemplate template = list.get(0);
		//编辑判断模板key是否相同
		if (!template.getMkey().equals(appSmsTemplate.getMkey())) {
			filter = new QueryFilter(AppSmsTemplate.class,request);
			filter.addFilter("mkey=",appSmsTemplate.getMkey().trim());
			filter.addFilter("messageCategory=",appSmsTemplate.getMessageCategory());
			List<AppSmsTemplate> appSmsTemplateList =super.find(filter);
			if (appSmsTemplateList != null && appSmsTemplateList.size() > 0){
				return new JsonResult().setSuccess(false).setMsg("同语种模板key已存在");
			}
		}
		return super.update(appSmsTemplate);
	}


	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppSmsTemplate.class,request);
		String messageCategory = request.getParameter("messageCategory");
		if(null != messageCategory && !"".equals(messageCategory)){
			filter.addFilter("messageCategory=",messageCategory);
		}
		filter.setOrderby("created desc");

		return super.findPage(filter);
	}

	@MethodName(name = "开启/关闭模板")
	@RequestMapping("/switchOpen/{ids}")
	@ResponseBody
	public JsonResult switchOpen(@PathVariable Long[] ids, HttpServletRequest request) {
		String type = request.getParameter("type");
		AppSmsTemplateService appSmsTemplateService = (AppSmsTemplateService)service;
		for (int i = 0; i < ids.length; i++) {
			AppSmsTemplate appSmsTemplate = new AppSmsTemplate();
			appSmsTemplate.setId(ids[i]);
			if ("open".equals(type))
				appSmsTemplate.setIsOpen(1);
			else
				appSmsTemplate.setIsOpen(0);
			appSmsTemplate.setModified(new Date());
			appSmsTemplateService.update(appSmsTemplate);
		}
		return new JsonResult().setSuccess(true).setMsg("操作成功");
	}



}

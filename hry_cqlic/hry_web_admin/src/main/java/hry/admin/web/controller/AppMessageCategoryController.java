/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:20:33 
 */
package hry.admin.web.controller;


import hry.admin.dic.model.AppDic;
import hry.admin.dic.service.AppDicService;
import hry.admin.web.model.AppMessageCategory;
import hry.admin.web.service.AppMessageCategoryService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.util.StringUtil;
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
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:20:33 
 */
@Controller
@RequestMapping("/web/appmessagecategory")
public class AppMessageCategoryController extends BaseController<AppMessageCategory, Long> {
	
	@Resource(name = "appMessageCategoryService")
	@Override
	public void setService(BaseService<AppMessageCategory, Long> service) {
		super.service = service;
	}

	@Resource(name = "appDicService")
	private AppDicService appDicService;

	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppMessageCategory appMessageCategory = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appmessagecategorysee");
		mav.addObject("model", appMessageCategory);
		return mav;
	}


	@RequestMapping(value="/gotoCategory")
	public ModelAndView gotoCategory(HttpServletRequest request){
		String messageCategory = request.getParameter("messageCategory");
		ModelAndView mav = new ModelAndView("/admin/web/appmessagecategorylist");
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "sysLanguage");
		List<AppDic> keyList = appDicService.find(filter);
		if (!StringUtil.isNull(messageCategory)) {
			messageCategory = keyList.get(0).getValue();
		}
		mav.addObject("keyList", keyList);
		mav.addObject("messageCategory", messageCategory);
		return mav;
	}

	@RequestMapping(value="/gotoAdd")
	public ModelAndView gotoAdd(HttpServletRequest request){
		String messageCategory = request.getParameter("messageCategory");
		ModelAndView mav = new ModelAndView("/admin/web/appmessagecategoryadd");
		mav.addObject("messageCategory", messageCategory);
		return mav;
	}


	@RequestMapping(value="/add")
	@ResponseBody
	@CommonLog(name = "系统信件模板-添加")
	public JsonResult add(HttpServletRequest request,AppMessageCategory appMessageCategory){
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "trigger");
		List<AppDic> keyList = appDicService.find(filter);
		for(AppDic appDic : keyList){
			if(appDic.getValue().equals(appMessageCategory.getTrigger())){
				appMessageCategory.setTriggerPointLan(appDic.getName());
			}
		}

		appMessageCategory.setIsOpen(0);//默认关闭
		appMessageCategory.setState(1);//添加的消息类别是可编辑的

		//appMessageCategory.setTriggerPointLan();
		return super.save(appMessageCategory);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id, HttpServletRequest request){
		AppMessageCategory appMessageCategory = service.get(id);
		String messageCategory = request.getParameter("messageCategory");
		ModelAndView mav = new ModelAndView("/admin/web/appmessagecategorymodify");
		mav.addObject("model", appMessageCategory);
		mav.addObject("messageCategory", messageCategory);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	@CommonLog(name = "系统信件模板-修改")
	public JsonResult modify(HttpServletRequest request,AppMessageCategory appMessageCategory){
		QueryFilter filter = new QueryFilter(AppDic.class);
		filter.addFilter("pkey=", "trigger");
		List<AppDic> keyList = appDicService.find(filter);
		for(AppDic appDic : keyList){
			if(appDic.getValue().equals(appMessageCategory.getTrigger())){
				appMessageCategory.setTriggerPointLan(appDic.getName());
			}
		}
		return super.update(appMessageCategory);
	}
	
	@RequestMapping(value="/remove")
	@ResponseBody
	@CommonLog(name = "系统信件模板-删除")
	public JsonResult remove(String ids){
		AppMessageCategoryService appMessageCategoryService = (AppMessageCategoryService)service;
		JsonResult result = appMessageCategoryService.removeCategory(ids);
		return result;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppMessageCategory.class, request);
		String messageCategory = request.getParameter("messageCategory");
		if(null != messageCategory && !"".equals(messageCategory)){
			filter.addFilter("messageCategory=",messageCategory);
		}
		filter.setOrderby("sort asc");
		filter.addFilter("state!=", 0);

		return super.findPage(filter);
	}

	@MethodName(name = "开启/关系消息类型")
	@RequestMapping("/switchOpen/{ids}")
	@ResponseBody
	public JsonResult switchOpen(@PathVariable Long[] ids, HttpServletRequest request) {
		String type = request.getParameter("type");
		AppMessageCategoryService appMessageCategoryService = (AppMessageCategoryService)service;
		JsonResult result = appMessageCategoryService.switchOpen(ids, type);
		return result;
	}

	@MethodName(name = "消息类别查询")
	@RequestMapping("/messageTypeList")
	@ResponseBody
	public List<AppMessageCategory> messageTypeList(HttpServletRequest request) {
		QueryFilter filter = new QueryFilter(AppMessageCategory.class);
		filter.addFilter("state_NEQ", 0);
		filter.addFilter("isOpen=", 1);
		return service.find(filter);
	}
	
}

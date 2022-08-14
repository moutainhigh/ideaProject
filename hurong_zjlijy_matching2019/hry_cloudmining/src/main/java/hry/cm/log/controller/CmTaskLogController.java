/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-31 19:33:49 
 */
package hry.cm.log.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.cm.log.model.CmTaskLog;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-31 19:33:49 
 */
@Controller
@RequestMapping("/log/cmtasklog")
public class CmTaskLogController extends BaseController<CmTaskLog, Long> {
	
	@Resource(name = "cmTaskLogService")
	@Override
	public void setService(BaseService<CmTaskLog, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		CmTaskLog cmTaskLog = service.get(id);
		ModelAndView mav = new ModelAndView("/cm/log/cmtasklogsee");
		mav.addObject("model", cmTaskLog);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,CmTaskLog cmTaskLog){
		return super.save(cmTaskLog);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		CmTaskLog cmTaskLog = service.get(id);
		ModelAndView mav = new ModelAndView("/cm/log/cmtasklogmodify");
		mav.addObject("model", cmTaskLog);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,CmTaskLog cmTaskLog){
		return super.update(cmTaskLog);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(CmTaskLog.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

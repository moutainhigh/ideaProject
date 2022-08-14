/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-06 15:31:10 
 */
package hry.admin.klg.log.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.log.model.KlgExceptionLog;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-06 15:31:10 
 */
@Controller
@RequestMapping("/klg/log/klgexceptionlog")
public class KlgExceptionLogController extends BaseController<KlgExceptionLog, Long> {
	
	@Resource(name = "klgExceptionLogService")
	@Override
	public void setService(BaseService<KlgExceptionLog, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgExceptionLog klgExceptionLog = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/log/klgexceptionlogsee");
		mav.addObject("model", klgExceptionLog);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgExceptionLog klgExceptionLog){
		return super.save(klgExceptionLog);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgExceptionLog klgExceptionLog = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/log/klgexceptionlogmodify");
		mav.addObject("model", klgExceptionLog);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgExceptionLog klgExceptionLog){
		return super.update(klgExceptionLog);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgExceptionLog.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

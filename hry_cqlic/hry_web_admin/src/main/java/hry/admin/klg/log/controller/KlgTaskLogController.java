/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-06 15:30:25 
 */
package hry.admin.klg.log.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.log.model.KlgTaskLog;

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
 * @Date:        2019-05-06 15:30:25 
 */
@Controller
@RequestMapping("/klg/log/klgtasklog")
public class KlgTaskLogController extends BaseController<KlgTaskLog, Long> {
	
	@Resource(name = "klgTaskLogService")
	@Override
	public void setService(BaseService<KlgTaskLog, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgTaskLog klgTaskLog = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/log/klgtasklogsee");
		mav.addObject("model", klgTaskLog);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgTaskLog klgTaskLog){
		return super.save(klgTaskLog);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgTaskLog klgTaskLog = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/log/klgtasklogmodify");
		mav.addObject("model", klgTaskLog);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgTaskLog klgTaskLog){
		return super.update(klgTaskLog);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgTaskLog.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

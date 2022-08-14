/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-13 11:25:18 
 */
package hry.admin.exchange.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.ExRobotLog;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-13 11:25:18 
 */
@Controller
@RequestMapping("/exchange/exrobotlog")
public class ExRobotLogController extends BaseController<ExRobotLog, Long> {
	
	@Resource(name = "exRobotLogService")
	@Override
	public void setService(BaseService<ExRobotLog, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExRobotLog exRobotLog = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exrobotlogsee");
		mav.addObject("model", exRobotLog);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExRobotLog exRobotLog){
		return super.save(exRobotLog);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExRobotLog exRobotLog = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exrobotlogmodify");
		mav.addObject("model", exRobotLog);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExRobotLog exRobotLog){
		return super.update(exRobotLog);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExRobotLog.class,request);
		filter.setOrderby("openTime desc");
		return super.findPage(filter);
	}
	
	
	
	
}

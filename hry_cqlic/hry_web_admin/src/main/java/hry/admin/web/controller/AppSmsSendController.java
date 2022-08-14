/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:42:04 
 */
package hry.admin.web.controller;


import hry.admin.web.model.AppSmsSend;
import hry.bean.JsonResult;
import hry.bean.PageResult;
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

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:42:04 
 */
@Controller
@RequestMapping("/web/appsmssend")
public class AppSmsSendController extends BaseController<AppSmsSend, Long> {
	
	@Resource(name = "appSmsSendService")
	@Override
	public void setService(BaseService<AppSmsSend, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppSmsSend appSmsSend = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appsmssendsee");
		mav.addObject("model", appSmsSend);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppSmsSend appSmsSend){
		return super.save(appSmsSend);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppSmsSend appSmsSend = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appsmssendmodify");
		mav.addObject("model", appSmsSend);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppSmsSend appSmsSend){
		return super.update(appSmsSend);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppSmsSend.class,request);
		filter.setOrderby("created desc");
		return super.findPage(filter);
	}
	
	
	
	
}

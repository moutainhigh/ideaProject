/**
 * Copyright:    
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 14:48:34 
 */
package hry.admin.lend.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.lend.model.ExLendEntrust;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 14:48:34 
 */
@Controller
@RequestMapping("/lend/exlendentrust")
public class ExLendEntrustController extends BaseController<ExLendEntrust, Long> {
	
	@Resource(name = "exLendEntrustService")
	@Override
	public void setService(BaseService<ExLendEntrust, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExLendEntrust exLendEntrust = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/lend/exlendentrustsee");
		mav.addObject("model", exLendEntrust);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExLendEntrust exLendEntrust){
		return super.save(exLendEntrust);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExLendEntrust exLendEntrust = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/lend/exlendentrustmodify");
		mav.addObject("model", exLendEntrust);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExLendEntrust exLendEntrust){
		return super.update(exLendEntrust);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/currentList")
	@ResponseBody
	public PageResult currentList(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExLendEntrust.class,request);
		filter.or("status=", String.valueOf(0));
		filter.or("status=", String.valueOf(1));
		filter.setOrderby(" created desc ");
		return super.findPage(filter);
	}

	@RequestMapping(value="/historyList")
	@ResponseBody
	public PageResult historyList(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExLendEntrust.class,request);
		filter.or("status=", String.valueOf(2));
		filter.or("status=", String.valueOf(3));
		filter.or("status=", String.valueOf(4));
		filter.setOrderby(" created desc ");
		return super.findPage(filter);
	}
	
	
	
	
}

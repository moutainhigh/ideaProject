/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-20 10:19:20 
 */
package hry.admin.exchange.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.AppLogThirdInterface;

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
 * @Date:        2018-07-20 10:19:20 
 */
@Controller
@RequestMapping("/exchange/applogthirdinterface")
public class AppLogThirdInterfaceController extends BaseController<AppLogThirdInterface, Long> {
	
	@Resource(name = "appLogThirdInterfaceService")
	@Override
	public void setService(BaseService<AppLogThirdInterface, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppLogThirdInterface appLogThirdInterface = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/applogthirdinterfacesee");
		mav.addObject("model", appLogThirdInterface);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppLogThirdInterface appLogThirdInterface){
		return super.save(appLogThirdInterface);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppLogThirdInterface appLogThirdInterface = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/applogthirdinterfacemodify");
		mav.addObject("model", appLogThirdInterface);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppLogThirdInterface appLogThirdInterface){
		return super.update(appLogThirdInterface);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppLogThirdInterface.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

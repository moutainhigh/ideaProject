/**
 * Copyright:    
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-29 13:36:05 
 */
package hry.admin.exchange.controller;


import hry.admin.exchange.service.ReleaseAdvertisementService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.ReleaseAdvertisement;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-29 13:36:05 
 */
@Controller
@RequestMapping("/exchange/releaseadvertisement")
public class ReleaseAdvertisementController extends BaseController<ReleaseAdvertisement, Long> {
	
	@Resource(name = "releaseAdvertisementService")
	@Override
	public void setService(BaseService<ReleaseAdvertisement, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ReleaseAdvertisement releaseAdvertisement = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/releaseadvertisementsee");
		mav.addObject("model", releaseAdvertisement);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ReleaseAdvertisement releaseAdvertisement){
		return super.save(releaseAdvertisement);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ReleaseAdvertisement releaseAdvertisement = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/releaseadvertisementmodify");
		mav.addObject("model", releaseAdvertisement);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ReleaseAdvertisement releaseAdvertisement){
		return super.update(releaseAdvertisement);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ReleaseAdvertisement.class,request);
		PageResult pageResult=((ReleaseAdvertisementService)service).findPageAll(filter);
		return pageResult;
	}
	
	
	
	
}

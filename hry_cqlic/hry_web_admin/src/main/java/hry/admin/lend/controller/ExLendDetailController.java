/**
 * Copyright:    
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 17:58:04 
 */
package hry.admin.lend.controller;


import hry.admin.lend.service.ExLendDetailService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.reward.model.page.FrontPage;
import hry.util.QueryFilter;
import hry.admin.lend.model.ExLendDetail;

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
 * @Date:        2018-10-18 17:58:04 
 */
@Controller
@RequestMapping("/lend/exlenddetail")
public class ExLendDetailController extends BaseController<ExLendDetail, Long> {
	
	@Resource(name = "exLendDetailService")
	@Override
	public void setService(BaseService<ExLendDetail, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExLendDetail exLendDetail = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/lend/exlenddetailsee");
		mav.addObject("model", exLendDetail);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExLendDetail exLendDetail){
		return super.save(exLendDetail);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExLendDetail exLendDetail = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/lend/exlenddetailmodify");
		mav.addObject("model", exLendDetail);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExLendDetail exLendDetail){
		return super.update(exLendDetail);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public FrontPage list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExLendDetail.class,request);
		return ((ExLendDetailService)service).findPageByFilter(filter);
	}
	
	
	
	
}

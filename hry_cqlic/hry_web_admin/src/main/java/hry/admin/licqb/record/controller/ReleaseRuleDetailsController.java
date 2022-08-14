/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-23 16:57:20 
 */
package hry.admin.licqb.record.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.record.model.ReleaseRuleDetails;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-23 16:57:20 
 */
@Controller
@RequestMapping("/licqb/record/releaseruledetails")
public class ReleaseRuleDetailsController extends BaseController<ReleaseRuleDetails, Long> {
	
	@Resource(name = "releaseRuleDetailsService")
	@Override
	public void setService(BaseService<ReleaseRuleDetails, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ReleaseRuleDetails releaseRuleDetails = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/record/releaseruledetailssee");
		mav.addObject("model", releaseRuleDetails);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ReleaseRuleDetails releaseRuleDetails){
		return super.save(releaseRuleDetails);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ReleaseRuleDetails releaseRuleDetails = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/record/releaseruledetailsmodify");
		mav.addObject("model", releaseRuleDetails);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ReleaseRuleDetails releaseRuleDetails){
		return super.update(releaseRuleDetails);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ReleaseRuleDetails.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

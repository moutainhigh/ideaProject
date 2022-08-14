/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:33:04 
 */
package hry.admin.klg.prizedraw.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hry.admin.klg.prizedraw.model.KlPrizedrawIssue;
import hry.admin.klg.prizedraw.model.KlPrizedrawSelection;
import hry.admin.klg.prizedraw.service.KlPrizedrawIssueService;
import hry.admin.klg.prizedraw.service.KlPrizedrawSelectionService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

/**
 * Copyright:   互融云
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:33:04 
 */
@Controller
@RequestMapping("/klg/prizedraw/klprizedrawselection")
public class KlPrizedrawSelectionController extends BaseController<KlPrizedrawSelection, Long> {
	
	@Resource(name = "klPrizedrawSelectionService")
	@Override
	public void setService(BaseService<KlPrizedrawSelection, Long> service) {
		super.service = service;
	}
	
	@Resource
	private KlPrizedrawIssueService klPrizedrawIssueService;
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlPrizedrawSelection klPrizedrawSelection = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/prizedraw/klprizedrawselectionsee");
		mav.addObject("model", klPrizedrawSelection);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlPrizedrawSelection klPrizedrawSelection){
		return super.save(klPrizedrawSelection);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlPrizedrawSelection klPrizedrawSelection = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/prizedraw/klprizedrawselectionmodify");
		mav.addObject("model", klPrizedrawSelection);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlPrizedrawSelection klPrizedrawSelection){
		return super.update(klPrizedrawSelection);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		/*QueryFilter filter = new QueryFilter(KlPrizedrawSelection.class,request);
		return super.findPage(filter);*/
		QueryFilter filter = new QueryFilter(KlPrizedrawSelection.class, request);
        PageResult findPageBySql = ((KlPrizedrawSelectionService) service).findPageBySql(filter);
        return findPageBySql;
	}
	
	@RequestMapping(value="/seeMobile/{issueNumber}")
	public ModelAndView seeMobile(@PathVariable String issueNumber){
		QueryFilter filter = new QueryFilter(KlPrizedrawIssue.class);
		filter.addFilter("issueNumber=", issueNumber);
		KlPrizedrawIssue klPrizedrawIssue = klPrizedrawIssueService.get(filter);
		ModelAndView mav = new ModelAndView("/admin/klg/prizedraw/zhongjiangMoblie");
		mav.addObject("model", klPrizedrawIssue);
		return mav;
	}
	
	
	
}

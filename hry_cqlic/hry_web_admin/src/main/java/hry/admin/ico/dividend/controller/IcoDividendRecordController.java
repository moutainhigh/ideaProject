/**
 * Copyright:    
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 20:56:02 
 */
package hry.admin.ico.dividend.controller;


import hry.admin.ico.dividend.service.IcoDividendRecordService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.dividend.model.IcoDividendRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 20:56:02 
 */
@Controller
@RequestMapping("/ico/dividend/icodividendrecord")
public class IcoDividendRecordController extends BaseController<IcoDividendRecord, Long> {
	
	@Resource(name = "icoDividendRecordService")
	@Override
	public void setService(BaseService<IcoDividendRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		IcoDividendRecord icoDividendRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/dividend/icodividendrecordsee");
		mav.addObject("model", icoDividendRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,IcoDividendRecord icoDividendRecord){
		return super.save(icoDividendRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		IcoDividendRecord icoDividendRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/dividend/icodividendrecordmodify");
		mav.addObject("model", icoDividendRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,IcoDividendRecord icoDividendRecord){
		return super.update(icoDividendRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoDividendRecord.class,request);
		return super.findPage(filter);
	}

	@RequestMapping(value="/findPageBySql")
	@ResponseBody
	public PageResult findPageBySql(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoDividendRecord.class,request);
		PageResult findPageBySql = ((IcoDividendRecordService) service).findPageBySql(filter);
		return findPageBySql;
	}

	@RequestMapping(value="/expenditureAccount")
	@ResponseBody
	public PageResult expenditureAccount (HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoDividendRecord.class,request);
		PageResult findPageBySql = ((IcoDividendRecordService) service).expenditureAccount(filter);
		return findPageBySql;
	}

	
}

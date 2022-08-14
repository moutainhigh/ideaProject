/**
 * Copyright:    
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 17:16:18 
 */
package hry.admin.ico.award.controller;


import hry.admin.ico.award.service.IcoAwardRecordService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.award.model.IcoAwardRecord;

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
 * @Date:        2019-01-14 17:16:18 
 */
@Controller
@RequestMapping("/ico/award/icoawardrecord")
public class IcoAwardRecordController extends BaseController<IcoAwardRecord, Long> {
	
	@Resource(name = "icoAwardRecordService")
	@Override
	public void setService(BaseService<IcoAwardRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		IcoAwardRecord icoAwardRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/award/icoawardrecordsee");
		mav.addObject("model", icoAwardRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,IcoAwardRecord icoAwardRecord){
		return super.save(icoAwardRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		IcoAwardRecord icoAwardRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/award/icoawardrecordmodify");
		mav.addObject("model", icoAwardRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,IcoAwardRecord icoAwardRecord){
		return super.update(icoAwardRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoAwardRecord.class,request);
		return super.findPage(filter);
	}

	@RequestMapping(value="/findPageBySql")
	@ResponseBody
	public PageResult findPageBySql(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoAwardRecord.class,request);
		PageResult findPageBySql = ((IcoAwardRecordService) service).findPageBySql(filter);
		return findPageBySql;
	}

}

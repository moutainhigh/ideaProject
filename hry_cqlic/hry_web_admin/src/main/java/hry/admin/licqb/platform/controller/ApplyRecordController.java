/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:46:02 
 */
package hry.admin.licqb.platform.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.platform.model.ApplyRecord;

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
 * @Date:        2019-08-12 17:46:02 
 */
@Controller
@RequestMapping("/licqb/platform/applyrecord")
public class ApplyRecordController extends BaseController<ApplyRecord, Long> {
	
	@Resource(name = "applyRecordService")
	@Override
	public void setService(BaseService<ApplyRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ApplyRecord applyRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/platform/applyrecordsee");
		mav.addObject("model", applyRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ApplyRecord applyRecord){
		return super.save(applyRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ApplyRecord applyRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/platform/applyrecordmodify");
		mav.addObject("model", applyRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ApplyRecord applyRecord){
		return super.update(applyRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ApplyRecord.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

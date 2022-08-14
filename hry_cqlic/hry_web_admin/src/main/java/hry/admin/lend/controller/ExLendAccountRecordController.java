/**
 * Copyright:    
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-11-06 16:45:27 
 */
package hry.admin.lend.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.lend.model.ExLendAccountRecord;

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
 * @Date:        2018-11-06 16:45:27 
 */
@Controller
@RequestMapping("/lend/exlendaccountrecord")
public class ExLendAccountRecordController extends BaseController<ExLendAccountRecord, Long> {
	
	@Resource(name = "exLendAccountRecordService")
	@Override
	public void setService(BaseService<ExLendAccountRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExLendAccountRecord exLendAccountRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/lend/exlendaccountrecordsee");
		mav.addObject("model", exLendAccountRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExLendAccountRecord exLendAccountRecord){
		return super.save(exLendAccountRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExLendAccountRecord exLendAccountRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/lend/exlendaccountrecordmodify");
		mav.addObject("model", exLendAccountRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExLendAccountRecord exLendAccountRecord){
		return super.update(exLendAccountRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExLendAccountRecord.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

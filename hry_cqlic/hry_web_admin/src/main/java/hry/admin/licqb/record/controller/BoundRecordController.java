/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-04-02 10:43:23 
 */
package hry.admin.licqb.record.controller;


import hry.admin.licqb.record.model.BoundRecord;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Copyright:   互融云
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-04-02 10:43:23 
 */
@Controller
@RequestMapping("/licqb/record/boundrecord")
public class BoundRecordController extends BaseController<BoundRecord, Long> {
	
	@Resource(name = "boundRecordService")
	@Override
	public void setService(BaseService<BoundRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		BoundRecord boundRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/record/boundrecordsee");
		mav.addObject("model", boundRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,BoundRecord boundRecord){
		return super.save(boundRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		BoundRecord boundRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/record/boundrecordmodify");
		mav.addObject("model", boundRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,BoundRecord boundRecord){
		return super.update(boundRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(BoundRecord.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

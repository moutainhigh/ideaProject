/**
 * Copyright:    
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-09-17 12:01:26 
 */
package hry.admin.exchange.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.BuildRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-09-17 12:01:26 
 */
@Controller
@RequestMapping("/exchange/buildrecord")
public class BuildRecordController extends BaseController<BuildRecord, Long> {
	
	@Resource(name = "buildRecordService")
	@Override
	public void setService(BaseService<BuildRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		BuildRecord buildRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/buildrecordsee");
		mav.addObject("model", buildRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,BuildRecord buildRecord){
		return super.save(buildRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		BuildRecord buildRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/buildrecordmodify");
		mav.addObject("model", buildRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,BuildRecord buildRecord){
		return super.update(buildRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		String priceCoinCode = request.getParameter("priceCoinCode");
		QueryFilter filter = new QueryFilter(BuildRecord.class,request);
		if(!StringUtils.isEmpty(priceCoinCode)){
			filter.addFilter("priceCoinCode=",priceCoinCode);
		}

		filter.setOrderby("pullTime desc");
		return super.findPage(filter);
	}
	
	
	
	
}

/**
 * Copyright:    
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-02 09:46:50 
 */
package hry.admin.web.controller;


import hry.admin.dic.service.AppDicService;
import hry.admin.web.model.AppWorkOrder;
import hry.admin.web.model.AppWorkOrderCategory;
import hry.admin.web.service.AppWorkOrderService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-02 09:46:50 
 */
@Controller
@RequestMapping("/web/appworkordercategory")
public class AppWorkOrderCategoryController extends BaseController<AppWorkOrderCategory, Long> {
	
	@Resource(name = "appWorkOrderCategoryService")
	@Override
	public void setService(BaseService<AppWorkOrderCategory, Long> service) {
		super.service = service;
	}

	@Resource(name = "appDicService")
	private AppDicService appDicService;
	@Resource(name = "appWorkOrderService")
	private AppWorkOrderService appWorkOrderService;

	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppWorkOrderCategory appWorkOrderCategory = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appworkordercategorysee");
		mav.addObject("model", appWorkOrderCategory);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppWorkOrderCategory appWorkOrderCategory){
		return super.save(appWorkOrderCategory);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppWorkOrderCategory appWorkOrderCategory = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appworkordercategorymodify");
		mav.addObject("model", appWorkOrderCategory);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppWorkOrderCategory appWorkOrderCategory){
		if(StringUtils.isEmpty(appWorkOrderCategory.getDescribes())){
			appWorkOrderCategory.setDescribes("-");
		}
		return super.update(appWorkOrderCategory);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		JsonResult jsonResult = new JsonResult();
		Boolean flag=true;
		AppWorkOrder appWorkOrder=new AppWorkOrder();
		String[] split = ids.split(",");
		for (String string : split) {
			appWorkOrder.setCategoryId(Long.valueOf(string));
			Long count = appWorkOrderService.count(appWorkOrder);
			if(count>0) {
				flag=false;
				break;
			}
		}
		if(flag) {
			jsonResult=super.deleteBatch(ids);
		}
		else {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("该类型被使用，无法删除");
		}

		return jsonResult;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppWorkOrderCategory.class,request);
		String state = request.getParameter("state");
		if (!StringUtils.isEmpty(state)) {
			filter.addFilter("state=",state);
		}
		filter.setOrderby("sort desc");
		return super.findPage(filter);
	}



	/**
	 * 通过语言查询类别
	 * @param request
	 * @return
	 */
	@RequestMapping("/ajaxSelectType")
	@ResponseBody
	public List<AppWorkOrderCategory> ajaxSelectType(HttpServletRequest request,String languageDic) {
		QueryFilter filter = new QueryFilter(AppWorkOrderCategory.class);
		filter.addFilter("languageDic=",languageDic);
		List<AppWorkOrderCategory> list = super.find(filter);
		return list;
	}
	
}

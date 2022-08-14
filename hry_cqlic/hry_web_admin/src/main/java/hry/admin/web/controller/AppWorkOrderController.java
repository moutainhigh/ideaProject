/**
 * Copyright:    
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-02 09:48:18 
 */
package hry.admin.web.controller;


import hry.admin.web.model.AppWorkOrder;
import hry.admin.web.service.AppWorkOrderService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.JsoupUtil;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Copyright:   互融云
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-02 09:48:18 
 */
@Controller
@RequestMapping("/web/appworkorder")
public class AppWorkOrderController extends BaseController<AppWorkOrder, Long> {
	
	@Resource(name = "appWorkOrderService")
	@Override
	public void setService(BaseService<AppWorkOrder, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppWorkOrder appWorkOrder = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appworkordersee");
		mav.addObject("model", appWorkOrder);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppWorkOrder appWorkOrder){
		return super.save(appWorkOrder);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppWorkOrder appWorkOrder = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appworkordermodify");
		mav.addObject("model", appWorkOrder);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	@CommonLog(name = "工单受理")
	public JsonResult modify(HttpServletRequest request, AppWorkOrder appWorkOrder){
		String replayContent = request.getParameter("editorValue");
		appWorkOrder.setReplyContent(JsoupUtil.clean(replayContent));
		AppWorkOrderService appWorkOrderService=(AppWorkOrderService)service;
		JsonResult jsonResult = appWorkOrderService.sendMessageToCustomer(appWorkOrder);
		if (jsonResult.getSuccess()) {
			appWorkOrder.setProcessTime(new Date());
			appWorkOrder.setState(1);
			super.update(appWorkOrder);
			return jsonResult;
		}
		return jsonResult.setSuccess(false).setMsg("受理失败！");
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request,AppWorkOrder appWorkOrder){
		QueryFilter filter = new QueryFilter(AppWorkOrder.class,request);
		AppWorkOrderService appWorkOrderService=(AppWorkOrderService)service;
		PageResult pageResult =appWorkOrderService.queryByPage(filter,appWorkOrder);
		return pageResult;
	}


	
	
}

/**
 * Copyright:    
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-19 11:31:39 
 */
package hry.admin.web.controller;


import hry.admin.web.model.AppServerMonitor;
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
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-19 11:31:39 
 */
@Controller
@RequestMapping("/web/appservermonitor")
public class AppServerMonitorController extends BaseController<AppServerMonitor, Long> {
	
	@Resource(name = "appServerMonitorService")
	@Override
	public void setService(BaseService<AppServerMonitor, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppServerMonitor appServerMonitor = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appservermonitorsee");
		mav.addObject("model", appServerMonitor);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppServerMonitor appServerMonitor){
		AppServerMonitor app=new AppServerMonitor();
		app.setServerHost(appServerMonitor.getServerHost());
		app.setServerPort(appServerMonitor.getServerPort());
		long count=service.count(app);
		if(count>0){
			return new JsonResult().setSuccess(false).setMsg("该服务已经添加");
		}
		return super.save(appServerMonitor);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppServerMonitor appServerMonitor = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appservermonitormodify");
		mav.addObject("model", appServerMonitor);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppServerMonitor appServerMonitor){
		AppServerMonitor app=new AppServerMonitor();
		app.setServerHost(appServerMonitor.getServerHost());
		app.setServerPort(appServerMonitor.getServerPort());
		long count=service.count(app);
		if(count>0){
			AppServerMonitor app1=new AppServerMonitor();
			app1.setId(appServerMonitor.getId());
			app1.setServerHost(appServerMonitor.getServerHost());
			app1.setServerPort(appServerMonitor.getServerPort());
			long count1=service.count(app1);
			if(count1==0) {
				return new JsonResult().setSuccess(false).setMsg("该服务已经添加");
			}
		}
		return super.update(appServerMonitor);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppServerMonitor.class,request);
		PageResult pageResult=super.findPage(filter);
		List<AppServerMonitor> list = pageResult.getRows();
		for (AppServerMonitor appServerMonitor:list ) {
			appServerMonitor.setServerHost("***.***.***"+appServerMonitor.getServerHost().substring(appServerMonitor.getServerHost().lastIndexOf("."),appServerMonitor.getServerHost().length()));
		}
		pageResult.setRows(list);
		return pageResult;
	}

	@RequestMapping(value = "/openOrClose")
	@ResponseBody
	public JsonResult openOrClose (HttpServletRequest request) {
		String id = request.getParameter("id");
		JsonResult jsonResult = new JsonResult();
		AppServerMonitor appServerMonitor = service.get(new Long(id));
		if (appServerMonitor != null) {
			if (appServerMonitor.getIsOpen() == 1) {
				appServerMonitor.setIsOpen(0);
			} else {
				appServerMonitor.setIsOpen(1);
			}
			return super.update(appServerMonitor);
		}
		jsonResult.setSuccess(false);
		return jsonResult;
	}
	
}

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-29 15:01:17 
 */
package hry.otc.releaseAdvertisement.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.otc.releaseAdvertisement.model.AppAppeal;
import hry.util.QueryFilter;

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-29 15:01:17 
 */
@Controller
@RequestMapping("/releaseAdvertisement/appappeal")
public class AppAppealController extends BaseController<AppAppeal, Long> {
	
	@Resource(name = "appAppealService")
	@Override
	public void setService(BaseService<AppAppeal, Long> service) {
		super.service = service;
	}
	
	@MethodName(name = "查看AppAppeal")
	@RequestMapping(value="/see/{id}")
	@MyRequiresPermissions
	@ResponseBody
	public AppAppeal see(@PathVariable Long id){
		AppAppeal appAppeal = service.get(id);
		return appAppeal;
	}
	
	@MethodName(name="增加AppAppeal")
	@RequestMapping(value="/add")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppAppeal appAppeal){
		return super.save(appAppeal);
	}
	
	@MethodName(name="修改AppAppeal")
	@RequestMapping(value="/modify")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppAppeal appAppeal){
		return super.update(appAppeal);
	}
	
	@MethodName(name="删除AppAppeal")
	@RequestMapping(value="/remove/{ids}")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult remove(@PathVariable String ids){
		return super.deleteBatch(ids);
	}
	
	@MethodName(name = "列表AppAppeal")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppAppeal.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

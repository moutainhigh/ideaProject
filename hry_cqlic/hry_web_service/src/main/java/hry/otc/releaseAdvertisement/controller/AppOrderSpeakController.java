/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-29 14:22:05 
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
import hry.otc.releaseAdvertisement.model.AppOrderSpeak;
import hry.util.QueryFilter;

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-29 14:22:05 
 */
@Controller
@RequestMapping("/releaseAdvertisement/apporderspeak")
public class AppOrderSpeakController extends BaseController<AppOrderSpeak, Long> {
	
	@Resource(name = "appOrderSpeakService")
	@Override
	public void setService(BaseService<AppOrderSpeak, Long> service) {
		super.service = service;
	}
	
	@MethodName(name = "查看AppOrderSpeak")
	@RequestMapping(value="/see/{id}")
	@MyRequiresPermissions
	@ResponseBody
	public AppOrderSpeak see(@PathVariable Long id){
		AppOrderSpeak appOrderSpeak = service.get(id);
		return appOrderSpeak;
	}
	
	@MethodName(name="增加AppOrderSpeak")
	@RequestMapping(value="/add")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppOrderSpeak appOrderSpeak){
		return super.save(appOrderSpeak);
	}
	
	@MethodName(name="修改AppOrderSpeak")
	@RequestMapping(value="/modify")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppOrderSpeak appOrderSpeak){
		return super.update(appOrderSpeak);
	}
	
	@MethodName(name="删除AppOrderSpeak")
	@RequestMapping(value="/remove/{ids}")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult remove(@PathVariable String ids){
		return super.deleteBatch(ids);
	}
	
	@MethodName(name = "列表AppOrderSpeak")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppOrderSpeak.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

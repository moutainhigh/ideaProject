/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-08-10 15:10:34 
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
import hry.otc.releaseAdvertisement.model.OtcAppLog;
import hry.util.QueryFilter;

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-08-10 15:10:34 
 */
@Controller
@RequestMapping("/releaseAdvertisement/otcapplog")
public class OtcAppLogController extends BaseController<OtcAppLog, Long> {
	
	@Resource(name = "otcAppLogService")
	@Override
	public void setService(BaseService<OtcAppLog, Long> service) {
		super.service = service;
	}
	
	@MethodName(name = "查看OtcAppLog")
	@RequestMapping(value="/see/{id}")
	@MyRequiresPermissions
	@ResponseBody
	public OtcAppLog see(@PathVariable Long id){
		OtcAppLog otcAppLog = service.get(id);
		return otcAppLog;
	}
	
	@MethodName(name="增加OtcAppLog")
	@RequestMapping(value="/add")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult add(HttpServletRequest request,OtcAppLog otcAppLog){
		return super.save(otcAppLog);
	}
	
	@MethodName(name="修改OtcAppLog")
	@RequestMapping(value="/modify")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,OtcAppLog otcAppLog){
		return super.update(otcAppLog);
	}
	
	@MethodName(name="删除OtcAppLog")
	@RequestMapping(value="/remove/{ids}")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult remove(@PathVariable String ids){
		return super.deleteBatch(ids);
	}
	
	@MethodName(name = "列表OtcAppLog")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(OtcAppLog.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

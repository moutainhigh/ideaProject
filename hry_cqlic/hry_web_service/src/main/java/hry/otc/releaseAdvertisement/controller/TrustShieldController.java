/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-27 14:52:56 
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
import hry.otc.releaseAdvertisement.model.TrustShield;
import hry.util.QueryFilter;

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-27 14:52:56 
 */
@Controller
@RequestMapping("/releaseAdvertisement/trustshield")
public class TrustShieldController extends BaseController<TrustShield, Long> {
	
	@Resource(name = "trustShieldService")
	@Override
	public void setService(BaseService<TrustShield, Long> service) {
		super.service = service;
	}
	
	@MethodName(name = "查看TrustShield")
	@RequestMapping(value="/see/{id}")
	@MyRequiresPermissions
	@ResponseBody
	public TrustShield see(@PathVariable Long id){
		TrustShield trustShield = service.get(id);
		return trustShield;
	}
	
	@MethodName(name="增加TrustShield")
	@RequestMapping(value="/add")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult add(HttpServletRequest request,TrustShield trustShield){
		return super.save(trustShield);
	}
	
	@MethodName(name="修改TrustShield")
	@RequestMapping(value="/modify")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,TrustShield trustShield){
		return super.update(trustShield);
	}
	
	@MethodName(name="删除TrustShield")
	@RequestMapping(value="/remove/{ids}")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult remove(@PathVariable String ids){
		return super.deleteBatch(ids);
	}
	
	@MethodName(name = "列表TrustShield")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(TrustShield.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-22 11:39:46 
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
import hry.otc.releaseAdvertisement.model.ReleaseAdvertisement;
import hry.otc.releaseAdvertisement.service.ReleaseAdvertisementService;
import hry.util.QueryFilter;

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-22 11:39:46 
 */
@Controller
@RequestMapping("/releaseAdvertisement/releaseadvertisement")
public class ReleaseAdvertisementController extends BaseController<ReleaseAdvertisement, Long> {
	
	@Resource(name = "releaseAdvertisementService")
	@Override
	public void setService(BaseService<ReleaseAdvertisement, Long> service) {
		super.service = service;
	}
	
	@MethodName(name = "查看ReleaseAdvertisement")
	@RequestMapping(value="/see/{id}")
	@MyRequiresPermissions
	@ResponseBody
	public ReleaseAdvertisement see(@PathVariable Long id){
		ReleaseAdvertisement releaseAdvertisement = service.get(id);
		return releaseAdvertisement;
	}
	
	@MethodName(name="增加ReleaseAdvertisement")
	@RequestMapping(value="/add")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ReleaseAdvertisement releaseAdvertisement){
		return super.save(releaseAdvertisement);
	}
	
	@MethodName(name="修改ReleaseAdvertisement")
	@RequestMapping(value="/modify")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ReleaseAdvertisement releaseAdvertisement){
		return super.update(releaseAdvertisement);
	}
	
	@MethodName(name="删除ReleaseAdvertisement")
	@RequestMapping(value="/remove/{ids}")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult remove(@PathVariable String ids){
		return super.deleteBatch(ids);
	}
	
	@MethodName(name = "列表ReleaseAdvertisement")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ReleaseAdvertisement.class,request);
		PageResult pageResult=((ReleaseAdvertisementService)service).findPageAll(filter);
		return pageResult;
	}
	
	
	
	
}

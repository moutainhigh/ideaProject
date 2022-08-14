/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-29 16:39:22 
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
import hry.otc.releaseAdvertisement.model.ExCoinFee;
import hry.util.QueryFilter;

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-29 16:39:22 
 */
@Controller
@RequestMapping("/releaseAdvertisement/excoinfee")
public class ExCoinFeeController extends BaseController<ExCoinFee, Long> {
	
	@Resource(name = "exCoinFeeService")
	@Override
	public void setService(BaseService<ExCoinFee, Long> service) {
		super.service = service;
	}
	
	@MethodName(name = "查看ExCoinFee")
	@RequestMapping(value="/see/{id}")
	@MyRequiresPermissions
	@ResponseBody
	public ExCoinFee see(@PathVariable Long id){
		ExCoinFee exCoinFee = service.get(id);
		return exCoinFee;
	}
	
	@MethodName(name="增加ExCoinFee")
	@RequestMapping(value="/add")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExCoinFee exCoinFee){
		return super.save(exCoinFee);
	}
	
	@MethodName(name="修改ExCoinFee")
	@RequestMapping(value="/modify")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExCoinFee exCoinFee){
		return super.update(exCoinFee);
	}
	
	@MethodName(name="删除ExCoinFee")
	@RequestMapping(value="/remove/{ids}")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult remove(@PathVariable String ids){
		return super.deleteBatch(ids);
	}
	
	@MethodName(name = "列表ExCoinFee")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExCoinFee.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

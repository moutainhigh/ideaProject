/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-25 15:56:13 
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
import hry.otc.releaseAdvertisement.model.ExCoinExchangeRate;
import hry.util.QueryFilter;

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-25 15:56:13 
 */
@Controller
@RequestMapping("/releaseAdvertisement/excoinexchangerate")
public class ExCoinExchangeRateController extends BaseController<ExCoinExchangeRate, Long> {
	
	@Resource(name = "exCoinExchangeRateService")
	@Override
	public void setService(BaseService<ExCoinExchangeRate, Long> service) {
		super.service = service;
	}
	
	@MethodName(name = "查看ExCoinExchangeRate")
	@RequestMapping(value="/see/{id}")
	@MyRequiresPermissions
	@ResponseBody
	public ExCoinExchangeRate see(@PathVariable Long id){
		ExCoinExchangeRate exCoinExchangeRate = service.get(id);
		return exCoinExchangeRate;
	}
	
	@MethodName(name="增加ExCoinExchangeRate")
	@RequestMapping(value="/add")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExCoinExchangeRate exCoinExchangeRate){
		return super.save(exCoinExchangeRate);
	}
	
	@MethodName(name="修改ExCoinExchangeRate")
	@RequestMapping(value="/modify")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExCoinExchangeRate exCoinExchangeRate){
		return super.update(exCoinExchangeRate);
	}
	
	@MethodName(name="删除ExCoinExchangeRate")
	@RequestMapping(value="/remove/{ids}")
	@MyRequiresPermissions
	@ResponseBody
	public JsonResult remove(@PathVariable String ids){
		return super.deleteBatch(ids);
	}
	
	@MethodName(name = "列表ExCoinExchangeRate")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExCoinExchangeRate.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

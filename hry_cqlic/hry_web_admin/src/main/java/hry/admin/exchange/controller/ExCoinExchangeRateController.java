/**
 * Copyright:    
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-26 17:05:34 
 */
package hry.admin.exchange.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.ExCoinExchangeRate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-26 17:05:34 
 */
@Controller
@RequestMapping("/exchange/excoinexchangerate")
public class ExCoinExchangeRateController extends BaseController<ExCoinExchangeRate, Long> {
	
	@Resource(name = "exCoinExchangeRateService")
	@Override
	public void setService(BaseService<ExCoinExchangeRate, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExCoinExchangeRate exCoinExchangeRate = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/excoinexchangeratesee");
		mav.addObject("model", exCoinExchangeRate);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExCoinExchangeRate exCoinExchangeRate){
		JsonResult jsonResult = super.save(exCoinExchangeRate);
		if(jsonResult.getSuccess()){
			RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
			//redisService.sadd(exCoinExchangeRate.getBaseCoinCode() + "_" + exCoinExchangeRate.getTargetCoinCode(), exCoinExchangeRate.getExchangeRate() + "");
			redisService.save("otc:" + exCoinExchangeRate.getBaseCoinCode() + "_" + exCoinExchangeRate.getTargetCoinCode(), exCoinExchangeRate.getExchangeRate() + "");
		}
		return jsonResult;
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExCoinExchangeRate exCoinExchangeRate = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/excoinexchangeratemodify");
		mav.addObject("model", exCoinExchangeRate);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExCoinExchangeRate exCoinExchangeRate){
		return super.update(exCoinExchangeRate);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExCoinExchangeRate.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

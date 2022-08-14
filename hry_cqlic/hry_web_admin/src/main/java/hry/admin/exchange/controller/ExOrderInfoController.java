/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:26:42 
 */
package hry.admin.exchange.controller;


import com.alibaba.fastjson.JSONObject;
import hry.admin.exchange.model.ExProduct;
import hry.admin.exchange.service.ExOrderInfoService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.ExOrderInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:26:42 
 */
@Controller
@RequestMapping("/exchange/exorderinfo")
public class ExOrderInfoController extends BaseController<ExOrderInfo, Long> {
	
	@Resource(name = "exOrderInfoService")
	@Override
	public void setService(BaseService<ExOrderInfo, Long> service) {
		super.service = service;
	}

	@Resource
	private RedisService redisService;
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExOrderInfo exOrderInfo = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exorderinfosee");
		mav.addObject("model", exOrderInfo);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExOrderInfo exOrderInfo){
		return super.save(exOrderInfo);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExOrderInfo exOrderInfo = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exorderinfomodify");
		mav.addObject("model", exOrderInfo);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExOrderInfo exOrderInfo){
		return super.update(exOrderInfo);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExOrderInfo.class,request);

		return ((ExOrderInfoService) service).findPageBySql(filter);
	}

	@MethodName(name = "查询订单平台收取费用台账")
	@RequestMapping("/listfees")
	@ResponseBody
	public PageResult listfees(HttpServletRequest request) {
		QueryFilter filter = new QueryFilter(ExOrderInfo.class,request);
		filter.setOrderby("transactionTime desc");
		PageResult findPage = ((ExOrderInfoService) service).findPageBySql(filter);
		List<ExOrderInfo> list=(List<ExOrderInfo>)findPage.getRows();
		for(ExOrderInfo l:list){
			ExProduct exProduct = getExproduct(l.getCoinCode());
			Integer keepDecimalForCoin = 6;
			if(null != exProduct){
				keepDecimalForCoin = exProduct.getKeepDecimalForCoin();
			}
			if(null!=l.getTransactionFee()){
				l.setTransactionFee(l.getTransactionFee().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_UP));
			}
			if(null!=l.getTransactionPrice()){
				l.setTransactionPrice(l.getTransactionPrice().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_UP));
			}
			if(null!=l.getTransactionCount()){
				l.setTransactionCount(l.getTransactionCount().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_UP));
			}
			if(null!=l.getTransactionSum()){
				l.setTransactionSum(l.getTransactionSum().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_UP));
			}
			if(l.getTransactionFeeRate()==null){
				l.setTransactionFeeRate(new BigDecimal(0));
			}
			if(l.getTransactionFee()==null){
				l.setTransactionFee(new BigDecimal(0));
			}
		}
		return findPage;
	}

	public ExProduct getExproduct(String coinCode){
		String exProductStr = redisService.hget("ex:Product",coinCode);
		return StringUtils.isEmpty(exProductStr)?null: JSONObject.parseObject(exProductStr,ExProduct.class);
	}
	
	
}

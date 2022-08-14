/**
 * Copyright:    
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-11-26 17:36:28 
 */
package hry.mall.order.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.mall.order.model.OrderCoin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-11-26 17:36:28 
 */
@Controller
@RequestMapping("/order/ordercoin")
public class OrderCoinController extends BaseController<OrderCoin, Long> {
	
	@Resource(name = "orderCoinService")
	@Override
	public void setService(BaseService<OrderCoin, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		OrderCoin orderCoin = service.get(id);
		ModelAndView mav = new ModelAndView("/mall/order/ordercoinsee");
		mav.addObject("model", orderCoin);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,OrderCoin orderCoin){
		return super.save(orderCoin);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		OrderCoin orderCoin = service.get(id);
		ModelAndView mav = new ModelAndView("/mall/order/ordercoinmodify");
		mav.addObject("model", orderCoin);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,OrderCoin orderCoin){
		return super.update(orderCoin);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(OrderCoin.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

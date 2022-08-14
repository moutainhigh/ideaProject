/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-16 14:25:18 
 */
package hry.admin.klg.transaction.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hry.admin.klg.transaction.model.KlgBuyTransaction;
import hry.admin.klg.transaction.model.KlgSellTransaction;
import hry.admin.klg.transaction.service.KlgBuyTransactionService;
import hry.admin.klg.transaction.service.KlgSellTransactionService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

/**
 * Copyright:   互融云
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-16 14:25:18 
 */
@Controller
@RequestMapping("/klg/transaction/klgselltransaction")
public class KlgSellTransactionController extends BaseController<KlgSellTransaction, Long> {
	
	@Resource(name = "klgSellTransactionService")
	@Override
	public void setService(BaseService<KlgSellTransaction, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgSellTransaction klgSellTransaction = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/transaction/klgselltransactionsee");
		mav.addObject("model", klgSellTransaction);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgSellTransaction klgSellTransaction){
		return super.save(klgSellTransaction);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgSellTransaction klgSellTransaction = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/transaction/klgselltransactionmodify");
		mav.addObject("model", klgSellTransaction);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgSellTransaction klgSellTransaction){
		return super.update(klgSellTransaction);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		/*QueryFilter filter = new QueryFilter(KlgSellTransaction.class,request);
		return super.findPage(filter);*/
		QueryFilter filter = new QueryFilter(KlgSellTransaction.class, request);
        PageResult findPageBySql = ((KlgSellTransactionService) service).findPageBySql(filter);
        return findPageBySql;
	}
	
	@RequestMapping(value="/findListGroupByday")
	@ResponseBody
	public PageResult findListGroupByday(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgBuyTransaction.class, request);
        PageResult findPageBySql = ((KlgSellTransactionService) service).findPageGroupBydaySql(filter);
        return findPageBySql;
	}
	
	
}

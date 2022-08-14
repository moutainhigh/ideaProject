/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-16 11:40:59 
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
import hry.admin.klg.transaction.service.KlgBuyTransactionService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

/**
 * Copyright:   互融云
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-16 11:40:59 
 */
@Controller
@RequestMapping("/klg/transaction/klgbuytransaction")
public class KlgBuyTransactionController extends BaseController<KlgBuyTransaction, Long> {
	
	@Resource(name = "klgBuyTransactionService")
	@Override
	public void setService(BaseService<KlgBuyTransaction, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgBuyTransaction klgBuyTransaction = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/transaction/klgbuytransactionsee");
		mav.addObject("model", klgBuyTransaction);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgBuyTransaction klgBuyTransaction){
		return super.save(klgBuyTransaction);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgBuyTransaction klgBuyTransaction = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/transaction/klgbuytransactionmodify");
		mav.addObject("model", klgBuyTransaction);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgBuyTransaction klgBuyTransaction){
		return super.update(klgBuyTransaction);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		/*QueryFilter filter = new QueryFilter(KlgBuyTransaction.class,request);
		return super.findPage(filter);*/
		QueryFilter filter = new QueryFilter(KlgBuyTransaction.class, request);
        PageResult findPageBySql = ((KlgBuyTransactionService) service).findPageBySql(filter);
        return findPageBySql;
	}
	
	@RequestMapping(value="/findListGroupByday")
	@ResponseBody
	public PageResult findListGroupByday(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgBuyTransaction.class, request);
        PageResult findPageBySql = ((KlgBuyTransactionService) service).findPageGroupBydaySql(filter);
        return findPageBySql;
	}
	
	/**
	 * 利息解冻确认按钮
	 * @return
	 */
	@RequestMapping(value="/jiedongSubmit")
	@ResponseBody
	public JsonResult jiedongSubmit(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		String  ids = request.getParameter("ids");
		return ((KlgBuyTransactionService) service).jiedongSubmit(ids);
	}
	
	
}

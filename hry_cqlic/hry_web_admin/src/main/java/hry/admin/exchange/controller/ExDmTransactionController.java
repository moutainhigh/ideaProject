/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:59:41 
 */
package hry.admin.exchange.controller;


import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:59:41 
 */
@Controller
@RequestMapping("/exchange/exdmtransaction")
public class ExDmTransactionController extends BaseController<ExDmTransaction, Long> {
	
	@Resource(name = "exDmTransactionService")
	@Override
	public void setService(BaseService<ExDmTransaction, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExDmTransaction exDmTransaction = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exdmtransactionsee");
		mav.addObject("model", exDmTransaction);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExDmTransaction exDmTransaction){
		return super.save(exDmTransaction);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExDmTransaction exDmTransaction = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exdmtransactionmodify");
		mav.addObject("model", exDmTransaction);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExDmTransaction exDmTransaction){
		return super.update(exDmTransaction);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExDmTransaction.class, request);

		String transactionType = request.getParameter("transactionType");
		String status = request.getParameter("status");
		if (!org.springframework.util.StringUtils.isEmpty(transactionType) && !org.springframework.util.StringUtils.isEmpty(status) && Integer.valueOf(transactionType).intValue() == 2 && Integer.valueOf(status).intValue() == 1) {
			filter.addFilter("remark!=", "C2C卖币");
		}

		PageResult page = ((ExDmTransactionService) service).findPageBySql(filter);

		return page;
	}



	@RequestMapping(value = "/post")
	@MethodName(name = "通过一个提币订单")
	@ResponseBody
	@CommonLog(name = "提币审核-通过")
	public synchronized JsonResult post(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		String googleCode = request.getParameter("google_code");
		Map<String, String> params = new HashMap<String, String>();
		params.put("ids", ids);
		params.put("googleCode", googleCode);
		return ((ExDmTransactionService) service).checkPass(params);
	}

	@RequestMapping(value = "/stop/{ids}")
	@MethodName(name = "驳回一个订单")
	@ResponseBody
	@CommonLog(name = "提币审核-驳回")
	public synchronized JsonResult stop(@PathVariable String ids, HttpServletRequest request) {
		String str = request.getParameter("reason");
		Map<String, String> params = new HashMap<String, String>();
		params.put("ids", ids);
		params.put("reason", str);
		return ((ExDmTransactionService) service).checkReject(params);

	}

}

/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:34:46 
 */
package hry.admin.c2c.controller;


import com.alibaba.fastjson.JSON;
import hry.admin.c2c.model.C2cTransaction;
import hry.admin.c2c.service.C2cTransactionService;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.web.model.AppWorkOrder;
import hry.admin.web.service.AppWorkOrderService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:34:46 
 */
@Controller
@RequestMapping("/c2c/c2ctransaction")
public class C2cTransactionController extends BaseController<C2cTransaction, Long> {
	
	@Resource(name = "c2cTransactionService")
	@Override
	public void setService(BaseService<C2cTransaction, Long> service) {
		super.service = service;
	}

	@Resource
	private AppCustomerService appCustomerService;

	@Resource
	private ExDmTransactionService exDmTransactionService;

	@Resource
	private AppWorkOrderService appWorkOrderService;
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		C2cTransaction c2cTransaction = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/c2c/c2ctransactionsee");
		mav.addObject("model", c2cTransaction);
		String c2cOrderDetail = ((C2cTransactionService)service).getC2cOrderDetail(c2cTransaction.getTransactionNum());
		mav.addObject("detail", JSON.parseObject(c2cOrderDetail));
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,C2cTransaction c2cTransaction){
		return super.save(c2cTransaction);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		C2cTransaction c2cTransaction = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/c2c/c2ctransactionmodify");
		mav.addObject("model", c2cTransaction);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,C2cTransaction c2cTransaction){
		return super.update(c2cTransaction);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(C2cTransaction.class, request);
		PageResult findPage =  ((C2cTransactionService) service).findPageBySql(filter);
		return findPage;
	}


	@RequestMapping(value="/findCountByState")
	@ResponseBody
	public JsonResult findCountByState(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		Map<String,Object> map = new HashMap<>();

		QueryFilter filter = new QueryFilter(C2cTransaction.class);
		filter.addFilter("status=","1");
		List<C2cTransaction> c2cList =service.find(filter);
		//添加才c2c的数量
		map.put("c2cSize",c2cList.size());

		QueryFilter queryFilter = new QueryFilter(AppCustomer.class);
		queryFilter.addFilter("states=","1");
		List<AppCustomer> cusList = appCustomerService.find(queryFilter);
		//添加未审核会员的数量
		map.put("customerSize",cusList.size());
		//已拒绝


		QueryFilter transFilter = new QueryFilter(ExDmTransaction.class);
		transFilter.addFilter("status=","1");
		transFilter.addFilter("transactionType=","2");
		transFilter.addFilter("optType!=","10");
		List<ExDmTransaction> transList = exDmTransactionService.find(transFilter);
		//添加提币审核信息
		map.put("transactionSize",transList.size());

		QueryFilter workorderFilter = new QueryFilter(AppWorkOrder.class);
		workorderFilter.addFilter("state!=","1");
		List<AppWorkOrder> workOrderList = appWorkOrderService.find(workorderFilter);
		map.put("workOrderList",workOrderList.size());

		jsonResult.setSuccess(true);
		jsonResult.setObj(map);




		return jsonResult;
	}


	/**
	 * 关闭交易
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/close/{ids}",method = RequestMethod.POST)
	@ResponseBody
	@CommonLog(name = "C2C交易关闭")
	public JsonResult close(@PathVariable String ids) {
		JsonResult jsonResult = new JsonResult();

		String[] arr = ids.split(",");
		for (String id : arr) {
			jsonResult = ((C2cTransactionService) service).close(Long.valueOf(id));
		}

		return jsonResult;
	}


	/**
	 * 确认交易
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/confirm/{ids}",method = RequestMethod.POST)
	@ResponseBody
	@CommonLog(name = "C2C确认到账")
	public JsonResult confirm(@PathVariable String ids) {

		JsonResult jsonResult = new JsonResult();

		String[] arr = ids.split(",");
		for (String id : arr) {
			jsonResult = ((C2cTransactionService) service).confirm(Long.valueOf(id));
		}

		return jsonResult;
	}
	
	
	
	
}

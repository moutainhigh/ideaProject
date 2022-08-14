/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:12:40 
 */
package hry.admin.exchange.controller;


import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.exchange.model.Coin;
import hry.admin.exchange.model.ExEntrust;
import hry.admin.exchange.model.ExOrderInfo;
import hry.admin.exchange.service.ExEntrustService;
import hry.admin.exchange.service.ProductCommonService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.trade.redis.model.EntrustTrade;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:12:40 
 */
@Controller
@RequestMapping("/exchange/exentrust")
public class ExEntrustController extends BaseController<ExEntrust, Long> {
	
	@Resource(name = "exEntrustService")
	@Override
	public void setService(BaseService<ExEntrust, Long> service) {
		super.service = service;
	}

	@Resource
	private ProductCommonService productCommonService;

	@Resource
	private AppCustomerService appCustomerService;

	@Resource
	private ExEntrustService entrustService;
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExEntrust exEntrust = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exentrustsee");
		mav.addObject("model", exEntrust);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExEntrust exEntrust){
		return super.save(exEntrust);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExEntrust exEntrust = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/exentrustmodify");
		mav.addObject("model", exEntrust);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExEntrust exEntrust){
		return super.update(exEntrust);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExEntrust.class,request);
		PageResult findPage = ((ExEntrustService) service).findPageBySql(filter);
		List<ExEntrust> list=(List<ExEntrust>)findPage.getRows();

		for(ExEntrust l:list){
			AppCustomer appCustomer = appCustomerService.get(l.getCustomerId());
			if(appCustomer!=null)
			l.setCustomerIp(appCustomer.getMessIp()==null ? "" : appCustomer.getMessIp());

			Coin productCommon = productCommonService.getProductCommon(l.getCoinCode(),l.getFixPriceCoinCode());
			if(null!=productCommon){
				int keepDecimalForCoin=productCommon.getKeepDecimalForCoin();
				int keepDecimalForCurrency=productCommon.getKeepDecimalForCurrency();
				l.setEntrustPrice(l.getEntrustPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_UP));
				l.setEntrustCount(l.getEntrustCount().setScale(keepDecimalForCoin,BigDecimal.ROUND_HALF_UP));
				l.setSurplusEntrustCount((l.getEntrustCount().subtract(l.getSurplusEntrustCount())).setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_UP));
				l.setKeepDecimalForCoin(keepDecimalForCoin);
				l.setKeepDecimalForCurrency(keepDecimalForCurrency);
			}
			l.setEntrustSum(l.getEntrustSum().setScale(10, BigDecimal.ROUND_HALF_UP));

			l.setTransactionFee(l.getTransactionFee().setScale(10, BigDecimal.ROUND_HALF_UP));
		}
		return findPage;
	}

	@RequestMapping(value="/lendList")
	@ResponseBody
	public PageResult lendList(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExEntrust.class,request);
		PageResult findPage = ((ExEntrustService) service).findLendPageBySql(filter);
		List<ExEntrust> list=(List<ExEntrust>)findPage.getRows();

		for(ExEntrust l:list){
			AppCustomer appCustomer = appCustomerService.get(l.getCustomerId());
			if(appCustomer!=null)
			l.setCustomerIp(appCustomer.getMessIp()==null ? "" : appCustomer.getMessIp());

			Coin productCommon = productCommonService.getProductCommon(l.getCoinCode(),l.getFixPriceCoinCode());
			if(null!=productCommon){
				int keepDecimalForCoin=productCommon.getKeepDecimalForCoin();
				int keepDecimalForCurrency=productCommon.getKeepDecimalForCurrency();
				l.setEntrustPrice(l.getEntrustPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_UP));
				l.setEntrustCount(l.getEntrustCount().setScale(keepDecimalForCoin,BigDecimal.ROUND_HALF_UP));
				l.setSurplusEntrustCount((l.getEntrustCount().subtract(l.getSurplusEntrustCount())).setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_UP));
				l.setKeepDecimalForCoin(keepDecimalForCoin);
				l.setKeepDecimalForCurrency(keepDecimalForCurrency);
			}
			l.setEntrustSum(l.getEntrustSum().setScale(10, BigDecimal.ROUND_HALF_UP));

			l.setTransactionFee(l.getTransactionFee().setScale(10, BigDecimal.ROUND_HALF_UP));
		}
		return findPage;
	}

	@MethodName(name = "撤销委托订单")
	@RequestMapping(value = "/cancelExEntrust")
	@ResponseBody
	@CommonLog(name = "后台撤销委托单")
	public JsonResult cancelExEntrust(HttpServletRequest request) {
		JsonResult jsonResult = new JsonResult();
		String entrustNums = request.getParameter("entrustNums").toString();
		String[] id=entrustNums.split(",");
		for(int i=0;i<id.length;i++){
			EntrustTrade entrustTrade= new EntrustTrade();
			QueryFilter filter = new QueryFilter(ExEntrust.class);
			filter.addFilter("entrustNum_in", entrustNums);
			List<ExEntrust> list = this.find(filter);
			if(null!=list&&list.size()>0){
				entrustTrade.setEntrustNum(list.get(i).getEntrustNum());
				entrustTrade.setCoinCode(list.get(i).getCoinCode());
				entrustTrade.setType(list.get(i).getType());
				entrustTrade.setFixPriceCoinCode(list.get(i).getFixPriceCoinCode());
				entrustTrade.setEntrustPrice(list.get(i).getEntrustPrice());
				entrustService.cancelExEntrust(entrustTrade, "平台手动取消");
			}

		}
		//	entrustService.cancelExEntrust(id[i].toString(),null,"平台手动取消");
		jsonResult.setSuccess(true);
		jsonResult.setMsg("撤销成功");
		// }
		return jsonResult;

	}

	@MethodName(name = "查询订单匹配详情")
	@RequestMapping(value = "/orderFindByentrustNum")
	@ResponseBody
	public  List<ExOrderInfo> orderFindByentrustNum(HttpServletRequest request) {
		String entrustNum = request.getParameter("entrustNum");
		String coinCode = request.getParameter("coinCode");
		String type = request.getParameter("type");
		List<ExOrderInfo> list=entrustService.getMatchDetail(entrustNum, coinCode,type);
		for(ExOrderInfo l:list){
			Coin productCommon = productCommonService.getProductCommon(l.getCoinCode(),l.getFixPriceCoinCode());
			if(null!=productCommon){
				int keepDecimalForCoin=(productCommon.getKeepDecimalForCoin());
				int keepDecimalForCurrency=(productCommon.getKeepDecimalForCurrency());
				l.setTransactionPrice(l.getTransactionPrice().setScale(keepDecimalForCurrency,BigDecimal.ROUND_HALF_UP));
				l.setTransactionCount(l.getTransactionCount().setScale(keepDecimalForCoin,BigDecimal.ROUND_HALF_UP));
				l.setKeepDecimalForCoin(keepDecimalForCoin);
				l.setKeepDecimalForCurrency(keepDecimalForCurrency);
			}
			l.setTransactionSum(l.getTransactionSum().setScale(2, BigDecimal.ROUND_HALF_DOWN));
		}
		return list;
	}
}

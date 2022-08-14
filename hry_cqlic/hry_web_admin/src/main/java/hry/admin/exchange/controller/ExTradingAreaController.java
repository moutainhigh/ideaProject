/**
 * Copyright:    
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-10-23 11:12:53 
 */
package hry.admin.exchange.controller;


import hry.admin.customer.model.AppCustomer;
import hry.admin.exchange.model.ExCointoCoin;
import hry.admin.exchange.model.ExTradingArea;
import hry.admin.exchange.service.ExCointoCoinService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
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
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-10-23 11:12:53 
 */
@Controller
@RequestMapping("/exchange/extradingarea")
public class ExTradingAreaController extends BaseController<ExTradingArea, Long> {
	
	@Resource(name = "exTradingAreaService")
	@Override
	public void setService(BaseService<ExTradingArea, Long> service) {
		super.service = service;
	}
	@Resource
	public ExCointoCoinService exCointoCoinService;

	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExTradingArea exTradingArea){
		ExTradingArea extradingQuery=new ExTradingArea();
		Long count = service.count(extradingQuery);
		if(count<4L){
			ExTradingArea queryRepeat=new ExTradingArea();
			queryRepeat.setTradingArea(exTradingArea.getTradingArea());
			Long query = service.count(queryRepeat);
			if(query==0L){
				return super.save(exTradingArea);
			}else{
				return new JsonResult().setSuccess(false).setMsg("交易区已存在");
			}

		}else{
			return new JsonResult().setSuccess(false).setMsg("最多只能创建4个交易区");
		}

	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExTradingArea exTradingArea = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/extradingareamodify");
		mav.addObject("model", exTradingArea);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExTradingArea exTradingArea){
		QueryFilter filter = new QueryFilter(ExTradingArea.class);
		filter.addFilter("tradingArea =", exTradingArea.getTradingArea());
		List<ExTradingArea> list = service.find(filter);
		if (list.size() > 0) {
			if( !exTradingArea.getId().equals(list.get(0).getId())){
				return new JsonResult().setSuccess(false).setMsg("交易区已存在");
			}
		}else{//假如交易区变为新的且系统中不存在，需要查询之前的交易区下是否有交易对
			ExTradingArea oldExTradingArea = service.get(exTradingArea.getId());
			ExCointoCoin exCointoCoin=new ExCointoCoin();
			exCointoCoin.setFixPriceCoinCode(oldExTradingArea.getTradingArea());
			Long count = exCointoCoinService.count(exCointoCoin);
			if(count>0L){
				return new JsonResult().setSuccess(false).setMsg("原交易区下有交易对存在，不能修改交易区");
			}
		}

		return super.update(exTradingArea);
	}



	@CommonLog(name = "交易区管理-开启交易区")
	@RequestMapping(value = "/openChange/{id}")
	@ResponseBody
	public JsonResult open(@PathVariable Long id) {
		JsonResult jsonResult = new JsonResult();
		ExTradingArea exTradingArea = service.get(id);
		if(exTradingArea.getStruts()==1){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("交易区已经开启，请不要重复开启");
			return jsonResult;
		}
		//查询交易区对
		QueryFilter filter = new QueryFilter(ExCointoCoin.class);
		filter.addFilter("fixPriceCoinCode =", exTradingArea.getTradingArea());
		filter.addFilter("state =", 0);
		List<ExCointoCoin> exCointoCoinList = exCointoCoinService.find(filter);
		for (ExCointoCoin exCointoCoin:exCointoCoinList) {
			exCointoCoin.setState(1);
			 exCointoCoinService.update(exCointoCoin);
			exCointoCoinService.initRedisCode();
			exCointoCoinService.newinitRedisCode();
			exCointoCoinService.openNewCointoCoin(exCointoCoin);
		}
		exTradingArea.setStruts(1);
		super.update(exTradingArea);
		jsonResult.setSuccess(true);
		jsonResult.setMsg("开启成功");
		return jsonResult;
	}
	@CommonLog(name = "交易区管理-关闭交易区")
	@RequestMapping(value = "/closeChange/{id}")
	@ResponseBody
	public JsonResult close(@PathVariable Long id) {
		JsonResult jsonResult = new JsonResult();
		ExTradingArea exTradingArea = service.get(id);
		if(exTradingArea.getStruts()==0){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("交易区已经关闭，请不要重复关闭");
			return jsonResult;
		}
			//查询交易区对
		QueryFilter filter = new QueryFilter(ExCointoCoin.class);
		filter.addFilter("fixPriceCoinCode =", exTradingArea.getTradingArea());
		filter.addFilter("state =", 1);
		List<ExCointoCoin> exCointoCoinList = exCointoCoinService.find(filter);
		for (ExCointoCoin exCointoCoin:exCointoCoinList) {
			exCointoCoin.setState(0);
			exCointoCoinService.update(exCointoCoin);
			exCointoCoinService.initRedisCode();
			exCointoCoinService.closeCointoCoin(exCointoCoin);
		}
		exTradingArea.setStruts(0);
		super.update(exTradingArea);
		jsonResult.setSuccess(true);
		jsonResult.setMsg("关闭成功");
		return jsonResult;
	}
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExTradingArea.class,request);
		filter.setOrderby("sort asc");
		return super.findPage(filter);
	}

	/**
	 * 通过语言查询类别
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryOpenArea")
	@ResponseBody
	public List<ExTradingArea> queryOpenArea(HttpServletRequest request) {
		QueryFilter filter = new QueryFilter(ExTradingArea.class);
		filter.addFilter("struts=",1);
		filter.setOrderby("sort asc");
		List<ExTradingArea> list = super.find(filter);
		return list;
	}



}

/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-14 15:22:23 
 */
package hry.admin.licqb.record.controller;


import hry.admin.customer.model.AppPersonInfo;
import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.licqb.platform.model.CustomerLevel;
import hry.admin.licqb.platform.model.UsdtTotal;
import hry.admin.licqb.record.service.DealRecordService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.licqb.record.model.DealRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-14 15:22:23 
 */
@Controller
@RequestMapping("/licqb/record/dealrecord")
public class DealRecordController extends BaseController<DealRecord, Long> {

	@Resource(name = "dealRecordService")
	@Override
	public void setService(BaseService<DealRecord, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		DealRecord dealRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/record/dealrecordsee");
		mav.addObject("model", dealRecord);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,DealRecord dealRecord){
		return super.save(dealRecord);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		DealRecord dealRecord = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/licqb/record/dealrecordmodify");
		mav.addObject("model", dealRecord);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,DealRecord dealRecord){
		return super.update(dealRecord);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(DealRecord.class,request);
		return super.findPage(filter);
	}
	

	/**
	 * 根据类型查看不同的奖励记录
	 * */
	@RequestMapping(value="/findDealRecordList")
	@ResponseBody
	public PageResult findDealRecordList(HttpServletRequest request) {
		QueryFilter filter = new QueryFilter(DealRecord.class,request);
		return ((DealRecordService)service).findDealRecordList(filter);
	}


	/*****~~~~~~~~~~~~~~~~~~~~取证分析~~~~~~~~~~~~~~~~~~~~~*****/
	@RequestMapping(value="/getUserTotal")
	@ResponseBody
	public PageResult getUserTotal(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(UsdtTotal.class,request);
		return ((DealRecordService)service).getUserTotal(filter);
	}

	@RequestMapping(value="/findAllUserInfo")
	@ResponseBody
	public PageResult findAllUserInfo(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppPersonInfo.class,request);
		return ((DealRecordService)service).findAllUserInfo(filter);
	}

	@RequestMapping(value="/findUserTeamInfo")
	@ResponseBody
	public PageResult findUserTeamInfo(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(CustomerLevel.class,request);
		return ((DealRecordService)service).findUserTeamInfo(filter);
	}

	@RequestMapping(value="/gotoChargeMoneyListFtl")
	@ResponseBody
	public ModelAndView gotoChargeMoneyFtl(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/admin/licqb/record/evidenceChargeMoneyList");
		// 交易充值总数
		QueryFilter filter = new QueryFilter(ExDmTransaction.class);
		Long chargeMoney = ((DealRecordService)service).getDealDmMoney(1);
		mav.addObject("chargeMoney", chargeMoney);
		return mav;
	}

	@RequestMapping(value="/gotoMakeMoneyListFtl")
	@ResponseBody
	public ModelAndView gotoMakeMoneyListFtl(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/admin/licqb/record/evidenceMakeMoneyList");
		// 交易充值总数
		QueryFilter filter = new QueryFilter(ExDmTransaction.class);
		Long makeMoney = ((DealRecordService)service).getDealDmMoney(2);
		mav.addObject("makeMoney", makeMoney);
		return mav;
	}


}

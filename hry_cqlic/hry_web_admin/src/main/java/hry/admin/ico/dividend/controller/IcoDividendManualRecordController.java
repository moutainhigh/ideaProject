/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-22 11:33:10 
 */
package hry.admin.ico.dividend.controller;


import hry.admin.ico.dividend.service.IcoDividendManualRecordService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.ico.remote.RemoteIcoRewardService;
import hry.util.QueryFilter;
import hry.admin.ico.dividend.model.IcoDividendManualRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.SpringUtil;
import hry.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-22 11:33:10 
 */
@Controller
@RequestMapping("/ico/dividend/icodividendmanualrecord")
public class IcoDividendManualRecordController extends BaseController<IcoDividendManualRecord, Long> {
	
	@Resource(name = "icoDividendManualRecordService")
	@Override
	public void setService(BaseService<IcoDividendManualRecord, Long> service) {
		super.service = service;
	}

	/**
	 * 后台分红操作
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/grantDividend")
	@ResponseBody
	public JsonResult grantDividend(HttpServletRequest request,String reason,
									String coinCode,String dividendNum,String levelSort){
		RemoteIcoRewardService remoteIcoRewardService= SpringUtil.getBean("remoteIcoRewardService");
		HashMap<String,String> hashMap=new HashMap<>();
		hashMap.put("reason",reason);//分红原因
		hashMap.put("coinCode",coinCode);//分红币种
		hashMap.put("dividendNum",dividendNum);//分红总量
		if(StringUtil.isNull(levelSort)){
			hashMap.put("levelSort",levelSort);//等级排序
		}
		//return new JsonResult().setSuccess(true).setMsg("成功");
		return  remoteIcoRewardService.grantDividend(hashMap);
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoDividendManualRecord.class,request);
		PageResult findPageBySql = ((IcoDividendManualRecordService) service).findPageBySql(filter);
		return findPageBySql;
	}
	

}

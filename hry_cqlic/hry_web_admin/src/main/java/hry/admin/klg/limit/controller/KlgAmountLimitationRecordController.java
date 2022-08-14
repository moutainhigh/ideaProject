/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-18 14:48:45 
 */
package hry.admin.klg.limit.controller;


import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.klg.limit.model.KlgAmountLimitationRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-18 14:48:45 
 */
@Controller
@RequestMapping("/klg/limit/klgamountlimitationrecord")
public class KlgAmountLimitationRecordController extends BaseController<KlgAmountLimitationRecord, Long> {
	
	@Resource(name = "klgAmountLimitationRecordService")
	@Override
	public void setService(BaseService<KlgAmountLimitationRecord, Long> service) {
		super.service = service;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request,String type, String operatorId,
						  String startTime,String endTime){
		QueryFilter filter = new QueryFilter(KlgAmountLimitationRecord.class,request);
		if(StringUtil.isNull(type)){
			filter.addFilter("type=",type);
		}if(StringUtil.isNull(operatorId)){
			filter.addFilter("operatorId=",operatorId);
		}
		if(StringUtil.isNull(startTime)){
			filter.addFilter("created>=",startTime);
		}
		if(StringUtil.isNull(endTime)){
			filter.addFilter("created<=",endTime);
		}
		filter.setOrderby("created desc");
		return super.findPage(filter);
	}
	
}

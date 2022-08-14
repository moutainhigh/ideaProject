/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 16:50:30 
 */
package hry.admin.ico.account.controller;


import hry.admin.ico.account.service.IcoTransactionRecordService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.account.model.IcoTransactionRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 16:50:30 
 */
@Controller
@RequestMapping("/ico/account/icotransactionrecord")
public class IcoTransactionRecordController extends BaseController<IcoTransactionRecord, Long> {
	
	@Resource(name = "icoTransactionRecordService")
	@Override
	public void setService(BaseService<IcoTransactionRecord, Long> service) {
		super.service = service;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoTransactionRecord.class,request);
		PageResult pageResult=((IcoTransactionRecordService)service).findPageBySql(filter);
		return pageResult;
	}

	@RequestMapping(value="/findByCustomer")
	@ResponseBody
	public PageResult findByCustomer(HttpServletRequest request, @RequestParam(required = true) String customerId, @RequestParam(required = true) String coinCode){
		QueryFilter filter = new QueryFilter(IcoTransactionRecord.class,request);
		PageResult pageResult=((IcoTransactionRecordService)service).findPageBySql(filter);
		return pageResult;
	}
}

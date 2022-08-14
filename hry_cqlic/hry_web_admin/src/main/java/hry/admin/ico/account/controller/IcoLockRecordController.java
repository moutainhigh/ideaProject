/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 19:47:55 
 */
package hry.admin.ico.account.controller;


import hry.admin.ico.account.service.IcoLockRecordService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.account.model.IcoLockRecord;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 19:47:55 
 */
@Controller
@RequestMapping("/ico/account/icolockrecord")
public class IcoLockRecordController extends BaseController<IcoLockRecord, Long> {
	
	@Resource(name = "icoLockRecordService")
	@Override
	public void setService(BaseService<IcoLockRecord, Long> service) {
		super.service = service;
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoLockRecord.class,request);
		PageResult pageResult=((IcoLockRecordService)service).findPageBySql(filter);
		return pageResult;
	}


}

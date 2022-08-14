/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-02-26 13:46:47 
 */
package hry.admin.ico.account.controller;


import hry.admin.ico.account.service.IcoTransferAccountsService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.account.model.IcoTransferAccounts;

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
 * @Date:        2019-02-26 13:46:47 
 */
@Controller
@RequestMapping("/ico/account/icotransferaccounts")
public class IcoTransferAccountsController extends BaseController<IcoTransferAccounts, Long> {
	
	@Resource(name = "icoTransferAccountsService")
	@Override
	public void setService(BaseService<IcoTransferAccounts, Long> service) {
		super.service = service;
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoTransferAccounts.class,request);
		PageResult pageResult=((IcoTransferAccountsService)service).findPageBySql(filter);
		return pageResult;
	}
	
	
	
	
}

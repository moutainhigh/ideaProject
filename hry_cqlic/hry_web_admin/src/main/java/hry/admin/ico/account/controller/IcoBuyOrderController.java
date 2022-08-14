/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 14:51:32 
 */
package hry.admin.ico.account.controller;


import hry.admin.ico.account.service.IcoBuyOrderService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.account.model.IcoBuyOrder;

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
 * @Date:        2019-01-14 14:51:32 
 */
@Controller
@RequestMapping("/ico/account/icobuyorder")
public class IcoBuyOrderController extends BaseController<IcoBuyOrder, Long> {
	
	@Resource(name = "icoBuyOrderService")
	@Override
	public void setService(BaseService<IcoBuyOrder, Long> service) {
		super.service = service;
	}


	//币币交易记录
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoBuyOrder.class,request);
		PageResult pageResult=((IcoBuyOrderService)service).findPageBySql(filter);
		return pageResult;
	}
	
	
	
	
}

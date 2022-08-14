/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 12:02:43 
 */
package hry.admin.ico.account.controller;


import hry.admin.ico.account.service.IcoAccountService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.account.model.IcoAccount;

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
 * @Date:        2019-01-14 12:02:43 
 */
@Controller
@RequestMapping("/ico/account/icoaccount")
public class IcoAccountController extends BaseController<IcoAccount, Long> {
	
	@Resource(name = "icoAccountService")
	@Override
	public void setService(BaseService<IcoAccount, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		IcoAccount icoAccount = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/account/icoaccountsee");
		mav.addObject("model", icoAccount);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,IcoAccount icoAccount){
		return super.save(icoAccount);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		IcoAccount icoAccount = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/account/icoaccountmodify");
		mav.addObject("model", icoAccount);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,IcoAccount icoAccount){
		return super.update(icoAccount);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoAccount.class,request);
		PageResult pageResult=((IcoAccountService)service).findPageBySql(filter);
		return pageResult;
	}
	
	
	
	
}

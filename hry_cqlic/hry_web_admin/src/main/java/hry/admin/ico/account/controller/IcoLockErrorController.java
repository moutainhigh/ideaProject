/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-05 10:03:27 
 */
package hry.admin.ico.account.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.admin.ico.account.model.IcoLockError;

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
 * @Date:        2019-03-05 10:03:27 
 */
@Controller
@RequestMapping("/ico/account/icolockerror")
public class IcoLockErrorController extends BaseController<IcoLockError, Long> {
	
	@Resource(name = "icoLockErrorService")
	@Override
	public void setService(BaseService<IcoLockError, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		IcoLockError icoLockError = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/account/icolockerrorsee");
		mav.addObject("model", icoLockError);
		return mav;
	}
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoLockError.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

/**
 * Copyright:    
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 14:48:05 
 */
package hry.admin.lend.controller;


import hry.admin.lend.service.ExLendAccountService;
import hry.bean.JsonResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.reward.model.page.FrontPage;
import hry.util.QueryFilter;
import hry.admin.lend.model.ExLendAccount;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 14:48:05 
 */
@Controller
@RequestMapping("/lend/exlendaccount")
public class ExLendAccountController extends BaseController<ExLendAccount, Long> {
	
	@Resource(name = "exLendAccountService")
	@Override
	public void setService(BaseService<ExLendAccount, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExLendAccount exLendAccount = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/lend/exlendaccountsee");
		mav.addObject("model", exLendAccount);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExLendAccount exLendAccount){
		return super.save(exLendAccount);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExLendAccount exLendAccount = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/lend/exlendaccountmodify");
		mav.addObject("model", exLendAccount);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExLendAccount exLendAccount){
		return super.update(exLendAccount);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public FrontPage list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExLendAccount.class,request);
		return ((ExLendAccountService)service).findPageByFilter(filter);
	}
	
	
	
	
}

/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-15 16:35:25 
 */
package hry.admin.klg.platform.controller;


import com.alibaba.fastjson.JSON;
import hry.admin.klg.platform.service.KlgPlatformAccountService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.klg.remote.RemoteKlgService;
import hry.util.QueryFilter;
import hry.admin.klg.platform.model.KlgPlatformAccount;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.SpringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-15 16:35:25 
 */
@Controller
@RequestMapping("/klg/platform/klgplatformaccount")
public class KlgPlatformAccountController extends BaseController<KlgPlatformAccount, Long> {

	//SME总帐户
	public  final  static String accountID="101";

	@Resource
	private MessageProducer messageProducer;

	@Resource(name = "klgPlatformAccountService")
	@Override
	public void setService(BaseService<KlgPlatformAccount, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlgPlatformAccount klgPlatformAccount = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/platform/klgplatformaccountsee");
		mav.addObject("model", klgPlatformAccount);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlgPlatformAccount klgPlatformAccount){
		return super.save(klgPlatformAccount);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlgPlatformAccount klgPlatformAccount = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/platform/klgplatformaccountmodify");
		mav.addObject("model", klgPlatformAccount);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgPlatformAccount klgPlatformAccount){
		return super.update(klgPlatformAccount);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgPlatformAccount.class,request);
		return super.findPage(filter);
	}
	/**
	 *
	 * @param account
	 * @param toAccount
	 * @param money
	 * @return
	 */
	@RequestMapping(value="/transfer")
	@ResponseBody
	public JsonResult transfer(@RequestParam(required = true) String account,
							   @RequestParam(required = true) String toAccount,
							   @RequestParam(required = true) String money){
		return  ((KlgPlatformAccountService)service).transfer(money,account,toAccount);
	}

	/**
	 * 充值
	 * @param account
	 * @param toAccount
	 * @param money
	 * @return
	 */
	@RequestMapping(value="/recharge")
	@ResponseBody
	public JsonResult recharge(@RequestParam(required = true) String account,
							   @RequestParam(required = true) String money){
		if(!account.equals(accountID)){
			return  new JsonResult().setSuccess(false).setMsg("此账户不可充值");
		}
		return  ((KlgPlatformAccountService)service).recharge(money,account);
	}


}

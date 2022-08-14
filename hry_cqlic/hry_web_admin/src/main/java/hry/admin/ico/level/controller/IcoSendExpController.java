/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-20 19:32:24 
 */
package hry.admin.ico.level.controller;


import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.ico.remote.RemoteIcoRewardService;
import hry.manage.remote.RemoteManageService;
import hry.util.QueryFilter;
import hry.admin.ico.level.model.IcoSendExp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.SpringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-20 19:32:24 
 */
@Controller
@RequestMapping("/ico/level/icosendexp")
public class IcoSendExpController extends BaseController<IcoSendExp, Long> {

	@Resource(name = "icoSendExpService")
	@Override
	public void setService(BaseService<IcoSendExp, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		IcoSendExp icoSendExp = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/level/icosendexpsee");
		mav.addObject("model", icoSendExp);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,IcoSendExp icoSendExp){
		return super.save(icoSendExp);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		IcoSendExp icoSendExp = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/level/icosendexpmodify");
		mav.addObject("model", icoSendExp);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,IcoSendExp icoSendExp){
		return super.update(icoSendExp);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoSendExp.class,request);
		return super.findPage(filter);
	}

	@RequestMapping(value="/sendExp")
	@ResponseBody
	public JsonResult sendExp(HttpServletRequest request){
		RemoteIcoRewardService remoteIcoRewardService= SpringUtil.getBean("remoteIcoRewardService");
		return remoteIcoRewardService.sendExp();
	}
	//临时修复数据
	@RequestMapping(value="/repairData")
	@ResponseBody
	public JsonResult repairData(HttpServletRequest request){
		RemoteIcoRewardService remoteIcoRewardService= SpringUtil.getBean("remoteIcoRewardService");
		return remoteIcoRewardService.repairData();
	}


	@RequestMapping(value="/send")
	@ResponseBody
	public JsonResult send(HttpServletRequest request,Long id){
		IcoSendExp icoSendExp = service.get(new QueryFilter(IcoSendExp.class).addFilter("id=",id)
					.addFilter("state=","0"));
		if(icoSendExp!=null){
			RemoteManageService remoteManageService= SpringUtil.getBean("remoteManageService");
			/*@param customerId  用户id
			 * @param account_type  交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除 0203月末扣除10%）
			 * @param experience  经验值（无正负）
			 * @param money 持币数（交易类型属于 0102时 传null）
			 * @param upgradeNote  备注
			 * @return*/
			Long customerId=Long.valueOf(icoSendExp.getCustomerId());
			Long  expr=icoSendExp.getExperience();
			if(expr<0){
				return new JsonResult().setSuccess(false).setMsg("数据错误");
			}
			remoteManageService.clearingExperience(customerId,"0101",expr,
					icoSendExp.getNumber(),"手动操作发送经验");
			icoSendExp.setState(1);
			service.update(icoSendExp);
			return  new JsonResult().setSuccess(true).setMsg("成功");
		}else{
			return  new JsonResult().setSuccess(false).setMsg("不存在Id");
		}
	}

}

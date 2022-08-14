/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:49:22 
 */
package hry.admin.commend.controller;


import hry.admin.commend.model.AppCommendMoney;
import hry.admin.commend.service.AppCommendMoneyService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:49:22 
 */
@Controller
@RequestMapping("/commend/appcommendmoney")
public class AppCommendMoneyController extends BaseController<AppCommendMoney, Long> {
	
	@Resource(name = "appCommendMoneyService")
	@Override
	public void setService(BaseService<AppCommendMoney, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppCommendMoney appCommendMoney = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommendmoneysee");
		mav.addObject("model", appCommendMoney);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppCommendMoney appCommendMoney){
		return super.save(appCommendMoney);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppCommendMoney appCommendMoney = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/commend/appcommendmoneymodify");
		mav.addObject("model", appCommendMoney);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppCommendMoney appCommendMoney){
		return super.update(appCommendMoney);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppCommendMoney.class,request);
		PageResult pageResult = ((AppCommendMoneyService)service).findPageBySql(filter);
		List<AppCommendMoney> appCommendMoneyList = pageResult.getRows();
		Iterator<AppCommendMoney> iterator =  appCommendMoneyList.iterator();

		while (iterator.hasNext()){
			AppCommendMoney appCommendMoney = iterator.next();
			if(appCommendMoney.getPaidMoney().compareTo(appCommendMoney.getMoneyNum()) ==0){
				iterator.remove();
			}
		}

		return pageResult;
	}


	//派发佣金
	@RequestMapping(value="/postMoney",method = RequestMethod.GET)
	@ResponseBody
	@CommonLog(name = "派发佣金")
	public JsonResult postMoney(HttpServletRequest req){
		String ids = req.getParameter("ids");
		Long id = Long.valueOf(ids);
		String fixPriceCoinCode = req.getParameter("fixPriceCoinCode");
		//  Integer money = Integer.valueOf(req.getParameter("money"));

		//如果佣金结算金额不一致，以最大值为准（暂时注掉）
		//BigDecimal count = commissionDeployService.getStandardMoney();

		AppCommendMoney appCommendMoney = service.get(id);

		AppCommendMoneyService appCommendMoneyService = (AppCommendMoneyService)service;

		JsonResult result = appCommendMoneyService.postMoneyById(id, appCommendMoney.getMoneyNum().subtract(appCommendMoney.getPaidMoney()));

		return result;

	}
	
	
	
}

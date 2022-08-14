/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-22 10:07:04 
 */
package hry.admin.exchange.controller;


import hry.admin.exchange.service.ExCointoMoneyService;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.admin.exchange.model.ExCointoMoney;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.sys.ContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Copyright:   互融云
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-22 10:07:04 
 */
@Controller
@RequestMapping("/exchange/excointomoney")
public class ExCointoMoneyController extends BaseController<ExCointoMoney, Long> {
	
	@Resource(name = "exCointoMoneyService")
	@Override
	public void setService(BaseService<ExCointoMoney, Long> service) {
		super.service = service;
	}

	@Autowired
	private RedisService redisService;
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		ExCointoMoney exCointoMoney = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/excointomoneysee");
		mav.addObject("model", exCointoMoney);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,ExCointoMoney exCointoMoney){
		JsonResult jsonResult = new JsonResult();
		BaseManageUser user = ContextUtil.getCurrentUser();
		exCointoMoney.setCreator(user.getUsername());

		if(service.count(new QueryFilter(ExCointoMoney.class).addFilter("lan=",exCointoMoney.getLan())) > 0){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("该语种已经存在汇率换算，请确认");
			return jsonResult;
		}
		jsonResult = super.save(exCointoMoney);
		//刷新redis
		((ExCointoMoneyService)service).initRedis();
		return jsonResult;
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		ExCointoMoney exCointoMoney = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/exchange/excointomoneymodify");
		mav.addObject("model", exCointoMoney);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,ExCointoMoney exCointoMoney){
		JsonResult jsonResult = super.update(exCointoMoney);
		//刷新redis
		((ExCointoMoneyService)service).initRedis();
		return jsonResult;
	}

	@RequestMapping(value="/setState/{id}")
	@ResponseBody
	public JsonResult setState(HttpServletRequest request,@PathVariable Long id){
		ExCointoMoney exCointoMoney = service.get(id);
		Integer state = Integer.valueOf(request.getParameter("state"));

		if(StringUtils.isEmpty(state)){
			return new JsonResult().setMsg("状态不能为空").setSuccess(false);
		}
		if(1 == state){
			QueryFilter queryFilter = new QueryFilter(ExCointoMoney.class);
			queryFilter.addFilter("lan=",exCointoMoney.getLan());
			queryFilter.addFilter("state=",1);
			List<ExCointoMoney> list = service.find(queryFilter);
			if(null != list && list.size()>0){
				return new JsonResult().setMsg("同一个语种的汇率只能开启一个").setSuccess(false);
			}
		}

		exCointoMoney.setState(state);
		JsonResult jsonResult = super.update(exCointoMoney);
		//刷新redis
		((ExCointoMoneyService)service).initRedis();
		return jsonResult;
	}


	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		JsonResult jsonResult = super.deleteBatch(ids);
		//刷新redis
		((ExCointoMoneyService)service).initRedis();
		return jsonResult;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(ExCointoMoney.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

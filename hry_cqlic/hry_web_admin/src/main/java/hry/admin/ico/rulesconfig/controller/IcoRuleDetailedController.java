/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-12 18:38:38 
 */
package hry.admin.ico.rulesconfig.controller;


import hry.admin.ico.rulesconfig.model.RulesConfig;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.admin.ico.rulesconfig.model.IcoRuleDetailed;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.SpringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-12 18:38:38 
 */
@Controller
@RequestMapping("/ico/rulesconfig/icoruledetailed")
public class IcoRuleDetailedController extends BaseController<IcoRuleDetailed, Long> {


	@Resource
	private MessageProducer messageProducer;

	@Resource(name = "icoRuleDetailedService")
	@Override
	public void setService(BaseService<IcoRuleDetailed, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		IcoRuleDetailed icoRuleDetailed = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/rulesconfig/icoruledetailedsee");
		mav.addObject("model", icoRuleDetailed);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,IcoRuleDetailed icoRuleDetailed){
		icoRuleDetailed.setTotalNum(icoRuleDetailed.getSaleNum());
		icoRuleDetailed.setSaleSurplusNum(icoRuleDetailed.getSaleNum());
		icoRuleDetailed.setState("0");
		return super.save(icoRuleDetailed);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		IcoRuleDetailed icoRuleDetailed = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/ico/rulesconfig/icoruledetailedmodify");
		mav.addObject("model", icoRuleDetailed);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,IcoRuleDetailed icoRuleDetailed){
		return super.update(icoRuleDetailed);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(IcoRuleDetailed.class,request);
		PageResult result =super.findPage(filter);
		return  result;
	}


	@RequestMapping(value="/issue")
	@ResponseBody
	public synchronized JsonResult issue(HttpServletRequest request){
		String ids = request.getParameter("id");
		QueryFilter filter = new QueryFilter(IcoRuleDetailed.class);
		filter.addFilter("id=", ids);
		filter.addFilter("state=", "0");
		IcoRuleDetailed icoRuleDetailed = service.get(filter);
		if(icoRuleDetailed!=null&&icoRuleDetailed.getState().equals("0")){
			RedisService redisService = (RedisService) SpringUtil.getBean("redisService");
			String str = redisService.get(RulesConfig.PLATFORM_NUMBER);//平台币剩余数量
			String circulation = redisService.get(RulesConfig.PLATFORM_CIRCULATION);//发行总数量
			if(circulation==null||circulation.equals("")){
				circulation = "0";
			}
			icoRuleDetailed.setState("1");//已发行
			service.update(icoRuleDetailed);
			messageProducer.toPlatformCurrency(icoRuleDetailed.getSaleSurplusNum().stripTrailingZeros().toPlainString());
			redisService.save(RulesConfig.PLATFORM_CIRCULATION, icoRuleDetailed.getSaleSurplusNum().add(new BigDecimal(circulation)).stripTrailingZeros().toPlainString());
			return new JsonResult().setSuccess(true).setMsg("操作成功");
		}else{
			return new JsonResult().setSuccess(false).setMsg("更新失败");
		}
	}
	
}

/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-16 16:55:42 
 */
package hry.admin.klg.limit.controller;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import hry.util.date.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hry.admin.klg.limit.model.KlgAmountLimitation;
import hry.admin.klg.limit.service.KlgAmountLimitationRecordService;
import hry.admin.klg.transaction.model.vo.KlgBuyTransactionVo;
import hry.admin.klg.transaction.service.KlgBuyTransactionService;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
/**
 * Copyright:   互融云
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-16 16:55:42 
 */
@Controller
@RequestMapping("/klg/limit/klgamountlimitation")
public class KlgAmountLimitationController extends BaseController<KlgAmountLimitation, Long> {

	private static  final  String endDate="23:59:59";

	@Resource
	private RedisService redisService;
	@Resource
	private KlgAmountLimitationRecordService limitationRecordService;
	@Resource
	private KlgBuyTransactionService klgBuyTransactionService;

	@Resource(name = "klgAmountLimitationService")
	@Override
	public void setService(BaseService<KlgAmountLimitation, Long> service) {
		super.service = service;
	}

	/**
	 *type{1,2,3} 抢单额度限制,新人购买额度,预约总额度限制
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/modifysee/{type}")
	public ModelAndView modifysee(@PathVariable Integer type){
		QueryFilter queryFilter=new QueryFilter(KlgAmountLimitation.class);
		queryFilter.addFilter("type=",type);
		KlgAmountLimitation klgAmountLimitation = service.get(queryFilter);
		ModelAndView mav = new ModelAndView("/admin/klg/limit/klgamountlimitationmodify"+type);
		if(type==3){//预约额度
			String starDate=klgAmountLimitation.getPmTime();//下午开盘时间
			if(starDate==null){
				starDate="12:00:00";
				klgAmountLimitation.setPmTime(starDate);
			}
			boolean flg=hry.util.DateUtil.isCheackTime(starDate,endDate);
			if(flg){//当前属于下午
				klgAmountLimitation.setMorningLimit(BigDecimal.ZERO);//上午为0
				klgAmountLimitation.setAfternoonLimit(klgAmountLimitation.getMoney());//下午剩余
			}else{//当前属于上午
				BigDecimal morningLimit=klgAmountLimitation.getMorningLimit();//上午额度
				BigDecimal sub=klgAmountLimitation.getTotalMoney().subtract(klgAmountLimitation.getMoney());//已卖出额度
				if(morningLimit.compareTo(sub)>=0){//上午额度 大于已售出额度
					klgAmountLimitation.setMorningLimit(morningLimit.subtract(sub));
				}else{
					klgAmountLimitation.setMorningLimit(BigDecimal.ZERO);
					klgAmountLimitation.setAfternoonLimit(klgAmountLimitation.getMoney());
				}
			}
			List<KlgBuyTransactionVo> list = klgBuyTransactionService.getForecastSum();
			mav.addObject("list", list);
		}
		mav.addObject("model", klgAmountLimitation);
		return mav;
	}

	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlgAmountLimitation klgAmountLimitation){
		BaseManageUser user=(BaseManageUser)request.getSession().getAttribute("user");
		Integer type=klgAmountLimitation.getType();
		if(type.intValue()==3){//预约
			BigDecimal v1= klgAmountLimitation.getMorningLimit();//上午额度
			BigDecimal v2=klgAmountLimitation.getAfternoonLimit();//下午额度
			BigDecimal total=v1.add(v2);
			klgAmountLimitation.setTotalMoney(total);//总额
			klgAmountLimitation.setMoney(total);
		}
		//先记录后操作
		limitationRecordService.saveLimitationRecord(user,klgAmountLimitation);
		JsonResult jsonResult= super.update(klgAmountLimitation);
		if(!jsonResult.getSuccess()){//限制额度
			throw new RuntimeException();
		}
		return jsonResult;
	}

	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(KlgAmountLimitation.class,request);
		return super.findPage(filter);
	}
	
	
	
	
}

/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:32:39 
 */
package hry.admin.klg.prizedraw.controller;


import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import hry.admin.klg.prizedraw.model.KlPrizedrawIssue;
import hry.admin.klg.prizedraw.service.KlPrizedrawIssueService;
import hry.admin.klg.transaction.model.KlgBuyTransaction;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;

/**
 * Copyright:   互融云
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:32:39 
 */
@Controller
@RequestMapping("/klg/prizedraw/klprizedrawissue")
public class KlPrizedrawIssueController extends BaseController<KlPrizedrawIssue, Long> {
	
	@Resource(name = "klPrizedrawIssueService")
	@Override
	public void setService(BaseService<KlPrizedrawIssue, Long> service) {
		super.service = service;
	}
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		KlPrizedrawIssue klPrizedrawIssue = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/prizedraw/klprizedrawissuesee");
		mav.addObject("model", klPrizedrawIssue);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,KlPrizedrawIssue klPrizedrawIssue) throws ParseException{
		
		//根据期数查询
		QueryFilter filter = new QueryFilter(KlPrizedrawIssue.class);
		filter.addFilter("issueNumber=", klPrizedrawIssue.getIssueNumber());
		KlPrizedrawIssue klPi = service.get(filter);
		if(klPi!=null){
			return new JsonResult().setSuccess(false).setMsg("大奖期数不能重复");
		}
		//判断开始时间是否是周一
		Calendar cal=Calendar.getInstance();
		cal.setTime(klPrizedrawIssue.getStartDate()); 
		int week=cal.get(Calendar.DAY_OF_WEEK)-1;
		if(week!=1){
			return new JsonResult().setSuccess(false).setMsg("开始时间必须为周一");
		}
		//判断当前日期是否已设置
		QueryFilter filterD = new QueryFilter(KlPrizedrawIssue.class);
		filterD.addFilter("startDate=", klPrizedrawIssue.getStartDate());
		KlPrizedrawIssue klP = service.get(filterD);
		if(klP!=null){
			return new JsonResult().setSuccess(false).setMsg("大奖开始不能重复");
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String dateString = df.format(klPrizedrawIssue.getStartDate());
		Date currentTime_1 = df.parse(dateString);
		//周一下单时间
		if(klPrizedrawIssue.getMondayNumber()!=null&&!klPrizedrawIssue.getMondayNumber().equals("")){
			klPrizedrawIssue.setMondayDate(currentTime_1);
		}
		//周2下单时间
		if(klPrizedrawIssue.getTuesdayNumber()!=null&&!klPrizedrawIssue.getTuesdayNumber().equals("")){
			Calendar c = Calendar.getInstance();
	        c.setTime(currentTime_1);
	        c.add(Calendar.DAY_OF_MONTH, 1);// +1天
			klPrizedrawIssue.setTuesdayDate(c.getTime());
		}
		//周3下单时间
		if(klPrizedrawIssue.getWednesdayNumber()!=null&&!klPrizedrawIssue.getWednesdayNumber().equals("")){
			Calendar c = Calendar.getInstance();
			c.setTime(currentTime_1);
			c.add(Calendar.DAY_OF_MONTH, 2);// +2天
			klPrizedrawIssue.setWednesdayDate(c.getTime());
		}
		//周4下单时间
		if(klPrizedrawIssue.getThursdayNumber()!=null&&!klPrizedrawIssue.getThursdayNumber().equals("")){
			Calendar c = Calendar.getInstance();
			c.setTime(currentTime_1);
			c.add(Calendar.DAY_OF_MONTH, 3);// +3天
			klPrizedrawIssue.setThursdayDate(c.getTime());
		}
		//周5下单时间
		if(klPrizedrawIssue.getFridayNumber()!=null&&!klPrizedrawIssue.getFridayNumber().equals("")){
			Calendar c = Calendar.getInstance();
			c.setTime(currentTime_1);
			c.add(Calendar.DAY_OF_MONTH, 4);// +3天
			klPrizedrawIssue.setFridayDate(c.getTime());
		}
		//周6下单时间
		if(klPrizedrawIssue.getSaturdayNumber()!=null&&!klPrizedrawIssue.getSaturdayNumber().equals("")){
			Calendar c = Calendar.getInstance();
			c.setTime(currentTime_1);
			c.add(Calendar.DAY_OF_MONTH, 5);// +3天
			klPrizedrawIssue.setSaturdayDate(c.getTime());
		}
		//周日下单时间
		if(klPrizedrawIssue.getSundayNumber()!=null&&!klPrizedrawIssue.getSundayNumber().equals("")){
			Calendar c = Calendar.getInstance();
			c.setTime(currentTime_1);
			c.add(Calendar.DAY_OF_MONTH, 6);// +3天
			klPrizedrawIssue.setSundayDate(c.getTime());
		}
		
		
		return super.save(klPrizedrawIssue);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		KlPrizedrawIssue klPrizedrawIssue = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/klg/prizedraw/klprizedrawissuemodify");
		mav.addObject("model", klPrizedrawIssue);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,KlPrizedrawIssue klPrizedrawIssue) throws ParseException{
		KlPrizedrawIssue klPrizedrawIssueOld = service.get(klPrizedrawIssue.getId());
		if(klPrizedrawIssueOld.getStartDate().before(new Date())){
			return new JsonResult().setSuccess(false).setMsg("本期大奖已开始,不能进行修改");
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String dateString = df.format(klPrizedrawIssueOld.getStartDate());
		Date currentTime_1 = df.parse(dateString);
		//周一下单时间
		if(klPrizedrawIssue.getMondayNumber()!=null&&!klPrizedrawIssue.getMondayNumber().equals("")){
			klPrizedrawIssue.setMondayDate(currentTime_1);
		}
		//周2下单时间
		if(klPrizedrawIssue.getTuesdayNumber()!=null&&!klPrizedrawIssue.getTuesdayNumber().equals("")){
			Calendar c = Calendar.getInstance();
	        c.setTime(currentTime_1);
	        c.add(Calendar.DAY_OF_MONTH, 1);// +1天
			klPrizedrawIssue.setTuesdayDate(c.getTime());
		}
		//周3下单时间
		if(klPrizedrawIssue.getWednesdayNumber()!=null&&!klPrizedrawIssue.getWednesdayNumber().equals("")){
			Calendar c = Calendar.getInstance();
			c.setTime(currentTime_1);
			c.add(Calendar.DAY_OF_MONTH, 2);// +2天
			klPrizedrawIssue.setWednesdayDate(c.getTime());
		}
		//周4下单时间
		if(klPrizedrawIssue.getThursdayNumber()!=null&&!klPrizedrawIssue.getThursdayNumber().equals("")){
			Calendar c = Calendar.getInstance();
			c.setTime(currentTime_1);
			c.add(Calendar.DAY_OF_MONTH, 3);// +3天
			klPrizedrawIssue.setThursdayDate(c.getTime());
		}
		//周5下单时间
		if(klPrizedrawIssue.getFridayNumber()!=null&&!klPrizedrawIssue.getFridayNumber().equals("")){
			Calendar c = Calendar.getInstance();
			c.setTime(currentTime_1);
			c.add(Calendar.DAY_OF_MONTH, 4);// +3天
			klPrizedrawIssue.setFridayDate(c.getTime());
		}
		//周6下单时间
		if(klPrizedrawIssue.getSaturdayNumber()!=null&&!klPrizedrawIssue.getSaturdayNumber().equals("")){
			Calendar c = Calendar.getInstance();
			c.setTime(currentTime_1);
			c.add(Calendar.DAY_OF_MONTH, 5);// +3天
			klPrizedrawIssue.setSaturdayDate(c.getTime());
		}
		//周日下单时间
		if(klPrizedrawIssue.getSundayNumber()!=null&&!klPrizedrawIssue.getSundayNumber().equals("")){
			Calendar c = Calendar.getInstance();
			c.setTime(currentTime_1);
			c.add(Calendar.DAY_OF_MONTH, 6);// +3天
			klPrizedrawIssue.setSundayDate(c.getTime());
		}
		return super.update(klPrizedrawIssue);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		/*QueryFilter filter = new QueryFilter(KlPrizedrawIssue.class,request);
		return super.findPage(filter);*/
		QueryFilter filter = new QueryFilter(KlgBuyTransaction.class, request);
        PageResult findPageBySql = ((KlPrizedrawIssueService) service).findPageBySql(filter);
        return findPageBySql;
	}
	
	
	
	
}

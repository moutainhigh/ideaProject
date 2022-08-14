/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:34:09 
 */
package hry.admin.c2c.controller;


import hry.admin.c2c.model.AppBusinessman;
import hry.admin.c2c.model.AppBusinessmanBank;
import hry.admin.c2c.model.C2cTransaction;
import hry.admin.c2c.service.AppBusinessmanService;
import hry.admin.c2c.service.C2cTransactionService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:34:09 
 */
@Controller
@RequestMapping("/c2c/appbusinessmanbank")
public class AppBusinessmanBankController extends BaseController<AppBusinessmanBank, Long> {
	
	@Resource(name = "appBusinessmanBankService")
	@Override
	public void setService(BaseService<AppBusinessmanBank, Long> service) {
		super.service = service;
	}

	@Resource
	AppBusinessmanService appBusinessmanService;
	@Resource
	private C2cTransactionService c2cTransactionService;
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppBusinessmanBank appBusinessmanBank = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/c2c/appbusinessmanbanksee");
		mav.addObject("model", appBusinessmanBank);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	@CommonLog(name = "交易商银行卡添加")
	public JsonResult add(HttpServletRequest request,AppBusinessmanBank appBusinessmanBank){
		QueryFilter queryFilter = new QueryFilter(AppBusinessmanBank.class);
		queryFilter.addFilter("businessmanId=",appBusinessmanBank.getBusinessmanId());
		List<AppBusinessmanBank> oldCard = service.find(queryFilter);
		if(oldCard!=null && oldCard.size()>0){
			return new JsonResult("一个交易商只能加一个银行卡").setSuccess(false);
		}
		return super.save(appBusinessmanBank);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppBusinessmanBank appBusinessmanBank = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/c2c/appbusinessmanbankmodify");
		mav.addObject("model", appBusinessmanBank);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppBusinessmanBank appBusinessmanBank){
		return super.update(appBusinessmanBank);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	@CommonLog(name = "删除交易商银行卡")
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppBusinessmanBank.class,request);
		PageResult page = super.findPage(filter);
		List<AppBusinessmanBank> rows = page.getRows();
		if(rows!=null&&rows.size()>0){
			for(AppBusinessmanBank bank : rows){
				if(bank.getBusinessmanId()!=null){
					AppBusinessman appBusinessman = appBusinessmanService.get(bank.getBusinessmanId());
					if(appBusinessman!=null){
						bank.setBusinessName(appBusinessman.getTrueName());
					}
				}
			}
		}

		return page;
	}

	/**
	 * 查询银行卡或交易商是否已经产生数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/getC2cList")
	@ResponseBody
	public List<C2cTransaction> getC2cList(HttpServletRequest request){
		return c2cTransactionService.getC2cList(request);
	}
	
}

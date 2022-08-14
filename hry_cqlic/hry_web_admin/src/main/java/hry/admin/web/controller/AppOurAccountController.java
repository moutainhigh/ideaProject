/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-13 18:38:15 
 */
package hry.admin.web.controller;


import hry.admin.exchange.model.AppTransaction;
import hry.admin.exchange.service.AppTransactionService;
import hry.admin.web.model.AppOurAccount;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-13 18:38:15 
 */
@Controller
@RequestMapping("/web/appouraccount")
public class AppOurAccountController extends BaseController<AppOurAccount, Long> {
	
	@Resource(name = "appOurAccountService")
	@Override
	public void setService(BaseService<AppOurAccount, Long> service) {
		super.service = service;
	}

	@Resource
	private AppTransactionService appTransactionService;
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppOurAccount appOurAccount = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appouraccountsee");
		mav.addObject("model", appOurAccount);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppOurAccount appOurAccount){
		appOurAccount.setWebsite("1");
		appOurAccount.setCurrencyType("cny");
		return super.save(appOurAccount);
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppOurAccount appOurAccount = service.get(id);
		ModelAndView mav = new ModelAndView("/admin/web/appouraccountmodify");
		mav.addObject("model", appOurAccount);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppOurAccount appOurAccount){
		return super.update(appOurAccount);
	}
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	@CommonLog(name = "充值提现-我方账户删除")
	public JsonResult remove(String ids){
		return super.deleteBatch(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		String coinCode = request.getParameter("coinCode");
		String accountType = request.getParameter("accountType");
		QueryFilter filter = new QueryFilter(AppOurAccount.class, request);
		if(null != coinCode){
			filter.addFilter("currencyType=", coinCode);
		}

		if(null != accountType){
			if(2 == Integer.valueOf(accountType)){
				filter.addFilter("accountType_in", "0,2");
			}
		}

		String ico=request.getParameter("ico");
		if("1".equals(ico)){
			filter.addFilter("openAccountType_in", "2,3");
		}else{
			filter.addFilter("openAccountType_notin", "2,3");
		}
		PageResult page = super.findPage(filter);
		//充值-提现
		AppOurAccount appOurAccount = null;
		List<AppOurAccount> listappOurAccount = (List<AppOurAccount>) page.getRows();
		if(listappOurAccount.size()!=0){
			for(int j=0;j<listappOurAccount.size();j++){
				if(listappOurAccount.get(j).getIsShow()==1){
					appOurAccount = listappOurAccount.get(j);
				}
			}

			if(appOurAccount!=null){
				QueryFilter qf = new QueryFilter(AppTransaction.class);
				qf.addFilter("transactionType_in", "3,4");
				qf.addFilter("status=", "2");
				qf.addFilter("ourAccountNumber=", appOurAccount.getAccountNumber());
				List<AppTransaction> listtr = appTransactionService.find(qf);

				BigDecimal add = new BigDecimal(0);
				BigDecimal subtract = new BigDecimal(0);
				for(int i=0;i<listtr.size();i++){
					if(listtr.get(i).getTransactionType()==3){
						add = add.add(listtr.get(i).getTransactionMoney());
					}
					if(listtr.get(i).getTransactionType()==4){
						subtract = subtract.add(listtr.get(i).getTransactionMoney());
					}
				}
				BigDecimal sum = add.subtract(subtract);
				appOurAccount.setAccountMoney(sum);
				super.update(appOurAccount);
			}
		}
		return page;
	}
	
	
	
	
}

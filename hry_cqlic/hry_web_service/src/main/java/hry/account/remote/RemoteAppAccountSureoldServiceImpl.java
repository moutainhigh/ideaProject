/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年4月1日 上午11:18:14
 */
package hry.account.remote;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppAccountSureold;
import hry.account.fund.service.AppAccountService;
import hry.account.fund.service.AppAccountSureoldService;
import hry.account.fund.service.AppColdAccountRecordService;
import hry.account.fund.service.AppHotAccountRecordService;
import hry.bean.JsonResult;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.customer.user.model.AppCustomer;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.rpc.RpcContext;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年4月1日 上午11:18:14 
 */
public class RemoteAppAccountSureoldServiceImpl implements  RemoteAppAccountSureoldService {
	
	
	@Resource
	private AppAccountSureoldService  appAccountSureoldService;

	public List<AppAccountSureold> getBycustomerId(Long customerId,
			String userName,String currencyType, String website) {
		QueryFilter filter=new QueryFilter(AppAccountSureold.class);
		filter.addFilter("customerId=", customerId);
		filter.addFilter("userName=", userName);
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("website=", website);
		
		return appAccountSureoldService.find(filter);
	}

	@Override
	public AppAccountSureold getBycustomerIdAndcoinCode(Long customerId,
			String userName,String coinCode, String currencyType, String website) {
		QueryFilter filter=new QueryFilter(AppAccountSureold.class);
		filter.addFilter("customerId=", customerId);
		filter.addFilter("userName=", userName);
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("website=", website);
		filter.addFilter("coinCode=", coinCode);
		return appAccountSureoldService.get(filter);
	}

	@Override
	public void updateAccount(AppAccountSureold appAccountSureold) {
		appAccountSureoldService.update(appAccountSureold);
		
	}
	@Override
	public void saveAccount(AppAccountSureold appAccountSureold) {
		appAccountSureoldService.save(appAccountSureold);
		
	}
	
	

}

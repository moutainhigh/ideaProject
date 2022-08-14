/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.exchange.remote.lend;

import hry.account.fund.model.AppAccount;
import hry.account.remote.RemoteAppAccountService;
import hry.change.remote.lend.RemoteExDmLendService;
import hry.core.constant.CodeConstant;
import hry.bean.PageResult;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.message.MessageConstant;
import hry.util.message.MessageUtil;
import hry.util.sys.ContextUtil;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmLendIntent;
import hry.exchange.lend.service.ExDmLendIntentService;
import hry.exchange.lend.service.ExDmLendService;
import hry.exchange.lend.service.ExDmPingService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.rpc.RpcContext;


/**
 * 
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月23日 下午6:44:50
 */

public class RemoteExDmLendServiceImpl implements RemoteExDmLendService {
	@Resource
	private ExDmPingService exDmPingService;
	@Resource
	private ExDmLendService exDmLendService;
	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;

	@Resource
	private ExDmLendIntentService exDmLendIntentService;
	@Override
	public String[] saveExDmLend(ExDmLend exDmLend) {
		return exDmLendService.saveExDmLend(exDmLend);
		
	}
	
	public String[] repayPrincipal(Long lendId,BigDecimal repayCount) {
		String[] relt = new String[2];
	   ExDmLend exDmLend=exDmLendService.get(lendId);
	   exDmLend.setRepayLendCount(exDmLend.getRepayLendCount().add(repayCount));
	   if(exDmLend.getLendCount().equals(exDmLend.getRepayLendCount())){
		   exDmLend.setStatus(2);
	   }else{
		   exDmLend.setStatus(1);
	   }
	   exDmLendService.update(exDmLend);
	   return relt;
	   
		
	}
	@Override
	public BigDecimal netAsseToLend(Long customerId,String currencyType,String website) {
		// TODO Auto-generated method stub
		return exDigitalmoneyAccountService.netAsseToLend(customerId,currencyType,website);
	}

	@Override
	public PageResult listPage(RemoteQueryFilter filter) {
		return exDmLendService.listPage(filter);
	}

	@Override
	public String repaymentInfo(Long id) {
		ExDmLend exDmLend=exDmLendService.get(id);
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		RpcContext.getContext().setAttachment("saasId",exDmLend.getSaasId());
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(exDmLend.getCustomerId(), exDmLend.getCurrencyType(),exDmLend.getWebsite());
		
		StringBuffer sb=new StringBuffer("{\"LendCount\":"+exDmLend.getLendCount()); //借款总金额
		sb.append(",\"notRepayLendCount\":"+exDmLend.getLendCount().subtract(exDmLend.getRepayLendCount()));
		sb.append(",\"balance\":"+appAccount.getHotMoney());
		sb.append(",\"notInterest\":"+exDmLend.getInterestCount().subtract(exDmLend.getRepayInterestCount())+"}");
		return sb.toString();
	}

	@Override
	public String[] repayment(Long id, String type,BigDecimal repaymentMoney) {
		return exDmLendService.repayment(id, type, repaymentMoney);
		
	}

	@Override
	public PageResult listIntentPage(RemoteQueryFilter filter) {
		return null;
		/*
		// TODO Auto-generated method stub
		 return exDmLendIntentService.listIntentPage(filter);
	*/}

	@Override
	public Boolean isPinging(Long customerId,String userCode,String currencyType,String website) {
		// TODO Auto-generated method stub
		return exDmPingService.isPinging(customerId,userCode,currencyType,website);
	}

	
	
	@Override
	public List<ExDmLend> list(Long customerId, String userCode,
			String currencyType, String website,String  lendCoin) {
		QueryFilter  filter=new QueryFilter(ExDmLend.class);
		if(null!=customerId&&!"".equals(customerId)){
			filter.addFilter("customerId=", customerId);
		}
		
		filter.addFilter("currencyType=", currencyType);
		filter.addFilter("website=", website);
		if(null!=lendCoin&&!"".equals(lendCoin)){
			filter.addFilter("lendCoin=", lendCoin);
		}
		return exDmLendService.find(filter);
	}
	@Override
	public List<ExDmLend> find(RemoteQueryFilter remoteQueryFilter) {
		return exDmLendService.find(remoteQueryFilter.getQueryFilter());
	}

	@Override
	public List<ExDmLendIntent> findintent(RemoteQueryFilter remoteQueryFilter) {
		return exDmLendIntentService.find(remoteQueryFilter.getQueryFilter());
	}


}

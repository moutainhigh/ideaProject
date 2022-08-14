/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年4月1日 上午11:18:14
 */
package hry.account.remote;

import com.alibaba.dubbo.rpc.RpcContext;
import hry.account.fund.model.AppAccount;
import hry.account.fund.model.vo.AppAccountVo;
import hry.account.fund.service.AppAccountService;
import hry.account.fund.service.AppColdAccountRecordService;
import hry.account.fund.service.AppHotAccountRecordService;
import hry.bean.JsonResult;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.redis.common.dao.RedisUtil;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年4月1日 上午11:18:14 
 */
public class RemoteAppAccountServiceImpl implements  RemoteAppAccountService {
	
	private final Logger log = Logger.getLogger(RemoteAppAccountServiceImpl.class);
	
	@Resource
	private AppAccountService  appAccountService;
	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	
	@Resource
	private AppColdAccountRecordService  appColdAccountRecordService;
	@Resource
	private AppHotAccountRecordService  appHotAccountRecordService;
	@Override
	public JsonResult openAccount(AppCustomer appCustomer,AppPersonInfo appPersonInfo,String currencyType,String website) {
		
		JsonResult jr = new JsonResult();
		
		QueryFilter filter = new QueryFilter(AppAccount.class);
		filter.addFilter("customerId=", appCustomer.getId());
		filter.addFilter("website=", website);
		filter.addFilter("currencyType=", currencyType);
		AppAccount _appAccount = appAccountService.get(filter);
		// 判断账户没有开通过人民币账户
		if (_appAccount != null) {
			jr.setSuccess(true);
			jr.setMsg("账户存在");
			return jr;
		} else {
			AppAccount account = new AppAccount();
			account.setWebsite(website); // 设置站点
			account.setCurrencyType(currencyType); // 设置账户类型
			account.setCustomerId(appCustomer.getId());
			account.setUserName(appCustomer.getUserName());
			// 88代表cny银行账户账号
			if (ContextUtil.CN.equals(website)) {
				account.setAccountNum(IdGenerate.accountNum(appCustomer.getId().intValue(), "88"));
			} else {// 99代表en银行账户账号
				account.setAccountNum(IdGenerate.accountNum(appCustomer.getId().intValue(), "99"));
			}
			account.setSaasId(appCustomer.getSaasId());
			appAccountService.save(account);
			
			log.info("初始化账户缓存---------->>>:"+account.getId().toString());
			RedisUtil<AppAccount> redisUtil = new RedisUtil<AppAccount>(AppAccount.class);
			redisUtil.put(account, account.getId().toString());
			
			jr.setMsg("开通成功");
			jr.setSuccess(true);
			return jr;
		}

	
	}

	@Override
	public List<AppAccount> findByCustomerId(Long customerId) {
		
		String saasId = RpcContext.getContext().getAttachment("saasId");
		QueryFilter filter = new QueryFilter(AppAccount.class);
		filter.setSaasId(saasId);
		filter.addFilter("customerId=", customerId);
		//filter.addFilter("website=", ContextUtil.getRemoteWebsite());
		return appAccountService.find(filter);
		
	}

	@Override
	public AppAccount getByCustomerIdAndType(Long customerId,String currencyType,String website) {
		String saasId = RpcContext.getContext().getAttachment("saasId");
		if(null==saasId){
			 saasId = PropertiesUtils.APP.getProperty("app.saasId");
		}
		QueryFilter filter = new QueryFilter(AppAccount.class);
		filter.setSaasId(saasId);
		filter.addFilter("customerId=", customerId);
		if(!StringUtils.isEmpty(currencyType)){
			filter.addFilter("currencyType=", currencyType);
		}
		if(!StringUtils.isEmpty(website)){
			filter.addFilter("website=", website);
		}
	
		
		AppAccount account = appAccountService.get(filter);
		return account;
	}

	@Override
	public void update(AppAccount appAccount) {
		appAccountService.update(appAccount);
	}

	@Override
	public String[] freezeAccountSelf(Long id,
			BigDecimal freezeMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		
		return appAccountService.freezeAccountSelf(id, freezeMoney, transactionNum, remark,isculAppAccount,isImmediatelySaveRecord);
	}

	@Override
	public String[] unfreezeAccountSelf(Long id,
			BigDecimal unfreezeMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		return appAccountService.unfreezeAccountSelf(id, unfreezeMoney, transactionNum, remark,isculAppAccount,isImmediatelySaveRecord);
	}

	@Override
	public String[] unfreezeAccountThem(Long id,
			BigDecimal unfreezeMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		return appAccountService.unfreezeAccountThem(id, unfreezeMoney, transactionNum, remark,isculAppAccount,isImmediatelySaveRecord);
	}

	@Override
	public String[] inComeToHotAccount(Long id,
			BigDecimal incomeMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		return appAccountService.inComeToHotAccount(id, incomeMoney, transactionNum, remark,isculAppAccount,isImmediatelySaveRecord);
	}

	@Override
	public String[] payFromHotAccount(Long id,
			BigDecimal payMoney, String transactionNum, String remark,Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		return appAccountService.payFromHotAccount(id, payMoney, transactionNum, remark,isculAppAccount,isImmediatelySaveRecord);
	}

	@Override
	public String[] updateAccount(AppAccount appAccount) {
		return appAccountService.updateAccount(appAccount);
	}

	public Boolean postCommission(BigDecimal money, String agentName,
			String website, String currencyType, String operator,String fixPriceCoinCode) {
		if(fixPriceCoinCode.equals("CNY")){
		appAccountService.saveMoney(money,agentName,website,currencyType,operator);
		}else{
			exDigitalmoneyAccountService.saveMoney(money,agentName,website,currencyType,operator,fixPriceCoinCode);
		}
		return true;
	}

	
	/**
	 * 
	 * 根据用户的id 查询用户的账户表
	 * 
	 */
	@Override
	public AppAccount findById(Long id){
		return appAccountService.get(id);
	}


	@Override
	public String[] payFromHotAccountNegative(Long id, BigDecimal payMoney,
			String transactionNum, String remark, Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		// TODO Auto-generated method stub
		return appAccountService.payFromHotAccountNegative(id, payMoney, transactionNum, remark, isculAppAccount,isImmediatelySaveRecord);
	}


	@Override
	public String[] unfreezeAccountSelfCancelExEntrust(Long id,
			BigDecimal unfreezeMoney, String transactionNum, String remark,
			Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		return appAccountService.unfreezeAccountSelfCancelExEntrust(id, unfreezeMoney, transactionNum, remark, isculAppAccount,isImmediatelySaveRecord);
	}

	
	@Override
	public void removeHotRecord(Long id) {
		// TODO Auto-generated method stub
		 appColdAccountRecordService.delete(id);
	}

	@Override
	public void removeColdRecord(Long id) {
		 appColdAccountRecordService.delete(id);
	}

	
	@Override
	public String[] unfreezeAccountThemBuyTranstion(Long id,
			BigDecimal unfreezeMoney, String transactionNum, String remark,
			Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		// TODO Auto-generated method stub
		return appAccountService.unfreezeAccountThemBuyTranstion(id, unfreezeMoney, transactionNum, remark, isculAppAccount,isImmediatelySaveRecord);
	}

	@Override
	public AppAccount get(RemoteQueryFilter filter) {
		return appAccountService.get(filter.getQueryFilter());
	}

	@Override
	public void save(AppAccount account) {
		appAccountService.save(account); 
	}
	
	
	@Override
	public AppAccountVo findAccountByUserName(String userName,String website){
		AppAccountVo accountVo = appAccountService.findAccountByUserName(userName,website);
		return accountVo;
	}


	@Override
	public String[] unfreezeAccountSelfLendMoney(Long id,
			BigDecimal unfreezeMoney, String transactionNum, String remark,
			Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		// TODO Auto-generated method stub
		return appAccountService.unfreezeAccountSelfLendMoney(id, unfreezeMoney, transactionNum, remark, isculAppAccount,isImmediatelySaveRecord);
	}

	@Override
	public String[] LendMoneyToHotMoneyAccountSelf(Long id,
			BigDecimal unfreezeMoney, String transactionNum, String remark,
			Integer isculAppAccount,Integer isImmediatelySaveRecord) {
		// TODO Auto-generated method stub
		return appAccountService.LendMoneyToHotMoneyAccountSelf(id, unfreezeMoney, transactionNum, remark, isculAppAccount,isImmediatelySaveRecord);
	}

	@Override
	public AppAccount get(Long accountId) {
		// TODO Auto-generated method stub
		return appAccountService.get(accountId);
	}

	@Override
	public Boolean postCommissionNew(BigDecimal money, Long custromerId, String fixPriceCoinCode,Integer fixPriceType) {
			if(fixPriceType.equals("0")){
			//appAccountService.saveMoney(money,custromerId,fixPriceCoinCode);
			}else{
			//	appAccountService.saveMoneyCoin(money,custromerId,fixPriceCoinCode);
			}
			return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}

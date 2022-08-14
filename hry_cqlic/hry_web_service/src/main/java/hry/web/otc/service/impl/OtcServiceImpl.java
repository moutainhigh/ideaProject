package hry.web.otc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppBankCard;
import hry.account.fund.service.AppAccountService;
import hry.account.fund.service.AppBankCardService;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.model.ExProductParameter;
import hry.exchange.product.service.ExProductParameterService;
import hry.exchange.product.service.ExProductService;
import hry.manage.remote.model.base.FrontPage;
import hry.mq.producer.service.MessageProducer;
import hry.trade.redis.model.Accountadd;
import hry.util.QueryFilter;

/**
 * OTC与交易所之间的调用
 * OtcServiceImpl.java
 * @author denghf
 * 2018年6月25日 下午5:49:22
 */
@Service
public class OtcServiceImpl{

	@Resource
	private ExProductService exProductService;
	@Resource
	private ExProductParameterService exProductParameterService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	private AppCustomerService appCustomerService;
	@Resource
	private AppBankCardService appBankCardService;
	@Resource
	private AppAccountService appAccountService;
	@Resource
	private AppPersonInfoService appPersonInfoService;
	
	public ExProduct getExProductByCoinCode(String coinCode){
		ExProduct exProduct = exProductService.get(new QueryFilter(ExProduct.class).addFilter("coinCode=", coinCode));
		return exProduct;
	}
	
	public ExProductParameter getExProductParameterByCoinCode(String coinCode){
		ExProductParameter exProductParameter = exProductParameterService.get(new QueryFilter(ExProductParameter.class).addFilter("coinCode=", coinCode));
		return exProductParameter;
	}
	
	public ExDigitalmoneyAccount getCoinAccountByIdAndCoinCode(Long id, String coinCode){
		QueryFilter qf = new QueryFilter(ExDigitalmoneyAccount.class);
		qf.addFilter("customerId=", id);
		qf.addFilter("coinCode=", coinCode);
		ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(qf);
		if(exDigitalmoneyAccount!=null){
			return exDigitalmoneyAccount;
		}
		return null;
	}
	
	public void publish(Long accountId, BigDecimal money, Integer monteyType, Integer acccountType, String transactionNum, Integer remarks){
		Accountadd accountadd = new Accountadd();
		accountadd.setAccountId(accountId);
		accountadd.setMoney(money);
		accountadd.setMonteyType(monteyType);
		accountadd.setAcccountType(acccountType);
		accountadd.setRemarks(remarks);
		if(StringUtil.isNotEmpty(transactionNum)){
			accountadd.setTransactionNum(transactionNum);
		}
		List<Accountadd> list = new ArrayList<Accountadd>();
		list.add(accountadd);
		messageProducer.toAccount(JSON.toJSONString(list));
	}
	
	public AppCustomer getAppCustomerById(Long id){
		AppCustomer appCustomer = appCustomerService.get(id);
		return appCustomer;
	}
	
	public void updateAppCustomer(AppCustomer appCustomer){
		appCustomerService.update(appCustomer);
	}

	public void updateNickName(String nName, Long id){
		AppCustomer appCustomer = getAppCustomerById(id);
		if(appCustomer != null){
			appCustomer.setNickNameOtc(nName);
			appCustomerService.update(appCustomer);
		}
	}
	
	
	public FrontPage findPageBySql1(Map<String, String> map) {
		return appBankCardService.findPageBySql1(map);
	}

	public List<AppBankCard> getPersonalAsset(Long customerId, String type, String isDefault) {
		return appBankCardService.getPersonalAsset(customerId, type, isDefault);
	}
	
	public void updateIsDefault(Map<String, String> map) {
		appBankCardService.updateIsDefault(map);
	}
	
	public AppBankCard selectByParameter(Long customerId, int type, int isDefault){
		QueryFilter pFilter = new QueryFilter(AppBankCard.class);
		pFilter.addFilter("customerId=", customerId);
		pFilter.addFilter("type=", type);
		pFilter.addFilter("isDefault=", isDefault);
		AppBankCard appBankCard = appBankCardService.get(pFilter);
		return appBankCard;
	}
	
	public AppBankCard getById(Long id){
		AppBankCard appBankCard = appBankCardService.get(id);
		return appBankCard;
	}
	
	public void save(AppBankCard appBankCard){
		AppAccount appAccount = appAccountService.get(new QueryFilter(AppAccount.class).addFilter("customerId=", appBankCard.getCustomerId()));
		if(appAccount!=null){
			appBankCard.setAccountId(appAccount.getId());
		}
		appBankCardService.save(appBankCard);
	}
	
	public void delete(Long id){
		appBankCardService.delete(id);
	}
	
	public void update(AppBankCard appBankCard){
		appBankCardService.update(appBankCard);
	}
	
	public AppBankCard isBankCard(String type, Long id){
		QueryFilter qf = new QueryFilter(AppBankCard.class);
		qf.addFilter("customerId=", id);
		qf.addFilter("type=", type);
		AppBankCard appBankCard = appBankCardService.get(qf);
		return appBankCard;
	}
	
	public AppPersonInfo getAppPersonInfoByUserId(Long userId){
		AppPersonInfo appPersonInfo = appPersonInfoService.get(new QueryFilter(AppPersonInfo.class).addFilter("customerId=", userId));
		return appPersonInfo;
	}

	public void updateIsDeleteById (Long id) {
		appBankCardService.updateIsDeleteById(id);
	}
}

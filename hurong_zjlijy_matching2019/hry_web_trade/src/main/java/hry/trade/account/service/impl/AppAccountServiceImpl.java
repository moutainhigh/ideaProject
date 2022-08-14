/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.trade.account.service.impl;

import com.alibaba.fastjson.JSON;
import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppColdAccountRecord;
import hry.account.fund.model.AppHotAccountRecord;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.trade.account.service.AppAccountService;
import hry.trade.account.service.AppColdAccountRecordService;
import hry.trade.account.service.AppHotAccountRecordService;
import hry.trade.model.AccountRemarkEnum;
import hry.trade.model.TradeRedis;
import hry.trade.redis.model.AppAccountRedis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei
 * @Date :          2016年3月31日 下午6:52:11
 */
@Service("appAccountService")
public class AppAccountServiceImpl extends BaseServiceImpl<AppAccount, Long> implements AppAccountService{

	@Resource(name="appAccountDao")
	@Override
	public void setDao(BaseDao<AppAccount, Long> dao) {
		super.dao = dao;
	}
	@Resource
	private AppColdAccountRecordService  appColdAccountRecordService;
	@Resource
	private AppHotAccountRecordService  appHotAccountRecordService;



	@Override
	public AppColdAccountRecord createColdRecord(Integer recordType,AppAccount account, BigDecimal freezeMoney, BigDecimal balanceMoney,String transactionNum,String orderNum,Integer remark){
		//资金池5(完成)，托管0(处理中)，将来通过配置文件来获取判断
		Integer status=5;

		AppColdAccountRecord coldAccountRecord=new AppColdAccountRecord();
		coldAccountRecord.setAccountId(account.getId());
		coldAccountRecord.setWebsite(account.getWebsite());
		coldAccountRecord.setCurrencyType(account.getCurrencyType());
		coldAccountRecord.setCustomerId(account.getCustomerId());
		coldAccountRecord.setSaasId(account.getSaasId());
		coldAccountRecord.setUserName(account.getUserName());
		coldAccountRecord.setTrueName(account.getTrueName());
		coldAccountRecord.setTransactionNum(transactionNum);
		coldAccountRecord.setRecordType(recordType);
		coldAccountRecord.setStatus(status);
		coldAccountRecord.setTransactionMoney(freezeMoney);
		coldAccountRecord.setRemark(AccountRemarkEnum.getName(remark));
		coldAccountRecord.setBalanceMoney(balanceMoney);
		coldAccountRecord.setOrderNum(orderNum);
		return coldAccountRecord;

	}
	@Override
	public AppHotAccountRecord createHotRecord(Integer recordType,AppAccount account, BigDecimal freezeMoney, BigDecimal balanceMoney,String transactionNum,String orderNum,Integer remark){
		//资金池5(完成)，托管0(处理中)将来通过配置文件来获取判断

		Integer status=5;
		AppHotAccountRecord hotAccountRecord=new AppHotAccountRecord();
		hotAccountRecord.setAccountId(account.getId());
		hotAccountRecord.setWebsite(account.getWebsite());
		hotAccountRecord.setCurrencyType(account.getCurrencyType());
		hotAccountRecord.setCustomerId(account.getCustomerId());
		hotAccountRecord.setSaasId(account.getSaasId());
		hotAccountRecord.setUserName(account.getUserName());
		hotAccountRecord.setTrueName(account.getTrueName());
		hotAccountRecord.setTransactionNum(transactionNum);
		hotAccountRecord.setRecordType(recordType);
		hotAccountRecord.setStatus(status);
		hotAccountRecord.setTransactionMoney(freezeMoney);
		hotAccountRecord.setRemark(AccountRemarkEnum.getName(remark));
		hotAccountRecord.setBalanceMoney(balanceMoney);
		hotAccountRecord.setOrderNum(orderNum);
		return hotAccountRecord;

	}



	@Override
	public AppAccountRedis getAppAccountByRedis(String id) {
		Object obj=TradeRedis.redisUtilAppAccount.get(id);
		if(null==obj){
			// 去数据拿
			AppAccount account =this.get(Long.valueOf(id));
			AppAccountRedis accountr=(AppAccountRedis)JSON.parseObject(JSON.toJSONString( account),AppAccountRedis.class);
			return accountr;
		}else{
			AppAccountRedis accountr=(AppAccountRedis)obj;
			return accountr;
		}

	}

	@Override
	public void setAppAccounttoRedis(AppAccountRedis appAccount) {
		TradeRedis.redisUtilAppAccount.put(appAccount, appAccount.getId().toString());
	}

}

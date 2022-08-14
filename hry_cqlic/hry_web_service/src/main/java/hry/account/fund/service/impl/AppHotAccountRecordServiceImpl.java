/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.account.fund.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppHotAccountRecord;
import hry.account.fund.service.AppHotAccountRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
@Service("appHotAccountRecordService")
public class AppHotAccountRecordServiceImpl extends BaseServiceImpl<AppHotAccountRecord, Long> implements AppHotAccountRecordService{
	
	@Resource(name="appHotAccountRecordDao")
	@Override
	public void setDao(BaseDao<AppHotAccountRecord, Long> dao) {
		super.dao = dao;
	}
	
	/**
	 * 给某个账户添加一条流水  
	 * 
	 * type 表示怎加或减少
	 * 
	 * money 表示改变的钱数
	 * 
	 * states 0处理中 5成功 10失败 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月5日 下午1:41:34
	 */
	@Override
	public void addHotRecord(BigDecimal money,AppAccount appAccount,int type,int states){
		
		AppHotAccountRecord appHotAccountRecord = new AppHotAccountRecord();
		
		appHotAccountRecord.setAccountId(appAccount.getId());
		appHotAccountRecord.setCurrencyType(appAccount.getCurrencyType());
		appHotAccountRecord.setCustomerId(appAccount.getCustomerId());
		appHotAccountRecord.setRecordType(type);
		appHotAccountRecord.setRemark("给  "+appAccount.getUserName()+" 转了一笔流水");
		appHotAccountRecord.setStatus(states);
		appHotAccountRecord.setTransactionMoney(money);
		appHotAccountRecord.setUserName(appAccount.getUserName());
		appHotAccountRecord.setWebsite(appAccount.getWebsite());
		appHotAccountRecord.setTransactionNum("000000000");
		
		super.save(appHotAccountRecord);
	
	}
	
	
	
	
	
	
	

}

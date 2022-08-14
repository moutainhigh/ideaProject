/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-20 09:55:15 
 */
package hry.admin.exchange.service.impl;

import hry.admin.exchange.model.AppAccountRecord;
import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.service.AppAccountRecordService;
import hry.admin.web.model.AppOurAccount;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p> AppAccountRecordService </p>
 * @author:         tianpengyu
 * @Date :          2018-07-20 09:55:15  
 */
@Service("appAccountRecordService")
public class AppAccountRecordServiceImpl extends BaseServiceImpl<AppAccountRecord, Long> implements AppAccountRecordService{
	
	@Resource(name="appAccountRecordDao")
	@Override
	public void setDao(BaseDao<AppAccountRecord, Long> dao) {
		super.dao = dao;
	}

	/**
	 *
	 * 我方账户 增加或 减少币的时候 操作的流水
	 *
	 * @author:    Wu Shuiming
	 * @version:   V1.0
	 * @date:      2016年9月3日 下午6:04:08
	 */
	@Override
	public void addRecordForBit(AppOurAccount ourAccount, String exDigNum, ExDmTransaction exDmTransaction, String auditor, String remark){

		if(null != ourAccount && null != exDmTransaction){
			AppAccountRecord appAccountRecord = new AppAccountRecord();
			appAccountRecord.setAppAccountNum(ourAccount.getAccountNumber());
			appAccountRecord.setAppAccountId(ourAccount.getId());
			appAccountRecord.setCustomerId(exDmTransaction.getCustomerId());
			appAccountRecord.setCustomerName(exDmTransaction.getCustomerName());
			Integer type = Integer.valueOf(ourAccount.getOpenAccountType());
			if(type == 0){
				appAccountRecord.setRecordType(1);
			}else if(type == 1){
				appAccountRecord.setRecordType(2);
			}else{
				appAccountRecord.setRecordType(type);
			}

			appAccountRecord.setSource(1);
			appAccountRecord.setTransactionMoney(exDmTransaction.getTransactionMoney());
			appAccountRecord.setTransactionNum(exDmTransaction.getTransactionNum());
			appAccountRecord.setStatus(5);
			appAccountRecord.setRemark(remark);
			appAccountRecord.setCurrencyName(exDmTransaction.getCoinCode());
			appAccountRecord.setCurrencyType(null);
			appAccountRecord.setAuditor(auditor);
			appAccountRecord.setOperationTime(new Date());
			appAccountRecord.setCustomerAccount(exDigNum);
			appAccountRecord.setWebsite(ourAccount.getWebsite());

			super.save(appAccountRecord);

		}
	}
}

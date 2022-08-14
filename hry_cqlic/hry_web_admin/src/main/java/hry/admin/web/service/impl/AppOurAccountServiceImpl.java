/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-13 18:38:15 
 */
package hry.admin.web.service.impl;

import hry.admin.exchange.model.AppAccountRecord;
import hry.admin.exchange.model.ExDmTransaction;
import hry.admin.exchange.service.AppAccountRecordService;
import hry.admin.web.model.AppOurAccount;
import hry.admin.web.service.AppOurAccountService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> AppOurAccountService </p>
 * @author:         tianpengyu
 * @Date :          2018-07-13 18:38:15  
 */
@Service("appOurAccountService")
public class AppOurAccountServiceImpl extends BaseServiceImpl<AppOurAccount, Long> implements AppOurAccountService{
	
	@Resource(name="appOurAccountDao")
	@Override
	public void setDao(BaseDao<AppOurAccount, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private AppAccountRecordService appAccountRecordService;


	@Override
	public AppOurAccount getAppOurAccount(String website, String currencyType, Integer type) {
		QueryFilter filter = new QueryFilter(AppOurAccount.class);
		filter.addFilter("isShow=", 1);
		filter.addFilter("openAccountType=", type);
		filter.addFilter("website=", website);
		filter.addFilter("currencyType=", currencyType);
		return super.get(filter);
	}

	/**
	 *
	 * 给我方账户 增加或 减少一笔钱
	 *
	 * ourAccount   我方 对应 的币种账号(区分 充值提现 )
	 * ExDmTransaction  充值提现单
	 * digAccountNum  用户 账号
	 * remark 备注
	 * auditor  操作 人
	 *
	 * @author:    Wu Shuiming
	 * @version:   V1.0
	 * @date:      2016年9月3日 下午6:09:51
	 */
	@Override
	public void changeCountToOurAccoun(AppOurAccount ourAccount, ExDmTransaction dmTransaction, String digAccountNum, String remark, String auditor){

		if(null != ourAccount && null != dmTransaction){
			String types = ourAccount.getOpenAccountType();
			Integer type = Integer.valueOf(types);
			Boolean b=false;
			if("fee".equals(auditor)){//手续费
				b = this.changeBitForOurAccount(dmTransaction.getFee(), ourAccount);
			}
			if(type==0){//充币
				b = this.changeBitForOurAccount(dmTransaction.getTransactionMoney().subtract(dmTransaction.getFee()), ourAccount);
			}
			if(type==1){//提币
				b = this.changeBitForOurAccount(dmTransaction.getTransactionMoney(), ourAccount);
			}
			if(b){
				appAccountRecordService.addRecordForBit(ourAccount, digAccountNum, dmTransaction, auditor, remark);
			}
		}

	}

	/**
	 *
	 * 给我方币种 账户  增加一笔 或减少一笔钱
	 *
	 *
	 * @author:    Wu Shuiming
	 * @version:   V1.0
	 * @date:      2016年9月3日 下午4:44:00
	 */
	@Override
	public Boolean changeBitForOurAccount(BigDecimal bitCount, AppOurAccount ourAccount){

		if(null != ourAccount){
			BigDecimal totalCount = ourAccount.getAccountMoney();
			//0 充币 1 提币   2.ico充币  3.ico提币
			String types = ourAccount.getOpenAccountType();
			Integer type = Integer.valueOf(types);
			if (bitCount != null) {
				if (type == 0 || type == 2) {
					ourAccount.setAccountMoney(totalCount.add(bitCount));
					super.update(ourAccount);
					return true;
				}
				if (type == 1 || type == 3) {
					ourAccount.setAccountMoney(totalCount.subtract(bitCount));
					super.update(ourAccount);
					return true;
				}
			}

		}

		return false;
	}



}

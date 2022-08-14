/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年6月17日 上午10:22:49
 */
package hry.account.remote;

import hry.account.fund.model.AppAccountRecord;
import hry.account.fund.model.AppOurAccount;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.trade.entrust.model.ExOrderInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年6月17日 上午10:22:49 
 */
public interface RemoteAppOurAccountService {
	
	
	/**
	 * 返回我方账户的钱账户   0表示银行卡     2 表示支付宝
	 * 
	 * @param website
	 * @param currencyType
	 * @param accountType
	 * @return
	 */
	public AppOurAccount getOurAccount(String website, String currencyType, String accountType);

	

	/**
	 * 
	 *  Map 里的值
	 * 			type :  1充值 2提现 3充值手续费 4提现手续费5表示卖方手续费6表示买方手续费
	 * 			source :  0 表示线下充值  1 表示线上充值 2表示 3表示交易手续费 4 表示充值手续费
	 * 			states :  0未审核 5已审核 10失败
	 *  
	 *  auditor : 操作人  
	 *  
	 *  remark : 描述 
	 * 
	 * 
	 * 给我方账号增加一笔钱  并保存流水 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月9日 上午11:54:40
	 */
	public void addMoneyForOurAccount(ExOrderInfo orderInfo);


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
	public void changeCountToOurAccoun(AppOurAccount ourAccount,
                                       ExDmTransaction dmTransaction, String digAccountNum, String remark,
                                       String auditor);
	
	



	/**
	 * 
	 * type  0 充值   1提现 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月3日 下午7:35:11
	 */
	public AppOurAccount findAppOurAccount(String website, String currencyType,
                                           Integer type);
	
	
	
	/**
	 * 查询我方账户
	 * <p> TODO</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @param website
	 * @param:    @param type
	 * @param:    @return
	 * @return: List<AppOurAccount> 
	 * @Date :          2016年9月6日 下午2:02:33   
	 * @throws:
	 */
	public List<AppOurAccount>  getOurAccounts(String website, String currencyType);
	
	
	
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: boolean 
	 * @Date :          2016年9月6日 下午6:47:22   
	 * @throws:
	 */
	public boolean  updateAccountBalance(AppOurAccount appOurAccount, AppAccountRecord appAccountRecord);
	
	/**
	 * 充币到提币账户后 
	 * 更新提币账户余额
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: boolean 
	 * @Date :          2016年9月6日 下午6:47:22   
	 * @throws:
	 */
	public boolean  updateWithdrawAccountBalance(AppOurAccount appOurAccount, AppAccountRecord appAccountRecord);



	/**
	 * <p> TODO</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @param txid
	 * @param:    @return
	 * @return: List<AppAccountRecord> 
	 * @Date :          2016年9月18日 下午6:37:12   
	 * @throws:
	 */
	public List<AppAccountRecord> getAccountRecord(String txid);



	/**
	 * 
	 * 从我方账户里面给用户的钱包里转一定的数量到用户的钱包
	 * 
	 * @param transactionMoney
	 * @param appOurAccount
	 */
	public void changeBitForOurAccount(BigDecimal transactionMoney,
                                       AppOurAccount appOurAccount);
	
	
	/**
	 * 
	 * <p> TODO</p>
	 * @author:         Yuan Zhicheng
	 * @param:    @param coinCode
	 * @param:    @param accountType
	 * @param:    @param openAccountType /0 充币 1 提币
	 * @param:    @param isShow
	 * @param:    @param website
	 * @param:    @return
	 * @return: AppOurAccount 
	 * @Date :          2016年11月22日 上午11:03:34   
	 * @throws:
	 */
	public AppOurAccount findOurCoinAccountInfo(String coinCode, String accountType, String openAccountType, String isShow, String website);
	
	/**
	 * 用户注册实名后给用户奖励一个币
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param customerId
	 * @return: void 
	 * @Date :          2017年3月9日 下午3:10:25   
	 * @throws:
	 */
	public void sendAuthRewardToCustomer(Long customerId, String coinCode);


	/**
	 * 金科比地址生成成功后，再判断一下实名返币是否成功，没成功继续返
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param userName 
	 * @param:    
	 * @return: void 
	 * @Date :          2017年3月23日 下午7:39:13   
	 * @throws:
	 */
	public void judgeAuthRewardState(String userName, Long exdmaccountId);
}

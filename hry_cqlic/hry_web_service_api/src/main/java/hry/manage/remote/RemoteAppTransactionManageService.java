package hry.manage.remote;

import hry.manage.remote.model.*;
import hry.manage.remote.model.base.FrontPage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * RemoteAppTransactionManageService.java
 * @author denghuifan
 */
public interface RemoteAppTransactionManageService {

	/**
	 * 分页查询我的交易列表
	 * @param params
	 * @return
	 */
	FrontPage frontselectFee(Map<String, String> params);

	/**
	 * 查询该账户的所有银行卡信息
	 * @param id
	 * @return
	 */
	List<AppBankCardManage> findByCustomerId(Long id);

	/**
	 * 保存银行卡
	 * @param user
	 * @return
	 */
	RemoteResult saveBankCard(User user, AppBankCardManage appBankCardManage);

	/**
	 * 删除银行卡
	 * @param id
	 * @return
	 */
	RemoteResult delete(Long id);

	/**
	 * 账户卡号查重
	 * @param cardNumber
	 * @param s
	 * @param type
	 * @return
	 */
	int findSaveFlag(String cardNumber, String s, Integer type);

	/**
	 * 是否开启第三方充值接口
	 * @param type
	 * @return
	 */
	List<AppConfig> getConfigInfo(String type);

	/**
	 * 查询交易记录
	 * @param params
	 * @return
	 */
	FrontPage findExdmtransactionRecord (Map<String, String> params);

	/**
	 * 查询币账户
	 * @param id
	 * @return
	 */
	List<ExDigitalmoneyAccountManage> listexd(Long id, String language);

	/**
	 * 获取当前账户的钱包地址
	 * @param id
	 * @return
	 */
	List<ExDmCustomerPublicKeyManage> getList(Long id);

	/**
	 * 用户提交提币订单
	 * @param user
	 * @param code
	 * @param btcNum
	 * @param type
	 * @param username
	 * @return
	 */
	RemoteResult getOrder(User user, String code, String btcNum, String type, String username, String btcKey, String pacecurrecy);

	/**
	 * 获取币种列表
	 * @return
	 */
	List<ExProductManage> listProduct();

	/**
	 * 查询币账户
	 * @param id
	 * @return
	 */
	List<ExDmCustomerPublicKeyManage> listPublic(Long id);

	/**
	 * 保存用户虚拟币地址
	 * @param exDmCustomerPublicKeyManage
	 */
	void save(ExDmCustomerPublicKeyManage exDmCustomerPublicKeyManage, User user);

	/**
	 * 删除用户币地址
	 * @param id
	 * @param cusId
	 * @return
	 */
	RemoteResult deletePublieKey(Long id, String cusId);

    /**
     * 生成充值汇款单
     * @param remitter
     * @param bankCode
     * @param bankAmount
     * @param bankName
     * @param appTransaction
     * @param user
     * @return
     */
    RemoteResult generateRmbdeposit(String surname, String remitter, String bankCode, String bankAmount, String bankName, AppTransactionManage appTransaction, User user);

    /**
     * 充值分页
     * @param params
     * @return
     */
    FrontPage findTransaction(Map<String, String> params);

	/**
	 * 保存提现订单
	 * @param accountPassWord
	 * @param bankCardId
	 * @param withdrawCode
	 * @param accountpwSmsCode
	 * @param user
	 * @param encryString
	 * @return
	 */
	RemoteResult rmbwithdraw(String accountPassWord, String bankCardId, String withdrawCode, String accountpwSmsCode, User user, String encryString, AppTransactionManage appTransaction);

	/**
	 * 交易订单信息验证
	 * @param user
	 * @param code
	 * @param accountpwSmsCode
	 * @param sessionAccountpwSmsCode
	 * @param btcNum
	 * @param type
	 * @param username
	 * @param btcKey
	 * @return
	 */
	RemoteResult getOrdervail(User user, String code, String accountpwSmsCode, String sessionAccountpwSmsCode, String btcNum, String type, String username, String btcKey);

	/**
	 * 设置默认银行卡
	 * @param id
	 * @return
	 */
	RemoteResult setDefaultBankCard (Long id);




































	// =============================================分割线================================================



	/**
	 * 保存充值订单
	 * @param appTransactionManage
	 */
	public void save(AppTransactionManage appTransactionManage);

	/**
	 * 保存银行卡信息
	 * @param appBankCardManage
	 */
	public void save(AppBankCardManage appBankCardManage);




	public RemoteResult delete(Long id, String cusId);

	/**
	 * 通过银行卡号获取
	 * @param cardname
	 * @return
	 */
	public AppBankCardManage get(String cardname);

	/**
	 * 通过银行卡Id
	 * @param cardname
	 * @return
	 */
	public AppBankCardManage get(Long cardId);

	/**
	 * 好像是计算当天的最大提现金额
	 * @param type
	 * @param beginDate
	 * @param endDate
	 * @param status
	 * @param userName
	 * @return
	 */
	public BigDecimal countByDate(String[] type, String beginDate, String endDate, String[] status, String userName);

	/**
	 * 保存提现订单及虚拟账户信息
	 * @param appAccount
	 * @param appTransaction
	 */
	public void rmbwithdraw(AppAccountManage appAccount, AppTransactionManage appTransaction);




	/**
	 * 虚拟货币的分页
	 * @param map
	 * @return
	 */
	public FrontPage findExdmtransaction(Map<String, String> map);






	/**
	 * 删除币账户
	 * @param id
	 */
	public void deletePublieKey(Long id);









	/**
	 * 测试充值
	 * @param id
	 * @param userName
	 * @return
	 */
	public String[] testconfirmRmbRecharge(String surname, String remitter, String bankCode, String bankAmount, String bankName, AppTransactionManage appTransaction, User user);
	/**
	 * 测试充币
	 * @param id
	 * @param userName
	 * @return
	 */
	public String[] testCoinRecharge(String coinCode, String bankAmount, User user);

	/**
	 * 获取提现手续费率
	 * @return
	 */
	public BigDecimal witfee();

	/**
	 * LMC 钱包-钱包转币
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param lmcTransfer
	 * @param:    @return
	 * @return: String[]
	 * @Date :          2017年7月31日 下午3:45:28
	 * @throws:
	 */
	public String[] lmcTransfer(LmcTransfer lmcTransfer);

	/**
	 * LMC 查询账单总和
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param transfer
	 * @param:    @return
	 * @return: String[]
	 * @Date :          2017年7月31日 下午5:50:51
	 * @throws:
	 */
	public String [] walletTransferSum(LmcTransfer transfer);
	/**
	 * LMC 获取账单列表
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param transfer
	 * @param:    @return
	 * @return: String[]
	 * @Date :          2017年7月31日 下午5:50:51
	 * @throws:
	 */
	public String [] listwalletTransfer(LmcTransfer transfer);

	/**
	 * 获得钱包地址
	 * @param customerId
	 * @param coinCode
	 * @return
	 */
	public List<ExDmCustomerPublicKeyManage> getList(Long customerId, String coinCode);



	public FrontPage selectFee(Map<String, String> params);




	public List<ExDigitalmoneyAccountManage> listcoin(String coinCode);


    String getRetentionNumberByProduct(String coinCode);






}

package hry.otc.remote;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.otc.remote.model.AppAppealRemote;
import hry.otc.remote.model.AppOrderSpeakRemote;
import hry.otc.remote.model.OtcAppLogRemote;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;

public interface RemoteAdvertisementService {

	/**
	 * 新增申述
	 * @param userId
	 * @param tradeNum
	 * @param appeal
	 * @param content
	 * @return
	 */
	JsonResult addAppeal(Long userId, String tradeNum, String appeal, String content, String thingUrl,String transactionMode);

	/**
	 * 跳转订单流程页面 - 查询
	 * @param tradeNum
	 * @return
	 */
	JsonResult appealInfor(String tradeNum);

	/**
	 * 获取订单信息
	 * @param tradeNum
	 * @param releaseId
	 * @return
	 */
	JsonResult orderAccounting(String tradeNum, Long releaseId);

	/**
	 * 获取一条申诉信息
	 * @param transactionNum
	 * @return
	 */
	AppAppealRemote getAppAppealByNum(String transactionNum);

	/**
	 * 取消申诉
	 * @param transactinNum 交易单号
	 * @return
	 */
	JsonResult cancelAppeal(String transactinNum);

	/**
	 * 广告大厅交易详情
	 * @param payType
	 * @param nationality
	 * @param coinCode
	 * @param transactionMode
	 * @param offset
	 * @param limit
	 * @return
	 */
	FrontPage advertisingHallDetail(String payType,String nationality,String coinCode, String transactionMode, String offset, String limit, String legalCurrency);

	/**
	 * 查询发布广告者完成率
	 * @param userId
	 * @param coinCode
	 * @return
	 */
	BigDecimal getCompletionRate(Long userId, String coinCode);

	/**
	 * 获取该币种小数位
	 * @param coinCode
	 * @return
	 */
	int keepDecimalForCoin(String coinCode);

	/**
	 * 查询一条广告
	 * @param id
	 * @return
	 */
	JsonResult getById(Long id);

	/**
	 * 添加个人资产信息
	 * @param type
	 * @param bankName
	 * @param account
	 * @param surname
	 * @param truename
	 * @param subBranch
	 * @param thingUrl
	 * @param customerId
	 * @param bankAddress
	 * @param bankProvince
	 * @param cardBank
	 */
	void addPersonalAsset(String type, String bankName, String account, String surname,String truename, String subBranch, String thingUrl, String customerId, String bankAddress, String bankProvince, String cardBank);

	/**
	 * 根据主键id删除记录
	 * @param id
	 * @param userId
	 * @return
	 */
	JsonResult deletePersonalAsset(Long id, Long userId);

	/**
	 * 设置为默认支付信息
	 * @param id
	 * @param type
	 * @param userId
	 * @return
	 */
	JsonResult setDefault(Long id, String type, Long userId);

	/**
	 * 根据基础币code获得对应的汇率
	 */
	JsonResult getExchangeRateByCode(String baseCoinCode);

	/**
	 * 获得支付记录
	 * @param userId 用户id
	 * @param type 支付信息类型
	 * @param isDefault 是否默认 不为空时，查询默认
	 * @return
	 */
	JsonResult getPersonalAsset(Long userId,String type, String isDefault);

	/**
	 * 添加广告
	 * @param transactionMode
	 * @param coinCode
	 * @param coinName
	 * @param nationality
	 * @param isFixed
	 * @param tradeMoney
	 * @param premium
	 * @param paymentTerm
	 * @param tradeMoneyMix
	 * @param tradeMoneyMax
	 * @param remark
	 * @param isAutomatic
	 * @param isSecurity
	 * @param isBeTrusted
	 * @param userId
	 * @param payType
	 * @param payTypeRemake
	 * @param serviceCharge
	 * @return
	 */
	JsonResult addReleaseAdvertisement(String transactionMode, String coinCode, String coinName,
			  String nationality, String isFixed, String tradeMoney, String premium, String paymentTerm,
			  String tradeMoneyMix, String tradeMoneyMax, String remark, String isAutomatic, String isSecurity,
			  String isBeTrusted, Long userId, String payType, String payTypeRemake, String serviceCharge, BigDecimal coinNumMin, BigDecimal coinNumMax,String hidshichangjiage, String legalCurrency);

	/**
	 * 个人挂单手续费
	 * @param premium//溢价
	 * @param shichangjiage//拉取价格
	 * @param coinCode//币种
	 * @param tradeMoneyMax//最高价格
	 * @param transactionMode//交易类型
	 * @param isFixeds//是否固定
	 * @param tradeMoney//固定价格
	 * @return
	 */
	JsonResult userExCoinParameter(Long userId, String premium, String shichangjiage, String coinCode,
			  String tradeMoneyMax, String transactionMode, String isFixeds, String tradeMoney);

	/**
	 * 广告大厅 - 用户主页 - 个人信息
	 * @param id
	 * @param coinCode
	 * @return
	 */
	JsonResult adUserInfor(Long id, String coinCode);

	/**
	 * 进入用户详情 对于当前用户 是否信任 屏蔽
	 * @param id1
	 * @param id2
	 * @return
	 */
	JsonResult selectTrustShield(Long id1, Long id2);

	/**
	 * 获取用户缓存里的币余额
	 * @param userId
	 * @param coinCode
	 * @return
	 */
	ExDigitalmoneyAccountRedis getCoinAccountRedis(Long userId, String coinCode);

	/**
	 * 是否可以进行交易（即是否已经有当前类型的交易处于进行中）
	 * @param customerId
	 * @param transactionMode 交易类型（1--出售广告  2--购买广告）
	 * @param coinCode
	 * @return
	 */
	JsonResult isCanTransaction(String customerId, String transactionMode,String coinCode);

	/**
	 * 买卖
	 * @param releaseId
	 * @param tradeMoney
	 * @param coinCodeMoney
	 * @param remark
	 * @param buyId
	 * @param sellId
	 * @param transactionMode
	 * @param coinCode
	 * @param payType
	 * @param userid
	 * @param editor
	 * @return
	 */
	JsonResult buydetail(Long releaseId, String tradeMoney, String coinCodeMoney, String remark, Long buyId,
								Long sellId, String transactionMode, String coinCode, String payType, Long userid, String editor);

	/**
	 * 订单在redis的存储时间
	 * @param tradeNum
	 * @param paymentTerm
	 * @return
	 */
	JsonResult redisTimeOrder(String tradeNum, String paymentTerm);

	/**
	 * 查询聊天记录
	 * @param orderId
	 * @return
	 */
	List<AppOrderSpeakRemote> selectOrderSpeak(Long orderId);

	/**
	 * 卖家保存聊天消息
	 * @param orderId
	 * @param buyId
	 * @param sellId
	 * @param sellSpeak
	 */
	void addSellOrderSpeak(String orderId, Long buyId, Long sellId, String sellSpeak);

	/**
	 * 个人买卖单
	 * @param map
	 * @return
	 */
	FrontPage userAppTransaction(Map<String, String> map);

	/**
	 * 买卖方退款一类的
	 * @param tradeNum
	 * @param type
	 * @param transactionMode
	 * @param content
	 * @param userid
	 * @return
	 */
	JsonResult refundAndReject(String tradeNum, String type, String transactionMode, String content, Long userid);

	/**
	 * 取消订单
	 * @param tradeNum
	 */
	JsonResult cancleOrder(String tradeNum);

	/**
	 * 已完成订单
	 * @param tradeNum
	 * @param paymentTerm
	 * @return
	 */
	JsonResult orderCompleted(String tradeNum, String paymentTerm);

	/**
	 * 完成支付
	 *
	 * @param tradeNum
	 * @param paymentTerm
	 * @return
	 */
	JsonResult orderPayment(String tradeNum, String paymentTerm);

	/**
	 * 删除 1-买方  2-卖方
	 * @param transactionNum
	 * @param type
	 * @return
	 */
	JsonResult updateIsDeleted(String transactionNum, String type);

	/**
	 * 更新广告状态
	 * @return
	 */
	JsonResult closeReleaseAdvertisement(Long releaseId);

	/**
	 * 我的广告 - 查询
	 * @param params
	 * @return
	 */
	FrontPage queryReleaseAdvertisement(Map<String, String> params);

	/**
	 * 根据所属人id和类型修改广告集合
	 */
	JsonResult batchCloseAd(Long customerId,String transactionMode);

	/**
	 * OTC昵称
	 * @param nName
	 * @param id
	 */
	void updateNickName(String nName, Long id);

	/**
	 * 增加聊天记录
	 * @param orderId
	 * @param buyId
	 * @param sellId
	 * @param buySpeak
	 */
	void addOrderSpeak(String orderId, Long buyId, Long sellId, String buySpeak);

	/**
	 * 信任 - 屏蔽
	 * @param trustId
	 * @param ShieldId
	 * @return
	 */
	JsonResult trustShield(Long trustId, Long ShieldId, String type);

	/**
	 * 获得发广告者的信息
	 * @param id
	 * @return
	 */
	JsonResult getUserById(Long id);

	/**
	 * 获取平均放行时间
	 * @param customerId
	 * @return
	 */
	String avgTime(Long customerId);

	/**
	 * 查询订单状态
	 * @param tradeNum
	 * @return
	 */
	JsonResult findOtcOrderById(String tradeNum);





















	// ==========================================分割线=====================================


	/**
	 * 获得币种的吃单手续费 和 吃单手续费类型
	 * @param coinCodeMoney
	 * @param coinCode
	 * @return
	 */
	public JsonResult getExCoinFee(String coinCodeMoney, String coinCode);

	/**
	 * 定时器定时取消订单
	 * @param params
	 * @return
	 */
	public FrontPage gettransaction(Map<String, String> params);

	/**
	 * 恢复广告解冻币
	 * @param tradeNum
	 * @param releaseId
	 * @return
	 */
	public JsonResult recoveryReleaseAdvertisement(String tradeNum, Long releaseId);



	/**
	 * 查询是否已经绑定过银行卡或微信或支付宝
	 * @param type
	 * @param id
	 * @return
	 */
	public JsonResult isBankCard(String type, Long id);







	/**
	 * OTC记录登录时间
	 * @param userId
	 * @return
	 */
	public JsonResult isLogLogin(Long userId);

	/**
	 * 更新
	 * @param appLogRemote
	 */
	public void updateByPrimaryKeySelective(OtcAppLogRemote appLogRemote);

	/**
	 * 新增
	 * @param appLogRemote
	 */
	public void insert(OtcAppLogRemote appLogRemote);

	/**
	 * 广告大厅 - 用户主页 - 广告信息
	 * @param id
	 * @param state
	 * @return
	 */
	public JsonResult aduserTable(Long id,String state);

	/**
	 * 判断该广告是否有未完成的订单
	 * @param aLong
	 * @return
	 */
	Boolean findOrderByStatus (Long aLong);

	/**
	 * 全部撤销5.5元一下并且不存在订单的广告（取消和完成的除外）
	 * @return
	 */
	void closeAllReleaseAdvertisement ();




}

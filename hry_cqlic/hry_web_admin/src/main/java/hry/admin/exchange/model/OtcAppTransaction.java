/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-26 18:12:38 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * <p> OtcAppTransaction </p>
 * @author:         denghf
 * @Date :          2018-10-26 18:12:38  
 */
@Table(name="otc_app_transaction")
public class OtcAppTransaction extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "transactionNum")
	private String transactionNum;  //交易订单号
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "accountId")
	private Long accountId;  //虚拟账户id
	
	@Column(name= "buyUserId")
	private Long buyUserId;  //买方ID
	
	@Column(name= "buyUserName")
	private String buyUserName;  //买方用户名
	
	@Column(name= "buyfee")
	private BigDecimal buyfee;  //买方手续费
	
	@Column(name= "sellUserId")
	private Long sellUserId;  //卖方ID
	
	@Column(name= "sellUserName")
	private String sellUserName;  //卖方用户名
	
	@Column(name= "sellfee")
	private BigDecimal sellfee;  //卖方手续费
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代码
	
	@Column(name= "transactionMode")
	private Integer transactionMode;  //交易方式(1在线购买,2在线出售,3本地购买)
	
	@Column(name= "payType")
	private String payType;  //交易类型(1银行转账,2支付宝,3微信支付)
	
	@Column(name= "tradeNum")
	private BigDecimal tradeNum;  //交易数量
	
	@Column(name= "tradeMoney")
	private BigDecimal tradeMoney;  //交易金额
	
	@Column(name= "transactionType")
	private Integer transactionType;  //交易类型(1定价交易 2市价交易)

	/**
	 * 1待支付 2已付款待确认 3已支付 4申诉中待回复 5已取消 6申请退款中 7退款已驳回 8申诉完成
	 * 9申诉成功,待确认 10申诉失败,待确认 11平台通过申诉 12平台驳回申诉 13退款成功 14已完成
	 * 15买家申诉中 16卖家申诉中
	 */
	@Column(name= "status")
	private Integer status;
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "payTime")
	private Date payTime;  //transactionMode为1时,为买家购买时间,为2时,为卖家购买时间
	
	@Column(name= "confirmMoney")
	private Date confirmMoney;  //transactionMode为1时,为买家确认收款时间,为2时,为卖家确认收款时间
	
	@Column(name= "appealTime")
	private Date appealTime;  //transactionMode为1时,为买家申诉时间,为2时,为卖家申诉时间
	
	@Column(name= "advertisementId")
	private Long advertisementId;  //广告ID
	
	@Column(name= "referenceNum")
	private String referenceNum;  //付款参考号
	
	@Column(name= "sellIsDeleted")
	private Integer sellIsDeleted;  //卖方是否删除
	
	@Column(name= "buyIsDeleted")
	private Integer buyIsDeleted;  //买方是否删除
	
	@Column(name= "bankId")
	private Long bankId;  //支付信息记录id
	
	@Column(name= "bankNumber")
	private String bankNumber;  //银行卡号
	
	@Column(name= "alipayId")
	private Long alipayId;  //支付信息记录id
	
	@Column(name= "alipayAccount")
	private String alipayAccount;  //支付宝账号
	
	@Column(name= "alipayThingUrl")
	private String alipayThingUrl;  //支付宝二维码
	
	@Column(name= "wechatId")
	private Long wechatId;  //支付信息记录id
	
	@Column(name= "wechatAccount")
	private String wechatAccount;  //微信账号
	
	@Column(name= "wechatThingUrl")
	private String wechatThingUrl;  //微信二维码

	@Column(name= "paymentCode", columnDefinition= "varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '付款参考码'")
	private String paymentCode;  //付款参考码

	@Column(name= "legalCurrency", columnDefinition = "varchar(50) DEFAULT null COMMENT '法币'")
	private String legalCurrency; // 法币

	@Transient
	private String paymentTerm;//付款期限
	@Transient
	private String stateZHCN;// 状态中文意思

	@Transient
	private String advertisementRemark;// 挂单人的备注

	@Transient
	private String buyMobile;// 买方手机号

	@Transient
	private String buyEmail;// 买方邮箱

	@Transient
	private String sellMobile;// 卖方手机号

	@Transient
	private String sellEmail;// 卖方邮箱

	public String getLegalCurrency() {
		return legalCurrency;
	}

	public void setLegalCurrency(String legalCurrency) {
		this.legalCurrency = legalCurrency;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getAdvertisementRemark() {
		return advertisementRemark;
	}

	public void setAdvertisementRemark(String advertisementRemark) {
		this.advertisementRemark = advertisementRemark;
	}

	public String getBuyMobile() {
		return buyMobile;
	}

	public void setBuyMobile(String buyMobile) {
		this.buyMobile = buyMobile;
	}

	public String getBuyEmail() {
		return buyEmail;
	}

	public void setBuyEmail(String buyEmail) {
		this.buyEmail = buyEmail;
	}

	public String getSellMobile() {
		return sellMobile;
	}

	public void setSellMobile(String sellMobile) {
		this.sellMobile = sellMobile;
	}

	public String getSellEmail() {
		return sellEmail;
	}

	public void setSellEmail(String sellEmail) {
		this.sellEmail = sellEmail;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public String getStateZHCN() {
		return stateZHCN;
	}

	public void setStateZHCN(String stateZHCN) {
		this.stateZHCN = stateZHCN;
	}

	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>交易订单号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>交易订单号</p>
	 * @author:  denghf
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  denghf
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>虚拟账户id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>虚拟账户id</p>
	 * @author:  denghf
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>买方ID</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Long getBuyUserId() {
		return buyUserId;
	}
	
	/**
	 * <p>买方ID</p>
	 * @author:  denghf
	 * @param:   @param buyUserId
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setBuyUserId(Long buyUserId) {
		this.buyUserId = buyUserId;
	}
	
	
	/**
	 * <p>买方用户名</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getBuyUserName() {
		return buyUserName;
	}
	
	/**
	 * <p>买方用户名</p>
	 * @author:  denghf
	 * @param:   @param buyUserName
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}
	
	
	/**
	 * <p>买方手续费</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public BigDecimal getBuyfee() {
		return buyfee;
	}
	
	/**
	 * <p>买方手续费</p>
	 * @author:  denghf
	 * @param:   @param buyfee
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setBuyfee(BigDecimal buyfee) {
		this.buyfee = buyfee;
	}
	
	
	/**
	 * <p>卖方ID</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Long getSellUserId() {
		return sellUserId;
	}
	
	/**
	 * <p>卖方ID</p>
	 * @author:  denghf
	 * @param:   @param sellUserId
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setSellUserId(Long sellUserId) {
		this.sellUserId = sellUserId;
	}
	
	
	/**
	 * <p>卖方用户名</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getSellUserName() {
		return sellUserName;
	}
	
	/**
	 * <p>卖方用户名</p>
	 * @author:  denghf
	 * @param:   @param sellUserName
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setSellUserName(String sellUserName) {
		this.sellUserName = sellUserName;
	}
	
	
	/**
	 * <p>卖方手续费</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public BigDecimal getSellfee() {
		return sellfee;
	}
	
	/**
	 * <p>卖方手续费</p>
	 * @author:  denghf
	 * @param:   @param sellfee
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setSellfee(BigDecimal sellfee) {
		this.sellfee = sellfee;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  denghf
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>交易方式(1在线购买,2在线出售,3本地购买)</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Integer getTransactionMode() {
		return transactionMode;
	}
	
	/**
	 * <p>交易方式(1在线购买,2在线出售,3本地购买)</p>
	 * @author:  denghf
	 * @param:   @param transactionMode
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setTransactionMode(Integer transactionMode) {
		this.transactionMode = transactionMode;
	}
	
	
	/**
	 * <p>交易类型(1银行转账,2支付宝,3微信支付)</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getPayType() {
		return payType;
	}
	
	/**
	 * <p>交易类型(1银行转账,2支付宝,3微信支付)</p>
	 * @author:  denghf
	 * @param:   @param payType
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	
	/**
	 * <p>交易数量</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public BigDecimal getTradeNum() {
		return tradeNum;
	}
	
	/**
	 * <p>交易数量</p>
	 * @author:  denghf
	 * @param:   @param tradeNum
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setTradeNum(BigDecimal tradeNum) {
		this.tradeNum = tradeNum;
	}
	
	
	/**
	 * <p>交易金额</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}
	
	/**
	 * <p>交易金额</p>
	 * @author:  denghf
	 * @param:   @param tradeMoney
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	
	
	/**
	 * <p>交易类型(1定价交易 2市价交易)</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Integer getTransactionType() {
		return transactionType;
	}
	
	/**
	 * <p>交易类型(1定价交易 2市价交易)</p>
	 * @author:  denghf
	 * @param:   @param transactionType
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}
	
	
	/**
	 * <p>1待支付 2已付款待确认 3已完成 4申诉中待回复 5已取消 6申请退款中 7退款已驳回 8申诉完成 9申诉成功,待确认 10申诉失败,待确认 11平台通过申诉 12平台驳回申诉 13退款成功</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>1待支付 2已付款待确认 3已完成 4申诉中待回复 5已取消 6申请退款中 7退款已驳回 8申诉完成 9申诉成功,待确认 10申诉失败,待确认 11平台通过申诉 12平台驳回申诉 13退款成功</p>
	 * @author:  denghf
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  denghf
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>transactionMode为1时,为买家购买时间,为2时,为卖家购买时间</p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Date getPayTime() {
		return payTime;
	}
	
	/**
	 * <p>transactionMode为1时,为买家购买时间,为2时,为卖家购买时间</p>
	 * @author:  denghf
	 * @param:   @param payTime
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	
	/**
	 * <p>transactionMode为1时,为买家确认收款时间,为2时,为卖家确认收款时间</p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Date getConfirmMoney() {
		return confirmMoney;
	}
	
	/**
	 * <p>transactionMode为1时,为买家确认收款时间,为2时,为卖家确认收款时间</p>
	 * @author:  denghf
	 * @param:   @param confirmMoney
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setConfirmMoney(Date confirmMoney) {
		this.confirmMoney = confirmMoney;
	}
	
	
	/**
	 * <p>transactionMode为1时,为买家申诉时间,为2时,为卖家申诉时间</p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Date getAppealTime() {
		return appealTime;
	}
	
	/**
	 * <p>transactionMode为1时,为买家申诉时间,为2时,为卖家申诉时间</p>
	 * @author:  denghf
	 * @param:   @param appealTime
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setAppealTime(Date appealTime) {
		this.appealTime = appealTime;
	}
	
	
	/**
	 * <p>广告ID</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Long getAdvertisementId() {
		return advertisementId;
	}
	
	/**
	 * <p>广告ID</p>
	 * @author:  denghf
	 * @param:   @param advertisementId
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}
	
	
	/**
	 * <p>付款参考号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getReferenceNum() {
		return referenceNum;
	}
	
	/**
	 * <p>付款参考号</p>
	 * @author:  denghf
	 * @param:   @param referenceNum
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setReferenceNum(String referenceNum) {
		this.referenceNum = referenceNum;
	}
	
	
	/**
	 * <p>卖方是否删除</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Integer getSellIsDeleted() {
		return sellIsDeleted;
	}
	
	/**
	 * <p>卖方是否删除</p>
	 * @author:  denghf
	 * @param:   @param sellIsDeleted
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setSellIsDeleted(Integer sellIsDeleted) {
		this.sellIsDeleted = sellIsDeleted;
	}
	
	
	/**
	 * <p>买方是否删除</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Integer getBuyIsDeleted() {
		return buyIsDeleted;
	}
	
	/**
	 * <p>买方是否删除</p>
	 * @author:  denghf
	 * @param:   @param buyIsDeleted
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setBuyIsDeleted(Integer buyIsDeleted) {
		this.buyIsDeleted = buyIsDeleted;
	}
	
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Long getBankId() {
		return bankId;
	}
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @param:   @param bankId
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
	
	
	/**
	 * <p>银行卡号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getBankNumber() {
		return bankNumber;
	}
	
	/**
	 * <p>银行卡号</p>
	 * @author:  denghf
	 * @param:   @param bankNumber
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Long getAlipayId() {
		return alipayId;
	}
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @param:   @param alipayId
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setAlipayId(Long alipayId) {
		this.alipayId = alipayId;
	}
	
	
	/**
	 * <p>支付宝账号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getAlipayAccount() {
		return alipayAccount;
	}
	
	/**
	 * <p>支付宝账号</p>
	 * @author:  denghf
	 * @param:   @param alipayAccount
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	
	
	/**
	 * <p>支付宝二维码</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getAlipayThingUrl() {
		return alipayThingUrl;
	}
	
	/**
	 * <p>支付宝二维码</p>
	 * @author:  denghf
	 * @param:   @param alipayThingUrl
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setAlipayThingUrl(String alipayThingUrl) {
		this.alipayThingUrl = alipayThingUrl;
	}
	
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public Long getWechatId() {
		return wechatId;
	}
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @param:   @param wechatId
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}
	
	
	/**
	 * <p>微信账号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getWechatAccount() {
		return wechatAccount;
	}
	
	/**
	 * <p>微信账号</p>
	 * @author:  denghf
	 * @param:   @param wechatAccount
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}
	
	
	/**
	 * <p>微信二维码</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 18:12:38    
	 */
	public String getWechatThingUrl() {
		return wechatThingUrl;
	}
	
	/**
	 * <p>微信二维码</p>
	 * @author:  denghf
	 * @param:   @param wechatThingUrl
	 * @return:  void 
	 * @Date :   2018-10-26 18:12:38   
	 */
	public void setWechatThingUrl(String wechatThingUrl) {
		this.wechatThingUrl = wechatThingUrl;
	}
	
	

}

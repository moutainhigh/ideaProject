/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-29 13:36:05 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> ReleaseAdvertisement </p>
 * @author:         denghf
 * @Date :          2018-10-29 13:36:05  
 */
@Table(name="release_advertisement")
public class ReleaseAdvertisement extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "accountId")
	private Long accountId;  //虚拟账户id
	
	@Column(name= "coinName")
	private String coinName;  //币种名称
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代码
	
	@Column(name= "transactionMode")
	private Integer transactionMode;  //交易方式(1在线购买,2在线出售,3本地购买)
	
	@Column(name= "nationality")
	private String nationality;  //国籍
	
	@Column(name= "isFixed")
	private Integer isFixed;  //固定价格是否开启 0否 1是
	
	@Column(name= "tradeMoney")
	private BigDecimal tradeMoney;  //交易金额
	
	@Column(name= "premium")
	private BigDecimal premium;  //溢价
	
	@Column(name= "paymentTerm")
	private String paymentTerm;  //付款期限
	
	@Column(name= "payType")
	private String payType;  //交易类型(1银行转账,2支付宝,3微信支付)
	
	@Column(name= "tradeMoneyMix")
	private BigDecimal tradeMoneyMix;  //最低交易金额
	
	@Column(name= "tradeMoneyMax")
	private BigDecimal tradeMoneyMax;  //最高交易金额
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "isAutomatic")
	private Integer isAutomatic;  //固定价格是否开启 0否 1是
	
	@Column(name= "automaticReply")
	private String automaticReply;  //自动回复内容
	
	@Column(name= "isSecurity")
	private Integer isSecurity;  //是否启用安全选项 0否 1是
	
	@Column(name= "isBeTrusted")
	private Integer isBeTrusted;  //是否启用仅限受信任的交易者 0否 1是
	
	@Column(name= "transactionNum")
	private Integer transactionNum;  //交易次数
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "payTypeRemake")
	private String payTypeRemake;  //上传的资料
	
	@Column(name= "status")
	private Integer status;  //广告状态 0关闭 1开启
	
	@Column(name= "state")
	private Integer state;  //广告状态为0有效，1作废
	
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
	
	@Column(name= "coinNumMin")
	private BigDecimal coinNumMin;  //数量min
	
	@Column(name= "coinNumMax")
	private BigDecimal coinNumMax;  //数量max

	@Column(name= "initialCoinNumMin", columnDefinition = "decimal(20,10) DEFAULT null COMMENT '初始广告数量min'")
	private BigDecimal initialCoinNumMin; // 初始广告最小数量记录

	@Column(name= "initialCoinNumMax", columnDefinition = "decimal(20,10) DEFAULT null COMMENT '初始广告数量max'")
	private BigDecimal initialCoinNumMax; // 初始广告最大数量记录

	@Column(name= "legalCurrency", columnDefinition = "varchar(50) DEFAULT null COMMENT '法币'")
	private String legalCurrency; // 法币

	@Transient
	private Integer advStatus;//取消订单时的状态,1 全部吃单之后已成交 2 吃单了部分然后取消，显示部分成交已取消 3未成交已取消

	@Transient
	private String mobilePhone;

	@Transient
	private String email;

	public String getLegalCurrency() {
		return legalCurrency;
	}

	public void setLegalCurrency(String legalCurrency) {
		this.legalCurrency = legalCurrency;
	}

	public BigDecimal getInitialCoinNumMin() {
		return initialCoinNumMin;
	}

	public void setInitialCoinNumMin(BigDecimal initialCoinNumMin) {
		this.initialCoinNumMin = initialCoinNumMin;
	}

	public BigDecimal getInitialCoinNumMax() {
		return initialCoinNumMax;
	}

	public void setInitialCoinNumMax(BigDecimal initialCoinNumMax) {
		this.initialCoinNumMax = initialCoinNumMax;
	}

	public Integer getAdvStatus() {
		return advStatus;
	}

	public void setAdvStatus(Integer advStatus) {
		this.advStatus = advStatus;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  denghf
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>虚拟账户id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>虚拟账户id</p>
	 * @author:  denghf
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>币种名称</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getCoinName() {
		return coinName;
	}
	
	/**
	 * <p>币种名称</p>
	 * @author:  denghf
	 * @param:   @param coinName
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  denghf
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>交易方式(1在线购买,2在线出售,3本地购买)</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Integer getTransactionMode() {
		return transactionMode;
	}
	
	/**
	 * <p>交易方式(1在线购买,2在线出售,3本地购买)</p>
	 * @author:  denghf
	 * @param:   @param transactionMode
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setTransactionMode(Integer transactionMode) {
		this.transactionMode = transactionMode;
	}
	
	
	/**
	 * <p>国籍</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getNationality() {
		return nationality;
	}
	
	/**
	 * <p>国籍</p>
	 * @author:  denghf
	 * @param:   @param nationality
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
	/**
	 * <p>固定价格是否开启 0否 1是</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Integer getIsFixed() {
		return isFixed;
	}
	
	/**
	 * <p>固定价格是否开启 0否 1是</p>
	 * @author:  denghf
	 * @param:   @param isFixed
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setIsFixed(Integer isFixed) {
		this.isFixed = isFixed;
	}
	
	
	/**
	 * <p>交易金额</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}
	
	/**
	 * <p>交易金额</p>
	 * @author:  denghf
	 * @param:   @param tradeMoney
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	
	
	/**
	 * <p>溢价</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public BigDecimal getPremium() {
		return premium;
	}
	
	/**
	 * <p>溢价</p>
	 * @author:  denghf
	 * @param:   @param premium
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	
	
	/**
	 * <p>付款期限</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getPaymentTerm() {
		return paymentTerm;
	}
	
	/**
	 * <p>付款期限</p>
	 * @author:  denghf
	 * @param:   @param paymentTerm
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	
	
	/**
	 * <p>交易类型(1银行转账,2支付宝,3微信支付)</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getPayType() {
		return payType;
	}
	
	/**
	 * <p>交易类型(1银行转账,2支付宝,3微信支付)</p>
	 * @author:  denghf
	 * @param:   @param payType
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	
	/**
	 * <p>最低交易金额</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public BigDecimal getTradeMoneyMix() {
		return tradeMoneyMix;
	}
	
	/**
	 * <p>最低交易金额</p>
	 * @author:  denghf
	 * @param:   @param tradeMoneyMix
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setTradeMoneyMix(BigDecimal tradeMoneyMix) {
		this.tradeMoneyMix = tradeMoneyMix;
	}
	
	
	/**
	 * <p>最高交易金额</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public BigDecimal getTradeMoneyMax() {
		return tradeMoneyMax;
	}
	
	/**
	 * <p>最高交易金额</p>
	 * @author:  denghf
	 * @param:   @param tradeMoneyMax
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setTradeMoneyMax(BigDecimal tradeMoneyMax) {
		this.tradeMoneyMax = tradeMoneyMax;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  denghf
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>固定价格是否开启 0否 1是</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Integer getIsAutomatic() {
		return isAutomatic;
	}
	
	/**
	 * <p>固定价格是否开启 0否 1是</p>
	 * @author:  denghf
	 * @param:   @param isAutomatic
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setIsAutomatic(Integer isAutomatic) {
		this.isAutomatic = isAutomatic;
	}
	
	
	/**
	 * <p>自动回复内容</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getAutomaticReply() {
		return automaticReply;
	}
	
	/**
	 * <p>自动回复内容</p>
	 * @author:  denghf
	 * @param:   @param automaticReply
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setAutomaticReply(String automaticReply) {
		this.automaticReply = automaticReply;
	}
	
	
	/**
	 * <p>是否启用安全选项 0否 1是</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Integer getIsSecurity() {
		return isSecurity;
	}
	
	/**
	 * <p>是否启用安全选项 0否 1是</p>
	 * @author:  denghf
	 * @param:   @param isSecurity
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setIsSecurity(Integer isSecurity) {
		this.isSecurity = isSecurity;
	}
	
	
	/**
	 * <p>是否启用仅限受信任的交易者 0否 1是</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Integer getIsBeTrusted() {
		return isBeTrusted;
	}
	
	/**
	 * <p>是否启用仅限受信任的交易者 0否 1是</p>
	 * @author:  denghf
	 * @param:   @param isBeTrusted
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setIsBeTrusted(Integer isBeTrusted) {
		this.isBeTrusted = isBeTrusted;
	}
	
	
	/**
	 * <p>交易次数</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Integer getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>交易次数</p>
	 * @author:  denghf
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setTransactionNum(Integer transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>上传的资料</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getPayTypeRemake() {
		return payTypeRemake;
	}
	
	/**
	 * <p>上传的资料</p>
	 * @author:  denghf
	 * @param:   @param payTypeRemake
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setPayTypeRemake(String payTypeRemake) {
		this.payTypeRemake = payTypeRemake;
	}
	
	
	/**
	 * <p>广告状态 0关闭 1开启</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>广告状态 0关闭 1开启</p>
	 * @author:  denghf
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>广告状态为0有效，1作废</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>广告状态为0有效，1作废</p>
	 * @author:  denghf
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Long getBankId() {
		return bankId;
	}
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @param:   @param bankId
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
	
	
	/**
	 * <p>银行卡号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getBankNumber() {
		return bankNumber;
	}
	
	/**
	 * <p>银行卡号</p>
	 * @author:  denghf
	 * @param:   @param bankNumber
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Long getAlipayId() {
		return alipayId;
	}
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @param:   @param alipayId
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setAlipayId(Long alipayId) {
		this.alipayId = alipayId;
	}
	
	
	/**
	 * <p>支付宝账号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getAlipayAccount() {
		return alipayAccount;
	}
	
	/**
	 * <p>支付宝账号</p>
	 * @author:  denghf
	 * @param:   @param alipayAccount
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	
	
	/**
	 * <p>支付宝二维码</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getAlipayThingUrl() {
		return alipayThingUrl;
	}
	
	/**
	 * <p>支付宝二维码</p>
	 * @author:  denghf
	 * @param:   @param alipayThingUrl
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setAlipayThingUrl(String alipayThingUrl) {
		this.alipayThingUrl = alipayThingUrl;
	}
	
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public Long getWechatId() {
		return wechatId;
	}
	
	/**
	 * <p>支付信息记录id</p>
	 * @author:  denghf
	 * @param:   @param wechatId
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}
	
	
	/**
	 * <p>微信账号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getWechatAccount() {
		return wechatAccount;
	}
	
	/**
	 * <p>微信账号</p>
	 * @author:  denghf
	 * @param:   @param wechatAccount
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}
	
	
	/**
	 * <p>微信二维码</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public String getWechatThingUrl() {
		return wechatThingUrl;
	}
	
	/**
	 * <p>微信二维码</p>
	 * @author:  denghf
	 * @param:   @param wechatThingUrl
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setWechatThingUrl(String wechatThingUrl) {
		this.wechatThingUrl = wechatThingUrl;
	}
	
	
	/**
	 * <p>数量min</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public BigDecimal getCoinNumMin() {
		return coinNumMin;
	}
	
	/**
	 * <p>数量min</p>
	 * @author:  denghf
	 * @param:   @param coinNumMin
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setCoinNumMin(BigDecimal coinNumMin) {
		this.coinNumMin = coinNumMin;
	}
	
	
	/**
	 * <p>数量max</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-10-29 13:36:05    
	 */
	public BigDecimal getCoinNumMax() {
		return coinNumMax;
	}
	
	/**
	 * <p>数量max</p>
	 * @author:  denghf
	 * @param:   @param coinNumMax
	 * @return:  void 
	 * @Date :   2018-10-29 13:36:05   
	 */
	public void setCoinNumMax(BigDecimal coinNumMax) {
		this.coinNumMax = coinNumMax;
	}
	
	

}

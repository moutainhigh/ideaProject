/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-22 11:39:46 
 */
package hry.otc.remote.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * <p> ReleaseAdvertisement </p>
 * @author:         denghf
 * @Date :          2018-06-22 11:39:46  
 */
@ApiModel(value = "OTC广告实体类")
public class ReleaseAdvertisementRemote implements Serializable{
	
	
	private Long id;  //id

	@ApiModelProperty(value = "用户id")
	private Long customerId;

	@ApiModelProperty(value = "虚拟账户id")
	private Long accountId;

	@ApiModelProperty(value = "币种名称")
	private String coinName;

	@ApiModelProperty(value = "币种代码")
	private String coinCode;

	@ApiModelProperty(value = "交易方式(1在线购买,2在线出售,3本地购买)")
	private Integer transactionMode;

	@ApiModelProperty(value = "国籍")
	private String nationality;

	@ApiModelProperty(value = "固定价格是否开启 0否 1是")
	private Integer isFixed;

	@ApiModelProperty(value = "交易金额")
	private BigDecimal tradeMoney;

	@ApiModelProperty(value = "溢价")
	private BigDecimal premium;

	@ApiModelProperty(value = "付款期限")
	private String paymentTerm;

	@ApiModelProperty(value = "交易类型(1银行转账,2支付宝,3微信支付)")
	private String payType;

	@ApiModelProperty(value = "最低交易金额")
	private BigDecimal tradeMoneyMix;

	@ApiModelProperty(value = "最高交易金额")
	private BigDecimal tradeMoneyMax;

	@ApiModelProperty(value = "备注")
	private String remark;

	private Integer isAutomatic;

	@ApiModelProperty(value = "自动回复内容")
	private String automaticReply;

	@ApiModelProperty(value = "是否启用安全选项 0否 1是")
	private Integer isSecurity;

	@ApiModelProperty(value = "是否启用仅限受信任的交易者 0否 1是")
	private Integer isBeTrusted;

	@ApiModelProperty(value = "交易次数")
	private Integer transactionNum;

	@ApiModelProperty(value = "上传的资料")
	private String payTypeRemake;

	@ApiModelProperty(value = "广告状态 0关闭 1开启")
	private Integer status;

	@ApiModelProperty(value = "广告状态为0有效，1作废")
	private Integer state;

	@ApiModelProperty(value = "支付信息记录id")
	private Long bankId;

	@ApiModelProperty(value = "银行卡号")
	private String bankNumber;

	@ApiModelProperty(value = "支付信息记录id")
	private Long alipayId;

	@ApiModelProperty(value = "支付宝账号")
	private String alipayAccount;

	@ApiModelProperty(value = "支付宝二维码")
	private String alipayThingUrl;

	@ApiModelProperty(value = "支付信息记录id")
	private Long wechatId;

	@ApiModelProperty(value = "微信账号")
	private String wechatAccount;

	@ApiModelProperty(value = "微信二维码")
	private String wechatThingUrl;

	private String username;

	@ApiModelProperty(value = "付款方式汉字")
	private String payTypeString;

	private String created;

	@ApiModelProperty(value = "折算后币的数量 - 最小值")
	private BigDecimal coinNumMin;

	@ApiModelProperty(value = "折算后币的数量 - 最大值")
	private BigDecimal coinNumMax;

	@ApiModelProperty(value = "初始广告最小数量记录")
	private BigDecimal initialCoinNumMin;

	@ApiModelProperty(value = "初始广告最大数量记录")
	private BigDecimal initialCoinNumMax;

	@ApiModelProperty(value = "取消订单时的状态,1 全部吃单之后已成交 2 吃单了部分然后取消，显示部分成交已取消 3未成交已取消")
	private Integer advStatus;

	@ApiModelProperty(value = "完成率")
	private BigDecimal completionRate;

	public BigDecimal getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(BigDecimal completionRate) {
		this.completionRate = completionRate;
	}

	public BigDecimal getCoinNumMin() {
		return coinNumMin;
	}

	public void setCoinNumMin(BigDecimal coinNumMin) {
		this.coinNumMin = coinNumMin;
	}

	public BigDecimal getCoinNumMax() {
		return coinNumMax;
	}

	public void setCoinNumMax(BigDecimal coinNumMax) {
		this.coinNumMax = coinNumMax;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getPayTypeString() {
		return payTypeString;
	}

	public void setPayTypeString(String payTypeString) {
		this.payTypeString = payTypeString;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public Long getAlipayId() {
		return alipayId;
	}

	public void setAlipayId(Long alipayId) {
		this.alipayId = alipayId;
	}

	public Long getWechatId() {
		return wechatId;
	}

	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}

	/**
	 * <p>币种名称</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getCoinName() {
		return coinName;
	}
	
	/**
	 * <p>币种名称</p>
	 * @author:  denghf
	 * @param:   @param coinName
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  denghf
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>交易方式(1在线购买,2在线出售,3本地购买)</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public Integer getTransactionMode() {
		return transactionMode;
	}
	
	/**
	 * <p>交易方式(1在线购买,2在线出售,3本地购买)</p>
	 * @author:  denghf
	 * @param:   @param transactionMode
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setTransactionMode(Integer transactionMode) {
		this.transactionMode = transactionMode;
	}
	
	
	/**
	 * <p>国籍</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getNationality() {
		return nationality;
	}
	
	/**
	 * <p>国籍</p>
	 * @author:  denghf
	 * @param:   @param nationality
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
	/**
	 * <p>固定价格是否开启 0否 1是</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public Integer getIsFixed() {
		return isFixed;
	}
	
	/**
	 * <p>固定价格是否开启 0否 1是</p>
	 * @author:  denghf
	 * @param:   @param isFixed
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setIsFixed(Integer isFixed) {
		this.isFixed = isFixed;
	}
	
	
	/**
	 * <p>交易金额</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}
	
	/**
	 * <p>交易金额</p>
	 * @author:  denghf
	 * @param:   @param tradeMoney
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	
	
	/**
	 * <p>溢价</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public BigDecimal getPremium() {
		return premium;
	}
	
	/**
	 * <p>溢价</p>
	 * @author:  denghf
	 * @param:   @param premium
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	
	
	/**
	 * <p>付款期限</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getPaymentTerm() {
		return paymentTerm;
	}
	
	/**
	 * <p>付款期限</p>
	 * @author:  denghf
	 * @param:   @param paymentTerm
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	
	
	/**
	 * <p>交易类型(1银行转账,2支付宝,3微信支付)</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getPayType() {
		return payType;
	}
	
	/**
	 * <p>交易类型(1银行转账,2支付宝,3微信支付)</p>
	 * @author:  denghf
	 * @param:   @param payType
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	
	/**
	 * <p>最低交易金额</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public BigDecimal getTradeMoneyMix() {
		return tradeMoneyMix;
	}
	
	/**
	 * <p>最低交易金额</p>
	 * @author:  denghf
	 * @param:   @param tradeMoneyMix
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setTradeMoneyMix(BigDecimal tradeMoneyMix) {
		this.tradeMoneyMix = tradeMoneyMix;
	}
	
	
	/**
	 * <p>最高交易金额</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public BigDecimal getTradeMoneyMax() {
		return tradeMoneyMax;
	}
	
	/**
	 * <p>最高交易金额</p>
	 * @author:  denghf
	 * @param:   @param tradeMoneyMax
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setTradeMoneyMax(BigDecimal tradeMoneyMax) {
		this.tradeMoneyMax = tradeMoneyMax;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  denghf
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>固定价格是否开启 0否 1是</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public Integer getIsAutomatic() {
		return isAutomatic;
	}
	
	/**
	 * <p>固定价格是否开启 0否 1是</p>
	 * @author:  denghf
	 * @param:   @param isAutomatic
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setIsAutomatic(Integer isAutomatic) {
		this.isAutomatic = isAutomatic;
	}
	
	
	/**
	 * <p>自动回复内容</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getAutomaticReply() {
		return automaticReply;
	}
	
	/**
	 * <p>自动回复内容</p>
	 * @author:  denghf
	 * @param:   @param automaticReply
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setAutomaticReply(String automaticReply) {
		this.automaticReply = automaticReply;
	}
	
	
	/**
	 * <p>是否启用安全选项 0否 1是</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public Integer getIsSecurity() {
		return isSecurity;
	}
	
	/**
	 * <p>是否启用安全选项 0否 1是</p>
	 * @author:  denghf
	 * @param:   @param isSecurity
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setIsSecurity(Integer isSecurity) {
		this.isSecurity = isSecurity;
	}
	
	
	/**
	 * <p>是否启用仅限受信任的交易者 0否 1是</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public Integer getIsBeTrusted() {
		return isBeTrusted;
	}
	
	/**
	 * <p>是否启用仅限受信任的交易者 0否 1是</p>
	 * @author:  denghf
	 * @param:   @param isBeTrusted
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setIsBeTrusted(Integer isBeTrusted) {
		this.isBeTrusted = isBeTrusted;
	}
	
	
	/**
	 * <p>交易次数</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public Integer getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>交易次数</p>
	 * @author:  denghf
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setTransactionNum(Integer transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>上传的资料</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getPayTypeRemake() {
		return payTypeRemake;
	}
	
	/**
	 * <p>上传的资料</p>
	 * @author:  denghf
	 * @param:   @param payTypeRemake
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setPayTypeRemake(String payTypeRemake) {
		this.payTypeRemake = payTypeRemake;
	}
	
	
	/**
	 * <p>广告状态 0关闭 1开启</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>广告状态 0关闭 1开启</p>
	 * @author:  denghf
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>广告状态为0有效，1作废</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>广告状态为0有效，1作废</p>
	 * @author:  denghf
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	
	
	/**
	 * <p>银行卡号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getBankNumber() {
		return bankNumber;
	}
	
	/**
	 * <p>银行卡号</p>
	 * @author:  denghf
	 * @param:   @param bankNumber
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	
	
	
	
	/**
	 * <p>支付宝账号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getAlipayAccount() {
		return alipayAccount;
	}
	
	/**
	 * <p>支付宝账号</p>
	 * @author:  denghf
	 * @param:   @param alipayAccount
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	
	
	/**
	 * <p>支付宝二维码</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getAlipayThingUrl() {
		return alipayThingUrl;
	}
	
	/**
	 * <p>支付宝二维码</p>
	 * @author:  denghf
	 * @param:   @param alipayThingUrl
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setAlipayThingUrl(String alipayThingUrl) {
		this.alipayThingUrl = alipayThingUrl;
	}
	
	
	/**
	 * <p>微信账号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getWechatAccount() {
		return wechatAccount;
	}
	
	/**
	 * <p>微信账号</p>
	 * @author:  denghf
	 * @param:   @param wechatAccount
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}
	
	
	/**
	 * <p>微信二维码</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-22 11:39:46    
	 */
	public String getWechatThingUrl() {
		return wechatThingUrl;
	}
	
	/**
	 * <p>微信二维码</p>
	 * @author:  denghf
	 * @param:   @param wechatThingUrl
	 * @return:  void 
	 * @Date :   2018-06-22 11:39:46   
	 */
	public void setWechatThingUrl(String wechatThingUrl) {
		this.wechatThingUrl = wechatThingUrl;
	}

	public BigDecimal getInitialCoinNumMin () {
		return initialCoinNumMin;
	}

	public void setInitialCoinNumMin (BigDecimal initialCoinNumMin) {
		this.initialCoinNumMin = initialCoinNumMin;
	}

	public BigDecimal getInitialCoinNumMax () {
		return initialCoinNumMax;
	}

	public void setInitialCoinNumMax (BigDecimal initialCoinNumMax) {
		this.initialCoinNumMax = initialCoinNumMax;
	}

	public Integer getAdvStatus () {
		return advStatus;
	}

	public void setAdvStatus (Integer advStatus) {
		this.advStatus = advStatus;
	}
}

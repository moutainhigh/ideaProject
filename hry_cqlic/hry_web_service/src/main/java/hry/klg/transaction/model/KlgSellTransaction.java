/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-18 14:58:49 
 */
package hry.klg.transaction.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * <p> KlgSellTransaction </p>
 * @author:         yaozhuo
 * @Date :          2019-04-18 14:58:49  
 */
@Table(name="klg_sell_transaction")
public class KlgSellTransaction extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "customerGrade")
	private Integer customerGrade;  //排单时用户等级
	
	@Column(name= "transactionNum")
	private String transactionNum;  //流水号
	
	@Column(name= "accountId")
	private Long accountId;  //数字货币账户id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种
	
	@Column(name= "usdtMoney")
	private BigDecimal usdtMoney;  //卖出后获取瓜分后总USDT
	
	@Column(name= "smeMoney")
	private BigDecimal smeMoney;  //卖出平台币金额
	
	@Column(name= "candySmeMoney")
	private BigDecimal candySmeMoney;  //当前应获糖果总金额(SME）
	
	@Column(name= "smeusdtRate")
	private BigDecimal smeusdtRate;  //排队时SME-USDT汇率
	
	@Column(name= "status")
	private Integer status;  //1排队中 2排队成功 3待收款 4已完成
	
	@Column(name= "userId")
	private Long userId;  //操作人id
	
	@Column(name= "saasId")
	private String saasId;  //saasId
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "timeStamp")
	private Long timeStamp;  //排队开始时间戳
	
	@Column(name= "oneselfRate")
	private BigDecimal oneselfRate;  //本人获取糖果比例
	
	@Column(name= "platformRate")
	private BigDecimal platformRate;  //平台获取糖果比例

	@Column(name= "seePointRate")
	private BigDecimal seePointRate;  //见点糖果比例
	
	@Column(name= "gradationRate")
	private BigDecimal gradationRate;  //级差糖果比例
	
	@Column(name= "prizeRate")
	private BigDecimal prizeRate;  //周奖糖果比例


	@Column(name= "sellDay")
	private Integer sellDay;  //卖出天数
	@Column(name= "sellEndTime")
	private Date sellEndTime;  //卖出时间

	public Integer getSellDay() {
		return sellDay;
	}

	public void setSellDay(Integer sellDay) {
		this.sellDay = sellDay;
	}

	public Date getSellEndTime() {
		return sellEndTime;
	}

	public void setSellEndTime(Date sellEndTime) {
		this.sellEndTime = sellEndTime;
	}

	@Transient
	private String mobilePhone;  //

	@Transient
	private String email;  //

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
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozhuo
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>排单时用户等级</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public Integer getCustomerGrade() {
		return customerGrade;
	}
	
	/**
	 * <p>排单时用户等级</p>
	 * @author:  yaozhuo
	 * @param:   @param customerGrade
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setCustomerGrade(Integer customerGrade) {
		this.customerGrade = customerGrade;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozhuo
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>数字货币账户id</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>数字货币账户id</p>
	 * @author:  yaozhuo
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  yaozhuo
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>卖出后获取瓜分后总USDT</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public BigDecimal getUsdtMoney() {
		return usdtMoney;
	}
	
	/**
	 * <p>卖出后获取瓜分后总USDT</p>
	 * @author:  yaozhuo
	 * @param:   @param usdtMoney
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setUsdtMoney(BigDecimal usdtMoney) {
		this.usdtMoney = usdtMoney;
	}
	
	
	/**
	 * <p>卖出平台币金额</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public BigDecimal getSmeMoney() {
		return smeMoney;
	}
	
	/**
	 * <p>卖出平台币金额</p>
	 * @author:  yaozhuo
	 * @param:   @param smeMoney
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setSmeMoney(BigDecimal smeMoney) {
		this.smeMoney = smeMoney;
	}
	
	
	/**
	 * <p>当前应获糖果总金额(SME）</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public BigDecimal getCandySmeMoney() {
		return candySmeMoney;
	}
	
	/**
	 * <p>当前应获糖果总金额(SME）</p>
	 * @author:  yaozhuo
	 * @param:   @param candySmeMoney
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setCandySmeMoney(BigDecimal candySmeMoney) {
		this.candySmeMoney = candySmeMoney;
	}
	
	
	/**
	 * <p>排队时SME-USDT汇率</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public BigDecimal getSmeusdtRate() {
		return smeusdtRate;
	}
	
	/**
	 * <p>排队时SME-USDT汇率</p>
	 * @author:  yaozhuo
	 * @param:   @param smeusdtRate
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setSmeusdtRate(BigDecimal smeusdtRate) {
		this.smeusdtRate = smeusdtRate;
	}
	
	
	/**
	 * <p>1排队中 2排队成功 3待收款 4已完成</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>1排队中 2排队成功 3待收款 4已完成</p>
	 * @author:  yaozhuo
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>操作人id</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p>操作人id</p>
	 * @author:  yaozhuo
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p>saasId</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p>saasId</p>
	 * @author:  yaozhuo
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>排队开始时间戳</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public Long getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * <p>排队开始时间戳</p>
	 * @author:  yaozhuo
	 * @param:   @param timeStamp
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	/**
	 * <p>本人获取糖果比例</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public BigDecimal getOneselfRate() {
		return oneselfRate;
	}
	
	/**
	 * <p>本人获取糖果比例</p>
	 * @author:  yaozhuo
	 * @param:   @param oneselfRate
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setOneselfRate(BigDecimal oneselfRate) {
		this.oneselfRate = oneselfRate;
	}
	
	
	/**
	 * <p>平台获取糖果比例</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public BigDecimal getPlatformRate() {
		return platformRate;
	}
	
	/**
	 * <p>平台获取糖果比例</p>
	 * @author:  yaozhuo
	 * @param:   @param platformRate
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setPlatformRate(BigDecimal platformRate) {
		this.platformRate = platformRate;
	}
	
	
	/**
	 * <p>见点糖果比例</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public BigDecimal getSeePointRate() {
		return seePointRate;
	}
	
	/**
	 * <p>见点糖果比例</p>
	 * @author:  yaozhuo
	 * @param:   @param seePointRate
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setSeePointRate(BigDecimal seePointRate) {
		this.seePointRate = seePointRate;
	}
	
	
	/**
	 * <p>级差糖果比例</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public BigDecimal getGradationRate() {
		return gradationRate;
	}
	
	/**
	 * <p>级差糖果比例</p>
	 * @author:  yaozhuo
	 * @param:   @param gradationRate
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setGradationRate(BigDecimal gradationRate) {
		this.gradationRate = gradationRate;
	}
	
	
	/**
	 * <p>周奖糖果比例</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 14:58:49    
	 */
	public BigDecimal getPrizeRate() {
		return prizeRate;
	}
	
	/**
	 * <p>周奖糖果比例</p>
	 * @author:  yaozhuo
	 * @param:   @param prizeRate
	 * @return:  void 
	 * @Date :   2019-04-18 14:58:49   
	 */
	public void setPrizeRate(BigDecimal prizeRate) {
		this.prizeRate = prizeRate;
	}
	
	

}

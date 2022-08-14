/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zenghao
 * @version:     V1.0 
 * @Date:        2016-11-21 15:48:49 
 */
package hry.exchange.subscription.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> ExSubscriptionPlan </p>
 * @author:         zenghao
 * @Date :          2016-11-21 15:48:49  
 */
@Table(name="ex_subscription_plan")
public class ExSubscriptionPlan extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种名称，产品代码
	
	@Column(name= "period")
	private Integer period;  //认购期数
	
	@Column(name= "startTime")
	private Date startTime;  //开始认购时间
	
	@Column(name= "openNumber")
	private BigDecimal openNumber;  //开放认购数量
	
	@Column(name= "minimumNumber")
	private BigDecimal minimumNumber;  //最低认购数量，金额
	
	@Column(name= "maximumNumber")
	private BigDecimal maximumNumber;  //最高认购数量，金额
	
	@Column(name= "initialPrice")
	private BigDecimal initialPrice;  //认购初始价
	
	@Column(name= "highestPrice")
	private BigDecimal highestPrice;  //认购最高价
	
	@Column(name= "state")
	private Integer state;  //状态：0未发布，1已发布，2已开始认购，3认购完成，4已删除状态为2，3时，不能删除
	
	@Column(name= "repurchaseFee")
	private BigDecimal repurchaseFee;  //回购手续费
	
	@Column(name= "priceBase")
	private BigDecimal priceBase;  //涨价基数
	
	@Column(name= "rose")
	private BigDecimal rose;  //涨幅
	
	@Column(name= "sratioOne")
	private BigDecimal sratioOne;  //认购返佣比例一级
	
	@Column(name= "sratioTwo")
	private BigDecimal sratioTwo;  //认购返佣比例二级
	
	@Column(name= "sratioTheree")
	private BigDecimal sratioTheree;  //认购返佣比例三级
	
	@Column(name= "ftratioOne")
	private BigDecimal ftratioOne;  //集市交易返佣比一级
	
	@Column(name= "ftratioTwo")
	private BigDecimal ftratioTwo;  //集市交易返佣比二级
	
	@Column(name= "ftratioTheree")
	private BigDecimal ftratioTheree;  //集市交易返佣比三级
	
	@Column(name= "purchaseNumber")
	private BigDecimal purchaseNumber;  //已认购数量
	
	@Column(name= "surplusNumber")
	private BigDecimal surplusNumber;  //剩余可认购个数
	
	@Column(name= "transactionPrice")
	private BigDecimal transactionPrice;  //交易最高价
	
	@Column(name= "startInitialPrice")
	private BigDecimal startInitialPrice;  //交易初始价
	
	@Column(name= "repurchasePeriod")
	private Integer repurchasePeriod;  //回购有效期
	
	@Column(name= "coinName")
	private String coinName;  //币种名称
	
	//不与数据库映射
	@Transient
	private BigDecimal currentPrice;//当前认购价;
	@Transient
	private BigDecimal apphotMoney;//账户可用余额;
	@Transient
	private BigDecimal digitalMoney;//当前持币个数;
	@Transient
	private BigDecimal buySubscribedNum;//用户当期已认购个数，金额
	@Transient
	private BigDecimal totalNum;//最多可认购多少个	
	@Transient
	private BigDecimal progress;//认购进度
	/**
	 * <p>id</p>
	 * @author:  zenghao
	 * @return:  Long 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  zenghao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>币种名称，产品代码</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种名称，产品代码</p>
	 * @author:  zenghao
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>认购期数</p>
	 * @author:  zenghao
	 * @return:  Integer 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public Integer getPeriod() {
		return period;
	}
	
	/**
	 * <p>认购期数</p>
	 * @author:  zenghao
	 * @param:   @param period
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	
	
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * <p>开放认购数量</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getOpenNumber() {
		return openNumber;
	}
	
	/**
	 * <p>开放认购数量</p>
	 * @author:  zenghao
	 * @param:   @param openNumber
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setOpenNumber(BigDecimal openNumber) {
		this.openNumber = openNumber;
	}
	
	
	/**
	 * <p>最低认购数量</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getMinimumNumber() {
		return minimumNumber;
	}
	
	/**
	 * <p>最低认购数量</p>
	 * @author:  zenghao
	 * @param:   @param minimumNumber
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setMinimumNumber(BigDecimal minimumNumber) {
		this.minimumNumber = minimumNumber;
	}
	
	
	/**
	 * <p>最高认购数量</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getMaximumNumber() {
		return maximumNumber;
	}
	
	/**
	 * <p>最高认购数量</p>
	 * @author:  zenghao
	 * @param:   @param maximumNumber
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setMaximumNumber(BigDecimal maximumNumber) {
		this.maximumNumber = maximumNumber;
	}
	
	
	/**
	 * <p>认购初始价</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getInitialPrice() {
		return initialPrice;
	}
	
	/**
	 * <p>认购初始价</p>
	 * @author:  zenghao
	 * @param:   @param initialPrice
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setInitialPrice(BigDecimal initialPrice) {
		this.initialPrice = initialPrice;
	}
	
	
	/**
	 * <p>认购最高价</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getHighestPrice() {
		return highestPrice;
	}
	
	/**
	 * <p>认购最高价</p>
	 * @author:  zenghao
	 * @param:   @param highestPrice
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}
	
	
	/**
	 * <p>状态：0未发布，1已发布，2已开始认购，3认购完成，4已删除状态为2，3时，不能删除</p>
	 * @author:  zenghao
	 * @return:  Integer 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>状态：0未发布，1已发布，2已开始认购，3认购完成，4已删除状态为2，3时，不能删除</p>
	 * @author:  zenghao
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>回购手续费</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getRepurchaseFee() {
		return repurchaseFee;
	}
	
	/**
	 * <p>回购手续费</p>
	 * @author:  zenghao
	 * @param:   @param repurchaseFee
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setRepurchaseFee(BigDecimal repurchaseFee) {
		this.repurchaseFee = repurchaseFee;
	}
	
	
	/**
	 * <p>涨价基数</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getPriceBase() {
		return priceBase;
	}
	
	/**
	 * <p>涨价基数</p>
	 * @author:  zenghao
	 * @param:   @param priceBase
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setPriceBase(BigDecimal priceBase) {
		this.priceBase = priceBase;
	}
	
	
	/**
	 * <p>rose</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getRose() {
		return rose;
	}
	
	/**
	 * <p>rose</p>
	 * @author:  zenghao
	 * @param:   @param rose
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setRose(BigDecimal rose) {
		this.rose = rose;
	}
	
	
	/**
	 * <p>认购返佣比例一级</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getSratioOne() {
		return sratioOne;
	}
	
	/**
	 * <p>认购返佣比例一级</p>
	 * @author:  zenghao
	 * @param:   @param sratioOne
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setSratioOne(BigDecimal sratioOne) {
		this.sratioOne = sratioOne;
	}
	
	
	/**
	 * <p>认购返佣比例二级</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getSratioTwo() {
		return sratioTwo;
	}
	
	/**
	 * <p>认购返佣比例二级</p>
	 * @author:  zenghao
	 * @param:   @param sratioTwo
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setSratioTwo(BigDecimal sratioTwo) {
		this.sratioTwo = sratioTwo;
	}
	
	
	/**
	 * <p>认购返佣比例三级</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getSratioTheree() {
		return sratioTheree;
	}
	
	/**
	 * <p>认购返佣比例三级</p>
	 * @author:  zenghao
	 * @param:   @param sratioTheree
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setSratioTheree(BigDecimal sratioTheree) {
		this.sratioTheree = sratioTheree;
	}
	
	
	/**
	 * <p>集市交易返佣比一级</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getFtratioOne() {
		return ftratioOne;
	}
	
	/**
	 * <p>集市交易返佣比一级</p>
	 * @author:  zenghao
	 * @param:   @param ftratioOne
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setFtratioOne(BigDecimal ftratioOne) {
		this.ftratioOne = ftratioOne;
	}
	
	
	/**
	 * <p>集市交易返佣比二级</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getFtratioTwo() {
		return ftratioTwo;
	}
	
	/**
	 * <p>集市交易返佣比二级</p>
	 * @author:  zenghao
	 * @param:   @param ftratioTwo
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setFtratioTwo(BigDecimal ftratioTwo) {
		this.ftratioTwo = ftratioTwo;
	}
	
	
	/**
	 * <p>集市交易返佣比三级</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getFtratioTheree() {
		return ftratioTheree;
	}
	
	/**
	 * <p>集市交易返佣比三级</p>
	 * @author:  zenghao
	 * @param:   @param ftratioTheree
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setFtratioTheree(BigDecimal ftratioTheree) {
		this.ftratioTheree = ftratioTheree;
	}
	
	
	/**
	 * <p>已认购数量</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getPurchaseNumber() {
		return purchaseNumber;
	}
	
	/**
	 * <p>已认购数量</p>
	 * @author:  zenghao
	 * @param:   @param purchaseNumber
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setPurchaseNumber(BigDecimal purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	
	
	/**
	 * <p>剩余可认购个数</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getSurplusNumber() {
		return surplusNumber;
	}
	
	/**
	 * <p>剩余可认购个数</p>
	 * @author:  zenghao
	 * @param:   @param surplusNumber
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setSurplusNumber(BigDecimal surplusNumber) {
		this.surplusNumber = surplusNumber;
	}
	
	
	/**
	 * <p>交易最高价</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-21 15:48:49    
	 */
	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}
	
	/**
	 * <p>交易最高价</p>
	 * @author:  zenghao
	 * @param:   @param transactionPrice
	 * @return:  void 
	 * @Date :   2016-11-21 15:48:49   
	 */
	public void setTransactionPrice(BigDecimal transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	public BigDecimal getStartInitialPrice() {
		return startInitialPrice;
	}

	public void setStartInitialPrice(BigDecimal startInitialPrice) {
		this.startInitialPrice = startInitialPrice;
	}



	public Integer getRepurchasePeriod() {
		return repurchasePeriod;
	}

	public void setRepurchasePeriod(Integer repurchasePeriod) {
		this.repurchasePeriod = repurchasePeriod;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public BigDecimal getApphotMoney() {
		return apphotMoney;
	}

	public void setApphotMoney(BigDecimal apphotMoney) {
		this.apphotMoney = apphotMoney;
	}

	public BigDecimal getDigitalMoney() {
		return digitalMoney;
	}

	public void setDigitalMoney(BigDecimal digitalMoney) {
		this.digitalMoney = digitalMoney;
	}

	public BigDecimal getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}

	

	public BigDecimal getBuySubscribedNum() {
		return buySubscribedNum;
	}

	public void setBuySubscribedNum(BigDecimal buySubscribedNum) {
		this.buySubscribedNum = buySubscribedNum;
	}

	public BigDecimal getProgress() {
		return progress;
	}

	public void setProgress(BigDecimal progress) {
		this.progress = progress;
	}

	

}

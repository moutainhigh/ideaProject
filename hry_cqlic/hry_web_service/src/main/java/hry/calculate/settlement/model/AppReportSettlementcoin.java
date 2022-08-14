/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月3日 下午2:27:19
 */
package hry.calculate.settlement.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;

/**
 * @author Wu shuiming
 * @date 2016年9月3日 下午2:27:19
 */
@SuppressWarnings("serial")
@Table(name="app_report_settlementcoin")
public class AppReportSettlementcoin extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "customerId")
	private Long customerId;
	// 客户类型customerType甲类账户1(普通的，默认)，乙类账号2，丙类账户3
	@Column(name = "customerType")
	private Integer customerType;
	// 账户id
	@Column(name = "accountId")
	private Long accountId;

	// 币的代码 (股票的代码) 不能为空
	@Column(name = "coinCode")
	private String coinCode;
	//用户名
	@Column(name="userName")
	private String userName;   
	// 客户编号
	@Column(name = "userCode")
	private String userCode;
	// 注册姓名
	@Column(name = "trueName")
	private String trueName;
	// 确认状态  -1 刚生成状态0，未确认状态，1，确认状态
	@Column(name = "stutus")
	private Integer stutus;
	@Column(name = "settleDate")
	private Date  settleDate;
	
	
	@Column(name = "startSettleDate")
	private Date  startSettleDate;
	@Column(name = "endSettleDate")
	private Date  endSettleDate;
	// 转入量
	@Column(name = "inCoinCount")
	private BigDecimal inCoinCount;
	// 转出量
	@Column(name = "outCoinCount")
	private BigDecimal outCoinCount;
	//充值手续费
	@Column(name = "inCoinFee")
	private BigDecimal inCoinFee;
	//提现手续费
	@Column(name = "outCoinFee")
	private BigDecimal outCoinFee;
	
	//买成交量
	@Column(name = "buyTransactionCount")
	private BigDecimal buyTransactionCount;
	//卖成交量
	@Column(name = "sellTransactionCount")
	private BigDecimal sellTransactionCount;
	//期初持仓
	@Column(name = "beginCoinCount")
	private BigDecimal beginCoinCount;
	//期末持仓
	@Column(name = "endCoinCount")
	private BigDecimal endCoinCount;
	
	//期初资金
	@Column(name = "beginMoney")
	private BigDecimal beginMoney;
	//期末资金
	@Column(name = "endMoney")
	private BigDecimal endMoney;
	//融资金额
	@Column(name = "lendMoney")
	private BigDecimal lendMoney;
	//偿还的包括利息
	@Column(name = "repaylendMoney")
	private BigDecimal repaylendMoney;
	
	
	
	
	//期初杠杆资金
	@Column(name = "beginLendMoney")
	private BigDecimal beginLendMoney;
	//借入杠杆资金
	@Column(name = "daylendMoney")
	private BigDecimal daylendMoney;
	//费率
	@Column(name = "lendRate")
	private BigDecimal lendRate;
	//已还本金/杠杆
	@Column(name = "repayLendMoney")
	private BigDecimal repayLendMoney;
	//已还利息
	@Column(name = "repayInterestMoney")
	private BigDecimal repayInterestMoney;
	
	//期末杠杆资金
	@Column(name = "endLendMoney")
	private BigDecimal endLendMoney;
	//未还利息
	@Column(name = "notInterestMoney")
	private BigDecimal notInterestMoney;
	//交易账户类型 cny,usd
	@Column(name = "currencyType")
	private String currencyType;
	//站点类别 en ,cn
	@Column(name = "website")
	private String website;
		
	
	
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getBeginLendMoney() {
		return beginLendMoney;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setBeginLendMoney(BigDecimal beginLendMoney) {
		this.beginLendMoney = beginLendMoney;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getDaylendMoney() {
		return daylendMoney;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setDaylendMoney(BigDecimal daylendMoney) {
		this.daylendMoney = daylendMoney;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getLendRate() {
		return lendRate;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setLendRate(BigDecimal lendRate) {
		this.lendRate = lendRate;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRepayLendMoney() {
		return repayLendMoney;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRepayLendMoney(BigDecimal repayLendMoney) {
		this.repayLendMoney = repayLendMoney;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRepayInterestMoney() {
		return repayInterestMoney;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRepayInterestMoney(BigDecimal repayInterestMoney) {
		this.repayInterestMoney = repayInterestMoney;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getEndLendMoney() {
		return endLendMoney;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setEndLendMoney(BigDecimal endLendMoney) {
		this.endLendMoney = endLendMoney;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getNotInterestMoney() {
		return notInterestMoney;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setNotInterestMoney(BigDecimal notInterestMoney) {
		this.notInterestMoney = notInterestMoney;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getLendMoney() {
		return lendMoney;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRepaylendMoney() {
		return repaylendMoney;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRepaylendMoney(BigDecimal repaylendMoney) {
		this.repaylendMoney = repaylendMoney;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getInCoinFee() {
		return inCoinFee;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setInCoinFee(BigDecimal inCoinFee) {
		this.inCoinFee = inCoinFee;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getOutCoinFee() {
		return outCoinFee;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setOutCoinFee(BigDecimal outCoinFee) {
		this.outCoinFee = outCoinFee;
	}



	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getCustomerType() {
		return customerType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getStartSettleDate() {
		return startSettleDate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setStartSettleDate(Date startSettleDate) {
		this.startSettleDate = startSettleDate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getEndSettleDate() {
		return endSettleDate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setEndSettleDate(Date endSettleDate) {
		this.endSettleDate = endSettleDate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getAccountId() {
		return accountId;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getWebsite() {
		return website;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCoinCode() {
		return coinCode;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getSettleDate() {
		return settleDate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getId() {
		return id;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getCustomerId() {
		return customerId;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserName() {
		return userName;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserCode() {
		return userCode;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTrueName() {
		return trueName;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getStutus() {
		return stutus;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setStutus(Integer stutus) {
		this.stutus = stutus;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getInCoinCount() {
		return inCoinCount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setInCoinCount(BigDecimal inCoinCount) {
		this.inCoinCount = inCoinCount;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getOutCoinCount() {
		return outCoinCount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setOutCoinCount(BigDecimal outCoinCount) {
		this.outCoinCount = outCoinCount;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getBuyTransactionCount() {
		return buyTransactionCount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setBuyTransactionCount(BigDecimal buyTransactionCount) {
		this.buyTransactionCount = buyTransactionCount;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getSellTransactionCount() {
		return sellTransactionCount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setSellTransactionCount(BigDecimal sellTransactionCount) {
		this.sellTransactionCount = sellTransactionCount;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getBeginCoinCount() {
		return beginCoinCount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setBeginCoinCount(BigDecimal beginCoinCount) {
		this.beginCoinCount = beginCoinCount;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getEndCoinCount() {
		return endCoinCount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setEndCoinCount(BigDecimal endCoinCount) {
		this.endCoinCount = endCoinCount;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getBeginMoney() {
		return beginMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setBeginMoney(BigDecimal beginMoney) {
		this.beginMoney = beginMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getEndMoney() {
		return endMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setEndMoney(BigDecimal endMoney) {
		this.endMoney = endMoney;
	}
	
	
	
	


	

}







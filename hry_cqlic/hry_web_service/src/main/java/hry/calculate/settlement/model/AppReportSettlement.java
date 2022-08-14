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
@Table(name="app_report_settlement")
public class AppReportSettlement extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	@Column(name = "customerId")
	private Long customerId;
	// 客户类型customerType甲类账户1(普通的，默认)，乙类账号2，丙类账户3
	@Column(name = "customerType")
	private Integer customerType;
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
    // 充值额
	@Column(name = "rechargeMoney")
	private BigDecimal rechargeMoney;
	// 提现额
	@Column(name = "withdrawMoney")
	private BigDecimal withdrawMoney;
	// 买成交额
	@Column(name = "buyTransactionMoney")
	private BigDecimal buyTransactionMoney;
	//卖成交额
	@Column(name = "sellTransactionMoney")
	private BigDecimal sellTransactionMoney;
	//充值手续费
	@Column(name = "rechargeFee")
	private BigDecimal rechargeFee;
	//提现手续费
	@Column(name = "withdrawFee")
	private BigDecimal withdrawFee;
	//交易手续费
	@Column(name = "transactionFee")
	private BigDecimal transactionFee;
	//期初资金
	@Column(name = "beginMoney")
	private BigDecimal beginMoney;
	//期末资金
	@Column(name = "endMoney")
	private BigDecimal endMoney;
	
	//融资金额，
	@Column(name = "lendMoney")
	private BigDecimal lendMoney;
	//偿还的包括利息
	@Column(name = "repaylendMoney")
	private BigDecimal repaylendMoney;
	
	//期末资产
	@Column(name = "endNetAsset")
	private BigDecimal endNetAsset;
	//期初净资产
	@Column(name = "beginNetAsset")
	private BigDecimal beginNetAsset;
	
	//盈亏
	@Column(name = "profitAndLossMoney")
	private BigDecimal profitAndLossMoney;
	
	
	
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
	public BigDecimal getProfitAndLossMoney() {
		return profitAndLossMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setProfitAndLossMoney(BigDecimal profitAndLossMoney) {
		this.profitAndLossMoney = profitAndLossMoney;
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
	public BigDecimal getEndNetAsset() {
		return endNetAsset;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setEndNetAsset(BigDecimal endNetAsset) {
		this.endNetAsset = endNetAsset;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getBeginNetAsset() {
		return beginNetAsset;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setBeginNetAsset(BigDecimal beginNetAsset) {
		this.beginNetAsset = beginNetAsset;
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
	public BigDecimal getRechargeMoney() {
		return rechargeMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getWithdrawMoney() {
		return withdrawMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setWithdrawMoney(BigDecimal withdrawMoney) {
		this.withdrawMoney = withdrawMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getBuyTransactionMoney() {
		return buyTransactionMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setBuyTransactionMoney(BigDecimal buyTransactionMoney) {
		this.buyTransactionMoney = buyTransactionMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getSellTransactionMoney() {
		return sellTransactionMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setSellTransactionMoney(BigDecimal sellTransactionMoney) {
		this.sellTransactionMoney = sellTransactionMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRechargeFee() {
		return rechargeFee;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRechargeFee(BigDecimal rechargeFee) {
		this.rechargeFee = rechargeFee;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getWithdrawFee() {
		return withdrawFee;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setWithdrawFee(BigDecimal withdrawFee) {
		this.withdrawFee = withdrawFee;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTransactionFee() {
		return transactionFee;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTransactionFee(BigDecimal transactionFee) {
		this.transactionFee = transactionFee;
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







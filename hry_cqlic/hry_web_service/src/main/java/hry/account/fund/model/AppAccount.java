/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:17:33
 */
package hry.account.fund.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;
import hry.core.mvc.model.ModelUtil;
import hry.customer.person.model.AppPersonInfo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:17:33 
 */
@Table(name="app_account")
public class AppAccount extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="website")
	private String website;//站点类别默认cn
	
	@Column(name="customerId")
	private Long customerId;   //用户id
	
	@Column(name="userName")
	private String userName;  //用户名
	
	@Column(name="hotMoney")
	private BigDecimal hotMoney; //可用金额
	
	@Column(name="coldMoney")
	private BigDecimal coldMoney; //冻结金额
	
	//=========================金科新加字段=================================
	@Column(name="rewardMoney")
	private BigDecimal rewardMoney; //待返佣金额
	
	@Column(name="hasRewardMoney")
	private BigDecimal hasRewardMoney; //已返佣金额
	//=========================金科新加字段结束=================================
	
	@Version
	private Integer version;  //版本号
	
	@Column(name="accountNum")
	private String accountNum;  //虚拟账号
	
	@Column(name="currencyType")
	private String currencyType;  //币种
	
	@Column(name="status")
	private Integer status;  //账户状态  0不可用  1可用
	// 借款总额
	@Column(name = "lendMoney")
	private BigDecimal lendMoney;
	
	@Transient
	private AppPersonInfo appPersonInfo;
	
	
	@Transient
	private BigDecimal rmbAccountNetAsse;
	
	@Transient
	private BigDecimal rmbLendMoneySum;
	@Transient
	private BigDecimal sumRmbfund;
	@Transient
	private BigDecimal sumCommissionMoney;//累计佣金
	@Transient
	private BigDecimal hotCurrency;//可用币
	@Transient
	private BigDecimal coldCurrency;//冻结币
	
	
	

	public BigDecimal getRewardMoney() {
		return rewardMoney;
	}

	public void setRewardMoney(BigDecimal rewardMoney) {
		this.rewardMoney = rewardMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRmbAccountNetAsse() {
		return rmbAccountNetAsse;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRmbAccountNetAsse(BigDecimal rmbAccountNetAsse) {
		this.rmbAccountNetAsse = rmbAccountNetAsse;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRmbLendMoneySum() {
		return rmbLendMoneySum;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRmbLendMoneySum(BigDecimal rmbLendMoneySum) {
		this.rmbLendMoneySum = rmbLendMoneySum;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getSumRmbfund() {
		return sumRmbfund;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setSumRmbfund(BigDecimal sumRmbfund) {
		this.sumRmbfund = sumRmbfund;
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
	 * @return:     BigDecimal
	 */
	public BigDecimal getLendMoney() {
		if(lendMoney==null){
			return BigDecimal.ZERO;
		}
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
	 * @return:     BigDecimal
	 */
	public BigDecimal getHotMoney() {
		if(hotMoney==null){
			return BigDecimal.ZERO;
		}
		return hotMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getColdMoney() {
		if(coldMoney==null){
			return BigDecimal.ZERO;
		}
		return coldMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getVersion() {
		return version;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getAccountNum() {
		return accountNum;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
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
	 * @return:     Integer
	 */
	public Integer getStatus() {
		return status;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * <p> TODO</p>
	 * @return:     AppPersonInfo
	 */
	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	/** 
	 * <p> TODO</p>
	 * @return: AppPersonInfo
	 */
	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	public BigDecimal getSumCommissionMoney() {
		return sumCommissionMoney;
	}

	public void setSumCommissionMoney(BigDecimal sumCommissionMoney) {
		this.sumCommissionMoney = sumCommissionMoney;
	}

	public BigDecimal getHotCurrency() {
		return hotCurrency;
	}

	public void setHotCurrency(BigDecimal hotCurrency) {
		this.hotCurrency = hotCurrency;
	}

	public BigDecimal getColdCurrency() {
		return coldCurrency;
	}

	public void setColdCurrency(BigDecimal coldCurrency) {
		this.coldCurrency = coldCurrency;
	}

	public BigDecimal getHasRewardMoney() {
		return hasRewardMoney;
	}

	public void setHasRewardMoney(BigDecimal hasRewardMoney) {
		this.hasRewardMoney = hasRewardMoney;
	}
	
}

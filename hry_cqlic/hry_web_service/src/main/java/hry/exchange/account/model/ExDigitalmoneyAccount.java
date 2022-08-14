package hry.exchange.account.model;

import hry.core.mvc.model.BaseModel;
import hry.customer.person.model.AppPersonInfo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@SuppressWarnings("serial")
@Table(name = "ex_digitalmoney_account")
public class ExDigitalmoneyAccount extends BaseModel {

	// 主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 版本字段
	@Version()
	private Integer version;
	// 用户id
	@Column(name = "customerId")
	private Long customerId;
	// 可用总额
	@Column(name = "hotMoney")
	private BigDecimal hotMoney;
	// 冻结总额
	@Column(name = "coldMoney")
	private BigDecimal coldMoney;
	// 用户登录名
	@Column(name = "userName")
	private String userName;

	
	//交易账户类型 cny,usd
	@Column(name = "currencyType")
	private String currencyType;
	
	//虚拟币种类型 btc ,ltc ,eth
	@Column(name = "coinCode")
	private String coinCode;
	
	//站点类别 en ,cn
	@Column(name = "website")
	private String website;
	
	// 虚拟币种名称（比特币  莱特币 ...）
	@Column(name = "coinName")
	private String coinName;
	
	// 虚拟账号（证件号+账户类型 0 线上注册用户 1线下注册用户）
	@Column(name = "accountNum")
	private String accountNum;
	// 账户状态（0 不可用1可用）
	@Column(name = "status")
	private Integer status;
	// 公钥
	@Column(name = "publicKey")
	private String publicKey;
	// 私钥
	@Column(name = "privateKey")
	private String privateKey;
	// 借款总额
	@Column(name = "lendMoney")
	private BigDecimal lendMoney;
	// 禁用
	@Column(name = "disableMoney")
	private BigDecimal disableMoney;
	
	
	@Column(name = "psitioNaveragePrice")
	private BigDecimal psitioNaveragePrice;
	@Column(name = "psitioProtectPrice")
	private BigDecimal psitioProtectPrice;
	@Column(name = "sumCost")
	private BigDecimal sumCost;
	@Transient
	private AppPersonInfo appPersonInfo;
	
	@Transient
	private BigDecimal positionAvePrice;  //持有均价
	
	@Transient
	private  BigDecimal floatprofitandlossMoney;//浮动盈亏
	
	@Transient
	private  BigDecimal floatprofitandlossMoneyRate;//浮动盈亏
	
	
	@Transient
	private BigDecimal rmbLendMoneySum;
	@Transient
	private BigDecimal sumRmbfund;
	

	@Transient
	private BigDecimal rmbAccountNetAsse;
	

	public BigDecimal getRmbAccountNetAsse() {
		return rmbAccountNetAsse;
	}

	public void setRmbAccountNetAsse(BigDecimal rmbAccountNetAsse) {
		this.rmbAccountNetAsse = rmbAccountNetAsse;
	}

	public BigDecimal getRmbLendMoneySum() {
		return rmbLendMoneySum;
	}

	public void setRmbLendMoneySum(BigDecimal rmbLendMoneySum) {
		this.rmbLendMoneySum = rmbLendMoneySum;
	}

	public BigDecimal getSumRmbfund() {
		return sumRmbfund;
	}

	public void setSumRmbfund(BigDecimal sumRmbfund) {
		this.sumRmbfund = sumRmbfund;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getFloatprofitandlossMoney() {
		return floatprofitandlossMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setFloatprofitandlossMoney(BigDecimal floatprofitandlossMoney) {
		this.floatprofitandlossMoney = floatprofitandlossMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getFloatprofitandlossMoneyRate() {
		return floatprofitandlossMoneyRate;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setFloatprofitandlossMoneyRate(
			BigDecimal floatprofitandlossMoneyRate) {
		this.floatprofitandlossMoneyRate = floatprofitandlossMoneyRate;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getPositionAvePrice() {
		return positionAvePrice;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setPositionAvePrice(BigDecimal positionAvePrice) {
		this.positionAvePrice = positionAvePrice;
	}


	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getSumCost() {
		return sumCost;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setSumCost(BigDecimal sumCost) {
		this.sumCost = sumCost;
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
	 * @return:     BigDecimal
	 */
	public BigDecimal getPsitioNaveragePrice() {
		return psitioNaveragePrice;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setPsitioNaveragePrice(BigDecimal psitioNaveragePrice) {
		this.psitioNaveragePrice = psitioNaveragePrice;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getPsitioProtectPrice() {
		return psitioProtectPrice;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setPsitioProtectPrice(BigDecimal psitioProtectPrice) {
		this.psitioProtectPrice = psitioProtectPrice;
	}

	/** 
	 * <p> TODO</p>
	 * @return: AppPersonInfo
	 */
	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	public BigDecimal getLendMoney() {
		if(lendMoney==null){
			return BigDecimal.ZERO;
		}
		return lendMoney;
	}

	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney;
	}

	


	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getHotMoney() {
		if(hotMoney==null){
			return BigDecimal.ZERO;
		}
		return hotMoney;
	}

	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}

	public BigDecimal getColdMoney() {
		if(coldMoney==null){
			return BigDecimal.ZERO;
		}
		return coldMoney;
	}

	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public BigDecimal getDisableMoney() {
		return disableMoney;
	}

	public void setDisableMoney(BigDecimal disableMoney) {
		this.disableMoney = disableMoney;
	}

	public ExDigitalmoneyAccount(Long id, Integer version, Long customerId,
			BigDecimal hotMoney, BigDecimal coldMoney, String userName,
			String currencyType, String accountNum, Integer status,
			String publicKey, String privateKey) {
		super();
		this.id = id;
		this.version = version;
		this.customerId = customerId;
		this.hotMoney = hotMoney;
		this.coldMoney = coldMoney;
		this.userName = userName;
		this.currencyType = currencyType;
		this.accountNum = accountNum;
		this.status = status;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public ExDigitalmoneyAccount() {
		super();
	}

	@Override
	public String toString() {
		return "ExDigitalmoneyAccount [id=" + id + ", version=" + version
				+ ", customerId=" + customerId + ", hotMoney=" + hotMoney
				+ ", coldMoney=" + coldMoney + ", userName=" + userName
				+ ", currencyType=" + currencyType + ", accountNum="
				+ accountNum + ", status=" + status + ", publicKey="
				+ publicKey + ", privateKey=" + privateKey + "]";
	}

}

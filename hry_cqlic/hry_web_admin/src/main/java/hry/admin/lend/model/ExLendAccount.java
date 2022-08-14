/**
 * Copyright:
 * @author:      HeC
 * @version:     V1.0
 * @Date:        2018-10-18 14:48:04
 */
package hry.admin.lend.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> ExLendAccount </p>
 * @author:         HeC
 * @Date :          2018-10-18 14:48:04
 */
@Table(name="ex_lend_account")
public class ExLendAccount extends BaseModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name= "customerId")
	private Long customerId;  //

	@Column(name= "coinCode")
	private String coinCode;  //交易对

	@Column(name= "assetMoney")
	private BigDecimal assetMoney;  //净资产

	@Column(name= "tradeCold")
	private BigDecimal tradeCold;  //交易币冻结资产

	@Column(name= "tradeMoney")
	private BigDecimal tradeMoney;  //交易币可用

	@Column(name= "priCold")
	private BigDecimal priCold;  //定价币冻结

	@Column(name= "priMoney")
	private BigDecimal priMoney;  //定价币可用

	/**
	 * 最后一组操作信息均以触发形式入账户记录表，保证数据强一致性与原子性
	 */
	@Column(name = "lastRemark")	//最后一组操作信息
	private String lastRemark = "";

	@Column(name = "lastCode")		//最后一组操作的币种
	private String lastCode = "";

	@Column(name = "lastCount")		//最后一组操作的数量
	private String lastCount = "";

	@Column(name = "lastOrderNum")
	private String lastOrderNum = "";	//最后一组操作订单号

	@Column(name = "lastType")
	private String lastType = "";	//操作类型

	@Transient
	private String email;

	@Transient
	private String mobilePhone;

	@Transient
	private BigDecimal risk;

	public BigDecimal getRisk() {
		return risk;
	}

	public void setRisk(BigDecimal risk) {
		this.risk = risk;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getLastOrderNum() {
		return lastOrderNum;
	}

	public void setLastOrderNum(String lastOrderNum) {
		this.lastOrderNum = lastOrderNum;
	}

	public String getLastRemark() {
		return lastRemark;
	}

	public void setLastRemark(String lastRemark) {
		this.lastRemark = lastRemark;
	}

	public String getLastCode() {
		return lastCode;
	}

	public void setLastCode(String lastCode) {
		this.lastCode = lastCode;
	}

	public String getLastCount() {
		return lastCount;
	}

	public void setLastCount(String lastCount) {
		this.lastCount = lastCount;
	}

	public String getLastType() {
		return lastType;
	}

	public void setLastType(String lastType) {
		this.lastType = lastType;
	}

	/**
	 * <p></p>
	 * @author:  HeC
	 * @return:  Long
	 * @Date :   2018-10-18 14:48:04
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p></p>
	 * @author:  HeC
	 * @param:   @param id
	 * @return:  void
	 * @Date :   2018-10-18 14:48:04
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p></p>
	 * @author:  HeC
	 * @return:  Long
	 * @Date :   2018-10-18 14:48:04
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * <p></p>
	 * @author:  HeC
	 * @param:   @param customerId
	 * @return:  void
	 * @Date :   2018-10-18 14:48:04
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	/**
	 * <p>交易对</p>
	 * @author:  HeC
	 * @return:  String
	 * @Date :   2018-10-18 14:48:04
	 */
	public String getCoinCode() {
		return coinCode;
	}

	/**
	 * <p>交易对</p>
	 * @author:  HeC
	 * @param:   @param coinCode
	 * @return:  void
	 * @Date :   2018-10-18 14:48:04
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}


	/**
	 * <p>净资产</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:48:04
	 */
	public BigDecimal getAssetMoney() {
		return assetMoney;
	}

	/**
	 * <p>净资产</p>
	 * @author:  HeC
	 * @param:   @param assetMoney
	 * @return:  void
	 * @Date :   2018-10-18 14:48:04
	 */
	public void setAssetMoney(BigDecimal assetMoney) {
		this.assetMoney = assetMoney;
	}


	/**
	 * <p>交易币冻结资产</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:48:04
	 */
	public BigDecimal getTradeCold() {
		return tradeCold;
	}

	/**
	 * <p>交易币冻结资产</p>
	 * @author:  HeC
	 * @param:   @param tradeCold
	 * @return:  void
	 * @Date :   2018-10-18 14:48:04
	 */
	public void setTradeCold(BigDecimal tradeCold) {
		this.tradeCold = tradeCold;
	}

	/**
	 * <p>交易币可用</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:48:04
	 */
	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}

	/**
	 * <p>交易币可用</p>
	 * @author:  HeC
	 * @param:   @param tradeMoney
	 * @return:  void
	 * @Date :   2018-10-18 14:48:04
	 */
	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}


	/**
	 * <p>定价币冻结</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:48:04
	 */
	public BigDecimal getPriCold() {
		return priCold;
	}

	/**
	 * <p>定价币冻结</p>
	 * @author:  HeC
	 * @param:   @param priCold
	 * @return:  void
	 * @Date :   2018-10-18 14:48:04
	 */
	public void setPriCold(BigDecimal priCold) {
		this.priCold = priCold;
	}

	/**
	 * <p>定价币可用</p>
	 * @author:  HeC
	 * @return:  BigDecimal
	 * @Date :   2018-10-18 14:48:04
	 */
	public BigDecimal getPriMoney() {
		return priMoney;
	}

	/**
	 * <p>定价币可用</p>
	 * @author:  HeC
	 * @param:   @param priMoney
	 * @return:  void
	 * @Date :   2018-10-18 14:48:04
	 */
	public void setPriMoney(BigDecimal priMoney) {
		this.priMoney = priMoney;
	}



}

/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:34:46 
 */
package hry.admin.c2c.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> C2cTransaction </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:34:46  
 */
@Table(name="c2c_transaction")
public class C2cTransaction extends BaseModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name = "transactionNum")
	private String transactionNum;  //

	@Column(name = "userName")
	private String userName;  //

	@Column(name = "customerId")
	private Long customerId;  //

	@Column(name = "accountId")
	private Long accountId;  //

	@Column(name = "transactionType")
	private Integer transactionType;  //交易类型  1买，2卖

	@Column(name = "transactionMoney")
	private BigDecimal transactionMoney;  //

	@Column(name = "transactionCount")
	private BigDecimal transactionCount;  //

	@Column(name = "transactionPrice")
	private BigDecimal transactionPrice;  //

	@Column(name = "status")
	private Integer status;  ////1待审核 2已完成 3以否决

	@Column(name = "remark")
	private String remark;  //

	@Column(name = "coinCode")
	private String coinCode;  //

	@Column(name = "fee")
	private BigDecimal fee;  //

	@Column(name = "saasId")
	private String saasId;  //

	@Column(name = "businessmanId")
	private Long businessmanId;  //

	@Column(name = "businessmanBankId")
	private Long businessmanBankId;  //

	@Column(name = "randomNum")
	private String randomNum;  //

	@Column(name = "customerBankId")
	private Long customerBankId;  //

	@Column(name = "transactionId")
	private Long transactionId;  //

	@Column(name = "status2")
	private Integer status2;  //

	@Column(name = "businessman")
	private String businessman;  //

	@Column(name = "checkUser")
	private String checkUser;  //

	@Transient // 不与数据库映射的字段
	private String businessmanTrueName;

	@Transient // 不与数据库映射的字段
	private String bankcard;

	@Transient // 不与数据库映射的字段
	private String bankname;

	@Transient // 不与数据库映射的字段
	private String surname;

	@Transient // 不与数据库映射的字段
	private String trueName;


	@Transient // 不与数据库映射的字段
	private Object appPersonInfo;

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankcard() {
		return bankcard;
	}

	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}

	public String getBusinessmanTrueName() {
		return businessmanTrueName;
	}

	public void setBusinessmanTrueName(String businessmanTrueName) {
		this.businessmanTrueName = businessmanTrueName;
	}

	public Object getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(Object appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: Long
	 * @Date :   2018-06-13 13:34:46
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param id
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: String
	 * @Date :   2018-06-13 13:34:46
	 */
	public String getTransactionNum() {
		return transactionNum;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param transactionNum
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: String
	 * @Date :   2018-06-13 13:34:46
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param userName
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: Long
	 * @Date :   2018-06-13 13:34:46
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param customerId
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: Long
	 * @Date :   2018-06-13 13:34:46
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param accountId
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: Integer
	 * @Date :   2018-06-13 13:34:46
	 */
	public Integer getTransactionType() {
		return transactionType;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param transactionType
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: BigDecimal
	 * @Date :   2018-06-13 13:34:46
	 */
	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param transactionMoney
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: BigDecimal
	 * @Date :   2018-06-13 13:34:46
	 */
	public BigDecimal getTransactionCount() {
		return transactionCount;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param transactionCount
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setTransactionCount(BigDecimal transactionCount) {
		this.transactionCount = transactionCount;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: BigDecimal
	 * @Date :   2018-06-13 13:34:46
	 */
	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param transactionPrice
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setTransactionPrice(BigDecimal transactionPrice) {
		this.transactionPrice = transactionPrice;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: Integer
	 * @Date :   2018-06-13 13:34:46
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param status
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: String
	 * @Date :   2018-06-13 13:34:46
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param remark
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: String
	 * @Date :   2018-06-13 13:34:46
	 */
	public String getCoinCode() {
		return coinCode;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param coinCode
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: BigDecimal
	 * @Date :   2018-06-13 13:34:46
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param fee
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: String
	 * @Date :   2018-06-13 13:34:46
	 */
	public String getSaasId() {
		return saasId;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param saasId
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: Long
	 * @Date :   2018-06-13 13:34:46
	 */
	public Long getBusinessmanId() {
		return businessmanId;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param businessmanId
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setBusinessmanId(Long businessmanId) {
		this.businessmanId = businessmanId;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: Long
	 * @Date :   2018-06-13 13:34:46
	 */
	public Long getBusinessmanBankId() {
		return businessmanBankId;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param businessmanBankId
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setBusinessmanBankId(Long businessmanBankId) {
		this.businessmanBankId = businessmanBankId;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: String
	 * @Date :   2018-06-13 13:34:46
	 */
	public String getRandomNum() {
		return randomNum;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param randomNum
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: Long
	 * @Date :   2018-06-13 13:34:46
	 */
	public Long getCustomerBankId() {
		return customerBankId;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param customerBankId
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setCustomerBankId(Long customerBankId) {
		this.customerBankId = customerBankId;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: Long
	 * @Date :   2018-06-13 13:34:46
	 */
	public Long getTransactionId() {
		return transactionId;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param transactionId
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: Integer
	 * @Date :   2018-06-13 13:34:46
	 */
	public Integer getStatus2() {
		return status2;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param status2
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setStatus2(Integer status2) {
		this.status2 = status2;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: String
	 * @Date :   2018-06-13 13:34:46
	 */
	public String getBusinessman() {
		return businessman;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param businessman
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setBusinessman(String businessman) {
		this.businessman = businessman;
	}


	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @return: String
	 * @Date :   2018-06-13 13:34:46
	 */
	public String getCheckUser() {
		return checkUser;
	}

	/**
	 * <p></p>
	 *
	 * @author: liushilei
	 * @param: @param checkUser
	 * @return: void
	 * @Date :   2018-06-13 13:34:46
	 */
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
}
	
	



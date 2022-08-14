/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-13 18:38:15 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppOurAccount </p>
 * @author:         tianpengyu
 * @Date :          2018-07-13 18:38:15  
 */
@Table(name="app_our_account")
public class AppOurAccount extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "currencyType")
	private String currencyType;  //
	
	@Column(name= "bankLogo")
	private String bankLogo;  //
	
	@Column(name= "isShow")
	private Integer isShow;  //
	
	@Column(name= "accountType")
	private String accountType;  //
	
	@Column(name= "openAccountType")
	private String openAccountType;  //
	
	@Column(name= "bankName")
	private String bankName;  //
	
	@Column(name= "accountName")
	private String accountName;  //
	
	@Column(name= "accountNumber")
	private String accountNumber;  //
	
	@Column(name= "openTime")
	private String openTime;  //
	
	@Column(name= "accountMoney")
	private BigDecimal accountMoney;  //
	
	@Column(name= "bankAddress")
	private String bankAddress;  //
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "retainsMoney")
	private BigDecimal retainsMoney;  //
	
	@Column(name= "accountMoneyNew")
	private BigDecimal accountMoneyNew;  //
	
	@Column(name= "todayAddedMoney")
	private BigDecimal todayAddedMoney;  //
	
	@Column(name= "accountFee")
	private BigDecimal accountFee;  //
	
	@Column(name= "hasOutFee")
	private BigDecimal hasOutFee;  //
	
	@Column(name= "todayAddedFee")
	private BigDecimal todayAddedFee;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getBankLogo() {
		return bankLogo;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param bankLogo
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setBankLogo(String bankLogo) {
		this.bankLogo = bankLogo;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public Integer getIsShow() {
		return isShow;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param isShow
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getAccountType() {
		return accountType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param accountType
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getOpenAccountType() {
		return openAccountType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param openAccountType
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setOpenAccountType(String openAccountType) {
		this.openAccountType = openAccountType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getBankName() {
		return bankName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param bankName
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getAccountName() {
		return accountName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param accountName
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param accountNumber
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getOpenTime() {
		return openTime;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param openTime
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public BigDecimal getAccountMoney() {
		return accountMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param accountMoney
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setAccountMoney(BigDecimal accountMoney) {
		this.accountMoney = accountMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getBankAddress() {
		return bankAddress;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param bankAddress
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public BigDecimal getRetainsMoney() {
		return retainsMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param retainsMoney
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setRetainsMoney(BigDecimal retainsMoney) {
		this.retainsMoney = retainsMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public BigDecimal getAccountMoneyNew() {
		return accountMoneyNew;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param accountMoneyNew
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setAccountMoneyNew(BigDecimal accountMoneyNew) {
		this.accountMoneyNew = accountMoneyNew;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public BigDecimal getTodayAddedMoney() {
		return todayAddedMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param todayAddedMoney
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setTodayAddedMoney(BigDecimal todayAddedMoney) {
		this.todayAddedMoney = todayAddedMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public BigDecimal getAccountFee() {
		return accountFee;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param accountFee
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setAccountFee(BigDecimal accountFee) {
		this.accountFee = accountFee;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public BigDecimal getHasOutFee() {
		return hasOutFee;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param hasOutFee
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setHasOutFee(BigDecimal hasOutFee) {
		this.hasOutFee = hasOutFee;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-13 18:38:15    
	 */
	public BigDecimal getTodayAddedFee() {
		return todayAddedFee;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param todayAddedFee
	 * @return:  void 
	 * @Date :   2018-07-13 18:38:15   
	 */
	public void setTodayAddedFee(BigDecimal todayAddedFee) {
		this.todayAddedFee = todayAddedFee;
	}
	
	

}

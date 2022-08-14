/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-09-20 17:34:28 
 */
package hry.exchange.lock.model;


import hry.bean.BaseModel;
import hry.customer.person.model.AppPersonInfo;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> ExDmUnlockHistory </p>
 * @author:         liuchenghui
 * @Date :          2018-09-20 17:34:28  
 */
@Table(name="ex_dm_unlock_history")
public class ExDmUnlockHistory extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //

	@Column(name = "recordId")
	private Long recordId;
	
	@Column(name= "transactionNum")
	private String transactionNum;  //
	
	@Column(name= "transactionMoney")
	private BigDecimal transactionMoney;  //
	
	@Column(name= "accountId")
	private Long accountId;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "optType")
	private Integer optType;  //

	@Column(name = "optUser")
	private String optUser;

	@Column(name= "saasId")
	private String saasId;  //
	
	@Transient
	private AppPersonInfo appPersonInfo;
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-09-20 17:34:28    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-09-20 17:34:28   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-09-20 17:34:28    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-09-20 17:34:28   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-09-20 17:34:28    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2018-09-20 17:34:28   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  BigDecimal 
	 * @Date :   2018-09-20 17:34:28    
	 */
	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param transactionMoney
	 * @return:  void 
	 * @Date :   2018-09-20 17:34:28   
	 */
	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-09-20 17:34:28    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2018-09-20 17:34:28   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-09-20 17:34:28    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-09-20 17:34:28   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-09-20 17:34:28    
	 */
	public Integer getOptType() {
		return optType;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param optType
	 * @return:  void 
	 * @Date :   2018-09-20 17:34:28   
	 */
	public void setOptType(Integer optType) {
		this.optType = optType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-09-20 17:34:28    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-09-20 17:34:28   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	public Long getRecordId () {
		return recordId;
	}

	public void setRecordId (Long recordId) {
		this.recordId = recordId;
	}

	public String getOptUser () {
		return optUser;
	}

	public void setOptUser (String optUser) {
		this.optUser = optUser;
	}

	public AppPersonInfo getAppPersonInfo () {
		return appPersonInfo;
	}

	public void setAppPersonInfo (AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}
}

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2018-07-30 20:52:23 
 */
package hry.admin.mining.model;

import hry.admin.customer.model.AppPersonInfo;
import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * <p> ExMiningUnfreeze </p>
 * @author:         jidn
 * @Date :          2018-07-30 20:52:23  
 */
@Table(name="ex_mining_unfreeze")
public class ExMiningUnfreeze extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "transactionNum")
	private String transactionNum;  //transactionNum
	
	@Column(name= "customerId")
	private Long customerId;  //customerId
	
	@Column(name= "accountId")
	private Long accountId;  //accountId
	
	@Column(name= "coinCode")
	private String coinCode;  //coinCode
	
	@Column(name= "money")
	private BigDecimal money;  //money
	
	@Column(name= "unfreezeNum")
	private BigDecimal unfreezeNum;//解冻数量
	
	@Column(name= "remark")
	private String remark;  //remark
	
	@Transient
	private AppPersonInfo appPersonInfo;
	
	
	
	public BigDecimal getUnfreezeNum() {
		return unfreezeNum;
	}

	public void setUnfreezeNum(BigDecimal unfreezeNum) {
		this.unfreezeNum = unfreezeNum;
	}

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2018-07-30 20:52:23    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-30 20:52:23   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>transactionNum</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2018-07-30 20:52:23    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>transactionNum</p>
	 * @author:  jidn
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2018-07-30 20:52:23   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>customerId</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2018-07-30 20:52:23    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>customerId</p>
	 * @author:  jidn
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-07-30 20:52:23   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>accountId</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2018-07-30 20:52:23    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>accountId</p>
	 * @author:  jidn
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2018-07-30 20:52:23   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>coinCode</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2018-07-30 20:52:23    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>coinCode</p>
	 * @author:  jidn
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-07-30 20:52:23   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>money</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2018-07-30 20:52:23    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>money</p>
	 * @author:  jidn
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2018-07-30 20:52:23   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p>remark</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2018-07-30 20:52:23    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>remark</p>
	 * @author:  jidn
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-07-30 20:52:23   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

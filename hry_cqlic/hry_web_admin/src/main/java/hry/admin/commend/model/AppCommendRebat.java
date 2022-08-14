/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:02 
 */
package hry.admin.commend.model;


import hry.admin.customer.model.AppPersonInfo;
import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> AppCommendRebat </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:50:02  
 */
@Table(name="app_commend_rebat")
public class AppCommendRebat extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "transactionNum")
	private String transactionNum;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	@Column(name= "rebatMoney")
	private BigDecimal rebatMoney;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "userCode")
	private String userCode;  //
	
	@Column(name= "saasId")
	private String saasId;  //

	@Transient   //不与数据库映射的字段
	private AppPersonInfo appPersonInfo;
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 19:50:02    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:02   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:02    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:02   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 19:50:02    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:02   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:02    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:02   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:50:02    
	 */
	public BigDecimal getRebatMoney() {
		return rebatMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param rebatMoney
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:02   
	 */
	public void setRebatMoney(BigDecimal rebatMoney) {
		this.rebatMoney = rebatMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:02    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:02   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:50:02    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:02   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:02    
	 */
	public String getUserCode() {
		return userCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param userCode
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:02   
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:02    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:02   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}
}

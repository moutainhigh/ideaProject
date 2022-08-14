/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:54:15 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;


import javax.persistence.*;

/**
 * <p> ExDmCustomerPublickey </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:54:15  
 */
@Table(name="ex_dm_customer_publickey")
public class ExDmCustomerPublickey extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "publicKeyName")
	private String publicKeyName;  //
	
	@Column(name= "publicKey")
	private String publicKey;  //
	
	@Column(name= "currencyType")
	private String currencyType;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "saasId")
	private String saasId;  //

	@Transient // 不与数据库映射的字段
	private Object appPersonInfo;

	public Object getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(Object appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 10:54:15    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 10:54:15   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:54:15    
	 */
	public String getPublicKeyName() {
		return publicKeyName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param publicKeyName
	 * @return:  void 
	 * @Date :   2018-06-13 10:54:15   
	 */
	public void setPublicKeyName(String publicKeyName) {
		this.publicKeyName = publicKeyName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:54:15    
	 */
	public String getPublicKey() {
		return publicKey;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param publicKey
	 * @return:  void 
	 * @Date :   2018-06-13 10:54:15   
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:54:15    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2018-06-13 10:54:15   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 10:54:15    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-13 10:54:15   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:54:15    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-06-13 10:54:15   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:54:15    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 10:54:15   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

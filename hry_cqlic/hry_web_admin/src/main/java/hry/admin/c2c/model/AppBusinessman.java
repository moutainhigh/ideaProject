/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:33:52 
 */
package hry.admin.c2c.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> appBusinessman </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:33:52  
 */
@Table(name="app_businessman")
public class AppBusinessman extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "userId")
	private Long userId;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	@Column(name= "type")
	private Integer type;  //type   A类==0   B类==1
	
	@Column(name= "feeType")
	private Integer feeType;  //
	
	@Column(name= "fee")
	private BigDecimal fee;  //
	
	@Column(name= "price")
	private BigDecimal price;  //
	
	@Column(name= "nationality")
	private String nationality;  //
	
	@Column(name= "changeCoin")
	private String changeCoin;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "isLock")
	private Integer isLock;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 13:33:52    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 13:33:52   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 13:33:52    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2018-06-13 13:33:52   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:33:52    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-06-13 13:33:52   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 13:33:52    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-06-13 13:33:52   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 13:33:52    
	 */
	public Integer getFeeType() {
		return feeType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param feeType
	 * @return:  void 
	 * @Date :   2018-06-13 13:33:52   
	 */
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 13:33:52    
	 */
	public BigDecimal getFee() {
		return fee;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param fee
	 * @return:  void 
	 * @Date :   2018-06-13 13:33:52   
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 13:33:52    
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param price
	 * @return:  void 
	 * @Date :   2018-06-13 13:33:52   
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:33:52    
	 */
	public String getNationality() {
		return nationality;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param nationality
	 * @return:  void 
	 * @Date :   2018-06-13 13:33:52   
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:33:52    
	 */
	public String getChangeCoin() {
		return changeCoin;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param changeCoin
	 * @return:  void 
	 * @Date :   2018-06-13 13:33:52   
	 */
	public void setChangeCoin(String changeCoin) {
		this.changeCoin = changeCoin;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:33:52    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 13:33:52   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 13:33:52    
	 */
	public Integer getIsLock() {
		return isLock;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isLock
	 * @return:  void 
	 * @Date :   2018-06-13 13:33:52   
	 */
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	
	

}

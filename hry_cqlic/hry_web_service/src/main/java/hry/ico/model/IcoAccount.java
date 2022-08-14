/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2019-01-14 13:38:06 
 */
package hry.ico.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IcoAccount </p>
 * @author:         denghf
 * @Date :          2019-01-14 13:38:06  
 */
@Table(name="ico_account")
public class IcoAccount extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "coinName")
	private String coinName;  //币名称
	
	@Column(name= "coinCode")
	private String coinCode;  //币Code
	
	@Column(name= "phone")
	private String phone;  //手机号
	
	@Column(name= "storageMoney")
	private BigDecimal storageMoney;  //存储总额
	
	@Column(name= "coldMoney")
	private BigDecimal coldMoney;  //冻结总额
	
	@Column(name= "hotMoney")
	private BigDecimal hotMoney;  //流通总额
	
	@Column(name= "publicKey")
	private String publicKey;  //

	@Column(name= "version")
	private Integer version;  //版本号

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2019-01-14 13:38:06    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-14 13:38:06   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-14 13:38:06    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  denghf
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-01-14 13:38:06   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币名称</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-14 13:38:06    
	 */
	public String getCoinName() {
		return coinName;
	}
	
	/**
	 * <p>币名称</p>
	 * @author:  denghf
	 * @param:   @param coinName
	 * @return:  void 
	 * @Date :   2019-01-14 13:38:06   
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
	
	/**
	 * <p>币Code</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-14 13:38:06    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币Code</p>
	 * @author:  denghf
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-01-14 13:38:06   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-14 13:38:06    
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  denghf
	 * @param:   @param phone
	 * @return:  void 
	 * @Date :   2019-01-14 13:38:06   
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	/**
	 * <p>存储总额</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 13:38:06    
	 */
	public BigDecimal getStorageMoney() {
		return storageMoney;
	}
	
	/**
	 * <p>存储总额</p>
	 * @author:  denghf
	 * @param:   @param storageMoney
	 * @return:  void 
	 * @Date :   2019-01-14 13:38:06   
	 */
	public void setStorageMoney(BigDecimal storageMoney) {
		this.storageMoney = storageMoney;
	}
	
	
	/**
	 * <p>冻结总额</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 13:38:06    
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	/**
	 * <p>冻结总额</p>
	 * @author:  denghf
	 * @param:   @param coldMoney
	 * @return:  void 
	 * @Date :   2019-01-14 13:38:06   
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}
	
	
	/**
	 * <p>流通总额</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 13:38:06    
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	/**
	 * <p>流通总额</p>
	 * @author:  denghf
	 * @param:   @param hotMoney
	 * @return:  void 
	 * @Date :   2019-01-14 13:38:06   
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-14 13:38:06    
	 */
	public String getPublicKey() {
		return publicKey;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param publicKey
	 * @return:  void 
	 * @Date :   2019-01-14 13:38:06   
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	

}

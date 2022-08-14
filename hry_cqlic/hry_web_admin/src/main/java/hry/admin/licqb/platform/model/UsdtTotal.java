/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-11 12:01:59 
 */
package hry.admin.licqb.platform.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> UsdtTotal </p>
 * @author:         zhouming
 * @Date :          2020-06-11 12:01:59  
 */
@Table(name="lc_usdt_total")
public class UsdtTotal extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代码
	
	@Column(name= "payMoney")
	private BigDecimal payMoney;  //充值总数
	
	@Column(name= "extractMoney")
	private BigDecimal extractMoney;  //提取总数
	
	@Column(name= "saasId")
	private String saasId;  //

	@Transient
	private Long customerId; // 用户Id

	@Column(name= "surplusMoney")
	private BigDecimal surplusMoney; // 剩余数量
	
	@Transient
	private String email; // 邮箱

	@Transient
	private String mobilePhone; // 手机号

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney = surplusMoney;
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

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-06-11 12:01:59    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-06-11 12:01:59   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-11 12:01:59    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2020-06-11 12:01:59   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>充值总数</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-06-11 12:01:59    
	 */
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	
	/**
	 * <p>充值总数</p>
	 * @author:  zhouming
	 * @param:   @param payMoney
	 * @return:  void 
	 * @Date :   2020-06-11 12:01:59   
	 */
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	
	
	/**
	 * <p>提取总数</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-06-11 12:01:59    
	 */
	public BigDecimal getExtractMoney() {
		return extractMoney;
	}
	
	/**
	 * <p>提取总数</p>
	 * @author:  zhouming
	 * @param:   @param extractMoney
	 * @return:  void 
	 * @Date :   2020-06-11 12:01:59   
	 */
	public void setExtractMoney(BigDecimal extractMoney) {
		this.extractMoney = extractMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-11 12:01:59    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-06-11 12:01:59   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

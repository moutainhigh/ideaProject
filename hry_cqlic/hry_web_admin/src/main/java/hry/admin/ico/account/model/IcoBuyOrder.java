/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 14:51:32 
 */
package hry.admin.ico.account.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> IcoBuyOrder </p>
 * @author:         lzy
 * @Date :          2019-01-14 14:51:32  
 */
@Table(name="ico_buy_order")
public class IcoBuyOrder extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "orderNumber")
	private String orderNumber;  //订单号
	
	@Column(name= "customerId")
	private String customerId;  //用户Id
	
	@Column(name= "payCoinName")
	private String payCoinName;  //支付币种名称
	
	@Column(name= "payCoinCode")
	private String payCoinCode;  //支付币种code
	
	@Column(name= "payNumber")
	private BigDecimal payNumber;  //支付数量
	
	@Column(name= "buyPrice")
	private BigDecimal buyPrice;  //购买定价
	
	@Column(name= "buyCoinCode")
	private String buyCoinCode;  //购买币种名称
	
	@Column(name= "buyNumber")
	private BigDecimal buyNumber;  //购买数量
	
	@Column(name= "poundageRate")
	private BigDecimal poundageRate;  //手续费费率
	
	@Column(name= "poundage")
	private BigDecimal poundage;  //手续费




	@Transient // 不与数据库映射的字段
	private String mobilePhone;   //用户手机号

	@Transient // 不与数据库映射的字段
	private String email;      	//用户email

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-14 14:51:32    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-14 14:51:32   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>订单号</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-14 14:51:32    
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	
	/**
	 * <p>订单号</p>
	 * @author:  lzy
	 * @param:   @param orderNumber
	 * @return:  void 
	 * @Date :   2019-01-14 14:51:32   
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-14 14:51:32    
	 */
	public String getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-01-14 14:51:32   
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>支付币种名称</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-14 14:51:32    
	 */
	public String getPayCoinName() {
		return payCoinName;
	}
	
	/**
	 * <p>支付币种名称</p>
	 * @author:  lzy
	 * @param:   @param payCoinName
	 * @return:  void 
	 * @Date :   2019-01-14 14:51:32   
	 */
	public void setPayCoinName(String payCoinName) {
		this.payCoinName = payCoinName;
	}
	
	
	/**
	 * <p>支付币种code</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-14 14:51:32    
	 */
	public String getPayCoinCode() {
		return payCoinCode;
	}
	
	/**
	 * <p>支付币种code</p>
	 * @author:  lzy
	 * @param:   @param payCoinCode
	 * @return:  void 
	 * @Date :   2019-01-14 14:51:32   
	 */
	public void setPayCoinCode(String payCoinCode) {
		this.payCoinCode = payCoinCode;
	}
	
	
	/**
	 * <p>支付数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 14:51:32    
	 */
	public BigDecimal getPayNumber() {
		return payNumber;
	}
	
	/**
	 * <p>支付数量</p>
	 * @author:  lzy
	 * @param:   @param payNumber
	 * @return:  void 
	 * @Date :   2019-01-14 14:51:32   
	 */
	public void setPayNumber(BigDecimal payNumber) {
		this.payNumber = payNumber;
	}
	
	
	/**
	 * <p>购买定价</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 14:51:32    
	 */
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	
	/**
	 * <p>购买定价</p>
	 * @author:  lzy
	 * @param:   @param buyPrice
	 * @return:  void 
	 * @Date :   2019-01-14 14:51:32   
	 */
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	
	/**
	 * <p>购买币种名称</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-14 14:51:32    
	 */
	public String getBuyCoinCode() {
		return buyCoinCode;
	}
	
	/**
	 * <p>购买币种名称</p>
	 * @author:  lzy
	 * @param:   @param buyCoinCode
	 * @return:  void 
	 * @Date :   2019-01-14 14:51:32   
	 */
	public void setBuyCoinCode(String buyCoinCode) {
		this.buyCoinCode = buyCoinCode;
	}
	
	
	/**
	 * <p>购买数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 14:51:32    
	 */
	public BigDecimal getBuyNumber() {
		return buyNumber;
	}
	
	/**
	 * <p>购买数量</p>
	 * @author:  lzy
	 * @param:   @param buyNumber
	 * @return:  void 
	 * @Date :   2019-01-14 14:51:32   
	 */
	public void setBuyNumber(BigDecimal buyNumber) {
		this.buyNumber = buyNumber;
	}
	
	
	/**
	 * <p>手续费费率</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 14:51:32    
	 */
	public BigDecimal getPoundageRate() {
		return poundageRate;
	}
	
	/**
	 * <p>手续费费率</p>
	 * @author:  lzy
	 * @param:   @param poundageRate
	 * @return:  void 
	 * @Date :   2019-01-14 14:51:32   
	 */
	public void setPoundageRate(BigDecimal poundageRate) {
		this.poundageRate = poundageRate;
	}
	
	
	/**
	 * <p>手续费</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 14:51:32    
	 */
	public BigDecimal getPoundage() {
		return poundage;
	}
	
	/**
	 * <p>手续费</p>
	 * @author:  lzy
	 * @param:   @param poundage
	 * @return:  void 
	 * @Date :   2019-01-14 14:51:32   
	 */
	public void setPoundage(BigDecimal poundage) {
		this.poundage = poundage;
	}
	
	

}

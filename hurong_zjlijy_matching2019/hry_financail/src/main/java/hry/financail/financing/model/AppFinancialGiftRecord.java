/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:06:06 
 */
package hry.financail.financing.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppFinancialGiftRecord </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:06:06  
 */
@Table(name="app_financial_gift_record")
public class AppFinancialGiftRecord extends BaseModel implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //赠送红包记录表id
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种类型
	
	@Column(name= "giftType")
	private String giftType;  //交易类型
	
	@Column(name= "giftMoney")
	private BigDecimal giftMoney;  //赠送金额
	
	@Column(name= "mobile")
	private String mobile;  //手机号
	
	@Column(name= "userName")
	private String userName;  //用户姓名
	
	@Column(name= "state")
	private Integer state;  //用户是否领取    0 未领取 1领取   2已过期
	
	@Column(name= "receiveTime")
	private Date receiveTime;  //领取时间
	
	@Column(name= "overTime")
	private Date overTime;  //过期时间
	
	
	
	
	/**
	 * <p>赠送红包记录表id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-03 11:06:06    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>赠送红包记录表id</p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-03 11:06:06   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-03 11:06:06    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  jidn
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-04-03 11:06:06   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币种类型</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:06:06    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种类型</p>
	 * @author:  jidn
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-04-03 11:06:06   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>交易类型</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:06:06    
	 */
	public String getGiftType() {
		return giftType;
	}
	
	/**
	 * <p>交易类型</p>
	 * @author:  jidn
	 * @param:   @param giftType
	 * @return:  void 
	 * @Date :   2019-04-03 11:06:06   
	 */
	public void setGiftType(String giftType) {
		this.giftType = giftType;
	}
	
	
	/**
	 * <p>赠送金额</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-04-03 11:06:06    
	 */
	public BigDecimal getGiftMoney() {
		return giftMoney;
	}
	
	/**
	 * <p>赠送金额</p>
	 * @author:  jidn
	 * @param:   @param giftMoney
	 * @return:  void 
	 * @Date :   2019-04-03 11:06:06   
	 */
	public void setGiftMoney(BigDecimal giftMoney) {
		this.giftMoney = giftMoney;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:06:06    
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  jidn
	 * @param:   @param mobile
	 * @return:  void 
	 * @Date :   2019-04-03 11:06:06   
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	/**
	 * <p>用户姓名</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:06:06    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>用户姓名</p>
	 * @author:  jidn
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2019-04-03 11:06:06   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>用户是否领取    0 未领取 1领取   2已过期</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2019-04-03 11:06:06    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>用户是否领取    0 未领取 1领取   2已过期</p>
	 * @author:  jidn
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-04-03 11:06:06   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>领取时间</p>
	 * @author:  jidn
	 * @return:  Date 
	 * @Date :   2019-04-03 11:06:06    
	 */
	public Date getReceiveTime() {
		return receiveTime;
	}
	
	/**
	 * <p>领取时间</p>
	 * @author:  jidn
	 * @param:   @param receiveTime
	 * @return:  void 
	 * @Date :   2019-04-03 11:06:06   
	 */
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	
	
	/**
	 * <p>过期时间</p>
	 * @author:  jidn
	 * @return:  Date 
	 * @Date :   2019-04-03 11:06:06    
	 */
	public Date getOverTime() {
		return overTime;
	}
	
	/**
	 * <p>过期时间</p>
	 * @author:  jidn
	 * @param:   @param overTime
	 * @return:  void 
	 * @Date :   2019-04-03 11:06:06   
	 */
	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}
	
	

}

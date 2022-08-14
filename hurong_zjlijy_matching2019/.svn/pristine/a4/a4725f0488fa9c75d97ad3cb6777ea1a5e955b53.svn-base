/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:07:30 
 */
package hry.financail.financing.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppFinancialRedAcount </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:07:30  
 */
@Table(name="app_financial_red_acount")
public class AppFinancialRedAcount extends BaseModel implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种类型
	
	@Column(name= "money")
	private BigDecimal money;  //账户红包金额
	
	@Column(name= "mobile")
	private String mobile;  //手机号
	
	@Column(name= "userName")
	private String userName;  //用户姓名

	@Column(name= "usedMoney")
	private BigDecimal usedMoney; //已使用红包金额
	
	
	
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-03 11:07:30    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:30   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2019-04-03 11:07:30    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  jidn
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:30   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币种类型</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:07:30    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种类型</p>
	 * @author:  jidn
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:30   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>账户红包金额</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2019-04-03 11:07:30    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>账户红包金额</p>
	 * @author:  jidn
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:30   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:07:30    
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  jidn
	 * @param:   @param mobile
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:30   
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	/**
	 * <p>用户姓名</p>
	 * @author:  jidn
	 * @return:  String 
	 * @Date :   2019-04-03 11:07:30    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>用户姓名</p>
	 * @author:  jidn
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2019-04-03 11:07:30   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getUsedMoney() {
		return usedMoney;
	}

	public void setUsedMoney(BigDecimal usedMoney) {
		this.usedMoney = usedMoney;
	}
}

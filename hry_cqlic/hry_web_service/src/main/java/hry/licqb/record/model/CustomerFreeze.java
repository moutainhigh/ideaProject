/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-13 15:29:30 
 */
package hry.licqb.record.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> CustomerFreeze </p>
 * @author:         zhouming
 * @Date :          2020-01-13 15:29:30  
 */
@Table(name="lc_customer_freeze")
public class CustomerFreeze extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代码
	
	@Column(name= "freezeMoney")
	private BigDecimal freezeMoney;  //冻结总金额

	@Transient
	private BigDecimal hotMoney; // 热账户金额

	@Transient
	private BigDecimal coldMoney; // 冷账户金额

	public BigDecimal getHotMoney() {
		return hotMoney;
	}

	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}

	public BigDecimal getColdMoney() {
		return coldMoney;
	}

	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}

	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-13 15:29:30    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-01-13 15:29:30   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-13 15:29:30    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2020-01-13 15:29:30   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-13 15:29:30    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2020-01-13 15:29:30   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>冻结总金额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-13 15:29:30    
	 */
	public BigDecimal getFreezeMoney() {
		return freezeMoney;
	}
	
	/**
	 * <p>冻结总金额</p>
	 * @author:  zhouming
	 * @param:   @param freezeMoney
	 * @return:  void 
	 * @Date :   2020-01-13 15:29:30   
	 */
	public void setFreezeMoney(BigDecimal freezeMoney) {
		this.freezeMoney = freezeMoney;
	}
	
	

}

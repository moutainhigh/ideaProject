/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-12-24 11:01:14 
 */
package hry.cm5.account.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm5Account </p>
 * @author:         zhouming
 * @Date :          2019-12-24 11:01:14  
 */
@Table(name="cm5_account")
public class Cm5Account extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //币种
	
	@Column(name= "customerId")
	private Long customerId;  //用户或者会员id
	
	@Column(name= "hotMoney")
	private BigDecimal hotMoney;  //可用总额
	
	@Column(name= "coldMoney")
	private BigDecimal coldMoney;  //冻结总额
	
	@Column(name= "usedMoney")
	private BigDecimal usedMoney;  //已用总额：当前所有持仓占用保证金总和
	
	
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-12-24 11:01:14    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-12-24 11:01:14   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-12-24 11:01:14    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  zhouming
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-12-24 11:01:14   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>用户或者会员id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-12-24 11:01:14    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户或者会员id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-12-24 11:01:14   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>可用总额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-12-24 11:01:14    
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	/**
	 * <p>可用总额</p>
	 * @author:  zhouming
	 * @param:   @param hotMoney
	 * @return:  void 
	 * @Date :   2019-12-24 11:01:14   
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	
	
	/**
	 * <p>冻结总额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-12-24 11:01:14    
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	/**
	 * <p>冻结总额</p>
	 * @author:  zhouming
	 * @param:   @param coldMoney
	 * @return:  void 
	 * @Date :   2019-12-24 11:01:14   
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}
	
	
	/**
	 * <p>已用总额：当前所有持仓占用保证金总和</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2019-12-24 11:01:14    
	 */
	public BigDecimal getUsedMoney() {
		return usedMoney;
	}
	
	/**
	 * <p>已用总额：当前所有持仓占用保证金总和</p>
	 * @author:  zhouming
	 * @param:   @param usedMoney
	 * @return:  void 
	 * @Date :   2019-12-24 11:01:14   
	 */
	public void setUsedMoney(BigDecimal usedMoney) {
		this.usedMoney = usedMoney;
	}
	
	

}

/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-15 16:35:25 
 */
package hry.admin.klg.platform.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgPlatformAccount </p>
 * @author:         lzy
 * @Date :          2019-04-15 16:35:25  
 */
@Table(name="klg_platform_account")
public class KlgPlatformAccount extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "type")
	private Integer type;  //账户类型
	
	@Column(name= "money")
	private BigDecimal money;  //金额
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-15 16:35:25    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-15 16:35:25   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>账户类型</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-15 16:35:25    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>账户类型</p>
	 * @author:  lzy
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-04-15 16:35:25   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>金额</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-15 16:35:25    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>金额</p>
	 * @author:  lzy
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2019-04-15 16:35:25   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-15 16:35:25    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-04-15 16:35:25   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	

}

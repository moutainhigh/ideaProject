/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-19 11:31:31 
 */
package hry.klg.buysellaccount.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgBuySellAccount </p>
 * @author:         yaozhuo
 * @Date :          2019-04-19 11:31:31  
 */
@Table(name="klg_buysell_account")
public class KlgBuySellAccount extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "accountName")
	private String accountName;  //账户名称
	
	@Column(name= "coinCode")
	private String coinCode;  //账户币种
	
	@Column(name= "money")
	private BigDecimal money;  //剩余金额
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-19 11:31:31    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-19 11:31:31   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>账户名称</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-19 11:31:31    
	 */
	public String getAccountName() {
		return accountName;
	}
	
	/**
	 * <p>账户名称</p>
	 * @author:  yaozhuo
	 * @param:   @param accountName
	 * @return:  void 
	 * @Date :   2019-04-19 11:31:31   
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	
	/**
	 * <p>剩余金额</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-19 11:31:31    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>剩余金额</p>
	 * @author:  yaozhuo
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2019-04-19 11:31:31   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-19 11:31:31    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-04-19 11:31:31   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-15 16:36:13 
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
 * <p> KlgPlatformAccountRecord </p>
 * @author:         lzy
 * @Date :          2019-04-15 16:36:13  
 */
@Table(name="klg_platform_account_record")
public class KlgPlatformAccountRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "serialNumber")
	private String serialNumber;  //
	
	@Column(name= "type")
	private Integer type;  //类型
	
	@Column(name= "money")
	private BigDecimal money;  //金额
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "nowMoney")
	private BigDecimal nowMoney;  //
	
	@Column(name= "accountId")
	private String accountId;  //
	
	@Column(name= "remark")
	private String remark;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-15 16:36:13    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-15 16:36:13   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-15 16:36:13    
	 */
	public String getSerialNumber() {
		return serialNumber;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param serialNumber
	 * @return:  void 
	 * @Date :   2019-04-15 16:36:13   
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
	/**
	 * <p>类型</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-15 16:36:13    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型</p>
	 * @author:  lzy
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-04-15 16:36:13   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>金额</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-15 16:36:13    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>金额</p>
	 * @author:  lzy
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2019-04-15 16:36:13   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-15 16:36:13    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-04-15 16:36:13   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-15 16:36:13    
	 */
	public BigDecimal getNowMoney() {
		return nowMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param nowMoney
	 * @return:  void 
	 * @Date :   2019-04-15 16:36:13   
	 */
	public void setNowMoney(BigDecimal nowMoney) {
		this.nowMoney = nowMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-15 16:36:13    
	 */
	public String getAccountId() {
		return accountId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2019-04-15 16:36:13   
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-15 16:36:13    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-04-15 16:36:13   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 13:57:48 
 */
package hry.cm2.account.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm2AccountRecord </p>
 * @author:         yaozh
 * @Date :          2019-10-14 13:57:48  
 */
@Table(name="cm2_account_record")
public class Cm2AccountRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "orderNum")
	private String orderNum;  //
	
	@Column(name= "accountId")
	private Long accountId;  //数字货币账户id（dm_account）
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种
	
	@Column(name= "recordType")
	private Integer recordType;  //流水类型 （ 1增加 2减少）
	
	@Column(name= "transactionMoney")
	private BigDecimal transactionMoney;  //交易金额
	
	@Column(name= "balanceMoney")
	private BigDecimal balanceMoney;  //
	
	@Column(name= "transactionNum")
	private String transactionNum;  //
	
	@Column(name= "remark")
	private String remark;  //交易备注
	
	@Column(name= "remarkkey")
	private Integer remarkkey;  //交易备注1,2,3
	
	@Column(name= "monteyType")
	private Integer monteyType;  //1热账户2冷账户3已用账户
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public String getOrderNum() {
		return orderNum;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param orderNum
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	/**
	 * <p>数字货币账户id（dm_account）</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>数字货币账户id（dm_account）</p>
	 * @author:  yaozh
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozh
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  yaozh
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>流水类型 （ 1增加 2减少）</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public Integer getRecordType() {
		return recordType;
	}
	
	/**
	 * <p>流水类型 （ 1增加 2减少）</p>
	 * @author:  yaozh
	 * @param:   @param recordType
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	
	
	/**
	 * <p>交易金额</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}
	
	/**
	 * <p>交易金额</p>
	 * @author:  yaozh
	 * @param:   @param transactionMoney
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public BigDecimal getBalanceMoney() {
		return balanceMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param balanceMoney
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setBalanceMoney(BigDecimal balanceMoney) {
		this.balanceMoney = balanceMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>交易备注</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>交易备注</p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>交易备注1,2,3</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public Integer getRemarkkey() {
		return remarkkey;
	}
	
	/**
	 * <p>交易备注1,2,3</p>
	 * @author:  yaozh
	 * @param:   @param remarkkey
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setRemarkkey(Integer remarkkey) {
		this.remarkkey = remarkkey;
	}
	
	
	/**
	 * <p>1热账户2冷账户3已用账户</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-14 13:57:48    
	 */
	public Integer getMonteyType() {
		return monteyType;
	}
	
	/**
	 * <p>1热账户2冷账户3已用账户</p>
	 * @author:  yaozh
	 * @param:   @param monteyType
	 * @return:  void 
	 * @Date :   2019-10-14 13:57:48   
	 */
	public void setMonteyType(Integer monteyType) {
		this.monteyType = monteyType;
	}
	
	

}

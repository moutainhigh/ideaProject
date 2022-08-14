/**
 * Copyright:   
 * @author:      gaomimi
 * @version:     V1.0 
 * @Date:        2019-01-21 18:07:32 
 */
package hry.cm5.account.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> ContractMainAccountRecord </p>
 * @author:         gaomimi
 * @Date :          2019-01-21 18:07:32  
 */
@Table(name="contract_main_account_record")
public class ContractMainAccountRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	@Column(name= "orderNum")
	private String orderNum;  //
	@Column(name= "coinCode")
	private String coinCode;  //币种
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "recordType")
	private Integer recordType;  //流水类型 合约账号 （ 1增加 2减少）
	
	@Column(name= "transactionMoney")
	private BigDecimal transactionMoney;  //交易金额
	
	@Column(name= "remark")
	private String remark;  //交易备注，contract定期合约，contractedone永续合约，二元期权


	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @return:  Long 
	 * @Date :   2019-01-21 18:07:32    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  gaomimi
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-21 18:07:32   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  gaomimi
	 * @return:  String 
	 * @Date :   2019-01-21 18:07:32    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  gaomimi
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-01-21 18:07:32   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  gaomimi
	 * @return:  Long 
	 * @Date :   2019-01-21 18:07:32    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  gaomimi
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-01-21 18:07:32   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>流水类型 合约账号 （ 1增加 2减少）</p>
	 * @author:  gaomimi
	 * @return:  Integer 
	 * @Date :   2019-01-21 18:07:32    
	 */
	public Integer getRecordType() {
		return recordType;
	}
	
	/**
	 * <p>流水类型 合约账号 （ 1增加 2减少）</p>
	 * @author:  gaomimi
	 * @param:   @param recordType
	 * @return:  void 
	 * @Date :   2019-01-21 18:07:32   
	 */
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	
	
	/**
	 * <p>交易金额</p>
	 * @author:  gaomimi
	 * @return:  BigDecimal 
	 * @Date :   2019-01-21 18:07:32    
	 */
	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}
	
	/**
	 * <p>交易金额</p>
	 * @author:  gaomimi
	 * @param:   @param transactionMoney
	 * @return:  void 
	 * @Date :   2019-01-21 18:07:32   
	 */
	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	
	
	/**
	 * <p>交易备注</p>
	 * @author:  gaomimi
	 * @return:  String 
	 * @Date :   2019-01-21 18:07:32    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>交易备注</p>
	 * @author:  gaomimi
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-01-21 18:07:32   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

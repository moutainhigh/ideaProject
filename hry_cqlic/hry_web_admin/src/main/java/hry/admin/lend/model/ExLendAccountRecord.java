/**
 * Copyright:   
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-11-06 16:45:27 
 */
package hry.admin.lend.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExLendAccountRecord </p>
 * @author:         HeC
 * @Date :          2018-11-06 16:45:27  
 */
@Table(name="ex_lend_account_record")
public class ExLendAccountRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //交易对
	
	@Column(name= "customerId")
	private Long customerId;  //用户
	
	@Column(name= "code")
	private String code;  //交易币种
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "count")
	private BigDecimal count;  //操作数量
	
	@Column(name= "beforeCount")
	private BigDecimal beforeCount;  //交易前币余额
	
	@Column(name= "afterCount")
	private BigDecimal afterCount;  //交易后币余额
	
	@Column(name= "beforeColdCount")
	private BigDecimal beforeColdCount;  //交易前冻结余额
	
	@Column(name= "afterColdCount")
	private BigDecimal afterColdCount;  //交易后冻结余额
	
	@Column(name= "remark1")
	private String remark1;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  HeC
	 * @return:  Long 
	 * @Date :   2018-11-06 16:45:27    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  HeC
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-06 16:45:27   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>交易对</p>
	 * @author:  HeC
	 * @return:  String 
	 * @Date :   2018-11-06 16:45:27    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>交易对</p>
	 * @author:  HeC
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-11-06 16:45:27   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>用户</p>
	 * @author:  HeC
	 * @return:  Long 
	 * @Date :   2018-11-06 16:45:27    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户</p>
	 * @author:  HeC
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-11-06 16:45:27   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>交易币种</p>
	 * @author:  HeC
	 * @return:  String 
	 * @Date :   2018-11-06 16:45:27    
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * <p>交易币种</p>
	 * @author:  HeC
	 * @param:   @param code
	 * @return:  void 
	 * @Date :   2018-11-06 16:45:27   
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  HeC
	 * @return:  String 
	 * @Date :   2018-11-06 16:45:27    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  HeC
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-11-06 16:45:27   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>操作数量</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-11-06 16:45:27    
	 */
	public BigDecimal getCount() {
		return count;
	}
	
	/**
	 * <p>操作数量</p>
	 * @author:  HeC
	 * @param:   @param count
	 * @return:  void 
	 * @Date :   2018-11-06 16:45:27   
	 */
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	
	
	/**
	 * <p>交易前币余额</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-11-06 16:45:27    
	 */
	public BigDecimal getBeforeCount() {
		return beforeCount;
	}
	
	/**
	 * <p>交易前币余额</p>
	 * @author:  HeC
	 * @param:   @param beforeCount
	 * @return:  void 
	 * @Date :   2018-11-06 16:45:27   
	 */
	public void setBeforeCount(BigDecimal beforeCount) {
		this.beforeCount = beforeCount;
	}
	
	
	/**
	 * <p>交易后币余额</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-11-06 16:45:27    
	 */
	public BigDecimal getAfterCount() {
		return afterCount;
	}
	
	/**
	 * <p>交易后币余额</p>
	 * @author:  HeC
	 * @param:   @param afterCount
	 * @return:  void 
	 * @Date :   2018-11-06 16:45:27   
	 */
	public void setAfterCount(BigDecimal afterCount) {
		this.afterCount = afterCount;
	}
	
	
	/**
	 * <p>交易前冻结余额</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-11-06 16:45:27    
	 */
	public BigDecimal getBeforeColdCount() {
		return beforeColdCount;
	}
	
	/**
	 * <p>交易前冻结余额</p>
	 * @author:  HeC
	 * @param:   @param beforeColdCount
	 * @return:  void 
	 * @Date :   2018-11-06 16:45:27   
	 */
	public void setBeforeColdCount(BigDecimal beforeColdCount) {
		this.beforeColdCount = beforeColdCount;
	}
	
	
	/**
	 * <p>交易后冻结余额</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-11-06 16:45:27    
	 */
	public BigDecimal getAfterColdCount() {
		return afterColdCount;
	}
	
	/**
	 * <p>交易后冻结余额</p>
	 * @author:  HeC
	 * @param:   @param afterColdCount
	 * @return:  void 
	 * @Date :   2018-11-06 16:45:27   
	 */
	public void setAfterColdCount(BigDecimal afterColdCount) {
		this.afterColdCount = afterColdCount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  HeC
	 * @return:  String 
	 * @Date :   2018-11-06 16:45:27    
	 */
	public String getRemark1() {
		return remark1;
	}
	
	/**
	 * <p></p>
	 * @author:  HeC
	 * @param:   @param remark1
	 * @return:  void 
	 * @Date :   2018-11-06 16:45:27   
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	

}

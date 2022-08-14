/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2019-01-15 14:33:22 
 */
package hry.ico.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IcoTransactionRecord </p>
 * @author:         denghf
 * @Date :          2019-01-15 14:33:22  
 */
@Table(name="ico_transaction_record")
public class IcoTransactionRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private String id;  //
	
	@Column(name= "projectNumber")
	private String projectNumber;  //流水号
	
	@Column(name= "type")
	private Integer type;  //类型( 11.锁仓12.释放 13锁仓扣币21转账入22转账出31分红32推荐奖励 41.充币42.提币51.买入52.卖出)
	
	@Column(name= "coinCode")
	private String coinCode;  //币种Code
	
	@Column(name= "transactionCount")
	private BigDecimal transactionCount;  //交易量
	
	@Column(name= "totalMoney")
	private BigDecimal totalMoney;  //总数量
	
	@Column(name= "hotMoney")
	private BigDecimal hotMoney;  //可用数量
	
	@Column(name= "coldMoney")
	private BigDecimal coldMoney;  //冻结数量
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "state")
	private Integer state;  //状态类型(101冻结 默认102解冻201.支出202.收入)
	
	@Column(name= "remark")
	private String remark;  //备注

	@Column(name= "foreignKey")
	private Long foreignKey;//外键Id

	@Column(name= "isShow")
	private Integer isShow;//是否显示

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Long getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(Long foreignKey) {
		this.foreignKey = foreignKey;
	}

	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2019-01-15 14:33:22    
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-15 14:33:22   
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-15 14:33:22    
	 */
	public String getProjectNumber() {
		return projectNumber;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  denghf
	 * @param:   @param projectNumber
	 * @return:  void 
	 * @Date :   2019-01-15 14:33:22   
	 */
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	
	
	/**
	 * <p>类型( 11.锁仓12.释放21转账31分红32推荐奖励 41.充币42.提币51.买入52.卖出)</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2019-01-15 14:33:22    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型( 11.锁仓12.释放21转账31分红32推荐奖励 41.充币42.提币51.买入52.卖出)</p>
	 * @author:  denghf
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-01-15 14:33:22   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>币种Code</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-15 14:33:22    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种Code</p>
	 * @author:  denghf
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-01-15 14:33:22   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>交易量</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2019-01-15 14:33:22    
	 */
	public BigDecimal getTransactionCount() {
		return transactionCount;
	}
	
	/**
	 * <p>交易量</p>
	 * @author:  denghf
	 * @param:   @param transactionCount
	 * @return:  void 
	 * @Date :   2019-01-15 14:33:22   
	 */
	public void setTransactionCount(BigDecimal transactionCount) {
		this.transactionCount = transactionCount;
	}
	
	
	/**
	 * <p>总数量</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2019-01-15 14:33:22    
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	
	/**
	 * <p>总数量</p>
	 * @author:  denghf
	 * @param:   @param totalMoney
	 * @return:  void 
	 * @Date :   2019-01-15 14:33:22   
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
	/**
	 * <p>可用数量</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2019-01-15 14:33:22    
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	/**
	 * <p>可用数量</p>
	 * @author:  denghf
	 * @param:   @param hotMoney
	 * @return:  void 
	 * @Date :   2019-01-15 14:33:22   
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	
	
	/**
	 * <p>冻结数量</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2019-01-15 14:33:22    
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	/**
	 * <p>冻结数量</p>
	 * @author:  denghf
	 * @param:   @param coldMoney
	 * @return:  void 
	 * @Date :   2019-01-15 14:33:22   
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-15 14:33:22    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  denghf
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-01-15 14:33:22   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>状态类型(101冻结 默认102解冻201.支出202.收入)</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2019-01-15 14:33:22    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>状态类型(101冻结 默认102解冻201.支出202.收入)</p>
	 * @author:  denghf
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-01-15 14:33:22   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2019-01-15 14:33:22    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  denghf
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-01-15 14:33:22   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

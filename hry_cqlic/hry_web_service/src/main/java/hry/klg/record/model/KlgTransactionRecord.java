/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-28 16:11:36 
 */
package hry.klg.record.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgTransactionRecord </p>
 * @author:         lzy
 * @Date :          2019-04-28 16:11:36  
 */
@Table(name="klg_transaction_record")
public class KlgTransactionRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "projectNumber")
	private String projectNumber;  //流水号
	
	@Column(name= "type")
	private Integer type;  //类型( )
	
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
	private Long foreignKey;  //
	
	@Column(name= "isShow")
	private Integer isShow;  //0不显示1显示


	public KlgTransactionRecord() {
	}

	public KlgTransactionRecord(String projectNumber, Integer type, String coinCode, BigDecimal transactionCount, BigDecimal totalMoney, BigDecimal hotMoney, BigDecimal coldMoney, Long customerId, Integer state, String remark, Long foreignKey) {
		this.projectNumber = projectNumber;
		this.type = type;
		this.coinCode = coinCode;
		this.transactionCount = transactionCount;
		this.totalMoney = totalMoney;
		this.hotMoney = hotMoney;
		this.coldMoney = coldMoney;
		this.customerId = customerId;
		this.state = state;
		this.remark = remark;
		this.foreignKey = foreignKey;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public String getProjectNumber() {
		return projectNumber;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  lzy
	 * @param:   @param projectNumber
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	
	
	/**
	 * <p>类型( )</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型( )</p>
	 * @author:  lzy
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>币种Code</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种Code</p>
	 * @author:  lzy
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>交易量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public BigDecimal getTransactionCount() {
		return transactionCount;
	}
	
	/**
	 * <p>交易量</p>
	 * @author:  lzy
	 * @param:   @param transactionCount
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setTransactionCount(BigDecimal transactionCount) {
		this.transactionCount = transactionCount;
	}
	
	
	/**
	 * <p>总数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	
	/**
	 * <p>总数量</p>
	 * @author:  lzy
	 * @param:   @param totalMoney
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
	/**
	 * <p>可用数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	/**
	 * <p>可用数量</p>
	 * @author:  lzy
	 * @param:   @param hotMoney
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	
	
	/**
	 * <p>冻结数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	/**
	 * <p>冻结数量</p>
	 * @author:  lzy
	 * @param:   @param coldMoney
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>状态类型(101冻结 默认102解冻201.支出202.收入)</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>状态类型(101冻结 默认102解冻201.支出202.收入)</p>
	 * @author:  lzy
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  lzy
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public Long getForeignKey() {
		return foreignKey;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param foreignKey
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setForeignKey(Long foreignKey) {
		this.foreignKey = foreignKey;
	}
	
	
	/**
	 * <p>0不显示1显示</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-28 16:11:36    
	 */
	public Integer getIsShow() {
		return isShow;
	}
	
	/**
	 * <p>0不显示1显示</p>
	 * @author:  lzy
	 * @param:   @param isShow
	 * @return:  void 
	 * @Date :   2019-04-28 16:11:36   
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
	

}

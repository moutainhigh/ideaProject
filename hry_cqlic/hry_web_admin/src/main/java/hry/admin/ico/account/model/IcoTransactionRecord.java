/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 16:50:30 
 */
package hry.admin.ico.account.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> IcoTransactionRecord </p>
 * @author:         lzy
 * @Date :          2019-01-14 16:50:30  
 */
@Table(name="ico_transaction_record")
public class IcoTransactionRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "projectNumber")
	private String projectNumber;  //流水号
	
	@Column(name= "type")
	private Integer type; //（词典key【transaction_type】）类型 11.锁仓12.释放21转账31分红32推荐奖励
	 							//41.充币42.提币51.买入52.卖出
	
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
	private Integer state;  //（词典key【fundStatus】）状态类型101冻结 默认102解冻201.支出202.收入
	
	@Column(name= "remark")
	private String remark;  //备注




	@Transient // 不与数据库映射的字段
	private String mobilePhone;   //用户手机号

	@Transient // 不与数据库映射的字段
	private String email;      	//用户email

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-14 16:50:30    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-14 16:50:30   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-14 16:50:30    
	 */
	public String getProjectNumber() {
		return projectNumber;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  lzy
	 * @param:   @param projectNumber
	 * @return:  void 
	 * @Date :   2019-01-14 16:50:30   
	 */
	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	
	
	/**
	 * <p>类型</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-01-14 16:50:30    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型</p>
	 * @author:  lzy
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-01-14 16:50:30   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>币种Code</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-14 16:50:30    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种Code</p>
	 * @author:  lzy
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-01-14 16:50:30   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>交易量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 16:50:30    
	 */
	public BigDecimal getTransactionCount() {
		return transactionCount;
	}
	
	/**
	 * <p>交易量</p>
	 * @author:  lzy
	 * @param:   @param transactionCount
	 * @return:  void 
	 * @Date :   2019-01-14 16:50:30   
	 */
	public void setTransactionCount(BigDecimal transactionCount) {
		this.transactionCount = transactionCount;
	}
	
	
	/**
	 * <p>总数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 16:50:30    
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	
	/**
	 * <p>总数量</p>
	 * @author:  lzy
	 * @param:   @param totalMoney
	 * @return:  void 
	 * @Date :   2019-01-14 16:50:30   
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
	/**
	 * <p>可用数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 16:50:30    
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	/**
	 * <p>可用数量</p>
	 * @author:  lzy
	 * @param:   @param hotMoney
	 * @return:  void 
	 * @Date :   2019-01-14 16:50:30   
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	
	
	/**
	 * <p>冻结数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-14 16:50:30    
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	/**
	 * <p>冻结数量</p>
	 * @author:  lzy
	 * @param:   @param coldMoney
	 * @return:  void 
	 * @Date :   2019-01-14 16:50:30   
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-14 16:50:30    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-01-14 16:50:30   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>状态类型</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-01-14 16:50:30    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>状态类型</p>
	 * @author:  lzy
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-01-14 16:50:30   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-14 16:50:30    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  lzy
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-01-14 16:50:30   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

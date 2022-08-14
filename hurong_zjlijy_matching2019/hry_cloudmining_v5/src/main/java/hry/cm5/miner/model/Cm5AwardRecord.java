/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:06:05 
 */
package hry.cm5.miner.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm5AwardRecord </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:06:05  
 */
@Table(name="cm5_award_record")
public class Cm5AwardRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "minerOrderId")
	private Long minerOrderId;  //矿机订单ID
	
	@Column(name= "customerId")
	private Long customerId;  //奖励人Id
	
	@Column(name= "inheritNum")
	private String inheritNum;  //继承码(用于矿机继承)
	
	@Column(name= "orderNum")
	private String orderNum;  //订单号
	
	@Column(name= "transactionNum")
	private String transactionNum;  //业务流水号
	
	@Column(name= "awardCode")
	private String awardCode;  //收益币种

	@Column(name= "awardRatio")
	private String awardRatio; //收益比例
	
	@Column(name= "awardOne")
	private BigDecimal awardOne;  //应发收益
	
	@Column(name= "awardTwo")
	private BigDecimal awardTwo;  //实发收益
	
	@Column(name= "awardThree")
	private BigDecimal awardThree;  //冻结收益
	
	@Column(name= "realityAwardCode")
	private String realityAwardCode;  //实际收益币种
	
	@Column(name= "realityRate")
	private BigDecimal realityRate;  //实际汇率
	
	@Column(name= "realityAwardTwo")
	private BigDecimal realityAwardTwo;  //实际发放数额
	
	@Column(name= "status")
	private Integer status;  //发放状态 1.未发放 2已发放
	
	@Column(name= "awardType")
	private Integer awardType;  //奖励类型 1、US奖励 2、USKC奖励 3、管道奖励 4、冻结
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "saasId")
	private String saasId;  //


	public String getAwardRatio() {
		return awardRatio;
	}

	public void setAwardRatio(String awardRatio) {
		this.awardRatio = awardRatio;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>矿机订单ID</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public Long getMinerOrderId() {
		return minerOrderId;
	}
	
	/**
	 * <p>矿机订单ID</p>
	 * @author:  zhouming
	 * @param:   @param minerOrderId
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setMinerOrderId(Long minerOrderId) {
		this.minerOrderId = minerOrderId;
	}
	
	
	/**
	 * <p>奖励人Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>奖励人Id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>继承码(用于矿机继承)</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public String getInheritNum() {
		return inheritNum;
	}
	
	/**
	 * <p>继承码(用于矿机继承)</p>
	 * @author:  zhouming
	 * @param:   @param inheritNum
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setInheritNum(String inheritNum) {
		this.inheritNum = inheritNum;
	}
	
	
	/**
	 * <p>订单号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public String getOrderNum() {
		return orderNum;
	}
	
	/**
	 * <p>订单号</p>
	 * @author:  zhouming
	 * @param:   @param orderNum
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	/**
	 * <p>业务流水号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>业务流水号</p>
	 * @author:  zhouming
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>收益币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public String getAwardCode() {
		return awardCode;
	}
	
	/**
	 * <p>收益币种</p>
	 * @author:  zhouming
	 * @param:   @param awardCode
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setAwardCode(String awardCode) {
		this.awardCode = awardCode;
	}
	
	
	/**
	 * <p>应发收益</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public BigDecimal getAwardOne() {
		return awardOne;
	}
	
	/**
	 * <p>应发收益</p>
	 * @author:  zhouming
	 * @param:   @param awardOne
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setAwardOne(BigDecimal awardOne) {
		this.awardOne = awardOne;
	}
	
	
	/**
	 * <p>实发收益</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public BigDecimal getAwardTwo() {
		return awardTwo;
	}
	
	/**
	 * <p>实发收益</p>
	 * @author:  zhouming
	 * @param:   @param awardTwo
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setAwardTwo(BigDecimal awardTwo) {
		this.awardTwo = awardTwo;
	}
	
	
	/**
	 * <p>冻结收益</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public BigDecimal getAwardThree() {
		return awardThree;
	}
	
	/**
	 * <p>冻结收益</p>
	 * @author:  zhouming
	 * @param:   @param awardThree
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setAwardThree(BigDecimal awardThree) {
		this.awardThree = awardThree;
	}
	
	
	/**
	 * <p>实际收益币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public String getRealityAwardCode() {
		return realityAwardCode;
	}
	
	/**
	 * <p>实际收益币种</p>
	 * @author:  zhouming
	 * @param:   @param realityAwardCode
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setRealityAwardCode(String realityAwardCode) {
		this.realityAwardCode = realityAwardCode;
	}
	
	
	/**
	 * <p>实际汇率</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public BigDecimal getRealityRate() {
		return realityRate;
	}
	
	/**
	 * <p>实际汇率</p>
	 * @author:  zhouming
	 * @param:   @param realityRate
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setRealityRate(BigDecimal realityRate) {
		this.realityRate = realityRate;
	}
	
	
	/**
	 * <p>实际发放数额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public BigDecimal getRealityAwardTwo() {
		return realityAwardTwo;
	}
	
	/**
	 * <p>实际发放数额</p>
	 * @author:  zhouming
	 * @param:   @param realityAwardTwo
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setRealityAwardTwo(BigDecimal realityAwardTwo) {
		this.realityAwardTwo = realityAwardTwo;
	}
	
	
	/**
	 * <p>发放状态 1.未发放 2已发放</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>发放状态 1.未发放 2已发放</p>
	 * @author:  zhouming
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>奖励类型 1、US奖励 2、USKC奖励 3、管道奖励</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public Integer getAwardType() {
		return awardType;
	}
	
	/**
	 * <p>奖励类型 1、US奖励 2、USKC奖励 3、管道奖励</p>
	 * @author:  zhouming
	 * @param:   @param awardType
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:06:05    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-01-08 14:06:05   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

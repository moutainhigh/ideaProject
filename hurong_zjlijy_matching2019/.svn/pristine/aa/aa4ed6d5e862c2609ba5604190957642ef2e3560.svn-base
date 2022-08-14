/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:00:54 
 */
package hry.cm4.customerminer.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm4CustomerMinerProfit </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:00:54  
 */
@Table(name="cm4_customer_miner_profit")
public class Cm4CustomerMinerProfit extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "orderId")
	private Long orderId;  //订单ID
	
	@Column(name= "minerId")
	private Long minerId;  //矿机ID
	
	@Column(name= "customerId")
	private Long customerId;  //购买人Id
	
	@Column(name= "customerMinerId")
	private Long customerMinerId;  //购买矿机Id
	
	@Column(name= "transactionNum")
	private String transactionNum;  //订单号
	
	@Column(name= "minerName")
	private String minerName;  //矿机名称
	
	@Column(name= "minerCode")
	private String minerCode;  //矿机编号
	
	@Column(name= "minerPrice")
	private BigDecimal minerPrice;  //矿机价钱
	
	@Column(name= "profitCoinCode")
	private String profitCoinCode;  //产出收益币种
	
	@Column(name= "profitProportion")
	private BigDecimal profitProportion;  //币种收益比例
	
	@Column(name= "profit1")
	private BigDecimal profit1;  //本次产币收益
	
	@Column(name= "profit2")
	private BigDecimal profit2;  //累计产币收益
	
	@Column(name= "status")
	private Integer status;  //发放状态 1.未发放 2已发放 3果树领取
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>订单ID</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单ID</p>
	 * @author:  yaozh
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>矿机ID</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public Long getMinerId() {
		return minerId;
	}
	
	/**
	 * <p>矿机ID</p>
	 * @author:  yaozh
	 * @param:   @param minerId
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setMinerId(Long minerId) {
		this.minerId = minerId;
	}
	
	
	/**
	 * <p>购买人Id</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>购买人Id</p>
	 * @author:  yaozh
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>购买矿机Id</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public Long getCustomerMinerId() {
		return customerMinerId;
	}
	
	/**
	 * <p>购买矿机Id</p>
	 * @author:  yaozh
	 * @param:   @param customerMinerId
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setCustomerMinerId(Long customerMinerId) {
		this.customerMinerId = customerMinerId;
	}
	
	
	/**
	 * <p>订单号</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>订单号</p>
	 * @author:  yaozh
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>矿机名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public String getMinerName() {
		return minerName;
	}
	
	/**
	 * <p>矿机名称</p>
	 * @author:  yaozh
	 * @param:   @param minerName
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setMinerName(String minerName) {
		this.minerName = minerName;
	}
	
	
	/**
	 * <p>矿机编号</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public String getMinerCode() {
		return minerCode;
	}
	
	/**
	 * <p>矿机编号</p>
	 * @author:  yaozh
	 * @param:   @param minerCode
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setMinerCode(String minerCode) {
		this.minerCode = minerCode;
	}
	
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public BigDecimal getMinerPrice() {
		return minerPrice;
	}
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  yaozh
	 * @param:   @param minerPrice
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setMinerPrice(BigDecimal minerPrice) {
		this.minerPrice = minerPrice;
	}
	
	
	/**
	 * <p>产出收益币种</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public String getProfitCoinCode() {
		return profitCoinCode;
	}
	
	/**
	 * <p>产出收益币种</p>
	 * @author:  yaozh
	 * @param:   @param profitCoinCode
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setProfitCoinCode(String profitCoinCode) {
		this.profitCoinCode = profitCoinCode;
	}
	
	
	/**
	 * <p>币种收益比例</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public BigDecimal getProfitProportion() {
		return profitProportion;
	}
	
	/**
	 * <p>币种收益比例</p>
	 * @author:  yaozh
	 * @param:   @param profitProportion
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setProfitProportion(BigDecimal profitProportion) {
		this.profitProportion = profitProportion;
	}
	
	
	/**
	 * <p>本次产币收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public BigDecimal getProfit1() {
		return profit1;
	}
	
	/**
	 * <p>本次产币收益</p>
	 * @author:  yaozh
	 * @param:   @param profit1
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setProfit1(BigDecimal profit1) {
		this.profit1 = profit1;
	}
	
	
	/**
	 * <p>累计产币收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public BigDecimal getProfit2() {
		return profit2;
	}
	
	/**
	 * <p>累计产币收益</p>
	 * @author:  yaozh
	 * @param:   @param profit2
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setProfit2(BigDecimal profit2) {
		this.profit2 = profit2;
	}
	
	
	/**
	 * <p>发放状态 1.未发放 2已发放 3果树领取</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>发放状态 1.未发放 2已发放 3果树领取</p>
	 * @author:  yaozh
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:00:54    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-11-21 10:00:54   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

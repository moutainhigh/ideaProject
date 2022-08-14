/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午4:25:12
 */
package hry.trade.robot.model;

import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年4月19日 下午8:14:59
 */
public class TransactionOrder extends BaseModel {

	// 主键
	@Id
//	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	// 交易订单号
	@Column(name = "orderNum")
	private String orderNum;
	// 币的代码
	@Column(name = "coinCode")
	private String coinCode;
	
	//平均价格
	@Column(name = "agvPrice")
	private BigDecimal agvPrice;
	
	//最低价格
	@Column(name = "minPrice")
	private BigDecimal minPrice;
	
	//最高价格
	@Column(name = "maxPrice")
	private BigDecimal maxPrice;
	
	//开盘价格
	@Column(name = "startPrice")
	private BigDecimal startPrice;
	
	//收盘价格
	@Column(name = "endPrice")
	private BigDecimal endPrice;
	
	// 成交数量
	@Column(name = "transactionCount")
	private BigDecimal transactionCount;
	// 成交时间
	@Column(name = "transactionTime")    
	private String transactionTime;    // where 条件 大于的交易时间
	
	//成交结束时间
	@Column(name = "transactionEndTime")
	private String transactionEndTime; //where 条件小于的交易时间
	
	

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getId() {
		return id;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOrderNum() {
		return orderNum;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCoinCode() {
		return coinCode;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getAgvPrice() {
		return agvPrice;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setAgvPrice(BigDecimal agvPrice) {
		this.agvPrice = agvPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getMinPrice() {
		return minPrice;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getStartPrice() {
		return startPrice;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getEndPrice() {
		return endPrice;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setEndPrice(BigDecimal endPrice) {
		this.endPrice = endPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTransactionCount() {
		return transactionCount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTransactionCount(BigDecimal transactionCount) {
		this.transactionCount = transactionCount;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTransactionTime() {
		return transactionTime;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTransactionEndTime() {
		return transactionEndTime;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTransactionEndTime(String transactionEndTime) {
		this.transactionEndTime = transactionEndTime;
	}
	



	


}

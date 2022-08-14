/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午4:25:12
 */
package hry.trade.entrust.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseExModel;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午4:25:12
 */
@SuppressWarnings("serial")
@Table(name = "ex_order")
public class ExOrder extends BaseExModel {

	// 主键
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 交易订单号
	@Column(name = "orderNum")
	private String orderNum;
	// 币的代码
	@Column(name = "coinCode")
	private String coinCode;
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //定价币种
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //0真实货币1虚拟币
	// 虚拟币名称
	@Column(name = "productName")
	private String productName;
	// 成交价（单价）
	@Column(name = "transactionPrice")
	private BigDecimal transactionPrice;
	// 成交价（总额）
	@Column(name = "transactionSum")
	private BigDecimal transactionSum;
	

	// 成交数量
	@Column(name = "transactionCount")
	private BigDecimal transactionCount;
	// 成交时间
	@Column(name = "transactionTime")
	private Date transactionTime;
	//成交时间戳
	@Column(name = "orderTimestapm")
	private Long orderTimestapm;
	//内盘buy外盘sell
	@Column(name = "inOrOutTransaction")
	private String inOrOutTransaction;

	//均价（收盘价）
	@Transient
	private BigDecimal averagePrice;

	


	

	public Integer getFixPriceType() {
		return fixPriceType;
	}


	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}


	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}


	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}


	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTransactionSum() {
		return transactionSum;
	}


	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTransactionSum(BigDecimal transactionSum) {
		this.transactionSum = transactionSum;
	}


	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getOrderTimestapm() {
		return orderTimestapm;
	}


	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setOrderTimestapm(Long orderTimestapm) {
		this.orderTimestapm = orderTimestapm;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getInOrOutTransaction() {
		return inOrOutTransaction;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setInOrOutTransaction(String inOrOutTransaction) {
		this.inOrOutTransaction = inOrOutTransaction;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	
	public String getCoinCode() {
		return coinCode;
	}

	
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	
	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}

	
	public void setTransactionPrice(BigDecimal transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	
	public BigDecimal getTransactionCount() {
		return transactionCount;
	}

	
	public void setTransactionCount(BigDecimal transactionCount) {
		this.transactionCount = transactionCount;
	}

	
	public Date getTransactionTime() {
		return transactionTime;
	}

	
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}


	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}


	public ExOrder(Long id, String orderNum, String coinCode,
			BigDecimal transactionPrice, BigDecimal transactionCount, Date transactionTime,Long timestamps) {
		super();
		this.id = id;
		this.orderNum = orderNum;
		this.coinCode = coinCode;
		this.transactionPrice = transactionPrice;
		this.transactionCount = transactionCount;
		this.transactionTime = transactionTime;
	}

	public ExOrder() {
		super();
	}

	@Override
	public String toString() {
		return "ExOrder [id=" + id + ", orderNum="
				+ orderNum + ", coinCode=" + coinCode
				+ ", transactionPrice=" + transactionPrice + ", transactionCount=" + transactionCount
				+ ", transactionTime=" + transactionTime
				+ ", timestamps=" + 
				 "]";
	}

}

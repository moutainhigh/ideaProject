/**
 * Copyright:   
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 14:48:34 
 */
package hry.admin.lend.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> ExLendEntrust </p>
 * @author:         HeC
 * @Date :          2018-10-18 14:48:34  
 */
@Table(name="ex_lend_entrust")
public class ExLendEntrust extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name= "entrustNum")
	private String entrustNum;  //委托单号
	
	@Column(name= "customerId")
	private Long customerId;  //委托人
	
	@Column(name= "coinCode")
	private String coinCode;  //交易对
	
	@Column(name= "type")
	private Integer type;  //1买 2卖
	
	@Column(name= "status")
	private Integer status;  //0未成交　1部分成交　2已完成　 3部分成交已撤销 4已撤销
	
	@Column(name= "entrustPrice")
	private BigDecimal entrustPrice;  //委托价格
	
	@Column(name= "entrustCount")
	private BigDecimal entrustCount;  //委托数量
	
	@Column(name= "entrustTime")
	private Date entrustTime;  //委托时间
	
	@Column(name= "transactionFee")
	private BigDecimal transactionFee;  //交易手续费(成交的总手续费)
	
	@Column(name= "transactionSum")
	private BigDecimal transactionSum;  //总成交量
	
	@Column(name= "processedPrice")
	private BigDecimal processedPrice;  //成交平均价
	
	@Column(name= "source")
	private Integer source;  //1用户下单 2手机端下单 4机器人下单 3强制平仓

	@Column(name= "remark")
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * <p></p>
	 * @author:  HeC
	 * @return:  Long 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  HeC
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * <p>委托单号</p>
	 * @author:  HeC
	 * @return:  String 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public String getEntrustNum() {
		return entrustNum;
	}
	
	/**
	 * <p>委托单号</p>
	 * @author:  HeC
	 * @param:   @param entrustNum
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setEntrustNum(String entrustNum) {
		this.entrustNum = entrustNum;
	}
	
	
	/**
	 * <p>委托人</p>
	 * @author:  HeC
	 * @return:  Long 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>委托人</p>
	 * @author:  HeC
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>交易对</p>
	 * @author:  HeC
	 * @return:  String 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>交易对</p>
	 * @author:  HeC
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>1买 2卖</p>
	 * @author:  HeC
	 * @return:  Integer 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>1买 2卖</p>
	 * @author:  HeC
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>0未成交　1部分成交　2已完成　 3部分成交已撤销 4已撤销   7队列中</p>
	 * @author:  HeC
	 * @return:  Integer 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>0未成交　1部分成交　2已完成　 3部分成交已撤销 4已撤销   7队列中</p>
	 * @author:  HeC
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>委托价格</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public BigDecimal getEntrustPrice() {
		return entrustPrice;
	}
	
	/**
	 * <p>委托价格</p>
	 * @author:  HeC
	 * @param:   @param entrustPrice
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setEntrustPrice(BigDecimal entrustPrice) {
		this.entrustPrice = entrustPrice;
	}
	
	
	/**
	 * <p>委托数量</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public BigDecimal getEntrustCount() {
		return entrustCount;
	}
	
	/**
	 * <p>委托数量</p>
	 * @author:  HeC
	 * @param:   @param entrustCount
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setEntrustCount(BigDecimal entrustCount) {
		this.entrustCount = entrustCount;
	}
	
	
	/**
	 * <p>委托时间</p>
	 * @author:  HeC
	 * @return:  Date 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public Date getEntrustTime() {
		return entrustTime;
	}
	
	/**
	 * <p>委托时间</p>
	 * @author:  HeC
	 * @param:   @param entrustTime
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setEntrustTime(Date entrustTime) {
		this.entrustTime = entrustTime;
	}
	
	
	/**
	 * <p>交易手续费(成交的总手续费)</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public BigDecimal getTransactionFee() {
		return transactionFee;
	}
	
	/**
	 * <p>交易手续费(成交的总手续费)</p>
	 * @author:  HeC
	 * @param:   @param transactionFee
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setTransactionFee(BigDecimal transactionFee) {
		this.transactionFee = transactionFee;
	}
	
	
	/**
	 * <p>总成交量</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public BigDecimal getTransactionSum() {
		return transactionSum;
	}
	
	/**
	 * <p>总成交量</p>
	 * @author:  HeC
	 * @param:   @param transactionSum
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setTransactionSum(BigDecimal transactionSum) {
		this.transactionSum = transactionSum;
	}
	
	
	/**
	 * <p>成交平均价</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public BigDecimal getProcessedPrice() {
		return processedPrice;
	}
	
	/**
	 * <p>成交平均价</p>
	 * @author:  HeC
	 * @param:   @param processedPrice
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setProcessedPrice(BigDecimal processedPrice) {
		this.processedPrice = processedPrice;
	}
	
	
	/**
	 * <p>1用户下单 2手机端下单 4机器人下单 3强制平仓</p>
	 * @author:  HeC
	 * @return:  Integer 
	 * @Date :   2018-10-18 14:48:34    
	 */
	public Integer getSource() {
		return source;
	}
	
	/**
	 * <p>1用户下单 2手机端下单 4机器人下单 3强制平仓</p>
	 * @author:  HeC
	 * @param:   @param source
	 * @return:  void 
	 * @Date :   2018-10-18 14:48:34   
	 */
	public void setSource(Integer source) {
		this.source = source;
	}
	
	

}

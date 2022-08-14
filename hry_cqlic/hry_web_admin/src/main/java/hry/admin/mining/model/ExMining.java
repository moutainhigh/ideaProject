/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2018-06-25 13:01:36 
 */
package hry.admin.mining.model;

import hry.admin.exchange.model.ExEntrust;
import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p> ExMining </p>
 * @author:         jidn
 * @Date :          2018-06-25 13:01:36  
 */
@Table(name="ex_mining")
public class ExMining extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "extrustId")
	private String extrustId;  //订单ID
	
	@Column(name= "totalFee")
	private BigDecimal totalFee; //产生的手续费(折算成平台币的)
	
	@Column(name= "returnCoin")
	private BigDecimal returnCoin;  //返还平台币数量
	
	@Column(name= "status")
	private Integer status;  //是否返还 0 待返还 1 已返还
	
	@Column(name= "platformCoinAvg")
	private BigDecimal platformCoinAvg;//返还时平台币均价
	
	@Column(name= "customerId")
	private Long customerId;//客户customerId
	
	@Column(name= "accountId")
	private Long accountId;//账户ID
	
	@Column(name= "platformCoin")
	private String platformCoin;//平台币
	
	@Column(name= "coinCode")
	private String coinCode; //交易币种
	
	
	@Transient
	private ExEntrust exentrust;
	@Transient
	private String cardId;
	@Transient
	private String coin;//手续费单位
	
	@Transient
	private Date transactionTime;
	@Transient
	private String orderNum;
	@Transient
	private String sourceId;
	
	
	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public String getCoin() {
		return coin;
	}

	public void setCoin(String coin) {
		this.coin = coin;
	}

	public BigDecimal getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getPlatformCoin() {
		return platformCoin;
	}

	public void setPlatformCoin(String platformCoin) {
		this.platformCoin = platformCoin;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public BigDecimal getPlatformCoinAvg() {
		return platformCoinAvg;
	}

	public void setPlatformCoinAvg(BigDecimal platformCoinAvg) {
		this.platformCoinAvg = platformCoinAvg;
	}

	public ExEntrust getExentrust() {
		return exentrust;
	}

	public void setExentrust(ExEntrust exentrust) {
		this.exentrust = exentrust;
	}

	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @return:  Long 
	 * @Date :   2018-06-25 13:01:36    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-25 13:01:36   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	

	public String getExtrustId() {
		return extrustId;
	}

	public void setExtrustId(String extrustId) {
		this.extrustId = extrustId;
	}

	/**
	 * <p>返还平台币数量</p>
	 * @author:  jidn
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 13:01:36    
	 */
	public BigDecimal getReturnCoin() {
		return returnCoin;
	}
	
	/**
	 * <p>返还平台币数量</p>
	 * @author:  jidn
	 * @param:   @param returnCoin
	 * @return:  void 
	 * @Date :   2018-06-25 13:01:36   
	 */
	public void setReturnCoin(BigDecimal returnCoin) {
		this.returnCoin = returnCoin;
	}
	
	
	/**
	 * <p>是否返还 0 待返还 1 已返还</p>
	 * @author:  jidn
	 * @return:  Integer 
	 * @Date :   2018-06-25 13:01:36    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>是否返还 0 待返还 1 已返还</p>
	 * @author:  jidn
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-25 13:01:36   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}

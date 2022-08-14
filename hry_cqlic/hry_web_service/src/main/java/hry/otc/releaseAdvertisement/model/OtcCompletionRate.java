/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-12-21 18:25:14 
 */
package hry.otc.releaseAdvertisement.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> OtcCompletionRate </p>
 * @author:         denghf
 * @Date :          2018-12-21 18:25:14  
 */
@Table(name="otc_completion_rate")
public class OtcCompletionRate extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种
	
	@Column(name= "tradeOk")
	private Long tradeOk;  //订单完成数量
	
	@Column(name= "tradeAll")
	private Long tradeAll;  //订单总数量
	
	@Column(name= "completionRate")
	private BigDecimal completionRate;  //完成率
	
	
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-12-21 18:25:14    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-21 18:25:14   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-12-21 18:25:14    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  denghf
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-12-21 18:25:14   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-12-21 18:25:14    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  denghf
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-12-21 18:25:14   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>订单完成数量</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-12-21 18:25:14    
	 */
	public Long getTradeOk() {
		return tradeOk;
	}
	
	/**
	 * <p>订单完成数量</p>
	 * @author:  denghf
	 * @param:   @param tradeOk
	 * @return:  void 
	 * @Date :   2018-12-21 18:25:14   
	 */
	public void setTradeOk(Long tradeOk) {
		this.tradeOk = tradeOk;
	}
	
	
	/**
	 * <p>订单总数量</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-12-21 18:25:14    
	 */
	public Long getTradeAll() {
		return tradeAll;
	}
	
	/**
	 * <p>订单总数量</p>
	 * @author:  denghf
	 * @param:   @param tradeAll
	 * @return:  void 
	 * @Date :   2018-12-21 18:25:14   
	 */
	public void setTradeAll(Long tradeAll) {
		this.tradeAll = tradeAll;
	}
	
	
	/**
	 * <p>完成率</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-12-21 18:25:14    
	 */
	public BigDecimal getCompletionRate() {
		return completionRate;
	}
	
	/**
	 * <p>完成率</p>
	 * @author:  denghf
	 * @param:   @param completionRate
	 * @return:  void 
	 * @Date :   2018-12-21 18:25:14   
	 */
	public void setCompletionRate(BigDecimal completionRate) {
		this.completionRate = completionRate;
	}
	
	

}

/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-26 17:05:34 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExCoinExchangeRate </p>
 * @author:         denghf
 * @Date :          2018-10-26 17:05:34  
 */
@Table(name="ex_coin_exchange_rate")
public class ExCoinExchangeRate extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "baseCoinCode")
	private String baseCoinCode;  //基础币code
	
	@Column(name= "baseCoinName")
	private String baseCoinName;  //基础币名称
	
	@Column(name= "targetCoinCode")
	private String targetCoinCode;  //目标币code
	
	@Column(name= "targetCoinName")
	private String targetCoinName;  //目标币名称
	
	@Column(name= "exchangeRate")
	private BigDecimal exchangeRate;  //目标币对基础币汇率
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-26 17:05:34    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-10-26 17:05:34   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>基础币code</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 17:05:34    
	 */
	public String getBaseCoinCode() {
		return baseCoinCode;
	}
	
	/**
	 * <p>基础币code</p>
	 * @author:  denghf
	 * @param:   @param baseCoinCode
	 * @return:  void 
	 * @Date :   2018-10-26 17:05:34   
	 */
	public void setBaseCoinCode(String baseCoinCode) {
		this.baseCoinCode = baseCoinCode;
	}
	
	
	/**
	 * <p>基础币名称</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 17:05:34    
	 */
	public String getBaseCoinName() {
		return baseCoinName;
	}
	
	/**
	 * <p>基础币名称</p>
	 * @author:  denghf
	 * @param:   @param baseCoinName
	 * @return:  void 
	 * @Date :   2018-10-26 17:05:34   
	 */
	public void setBaseCoinName(String baseCoinName) {
		this.baseCoinName = baseCoinName;
	}
	
	
	/**
	 * <p>目标币code</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 17:05:34    
	 */
	public String getTargetCoinCode() {
		return targetCoinCode;
	}
	
	/**
	 * <p>目标币code</p>
	 * @author:  denghf
	 * @param:   @param targetCoinCode
	 * @return:  void 
	 * @Date :   2018-10-26 17:05:34   
	 */
	public void setTargetCoinCode(String targetCoinCode) {
		this.targetCoinCode = targetCoinCode;
	}
	
	
	/**
	 * <p>目标币名称</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 17:05:34    
	 */
	public String getTargetCoinName() {
		return targetCoinName;
	}
	
	/**
	 * <p>目标币名称</p>
	 * @author:  denghf
	 * @param:   @param targetCoinName
	 * @return:  void 
	 * @Date :   2018-10-26 17:05:34   
	 */
	public void setTargetCoinName(String targetCoinName) {
		this.targetCoinName = targetCoinName;
	}
	
	
	/**
	 * <p>目标币对基础币汇率</p>
	 * @author:  denghf
	 * @return:  BigDecimal 
	 * @Date :   2018-10-26 17:05:34    
	 */
	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}
	
	/**
	 * <p>目标币对基础币汇率</p>
	 * @author:  denghf
	 * @param:   @param exchangeRate
	 * @return:  void 
	 * @Date :   2018-10-26 17:05:34   
	 */
	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-26 17:05:34    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-10-26 17:05:34   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

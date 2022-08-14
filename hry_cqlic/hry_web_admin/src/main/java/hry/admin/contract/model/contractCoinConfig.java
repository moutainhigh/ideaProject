/**
 * Copyright:   
 * @author:      hec
 * @version:     V1.0 
 * @Date:        2018-12-27 15:00:02 
 */
package hry.admin.contract.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> contractCoinConfig </p>
 * @author:         hec
 * @Date :          2018-12-27 15:00:02  
 */
@Table(name="contract_coin_config")
public class contractCoinConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代码
	
	@Column(name= "faceValue")
	private BigDecimal faceValue;  //合约面值
	
	@Column(name= "maxBuyIn")
	private BigDecimal maxBuyIn;  //最大买入
	
	@Column(name= "buyFeeRate")
	private BigDecimal buyFeeRate;  //买入手续费
	
	@Column(name= "sellFeeRate")
	private BigDecimal sellFeeRate;  //卖出手续费
	
	@Column(name= "keepDecimal")
	private Integer keepDecimal;  //保留小数位
	
	
	
	
	/**
	 * <p></p>
	 * @author:  hec
	 * @return:  Long 
	 * @Date :   2018-12-27 15:00:02    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  hec
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-27 15:00:02   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  hec
	 * @return:  String 
	 * @Date :   2018-12-27 15:00:02    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  hec
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-12-27 15:00:02   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>合约面值</p>
	 * @author:  hec
	 * @return:  BigDecimal 
	 * @Date :   2018-12-27 15:00:02    
	 */
	public BigDecimal getFaceValue() {
		return faceValue;
	}
	
	/**
	 * <p>合约面值</p>
	 * @author:  hec
	 * @param:   @param faceValue
	 * @return:  void 
	 * @Date :   2018-12-27 15:00:02   
	 */
	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}
	
	
	/**
	 * <p>最大买入</p>
	 * @author:  hec
	 * @return:  BigDecimal 
	 * @Date :   2018-12-27 15:00:02    
	 */
	public BigDecimal getMaxBuyIn() {
		return maxBuyIn;
	}
	
	/**
	 * <p>最大买入</p>
	 * @author:  hec
	 * @param:   @param maxBuyIn
	 * @return:  void 
	 * @Date :   2018-12-27 15:00:02   
	 */
	public void setMaxBuyIn(BigDecimal maxBuyIn) {
		this.maxBuyIn = maxBuyIn;
	}
	
	
	/**
	 * <p>买入手续费</p>
	 * @author:  hec
	 * @return:  BigDecimal 
	 * @Date :   2018-12-27 15:00:02    
	 */
	public BigDecimal getBuyFeeRate() {
		return buyFeeRate;
	}
	
	/**
	 * <p>买入手续费</p>
	 * @author:  hec
	 * @param:   @param buyFeeRate
	 * @return:  void 
	 * @Date :   2018-12-27 15:00:02   
	 */
	public void setBuyFeeRate(BigDecimal buyFeeRate) {
		this.buyFeeRate = buyFeeRate;
	}
	
	
	/**
	 * <p>卖出手续费</p>
	 * @author:  hec
	 * @return:  BigDecimal 
	 * @Date :   2018-12-27 15:00:02    
	 */
	public BigDecimal getSellFeeRate() {
		return sellFeeRate;
	}
	
	/**
	 * <p>卖出手续费</p>
	 * @author:  hec
	 * @param:   @param sellFeeRate
	 * @return:  void 
	 * @Date :   2018-12-27 15:00:02   
	 */
	public void setSellFeeRate(BigDecimal sellFeeRate) {
		this.sellFeeRate = sellFeeRate;
	}
	
	
	/**
	 * <p>保留小数位</p>
	 * @author:  hec
	 * @return:  Integer 
	 * @Date :   2018-12-27 15:00:02    
	 */
	public Integer getKeepDecimal() {
		return keepDecimal;
	}
	
	/**
	 * <p>保留小数位</p>
	 * @author:  hec
	 * @param:   @param keepDecimal
	 * @return:  void 
	 * @Date :   2018-12-27 15:00:02   
	 */
	public void setKeepDecimal(Integer keepDecimal) {
		this.keepDecimal = keepDecimal;
	}
	
	

}

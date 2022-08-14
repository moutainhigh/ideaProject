/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 15:49:05 
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
 * <p> ExProductParameter </p>
 * @author:         liushilei
 * @Date :          2018-06-12 15:49:05  
 */
@Table(name="ex_product_parameter")
public class ExProductParameter extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "buyFeeRate")
	private BigDecimal buyFeeRate;  //
	
	@Column(name= "sellFeeRate")
	private BigDecimal sellFeeRate;  //
	
	@Column(name= "buyMinMoney")
	private BigDecimal buyMinMoney;  //
	
	@Column(name= "sellMinCoin")
	private BigDecimal sellMinCoin;  //
	
	@Column(name= "state")
	private Integer state;  //
	
	@Column(name= "name")
	private String name;  //
	
	@Column(name= "prepaidFeeRate")
	private BigDecimal prepaidFeeRate;  //
	
	@Column(name= "paceFeeRate")
	private BigDecimal paceFeeRate;  //
	
	@Column(name= "oneTimePaceNum")
	private BigDecimal oneTimePaceNum;  //
	
	@Column(name= "oneDayPaceNum")
	private BigDecimal oneDayPaceNum;  //
	
	@Column(name= "leastPaceNum")
	private BigDecimal leastPaceNum;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public BigDecimal getBuyFeeRate() {
		return buyFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param buyFeeRate
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setBuyFeeRate(BigDecimal buyFeeRate) {
		this.buyFeeRate = buyFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public BigDecimal getSellFeeRate() {
		return sellFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sellFeeRate
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setSellFeeRate(BigDecimal sellFeeRate) {
		this.sellFeeRate = sellFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public BigDecimal getBuyMinMoney() {
		return buyMinMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param buyMinMoney
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setBuyMinMoney(BigDecimal buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public BigDecimal getSellMinCoin() {
		return sellMinCoin;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sellMinCoin
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setSellMinCoin(BigDecimal sellMinCoin) {
		this.sellMinCoin = sellMinCoin;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public BigDecimal getPrepaidFeeRate() {
		return prepaidFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param prepaidFeeRate
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setPrepaidFeeRate(BigDecimal prepaidFeeRate) {
		this.prepaidFeeRate = prepaidFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public BigDecimal getPaceFeeRate() {
		return paceFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param paceFeeRate
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setPaceFeeRate(BigDecimal paceFeeRate) {
		this.paceFeeRate = paceFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public BigDecimal getOneTimePaceNum() {
		return oneTimePaceNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param oneTimePaceNum
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setOneTimePaceNum(BigDecimal oneTimePaceNum) {
		this.oneTimePaceNum = oneTimePaceNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public BigDecimal getOneDayPaceNum() {
		return oneDayPaceNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param oneDayPaceNum
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setOneDayPaceNum(BigDecimal oneDayPaceNum) {
		this.oneDayPaceNum = oneDayPaceNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public BigDecimal getLeastPaceNum() {
		return leastPaceNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param leastPaceNum
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setLeastPaceNum(BigDecimal leastPaceNum) {
		this.leastPaceNum = leastPaceNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:49:05    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-12 15:49:05   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 12:06:01 
 */
package hry.admin.c2c.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> C2cCoin </p>
 * @author:         liushilei
 * @Date :          2018-06-13 12:06:01  
 */
@Table(name="c2c_coin")
public class C2cCoin extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "isOpen")
	private Integer isOpen;  //1开启  ，0 未开启
	
	@Column(name= "buyPrice")
	private BigDecimal buyPrice;  //
	
	@Column(name= "sellPrice")
	private BigDecimal sellPrice;  //
	
	@Column(name= "minCount")
	private BigDecimal minCount;  //
	
	@Column(name= "maxCount")
	private BigDecimal maxCount;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 12:06:01    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 12:06:01   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 12:06:01    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-13 12:06:01   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 12:06:01    
	 */
	public Integer getIsOpen() {
		return isOpen;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isOpen
	 * @return:  void 
	 * @Date :   2018-06-13 12:06:01   
	 */
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 12:06:01    
	 */
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param buyPrice
	 * @return:  void 
	 * @Date :   2018-06-13 12:06:01   
	 */
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 12:06:01    
	 */
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sellPrice
	 * @return:  void 
	 * @Date :   2018-06-13 12:06:01   
	 */
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 12:06:01    
	 */
	public BigDecimal getMinCount() {
		return minCount;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param minCount
	 * @return:  void 
	 * @Date :   2018-06-13 12:06:01   
	 */
	public void setMinCount(BigDecimal minCount) {
		this.minCount = minCount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 12:06:01    
	 */
	public BigDecimal getMaxCount() {
		return maxCount;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param maxCount
	 * @return:  void 
	 * @Date :   2018-06-13 12:06:01   
	 */
	public void setMaxCount(BigDecimal maxCount) {
		this.maxCount = maxCount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 12:06:01    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 12:06:01   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

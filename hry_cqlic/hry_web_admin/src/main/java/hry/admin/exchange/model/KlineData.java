/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-09-11 14:46:04 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlineData </p>
 * @author:         liushilei
 * @Date :          2018-09-11 14:46:04  
 */
@Table(name="kline_data")
public class KlineData extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "period")
	private String period;  //
	
	@Column(name= "openPrice")
	private BigDecimal openPrice;  //
	
	@Column(name= "closePrice")
	private BigDecimal closePrice;  //
	
	@Column(name= "highPrice")
	private BigDecimal highPrice;  //
	
	@Column(name= "lowPrice")
	private BigDecimal lowPrice;  //
	
	@Column(name= "time")
	private Date time;  //
	
	@Column(name= "timelong")
	private String timelong;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-09-11 14:46:04    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-09-11 14:46:04   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-09-11 14:46:04    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-09-11 14:46:04   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-09-11 14:46:04    
	 */
	public String getPeriod() {
		return period;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param period
	 * @return:  void 
	 * @Date :   2018-09-11 14:46:04   
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-09-11 14:46:04    
	 */
	public BigDecimal getOpenPrice() {
		return openPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param openPrice
	 * @return:  void 
	 * @Date :   2018-09-11 14:46:04   
	 */
	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-09-11 14:46:04    
	 */
	public BigDecimal getClosePrice() {
		return closePrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param closePrice
	 * @return:  void 
	 * @Date :   2018-09-11 14:46:04   
	 */
	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-09-11 14:46:04    
	 */
	public BigDecimal getHighPrice() {
		return highPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param highPrice
	 * @return:  void 
	 * @Date :   2018-09-11 14:46:04   
	 */
	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-09-11 14:46:04    
	 */
	public BigDecimal getLowPrice() {
		return lowPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param lowPrice
	 * @return:  void 
	 * @Date :   2018-09-11 14:46:04   
	 */
	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-09-11 14:46:04    
	 */
	public Date getTime() {
		return time;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param time
	 * @return:  void 
	 * @Date :   2018-09-11 14:46:04   
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-09-11 14:46:04    
	 */
	public String getTimelong() {
		return timelong;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param timelong
	 * @return:  void 
	 * @Date :   2018-09-11 14:46:04   
	 */
	public void setTimelong(String timelong) {
		this.timelong = timelong;
	}
	
	

}

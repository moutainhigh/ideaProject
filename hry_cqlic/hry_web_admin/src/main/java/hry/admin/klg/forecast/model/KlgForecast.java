/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-03 16:36:19 
 */
package hry.admin.klg.forecast.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgForecast </p>
 * @author:         yaozhuo
 * @Date :          2019-06-03 16:36:19  
 */
@Table(name="klg_forecast")
public class KlgForecast extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "yesterdaySurplus")
	private BigDecimal yesterdaySurplus;  //昨天剩余数量
	
	@Column(name= "todaySurplus")
	private BigDecimal todaySurplus;  //今天剩余数量
	
	@Column(name= "todayForecast")
	private BigDecimal todayForecast;  //预测今天排单数量
	
	@Column(name= "tomorrowForecast")
	private BigDecimal tomorrowForecast;  //预测明天排单数量
	
	@Column(name= "forecastInterval")
	private Integer forecastInterval;  //预测间隔天数
	
	@Column(name= "forecastTime")
	private Date forecastTime;  //预测时间
	
	
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-06-03 16:36:19    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-03 16:36:19   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>昨天剩余数量</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-06-03 16:36:19    
	 */
	public BigDecimal getYesterdaySurplus() {
		return yesterdaySurplus;
	}
	
	/**
	 * <p>昨天剩余数量</p>
	 * @author:  yaozhuo
	 * @param:   @param yesterdaySurplus
	 * @return:  void 
	 * @Date :   2019-06-03 16:36:19   
	 */
	public void setYesterdaySurplus(BigDecimal yesterdaySurplus) {
		this.yesterdaySurplus = yesterdaySurplus;
	}
	
	
	/**
	 * <p>今天剩余数量</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-06-03 16:36:19    
	 */
	public BigDecimal getTodaySurplus() {
		return todaySurplus;
	}
	
	/**
	 * <p>今天剩余数量</p>
	 * @author:  yaozhuo
	 * @param:   @param todaySurplus
	 * @return:  void 
	 * @Date :   2019-06-03 16:36:19   
	 */
	public void setTodaySurplus(BigDecimal todaySurplus) {
		this.todaySurplus = todaySurplus;
	}
	
	
	/**
	 * <p>预测今天排单数量</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-06-03 16:36:19    
	 */
	public BigDecimal getTodayForecast() {
		return todayForecast;
	}
	
	/**
	 * <p>预测今天排单数量</p>
	 * @author:  yaozhuo
	 * @param:   @param todayForecast
	 * @return:  void 
	 * @Date :   2019-06-03 16:36:19   
	 */
	public void setTodayForecast(BigDecimal todayForecast) {
		this.todayForecast = todayForecast;
	}
	
	
	/**
	 * <p>预测明天排单数量</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-06-03 16:36:19    
	 */
	public BigDecimal getTomorrowForecast() {
		return tomorrowForecast;
	}
	
	/**
	 * <p>预测明天排单数量</p>
	 * @author:  yaozhuo
	 * @param:   @param tomorrowForecast
	 * @return:  void 
	 * @Date :   2019-06-03 16:36:19   
	 */
	public void setTomorrowForecast(BigDecimal tomorrowForecast) {
		this.tomorrowForecast = tomorrowForecast;
	}
	
	
	/**
	 * <p>预测间隔天数</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-06-03 16:36:19    
	 */
	public Integer getForecastInterval() {
		return forecastInterval;
	}
	
	/**
	 * <p>预测间隔天数</p>
	 * @author:  yaozhuo
	 * @param:   @param forecastInterval
	 * @return:  void 
	 * @Date :   2019-06-03 16:36:19   
	 */
	public void setForecastInterval(Integer forecastInterval) {
		this.forecastInterval = forecastInterval;
	}
	
	
	/**
	 * <p>预测时间</p>
	 * @author:  yaozhuo
	 * @return:  Date 
	 * @Date :   2019-06-03 16:36:19    
	 */
	public Date getForecastTime() {
		return forecastTime;
	}
	
	/**
	 * <p>预测时间</p>
	 * @author:  yaozhuo
	 * @param:   @param forecastTime
	 * @return:  void 
	 * @Date :   2019-06-03 16:36:19   
	 */
	public void setForecastTime(Date forecastTime) {
		this.forecastTime = forecastTime;
	}
	
	

}

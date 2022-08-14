/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-09-17 12:01:26 
 */
package hry.admin.exchange.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.bean.BaseModel;

/**
 * <p> BuildRecord </p>
 * @author:         denghf
 * @Date :          2018-09-17 12:01:26  
 */
@Table(name="build_record")
public class BuildRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "startTime")
	private Date startTime;  //
	
	@Column(name= "endTime")
	private Date endTime;  //
	
	@Column(name= "pullTime")
	private Date pullTime;  //
	
	@Column(name= "tradeCoinCode")
	private String tradeCoinCode;  //
	
	@Column(name= "priceCoinCode")
	private String priceCoinCode;  //
	
	@Column(name= "1m")
	private Integer m1;  // 1分钟线
	
	@Column(name= "5m")
	private Integer m5;  // 5分钟线
	
	@Column(name= "15m")
	private Integer m15;  // 15分钟线
	
	@Column(name= "30m")
	private Integer m30;  // 30分钟线
	
	@Column(name= "60m")
	private Integer m60;  // 60分钟线
	
	@Column(name= "1day")
	private Integer day1;  // 1天
	
	@Column(name= "1week")
	private Integer week1;  // 1周
	
	public Integer getM1() {
		return m1;
	}

	public void setM1(Integer m1) {
		this.m1 = m1;
	}

	public Integer getM5() {
		return m5;
	}

	public void setM5(Integer m5) {
		this.m5 = m5;
	}

	public Integer getM15() {
		return m15;
	}

	public void setM15(Integer m15) {
		this.m15 = m15;
	}

	public Integer getM30() {
		return m30;
	}

	public void setM30(Integer m30) {
		this.m30 = m30;
	}

	public Integer getM60() {
		return m60;
	}

	public void setM60(Integer m60) {
		this.m60 = m60;
	}

	public Integer getDay1() {
		return day1;
	}

	public void setDay1(Integer day1) {
		this.day1 = day1;
	}

	public Integer getWeek1() {
		return week1;
	}

	public void setWeek1(Integer week1) {
		this.week1 = week1;
	}

	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-09-17 12:01:26    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-09-17 12:01:26   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-09-17 12:01:26    
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param startTime
	 * @return:  void 
	 * @Date :   2018-09-17 12:01:26   
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-09-17 12:01:26    
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param endTime
	 * @return:  void 
	 * @Date :   2018-09-17 12:01:26   
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-09-17 12:01:26    
	 */
	public Date getPullTime() {
		return pullTime;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param pullTime
	 * @return:  void 
	 * @Date :   2018-09-17 12:01:26   
	 */
	public void setPullTime(Date pullTime) {
		this.pullTime = pullTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-09-17 12:01:26    
	 */
	public String getTradeCoinCode() {
		return tradeCoinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param tradeCoinCode
	 * @return:  void 
	 * @Date :   2018-09-17 12:01:26   
	 */
	public void setTradeCoinCode(String tradeCoinCode) {
		this.tradeCoinCode = tradeCoinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-09-17 12:01:26    
	 */
	public String getPriceCoinCode() {
		return priceCoinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param priceCoinCode
	 * @return:  void 
	 * @Date :   2018-09-17 12:01:26   
	 */
	public void setPriceCoinCode(String priceCoinCode) {
		this.priceCoinCode = priceCoinCode;
	}
	
	

}

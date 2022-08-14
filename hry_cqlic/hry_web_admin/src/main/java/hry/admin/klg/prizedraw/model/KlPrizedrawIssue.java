/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-06 11:32:39 
 */
package hry.admin.klg.prizedraw.model;


import hry.bean.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlPrizedrawIssue </p>
 * @author:         yaozhuo
 * @Date :          2019-06-06 11:32:39  
 */
@Table(name="klg_prizedraw_lssue")
public class KlPrizedrawIssue extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "issueNumber")
	private Integer issueNumber;  //期号
	
	@Column(name= "primeNumber")
	private Integer primeNumber;  //质数
	
	@Column(name= "lotteryNumber")
	private String lotteryNumber;  //开奖号码
	
	@Column(name= "startDate")
	private Date startDate;  //开始时间
	
	@Column(name= "endDate")
	private Date endDate;  //结束时间
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "status")
	private Integer status;  //状态(1.待生效 2.生效中 3.已生效)
	
	@Column(name= "mondayNumber")
	private String mondayNumber;  //周一号码
	
	@Column(name= "tuesdayNumber")
	private String tuesdayNumber;  //周二号码
	
	@Column(name= "wednesdayNumber")
	private String wednesdayNumber;  //周三号码
	
	@Column(name= "thursdayNumber")
	private String thursdayNumber;  //周四号码
	
	@Column(name= "fridayNumber")
	private String fridayNumber;  //周五号码
	
	@Column(name= "saturdayNumber")
	private String saturdayNumber;  //周六号码
	
	@Column(name= "sundayNumber")
	private String sundayNumber;  //周日号码
	
	
	
	
	@Column(name= "mondayDate")
	private Date mondayDate;  //周一号码下单日期
	
	@Column(name= "tuesdayDate")
	private Date tuesdayDate;  //周二号码下单日期
	
	@Column(name= "wednesdayDate")
	private Date wednesdayDate;  //周三号码下单日期
	
	@Column(name= "thursdayDate")
	private Date thursdayDate;  //周四号码下单日期
	
	@Column(name= "fridayDate")
	private Date fridayDate;  //周五号码下单日期
	
	@Column(name= "saturdayDate")
	private Date saturdayDate;  //周六号码下单日期
	
	@Column(name= "sundayDate")
	private Date sundayDate;  //周日号码下单日期
	
	
	
	
	public Date getMondayDate() {
		return mondayDate;
	}

	public void setMondayDate(Date mondayDate) {
		this.mondayDate = mondayDate;
	}

	public Date getTuesdayDate() {
		return tuesdayDate;
	}

	public void setTuesdayDate(Date tuesdayDate) {
		this.tuesdayDate = tuesdayDate;
	}

	public Date getWednesdayDate() {
		return wednesdayDate;
	}

	public void setWednesdayDate(Date wednesdayDate) {
		this.wednesdayDate = wednesdayDate;
	}

	public Date getThursdayDate() {
		return thursdayDate;
	}

	public void setThursdayDate(Date thursdayDate) {
		this.thursdayDate = thursdayDate;
	}

	public Date getFridayDate() {
		return fridayDate;
	}

	public void setFridayDate(Date fridayDate) {
		this.fridayDate = fridayDate;
	}

	public Date getSaturdayDate() {
		return saturdayDate;
	}

	public void setSaturdayDate(Date saturdayDate) {
		this.saturdayDate = saturdayDate;
	}

	public Date getSundayDate() {
		return sundayDate;
	}

	public void setSundayDate(Date sundayDate) {
		this.sundayDate = sundayDate;
	}

	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>期号</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public Integer getIssueNumber() {
		return issueNumber;
	}
	
	/**
	 * <p>期号</p>
	 * @author:  yaozhuo
	 * @param:   @param issueNumber
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setIssueNumber(Integer issueNumber) {
		this.issueNumber = issueNumber;
	}
	
	
	/**
	 * <p>质数</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public Integer getPrimeNumber() {
		return primeNumber;
	}
	
	/**
	 * <p>质数</p>
	 * @author:  yaozhuo
	 * @param:   @param primeNumber
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setPrimeNumber(Integer primeNumber) {
		this.primeNumber = primeNumber;
	}
	
	
	/**
	 * <p>开奖号码</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public String getLotteryNumber() {
		return lotteryNumber;
	}
	
	/**
	 * <p>开奖号码</p>
	 * @author:  yaozhuo
	 * @param:   @param lotteryNumber
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setLotteryNumber(String lotteryNumber) {
		this.lotteryNumber = lotteryNumber;
	}
	
	
	/**
	 * <p>开始时间</p>
	 * @author:  yaozhuo
	 * @return:  Date 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * <p>开始时间</p>
	 * @author:  yaozhuo
	 * @param:   @param startDate
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * <p>结束时间</p>
	 * @author:  yaozhuo
	 * @return:  Date 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * <p>结束时间</p>
	 * @author:  yaozhuo
	 * @param:   @param endDate
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>状态(1.待生效 2.生效中 3.已生效)</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>状态(1.待生效 2.生效中 3.已生效)</p>
	 * @author:  yaozhuo
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>周一号码</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public String getMondayNumber() {
		return mondayNumber;
	}
	
	/**
	 * <p>周一号码</p>
	 * @author:  yaozhuo
	 * @param:   @param mondayNumber
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setMondayNumber(String mondayNumber) {
		this.mondayNumber = mondayNumber;
	}
	
	
	/**
	 * <p>周二号码</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public String getTuesdayNumber() {
		return tuesdayNumber;
	}
	
	/**
	 * <p>周二号码</p>
	 * @author:  yaozhuo
	 * @param:   @param tuesdayNumber
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setTuesdayNumber(String tuesdayNumber) {
		this.tuesdayNumber = tuesdayNumber;
	}
	
	
	/**
	 * <p>周三号码</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public String getWednesdayNumber() {
		return wednesdayNumber;
	}
	
	/**
	 * <p>周三号码</p>
	 * @author:  yaozhuo
	 * @param:   @param wednesdayNumber
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setWednesdayNumber(String wednesdayNumber) {
		this.wednesdayNumber = wednesdayNumber;
	}
	
	
	/**
	 * <p>周四号码</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public String getThursdayNumber() {
		return thursdayNumber;
	}
	
	/**
	 * <p>周四号码</p>
	 * @author:  yaozhuo
	 * @param:   @param thursdayNumber
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setThursdayNumber(String thursdayNumber) {
		this.thursdayNumber = thursdayNumber;
	}
	
	
	/**
	 * <p>周五号码</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public String getFridayNumber() {
		return fridayNumber;
	}
	
	/**
	 * <p>周五号码</p>
	 * @author:  yaozhuo
	 * @param:   @param fridayNumber
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setFridayNumber(String fridayNumber) {
		this.fridayNumber = fridayNumber;
	}
	
	
	/**
	 * <p>周六号码</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public String getSaturdayNumber() {
		return saturdayNumber;
	}
	
	/**
	 * <p>周六号码</p>
	 * @author:  yaozhuo
	 * @param:   @param saturdayNumber
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setSaturdayNumber(String saturdayNumber) {
		this.saturdayNumber = saturdayNumber;
	}
	
	
	/**
	 * <p>周日号码</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-06-06 11:32:39    
	 */
	public String getSundayNumber() {
		return sundayNumber;
	}
	
	/**
	 * <p>周日号码</p>
	 * @author:  yaozhuo
	 * @param:   @param sundayNumber
	 * @return:  void 
	 * @Date :   2019-06-06 11:32:39   
	 */
	public void setSundayNumber(String sundayNumber) {
		this.sundayNumber = sundayNumber;
	}
	
	

}

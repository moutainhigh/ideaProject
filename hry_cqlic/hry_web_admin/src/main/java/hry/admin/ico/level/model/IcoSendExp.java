/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-20 19:32:24 
 */
package hry.admin.ico.level.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IcoSendExp </p>
 * @author:         lzy
 * @Date :          2019-03-20 19:32:24  
 */
@Table(name="ico_send_exp")
public class IcoSendExp extends BaseModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private String customerId;  //
	
	@Column(name= "state")
	private Integer state;  //
	
	@Column(name= "countingTime")
	private Long countingTime;  //
	
	@Column(name= "number")
	private BigDecimal number;  //
	
	@Column(name= "releaseDate")
	private Long releaseDate;  //
	
	@Column(name= "hour")
	private Integer hour;  //
	
	@Column(name= "experience")
	private Long experience;  //
	
	@Column(name= "lockId")
	private Long lockId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-20 19:32:24    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-03-20 19:32:24   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-03-20 19:32:24    
	 */
	public String getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-03-20 19:32:24   
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-03-20 19:32:24    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-03-20 19:32:24   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-20 19:32:24    
	 */
	public Long getCountingTime() {
		return countingTime;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param countingTime
	 * @return:  void 
	 * @Date :   2019-03-20 19:32:24   
	 */
	public void setCountingTime(Long countingTime) {
		this.countingTime = countingTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-03-20 19:32:24    
	 */
	public BigDecimal getNumber() {
		return number;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-03-20 19:32:24   
	 */
	public void setNumber(BigDecimal number) {
		this.number = number;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-20 19:32:24    
	 */
	public Long getReleaseDate() {
		return releaseDate;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param releaseDate
	 * @return:  void 
	 * @Date :   2019-03-20 19:32:24   
	 */
	public void setReleaseDate(Long releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-03-20 19:32:24    
	 */
	public Integer getHour() {
		return hour;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param hour
	 * @return:  void 
	 * @Date :   2019-03-20 19:32:24   
	 */
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-20 19:32:24    
	 */
	public Long getExperience() {
		return experience;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param experience
	 * @return:  void 
	 * @Date :   2019-03-20 19:32:24   
	 */
	public void setExperience(Long experience) {
		this.experience = experience;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-20 19:32:24    
	 */
	public Long getLockId() {
		return lockId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param lockId
	 * @return:  void 
	 * @Date :   2019-03-20 19:32:24   
	 */
	public void setLockId(Long lockId) {
		this.lockId = lockId;
	}
	
	

}

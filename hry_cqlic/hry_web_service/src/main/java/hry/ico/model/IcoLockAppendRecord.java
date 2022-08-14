/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-03-21 16:02:14 
 */
package hry.ico.model;


import hry.bean.BaseModel;

import java.util.Date;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IcoLockAppendRecord </p>
 * @author:         lzy
 * @Date :          2019-03-21 16:02:14  
 */
@Table(name="ico_lock_append_record")
public class IcoLockAppendRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种名称
	
	@Column(name= "appendDay")
	private Integer appendDay;  //追加天数
	
	@Column(name= "releaseDate")
	private Date releaseDate;  //释放时间
	
	@Column(name= "lockId")
	private Long lockId;  //锁仓Id
	
	@Column(name= "experience")
	private Long experience;  //获得经验
	
	@Column(name= "number")
	private BigDecimal number;  //数量
	
	
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-21 16:02:14    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-03-21 16:02:14   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-21 16:02:14    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-03-21 16:02:14   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币种名称</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-03-21 16:02:14    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种名称</p>
	 * @author:  lzy
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-03-21 16:02:14   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>追加天数</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-03-21 16:02:14    
	 */
	public Integer getAppendDay() {
		return appendDay;
	}
	
	/**
	 * <p>追加天数</p>
	 * @author:  lzy
	 * @param:   @param appendDay
	 * @return:  void 
	 * @Date :   2019-03-21 16:02:14   
	 */
	public void setAppendDay(Integer appendDay) {
		this.appendDay = appendDay;
	}
	
	
	/**
	 * <p>释放时间</p>
	 * @author:  lzy
	 * @return:  Date 
	 * @Date :   2019-03-21 16:02:14    
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	/**
	 * <p>释放时间</p>
	 * @author:  lzy
	 * @param:   @param releaseDate
	 * @return:  void 
	 * @Date :   2019-03-21 16:02:14   
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	/**
	 * <p>锁仓Id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-21 16:02:14    
	 */
	public Long getLockId() {
		return lockId;
	}
	
	/**
	 * <p>锁仓Id</p>
	 * @author:  lzy
	 * @param:   @param lockId
	 * @return:  void 
	 * @Date :   2019-03-21 16:02:14   
	 */
	public void setLockId(Long lockId) {
		this.lockId = lockId;
	}
	
	
	/**
	 * <p>获得经验</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-03-21 16:02:14    
	 */
	public Long getExperience() {
		return experience;
	}
	
	/**
	 * <p>获得经验</p>
	 * @author:  lzy
	 * @param:   @param experience
	 * @return:  void 
	 * @Date :   2019-03-21 16:02:14   
	 */
	public void setExperience(Long experience) {
		this.experience = experience;
	}
	
	
	/**
	 * <p>数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-03-21 16:02:14    
	 */
	public BigDecimal getNumber() {
		return number;
	}
	
	/**
	 * <p>数量</p>
	 * @author:  lzy
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-03-21 16:02:14   
	 */
	public void setNumber(BigDecimal number) {
		this.number = number;
	}
	
	

}

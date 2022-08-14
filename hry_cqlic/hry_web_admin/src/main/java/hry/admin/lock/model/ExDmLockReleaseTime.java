/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-11-01 18:38:24 
 */
package hry.admin.lock.model;


import hry.bean.BaseModel;

import java.util.Date;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExDmLockReleaseTime </p>
 * @author:         liuchenghui
 * @Date :          2018-11-01 18:38:24  
 */
@Table(name="ex_dm_lock_releaseTime")
public class ExDmLockReleaseTime extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "recordId")
	private Long recordId;  //锁仓记录表主键
	
	@Column(name= "releaseTime")
	private Date releaseTime;  //释放时间
	
	@Column(name= "releaseVal")
	private BigDecimal releaseVal;  //释放值
	
	@Column(name= "state")
	private Integer state;  //释放状态 0：未释放 1：已释放 2: 自动转手动

	@Column(name = "saasId")
	private String saasId;
	
	
	/**
	 * <p>主键</p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-11-01 18:38:24    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  liuchenghui
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-01 18:38:24   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>锁仓记录表主键</p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-11-01 18:38:24    
	 */
	public Long getRecordId() {
		return recordId;
	}
	
	/**
	 * <p>锁仓记录表主键</p>
	 * @author:  liuchenghui
	 * @param:   @param recordId
	 * @return:  void 
	 * @Date :   2018-11-01 18:38:24   
	 */
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	
	
	/**
	 * <p>释放时间</p>
	 * @author:  liuchenghui
	 * @return:  Date
	 * @Date :   2018-11-01 18:38:24    
	 */
	public Date getReleaseTime() {
		return releaseTime;
	}
	
	/**
	 * <p>释放时间</p>
	 * @author:  liuchenghui
	 * @param:   @param releaseTime
	 * @return:  void 
	 * @Date :   2018-11-01 18:38:24   
	 */
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	
	/**
	 * <p>释放值</p>
	 * @author:  liuchenghui
	 * @return:  BigDecimal 
	 * @Date :   2018-11-01 18:38:24    
	 */
	public BigDecimal getReleaseVal() {
		return releaseVal;
	}
	
	/**
	 * <p>释放值</p>
	 * @author:  liuchenghui
	 * @param:   @param releaseVal
	 * @return:  void 
	 * @Date :   2018-11-01 18:38:24   
	 */
	public void setReleaseVal(BigDecimal releaseVal) {
		this.releaseVal = releaseVal;
	}
	
	
	/**
	 * <p>释放状态 0：未释放 1：已释放</p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-11-01 18:38:24    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>释放状态 0：未释放 1：已释放</p>
	 * @author:  liuchenghui
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-11-01 18:38:24   
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	public String getSaasId () {
		return saasId;
	}

	public void setSaasId (String saasId) {
		this.saasId = saasId;
	}
}

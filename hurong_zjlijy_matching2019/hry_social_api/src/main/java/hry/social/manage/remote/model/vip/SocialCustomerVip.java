/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-12 11:35:57 
 */
package hry.social.manage.remote.model.vip;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * <p> SocialCustomerVip </p>
 * @author:         javalx
 * @Date :          2019-06-12 11:35:57  
 */
@Table(name="social_customer_vip")
public class SocialCustomerVip extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //序号
	
	@Column(name= "customerId")
	private Long customerId;  //用户ID
	
	@Column(name= "vipId")
	private Long vipId;  //会员ID
	
	@Column(name= "additionRatio")
	private BigDecimal additionRatio;  //加成比例（%）
	
	@Column(name= "startTime")
	private Date startTime;  //开始时间
	
	@Column(name= "endTime")
	private Date endTime;  //到期时间
	
	@Column(name= "updateId")
	private Long updateId;  //升级ID
	
	@Column(name= "renewId")
	private Long renewId;  //续购ID

	@Transient
	private String vipName;  //会员等级

	@Transient
	private String serialNum;  //会员编号

	@Transient
	private String img;  //图标

	@Transient
	private String expire;  //到期天数


	@Transient
	private Integer states;  // 是否会员 0：否，1：是

	@Transient
	private BigDecimal taskForce;    // 当前任务算力值

	@Transient
	private BigDecimal millForce;    // 当前矿机算力值

	@Transient
	private BigDecimal millCoin;     // 当前矿机数币值

	@Transient
	private BigDecimal taskForceAdd; // 任务算力加成后值

	@Transient
	private BigDecimal millForceAdd; // 矿机算力加成后值

	@Transient
	private BigDecimal millCoinAdd;  // 矿机数币加成后值

	public Integer getStates() {
		return states;
	}

	public void setStates(Integer states) {
		this.states = states;
	}

	public BigDecimal getTaskForce() {
		return taskForce;
	}

	public void setTaskForce(BigDecimal taskForce) {
		this.taskForce = taskForce;
	}

	public BigDecimal getMillForce() {
		return millForce;
	}

	public void setMillForce(BigDecimal millForce) {
		this.millForce = millForce;
	}

	public BigDecimal getMillCoin() {
		return millCoin;
	}

	public void setMillCoin(BigDecimal millCoin) {
		this.millCoin = millCoin;
	}

	public BigDecimal getTaskForceAdd() {
		return taskForceAdd;
	}

	public void setTaskForceAdd(BigDecimal taskForceAdd) {
		this.taskForceAdd = taskForceAdd;
	}

	public BigDecimal getMillForceAdd() {
		return millForceAdd;
	}

	public void setMillForceAdd(BigDecimal millForceAdd) {
		this.millForceAdd = millForceAdd;
	}

	public BigDecimal getMillCoinAdd() {
		return millCoinAdd;
	}

	public void setMillCoinAdd(BigDecimal millCoinAdd) {
		this.millCoinAdd = millCoinAdd;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-12 11:35:57    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-12 11:35:57   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-12 11:35:57    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-12 11:35:57   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>会员ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-12 11:35:57    
	 */
	public Long getVipId() {
		return vipId;
	}
	
	/**
	 * <p>会员ID</p>
	 * @author:  javalx
	 * @param:   @param vipId
	 * @return:  void 
	 * @Date :   2019-06-12 11:35:57   
	 */
	public void setVipId(Long vipId) {
		this.vipId = vipId;
	}
	
	
	/**
	 * <p>加成比例（%）</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-12 11:35:57    
	 */
	public BigDecimal getAdditionRatio() {
		return additionRatio;
	}
	
	/**
	 * <p>加成比例（%）</p>
	 * @author:  javalx
	 * @param:   @param additionRatio
	 * @return:  void 
	 * @Date :   2019-06-12 11:35:57   
	 */
	public void setAdditionRatio(BigDecimal additionRatio) {
		this.additionRatio = additionRatio;
	}
	
	
	/**
	 * <p>开始时间</p>
	 * @author:  javalx
	 * @return:  Date 
	 * @Date :   2019-06-12 11:35:57    
	 */
	public Date getStartTime() {
		return startTime;
	}
	
	/**
	 * <p>开始时间</p>
	 * @author:  javalx
	 * @param:   @param startTime
	 * @return:  void 
	 * @Date :   2019-06-12 11:35:57   
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	
	/**
	 * <p>到期时间</p>
	 * @author:  javalx
	 * @return:  Date 
	 * @Date :   2019-06-12 11:35:57    
	 */
	public Date getEndTime() {
		return endTime;
	}
	
	/**
	 * <p>到期时间</p>
	 * @author:  javalx
	 * @param:   @param endTime
	 * @return:  void 
	 * @Date :   2019-06-12 11:35:57   
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
	/**
	 * <p>升级ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-12 11:35:57    
	 */
	public Long getUpdateId() {
		return updateId;
	}
	
	/**
	 * <p>升级ID</p>
	 * @author:  javalx
	 * @param:   @param updateId
	 * @return:  void 
	 * @Date :   2019-06-12 11:35:57   
	 */
	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}
	
	
	/**
	 * <p>续购ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-12 11:35:57    
	 */
	public Long getRenewId() {
		return renewId;
	}
	
	/**
	 * <p>续购ID</p>
	 * @author:  javalx
	 * @param:   @param renewId
	 * @return:  void 
	 * @Date :   2019-06-12 11:35:57   
	 */
	public void setRenewId(Long renewId) {
		this.renewId = renewId;
	}
	
	

}

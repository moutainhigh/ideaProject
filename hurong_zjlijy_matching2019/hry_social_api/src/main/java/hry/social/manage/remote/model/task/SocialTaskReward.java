/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 17:55:29 
 */
package hry.social.manage.remote.model.task;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * <p> SocialTaskReward </p>
 * @author:         javalx
 * @Date :          2019-06-11 17:55:29  
 */
@Table(name="social_task_reward")
public class SocialTaskReward extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "customerId")
	private Long customerId;  //用户ID
	
	@Column(name= "taskMark")
	private String taskMark;  //任务标识
	
	@Column(name= "categoryKey")
	private String categoryKey;  //任务类型
	
	@Column(name= "itemName")
	private String itemName;  //任务项名称
	
	@Column(name= "rewardType")
	private Integer rewardType;  //奖励类型(0:一次性奖励，1：按次数奖励，2：按时间奖励)
	
	@Column(name= "rewardWay")
	private Integer rewardWay;  //奖励方式(0:奖励永久算力，1：奖励时效性算力，2：奖励算力加成，3：奖励数币)
	
	@Column(name= "rewardNum")
	private BigDecimal rewardNum;  //奖励数量
	
	@Column(name= "coinCode")
	private String coinCode;  //奖励币种

	@Column(name= "created")
	private Date created;  //奖励时间

	@Transient
	private String name;//用户昵称

	@Transient
	private String mobilePhone;//用户手机号

	@Transient
	private String surname;//用户姓氏

	@Transient
	private String trueName;//用户名字


	@Override
	public String toString() {
		return "SocialTaskReward{" + "id=" + id + ", customerId=" + customerId + ", taskMark='" + taskMark + '\'' + ", categoryKey='" + categoryKey + '\'' + ", itemName='" + itemName + '\'' + ", rewardType=" + rewardType + ", rewardWay=" + rewardWay + ", rewardNum=" + rewardNum + ", coinCode='" + coinCode + '\'' + ", created=" + created + ", name='" + name + '\'' + ", mobilePhone='" + mobilePhone + '\'' + ", surname='" + surname + '\'' + ", trueName='" + trueName + '\'' + '}';
	}

	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 17:55:29    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-11 17:55:29   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 17:55:29    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-11 17:55:29   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>任务标识</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 17:55:29    
	 */
	public String getTaskMark() {
		return taskMark;
	}
	
	/**
	 * <p>任务标识</p>
	 * @author:  javalx
	 * @param:   @param taskMark
	 * @return:  void 
	 * @Date :   2019-06-11 17:55:29   
	 */
	public void setTaskMark(String taskMark) {
		this.taskMark = taskMark;
	}
	
	
	/**
	 * <p>任务类型</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 17:55:29    
	 */
	public String getCategoryKey() {
		return categoryKey;
	}
	
	/**
	 * <p>任务类型</p>
	 * @author:  javalx
	 * @param:   @param categoryKey
	 * @return:  void 
	 * @Date :   2019-06-11 17:55:29   
	 */
	public void setCategoryKey(String categoryKey) {
		this.categoryKey = categoryKey;
	}
	
	
	/**
	 * <p>任务项名称</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 17:55:29    
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * <p>任务项名称</p>
	 * @author:  javalx
	 * @param:   @param itemName
	 * @return:  void 
	 * @Date :   2019-06-11 17:55:29   
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	/**
	 * <p>奖励类型(0:一次性奖励，1：按次数奖励，2：按时间奖励)</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 17:55:29    
	 */
	public Integer getRewardType() {
		return rewardType;
	}
	
	/**
	 * <p>奖励类型(0:一次性奖励，1：按次数奖励，2：按时间奖励)</p>
	 * @author:  javalx
	 * @param:   @param rewardType
	 * @return:  void 
	 * @Date :   2019-06-11 17:55:29   
	 */
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}
	
	
	/**
	 * <p>奖励方式(0:奖励永久算力，1：奖励时效性算力，2：奖励算力加成，3：奖励数币)</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 17:55:29    
	 */
	public Integer getRewardWay() {
		return rewardWay;
	}
	
	/**
	 * <p>奖励方式(0:奖励永久算力，1：奖励时效性算力，2：奖励算力加成，3：奖励数币)</p>
	 * @author:  javalx
	 * @param:   @param rewardWay
	 * @return:  void 
	 * @Date :   2019-06-11 17:55:29   
	 */
	public void setRewardWay(Integer rewardWay) {
		this.rewardWay = rewardWay;
	}
	
	
	/**
	 * <p>奖励数量</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 17:55:29    
	 */
	public BigDecimal getRewardNum() {
		return rewardNum;
	}
	
	/**
	 * <p>奖励数量</p>
	 * @author:  javalx
	 * @param:   @param rewardNum
	 * @return:  void 
	 * @Date :   2019-06-11 17:55:29   
	 */
	public void setRewardNum(BigDecimal rewardNum) {
		this.rewardNum = rewardNum;
	}
	
	
	/**
	 * <p>奖励币种</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 17:55:29    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>奖励币种</p>
	 * @author:  javalx
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-06-11 17:55:29   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}
}

/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-04 11:06:01 
 */
package hry.admin.licqb.ecology.model;


import hry.bean.BaseModel;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * <p> Ecofund </p>
 * @author:         zhouming
 * @Date :          2020-06-04 11:06:01  
 */
@Table(name="lc_ecofund")
public class Ecofund extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "orderNum")
	private String orderNum;  //单号

	@Column(name= "activityName")
	private String activityName; //活动名称

	@Column(name= "activityDate")
	private Date activityDate;  //活动时间

	@Column(name= "activityAddress")
	private String activityAddress;  //活动地址
	
	@Column(name= "peopleCount")
	private Integer peopleCount;  //活动人数
	
	@Column(name= "activityIntro")
	private String activityIntro;  //活动简介
	
	@Column(name= "activityImage")
	private String activityImage;  //活动图片
	
	@Column(name= "activityStatus")
	private Integer activityStatus;  //1 申请中 2 后台审核拒绝 3 后台审核通过 4 用户拒绝 5 用户通过（资料待补充） 6 补充材料待审核 7 后台通过补充材料审核
	
	@Column(name= "activityReply")
	private String activityReply;  //活动平台回复

	@Column(name= "againCreated")
	private Date againCreated;  //补充申请时间

	@Column(name= "againActivityDate")
	private Date againActivityDate;  //补充活动时间
	
	@Column(name= "againActivityAddress")
	private String againActivityAddress;  //补充活动地址
	
	@Column(name= "againPeopleCount")
	private Integer againPeopleCount;  //补充活动人数
	
	@Column(name= "againActivityIntro")
	private String againActivityIntro;  //补充活动简介
	
	@Column(name= "againActivityImage")
	private String againActivityImage;  //补充活动图片

	@Column(name= "againActivityReply")
	private String againActivityReply;  // 补充材料平台回复

	@Column(name= "againActivityVideo")
	private String againActivityVideo; //补充材料视频

	@Column(name= "itAgain")
	private Integer itAgain; // 补充材料是否拒绝 0 未拒绝 1 已拒绝

	@Column(name= "saasId")
	private String saasId;  //

	@Transient
	private String mobilePhone; // 手机号

	@Transient
	private String email; //邮箱


	public Integer getItAgain() {
		return itAgain;
	}

	public void setItAgain(Integer itAgain) {
		this.itAgain = itAgain;
	}

	public String getAgainActivityVideo() {
		return againActivityVideo;
	}

	public void setAgainActivityVideo(String againActivityVideo) {
		this.againActivityVideo = againActivityVideo;
	}

	public String getAgainActivityReply() {
		return againActivityReply;
	}

	public void setAgainActivityReply(String againActivityReply) {
		this.againActivityReply = againActivityReply;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>单号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public String getOrderNum() {
		return orderNum;
	}
	
	/**
	 * <p>单号</p>
	 * @author:  zhouming
	 * @param:   @param orderNum
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	/**
	 * <p>活动时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public Date getActivityDate() {
		return activityDate;
	}
	
	/**
	 * <p>活动时间</p>
	 * @author:  zhouming
	 * @param:   @param activityDate
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	
	
	/**
	 * <p>活动地址</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public String getActivityAddress() {
		return activityAddress;
	}
	
	/**
	 * <p>活动地址</p>
	 * @author:  zhouming
	 * @param:   @param activityAddress
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setActivityAddress(String activityAddress) {
		this.activityAddress = activityAddress;
	}
	
	
	/**
	 * <p>活动人数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public Integer getPeopleCount() {
		return peopleCount;
	}
	
	/**
	 * <p>活动人数</p>
	 * @author:  zhouming
	 * @param:   @param peopleCount
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
	
	
	/**
	 * <p>活动简介</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public String getActivityIntro() {
		return activityIntro;
	}
	
	/**
	 * <p>活动简介</p>
	 * @author:  zhouming
	 * @param:   @param activityIntro
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setActivityIntro(String activityIntro) {
		this.activityIntro = activityIntro;
	}
	
	
	/**
	 * <p>活动图片</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public String getActivityImage() {
		return activityImage;
	}
	
	/**
	 * <p>活动图片</p>
	 * @author:  zhouming
	 * @param:   @param activityImge
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setActivityImage(String activityImage) {
		this.activityImage = activityImage;
	}
	
	
	/**
	 * <p>1 申请中 2 后台审核拒绝 3 后台审核通过 4 用户拒绝 5 用户通过（资料待补充） 6 补充材料待审核 7 后台通过补充材料审核 8申请删除 </p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public Integer getActivityStatus() {
		return activityStatus;
	}
	
	/**
	 * <p>1 申请中 2 后台审核拒绝 3 后台审核通过 4 用户拒绝 5 用户通过（资料待补充） 6 补充材料待审核 7 后台通过补充材料审核</p>
	 * @author:  zhouming
	 * @param:   @param activityStatus
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}
	
	
	/**
	 * <p>活动平台回复</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public String getActivityReply() {
		return activityReply;
	}
	
	/**
	 * <p>活动平台回复</p>
	 * @author:  zhouming
	 * @param:   @param activityReply
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setActivityReply(String activityReply) {
		this.activityReply = activityReply;
	}

	public Date getAgainCreated() {
		return againCreated;
	}

	public void setAgainCreated(Date againCreated) {
		this.againCreated = againCreated;
	}

	/**
	 * <p>补充活动时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public Date getAgainActivityDate() {
		return againActivityDate;
	}
	
	/**
	 * <p>补充活动时间</p>
	 * @author:  zhouming
	 * @param:   @param againActivityDate
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setAgainActivityDate(Date againActivityDate) {
		this.againActivityDate = againActivityDate;
	}
	
	
	/**
	 * <p>补充活动地址</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public String getAgainActivityAddress() {
		return againActivityAddress;
	}
	
	/**
	 * <p>补充活动地址</p>
	 * @author:  zhouming
	 * @param:   @param againActivityAddress
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setAgainActivityAddress(String againActivityAddress) {
		this.againActivityAddress = againActivityAddress;
	}
	
	
	/**
	 * <p>补充活动人数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public Integer getAgainPeopleCount() {
		return againPeopleCount;
	}
	
	/**
	 * <p>补充活动人数</p>
	 * @author:  zhouming
	 * @param:   @param againPeopleCount
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setAgainPeopleCount(Integer againPeopleCount) {
		this.againPeopleCount = againPeopleCount;
	}
	
	
	/**
	 * <p>补充活动简介</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public String getAgainActivityIntro() {
		return againActivityIntro;
	}
	
	/**
	 * <p>补充活动简介</p>
	 * @author:  zhouming
	 * @param:   @param againActivityIntro
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setAgainActivityIntro(String againActivityIntro) {
		this.againActivityIntro = againActivityIntro;
	}
	
	
	/**
	 * <p>补充活动图片</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public String getAgainActivityImage() {
		return againActivityImage;
	}
	
	/**
	 * <p>补充活动图片</p>
	 * @author:  zhouming
	 * @param:   @param againActivityImge
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setAgainActivityImage(String againActivityImage) {
		this.againActivityImage = againActivityImage;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-04 11:06:01    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-06-04 11:06:01   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

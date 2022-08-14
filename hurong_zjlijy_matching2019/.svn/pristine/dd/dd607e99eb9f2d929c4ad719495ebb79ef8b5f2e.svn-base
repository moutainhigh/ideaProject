/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-03 20:17:08 
 */
package hry.social.manage.remote.model.friend;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> SocialFriendApply </p>
 * @author:         javalx
 * @Date :          2019-06-03 20:17:08  
 */
@Table(name="social_friend_apply")
public class SocialFriendApply extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "customerId")
	private Long customerId;  //被申请人ID
	
	@Column(name= "applyId")
	private Long applyId;  //申请人ID
	
	@Column(name= "states")
	private Integer states;  //状态（0：待接受，1：接受  2 : 已拒绝）
	
	@Column(name= "remark")
	private String remark;  //好友备注
	
	@Column(name= "message")
	private String message;  //申请信息

	@Transient
	private String nickName;  //昵称

	@Transient
	private String headPortrait;  //头像

	@Transient
	private String email;  //邮箱

	@Transient
	private String mobilePhone;  //手机

	@Transient
	private String accid;  //云信帐号

	@Transient
	private String mood;  //心情签名

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAccid() {
		return accid;
	}

	public void setAccid(String accid) {
		this.accid = accid;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-03 20:17:08    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-03 20:17:08   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>被申请人ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-03 20:17:08    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>被申请人ID</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-03 20:17:08   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>申请人ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-03 20:17:08    
	 */
	public Long getApplyId() {
		return applyId;
	}
	
	/**
	 * <p>申请人ID</p>
	 * @author:  javalx
	 * @param:   @param applyId
	 * @return:  void 
	 * @Date :   2019-06-03 20:17:08   
	 */
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	
	
	/**
	 * <p>状态（0：待接受，1：接受  2 : 已拒绝）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-03 20:17:08    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p>状态（0：待接受，1：接受  2 : 已拒绝）</p>
	 * @author:  javalx
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2019-06-03 20:17:08   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	
	/**
	 * <p>好友备注</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 20:17:08    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>好友备注</p>
	 * @author:  javalx
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-06-03 20:17:08   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>申请信息</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 20:17:08    
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * <p>申请信息</p>
	 * @author:  javalx
	 * @param:   @param message
	 * @return:  void 
	 * @Date :   2019-06-03 20:17:08   
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

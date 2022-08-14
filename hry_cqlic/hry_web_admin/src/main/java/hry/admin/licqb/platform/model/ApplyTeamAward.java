/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:44:07 
 */
package hry.admin.licqb.platform.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ApplyTeamAward </p>
 * @author:         zhouming
 * @Date :          2019-08-12 17:44:07  
 */
@Table(name="lc_apply_team_award")
public class ApplyTeamAward extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "email")
	private String email;  //用户邮箱
	
	@Column(name= "mobilePhone")
	private String mobilePhone;  //用户手机号
	
	@Column(name= "socialType")
	private Integer socialType;  //社交类型: 1 QQ 2 微信 3 facebook
	
	@Column(name= "socialAccount")
	private String socialAccount;  //社交账户
	
	@Column(name= "socialGroupImg")
	private String socialGroupImg;  //社交群图片
	
	@Column(name= "applyStatus")
	private Integer applyStatus;  //申请步骤：1 填写社交账户 2 上传社交群图片 3申请完成
	
	@Column(name= "auditStatus")
	private Integer auditStatus;  //审核状态：0: 审核中 1 审核通过 2审核拒绝
	
	@Column(name= "auditExplain")
	private String auditExplain;  //审核说明


	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Integer getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(Integer applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-12 17:44:07    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-12 17:44:07   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-12 17:44:07    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-08-12 17:44:07   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>用户邮箱</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-12 17:44:07    
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * <p>用户邮箱</p>
	 * @author:  zhouming
	 * @param:   @param email
	 * @return:  void 
	 * @Date :   2019-08-12 17:44:07   
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <p>社交类型: 1 QQ 2 微信 3 facebook</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-12 17:44:07    
	 */
	public Integer getSocialType() {
		return socialType;
	}
	
	/**
	 * <p>社交类型: 1 QQ 2 微信 3 facebook</p>
	 * @author:  zhouming
	 * @param:   @param socialType
	 * @return:  void 
	 * @Date :   2019-08-12 17:44:07   
	 */
	public void setSocialType(Integer socialType) {
		this.socialType = socialType;
	}
	
	
	/**
	 * <p>社交账户</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-12 17:44:07    
	 */
	public String getSocialAccount() {
		return socialAccount;
	}
	
	/**
	 * <p>社交账户</p>
	 * @author:  zhouming
	 * @param:   @param socialAccount
	 * @return:  void 
	 * @Date :   2019-08-12 17:44:07   
	 */
	public void setSocialAccount(String socialAccount) {
		this.socialAccount = socialAccount;
	}
	
	
	/**
	 * <p>社交群图片</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-12 17:44:07    
	 */
	public String getSocialGroupImg() {
		return socialGroupImg;
	}
	
	/**
	 * <p>社交群图片</p>
	 * @author:  zhouming
	 * @param:   @param socialGroupImg
	 * @return:  void 
	 * @Date :   2019-08-12 17:44:07   
	 */
	public void setSocialGroupImg(String socialGroupImg) {
		this.socialGroupImg = socialGroupImg;
	}

	public String getAuditExplain() {
		return auditExplain;
	}

	public void setAuditExplain(String auditExplain) {
		this.auditExplain = auditExplain;
	}
}

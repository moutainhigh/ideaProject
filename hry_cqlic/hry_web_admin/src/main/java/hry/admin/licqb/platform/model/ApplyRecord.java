/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-12 17:46:02 
 */
package hry.admin.licqb.platform.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ApplyRecord </p>
 * @author:         zhouming
 * @Date :          2019-08-12 17:46:02  
 */
@Table(name="lc_apply_record")
public class ApplyRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "applyId")
	private Long applyId;  //社区奖励申请Id
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "email")
	private String email;  //用户邮箱
	
	@Column(name= "mobliePhone")
	private String mobliePhone;  //用户手机号
	
	@Column(name= "socialType")
	private Integer socialType;  //社交类型: 1 QQ 2 微信 3 facebook
	
	@Column(name= "socialAccount")
	private String socialAccount;  //社交账户
	
	@Column(name= "socialGroupImg")
	private String socialGroupImg;  //社交群图片
	
	@Column(name= "applyStaus")
	private Integer applyStaus;  //申请步骤：1 填写社交账户 2 上传社交群图片 3申请完成
	
	@Column(name= "auditStaus")
	private Integer auditStaus;  //审核状态：1 申请通过 2申请拒绝
	
	@Column(name= "auditExplain")
	private Integer auditExplain;  //审核说明
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-12 17:46:02    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-12 17:46:02   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>社区奖励申请Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-12 17:46:02    
	 */
	public Long getApplyId() {
		return applyId;
	}
	
	/**
	 * <p>社区奖励申请Id</p>
	 * @author:  zhouming
	 * @param:   @param applyId
	 * @return:  void 
	 * @Date :   2019-08-12 17:46:02   
	 */
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-12 17:46:02    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-08-12 17:46:02   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>用户邮箱</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-12 17:46:02    
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * <p>用户邮箱</p>
	 * @author:  zhouming
	 * @param:   @param email
	 * @return:  void 
	 * @Date :   2019-08-12 17:46:02   
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	/**
	 * <p>用户手机号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-12 17:46:02    
	 */
	public String getMobliePhone() {
		return mobliePhone;
	}
	
	/**
	 * <p>用户手机号</p>
	 * @author:  zhouming
	 * @param:   @param mobliePhone
	 * @return:  void 
	 * @Date :   2019-08-12 17:46:02   
	 */
	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
	}
	
	
	/**
	 * <p>社交类型: 1 QQ 2 微信 3 facebook</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-12 17:46:02    
	 */
	public Integer getSocialType() {
		return socialType;
	}
	
	/**
	 * <p>社交类型: 1 QQ 2 微信 3 facebook</p>
	 * @author:  zhouming
	 * @param:   @param socialType
	 * @return:  void 
	 * @Date :   2019-08-12 17:46:02   
	 */
	public void setSocialType(Integer socialType) {
		this.socialType = socialType;
	}
	
	
	/**
	 * <p>社交账户</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-12 17:46:02    
	 */
	public String getSocialAccount() {
		return socialAccount;
	}
	
	/**
	 * <p>社交账户</p>
	 * @author:  zhouming
	 * @param:   @param socialAccount
	 * @return:  void 
	 * @Date :   2019-08-12 17:46:02   
	 */
	public void setSocialAccount(String socialAccount) {
		this.socialAccount = socialAccount;
	}
	
	
	/**
	 * <p>社交群图片</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-12 17:46:02    
	 */
	public String getSocialGroupImg() {
		return socialGroupImg;
	}
	
	/**
	 * <p>社交群图片</p>
	 * @author:  zhouming
	 * @param:   @param socialGroupImg
	 * @return:  void 
	 * @Date :   2019-08-12 17:46:02   
	 */
	public void setSocialGroupImg(String socialGroupImg) {
		this.socialGroupImg = socialGroupImg;
	}
	
	
	/**
	 * <p>申请步骤：1 填写社交账户 2 上传社交群图片 3申请完成</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-12 17:46:02    
	 */
	public Integer getApplyStaus() {
		return applyStaus;
	}
	
	/**
	 * <p>申请步骤：1 填写社交账户 2 上传社交群图片 3申请完成</p>
	 * @author:  zhouming
	 * @param:   @param applyStaus
	 * @return:  void 
	 * @Date :   2019-08-12 17:46:02   
	 */
	public void setApplyStaus(Integer applyStaus) {
		this.applyStaus = applyStaus;
	}
	
	
	/**
	 * <p>审核状态：1 申请通过 2申请拒绝</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-12 17:46:02    
	 */
	public Integer getAuditStaus() {
		return auditStaus;
	}
	
	/**
	 * <p>审核状态：1 申请通过 2申请拒绝</p>
	 * @author:  zhouming
	 * @param:   @param auditStaus
	 * @return:  void 
	 * @Date :   2019-08-12 17:46:02   
	 */
	public void setAuditStaus(Integer auditStaus) {
		this.auditStaus = auditStaus;
	}
	
	
	/**
	 * <p>审核说明</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-12 17:46:02    
	 */
	public Integer getAuditExplain() {
		return auditExplain;
	}
	
	/**
	 * <p>审核说明</p>
	 * @author:  zhouming
	 * @param:   @param auditExplain
	 * @return:  void 
	 * @Date :   2019-08-12 17:46:02   
	 */
	public void setAuditExplain(Integer auditExplain) {
		this.auditExplain = auditExplain;
	}
	
	

}

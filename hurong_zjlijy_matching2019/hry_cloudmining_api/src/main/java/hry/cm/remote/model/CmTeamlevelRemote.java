package hry.cm.remote.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class CmTeamlevelRemote  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value ="用户Id")
	private Long customerId;  //用户Id
	
	@ApiModelProperty(value ="推荐人Id")
	private Long parentId;  //推荐人Id
	
	@ApiModelProperty(value ="层级")
	private Integer level;  //层级
	
	@ApiModelProperty(value ="推荐人邀请码")
	private String parentInvitationCode;  //推荐人邀请码
	
	@ApiModelProperty(value ="用户邀请码")
	private String customerInvitationCode;  //用户邀请码
	
	@ApiModelProperty(value ="姓")
	private String trueName;  //姓
	@ApiModelProperty(value ="名")
	private String surName;  //名
	@ApiModelProperty(value ="手机号")
	private String mobilePhone;  //手机号
	@ApiModelProperty(value ="邮箱")
	private String email;  //邮箱
	@ApiModelProperty(value ="昵称")
	private String nickName;  //昵称
	
	@ApiModelProperty(value ="个人投入")
	private String personalAchievement;  //个人投入
	@ApiModelProperty(value ="团队投入")
	private String teamAchievement;  //团队投入
	@ApiModelProperty(value ="团队人数")
	private Integer teamNum;  //团队人数
	
	
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getParentInvitationCode() {
		return parentInvitationCode;
	}
	public void setParentInvitationCode(String parentInvitationCode) {
		this.parentInvitationCode = parentInvitationCode;
	}
	public String getCustomerInvitationCode() {
		return customerInvitationCode;
	}
	public void setCustomerInvitationCode(String customerInvitationCode) {
		this.customerInvitationCode = customerInvitationCode;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
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
	public String getPersonalAchievement() {
		return personalAchievement;
	}
	public void setPersonalAchievement(String personalAchievement) {
		this.personalAchievement = personalAchievement;
	}
	public String getTeamAchievement() {
		return teamAchievement;
	}
	public void setTeamAchievement(String teamAchievement) {
		this.teamAchievement = teamAchievement;
	}
	public Integer getTeamNum() {
		return teamNum;
	}
	public void setTeamNum(Integer teamNum) {
		this.teamNum = teamNum;
	}

}

package hry.admin.klg.reward.model.vo;

import hry.admin.klg.reward.model.KlgReward;

public class KlgRewardVo extends KlgReward{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String trueName;  //姓
	private String surName;  //名
	private String mobilePhone;  //手机号
	private String email;  //邮箱
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
	
	

}

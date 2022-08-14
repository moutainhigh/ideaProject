package hry.klg.remote.model;

import java.io.Serializable;
import java.util.Date;

public class RemoteKlgPrizedrawCustomer implements Serializable {
	
	private Long id;  //
	private Long customerId;  //用户id
	private Integer issueNumber;  //期号
	private String prizedrawNumber;  //抽奖号码
	private Date startDate;  //开奖时间
	private Integer status;  //状态(1.未开奖 2.已开奖)
	private String trueName;  //姓
	private String surName;  //名
	private String mobilePhone;  //手机号
	private String email;  //邮箱
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Integer getIssueNumber() {
		return issueNumber;
	}
	public void setIssueNumber(Integer issueNumber) {
		this.issueNumber = issueNumber;
	}
	public String getPrizedrawNumber() {
		return prizedrawNumber;
	}
	public void setPrizedrawNumber(String prizedrawNumber) {
		this.prizedrawNumber = prizedrawNumber;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	
	

}

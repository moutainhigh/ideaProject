package hry.klg.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

public class KlPrizedrawSelectionRemote implements Serializable {
	
	private Long id;  //
	private Long customerId;  //用户id
	private Integer issueNumber;  //期号
	private Integer primeNumber;  //质数
	private String lotteryNumber;  //开奖号码
	private String prizedrawNumber;  //抽奖号码
	private Date startDate;  //开奖时间
	private Date created;  //添加时间
	private Integer status;  //奖金状态(1.未发放 2.已发放)
	private Integer prizedrawGrade;  //中奖等级(1.一等奖 2.二等奖 3.三等奖 ......)
	private BigDecimal money;  //中奖金额
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
	public Integer getPrimeNumber() {
		return primeNumber;
	}
	public void setPrimeNumber(Integer primeNumber) {
		this.primeNumber = primeNumber;
	}
	public String getLotteryNumber() {
		return lotteryNumber;
	}
	public void setLotteryNumber(String lotteryNumber) {
		this.lotteryNumber = lotteryNumber;
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPrizedrawGrade() {
		return prizedrawGrade;
	}
	public void setPrizedrawGrade(Integer prizedrawGrade) {
		this.prizedrawGrade = prizedrawGrade;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
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

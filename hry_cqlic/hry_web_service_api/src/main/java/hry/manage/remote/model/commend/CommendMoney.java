/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      
 * @version:     V1.0 
 * @Date:        2017-11-29 10:05:55 
 */
package hry.manage.remote.model.commend;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class CommendMoney implements Serializable {
	
	
	private Long id;  //id
	
	private Long custromerId;  //custromerId
	
	private String custromerName;  //custromerName
	
	
	private BigDecimal moneyNum;  //总佣金(commendOne+commendTwo+commendThree+commendLater)
	
	private BigDecimal paidMoney;  //已派发佣金
	
	private Date lastPaidTime;  //最后一次派发时间
	
	
	private String coinCode;  //佣金币种
	

	// 编号
	private String userCode;
	
	private BigDecimal commendOne;
	private BigDecimal commendTwo;
	private BigDecimal commendThree;
	private BigDecimal commendLater;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustromerId() {
		return custromerId;
	}
	public void setCustromerId(Long custromerId) {
		this.custromerId = custromerId;
	}
	public String getCustromerName() {
		return custromerName;
	}
	public void setCustromerName(String custromerName) {
		this.custromerName = custromerName;
	}
	public BigDecimal getMoneyNum() {
		return moneyNum;
	}
	public void setMoneyNum(BigDecimal moneyNum) {
		this.moneyNum = moneyNum;
	}
	public BigDecimal getPaidMoney() {
		return paidMoney;
	}
	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}
	public Date getLastPaidTime() {
		return lastPaidTime;
	}
	public void setLastPaidTime(Date lastPaidTime) {
		this.lastPaidTime = lastPaidTime;
	}
	public String getCoinCode() {
		return coinCode;
	}
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public BigDecimal getCommendOne() {
		return commendOne;
	}
	public void setCommendOne(BigDecimal commendOne) {
		this.commendOne = commendOne;
	}
	public BigDecimal getCommendTwo() {
		return commendTwo;
	}
	public void setCommendTwo(BigDecimal commendTwo) {
		this.commendTwo = commendTwo;
	}
	public BigDecimal getCommendThree() {
		return commendThree;
	}
	public void setCommendThree(BigDecimal commendThree) {
		this.commendThree = commendThree;
	}
	public BigDecimal getCommendLater() {
		return commendLater;
	}
	public void setCommendLater(BigDecimal commendLater) {
		this.commendLater = commendLater;
	}
	

	

	

}

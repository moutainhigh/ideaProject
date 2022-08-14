/**
 * Copyright:   
 * @author:      HeC
 * @version:     V1.0 
 * @Date:        2018-10-18 17:58:04 
 */
package hry.admin.lend.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> ExLendDetail </p>
 * @author:         HeC
 * @Date :          2018-10-18 17:58:04  
 */
@Table(name="ex_lend_detail")
public class ExLendDetail extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name= "coinCode")
	private String coinCode;  //交易对
	
	@Column(name= "lendCode")
	private String lendCode;  //借款币种
	
	@Column(name= "lendCount")
	private BigDecimal lendCount;  //申请的数量
	
	@Column(name= "ratio")
	private BigDecimal ratio;  //利率
	
	@Column(name= "dayCount")
	private BigDecimal dayCount;  //计算天数
	
	@Column(name= "repayInerest")
	private BigDecimal repayInerest;  //已还利息
	
	@Column(name= "nopayInerest")
	private BigDecimal nopayInerest;  //未偿还利息
	
	@Column(name= "repayMoney")
	private BigDecimal repayMoney;  //已偿还本金
	
	@Column(name= "status")
	private Integer status;  //还款状态1/2/3/4 还款中 已结清 平仓中 已平仓
	
	@Column(name= "customerId")
	private Long customerId;  //

	@Column(name= "lendNum")
	private String lendNum;

	@Transient
	private String email;

	@Transient
	private String mobilePhone;

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

	public String getLendNum() {
		return lendNum;
	}

	public void setLendNum(String lendNum) {
		this.lendNum = lendNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  HeC
	 * @return:  Long 
	 * @Date :   2018-10-18 17:58:04    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  HeC
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-10-18 17:58:04   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>交易对</p>
	 * @author:  HeC
	 * @return:  String 
	 * @Date :   2018-10-18 17:58:04    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>交易对</p>
	 * @author:  HeC
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-10-18 17:58:04   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>借款币种</p>
	 * @author:  HeC
	 * @return:  String 
	 * @Date :   2018-10-18 17:58:04    
	 */
	public String getLendCode() {
		return lendCode;
	}
	
	/**
	 * <p>借款币种</p>
	 * @author:  HeC
	 * @param:   @param lendCode
	 * @return:  void 
	 * @Date :   2018-10-18 17:58:04   
	 */
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	
	
	/**
	 * <p>申请的数量</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-10-18 17:58:04    
	 */
	public BigDecimal getLendCount() {
		return lendCount;
	}
	
	/**
	 * <p>申请的数量</p>
	 * @author:  HeC
	 * @param:   @param lendCount
	 * @return:  void 
	 * @Date :   2018-10-18 17:58:04   
	 */
	public void setLendCount(BigDecimal lendCount) {
		this.lendCount = lendCount;
	}
	
	
	/**
	 * <p>利率</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-10-18 17:58:04    
	 */
	public BigDecimal getRatio() {
		return ratio;
	}
	
	/**
	 * <p>利率</p>
	 * @author:  HeC
	 * @param:   @param ratio
	 * @return:  void 
	 * @Date :   2018-10-18 17:58:04   
	 */
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}
	
	
	/**
	 * <p>计算天数</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-10-18 17:58:04    
	 */
	public BigDecimal getDayCount() {
		return dayCount;
	}
	
	/**
	 * <p>计算天数</p>
	 * @author:  HeC
	 * @param:   @param dayCount
	 * @return:  void 
	 * @Date :   2018-10-18 17:58:04   
	 */
	public void setDayCount(BigDecimal dayCount) {
		this.dayCount = dayCount;
	}
	
	
	/**
	 * <p>已还利息</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-10-18 17:58:04    
	 */
	public BigDecimal getRepayInerest() {
		return repayInerest;
	}
	
	/**
	 * <p>已还利息</p>
	 * @author:  HeC
	 * @param:   @param repayInerest
	 * @return:  void 
	 * @Date :   2018-10-18 17:58:04   
	 */
	public void setRepayInerest(BigDecimal repayInerest) {
		this.repayInerest = repayInerest;
	}
	
	
	/**
	 * <p>未偿还利息</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-10-18 17:58:04    
	 */
	public BigDecimal getNopayInerest() {
		return nopayInerest;
	}
	
	/**
	 * <p>未偿还利息</p>
	 * @author:  HeC
	 * @param:   @param nopayInerest
	 * @return:  void 
	 * @Date :   2018-10-18 17:58:04   
	 */
	public void setNopayInerest(BigDecimal nopayInerest) {
		this.nopayInerest = nopayInerest;
	}
	
	
	/**
	 * <p>已偿还本金</p>
	 * @author:  HeC
	 * @return:  BigDecimal 
	 * @Date :   2018-10-18 17:58:04    
	 */
	public BigDecimal getRepayMoney() {
		return repayMoney;
	}
	
	/**
	 * <p>已偿还本金</p>
	 * @author:  HeC
	 * @param:   @param repayMoney
	 * @return:  void 
	 * @Date :   2018-10-18 17:58:04   
	 */
	public void setRepayMoney(BigDecimal repayMoney) {
		this.repayMoney = repayMoney;
	}
	
	
	/**
	 * <p>还款状态1/2/3/4 还款中 已结清 平仓中 已平仓</p>
	 * @author:  HeC
	 * @return:  Integer 
	 * @Date :   2018-10-18 17:58:04    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>还款状态1/2/3/4 还款中 已结清 平仓中 已平仓</p>
	 * @author:  HeC
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-10-18 17:58:04   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  HeC
	 * @return:  Long 
	 * @Date :   2018-10-18 17:58:04    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  HeC
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-10-18 17:58:04   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	

}

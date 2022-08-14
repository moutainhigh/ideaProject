/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-06-19 19:39:19 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> ShopWithdrawRecord </p>
 * @author:         houzhen
 * @Date :          2019-06-19 19:39:19  
 */
@Table(name="shop_withdraw_record")
public class ShopWithdrawRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "withdrawSn")
	private String withdrawSn;  //提现编号
	
	@Column(name= "memberId")
	private Long memberId;  //用户Id
	
	@Column(name= "way")
	private Integer way;  //方式：1支付宝；2微信；3银联
	
	@Column(name= "bankName")
	private String bankName;  //开户行
	
	@Column(name= "accountNumber")
	private String accountNumber;  //账号
	
	@Column(name= "applicationAmount")
	private BigDecimal applicationAmount;  //申请金额
	
	@Column(name= "handlingFee")
	private BigDecimal handlingFee;  //手续费
	
	@Column(name= "actualAmount")
	private BigDecimal actualAmount;  //实际到账金额
	
	@Column(name= "markImg")
	private String markImg;  //二维码
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "status")
	private Integer status;  //状态（1待审核，2审核通过。3审核驳回）
	
	@Column(name= "operator")
	private String operator;  //操作人
	
	@Column(name= "operatingTime")
	private Date operatingTime;  //操作时间
	
	@Column(name= "denialReason")
	private String denialReason;  //驳回原因
	
	@Column(name= "saasId")
	private String saasId;  //

	@Column(name= "source")
	private Integer source;  //数据来源（1 后台充值，2 后台扣减。3 用户提现）

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}



	/**
	 * <p></p>
	 * @author:  houzhen
	 * @return:  Long 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  houzhen
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>提现编号</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public String getWithdrawSn() {
		return withdrawSn;
	}
	
	/**
	 * <p>提现编号</p>
	 * @author:  houzhen
	 * @param:   @param withdrawSn
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setWithdrawSn(String withdrawSn) {
		this.withdrawSn = withdrawSn;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  houzhen
	 * @return:  Long 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  houzhen
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>方式：1支付宝；2微信；3银联</p>
	 * @author:  houzhen
	 * @return:  Integer 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public Integer getWay() {
		return way;
	}
	
	/**
	 * <p>方式：1支付宝；2微信；3银联</p>
	 * @author:  houzhen
	 * @param:   @param way
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setWay(Integer way) {
		this.way = way;
	}
	
	
	/**
	 * <p>开户行</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public String getBankName() {
		return bankName;
	}
	
	/**
	 * <p>开户行</p>
	 * @author:  houzhen
	 * @param:   @param bankName
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
	/**
	 * <p>账号</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	
	/**
	 * <p>账号</p>
	 * @author:  houzhen
	 * @param:   @param accountNumber
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	/**
	 * <p>申请金额</p>
	 * @author:  houzhen
	 * @return:  BigDecimal 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public BigDecimal getApplicationAmount() {
		return applicationAmount;
	}
	
	/**
	 * <p>申请金额</p>
	 * @author:  houzhen
	 * @param:   @param applicationAmount
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setApplicationAmount(BigDecimal applicationAmount) {
		this.applicationAmount = applicationAmount;
	}
	
	
	/**
	 * <p>手续费</p>
	 * @author:  houzhen
	 * @return:  BigDecimal 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public BigDecimal getHandlingFee() {
		return handlingFee;
	}
	
	/**
	 * <p>手续费</p>
	 * @author:  houzhen
	 * @param:   @param handlingFee
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setHandlingFee(BigDecimal handlingFee) {
		this.handlingFee = handlingFee;
	}
	
	
	/**
	 * <p>实际到账金额</p>
	 * @author:  houzhen
	 * @return:  BigDecimal 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public BigDecimal getActualAmount() {
		return actualAmount;
	}
	
	/**
	 * <p>实际到账金额</p>
	 * @author:  houzhen
	 * @param:   @param actualAmount
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
	
	
	/**
	 * <p>二维码</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public String getMarkImg() {
		return markImg;
	}
	
	/**
	 * <p>二维码</p>
	 * @author:  houzhen
	 * @param:   @param markImg
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setMarkImg(String markImg) {
		this.markImg = markImg;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  houzhen
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>状态（1申请中，2审核中，3审核通过。4审核驳回）</p>
	 * @author:  houzhen
	 * @return:  Integer 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>状态（1申请中，2审核中，3审核通过。4审核驳回）</p>
	 * @author:  houzhen
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>操作人</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * <p>操作人</p>
	 * @author:  houzhen
	 * @param:   @param operator
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	/**
	 * <p>操作时间</p>
	 * @author:  houzhen
	 * @return:  Date 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public Date getOperatingTime() {
		return operatingTime;
	}
	
	/**
	 * <p>操作时间</p>
	 * @author:  houzhen
	 * @param:   @param operatingTime
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setOperatingTime(Date operatingTime) {
		this.operatingTime = operatingTime;
	}
	
	
	/**
	 * <p>驳回原因</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public String getDenialReason() {
		return denialReason;
	}
	
	/**
	 * <p>驳回原因</p>
	 * @author:  houzhen
	 * @param:   @param denialReason
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setDenialReason(String denialReason) {
		this.denialReason = denialReason;
	}
	
	
	/**
	 * <p></p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-19 19:39:19    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  houzhen
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-06-19 19:39:19   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

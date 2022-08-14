/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:38:22 
 */
package hry.admin.licqb.ecology.model;


import hry.bean.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> EcologPay </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:38:22  
 */
@Table(name="lc_ecolog_pay")
public class EcologPay extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "enterId")
	private Long enterId;  //入驻订单Id
	
	@Column(name= "orderNum")
	private String orderNum;  //单号
	
	@Column(name= "payDate")
	private Date payDate;  //缴费时间
	
	@Column(name= "acceptAddress")
	private String acceptAddress;  //收款地址
	
	@Column(name= "paymentAddress")
	private String paymentAddress;  //付款地址
	
	@Column(name= "verifyDate")
	private Date verifyDate;  //核实时间
	
	@Column(name= "baseValidityDay")
	private Integer baseValidityDay;  //当前规则中有效期天数
	
	@Column(name= "residueValidityDay")
	private Integer residueValidityDay;  //上笔剩余天数
	
	@Column(name= "validityDay")
	private Integer validityDay;  //实际保证期有效天数

	@Column(name= "status")
	private Integer status; // 1 待核实 3 核实未通过 4核实完成

	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "saasId")
	private String saasId;  //


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>入驻订单Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public Long getEnterId() {
		return enterId;
	}
	
	/**
	 * <p>入驻订单Id</p>
	 * @author:  zhouming
	 * @param:   @param enterId
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setEnterId(Long enterId) {
		this.enterId = enterId;
	}
	
	
	/**
	 * <p>单号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public String getOrderNum() {
		return orderNum;
	}
	
	/**
	 * <p>单号</p>
	 * @author:  zhouming
	 * @param:   @param orderNum
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	/**
	 * <p>缴费时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public Date getPayDate() {
		return payDate;
	}
	
	/**
	 * <p>缴费时间</p>
	 * @author:  zhouming
	 * @param:   @param payDate
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	
	/**
	 * <p>收款地址</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public String getAcceptAddress() {
		return acceptAddress;
	}
	
	/**
	 * <p>收款地址</p>
	 * @author:  zhouming
	 * @param:   @param acceptAddress
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setAcceptAddress(String acceptAddress) {
		this.acceptAddress = acceptAddress;
	}
	
	
	/**
	 * <p>付款地址</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public String getPaymentAddress() {
		return paymentAddress;
	}
	
	/**
	 * <p>付款地址</p>
	 * @author:  zhouming
	 * @param:   @param paymentAddress
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setPaymentAddress(String paymentAddress) {
		this.paymentAddress = paymentAddress;
	}
	
	
	/**
	 * <p>核实时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public Date getVerifyDate() {
		return verifyDate;
	}
	
	/**
	 * <p>核实时间</p>
	 * @author:  zhouming
	 * @param:   @param verifyDate
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}
	
	
	/**
	 * <p>当前规则中有效期天数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public Integer getBaseValidityDay() {
		return baseValidityDay;
	}
	
	/**
	 * <p>当前规则中有效期天数</p>
	 * @author:  zhouming
	 * @param:   @param baseValidityDay
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setBaseValidityDay(Integer baseValidityDay) {
		this.baseValidityDay = baseValidityDay;
	}
	
	
	/**
	 * <p>上笔剩余天数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public Integer getResidueValidityDay() {
		return residueValidityDay;
	}
	
	/**
	 * <p>上笔剩余天数</p>
	 * @author:  zhouming
	 * @param:   @param residueValidityDay
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setResidueValidityDay(Integer residueValidityDay) {
		this.residueValidityDay = residueValidityDay;
	}
	
	
	/**
	 * <p>实际保证期有效天数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public Integer getValidityDay() {
		return validityDay;
	}
	
	/**
	 * <p>实际保证期有效天数</p>
	 * @author:  zhouming
	 * @param:   @param validityDay
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setValidityDay(Integer validityDay) {
		this.validityDay = validityDay;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:38:22    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-06-05 16:38:22   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

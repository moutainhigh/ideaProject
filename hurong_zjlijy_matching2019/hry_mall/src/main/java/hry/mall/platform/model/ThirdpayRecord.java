/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-12-21 15:28:20 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ThirdpayRecord </p>
 * @author:         luyue
 * @Date :          2018-12-21 15:28:20  
 */
@Table(name="shop_thirdpay_record")
public class ThirdpayRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "memberId")
	private Long memberId;  //用户ID,来源于app_customer主键
	
	@Column(name= "loginName")
	private String loginName;  //用户姓名
	
	@Column(name= "thirdPayConfig")
	private String thirdPayConfig;  //第三方标识
	
	@Column(name= "thirdPayFlagId")
	private String thirdPayFlagId;  //用户第三方账号
	
	@Column(name= "interfaceType")
	private String interfaceType;  //接口类型
	
	@Column(name= "interfaceName")
	private String interfaceName;  //接口名称
	
	@Column(name= "dealMoney")
	private BigDecimal dealMoney;  //交易金额
	
	@Column(name= "requestTime")
	private Date requestTime;  //请求接口时间
	
	@Column(name= "requestNum")
	private Integer requestNum;  //请求接口次数
	
	@Column(name= "returnTime")
	private Date returnTime;  //回调平台时间
	
	@Column(name= "returnNum")
	private Integer returnNum;  //回调次数
	
	@Column(name= "status")
	private Integer status;  //0=开始请求，1=已发起请求，2=交易成功，3=交易失败，4=第三方交易成功但平台处理业务数据异常
	
	@Column(name= "recordNumber")
	private String recordNumber;  //平台流水号
	
	@Column(name= "thirdRecordNumber")
	private String thirdRecordNumber;  //第三方返回流水号
	
	@Column(name= "code")
	private String code;  //第三方返回状态码
	
	@Column(name= "codeMsg")
	private String codeMsg;  //第三方返回状态说明
	
	@Column(name= "remark1")
	private String remark1;  //备注1
	
	@Column(name= "orderId")
	private Long orderId;  //订单id
	
	@Column(name= "phone")
	private String phone;  //手机号
	
	
	
	
	/**
	 * <p></p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户ID,来源于app_customer主键</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户ID,来源于app_customer主键</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>用户姓名</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public String getLoginName() {
		return loginName;
	}
	
	/**
	 * <p>用户姓名</p>
	 * @author:  luyue
	 * @param:   @param loginName
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	
	/**
	 * <p>第三方标识</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public String getThirdPayConfig() {
		return thirdPayConfig;
	}
	
	/**
	 * <p>第三方标识</p>
	 * @author:  luyue
	 * @param:   @param thirdPayConfig
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setThirdPayConfig(String thirdPayConfig) {
		this.thirdPayConfig = thirdPayConfig;
	}
	
	
	/**
	 * <p>用户第三方账号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public String getThirdPayFlagId() {
		return thirdPayFlagId;
	}
	
	/**
	 * <p>用户第三方账号</p>
	 * @author:  luyue
	 * @param:   @param thirdPayFlagId
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setThirdPayFlagId(String thirdPayFlagId) {
		this.thirdPayFlagId = thirdPayFlagId;
	}
	
	
	/**
	 * <p>接口类型</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public String getInterfaceType() {
		return interfaceType;
	}
	
	/**
	 * <p>接口类型</p>
	 * @author:  luyue
	 * @param:   @param interfaceType
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}
	
	
	/**
	 * <p>接口名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public String getInterfaceName() {
		return interfaceName;
	}
	
	/**
	 * <p>接口名称</p>
	 * @author:  luyue
	 * @param:   @param interfaceName
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	
	
	/**
	 * <p>交易金额</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public BigDecimal getDealMoney() {
		return dealMoney;
	}
	
	/**
	 * <p>交易金额</p>
	 * @author:  luyue
	 * @param:   @param dealMoney
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setDealMoney(BigDecimal dealMoney) {
		this.dealMoney = dealMoney;
	}
	
	
	/**
	 * <p>请求接口时间</p>
	 * @author:  luyue
	 * @return:  Date 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public Date getRequestTime() {
		return requestTime;
	}
	
	/**
	 * <p>请求接口时间</p>
	 * @author:  luyue
	 * @param:   @param requestTime
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	
	
	/**
	 * <p>请求接口次数</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public Integer getRequestNum() {
		return requestNum;
	}
	
	/**
	 * <p>请求接口次数</p>
	 * @author:  luyue
	 * @param:   @param requestNum
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setRequestNum(Integer requestNum) {
		this.requestNum = requestNum;
	}
	
	
	/**
	 * <p>回调平台时间</p>
	 * @author:  luyue
	 * @return:  Date 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public Date getReturnTime() {
		return returnTime;
	}
	
	/**
	 * <p>回调平台时间</p>
	 * @author:  luyue
	 * @param:   @param returnTime
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	
	
	/**
	 * <p>回调次数</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public Integer getReturnNum() {
		return returnNum;
	}
	
	/**
	 * <p>回调次数</p>
	 * @author:  luyue
	 * @param:   @param returnNum
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}
	
	
	/**
	 * <p>0=开始请求，1=已发起请求，2=交易成功，3=交易失败，4=第三方交易成功但平台处理业务数据异常</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>0=开始请求，1=已发起请求，2=交易成功，3=交易失败，4=第三方交易成功但平台处理业务数据异常</p>
	 * @author:  luyue
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>平台流水号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public String getRecordNumber() {
		return recordNumber;
	}
	
	/**
	 * <p>平台流水号</p>
	 * @author:  luyue
	 * @param:   @param recordNumber
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}
	
	
	/**
	 * <p>第三方返回流水号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public String getThirdRecordNumber() {
		return thirdRecordNumber;
	}
	
	/**
	 * <p>第三方返回流水号</p>
	 * @author:  luyue
	 * @param:   @param thirdRecordNumber
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setThirdRecordNumber(String thirdRecordNumber) {
		this.thirdRecordNumber = thirdRecordNumber;
	}
	
	
	/**
	 * <p>第三方返回状态码</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * <p>第三方返回状态码</p>
	 * @author:  luyue
	 * @param:   @param code
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	
	/**
	 * <p>第三方返回状态说明</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public String getCodeMsg() {
		return codeMsg;
	}
	
	/**
	 * <p>第三方返回状态说明</p>
	 * @author:  luyue
	 * @param:   @param codeMsg
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setCodeMsg(String codeMsg) {
		this.codeMsg = codeMsg;
	}
	
	
	/**
	 * <p>备注1</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public String getRemark1() {
		return remark1;
	}
	
	/**
	 * <p>备注1</p>
	 * @author:  luyue
	 * @param:   @param remark1
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-21 15:28:20    
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @param:   @param phone
	 * @return:  void 
	 * @Date :   2018-12-21 15:28:20   
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}

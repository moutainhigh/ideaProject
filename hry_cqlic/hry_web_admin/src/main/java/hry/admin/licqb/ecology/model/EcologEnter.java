/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:37:53 
 */
package hry.admin.licqb.ecology.model;


import hry.bean.BaseModel;


import javax.persistence.*;
import java.util.Date;

/**
 * <p> EcologEnter </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:37:53  
 */
@Table(name="lc_ecolog_enter")
public class EcologEnter extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "orderNum")
	private String orderNum;  //单号
	
	@Column(name= "plateId")
	private Long plateId;  //板块Id

	@Transient
	private String plateName; // 板块名称
	
	@Column(name= "enterLevel")
	private String enterLevel;  //入驻等级 A(前三) B 
	
	@Column(name= "enterName")
	private String enterName;  //入驻名称
	
	@Column(name= "enterLogo")
	private String enterLogo;  //入驻logo
	
	@Column(name= "downloadLink")
	private String downloadLink;  //下载链接
	
	@Column(name= "enterApplyIntro")
	private String enterApplyIntro;  //申请入驻简介
	
	@Column(name= "enterStatus")
	private Integer enterStatus;  //1 申请中 2 后台审核拒绝 3 后台审核通过(待付款) 4 用户拒绝 5 用户通过（待核实） 6 核实通过 7 核实未通过 8 已到期
	
	@Column(name= "renewStatus")
	private Integer renewStatus;  //0 未申请续费 1 续费待核实 
	
	@Column(name= "enterReply")
	private String enterReply;  //入驻申请回复

	@Column(name= "verifyDate")
	private Date verifyDate;  //核实时间
	
	@Column(name= "validityDay")
	private Integer validityDay;  //实际保证期有效天数
	
	@Column(name= "expireDate")
	private Date expireDate;  //到期时间
	
	@Column(name= "saasId")
	private String saasId;  //

	@Transient
	private String mobilePhone; // 手机号
	@Transient
	private String email; //邮箱

	@Transient
	private String acceptAddress; // 收款地址

	@Transient
	private String paymentAddress; // 付款地址

	public String getAcceptAddress() {
		return acceptAddress;
	}

	public void setAcceptAddress(String acceptAddress) {
		this.acceptAddress = acceptAddress;
	}

	public String getPaymentAddress() {
		return paymentAddress;
	}

	public void setPaymentAddress(String paymentAddress) {
		this.paymentAddress = paymentAddress;
	}

	public Date getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(Date verifyDate) {
		this.verifyDate = verifyDate;
	}

	public String getPlateName() {
		return plateName;
	}

	public void setPlateName(String plateName) {
		this.plateName = plateName;
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

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>单号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public String getOrderNum() {
		return orderNum;
	}
	
	/**
	 * <p>单号</p>
	 * @author:  zhouming
	 * @param:   @param orderNum
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	/**
	 * <p>板块Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public Long getPlateId() {
		return plateId;
	}
	
	/**
	 * <p>板块Id</p>
	 * @author:  zhouming
	 * @param:   @param plateId
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setPlateId(Long plateId) {
		this.plateId = plateId;
	}
	
	
	/**
	 * <p>入驻等级 A(前三) B </p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public String getEnterLevel() {
		return enterLevel;
	}
	
	/**
	 * <p>入驻等级 A(前三) B </p>
	 * @author:  zhouming
	 * @param:   @param enterLevel
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setEnterLevel(String enterLevel) {
		this.enterLevel = enterLevel;
	}
	
	
	/**
	 * <p>入驻名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public String getEnterName() {
		return enterName;
	}
	
	/**
	 * <p>入驻名称</p>
	 * @author:  zhouming
	 * @param:   @param enterName
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setEnterName(String enterName) {
		this.enterName = enterName;
	}
	
	
	/**
	 * <p>入驻logo</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public String getEnterLogo() {
		return enterLogo;
	}
	
	/**
	 * <p>入驻logo</p>
	 * @author:  zhouming
	 * @param:   @param enterLogo
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setEnterLogo(String enterLogo) {
		this.enterLogo = enterLogo;
	}
	
	
	/**
	 * <p>下载链接</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public String getDownloadLink() {
		return downloadLink;
	}
	
	/**
	 * <p>下载链接</p>
	 * @author:  zhouming
	 * @param:   @param downloadLink
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	
	
	/**
	 * <p>申请入驻简介</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public String getEnterApplyIntro() {
		return enterApplyIntro;
	}
	
	/**
	 * <p>申请入驻简介</p>
	 * @author:  zhouming
	 * @param:   @param enterApplyIntro
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setEnterApplyIntro(String enterApplyIntro) {
		this.enterApplyIntro = enterApplyIntro;
	}
	
	
	/**
	 * <p>1 申请中 2 后台审核拒绝 3 后台审核通过(待付款) 4 用户拒绝 5 用户通过（待核实） 6 核实通过 7 核实未通过 8 已到期</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public Integer getEnterStatus() {
		return enterStatus;
	}
	
	/**
	 * <p>1 申请中 2 后台审核拒绝 3 后台审核通过(待付款) 4 用户拒绝 5 用户通过（待核实） 6 核实通过 7 核实未通过 8 已到期</p>
	 * @author:  zhouming
	 * @param:   @param enterStatus
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setEnterStatus(Integer enterStatus) {
		this.enterStatus = enterStatus;
	}
	
	
	/**
	 * <p>0 未申请续费 1 续费待核实 </p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public Integer getRenewStatus() {
		return renewStatus;
	}
	
	/**
	 * <p>0 未申请续费 1 续费待核实 </p>
	 * @author:  zhouming
	 * @param:   @param renewStatus
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setRenewStatus(Integer renewStatus) {
		this.renewStatus = renewStatus;
	}
	
	
	/**
	 * <p>入驻申请回复</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public String getEnterReply() {
		return enterReply;
	}
	
	/**
	 * <p>入驻申请回复</p>
	 * @author:  zhouming
	 * @param:   @param enterReply
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setEnterReply(String enterReply) {
		this.enterReply = enterReply;
	}
	
	
	/**
	 * <p>实际保证期有效天数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public Integer getValidityDay() {
		return validityDay;
	}
	
	/**
	 * <p>实际保证期有效天数</p>
	 * @author:  zhouming
	 * @param:   @param validityDay
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setValidityDay(Integer validityDay) {
		this.validityDay = validityDay;
	}


	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:37:53    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:53   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

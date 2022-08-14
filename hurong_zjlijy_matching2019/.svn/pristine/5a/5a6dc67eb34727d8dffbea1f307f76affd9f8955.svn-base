/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-12-01 12:05:17 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> Payment </p>
 * @author:         kongdebiao
 * @Date :          2018-12-01 12:05:17  
 */
@Table(name="shop_payment")
public class Payment extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //支付索引id
	
	@Column(name= "paymentCode")
	private String paymentCode;  //支付代码名称
	
	@Column(name= "paymentName")
	private String paymentName;  //支付名称
	
	@Column(name= "paymentDescribe")
	private String paymentDescribe;  //支付方式描述
	
	@Column(name= "paymentLogo")
	private String paymentLogo;  //支付方式logo
	
	@Column(name= "appId")
	private String appId;  //AppId
	
	@Column(name= "appSecret")
	private String appSecret;  //AppSecret
	
	@Column(name= "merchantsKey")
	private String merchantsKey;  //商户支付密钥Key
	
	@Column(name= "merchantsId")
	private String merchantsId;  //受理商ID或商户号
	
	@Column(name= "payPoundage")
	private String payPoundage;  //支付手续费
	
	@Column(name= "sort")
	private String sort;  //排序
	
	@Column(name= "paymentState")
	private Integer paymentState;  //接口状态0禁用1启用
	
	
	
	
	/**
	 * <p>支付索引id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>支付索引id</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>支付代码名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public String getPaymentCode() {
		return paymentCode;
	}
	
	/**
	 * <p>支付代码名称</p>
	 * @author:  kongdebiao
	 * @param:   @param paymentCode
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	
	
	/**
	 * <p>支付名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public String getPaymentName() {
		return paymentName;
	}
	
	/**
	 * <p>支付名称</p>
	 * @author:  kongdebiao
	 * @param:   @param paymentName
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	
	
	/**
	 * <p>支付方式描述</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public String getPaymentDescribe() {
		return paymentDescribe;
	}
	
	/**
	 * <p>支付方式描述</p>
	 * @author:  kongdebiao
	 * @param:   @param paymentDescribe
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setPaymentDescribe(String paymentDescribe) {
		this.paymentDescribe = paymentDescribe;
	}
	
	
	/**
	 * <p>支付方式logo</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public String getPaymentLogo() {
		return paymentLogo;
	}
	
	/**
	 * <p>支付方式logo</p>
	 * @author:  kongdebiao
	 * @param:   @param paymentLogo
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setPaymentLogo(String paymentLogo) {
		this.paymentLogo = paymentLogo;
	}
	
	
	/**
	 * <p>AppId</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public String getAppId() {
		return appId;
	}
	
	/**
	 * <p>AppId</p>
	 * @author:  kongdebiao
	 * @param:   @param appId
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	
	/**
	 * <p>AppSecret</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public String getAppSecret() {
		return appSecret;
	}
	
	/**
	 * <p>AppSecret</p>
	 * @author:  kongdebiao
	 * @param:   @param appSecret
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
	
	/**
	 * <p>商户支付密钥Key</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public String getMerchantsKey() {
		return merchantsKey;
	}
	
	/**
	 * <p>商户支付密钥Key</p>
	 * @author:  kongdebiao
	 * @param:   @param merchantsKey
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setMerchantsKey(String merchantsKey) {
		this.merchantsKey = merchantsKey;
	}
	
	
	/**
	 * <p>受理商ID或商户号</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public String getMerchantsId() {
		return merchantsId;
	}
	
	/**
	 * <p>受理商ID或商户号</p>
	 * @author:  kongdebiao
	 * @param:   @param merchantsId
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setMerchantsId(String merchantsId) {
		this.merchantsId = merchantsId;
	}
	
	
	/**
	 * <p>支付手续费</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public String getPayPoundage() {
		return payPoundage;
	}
	
	/**
	 * <p>支付手续费</p>
	 * @author:  kongdebiao
	 * @param:   @param payPoundage
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setPayPoundage(String payPoundage) {
		this.payPoundage = payPoundage;
	}
	
	
	/**
	 * <p>排序</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public String getSort() {
		return sort;
	}
	
	/**
	 * <p>排序</p>
	 * @author:  kongdebiao
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p>接口状态0禁用1启用</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-12-01 12:05:17    
	 */
	public Integer getPaymentState() {
		return paymentState;
	}
	
	/**
	 * <p>接口状态0禁用1启用</p>
	 * @author:  kongdebiao
	 * @param:   @param paymentState
	 * @return:  void 
	 * @Date :   2018-12-01 12:05:17   
	 */
	public void setPaymentState(Integer paymentState) {
		this.paymentState = paymentState;
	}
	
	

}

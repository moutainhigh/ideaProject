/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-16 14:44:03 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> OrderAddress </p>
 * @author:         luyue
 * @Date :          2018-11-16 14:44:03  
 */
@Table(name="shop_order_address")
public class OrderAddress extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "memberId")
	private Long memberId;  //前台账号id
	
	@Column(name= "orderId")
	private Long orderId;  //订单id
	
	@Column(name= "provinceId")
	private String provinceId;  //省id
	
	@Column(name= "cityId")
	private String cityId;  //市id
	
	@Column(name= "countyId")
	private String countyId;  //县id
	
	@Column(name= "street")
	private String street;  //街道
	
	@Column(name= "detailAddress")
	private String detailAddress;  //详细地址
	
	@Column(name= "cellphone")
	private String cellphone;  //手机号
	
	@Column(name= "receiveName")
	private String receiveName;  //收货人姓名
	
	@Column(name= "zipCode")
	private String zipCode;  //邮政编码

	@Transient
	private String provinceName;  //省名称

	@Transient
	private String cityName;  //市名称

	@Transient
	private String countyName;  //区名称

	@Transient
	private String allAddress;  //省市区名称

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getAllAddress() {
		return allAddress;
	}

	public void setAllAddress(String allAddress) {
		this.allAddress = allAddress;
	}


	
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-16 14:44:03    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 14:44:03   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>前台账号id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-16 14:44:03    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>前台账号id</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2018-11-16 14:44:03   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-16 14:44:03    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2018-11-16 14:44:03   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>省id</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:44:03    
	 */
	public String getProvinceId() {
		return provinceId;
	}
	
	/**
	 * <p>省id</p>
	 * @author:  luyue
	 * @param:   @param provinceId
	 * @return:  void 
	 * @Date :   2018-11-16 14:44:03   
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	
	/**
	 * <p>市id</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:44:03    
	 */
	public String getCityId() {
		return cityId;
	}
	
	/**
	 * <p>市id</p>
	 * @author:  luyue
	 * @param:   @param cityId
	 * @return:  void 
	 * @Date :   2018-11-16 14:44:03   
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	
	/**
	 * <p>县id</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:44:03    
	 */
	public String getCountyId() {
		return countyId;
	}
	
	/**
	 * <p>县id</p>
	 * @author:  luyue
	 * @param:   @param countyId
	 * @return:  void 
	 * @Date :   2018-11-16 14:44:03   
	 */
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	
	
	/**
	 * <p>街道</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:44:03    
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * <p>街道</p>
	 * @author:  luyue
	 * @param:   @param street
	 * @return:  void 
	 * @Date :   2018-11-16 14:44:03   
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	
	/**
	 * <p>详细地址</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:44:03    
	 */
	public String getDetailAddress() {
		return detailAddress;
	}
	
	/**
	 * <p>详细地址</p>
	 * @author:  luyue
	 * @param:   @param detailAddress
	 * @return:  void 
	 * @Date :   2018-11-16 14:44:03   
	 */
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:44:03    
	 */
	public String getCellphone() {
		return cellphone;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @param:   @param cellphone
	 * @return:  void 
	 * @Date :   2018-11-16 14:44:03   
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
	/**
	 * <p>收货人姓名</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:44:03    
	 */
	public String getReceiveName() {
		return receiveName;
	}
	
	/**
	 * <p>收货人姓名</p>
	 * @author:  luyue
	 * @param:   @param receiveName
	 * @return:  void 
	 * @Date :   2018-11-16 14:44:03   
	 */
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	
	
	/**
	 * <p>邮政编码</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:44:03    
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * <p>邮政编码</p>
	 * @author:  luyue
	 * @param:   @param zipCode
	 * @return:  void 
	 * @Date :   2018-11-16 14:44:03   
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	

}

/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-16 14:57:27 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> OrderDaddress </p>
 * @author:         luyue
 * @Date :          2018-11-16 14:57:27  
 */
@Table(name="shop_order_daddress")
public class OrderDaddress extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "orderId")
	private Long orderId;  //订单id
	
	@Column(name= "type")
	private String type;  //是发货还是退货订单
	
	@Column(name= "name")
	private String name;  //发货点名称
	
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
	
	@Column(name= "sendName")
	private String sendName;  //发货人姓名
	
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
	 * @Date :   2018-11-16 14:57:27    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-16 14:57:27    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>是发货还是退货订单</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:57:27    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>是发货还是退货订单</p>
	 * @author:  luyue
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p>发货点名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:57:27    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>发货点名称</p>
	 * @author:  luyue
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>省id</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:57:27    
	 */
	public String getProvinceId() {
		return provinceId;
	}
	
	/**
	 * <p>省id</p>
	 * @author:  luyue
	 * @param:   @param provinceId
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	
	/**
	 * <p>市id</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:57:27    
	 */
	public String getCityId() {
		return cityId;
	}
	
	/**
	 * <p>市id</p>
	 * @author:  luyue
	 * @param:   @param cityId
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	
	/**
	 * <p>县id</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:57:27    
	 */
	public String getCountyId() {
		return countyId;
	}
	
	/**
	 * <p>县id</p>
	 * @author:  luyue
	 * @param:   @param countyId
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	
	
	/**
	 * <p>街道</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:57:27    
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * <p>街道</p>
	 * @author:  luyue
	 * @param:   @param street
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	
	/**
	 * <p>详细地址</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:57:27    
	 */
	public String getDetailAddress() {
		return detailAddress;
	}
	
	/**
	 * <p>详细地址</p>
	 * @author:  luyue
	 * @param:   @param detailAddress
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:57:27    
	 */
	public String getCellphone() {
		return cellphone;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @param:   @param cellphone
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
	/**
	 * <p>发货人姓名</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:57:27    
	 */
	public String getSendName() {
		return sendName;
	}
	
	/**
	 * <p>发货人姓名</p>
	 * @author:  luyue
	 * @param:   @param sendName
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	
	
	/**
	 * <p>邮政编码</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:57:27    
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * <p>邮政编码</p>
	 * @author:  luyue
	 * @param:   @param zipCode
	 * @return:  void 
	 * @Date :   2018-11-16 14:57:27   
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	

}

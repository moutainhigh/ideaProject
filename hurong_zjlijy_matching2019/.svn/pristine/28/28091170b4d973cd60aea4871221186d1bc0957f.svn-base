/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-16 14:53:24 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> Daddress </p>
 * @author:         luyue
 * @Date :          2018-11-16 14:53:24  
 */
@Table(name="shop_daddress")
public class Daddress extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "name")
	private String name;  //发货点名称
	
	@Column(name= "provinceId")
	private String info_province;  //省id
	
	@Column(name= "cityId")
	private String info_city;  //市id
	
	@Column(name= "countyId")
	private String info_county;  //县id
	
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
	
	@Column(name= "isDefault")
	private Short isDefault;  //发货地址是否默认，1是0否
	
	@Column(name= "isReceiveDefault")
	private Short isReceiveDefault;  //收货地址是否默认，1是0否
	
	@Transient
	private String provinceName;  //省名称
	public Short getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}

	public Short getIsReceiveDefault() {
		return isReceiveDefault;
	}

	public void setIsReceiveDefault(Short isReceiveDefault) {
		this.isReceiveDefault = isReceiveDefault;
	}

	@Transient
	private String cityName;  //市名称
	@Transient
	private String countyName;  //区名称

	
	
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

	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-16 14:53:24    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 14:53:24   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>发货点名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:53:24    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>发货点名称</p>
	 * @author:  luyue
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-11-16 14:53:24   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInfo_province() {
		return info_province;
	}

	public void setInfo_province(String info_province) {
		this.info_province = info_province;
	}

	public String getInfo_city() {
		return info_city;
	}

	public void setInfo_city(String info_city) {
		this.info_city = info_city;
	}

	public String getInfo_county() {
		return info_county;
	}

	public void setInfo_county(String info_county) {
		this.info_county = info_county;
	}

	/**
	 * <p>街道</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:53:24    
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * <p>街道</p>
	 * @author:  luyue
	 * @param:   @param street
	 * @return:  void 
	 * @Date :   2018-11-16 14:53:24   
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	
	/**
	 * <p>详细地址</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:53:24    
	 */
	public String getDetailAddress() {
		return detailAddress;
	}
	
	/**
	 * <p>详细地址</p>
	 * @author:  luyue
	 * @param:   @param detailAddress
	 * @return:  void 
	 * @Date :   2018-11-16 14:53:24   
	 */
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:53:24    
	 */
	public String getCellphone() {
		return cellphone;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @param:   @param cellphone
	 * @return:  void 
	 * @Date :   2018-11-16 14:53:24   
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
	/**
	 * <p>发货人姓名</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:53:24    
	 */
	public String getSendName() {
		return sendName;
	}
	
	/**
	 * <p>发货人姓名</p>
	 * @author:  luyue
	 * @param:   @param sendName
	 * @return:  void 
	 * @Date :   2018-11-16 14:53:24   
	 */
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	
	
	/**
	 * <p>邮政编码</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 14:53:24    
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * <p>邮政编码</p>
	 * @author:  luyue
	 * @param:   @param zipCode
	 * @return:  void 
	 * @Date :   2018-11-16 14:53:24   
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	

}

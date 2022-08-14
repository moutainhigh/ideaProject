/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-11-26 16:42:45 
 */
package hry.mall.merchant.model;


import hry.bean.BaseModel;


import javax.persistence.*;

/**
 * <p> Merchant </p>
 * @author:         zhouming
 * @Date :          2019-11-26 16:42:45  
 */
@Table(name="shop_merchant")
public class Merchant extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "storeName")
	private String storeName;  //商户名称
	
	@Column(name= "memberId")
	private Long memberId;  //商户关联用户Id
	
	@Column(name= "memberName")
	private String memberName;  //用户名称
	
	@Column(name= "merchantGradeId")
	private Long merchantGradeId;  //商户等级Id
	
	@Column(name= "merchantGradeName")
	private String merchantGradeName;  //商户等级名称
	
	@Column(name= "provinceId")
	private String provinceId;  //省id
	
	@Column(name= "cityId")
	private String cityId;  //市id
	
	@Column(name= "countyId")
	private String countyId;  //县id
	
	@Column(name= "merchantAddress")
	private String merchantAddress;  //商户详细地址
	
	@Column(name= "merchantMobile")
	private String merchantMobile;  //商户手机号
	
	@Column(name= "merchantLogo")
	private String merchantLogo;  //商户logo
	
	@Column(name= "merchantCoin")
	private String merchantCoin;  //商户支持币种 例：币种,币种.....
	
	@Column(name= "merchantState")
	private Integer merchantState;  //商户状态，0关闭，1开启，2审核中
	
	@Column(name= "merchantDesc")
	private String merchantDesc;  //商户简介

	@Transient
	private String gradeLogo; // 等级图片

	public String getGradeLogo() {
		return gradeLogo;
	}

	public void setGradeLogo(String gradeLogo) {
		this.gradeLogo = gradeLogo;
	}

	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>商户名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public String getStoreName() {
		return storeName;
	}
	
	/**
	 * <p>商户名称</p>
	 * @author:  zhouming
	 * @param:   @param storeName
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	
	/**
	 * <p>商户关联用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>商户关联用户Id</p>
	 * @author:  zhouming
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>用户名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public String getMemberName() {
		return memberName;
	}
	
	/**
	 * <p>用户名称</p>
	 * @author:  zhouming
	 * @param:   @param memberName
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
	/**
	 * <p>商户等级Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public Long getMerchantGradeId() {
		return merchantGradeId;
	}
	
	/**
	 * <p>商户等级Id</p>
	 * @author:  zhouming
	 * @param:   @param merchantGradeId
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setMerchantGradeId(Long merchantGradeId) {
		this.merchantGradeId = merchantGradeId;
	}
	
	
	/**
	 * <p>商户等级名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public String getMerchantGradeName() {
		return merchantGradeName;
	}
	
	/**
	 * <p>商户等级名称</p>
	 * @author:  zhouming
	 * @param:   @param merchantGradeName
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setMerchantGradeName(String merchantGradeName) {
		this.merchantGradeName = merchantGradeName;
	}
	
	
	/**
	 * <p>省id</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public String getProvinceId() {
		return provinceId;
	}
	
	/**
	 * <p>省id</p>
	 * @author:  zhouming
	 * @param:   @param provinceId
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	
	/**
	 * <p>市id</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public String getCityId() {
		return cityId;
	}
	
	/**
	 * <p>市id</p>
	 * @author:  zhouming
	 * @param:   @param cityId
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	
	/**
	 * <p>县id</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public String getCountyId() {
		return countyId;
	}
	
	/**
	 * <p>县id</p>
	 * @author:  zhouming
	 * @param:   @param countyId
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	
	
	/**
	 * <p>商户详细地址</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public String getMerchantAddress() {
		return merchantAddress;
	}
	
	/**
	 * <p>商户详细地址</p>
	 * @author:  zhouming
	 * @param:   @param merchantAddress
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	
	
	/**
	 * <p>商户手机号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public String getMerchantMobile() {
		return merchantMobile;
	}
	
	/**
	 * <p>商户手机号</p>
	 * @author:  zhouming
	 * @param:   @param merchantMobile
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setMerchantMobile(String merchantMobile) {
		this.merchantMobile = merchantMobile;
	}
	
	
	/**
	 * <p>商户logo</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public String getMerchantLogo() {
		return merchantLogo;
	}
	
	/**
	 * <p>商户logo</p>
	 * @author:  zhouming
	 * @param:   @param merchantLogo
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setMerchantLogo(String merchantLogo) {
		this.merchantLogo = merchantLogo;
	}
	
	
	/**
	 * <p>商户支持币种 例：币种,币种.....</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public String getMerchantCoin() {
		return merchantCoin;
	}
	
	/**
	 * <p>商户支持币种 例：币种,币种.....</p>
	 * @author:  zhouming
	 * @param:   @param merchantCoin
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setMerchantCoin(String merchantCoin) {
		this.merchantCoin = merchantCoin;
	}
	
	
	/**
	 * <p>商户状态，0关闭，1开启，2审核中</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public Integer getMerchantState() {
		return merchantState;
	}
	
	/**
	 * <p>商户状态，0关闭，1开启，2审核中</p>
	 * @author:  zhouming
	 * @param:   @param merchantState
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setMerchantState(Integer merchantState) {
		this.merchantState = merchantState;
	}
	
	
	/**
	 * <p>商户简介</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:42:45    
	 */
	public String getMerchantDesc() {
		return merchantDesc;
	}
	
	/**
	 * <p>商户简介</p>
	 * @author:  zhouming
	 * @param:   @param merchantDesc
	 * @return:  void 
	 * @Date :   2019-11-26 16:42:45   
	 */
	public void setMerchantDesc(String merchantDesc) {
		this.merchantDesc = merchantDesc;
	}
	
	

}

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午4:39:36
 */
package hry.manage.remote.model;

import hry.bean.BaseModel;

public class ExDmCustomerPublicKeyManage extends BaseModel {

	// 主键
	private Long id;
	// key的用户名(前台录入)
	private String publicKeyName;
	private String trueName;
	// 取币地址
	private String publicKey;
	// 币的类型
	private String currencyType;
	// 用户id
	private Long customerId;
	// 地址备注
	private String remark;
	
	private String surname;
	
	private Long create_long;

	private String picturePath;


	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Long getCreate_long() {
		if(super.getCreated()!=null){
			return super.getCreated().getTime();
		}
		return 0L;
	}

	public void setCreate_long(Long create_long) {
		this.create_long = create_long;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPublicKeyName() {
		return publicKeyName;
	}

	public void setPublicKeyName(String publicKeyName) {
		this.publicKeyName = publicKeyName;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ExDmCustomerPublicKeyManage(Long id, String publicKeyName,
			String publicKey, String currencyType, Long customerId,
			String remark) {
		super();
		this.id = id;
		this.publicKeyName = publicKeyName;
		this.publicKey = publicKey;
		this.currencyType = currencyType;
		this.customerId = customerId;
		this.remark = remark;
	}

	public ExDmCustomerPublicKeyManage() {
		super();
	}

	@Override
	public String toString() {
		return "ExDmCustomerPublicKey [id=" + id + ", publicKeyName="
				+ publicKeyName + ", publicKey=" + publicKey
				+ ", currencyType=" + currencyType + ", customerId="
				+ customerId + ", remark=" + remark + "]";
	}

}

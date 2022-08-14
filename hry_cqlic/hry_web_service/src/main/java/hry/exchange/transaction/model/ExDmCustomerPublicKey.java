/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午4:39:36
 */
package hry.exchange.transaction.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;
import hry.customer.person.model.AppPersonInfo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午4:39:36
 */
@SuppressWarnings("serial")
@Table(name = "ex_dm_customer_publickey")
public class ExDmCustomerPublicKey extends BaseModel {

	// 主键
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// key的用户名(前台录入)
	@Column(name = "publicKeyName")
	private String publicKeyName;

	// 取币地址
	@Column(name = "publicKey")
	private String publicKey;
	// 币的类型
	@Column(name = "currencyType")
	private String currencyType;
	// 用户id
	@Column(name = "customerId")
	private Long customerId;
	// 地址备注
	@Column(name = "remark")
	private String remark;
	
	@Transient   //不与数据库映射的字段
	private AppPersonInfo appPersonInfo;
	
	
	

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ExDmCustomerPublicKey(Long id, String publicKeyName,
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

	public ExDmCustomerPublicKey() {
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

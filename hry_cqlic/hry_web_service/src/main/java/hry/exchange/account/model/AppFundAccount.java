/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年3月24日 上午11:02:13
 */
package hry.exchange.account.model;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Zhang Xiaofang
 * @Date : 2016年3月24日 上午11:02:13
 */

@Table(name = "app_fund_account")
public class AppFundAccount extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Long id;

	// 取现地址
	@Column(name = "withdrawAddress")
	protected String withdrawAddress;

	// 币的类型
	@Column(name = "coinType")
	protected String coinType;

	// 前台用户的id
	@Column(name = "customId")
	protected Long customId;

	// 地址备注
	@Column(name = "addressRemark")
	protected String addressRemark;

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 取币地址
	 * 
	 * @return: String
	 */
	public String getWithdrawAddress() {
		return withdrawAddress;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setWithdrawAddress(String withdrawAddress) {
		this.withdrawAddress = withdrawAddress;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getCoinType() {
		return coinType;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: Long
	 */
	public Long getCustomId() {
		return customId;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: Long
	 */
	public void setCustomId(Long customId) {
		this.customId = customId;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getAddressRemark() {
		return addressRemark;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setAddressRemark(String addressRemark) {
		this.addressRemark = addressRemark;
	}

}

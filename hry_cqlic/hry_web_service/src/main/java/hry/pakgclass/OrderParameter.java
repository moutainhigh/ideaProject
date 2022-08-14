/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午3:55:51
 */
package hry.pakgclass;

import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月31日 下午3:55:51
 */
public class OrderParameter extends BaseModel {
	// 用戶id
	private Long customerId;
	// 虚拟货币数量
	private BigDecimal currencyNum;
	// 要转的公钥
	private String currencyKey;
	// 交易类型
	private String Type;
	// 虚拟币的账户id
	private Long currencyId;
	// 用户名
	private String customerName;
	
	//交易币的结算方式
	private String currencyType;
	//网站类型
	private String website;
	
	//手续费
	private BigDecimal fee;
	
	//我方账号
	private String ourAccountNumber;

	// 真实姓名
	private String trueName;
	
	private String surname;

	private String inAddress;//转入钱包
	
	private String outAddress;//转出钱包
	
	
	
	private BigDecimal btcCount;
	
	
	private Date BtcDate;//提取时间
	
	private String transactionNum; // 流水号

	public String getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBtcDate() {
		return BtcDate;
	}

	public void setBtcDate(Date btcDate) {
		BtcDate = btcDate;
	}

	public BigDecimal getBtcCount() {
		return btcCount;
	}

	public void setBtcCount(BigDecimal btcCount) {
		this.btcCount = btcCount;
	}

	public String getInAddress() {
		return inAddress;
	}

	public void setInAddress(String inAddress) {
		this.inAddress = inAddress;
	}

	public String getOutAddress() {
		return outAddress;
	}

	public void setOutAddress(String outAddress) {
		this.outAddress = outAddress;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOurAccountNumber() {
		return ourAccountNumber;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOurAccountNumber(String ourAccountNumber) {
		this.ourAccountNumber = ourAccountNumber;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getCurrencyNum() {
		return currencyNum;
	}

	public void setCurrencyNum(BigDecimal currencyNum) {
		this.currencyNum = currencyNum;
	}

	public String getCurrencyKey() {
		return currencyKey;
	}

	public void setCurrencyKey(String currencyKey) {
		this.currencyKey = currencyKey;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public OrderParameter(Long customerId, BigDecimal currencyNum,
			String currencyKey, String type, Long currencyId,
			String customerName) {
		super();
		this.customerId = customerId;
		this.currencyNum = currencyNum;
		this.currencyKey = currencyKey;
		Type = type;
		this.currencyId = currencyId;
		this.customerName = customerName;
	}

	public OrderParameter() {
		super();
	}

	@Override
	public String toString() {
		return "OrderParameter [customerId=" + customerId + ", currencyNum="
				+ currencyNum + ", currencyKey=" + currencyKey + ", Type="
				+ Type + ", currencyId=" + currencyId + ", customerName="
				+ customerName + "]";
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	

}

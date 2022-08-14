/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年9月20日 下午3:38:37
 */
package hry.calculate.mvc.po;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年9月20日 下午3:38:37 
 */
public class HistoryAccountFundInfo  implements Serializable {
	 private static final long serialVersionUID = -4825890686624512635L;
	private String coinCode;
	private String userName;

	private BigDecimal rechargeMoney;
	private BigDecimal withdrawMoney;
	private BigDecimal withdrawFee;
	private BigDecimal withdrawcoldMoney;
	private BigDecimal rechargeMoneyFee;
	
	//作为交易币：计算币的买成交量，卖成交量，成交手续费
	private BigDecimal sellTransactionMoney; 
	private BigDecimal buyTransactionMoney;
	private BigDecimal transactionFee;
	private BigDecimal coldEntrustMoney;
	//作为定价币：买成交额,卖成交额，成交手续费
	private BigDecimal sellTransactionFixPrice;
	private BigDecimal buyTransactionFixPrice;
	private BigDecimal coldEntrustFixPrice;
	private BigDecimal transactionFeeFixPrice;
	//佣金
	private BigDecimal drawalMoney;
	//融资融币
	private BigDecimal LendMoney;
	private BigDecimal repaylendMoney;
	private BigDecimal disableMoney;
	
	
	
	private String currencyType;  //交易类型
	private String website;//站点类别默认cn
	
	
	public BigDecimal getDrawalMoney() {
		return drawalMoney;
	}
	public void setDrawalMoney(BigDecimal drawalMoney) {
		this.drawalMoney = drawalMoney;
	}
	public BigDecimal getWithdrawFee() {
		return withdrawFee;
	}
	public void setWithdrawFee(BigDecimal withdrawFee) {
		this.withdrawFee = withdrawFee;
	}
	public BigDecimal getSellTransactionFixPrice() {
		return sellTransactionFixPrice;
	}
	public void setSellTransactionFixPrice(BigDecimal sellTransactionFixPrice) {
		this.sellTransactionFixPrice = sellTransactionFixPrice;
	}
	public BigDecimal getBuyTransactionFixPrice() {
		return buyTransactionFixPrice;
	}
	public void setBuyTransactionFixPrice(BigDecimal buyTransactionFixPrice) {
		this.buyTransactionFixPrice = buyTransactionFixPrice;
	}
	public BigDecimal getColdEntrustFixPrice() {
		return coldEntrustFixPrice;
	}
	public void setColdEntrustFixPrice(BigDecimal coldEntrustFixPrice) {
		this.coldEntrustFixPrice = coldEntrustFixPrice;
	}
	public BigDecimal getTransactionFeeFixPrice() {
		return transactionFeeFixPrice;
	}
	public void setTransactionFeeFixPrice(BigDecimal transactionFeeFixPrice) {
		this.transactionFeeFixPrice = transactionFeeFixPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getDisableMoney() {
		return disableMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setDisableMoney(BigDecimal disableMoney) {
		this.disableMoney = disableMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRechargeMoneyFee() {
		return rechargeMoneyFee;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRechargeMoneyFee(BigDecimal rechargeMoneyFee) {
		this.rechargeMoneyFee = rechargeMoneyFee;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getLendMoney() {
		return LendMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setLendMoney(BigDecimal lendMoney) {
		LendMoney = lendMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRepaylendMoney() {
		return repaylendMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRepaylendMoney(BigDecimal repaylendMoney) {
		this.repaylendMoney = repaylendMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getWithdrawcoldMoney() {
		return withdrawcoldMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setWithdrawcoldMoney(BigDecimal withdrawcoldMoney) {
		this.withdrawcoldMoney = withdrawcoldMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRechargeMoney() {
		return rechargeMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getWithdrawMoney() {
		return withdrawMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setWithdrawMoney(BigDecimal withdrawMoney) {
		this.withdrawMoney = withdrawMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getSellTransactionMoney() {
		return sellTransactionMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setSellTransactionMoney(BigDecimal sellTransactionMoney) {
		this.sellTransactionMoney = sellTransactionMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getBuyTransactionMoney() {
		return buyTransactionMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setBuyTransactionMoney(BigDecimal buyTransactionMoney) {
		this.buyTransactionMoney = buyTransactionMoney;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTransactionFee() {
		return transactionFee;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTransactionFee(BigDecimal transactionFee) {
		this.transactionFee = transactionFee;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getColdEntrustMoney() {
		return coldEntrustMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setColdEntrustMoney(BigDecimal coldEntrustMoney) {
		this.coldEntrustMoney = coldEntrustMoney;
	}
	
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCoinCode() {
		return coinCode;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserName() {
		return userName;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getWebsite() {
		return website;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	 
	
	
}

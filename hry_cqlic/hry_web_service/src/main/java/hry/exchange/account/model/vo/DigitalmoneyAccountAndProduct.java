/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月1日 下午6:36:53
 */
package hry.exchange.account.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Wu shuiming
 * @date 2016年8月1日 下午6:36:53
 * 
 * 查询用户的所有虚拟币信息  包括产品信息    
 * 
 */
@SuppressWarnings("serial")
public class DigitalmoneyAccountAndProduct implements Serializable {
	
	public String coinName;  // 币的名称
	
	public String coinCode;  //  币的代码
	
	public String picturePath;  // 图片地址
	
	public BigDecimal hotMoney;  // 可用余额
	
	public BigDecimal coldMoney; // 冻结钱数
	
	public BigDecimal lendMoney; // 已借的钱 
	
	public String userName;  // 用户名
	
	public String publicKey; // 公钥
	
	public Integer sort = 0;// 排序字段
	
	public BigDecimal buyRate ;// 买方手续费率
	
	public BigDecimal sellRate;// 卖方手续费率
	
	public Integer keepDecimalForCurrency; // 钱保留的位数
	
	
	public Integer getKeepDecimalForCurrency() {
		return keepDecimalForCurrency;
	}

	public void setKeepDecimalForCurrency(Integer keepDecimalForCurrency) {
		this.keepDecimalForCurrency = keepDecimalForCurrency;
	}

	public BigDecimal getBuyRate() {
		return buyRate;
	}

	public void setBuyRate(BigDecimal buyRate) {
		this.buyRate = buyRate;
	}

	public BigDecimal getSellRate() {
		return sellRate;
	}

	public void setSellRate(BigDecimal sellRate) {
		this.sellRate = sellRate;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public BigDecimal getHotMoney() {
		return hotMoney;
	}

	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}

	public BigDecimal getColdMoney() {
		return coldMoney;
	}

	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}

	public BigDecimal getLendMoney() {
		return lendMoney;
	}

	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	
	

	public DigitalmoneyAccountAndProduct(String coinName, String coinCode,
			String picturePath, BigDecimal hotMoney, BigDecimal coldMoney,
			BigDecimal lendMoney, String userName, String publicKey,
			Integer sort, BigDecimal buyRate, BigDecimal sellRate) {
		super();
		this.coinName = coinName;
		this.coinCode = coinCode;
		this.picturePath = picturePath;
		this.hotMoney = hotMoney;
		this.coldMoney = coldMoney;
		this.lendMoney = lendMoney;
		this.userName = userName;
		this.publicKey = publicKey;
		this.sort = sort;
		this.buyRate = buyRate;
		this.sellRate = sellRate;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public DigitalmoneyAccountAndProduct() {
		super();
	}
	
	

}

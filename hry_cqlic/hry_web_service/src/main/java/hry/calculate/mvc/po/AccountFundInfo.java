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
public class AccountFundInfo  implements Serializable {
	 private static final long serialVersionUID = -4825890686624512635L;
	
	private BigDecimal hotMoney;
	private BigDecimal coldMoney;
	private BigDecimal lendMoney;
	private String coinCode;
	private String userName;
	 
	private String currencyType;  //交易类型
	private String website;//站点类别默认cn
	public  static int rmbsacle=4;
	public  static int sacle=8;
	public String getCurrencyType() {
		return currencyType;
	}

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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	public void setHotMoney(BigDecimal hotMoney) {
	/*	if(this.userName.equals("18606930927")&&this.coinCode.equals("cny")){
			this.hotMoney = hotMoney.setScale(4,BigDecimal.ROUND_DOWN);
		}*/
		this.hotMoney = hotMoney.setScale(sacle,BigDecimal.ROUND_DOWN);
	}
	
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney.setScale(sacle,BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getLendMoney() {
		return lendMoney;
	}

	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney.setScale(sacle,BigDecimal.ROUND_DOWN);
	}


	public String getCoinCode() {
		return coinCode;
	}


	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	

	@Override
	public String toString() {
		//保留两位数据进行比较，如果角分相等就认为账户是没问题的
		return "AccountFundInfo [website=" + website + ", currencyType=" + currencyType
				+ ", coinCode=" + coinCode + ", hotMoney=" + hotMoney.setScale(sacle,BigDecimal.ROUND_DOWN).toPlainString()
				+ ", coldMoney=" + coldMoney.setScale(sacle,BigDecimal.ROUND_DOWN).toPlainString() + ", lendMoney=" + lendMoney.setScale(sacle,BigDecimal.ROUND_DOWN).toPlainString() + ", userName="
				+ userName ;
	}

	
}

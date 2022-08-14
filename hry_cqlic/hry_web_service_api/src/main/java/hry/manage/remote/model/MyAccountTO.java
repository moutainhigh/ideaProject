package hry.manage.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;

//个人中心数据模型
public class MyAccountTO implements Serializable {
	
	//冻结
	private BigDecimal coldMoney;
	//可用
	private BigDecimal hotMoney;
	//净资产
	private BigDecimal rmbAccountNetAsse;
	//总资产
	private BigDecimal sumRmbfund;
	//美元总资产
	private  BigDecimal sumMoney;
	//比特币总资产
	private  BigDecimal sumBtcMoney;




	public BigDecimal getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(BigDecimal sumMoney) {
		this.sumMoney = sumMoney;
	}

	public BigDecimal getHotMoney() {
		return hotMoney;
	}

	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}

	public BigDecimal getRmbAccountNetAsse() {
		return rmbAccountNetAsse;
	}

	public void setRmbAccountNetAsse(BigDecimal rmbAccountNetAsse) {
		this.rmbAccountNetAsse = rmbAccountNetAsse;
	}

	public BigDecimal getSumRmbfund() {
		return sumRmbfund;
	}

	public void setSumRmbfund(BigDecimal sumRmbfund) {
		this.sumRmbfund = sumRmbfund;
	}

	public BigDecimal getColdMoney() {
		return coldMoney;
	}

	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}

	public BigDecimal getSumBtcMoney() {
		return sumBtcMoney;
	}

	public void setSumBtcMoney(BigDecimal sumBtcMoney) {
		this.sumBtcMoney = sumBtcMoney;
	}
	
	
	
	

}

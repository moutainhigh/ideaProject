package hry.manage.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class CoinAccount implements Serializable {
	// 币的id 在wap端使用 
	private Long id;
	
	private String coinCode;
	// 可用总额
	private BigDecimal hotMoney;
	// 冻结总额
	private BigDecimal coldMoney;

	// 总额(可用+冻结，方便前台计算)
	private BigDecimal totalMoney;
	
	private String picturePath;
	
	private String name;//币的名字
	
	private String currencyType;//币的类型
	
	private String accountNum;//虚拟账号
	//为手机端使用
	private String tokenId;
	
	private String languageCode;
	//币的保留几位小数
	private Integer keepDecimalForCoin;

	//折合法币的保留几位小数
	private Integer keepDecimalForMoney;
	
	//api使用0为法币1为虚拟货币
	private Integer moneyAndCoin;
	
	private BigDecimal shizhi;
	// 总额,用来排序
	private BigDecimal allMoney;

	//实时价格（人民币）
	private String realTimePrice;

	// 是否开启提币
	private String openTibi;

	public Integer getKeepDecimalForMoney() {
		return keepDecimalForMoney;
	}

	public void setKeepDecimalForMoney(Integer keepDecimalForMoney) {
		this.keepDecimalForMoney = keepDecimalForMoney;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getRealTimePrice() {
		return realTimePrice;
	}

	public void setRealTimePrice(String realTimePrice) {
		this.realTimePrice = realTimePrice;
	}

	public BigDecimal getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(BigDecimal allMoney) {
		this.allMoney = allMoney;
	}

	public BigDecimal getShizhi() {
		return shizhi;
	}


	public void setShizhi(BigDecimal shizhi) {
		this.shizhi = shizhi;
	}

	public Integer getMoneyAndCoin() {
		return moneyAndCoin;
	}
	public void setMoneyAndCoin(Integer moneyAndCoin) {
		this.moneyAndCoin = moneyAndCoin;
	}
	public Integer getKeepDecimalForCoin() {
		return keepDecimalForCoin;
	}
	public void setKeepDecimalForCoin(Integer keepDecimalForCoin) {
		this.keepDecimalForCoin = keepDecimalForCoin;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoinCode() {
		return coinCode;
	}
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
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
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public String getOpenTibi () {
		return openTibi;
	}

	public void setOpenTibi (String openTibi) {
		this.openTibi = openTibi;
	}
}

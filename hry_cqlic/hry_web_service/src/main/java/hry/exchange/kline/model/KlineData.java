/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月3日 下午3:06:14
 */
package hry.exchange.kline.model;

import java.io.Serializable;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> K线数据</p>
 * @author:         Liu Yuan Zhicheng 
 * @Date :          2016年5月13日 下午3:06:14 
 */
public class KlineData implements Serializable{
	
	// 人民币 兑换美元 汇率 eg ：6.5057
	private String USDCNY="" ;
	
	//虚拟币单位 BTC / LTC ...
	private String contractUnit="" ;
	
	//市场名称 eg: HRY(中国)
	 private String marketName="" ;
	 
	 //资金类型 默认 人民币
	 private String moneyType="CNY";
	 
	 //标识 eg:hrybtccny
	 private String symbol="";
	 
	 //K线数据
	 private Object data;
	 
	 //其他平台 指定时间范围内的交易数据
	 private Object topTickers;

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUSDCNY() {
		return USDCNY;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUSDCNY(String uSDCNY) {
		USDCNY = uSDCNY;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getContractUnit() {
		return contractUnit;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setContractUnit(String contractUnit) {
		this.contractUnit = contractUnit;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getMarketName() {
		return marketName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getMoneyType() {
		return moneyType;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSymbol() {
		return symbol;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Object
	 */
	public Object getData() {
		return data;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Object
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Object
	 */
	public Object getTopTickers() {
		return topTickers;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Object
	 */
	public void setTopTickers(Object topTickers) {
		this.topTickers = topTickers;
	}
	 
	 
	 
}

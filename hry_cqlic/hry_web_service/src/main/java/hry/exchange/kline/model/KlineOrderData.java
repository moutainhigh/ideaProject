/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月19日 下午1:46:34
 */
package hry.exchange.kline.model;

import java.math.BigDecimal;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月19日 下午1:46:34 
 */
public class KlineOrderData {
	
	//对人民币
	private BigDecimal CNY;
	//对美元
	private BigDecimal USD;
	//时间
	private Long timestamp;
	//类型
	private String type = "";

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getCNY() {
		return CNY;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setCNY(BigDecimal cNY) {
		CNY = cNY;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getUSD() {
		return USD;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setUSD(BigDecimal uSD) {
		USD = uSD;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getType() {
		return type;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
}

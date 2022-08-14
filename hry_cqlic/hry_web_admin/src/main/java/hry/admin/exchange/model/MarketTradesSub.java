/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年5月17日 下午7:29:42
 */
package hry.admin.exchange.model;

import java.math.BigDecimal;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月17日 下午7:29:42 
 */
public class MarketTradesSub {
	private BigDecimal amount;
	private BigDecimal price;
	private String tid;
	private long  time;
	private String type;
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTid() {
		return tid;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}


	/**
	 * <p> TODO</p>
	 * @return:     long
	 */
	public long getTime() {
		return time;
	}
	/** 
	 * <p> TODO</p>
	 * @return: long
	 */
	public void setTime(long time) {
		this.time = time;
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

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年5月13日 下午5:27:31
 */
package hry.trade.websoketContext.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月13日 下午5:27:31 
 */
public class Total {
	public  String currentExchangPrice;
	public  String lastExchangPrice;
	public  String RiseAndFall;
	public  String transactionCount;
	public  String transactionSum;
	public  String maxPrice;
	public  String minPrice;
	public  List<BigDecimal[]> priceTrend;
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCurrentExchangPrice() {
		return currentExchangPrice;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCurrentExchangPrice(String currentExchangPrice) {
		this.currentExchangPrice = currentExchangPrice;
	}
	
	
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTransactionSum() {
		return transactionSum;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTransactionSum(String transactionSum) {
		this.transactionSum = transactionSum;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getLastExchangPrice() {
		return lastExchangPrice;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setLastExchangPrice(String lastExchangPrice) {
		this.lastExchangPrice = lastExchangPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRiseAndFall() {
		return RiseAndFall;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRiseAndFall(String riseAndFall) {
		RiseAndFall = riseAndFall;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTransactionCount() {
		return transactionCount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTransactionCount(String transactionCount) {
		this.transactionCount = transactionCount;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getMaxPrice() {
		return maxPrice;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getMinPrice() {
		return minPrice;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     List<BigDecimal[]>
	 */
	public List<BigDecimal[]> getPriceTrend() {
		return priceTrend;
	}
	/** 
	 * <p> TODO</p>
	 * @return: List<BigDecimal[]>
	 */
	public void setPriceTrend(List<BigDecimal[]> priceTrend) {
		this.priceTrend = priceTrend;
	}

	
	

	

}

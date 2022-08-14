/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月25日 下午4:09:31
 */
package hry.admin.trade.exEntrustOneDay.model;

import java.io.Serializable;
import java.math.BigDecimal;


public class ExEntrustSimple implements Serializable {
	 private static final long serialVersionUID = -4825890686624512635L;


	// 委托价格
	private BigDecimal entrustPrice;
	private BigDecimal surplusEntrustCount;
	

	public BigDecimal getEntrustPrice() {
		return entrustPrice;
	}
	public void setEntrustPrice(BigDecimal entrustPrice) {
		this.entrustPrice = entrustPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getSurplusEntrustCount() {
		return surplusEntrustCount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setSurplusEntrustCount(BigDecimal surplusEntrustCount) {
		this.surplusEntrustCount = surplusEntrustCount;
	}


	
}

/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月26日 上午11:18:00
 */
package hry.calculate.mvc.po;

import java.math.BigDecimal;

/**
 * @author Wu shuiming
 * @date 2016年8月26日 上午11:18:00
 */
public class PendingOrders {
	
	// 充值订单的未处理的总钱
	private BigDecimal sunMoney;
	
	// 虚拟币充值订单的未处理个数
	private Integer dmTransaxtionCount;
	

	public BigDecimal getSunMoney() {
		return sunMoney;
	}

	public void setSunMoney(BigDecimal sunMoney) {
		this.sunMoney = sunMoney;
	}

	public Integer getDmTransaxtionCount() {
		return dmTransaxtionCount;
	}

	public void setDmTransaxtionCount(Integer dmTransaxtionCount) {
		this.dmTransaxtionCount = dmTransaxtionCount;
	}

	@Override
	public String toString() {
		return "PendingOrders [sunMoney=" + sunMoney + ", dmTransaxtionCount="
				+ dmTransaxtionCount + "]";
	}
	

}

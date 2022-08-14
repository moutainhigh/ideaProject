/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月3日 上午9:54:27
 */
package hry.calculate.mvc.po;

import java.math.BigDecimal;

/**
 * 
 *    平台币种结算报表
 * 
 * @author Wu shuiming
 * @date 2016年9月3日 上午9:54:27
 */
public class TotalCurrencyForReport {

	private String coinCode; // 币种类型   (不受时间限制)
	
	private BigDecimal leverageCount; // 杠杆收费个数  (不受时间限制)
	
	private BigDecimal dmFee; // 充币提币收费个数
	
	private BigDecimal postCount; // 充币个数
	
	private BigDecimal getCount;  // 提币个数
	
	private BigDecimal totalCount; // 平台持有的个数  (不受时间限制)
	
	
	private BigDecimal sumOrderCount; // 平台总的交量
	private BigDecimal buyOrderCount; // 买的成交总量
	private BigDecimal sellOrderCount; // 卖的成交总量
	
	private BigDecimal availableCoinCount; // 可用币总量
	private BigDecimal frozenCoinCount; // 冻结币总量
	
	
	
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getAvailableCoinCount() {
		return availableCoinCount;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setAvailableCoinCount(BigDecimal availableCoinCount) {
		this.availableCoinCount = availableCoinCount;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getFrozenCoinCount() {
		return frozenCoinCount;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setFrozenCoinCount(BigDecimal frozenCoinCount) {
		this.frozenCoinCount = frozenCoinCount;
	}

	public BigDecimal getBuyOrderCount() {
		return buyOrderCount;
	}

	public void setBuyOrderCount(BigDecimal buyOrderCount) {
		this.buyOrderCount = buyOrderCount;
	}

	public BigDecimal getSellOrderCount() {
		return sellOrderCount;
	}

	public void setSellOrderCount(BigDecimal sellOrderCount) {
		this.sellOrderCount = sellOrderCount;
	}

	public BigDecimal getSumOrderCount() {
		return sumOrderCount;
	}

	public void setSumOrderCount(BigDecimal sumOrderCount) {
		this.sumOrderCount = sumOrderCount;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public BigDecimal getLeverageCount() {
		return leverageCount;
	}

	public void setLeverageCount(BigDecimal leverageCount) {
		this.leverageCount = leverageCount;
	}

	public BigDecimal getDmFee() {
		return dmFee;
	}

	public void setDmFee(BigDecimal dmFee) {
		this.dmFee = dmFee;
	}

	public BigDecimal getPostCount() {
		return postCount;
	}

	public void setPostCount(BigDecimal postCount) {
		this.postCount = postCount;
	}

	public BigDecimal getGetCount() {
		return getCount;
	}

	public void setGetCount(BigDecimal getCount) {
		this.getCount = getCount;
	}

	public BigDecimal getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(BigDecimal totalCount) {
		this.totalCount = totalCount;
	}
	

}

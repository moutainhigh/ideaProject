/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月23日 下午7:16:03
 */
package hry.calculate.mvc.po;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Wu shuiming
 * @date 2016年8月23日 下午7:16:03
 */
@SuppressWarnings("serial")
public class AppCalculateAllCustomer implements Serializable{
	
	private BigDecimal postTotalMoney;  // 总充值的钱数不分用户类型
	
	private BigDecimal getTotalMoney;  // 提现的总额度               
	
	private BigDecimal outTotalMoney;  // 出金总额  (去除手续费后  应出的所有的钱)
	
	private BigDecimal poundageMoney;  // 交易手续费
	
	private BigDecimal withdrawalMoney;  // 提现手续费总额
	
	private BigDecimal totalMoneyStart;   // 期初总资金(不分用户类型)
	
	private BigDecimal totalMoneyLast;   // 期末总资金(不分用户的类型)
	
	private BigDecimal intoTotalMoney;   // 转入总量(不分用户类型)

	private BigDecimal totalNumForCodeStart;  // 期初总数量(所用用户)
	
	private BigDecimal totalNumForCodeLast; // 期末(所有用户) 应该与上面的值相等
	
	private BigDecimal totalMoneyForTrading;  // 成交额(不分用户类型)
	
	private BigDecimal totalNumForTrading;  // 成交量(不分用户类型)

	public BigDecimal getPostTotalMoney() {
		return postTotalMoney;
	}

	public void setPostTotalMoney(BigDecimal postTotalMoney) {
		this.postTotalMoney = postTotalMoney;
	}

	public BigDecimal getGetTotalMoney() {
		return getTotalMoney;
	}

	public void setGetTotalMoney(BigDecimal getTotalMoney) {
		this.getTotalMoney = getTotalMoney;
	}

	public BigDecimal getOutTotalMoney() {
		return outTotalMoney;
	}

	public void setOutTotalMoney(BigDecimal outTotalMoney) {
		this.outTotalMoney = outTotalMoney;
	}

	public BigDecimal getPoundageMoney() {
		return poundageMoney;
	}

	public void setPoundageMoney(BigDecimal poundageMoney) {
		this.poundageMoney = poundageMoney;
	}

	public BigDecimal getWithdrawalMoney() {
		return withdrawalMoney;
	}

	public void setWithdrawalMoney(BigDecimal withdrawalMoney) {
		this.withdrawalMoney = withdrawalMoney;
	}

	public BigDecimal getTotalMoneyStart() {
		return totalMoneyStart;
	}

	public void setTotalMoneyStart(BigDecimal totalMoneyStart) {
		this.totalMoneyStart = totalMoneyStart;
	}

	public BigDecimal getTotalMoneyLast() {
		return totalMoneyLast;
	}

	public void setTotalMoneyLast(BigDecimal totalMoneyLast) {
		this.totalMoneyLast = totalMoneyLast;
	}

	public BigDecimal getIntoTotalMoney() {
		return intoTotalMoney;
	}

	public void setIntoTotalMoney(BigDecimal intoTotalMoney) {
		this.intoTotalMoney = intoTotalMoney;
	}

	public BigDecimal getTotalNumForCodeStart() {
		return totalNumForCodeStart;
	}

	public void setTotalNumForCodeStart(BigDecimal totalNumForCodeStart) {
		this.totalNumForCodeStart = totalNumForCodeStart;
	}

	public BigDecimal getTotalNumForCodeLast() {
		return totalNumForCodeLast;
	}

	public void setTotalNumForCodeLast(BigDecimal totalNumForCodeLast) {
		this.totalNumForCodeLast = totalNumForCodeLast;
	}

	public BigDecimal getTotalMoneyForTrading() {
		return totalMoneyForTrading;
	}

	public void setTotalMoneyForTrading(BigDecimal totalMoneyForTrading) {
		this.totalMoneyForTrading = totalMoneyForTrading;
	}

	public BigDecimal getTotalNumForTrading() {
		return totalNumForTrading;
	}

	public void setTotalNumForTrading(BigDecimal totalNumForTrading) {
		this.totalNumForTrading = totalNumForTrading;
	}

	@Override
	public String toString() {
		return "AppCalculateAllCustomer [postTotalMoney=" + postTotalMoney
				+ ", getTotalMoney=" + getTotalMoney + ", outTotalMoney="
				+ outTotalMoney + ", poundageMoney=" + poundageMoney
				+ ", withdrawalMoney=" + withdrawalMoney + ", totalMoneyStart="
				+ totalMoneyStart + ", totalMoneyLast=" + totalMoneyLast
				+ ", intoTotalMoney=" + intoTotalMoney
				+ ", totalNumForCodeStart=" + totalNumForCodeStart
				+ ", totalNumForCodeLast=" + totalNumForCodeLast
				+ ", totalMoneyForTrading=" + totalMoneyForTrading
				+ ", totalNumForTrading=" + totalNumForTrading + "]";
	}
	
	
	
	
	
	
	
	

}

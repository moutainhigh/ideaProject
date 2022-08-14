/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月23日 下午5:53:00
 */
package hry.calculate.mvc.po;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Wu shuiming
 * @date 2016年8月23日 下午5:53:00
 */
@SuppressWarnings("serial")
public class AppCalculate implements Serializable{

	public Integer newCustromers; // 新注册的用户
	
	public Integer activeCustromersFortransfer; // 活跃的用户   包括有充值提现等操作的用户                                                                                     
	
	public Integer activeCustromersForflatter; // 必须有成交记录的用户数
	
	public Integer custromerTotalNum; // 当闭市的时候会员的总数 
	
	public BigDecimal postTotalMoneyForCustromer; // 会员的总充值钱数(指定用户类型 --- 甲 乙 丙)
	
	public BigDecimal custromerTotalMoneyStart; // 当期初的时候会员的总钱数     
	
	public BigDecimal custromerTotalMoneyLast; //  当期末的时候会员的总钱数 (与期初的总钱数应该相等)
	
	public BigDecimal turnCodeCount; // 会员转入的总币数
	
	public BigDecimal custromerCodeCountStart; // 期初总持仓的数量
	
	public BigDecimal custromerCodeCountLast;  //  期末持仓的总数量 
	
	public BigDecimal custromerBuyForMoney;  // 会员买的总成交额度
	
	public BigDecimal custromerSellForMoney; // 会员卖的总成交额度
	
	public BigDecimal custromerBuyForNum;  //  会员买的总数量
	
	public BigDecimal custromerSellForNum;  // 会员卖的总数量

	public 	Integer getNewCustromers() {
		return newCustromers;
	}

	public void setNewCustromers(Integer newCustromers) {
		this.newCustromers = newCustromers;
	}

	public Integer getActiveCustromersFortransfer() {
		return activeCustromersFortransfer;
	}

	public void setActiveCustromersFortransfer(
			Integer activeCustromersFortransfer) {
		this.activeCustromersFortransfer = activeCustromersFortransfer;
	}

	public Integer getActiveCustromersForflatter() {
		return activeCustromersForflatter;
	}

	public void setActiveCustromersForflatter(Integer activeCustromersForflatter) {
		this.activeCustromersForflatter = activeCustromersForflatter;
	}

	public Integer getCustromerTotalNum() {
		return custromerTotalNum;
	}

	public void setCustromerTotalNum(Integer custromerTotalNum) {
		this.custromerTotalNum = custromerTotalNum;
	}

	public BigDecimal getPostTotalMoneyForCustromer() {
		return postTotalMoneyForCustromer;
	}

	public void setPostTotalMoneyForCustromer(BigDecimal postTotalMoneyForCustromer) {
		this.postTotalMoneyForCustromer = postTotalMoneyForCustromer;
	}

	public BigDecimal getCustromerTotalMoneyStart() {
		return custromerTotalMoneyStart;
	}

	public void setCustromerTotalMoneyStart(BigDecimal custromerTotalMoneyStart) {
		this.custromerTotalMoneyStart = custromerTotalMoneyStart;
	}

	public BigDecimal getCustromerTotalMoneyLast() {
		return custromerTotalMoneyLast;
	}

	public void setCustromerTotalMoneyLast(BigDecimal custromerTotalMoneyLast) {
		this.custromerTotalMoneyLast = custromerTotalMoneyLast;
	}

	public BigDecimal getTurnCodeCount() {
		return turnCodeCount;
	}

	public void setTurnCodeCount(BigDecimal turnCodeCount) {
		this.turnCodeCount = turnCodeCount;
	}

	public BigDecimal getCustromerCodeCountStart() {
		return custromerCodeCountStart;
	}

	public void setCustromerCodeCountStart(BigDecimal custromerCodeCountStart) {
		this.custromerCodeCountStart = custromerCodeCountStart;
	}

	public BigDecimal getCustromerCodeCountLast() {
		return custromerCodeCountLast;
	}

	public void setCustromerCodeCountLast(BigDecimal custromerCodeCountLast) {
		this.custromerCodeCountLast = custromerCodeCountLast;
	}

	public BigDecimal getCustromerBuyForMoney() {
		return custromerBuyForMoney;
	}

	public void setCustromerBuyForMoney(BigDecimal custromerBuyForMoney) {
		this.custromerBuyForMoney = custromerBuyForMoney;
	}

	public BigDecimal getCustromerSellForMoney() {
		return custromerSellForMoney;
	}

	public void setCustromerSellForMoney(BigDecimal custromerSellForMoney) {
		this.custromerSellForMoney = custromerSellForMoney;
	}

	public BigDecimal getCustromerBuyForNum() {
		return custromerBuyForNum;
	}

	public void setCustromerBuyForNum(BigDecimal custromerBuyForNum) {
		this.custromerBuyForNum = custromerBuyForNum;
	}

	public BigDecimal getCustromerSellForNum() {
		return custromerSellForNum;
	}

	public void setCustromerSellForNum(BigDecimal custromerSellForNum) {
		this.custromerSellForNum = custromerSellForNum;
	}

	@Override
	public String toString() {
		return "AppCalculate [newCustromers=" + newCustromers
				+ ", activeCustromersFortransfer="
				+ activeCustromersFortransfer + ", activeCustromersForflatter="
				+ activeCustromersForflatter + ", custromerTotalNum="
				+ custromerTotalNum + ", postTotalMoneyForCustromer="
				+ postTotalMoneyForCustromer + ", custromerTotalMoneyStart="
				+ custromerTotalMoneyStart + ", custromerTotalMoneyLast="
				+ custromerTotalMoneyLast + ", turnCodeCount=" + turnCodeCount
				+ ", custromerCodeCountStart=" + custromerCodeCountStart
				+ ", custromerCodeCountLast=" + custromerCodeCountLast
				+ ", custromerBuyForMoney=" + custromerBuyForMoney
				+ ", custromerSellForMoney=" + custromerSellForMoney
				+ ", custromerBuyForNum=" + custromerBuyForNum
				+ ", custromerSellForNum=" + custromerSellForNum + "]";
	}
	
	
	
	

}











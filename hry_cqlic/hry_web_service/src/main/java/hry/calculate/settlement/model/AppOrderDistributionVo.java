/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月13日 上午10:36:26
 */
package hry.calculate.settlement.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户的分布图 
 * 
 * @author Wu shuiming
 * @date 2016年9月13日 上午10:36:26
 */
@SuppressWarnings("serial")
public class AppOrderDistributionVo implements Serializable{

	private String coinName;  // 币的名称
	private String coinCode;  // 币的代码
	private BigDecimal coinCount;  // 单个币的成交量
	private BigDecimal coinCode1;  // 所有的币的成交总量
	
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public String getCoinCode() {
		return coinCode;
	}
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	public BigDecimal getCoinCount() {
		return coinCount;
	}
	public void setCoinCount(BigDecimal coinCount) {
		this.coinCount = coinCount;
	}
	public BigDecimal getCoinCode1() {
		return coinCode1;
	}
	public void setCoinCode1(BigDecimal coinCode1) {
		this.coinCode1 = coinCode1;
	}
	
	
	
	
}

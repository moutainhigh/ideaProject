/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年7月25日 上午11:04:11
 */
package hry.customer.agents.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Wu shuiming
 * @date 2016年7月25日 上午11:04:11
 */
@SuppressWarnings("serial")
public class CommissionForAgents implements Serializable{

	// app_trade_factorage 表中根据一级父查询出来的钱
	public BigDecimal f_amoney;
	
	// app_trade_factorage 表中根据二级父查询出来的钱
	public BigDecimal f_bmoney;
	
	// app_trade_factorage 表中根据三级父查询出来的钱
	public BigDecimal f_cmoney;
	
	// commission_detail 查询表中等于该用户的所有佣金
	// public BigDecimal d_jmoney;

	public BigDecimal getF_amoney() {
		return f_amoney;
	}

	public void setF_amoney(BigDecimal f_amoney) {
		this.f_amoney = f_amoney;
	}

	public BigDecimal getF_bmoney() {
		return f_bmoney;
	}

	public void setF_bmoney(BigDecimal f_bmoney) {
		this.f_bmoney = f_bmoney;
	}

	public BigDecimal getF_cmoney() {
		return f_cmoney;
	}

	public void setF_cmoney(BigDecimal f_cmoney) {
		this.f_cmoney = f_cmoney;
	}



	public CommissionForAgents(BigDecimal f_amoney, BigDecimal f_bmoney,
			BigDecimal f_cmoney) {
		super();
		this.f_amoney = f_amoney;
		this.f_bmoney = f_bmoney;
		this.f_cmoney = f_cmoney;
	}

	public CommissionForAgents() {
		super();
	}
	

	
	
	
}

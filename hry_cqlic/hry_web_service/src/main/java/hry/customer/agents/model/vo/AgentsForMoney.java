/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月3日 下午7:57:09
 */
package hry.customer.agents.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Wu shuiming
 * @date 2016年8月3日 下午7:57:09
 * 
 * 查询代理商的所收佣金的详细信息 封装对象
 * 
 */
/**
 * @author hurongyun
 *
 */
public class AgentsForMoney implements Serializable{
	
	public Long id;
	
	public String customerName;  // 代理商名称
	
	public String address;   // 代理地址
	
	public Date created;   // 成为代理的时间
	
	public BigDecimal oneMoney;  // 推荐的一级用户交的总佣金数
	
	public BigDecimal twoMoney;  // 推荐的二级用户交的总佣金数
	
	public BigDecimal threeMoney;  // 推荐的三级用户交的总佣金数
	
	public BigDecimal sumMoney;  // 总计或得的佣金数
	
	public BigDecimal deawalMoney; // 提现的佣金数 
	
	public BigDecimal surplusMoney; // 最后剩多少钱
	
	public Date modified; // 最后一次提现时间
	
	public String fixPriceCoinCode;

	public Integer fixPriceType;
	

	
	

	public Integer getFixPriceType() {
		return fixPriceType;
	}

	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}

	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}

	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public BigDecimal getOneMoney() {
		return oneMoney;
	}

	public void setOneMoney(BigDecimal oneMoney) {
		this.oneMoney = oneMoney;
	}

	public BigDecimal getTwoMoney() {
		return twoMoney;
	}

	public void setTwoMoney(BigDecimal twoMoney) {
		this.twoMoney = twoMoney;
	}

	public BigDecimal getThreeMoney() {
		return threeMoney;
	}

	public void setThreeMoney(BigDecimal threeMoney) {
		this.threeMoney = threeMoney;
	}
	
	public BigDecimal getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(BigDecimal sumMoney) {
		this.sumMoney = sumMoney;
	}

	
	public BigDecimal getDeawalMoney() {
		return deawalMoney;
	}

	public void setDeawalMoney(BigDecimal deawalMoney) {
		this.deawalMoney = deawalMoney;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

 
	public BigDecimal getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney = surplusMoney;
	}



	public AgentsForMoney(Long id, String customerName, String address,
			Date created, BigDecimal oneMoney, BigDecimal twoMoney,
			BigDecimal threeMoney, BigDecimal sumMoney, BigDecimal deawalMoney,
			BigDecimal surplusMoney, Date modified,String fixPriceCoinCode) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.address = address;
		this.created = created;
		this.oneMoney = oneMoney;
		this.twoMoney = twoMoney;
		this.threeMoney = threeMoney;
		this.sumMoney = sumMoney;
		this.deawalMoney = deawalMoney;
		this.surplusMoney = surplusMoney;
		this.modified = modified;
		this.fixPriceCoinCode=fixPriceCoinCode;
	}

	public AgentsForMoney() {
		super();
	}
	
	
	
}

/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月6日 上午11:51:14
 */
package hry.calculate.mvc.po;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Wu shuiming
 * @date 2016年9月6日 上午11:51:14
 */
@SuppressWarnings("serial")
public class TotalCustomerProfitForReport implements Serializable {

	private String userName;  // 用户名 
	private String trueName;  // 真实姓名 
	private BigDecimal beginMoney;  // 期初资金 
	private BigDecimal endMoney;  // 期末资金 
	private Integer customerType;  // 用户的类型 (1 2 3   甲乙丙 )
	private BigDecimal totalMoney;
	private String modified; // 日期  
	private BigDecimal beginNetAsset; // 期初资产(包括币的价值)
	private BigDecimal endNetAsset;  // 期末资产(包括币的价值)
	

	public BigDecimal getBeginNetAsset() {
		return beginNetAsset;
	}
	public void setBeginNetAsset(BigDecimal beginNetAsset) {
		this.beginNetAsset = beginNetAsset;
	}
	public BigDecimal getEndNetAsset() {
		return endNetAsset;
	}
	public void setEndNetAsset(BigDecimal endNetAsset) {
		this.endNetAsset = endNetAsset;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public BigDecimal getBeginMoney() {
		return beginMoney;
	}
	public void setBeginMoney(BigDecimal beginMoney) {
		this.beginMoney = beginMoney;
	}
	public BigDecimal getEndMoney() {
		return endMoney;
	}
	public void setEndMoney(BigDecimal endMoney) {
		this.endMoney = endMoney;
	}
	public Integer getCustomerType() {
		return customerType;
	}
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	
}

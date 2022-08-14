/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月1日 下午8:15:58
 */
package hry.calculate.mvc.po;

/**
 *  会员管理报表
 * 
 * @author Wu shuiming
 * @date 2016年9月1日 下午8:15:58
 */
public class TotalCustomerForReport {

	private Integer totalCustomer;  // 用户总数
	private Integer totalCustomerForType;  // 甲类用户总数
	private Integer totalCustomerForHot;  // 活跃用户个数
	public Integer getTotalCustomer() {
		return totalCustomer;
	}
	public void setTotalCustomer(Integer totalCustomer) {
		this.totalCustomer = totalCustomer;
	}
	public Integer getTotalCustomerForType() {
		return totalCustomerForType;
	}
	public void setTotalCustomerForType(Integer totalCustomerForType) {
		this.totalCustomerForType = totalCustomerForType;
	}
	public Integer getTotalCustomerForHot() {
		return totalCustomerForHot;
	}
	public void setTotalCustomerForHot(Integer totalCustomerForHot) {
		this.totalCustomerForHot = totalCustomerForHot;
	}
	
	
	
	
	
}

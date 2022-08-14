/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月23日 下午9:13:09
 */
package hry.calculate.mvc.service;

import hry.calculate.mvc.po.AppCalculate;
import hry.calculate.mvc.po.AppCalculateAllCustomer;
import hry.calculate.mvc.po.TotalCustomerForReport;

/**
 * @author Wu shuiming
 * @date 2016年8月23日 下午9:13:09
 */
public interface AppCalculateService {
	
	
	/**
	 * 查询不分用户的所用的数据
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月23日 下午9:16:58
	 */
	public AppCalculateAllCustomer findCalculateAll(String beginDate, String endDate);
	
	/**
	 * 
	 * 查询所有的信息  分用户
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月23日 下午9:17:51
	 */
	public AppCalculate findCalculate(String beginDate, String endDate, Integer type, String coinCode);
	
	/**
	 * 
	 * 查询平台会员管理报表 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年9月5日 下午2:54:02
	 */
	public TotalCustomerForReport findTotalCustomerForReport();
	
	
	
	
	
	

}

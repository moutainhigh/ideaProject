/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午4:20:26
 */
package hry.customer.user.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.model.AppCustomerSimple;
import hry.manage.remote.model.RemoteResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月24日 下午4:20:26 
 */
public interface AppCustomerService  extends BaseService<AppCustomer, Long>{

	/**
	 * <p>自定义分页方法</p>
	 * @author:         Liu Shilei
	 * @param:    
	 * @return: PageResult 
	 * @Date :          2016年4月21日 下午2:29:31   
	 * @throws:
	 */
	PageResult findPageBySql(QueryFilter filter);

	/**
	 * <p>查询用户简单对象</p>
	 * @author:         Liu Shilei
	 * @param:    @param request
	 * @param:    @return
	 * @return: List<AppCustomerSimple> 
	 * @Date :          2016年5月31日 下午6:26:15   
	 * @throws:
	 */
	List<AppCustomerSimple> findAppCustomerSimple(HttpServletRequest request);
	
	public JsonResult storpCustomer(Long id, boolean type);

	public JsonResult lockCustomer(Date time, Long id, boolean type);
	
	/**
	 * 
	 * <p>根据id查询用户信息</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @param map
	 * @param:    @return
	 * @return: List<AppCustomerSimple> 
	 * @Date :          2016年8月30日 下午8:02:11   
	 * @throws:
	 */
	List<AppCustomer> findById(Long id);
	
	/**
	 * 
	 * 查询已经实名的用户
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: List<AppCustomer> 
	 * @Date :          2016年9月28日 下午4:13:53   
	 * @throws:
	 */
	List<AppCustomer> getRealNameCustomer();
	/**
	 * 查询有资金变化的客户
	 * <p> TODO</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: List<AppCustomer> 
	 * @Date :          2016年9月28日 下午4:25:11   
	 * @throws:
	 */
	public List<AppCustomer> getFundChangeCustomers(Map<String, Object> map) ;

	/**
	 * 获取所有实名的人数量
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @return
	 * @return: int 
	 * @Date :          2017年3月14日 上午11:09:40   
	 * @throws:
	 */
	int getHasAuthNum();

	AppCustomer getByCustomerId(String username);

	AppCustomer getByPhone(String mobile);

	int commendCount(String userName);

	PageResult findPageBySqlList(QueryFilter filter);

    void giveCoin(Long id);

	RemoteResult getAuditpassword();

    RemoteResult setAuditpassword(Map<String, String> map);
}

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午12:18:00
 */
package hry.customer.remote;

import java.util.List;
import java.util.Map;

import hry.bean.JsonResult;
import hry.util.RemoteQueryFilter;
import hry.customer.agents.model.CustomerAsAgents;
import hry.customer.user.model.AppCustomer;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月28日 下午12:18:00 
 */
public interface RemoteAppCustomerService {
	
	
	public List<AppCustomer>  find(RemoteQueryFilter filter);
	
	/**
	 * 根据用户名查找方法
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param userName
	 * @param:    @param passWord
	 * @param:    @return
	 * @return: AppCustomer 
	 * @Date :          2016年3月28日 下午12:21:57   
	 * @throws:
	 */
	public AppCustomer login(String userName, String saasId);
	
	
	/**
	 * 注册前台客户
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param appCustomer
	 * @param:    @return JsonResult
	 * @return: boolean 
	 * @Date :          2016年3月28日 下午6:41:10   
	 * @throws:
	 */
	public JsonResult regist(AppCustomer appCustomer);
	
	
	/**
	 * 
	 * 注册用户并保存一个中间表(customerAsagentd)
	 * 
	 * @param appCustomer
	 * @return
	 */
	public JsonResult regist(AppCustomer appCustomer, CustomerAsAgents customerAsAgents);


	/**
	 * <p>更新AppCustomer</p>
	 * @author:         Liu Shilei
	 * @param:    @param appCustomer
	 * @return: void 
	 * @Date :          2016年3月30日 下午4:57:00   
	 * @throws:
	 */
	public void update(AppCustomer appCustomer);


	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param id
	 * @return: void 
	 * @Date :          2016年3月30日 下午6:24:18   
	 * @throws:
	 */
	public AppCustomer getById(Long id);


	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param remoteQueryFilter
	 * @param:    @return
	 * @return: AppCustomer 
	 * @Date :          2016年5月24日 下午7:25:02   
	 * @throws:
	 */
	public AppCustomer getByQueryFilter(RemoteQueryFilter remoteQueryFilter);
	
	
	/**
	 * 查询已经实名的用户
	 * @return
	 */
	public List<AppCustomer>  getRealNameCustomer();
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
	 * 获取已实名的客户数量
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @return
	 * @return: int 
	 * @Date :          2017年3月14日 上午11:04:29   
	 * @throws:
	 */
	public int getHasAuthNum();

	public AppCustomer getAppCustomerByCode(String referralCode);
}

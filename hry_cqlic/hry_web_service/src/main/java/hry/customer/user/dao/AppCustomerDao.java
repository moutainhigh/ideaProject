/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午3:16:42
 */
package hry.customer.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.core.mvc.dao.base.BaseDao;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.model.AppCustomerSimple;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月24日 下午3:16:42 
 */
public interface AppCustomerDao extends BaseDao<AppCustomer, Long>{

	/**
	 * <p>通过sql分页查询</p>
	 * @author:         Liu Shilei
	 * @param:    @param string
	 * @param:    @param string2
	 * @param:    @param i
	 * @return: void 
	 * @Date :          2016年4月21日 下午2:43:17   
	 * @throws:
	 */
	List<AppCustomer> findPageBySql(Map<String, Object> map);
	
	
	/**
	 * 查询简单对象   id  和用户姓名
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @return
	 * @return: List<AppCustomerSimple> 
	 * @Date :          2016年5月31日 下午6:28:09   
	 * @throws:
	 */
	List<AppCustomerSimple> findAppCustomerSimple(Map<String, Object> map);

	
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
	List<AppCustomer> findById(Map<String, Object> map);
	
	
	/**
	 * 查询实名认证的用户
	 * <p> TODO</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: List<AppCustomer> 
	 * @Date :          2016年9月28日 下午4:25:11   
	 * @throws:
	 */
	public List<AppCustomer> getRealNameCustomer() ;
	
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
	/**
	 * 连表查了apppersoninfo
	 * @param map
	 * @return
	 */
	 AppCustomer getAppCustomer(@Param(value = "userName") String userName);
	 /**
	 * 列只有id，username
	 * @param map
	 * @return
	 */
	 AppCustomer getAppCustomerSingleByUserName(@Param(value = "userName") String userName);
	 /**
	 * 列只有id，username
	 * @param map
	 * @return
	 */
	 AppCustomer getAppCustomerSingleByUsercode(@Param(value = "usercode") String usercode);


	List<AppCustomer> getByCustomerId(Map<String, Object> map);


	List<AppCustomer> getByPhone(Map<String, Object> map);


	int commendCount(String userName);
	
	/**
	 * 新sql分页查询
	 * @param map
	 * @return
	 */
	List<AppCustomer> findPageBySqlList(Map<String, Object> map);
	
	/**
	 * 根据条件查询客户
	 * @param map
	 * @return
	 */
	List<String> findCustomerByCondition(Map<String, Object> map);
	
	/**
	 * 查询分页总数
	 * @param map
	 * @return
	 */
	Long findPageBySqlCount(Map<String, Object> map);

	void updateGag();
	 
}

package hry.manage.remote;

import hry.manage.remote.model.AppHolidayConfig;

import java.util.List;



public interface RemoteBaseInfoService {

	/**
	 * 查询用户收藏的交易对
	 * @param customerId
	 * @return
	 */
	List<String> findCustomerCollection(Long customerId);

	/**
	 * 添加用户收藏的交易对
	 * @param customerId
	 * @param coinCode
	 * @return
	 */
	boolean addCustomerCollection(Long customerId, String coinCode);

	/**
	 * 删除用户收藏的交易对
	 * @param customerId
	 * @param coinCode
	 * @return
	 */
	boolean deleteCustomerCollection(Long customerId, String coinCode);
































































	// ====================================分割线==========================================================

	/**
	 * 查询节假日list
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @return
	 * @return: List<AppHolidayConfig>
	 * @Date :          2017年7月13日 下午6:18:50
	 * @throws:
	 */
	public List<AppHolidayConfig> listAppHolidayConfig();

	/**
	 * 查询系统配置根据key
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param key
	 * @param:    @return
	 * @return: String
	 * @Date :          2017年7月13日 下午6:36:44
	 * @throws:
	 */
	public String  getFinanceByKey(String key);

}

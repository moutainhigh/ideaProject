/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:50:05
 */
package hry.account.fund.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.account.fund.model.AppBankCard;
import hry.core.mvc.dao.base.BaseDao;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:50:05 
 */
public interface AppBankCardDao extends BaseDao<AppBankCard,Long>{

	List<AppBankCard> findPageBySql(Map<String, Object> map);
	
	/**
	 * 新sql查询方法
	 * @param map
	 * @return
	 */
	List<AppBankCard> findPageBySqlList(Map<String, Object> map);
	
	/**
	 * 根据条件查询客户信息
	 * @param map
	 * @return
	 */
	List<String> findCustomerByCondition(Map<String, Object> map);
	
	/**
	 * 查询总条数
	 * @param map
	 * @return
	 */
	Long findPageBySqlCount(Map<String, Object> map);

	int findSaveFlag(Map<String, Object> map);
	
	List<AppBankCard> findPageBySql1(Map<String, String> map);
	
	void updateIsDefault(Map<String, String> map);
	
	List<AppBankCard> getPersonalAsset(Map<String, Object> map);

    void updateIsDeleteById (Long id);
}

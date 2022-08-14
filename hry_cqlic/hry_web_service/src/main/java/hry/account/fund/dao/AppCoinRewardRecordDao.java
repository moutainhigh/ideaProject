package hry.account.fund.dao;

import hry.account.fund.model.AppCoinRewardRecord;
import hry.core.mvc.dao.base.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年3月9日 下午1:56:09
 */
public interface AppCoinRewardRecordDao extends BaseDao<AppCoinRewardRecord , Long> {

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
	List<AppCoinRewardRecord> findPageBySql(Map<String, Object> map);

}

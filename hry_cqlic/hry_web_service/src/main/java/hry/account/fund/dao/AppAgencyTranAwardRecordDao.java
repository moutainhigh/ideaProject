package hry.account.fund.dao;

import hry.account.fund.model.AppAgencyTranAwardRecord;
import hry.core.mvc.dao.base.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年3月9日 下午1:56:09
 */
public interface AppAgencyTranAwardRecordDao extends BaseDao<AppAgencyTranAwardRecord , Long> {

	/**
	 * 通过sql分页查询
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param map
	 * @param:    @return
	 * @return: List<AppAgencyTranAwardRecord> 
	 * @Date :          2017年3月28日 上午10:46:25   
	 * @throws:
	 */
	List<AppAgencyTranAwardRecord>  findPageBySql(Map<String, Object> map);

}

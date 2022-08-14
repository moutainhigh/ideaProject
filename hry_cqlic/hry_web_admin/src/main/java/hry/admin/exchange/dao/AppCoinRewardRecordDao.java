/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-02 11:44:17 
 */
package hry.admin.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.exchange.model.AppCoinRewardRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> AppCoinRewardRecordDao </p>
 * @author:         tianpengyu
 * @Date :          2018-07-02 11:44:17  
 */
public interface AppCoinRewardRecordDao extends  BaseDao<AppCoinRewardRecord, Long> {
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
    List<AppCoinRewardRecord> findPageBySql(Map<String,Object> map);

}

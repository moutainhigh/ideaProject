/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:03:31 
 */
package hry.cm4.miner.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm4.miner.model.Cm4Miner;
import org.apache.ibatis.annotations.Param;


/**
 * <p> Cm4MinerDao </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:03:31  
 */
public interface Cm4MinerDao extends  BaseDao<Cm4Miner, Long> {
    /**
     * 更新矿机剩余数量
     * @param id 矿机Id
     * @param orderNum 变动数量
     */
    void updateMinerSurplusNum(@Param("id")Long id, @Param("orderNum")int orderNum);
}

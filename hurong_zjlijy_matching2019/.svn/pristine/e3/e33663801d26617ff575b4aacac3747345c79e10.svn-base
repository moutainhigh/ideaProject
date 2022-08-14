/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 15:56:47 
 */
package hry.cm2.miner.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm2.miner.model.Cm2Miner;
import org.apache.ibatis.annotations.Param;


/**
 * <p> Cm2MinerDao </p>
 * @author:         yaozh
 * @Date :          2019-10-14 15:56:47  
 */
public interface Cm2MinerDao extends  BaseDao<Cm2Miner, Long> {

    /**
     * 更新矿机剩余数量
     * @param id 矿机Id
     * @param orderNum 变动数量
     */
    void updateMinerSurplusNum(@Param("id")Long id, @Param("orderNum")int orderNum);

}

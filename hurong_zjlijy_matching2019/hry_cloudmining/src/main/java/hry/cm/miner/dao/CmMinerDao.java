/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-26 17:32:36 
 */
package hry.cm.miner.dao;

import org.apache.ibatis.annotations.Param;

import hry.cm.miner.model.CmMiner;
import hry.core.mvc.dao.base.BaseDao;


/**
 * <p> CmMinerDao </p>
 * @author:         yaozh
 * @Date :          2019-07-26 17:32:36  
 */
public interface CmMinerDao extends  BaseDao<CmMiner, Long> {
	
	/**
	 * 更新矿机剩余数量
	 * @param id 矿机Id 
	 * @param orderNum 变动数量
	 */
	void updateMinerSurplusNum(@Param("id")Long id,@Param("orderNum")int orderNum);

}

/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-26 17:32:36 
 */
package hry.cm.miner.service;

import hry.core.mvc.service.base.BaseService;

import org.apache.ibatis.annotations.Param;

import hry.cm.miner.model.CmMiner;



/**
 * <p> CmMinerService </p>
 * @author:         yaozh
 * @Date :          2019-07-26 17:32:36 
 */
public interface CmMinerService  extends BaseService<CmMiner, Long>{
	
	/**
	 * 更新矿机剩余数量
	 * @param id 矿机Id 
	 * @param orderNum 变动数量
	 */
	void updateMinerSurplusNum(Long id,int orderNum);


}

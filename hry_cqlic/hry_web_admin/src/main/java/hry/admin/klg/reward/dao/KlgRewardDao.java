/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-24 18:13:04 
 */
package hry.admin.klg.reward.dao;

import java.util.List;
import java.util.Map;

import hry.admin.klg.reward.model.KlgReward;
import hry.admin.klg.reward.model.vo.KlgRewardVo;
import hry.core.mvc.dao.base.BaseDao;


/**
 * <p> KlgRewardDao </p>
 * @author:         yaozhuo
 * @Date :          2019-05-24 18:13:04  
 */
public interface KlgRewardDao extends  BaseDao<KlgReward, Long> {
	
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
    List<KlgRewardVo> findPageBySql(Map<String,Object> map);

}

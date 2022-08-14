/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-05-24 18:13:04 
 */
package hry.admin.klg.reward.service;

import hry.admin.klg.reward.model.KlgReward;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;



/**
 * <p> KlgRewardService </p>
 * @author:         yaozhuo
 * @Date :          2019-05-24 18:13:04 
 */
public interface KlgRewardService  extends BaseService<KlgReward, Long>{
	
	/**
     * 新sql分页查询
     * @param map
     * @return
     */
	PageResult findPageBySql(QueryFilter filter);


}

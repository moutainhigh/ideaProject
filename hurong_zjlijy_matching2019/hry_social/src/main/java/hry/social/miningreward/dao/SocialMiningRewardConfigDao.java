/**
 * Copyright:    
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-10 10:50:48 
 */
package hry.social.miningreward.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.miningreward.SocialMiningRewardConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> SocialMiningRewardConfigDao </p>
 * @author:         javalx
 * @Date :          2019-06-10 10:50:48  
 */
public interface SocialMiningRewardConfigDao extends  BaseDao<SocialMiningRewardConfig, Long> {


    SocialMiningRewardConfig getCoinConfig(String coinCode);

    List<String> findMiningCode(@Param("rewardType") Integer rewardType);

}

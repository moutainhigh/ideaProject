/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-04 10:07:52 
 */
package hry.social.friend.service;

import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.friend.SocialFriendReward;
import hry.util.QueryFilter;

import java.math.BigDecimal;


/**
 * <p> SocialFriendRewardService </p>
 * @author:         javalx
 * @Date :          2019-06-04 10:07:52 
 */
public interface SocialFriendRewardService  extends BaseService<SocialFriendReward, Long>{


    SocialFriendReward footing(QueryFilter filter);

    /**
     * 查询总打赏值
     *
     * @param customerId
     * @return
     */
    BigDecimal friendReward(Long customerId);

}

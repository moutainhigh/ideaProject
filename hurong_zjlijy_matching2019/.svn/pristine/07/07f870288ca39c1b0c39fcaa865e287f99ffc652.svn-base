/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-04 10:07:52 
 */
package hry.social.friend.service.impl;

import javax.annotation.Resource;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.friend.dao.SocialFriendRewardDao;
import hry.social.friend.service.SocialFriendRewardService;
import hry.social.manage.remote.model.friend.SocialFriendReward;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p> SocialFriendRewardService </p>
 * @author:         javalx
 * @Date :          2019-06-04 10:07:52  
 */
@Service("socialFriendRewardService")
public class SocialFriendRewardServiceImpl extends BaseServiceImpl<SocialFriendReward, Long> implements SocialFriendRewardService {
	
	@Resource(name="socialFriendRewardDao")
	@Override
	public void setDao(BaseDao<SocialFriendReward, Long> dao) {
		super.dao = dao;
	}

	@Override
	public SocialFriendReward footing(QueryFilter filter) {
		SocialFriendReward str = null;
		str = ((SocialFriendRewardDao) dao).footing();
		return str;
	}

	/**
	 * 查询总打赏值
	 *
	 * @param customerId
	 * @return
	 */
	@Override
	public BigDecimal friendReward(Long customerId) {
		return ((SocialFriendRewardDao) dao).friendReward(customerId);
	}
}

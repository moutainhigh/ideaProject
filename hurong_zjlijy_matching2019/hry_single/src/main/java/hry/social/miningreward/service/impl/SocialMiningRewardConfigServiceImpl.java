/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 10:50:48
 */
package hry.social.miningreward.service.impl;


import javax.annotation.Resource;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.model.social.miningreward.SocialMiningRewardConfig;
import hry.social.miningreward.service.SocialMiningRewardConfigService;
import org.springframework.stereotype.Service;


/**
 * <p> SocialMiningRewardConfigService </p>
 *
 * @author: javalx
 * @Date :          2019-06-10 10:50:48
 */
@Service("socialMiningRewardConfigService")
public class SocialMiningRewardConfigServiceImpl extends BaseServiceImpl<SocialMiningRewardConfig,Long> implements SocialMiningRewardConfigService {

    @Resource(name = "socialMiningRewardConfigDao")
    @Override
    public void setDao(BaseDao<SocialMiningRewardConfig,Long> dao) {
        super.dao = dao;
    }

}

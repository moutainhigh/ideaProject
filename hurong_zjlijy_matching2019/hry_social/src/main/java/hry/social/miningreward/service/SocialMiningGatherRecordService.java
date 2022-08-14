/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-10 11:13:59 
 */
package hry.social.miningreward.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.miningreward.SocialMiningGatherRecord;
import hry.util.QueryFilter;


/**
 * <p> SocialMiningGatherRecordService </p>
 * @author:         javalx
 * @Date :          2019-06-10 11:13:59 
 */
public interface SocialMiningGatherRecordService  extends BaseService<SocialMiningGatherRecord, Long>{


    PageResult findPageList(QueryFilter filter);

    SocialMiningGatherRecord footing(QueryFilter filter);

    /**
     * 查询收取记录
     *
     * @param customerId 收取用户ID
     * @param pickId     果园奖励ID
     * @return
     */
    int hasPick(Long customerId, Long pickId);
}

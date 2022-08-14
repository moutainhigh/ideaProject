/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 11:05:17
 */
package hry.social.miningreward.service;

import hry.bean.FrontPage;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.manage.remote.model.RemoteResult;
import hry.social.manage.remote.model.miningreward.SocialMiningRewardRecord;
import hry.social.manage.remote.model.miningreward.SocialPickData;
import hry.util.QueryFilter;

import java.util.List;
import java.util.Map;

/**
 * <p> SocialMiningRewardRecordService </p>
 *
 * @author: javalx
 * @Date :          2019-06-10 11:05:17
 */
public interface SocialMiningRewardRecordService extends BaseService<SocialMiningRewardRecord,Long> {

    PageResult findPageList(QueryFilter filter);

    SocialMiningRewardRecord footing(QueryFilter filter);

    /**
     * 查询好友的果子可采摘信息
     *
     * @param customerId 用户ID
     * @param gatheredId 好友ID
     * @return
     */
    List<SocialPickData> getPalPicked(Long customerId, Long gatheredId);

    /**
     * 采摘果子
     *
     * @param customerId 收取用户ID
     * @param gatheredId 被收取用户ID
     * @param id         被收取奖励ID
     * @return
     */
    RemoteResult gather(Long customerId, Long gatheredId, Long id);

    /**
     * 查询收取好友信息
     *
     * @param customerId
     * @param friendId
     * @return
     */
    SocialMiningRewardRecord getGatherInfo(Long customerId, Long friendId);

    /**
     * 好友动态分页查询
     *
     * @param params
     * @return
     */
    FrontPage findPalGatherReward(Map<String,String> params);

    /**
     * 首页动态分页查询
     *
     * @param params
     * @return
     */

    FrontPage miningRewardRecord(Map<String,String> params);

    /**
     * 查询果园配置币种
     *
     * @return
     */
    List<String> findMiningCode(Integer rewardType);
}

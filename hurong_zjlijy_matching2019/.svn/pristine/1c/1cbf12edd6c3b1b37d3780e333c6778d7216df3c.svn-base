/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 10:50:48
 */
package hry.social.manage.remote.api.miningreward;

import hry.bean.FrontPage;
import hry.manage.remote.model.CoinAccount;
import hry.manage.remote.model.RemoteResult;
import hry.social.manage.remote.model.miningreward.SocialPickData;

import java.util.List;
import java.util.Map;

/**
 * <p> SocialMiningRewardConfigService </p>
 *
 * @author: javalx
 * @Date :          2019-06-10 10:50:48
 */
public interface RemoteMiningRewardService {

    /**
     * 查询当前用户果园账户
     *
     * @param customerId
     * @return
     */
    List<CoinAccount> getMiningRewardAccount(Long customerId);

    /**
     * 查询登录用户的果子可采摘信息
     *
     * @param customerId
     * @return
     */
    List<SocialPickData> getMyPicked(Long customerId);

    /**
     * 查询好友的果子可采摘信息
     *
     * @param customerId
     * @param gatheredId
     * @return
     */
    List<SocialPickData> getPalPicked(Long customerId, Long gatheredId) throws Exception;

    /**
     * 采摘果子
     *
     * @param customerId
     * @param gatheredId
     * @param id
     * @return
     */
    RemoteResult gather(Long customerId, Long gatheredId, Long id);

    /**
     * 更新奖励状态
     *
     * @param id
     */
    void updateStates(Long id);

    /**
     * 采摘PK
     *
     * @param friendId
     * @param customerId
     * @return
     */
    Map<String,Object> gatherVs(Long friendId, Long customerId);

    /**
     * 查看好友收取动态
     *
     * @param params
     * @return
     */
    FrontPage findPalGatherReward(Map<String,String> params);

    /**
     * 查询动态（首页）
     *
     * @param params
     * @return
     */
    FrontPage miningRewardRecord(Map<String,String> params);

    /**
     * 查询数币交易流水（首页）
     *
     * @param params
     * @return
     */
    FrontPage coinTraderList(Map<String,String> params);

    /**
     * 查询果园配置币种
     *
     * @return
     */
    List<String> findMiningCode(Integer rewardType);
}

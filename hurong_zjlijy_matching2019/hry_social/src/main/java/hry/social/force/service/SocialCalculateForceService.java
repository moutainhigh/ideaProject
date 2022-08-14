/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 14:44:12
 */
package hry.social.force.service;

import hry.core.mvc.service.base.BaseService;
import hry.model.social.miningreward.SocialPickRedis;
import hry.social.manage.remote.model.force.SocialCalculateForce;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> SocialCalculateForceService </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 14:44:12
 */
public interface SocialCalculateForceService extends BaseService<SocialCalculateForce,Long> {

    /**
     * 查询满足条件的算力用户
     *
     * @param rewardCondition
     * @return
     */
    List<SocialCalculateForce> getCondePerson(BigDecimal rewardCondition);

    /**
     * 查询全网总算力
     *
     * @return
     */
    BigDecimal getAllTotal();

    void updateStates(Long id);

    /**
     * 查询用户实时算力
     *
     * @param customerId
     * @return
     */
    BigDecimal getRealtimeForce(Long customerId);

    /**
     * 计算算力排名
     *
     * @param force
     * @param customerId
     * @return
     */
    int getRankNum(BigDecimal force, Long customerId);

    /**
     * 查询榜首算力
     *
     * @return
     */
    BigDecimal getFirstForce();

    /**
     * 查询任务算力
     *
     * @param customerId
     * @return
     */
    BigDecimal findTaskFroce(Long customerId);

    /**
     * 查询矿机算力
     *
     * @param customerId
     * @return
     */
    BigDecimal findMillFroce(Long customerId);

    /**
     * 查询全网排名前100的用户
     *
     * @return
     */
    List<SocialCalculateForce> getTop();

    /**
     * 初始化算力账户
     *
     * @return
     */
    void initForce(Long customerId);

    /**
     * 查询币账户排名
     *
     * @param coinCode
     * @param hotMoney
     * @return
     */
    Integer getAccountRankNum(String coinCode, BigDecimal hotMoney, Long customerId);
}

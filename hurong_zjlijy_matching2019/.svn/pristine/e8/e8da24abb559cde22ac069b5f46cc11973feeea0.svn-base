/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 14:44:12
 */
package hry.social.force.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.model.social.miningreward.SocialPickRedis;
import hry.social.manage.remote.model.force.SocialCalculateForce;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> SocialCalculateForceDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 14:44:12
 */
public interface SocialCalculateForceDao extends BaseDao<SocialCalculateForce,Long> {


    /**
     * 查询满足条件的算力用户
     *
     * @param rewardCondition
     * @return
     */
    List<SocialCalculateForce> getCondePerson(@Param("rewardCondition") BigDecimal rewardCondition);

    BigDecimal getAllTotal();

    List<SocialPickRedis> findPicked(@Param("customerId") Long customerId, @Param("limt") int limt);

    void updateStates(@Param("id") Long id);

    /**
     * 查询算力超过当前用户的人数
     *
     * @param force
     * @return
     */
    int findGtForce(@Param("totalForce") BigDecimal force);

    /**
     * 查询算力相等且customerId小于当前用户的人数
     *
     * @param force
     * @param customerId
     * @return
     */
    int findEqForce(@Param("totalForce") BigDecimal force, @Param("customerId") Long customerId);

    /**
     * 查询榜首算力
     *
     * @return
     */
    BigDecimal getFirstForce();

    /**
     * 查询时效性任务算力
     *
     * @param customerId
     * @return
     */
    BigDecimal taskFroceTotal(@Param("customerId") Long customerId);

    /**
     * 查询永久性任务算力
     *
     * @param customerId
     * @return
     */
    BigDecimal perpetualTotal(@Param("customerId") Long customerId);

    /**
     * 查询时效性矿机算力
     *
     * @param customerId
     * @return
     */
    BigDecimal findMillFroce(@Param("customerId") Long customerId);

    /**
     * 算力排名Top100查询
     *
     * @return
     */
    List<SocialCalculateForce> getTop();

    /**
     * 个人总算里查询
     *
     * @return
     */
    SocialCalculateForce getForceByCustomerId(@Param("customerId") Long customerId);

    /**
     * 查询有效的失效性算力
     *
     * @param customerId
     * @return
     */
    BigDecimal terminableForce(@Param("customerId") Long customerId);

    /**
     * 查询有效的加成算力
     *
     * @param customerId
     * @return
     */
    BigDecimal additionTotal(@Param("customerId") Long customerId);

    /**
     * 更新算力榜信息
     *
     * @param customerId
     * @param perpetualForce
     * @param terminableForce
     * @param additionForce
     * @param totalForce
     */
    void updateForce(@Param("customerId") Long customerId, @Param("perpetualForce") BigDecimal perpetualForce, @Param("terminableForce") BigDecimal terminableForce, @Param("additionForce") BigDecimal additionForce, @Param("totalForce") BigDecimal totalForce);

    /**
     * 查询可用余额超过当前用户的人数
     *
     * @param coinCode
     * @param hotMoney
     * @return
     */
    Integer getAccountGtRankNum(@Param("coinCode") String coinCode, @Param("hotMoney") BigDecimal hotMoney);

    /**
     * 查询可用余额相等且customerId小于当前用户的人数
     *
     * @param coinCode
     * @param hotMoney
     * @param customerId
     * @return
     */
    Integer getAccountEqRankNum(@Param("coinCode") String coinCode, @Param("hotMoney") BigDecimal hotMoney, @Param("customerId") Long customerId);

}

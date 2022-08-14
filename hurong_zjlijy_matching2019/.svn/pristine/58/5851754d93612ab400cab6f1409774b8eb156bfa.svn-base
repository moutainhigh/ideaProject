/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 17:55:29
 */
package hry.social.task.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.task.SocialTaskReward;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> SocialTaskRewardDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 17:55:29
 */
public interface SocialTaskRewardDao extends BaseDao<SocialTaskReward,Long> {

    List<SocialTaskReward> findPageList(Map<String,Object> map);

    SocialTaskReward footing(Map<String,Object> map);

    /**
     * 查询任务是否超过总奖励上限
     *
     * @param customerId
     * @param taskMark
     * @param numberCaps
     * @return
     */
    int getHasMax(@Param("customerId") Long customerId, @Param("taskMark") String taskMark, @Param("numberCaps") Integer numberCaps);

    /**
     * 查询一次性任务是否完成
     *
     * @param customerId
     * @param taskMark
     * @return
     */
    int hashOnce(@Param("customerId") Long customerId, @Param("taskMark") String taskMark);

    /**
     *
     * @param customerId
     * @param taskMark
     * @param dailyUpper
     * @return
     */
    int hashNums(@Param("customerId") Long customerId, @Param("taskMark") String taskMark, @Param("dailyUpper")  int dailyUpper);

    /**
     * 一次性任务是否完成
     *
     * @param customerId
     * @param taskMark
     * @return
     */
    int hasFinish(@Param("customerId") Long customerId, @Param("taskMark") String taskMark);

    /**
     * 每日任务当天是否完成过一次
     *
     * @param customerId
     * @param taskMark
     * @return
     */
    int hasOnseFinish(@Param("customerId") Long customerId, @Param("taskMark") String taskMark);
}

/**
 * Copyright:
 * @author:      lzy
 * @version:     V1.0
 * @Date:        2019-04-17 14:01:32
 */
package hry.klg.level.dao;

import hry.core.mvc.dao.base.BaseDao;

import hry.klg.level.model.KlgCustomerLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p> KlgCustomerLevelDao </p>
 * @author:         lzy
 * @Date :          2019-04-17 14:01:32
 */
public interface KlgCustomerLevelDao extends  BaseDao<KlgCustomerLevel, Long> {

    public int customerRewardAdd(KlgCustomerLevel customerLevel);

    //定时重置用户奖励额度
    List<Long> resetRewardQuota(@Param("day") String day);
    //定时重置用户奖励额度
    int  updateResetRewardQuota(@Param("day") String day);

    //用户星级用户统计
    List<KlgCustomerLevel> sumStarCount(@Param("customerId") Long customerId, @Param("level")Integer level);
}

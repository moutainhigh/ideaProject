/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 18:28:34
 */
package hry.social.mill.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.mill.SocialMillTradeRecord;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> SocialMillTradeRecordDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 18:28:34
 */
public interface SocialMillTradeRecordDao extends BaseDao<SocialMillTradeRecord,Long> {

    List<SocialMillTradeRecord> findPageList(Map<String,Object> map);

    int hasForceMill(@Param("customerId") Long customerId);

    int hasCoinMill(@Param("customerId") Long customerId);

    BigDecimal findMillCoin(@Param("customerId") Long customerId);

    /**
     * 查询用户矿机购买记录
     *
     * @param customerId
     * @param rewardType
     * @return
     */
    List<SocialMillTradeRecord> getUerMillList(@Param("customerId") Long customerId, @Param("rewardType") int rewardType);

    /**
     *
     * @param coinCode
     * @return
     */
    BigDecimal getCoinReward(@Param("coinCode") String coinCode);
}

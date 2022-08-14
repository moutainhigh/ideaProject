/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0
 * @Date:        2016年3月28日 下午6:00:18
 */
package hry.social.transaction.dao;

import hry.social.transaction.model.ExDmTransaction;

import hry.core.mvc.dao.base.BaseDao;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:00:18
 */
public interface ExDmTransactionDao extends BaseDao<ExDmTransaction, Long> {

    ExDigitalmoneyAccountRedis getAccount4DB(@Param("customerId") Long customerId, @Param("coinCode") String coinCode);
}

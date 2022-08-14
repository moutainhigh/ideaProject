/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: Wu Shuiming
 * @version: V1.0
 * @Date: 2016年3月28日 下午6:57:19
 */
package hry.social.transaction.service;

import hry.core.mvc.service.base.BaseService;
import hry.social.transaction.model.ExDmTransaction;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:57:19
 */
public interface ExDmTransactionService extends BaseService<ExDmTransaction,Long> {

    /**
     * 数据库查询账户
     *
     * @param customerId
     * @param coinCode
     * @return
     */
    ExDigitalmoneyAccountRedis getAccount4DB(Long customerId, String coinCode);
}

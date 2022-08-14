/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 14:43:35
 */
package hry.social.force.service;

import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.force.SocialForceCoinAddition;

import java.math.BigDecimal;


/**
 * <p> SocialForceCoinAdditionService </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 14:43:35
 */
public interface SocialForceCoinAdditionService extends BaseService<SocialForceCoinAddition,Long> {

    /**
     * 查询加成值
     *
     * @param customerId
     * @return
     */
    BigDecimal findAddition(Long customerId);

    /**
     * 查询会员加成
     *
     * @param customerId
     * @return
     */
    SocialForceCoinAddition findVip(Long customerId);
}

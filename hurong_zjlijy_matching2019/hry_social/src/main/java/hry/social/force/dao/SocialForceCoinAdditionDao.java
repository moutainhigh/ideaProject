/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 14:43:35
 */
package hry.social.force.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.force.SocialForceCoinAddition;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


/**
 * <p> SocialForceCoinAdditionDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 14:43:35
 */
public interface SocialForceCoinAdditionDao extends BaseDao<SocialForceCoinAddition,Long> {

    /**
     * 查询加成值
     *
     * @param customerId
     * @return
     */
    BigDecimal findAddition(@Param("customerId") Long customerId);

    /**
     * 查询用户会员加成记录
     * @param customerId
     * @return
     */
    SocialForceCoinAddition findVip(Long customerId);

}

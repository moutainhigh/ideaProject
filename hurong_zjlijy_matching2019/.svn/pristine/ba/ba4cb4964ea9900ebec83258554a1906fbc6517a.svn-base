/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 11:35:57
 */
package hry.social.vip.dao;


import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.vip.SocialCustomerVip;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> SocialCustomerVipDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 11:35:57
 */
public interface SocialCustomerVipDao extends BaseDao<SocialCustomerVip,Long> {
    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    List<SocialCustomerVip> findPageList(Map<String,Object> map);

    /**
     * 查询会员信息
     *
     * @param customerId
     * @return
     */
    SocialCustomerVip getVipInfo(@Param("customerId") Long customerId);

    /**
     * 清除过期的信息
     *
     * @param customerId
     */
    void deleteOverdue(@Param("customerId") Long customerId);

    /**
     * 查询用户的会员加成
     *
     * @param customerId
     * @return
     */
    BigDecimal getVipAdd(@Param("customerId") Long customerId);
}

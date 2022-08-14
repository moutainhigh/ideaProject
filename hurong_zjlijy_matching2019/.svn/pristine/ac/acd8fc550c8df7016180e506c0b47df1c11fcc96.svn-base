/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 11:35:57
 */
package hry.social.vip.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.vip.SocialCustomerVip;
import hry.util.QueryFilter;

import java.math.BigDecimal;


/**
 * <p> SocialCustomerVipService </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 11:35:57
 */
public interface SocialCustomerVipService extends BaseService<SocialCustomerVip,Long> {

    /**
     * 分页查询
     *
     * @param filter
     * @return
     */
    PageResult findPageList(QueryFilter filter);

    /**
     * 查询会员信息
     *
     * @param customerId
     * @return
     */
    SocialCustomerVip getVipInfo(Long customerId);

    /**
     * 查询用户会员信息(包含任务算力&矿机算力&数币矿机加成信息)
     *
     * @param customerId
     * @return
     */
    SocialCustomerVip getUserVipInfo(Long customerId);

    /**
     * 查询用户的会员加成
     *
     * @param customerId
     * @return
     */
    BigDecimal getVipAdd(Long customerId);
}

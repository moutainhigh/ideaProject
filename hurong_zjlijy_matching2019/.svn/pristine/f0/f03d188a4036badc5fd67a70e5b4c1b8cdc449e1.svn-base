/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 14:44:12
 */
package hry.social.manage.remote.api.force;

import hry.manage.remote.model.RemoteResult;
import hry.social.manage.remote.model.force.SocialCalculateForce;
import hry.social.manage.remote.model.force.SocialPermanentForce;
import hry.social.manage.remote.model.force.SocialTerminableForce;
import hry.social.manage.remote.model.vip.SocialCustomerVip;
import hry.social.manage.remote.model.vip.SocialVipInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p> SocialCalculateForceService </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 14:44:12
 */
public interface RemoteForceService {

    /**
     * 查询当前用户算力值
     *
     * @param customerId
     * @return
     */
    BigDecimal getForce(Long customerId);

    /**
     * 查询个人算力分布信息
     *
     * @param customerId
     * @return
     */
    RemoteResult getForceInfo(Long customerId);

    /**
     * 查询个人永久算力信息
     *
     * @param customerId
     * @return
     */
    BigDecimal perpetualTotal(Long customerId);

    /**
     * 查询个人时效算力信息
     *
     * @param customerId
     * @return
     */
    BigDecimal terminablenTotal(Long customerId);

    /**
     * 查询个人会员加成信息
     *
     * @param customerId
     * @return
     */
    BigDecimal findAddition(Long customerId);

    /**
     * 查询个人任务加成信息
     *
     * @param customerId
     * @return
     */
    BigDecimal findTaskFroce(Long customerId);

    /**
     * 查询个人矿机加成信息
     *
     * @param customerId
     * @return
     */
    BigDecimal findMillFroce(Long customerId);

    /**
     * 查询个人永久算力获取记录
     *
     * @param customerId
     * @return
     */
    List<SocialPermanentForce> findPermanentList(Long customerId);

    /**
     * 有效的时效性算力记录
     *
     * @param customerId
     * @return
     */
    List<SocialTerminableForce> findTerminablen(Long customerId);

    /**
     * 失效的时效性算力记录
     *
     * @param customerId
     * @return
     */
    List<SocialTerminableForce> findDisTerminable(Long customerId);

    /**
     * 获取会员信息（加成）
     *
     * @param customerId
     * @return
     */
    SocialCustomerVip getVipInfo(Long customerId);

    /**
     * 查询全网排名前100的用户
     *
     * @return
     */
    List<SocialCalculateForce> getTop();

    /**
     * 查询币种对应的产出
     *
     * @param coinCode
     * @return
     */
    RemoteResult getCoinOut(String coinCode,Long customerId);
}

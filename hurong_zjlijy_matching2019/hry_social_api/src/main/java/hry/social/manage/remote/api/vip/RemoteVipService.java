/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 11:24:44
 */
package hry.social.manage.remote.api.vip;

import hry.manage.remote.model.RemoteResult;
import hry.social.manage.remote.model.vip.SocialCustomerVip;
import hry.social.manage.remote.model.vip.SocialVipInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p> SocialVipInfoService </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 11:24:44
 */
public interface RemoteVipService {
    /**
     * 查询用户会员支付信息
     *
     * @param customerId
     * @return
     */
    RemoteResult getVipPay(Long customerId);

    /**
     * 查询用户会员信息
     *
     * @param customerId
     * @return
     */
    SocialCustomerVip getUserVipInfo(Long customerId);

    /**
     * 查询可购买的会员信息
     *
     * @param socialCustomerVip
     * @return
     */
    List<SocialVipInfo> getAvailableVip(SocialCustomerVip socialCustomerVip);

    /**
     * 获取会员信息
     *
     * @param vipId
     * @return
     */
    SocialVipInfo vipOne(Long vipId);

    /**
     * 会员续购
     *
     * @param vipId
     * @param num
     * @param cnyAmount
     * @param coinCode
     * @param payNum
     * @param coinMarket
     * @param payAmount
     * @param accountPwd
     * @param customerId
     * @return
     */
    RemoteResult vipRenewSave(Long vipId, BigDecimal num, BigDecimal cnyAmount, String coinCode, BigDecimal payNum, BigDecimal coinMarket, BigDecimal payAmount, String accountPwd, Long customerId);

    /**
     * 会员升级
     *
     * @param oldvipId
     * @param nowvipId
     * @param updatePrice
     * @param coinCode
     * @param payNum
     * @param coinMarket
     * @param payAmount
     * @param accountPwd
     * @param customerId
     * @return
     */
    RemoteResult vipUpdateSave(Long oldvipId, Long nowvipId, BigDecimal updatePrice, String coinCode, BigDecimal payNum, BigDecimal coinMarket, BigDecimal payAmount, String accountPwd, Long customerId);
}

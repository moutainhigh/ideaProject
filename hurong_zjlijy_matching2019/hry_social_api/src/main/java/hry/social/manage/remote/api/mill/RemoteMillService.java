/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 18:29:25
 */
package hry.social.manage.remote.api.mill;

import hry.manage.remote.model.RemoteResult;

import java.math.BigDecimal;

/**
 * <p> SocialMillInfoService </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 18:29:25
 */
public interface RemoteMillService {

    /**
     * 查询用户矿机支付信息
     *
     * @param customerId
     * @return
     */
    RemoteResult getMillPay(Long customerId);

    /**
     * 查看用户矿机购买信息
     *
     * @param customerId
     * @return
     */
    RemoteResult getUserMillInfo(Long customerId);

    /**
     * 查看用户矿机购买记录
     *
     * @param customerId
     * @return
     */
    RemoteResult getUerMillList(Long customerId);

    /**
     * 查看用户可购买矿机信息
     *
     * @param rewardType
     * @return
     */
    RemoteResult getMillInfo(Integer rewardType);

    /**
     * 查看用户购买数币矿机总奖励量
     *
     * @param customerId
     * @return
     */
    BigDecimal findMillCoin(Long customerId);

    /**
     * 矿机购买
     *
     * @param millId     矿机ID
     * @param num        购买数量
     * @param cnyAmount  购买总价（CNY）
     * @param coinCode   支付币种
     * @param payNum     支付数
     * @param coinMarket 支付币市值
     * @param payAmount  支付币总金额（CNY）
     * @param accountPwd 资金密码
     * @param customerId 用户ID
     * @return
     */
    RemoteResult millSave(Long millId, BigDecimal num, BigDecimal cnyAmount, String coinCode, BigDecimal payNum, BigDecimal coinMarket, BigDecimal payAmount, String accountPwd, Long customerId);
}

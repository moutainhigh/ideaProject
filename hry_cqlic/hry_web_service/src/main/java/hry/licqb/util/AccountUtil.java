package hry.licqb.util;

import hry.trade.redis.model.Accountadd;

import java.math.BigDecimal;

/**
 * @author zhouming
 * @date 2019/8/15 11:07
 * @Version 1.0
 */
public class AccountUtil {

    /**
     * 插入发送消息信息
     *
     * @param accountId
     *            币账户id
     * @param Money
     *            金额
     * @param monteyType
     *            //1热账户，2，冷账号
     * @param acccountType
     *            //0资金账号，1币账户
     * @param remark
     *            //备注 必须填 安照AccountRemarkEnum.java
     * @param transactionNum
     *            订单号
     * @return
     */
    public static Accountadd getAccountAdd(Long accountId, BigDecimal Money, Integer monteyType, Integer acccountType,
                                     Integer remark, String transactionNum) {
        Accountadd accountadd = new Accountadd();
        accountadd.setAccountId(accountId);
        accountadd.setMoney(Money);
        accountadd.setMonteyType(monteyType);
        accountadd.setAcccountType(acccountType);
        accountadd.setRemarks(remark);
        accountadd.setTransactionNum(transactionNum);
        return accountadd;
    }
}

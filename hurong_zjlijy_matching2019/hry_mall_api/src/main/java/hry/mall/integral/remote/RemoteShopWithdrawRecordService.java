package hry.mall.integral.remote;

import hry.bean.JsonResult;

import java.math.BigDecimal;

/**
 * @auther zhouming
 * @date 2019/3/22 16:17
 * @Version 1.0
 */
public interface RemoteShopWithdrawRecordService {

    /**
     * 准备申请提现
     * **/
    JsonResult readyWithdrawalApplications(long memberId);

    /**
     * 申请提现提交
     * @param memberId
     * @param way 提现方式：1支付宝；2微信；3银联
     * @param bankName
     * @param accountNumber
     * @param applicationAmount
     * @param accountPwd
     * @param markImg
     * @return
     */
    JsonResult submitWithdrawalApplication(Long memberId, Integer way, String bankName, String accountNumber, BigDecimal applicationAmount, String accountPwd, String markImg);

    JsonResult isWithdraw(Long memberId, BigDecimal applicationAmount);

}

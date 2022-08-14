package hry.util;

import hry.redis.common.dao.RedisUtil;
import hry.trade.redis.model.AppAccountRedis;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;

/**
 * @author : javalx
 * @version : V1.0
 * @desc : redis用户信息和账户信息操作
 * @date : 2019/6/3 11:09
 */
public class UserRedisUtils {

    /**
     * 获得redis账户
     *
     * @return
     */
    public static AppAccountRedis getAccount(String accountId) {
        if (accountId != null && !"".equals(accountId)) {
            RedisUtil<AppAccountRedis> a = new RedisUtil<AppAccountRedis>(AppAccountRedis.class);
            AppAccountRedis appAccount = a.get(accountId);
            return appAccount;
        }
        return null;

    }

    /**
     * 获得redis币账户
     *
     * @return
     */
    public static ExDigitalmoneyAccountRedis getAccount(String exAccountId, String coinCode) {
        if (exAccountId != null && !"".equals(exAccountId)) {
            RedisUtil<ExDigitalmoneyAccountRedis> a = new RedisUtil<ExDigitalmoneyAccountRedis>(ExDigitalmoneyAccountRedis.class);
            ExDigitalmoneyAccountRedis dmAccount = a.get(exAccountId);
            return dmAccount;
        }
        return null;
    }

}

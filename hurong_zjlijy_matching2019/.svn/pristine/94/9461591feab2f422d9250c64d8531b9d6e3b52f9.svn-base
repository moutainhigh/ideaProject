package hry.util.deal;

import com.alibaba.fastjson.JSON;
import hry.app.account.service.OtcAccountService;
import hry.otc.manage.remote.model.account.OtcAccount;
import hry.util.AccountDeEnUtil;
import hry.util.OtcRedis;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.constant.ExchangeDataCacheRedis;
import hry.util.dto.OtcAccountRedis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class OtcDealUtil {
    /**
     * 更新币种账户
     */
    public static void setOtcAccount(OtcAccountRedis otcAccount, Transaction transaction){
        String accountKey = ExchangeDataCacheRedis.getAccountKey(otcAccount.getCoinCode());
        String  accountStr= AccountDeEnUtil.encode(JSON.toJSONString(otcAccount));
        transaction.hset(accountKey,otcAccount.getCustomerId()+"",accountStr);

    }

    /**
     * 给用户更新账户
     */
    public static void setCusomerOtcAccount(OtcAccountRedis otcAccount, Transaction transaction){
        setOtcAccount(otcAccount,transaction);

    }


    /***
     *内部方法不给外调
     * @param
     * @param coinCode
     * @return
     */
    public static OtcAccountRedis getOtcAccount(Long customerId, String coinCode) {
        try (Jedis jedis = OtcRedis.OTC_JEDIS_POOL.getResource()) {
            String accountKey = ExchangeDataCacheRedis.getAccountKey(coinCode);
            String accountStr = jedis.hget(accountKey, customerId + "");
            if (StringUtils.isNotEmpty(accountStr)) {
                  accountStr= AccountDeEnUtil.decode(accountStr);
                return JSON.parseObject(accountStr, OtcAccountRedis.class);
            }

            OtcAccountService otcAccountService = (OtcAccountService) SpringUtil.getBean("otcAccountService");
            synchronized (customerId) {
                QueryFilter filter = new QueryFilter(OtcAccount.class);
                filter.addFilter("customerId=", customerId);
                filter.addFilter("coinCode=", coinCode);
                List<OtcAccount> otcAccountlist = otcAccountService.find(filter);

                if (null != otcAccountlist &&otcAccountlist.size()>0) {
                    OtcAccount otcAccount=otcAccountlist.get(0);
                    OtcAccountRedis otcAccountRedis = JSON.parseObject(JSON.toJSONString(otcAccount), OtcAccountRedis.class);
                    jedis.hset(accountKey, customerId + "", AccountDeEnUtil.encode(JSON.toJSONString(otcAccountRedis)));
                    return otcAccountRedis;
                } else {
                    /**
                     * 创建币账户
                     */
                    OtcAccount account = new  OtcAccount();
                    account.setCoinCode(coinCode);
                    account.setCustomerId(customerId);
                    account.setHotMoney(BigDecimal.ZERO);
                    account.setColdMoney(BigDecimal.ZERO);
                    otcAccountService.save(account);

                    OtcAccountRedis accountRedis = new OtcAccountRedis();
                    BeanUtils.copyProperties(account, accountRedis);
                    jedis.hset(accountKey, customerId + "", AccountDeEnUtil.encode(JSON.toJSONString(accountRedis)));
                    return accountRedis;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得用户otc账户
     *
     * @param coinCode BTC
     */
    public static OtcAccountRedis getCustomerOtcAccount(Long customerId, String coinCode) {
        return getOtcAccount(customerId, coinCode);
    }


}

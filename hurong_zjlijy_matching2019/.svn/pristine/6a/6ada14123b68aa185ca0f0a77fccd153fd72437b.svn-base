package hry.cmson.deal;

import com.alibaba.fastjson.JSON;
import hry.cmson.account.model.CmSonAccount;
import hry.cmson.account.service.CmSonAccountService;
import hry.cmson.constant.ExchangeDataCacheRedis;
import hry.cmson.dto.CmAccountRedis;
import hry.cmson.util.AccountDeEnUtil;
import hry.cmson.util.CmRedis;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.List;

public class CmDealUtil {
    /**
     * 更新币种账户
     */
    public static void setCmAccount(CmAccountRedis cmSonAccount, redis.clients.jedis.Transaction transaction ){
        String accountKey = ExchangeDataCacheRedis.getAccountKey(cmSonAccount.getCoinCode());
        String  accountStr= AccountDeEnUtil.encode(JSON.toJSONString(cmSonAccount));
        transaction.hset(accountKey,cmSonAccount.getCustomerId()+"",accountStr);
    }


    /***
     *获取账户
     * @param
     * @param coinCode
     * @return
     */
    public static CmAccountRedis getCmAccount(Long customerId, String coinCode) {
        try (Jedis jedis = CmRedis.JEDIS_POOL.getResource()) {
            String accountKey = ExchangeDataCacheRedis.getAccountKey(coinCode);
            String accountStr = jedis.hget(accountKey, customerId + "");
            if (StringUtils.isNotEmpty(accountStr)) {
                accountStr= AccountDeEnUtil.decode(accountStr);
                return JSON.parseObject(accountStr,  CmAccountRedis.class);
            }

            CmSonAccountService cmAccountService = (CmSonAccountService) SpringUtil.getBean("cmSonAccountService");
            synchronized (customerId) {
                QueryFilter filter = new QueryFilter(CmSonAccount.class);
                filter.addFilter("customerId=", customerId);
                filter.addFilter("coinCode=", coinCode);
                List<CmSonAccount> cmAccountlist = cmAccountService.find(filter);

                if (null != cmAccountlist &&cmAccountlist.size()>0) {
                    CmSonAccount binaryAccount=cmAccountlist.get(0);
                    CmAccountRedis cmAccountRedis = JSON.parseObject(JSON.toJSONString(binaryAccount), CmAccountRedis.class);
                    jedis.hset(accountKey, customerId + "", AccountDeEnUtil.encode(JSON.toJSONString(cmAccountRedis)));
                    return cmAccountRedis;
                } else {
                    /**
                     * 创建币账户
                     */
                    CmSonAccount account = new  CmSonAccount();
                    account.setCoinCode(coinCode);
                    account.setCustomerId(customerId);
                    account.setHotMoney(BigDecimal.ZERO);
                    account.setColdMoney(BigDecimal.ZERO);
                    cmAccountService.save(account);

                    CmAccountRedis accountRedis = new CmAccountRedis();
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


}

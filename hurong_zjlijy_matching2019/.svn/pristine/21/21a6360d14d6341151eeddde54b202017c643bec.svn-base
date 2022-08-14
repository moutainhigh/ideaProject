package hry.cm.deal;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;

import hry.cm.account.model.CmAccount;
import hry.cm.account.service.CmAccountService;
import hry.cm.constant.ExchangeDataCacheRedis;
import hry.cm.dto.CmAccountRedis;
import hry.cm.util.AccountDeEnUtil;
import hry.cm.util.CmRedis;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import redis.clients.jedis.Jedis;

public class CmDealUtil {
    /**
     * 更新币种账户
     */
    public static void setCmAccount(CmAccountRedis CmAccount, redis.clients.jedis.Transaction transaction ){
        String accountKey = ExchangeDataCacheRedis.getAccountKey(CmAccount.getCoinCode());
        String  accountStr=AccountDeEnUtil.encode(JSON.toJSONString(CmAccount));
        transaction.hset(accountKey,CmAccount.getCustomerId()+"",accountStr);
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

            CmAccountService cmAccountService = (CmAccountService) SpringUtil.getBean("cmAccountService");
            synchronized (customerId) {
                QueryFilter filter = new QueryFilter(CmAccount.class);
                filter.addFilter("customerId=", customerId);
                filter.addFilter("coinCode=", coinCode);
                List<CmAccount> cmAccountlist = cmAccountService.find(filter);

                if (null != cmAccountlist &&cmAccountlist.size()>0) {
                    CmAccount binaryAccount=cmAccountlist.get(0);
                    CmAccountRedis cmAccountRedis = JSON.parseObject(JSON.toJSONString(binaryAccount), CmAccountRedis.class);
                    jedis.hset(accountKey, customerId + "", AccountDeEnUtil.encode(JSON.toJSONString(cmAccountRedis)));
                    return cmAccountRedis;
                } else {
                    /**
                     * 创建币账户
                     */
                    CmAccount account = new  CmAccount();
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

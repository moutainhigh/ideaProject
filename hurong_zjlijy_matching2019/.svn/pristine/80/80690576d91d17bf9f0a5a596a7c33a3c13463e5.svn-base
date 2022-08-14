package hry.cmson.deal;


import com.alibaba.fastjson.JSON;
import hry.cmson.constant.ExchangeDataCacheRedis;
import hry.cmson.dto.CmAccountRedis;
import hry.cmson.util.AccountDeEnUtil;
import hry.cmson.util.CmRedis;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;

public class CmCustomerUtil {

    /***
     *获取账户
     * @param
     * @param coinCode
     * @return
     */
    public static CmAccountRedis getTcAccount(Long customerId, String coinCode) {
        try (Jedis jedis = CmRedis.JEDIS_POOL.getResource()) {
            String accountKey = ExchangeDataCacheRedis.getAccountKey(coinCode);
            String accountStr = jedis.hget(accountKey, customerId + "");
            if (StringUtils.isNotEmpty(accountStr)) {
                accountStr = AccountDeEnUtil.decode(accountStr);
                return JSON.parseObject(accountStr,  CmAccountRedis.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /***
     *用户最大提币数量
     * @return
     */
    public static BigDecimal getMaxWithdrawal(Long customerId,String  coinCode){
        CmAccountRedis tcbyAccountRedis = getTcAccount( customerId,  coinCode);
        //最大转币数
        BigDecimal maxWithdrawal =null;
        /*if(tcbyAccountRedis.getEmptyCoinMoney().compareTo(tcbyAccountRedis.getColdMoney())>=0){
            maxWithdrawal=tcbyAccountRedis.getHotMoney().subtract(
                    (tcbyAccountRedis.getEmptyCoinMoney().subtract(tcbyAccountRedis.getColdMoney()))
            );
        }else{
            maxWithdrawal=tcbyAccountRedis.getHotMoney();

        }*/
        maxWithdrawal=tcbyAccountRedis.getHotMoney();
        return  maxWithdrawal;
    }


}

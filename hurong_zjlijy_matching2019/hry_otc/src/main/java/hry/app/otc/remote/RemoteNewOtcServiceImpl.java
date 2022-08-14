package hry.app.otc.remote;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import hry.app.account.service.ContractMainAccountRecordService;
import hry.app.account.service.OtcAccountRecordService;
import hry.app.account.service.OtcAccountService;
import hry.bean.FrontPage;
import hry.core.constant.CodeConstant;
import hry.mq.producer.service.MessageProducer;
import hry.otc.manage.remote.api.RemoteNewOtcService;
import hry.otc.manage.remote.model.account.OtcAccount;
import hry.redis.common.utils.RedisService;
import hry.util.AccountDeEnUtil;
import hry.util.OtcConfigUtil;
import hry.util.OtcRedis;
import hry.util.QueryFilter;
import hry.util.constant.ExchangeDataCacheRedis;
import hry.util.deal.OtcDealUtil;
import hry.util.decimal.DecimalUtil;
import hry.util.dto.Accountadd;
import hry.util.dto.OtcAccountRedis;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

public class RemoteNewOtcServiceImpl implements RemoteNewOtcService {

    @Resource
    private OtcAccountService otcAccountService;
    @Resource
    private ContractMainAccountRecordService contractMainAccountRecordService;
    @Resource
    private OtcAccountRecordService otcAccountRecordService;
    @Resource
    private RedisService redisService;

    @Resource
    private MessageProducer messageProducer;

    @Override
    public Boolean initAccount(Long customerId) {
        //出数据库查询，然后对照redis如果redis有账户户缓存，就不要再缓存，如果没有的就加上去
        // 查询该用户的合约账户数据
        QueryFilter accountQf = new QueryFilter(OtcAccount.class);
        accountQf.addFilter("customerId=", customerId);
        List<OtcAccount> accountList = otcAccountService.find(accountQf);
        if (accountList != null && accountList.size() > 0) {
            // 循环判断该账户在redis中是否存在，不存在添加，存在跳过
            try (Jedis jedis = OtcRedis.OTC_JEDIS_POOL.getResource()){
                for (OtcAccount account : accountList) {
                    String hget1 = jedis.hget(ExchangeDataCacheRedis.getAccountKey(account.getCoinCode()), account.getCustomerId().toString());
                    if (StringUtils.isEmpty(hget1)) {
                        OtcAccountRedis coinaccount = new OtcAccountRedis();
                        coinaccount.setCoinCode(account.getCoinCode());
                        coinaccount.setColdMoney(account.getColdMoney());
                        coinaccount.setCustomerId(account.getCustomerId());
                        coinaccount.setHotMoney(account.getHotMoney());
                        coinaccount.setId(account.getId());
                        jedis.hset(ExchangeDataCacheRedis.getAccountKey(account.getCoinCode()), account.getCustomerId().toString(), AccountDeEnUtil.encode(JSON.toJSONString(coinaccount)));
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }


    @Override
    public  List<OtcAccountRedis> getAccoutList(Long customerId) {
        try (Jedis jedis = OtcRedis.OTC_JEDIS_POOL.getResource()) {
            Set<String> coinKeys = OtcConfigUtil.getAllConskeysList();
            QueryFilter qf = new QueryFilter(OtcAccount.class);
            qf.addFilter("customerId=", customerId);
            List<OtcAccount> list = otcAccountService.find(qf);
            List<OtcAccountRedis> listaccount = new ArrayList<OtcAccountRedis>();
            for (OtcAccount c : list) {
                coinKeys.remove(c.getCoinCode());
                OtcAccountRedis account = new OtcAccountRedis();
                account.setId(c.getId());
                account.setCoinCode(c.getCoinCode());
                account.setCustomerId(customerId);
                account.setHotMoney(c.getHotMoney());
                account.setColdMoney(c.getColdMoney());

                //折算成人民币BTC_CNY,易租云接口
                String price = jedis.hget("hry:coinPrice", c.getCoinCode());
                if (StringUtil.isNotEmpty(price)) {
                    account.setValuation((account.getHotMoney().add(account.getColdMoney())).multiply(new BigDecimal(price)));
                } else {
                    String currentPrice = jedis.get(c.getCoinCode() + "_USDT:currentExchangPrice");
                    String USDTPrice = jedis.hget("hry:coinPrice", "USDT") == null ? "6.8" : jedis.hget("hry:coinPrice", "USDT");
                    if(!StringUtils.isEmpty(currentPrice)){
                        BigDecimal rmb = new BigDecimal(currentPrice).multiply(new BigDecimal(USDTPrice));
                        account.setValuation((account.getHotMoney().add(account.getColdMoney())).multiply(rmb));
                    }
                }
                listaccount.add(account);
            }
            //没有实际发生过业务的，是没有账户的
            Iterator<String> coinKeysIter = coinKeys.iterator();
            while (coinKeysIter.hasNext()) {
                String coinCode = coinKeysIter.next();
                OtcAccountRedis account = new OtcAccountRedis();
                account.setCoinCode(coinCode);
                account.setCustomerId(customerId);
                account.setHotMoney(BigDecimal.ZERO);
                account.setColdMoney(BigDecimal.ZERO);
                listaccount.add(account);
            }
            return listaccount;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //总资产，循环把估值相加就是
        return null;
    }


    @Override
    public synchronized String[] accountOtcTomain(Long customerId, Long mainAccountId, String coinCode, BigDecimal count) {
        //币种保留位数
        Integer retentionNumber = Integer.valueOf(redisService.get("retentionNumber:"+ coinCode));
        String[] rt = new String[2];
        OtcAccountRedis otcAccountRedis = OtcDealUtil.getCustomerOtcAccount( customerId,  coinCode);
        Long otcAccountId = otcAccountRedis.getId();

        if (count.compareTo(new BigDecimal("0")) > 0 && otcAccountRedis.getHotMoney().compareTo(count) >= 0) {
            contractMainAccountRecordService.otcTomain(customerId, mainAccountId, otcAccountId, coinCode, count);
            rt[0] = CodeConstant.CODE_SUCCESS;
            rt[1] = "下单成功";
            return rt;
        } else {
            rt[0] = CodeConstant.CODE_FAILED;
            rt[1] = "余额不足,最大能提"+ DecimalUtil.getPrettyNumber(otcAccountRedis.getHotMoney().setScale(retentionNumber,BigDecimal.ROUND_DOWN).toString());
            return rt;
        }

    }

    @Override
    public synchronized String[] accountMainToOtc(Long customerId, Long mainAccountId, String coinCode, BigDecimal count) {
        //余额判断在front判断，这里获取不到主账户的余额
        String[] rt = new String[2];
        OtcAccountRedis otcAccountRedis = OtcDealUtil.getCustomerOtcAccount( customerId,  coinCode);
        Long otcAccountId = otcAccountRedis.getId();
        contractMainAccountRecordService.mainToOtc(customerId, mainAccountId, otcAccountId, coinCode, count);
        rt[0] = CodeConstant.CODE_SUCCESS;
        rt[1] = "下单成功";
        return rt;
    }
    @Override
    public synchronized String[] beforaccount(Long customerId, String coinCode) {
        //余额判断在front判断，这里获取不到主账户的余额
        System.out.println("==== 建立OTC账户 ==== 用户ID : " + customerId + "  ==== 币种 : " + coinCode);
        String[] rt = new String[2];
        OtcAccountRedis otcAccountRedis = OtcDealUtil.getCustomerOtcAccount( customerId,  coinCode);

        rt[0] = CodeConstant.CODE_SUCCESS;
        return rt;
    }

    @Override
    public BigDecimal getHotMoneyByCoin (String coinCode, Long customerId) {
        OtcAccountRedis otcAccountRedis = OtcDealUtil.getCustomerOtcAccount( customerId,  coinCode);
        if(otcAccountRedis !=null){
            return otcAccountRedis.getHotMoney();
        }
        return BigDecimal.ZERO;
    }


    @Override
    public FrontPage getAccountRecord (Map<String, String> params) {
        return otcAccountRecordService.getAccountRecord(params);
    }

    @Override
    public OtcAccountRedis getAccoutByIdAndCoinCode (Long customerId, String coinCode) {
        OtcAccountRedis otcAccountRedis = OtcDealUtil.getCustomerOtcAccount( customerId,  coinCode);
        if(otcAccountRedis !=null){
            return otcAccountRedis;
        }
        return null;
    }

    @Override
    public void publish(Long userId,Long accountId,String coinCode, BigDecimal money, Integer monteyType, Integer acccountType, String transactionNum, Integer remarks){
        List<Accountadd> aaddlists = new ArrayList<Accountadd>();
        Accountadd accountadd3 = new Accountadd(userId,coinCode,accountId, money, monteyType, remarks,transactionNum);
        aaddlists.add(accountadd3);
        otcAccountService.accountaddQueue(JSON.toJSONString(aaddlists));
    }
}

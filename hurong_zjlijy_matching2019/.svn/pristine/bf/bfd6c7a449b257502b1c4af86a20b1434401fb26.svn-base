package hry.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.model.Coin;
import hry.manage.remote.model.CoinAccount;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 通知发送
 *
 * @author javal
 * @title: MessageRunnable
 * @projectName hurong_matching2019
 * @description: TODO
 * @date 2019/7/1116:18
 */
public class AccountRunnable implements Runnable {

    private final Logger log = Logger.getLogger(AccountRunnable.class);

    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 币种
     */
    private String coinCode;

    public AccountRunnable(String msgJson) {
        System.out.println("账户同步消息MQ信息====>>>" + msgJson);
        if (null != msgJson) {
            AccountSyncMessage accountMessage = JSON.parseObject(msgJson, AccountSyncMessage.class);
            this.customerId = accountMessage.getCustomerId();
            this.coinCode = accountMessage.getCoinCode();
        }
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            // 查redis缓存
            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
            UserRedis userRedis = redisUtil.get(customerId.toString());
            if (userRedis == null || "".equals(userRedis)) {
                return;
            }
            // 获得缓存中平台币账户id
            Map<String,Long> dmAccMap = userRedis.getDmAccountId();
            Long dmAccId = dmAccMap.get(coinCode);
            // 获得缓存币账户
            ExDigitalmoneyAccountRedis dmAccount = UserRedisUtils.getAccount(dmAccId.toString(), coinCode);
            if (dmAccount == null) {
                return;
            }
            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
            Map<String,String> productMap = redisService.getMap(SocialUtil.PRODUCT_KEY);
            String platCoinStr = productMap.get(coinCode);
            Integer keepNum = 8;
            CoinAccount coinAccount = new CoinAccount();
            if (platCoinStr != null) {
                Coin coin = JSONArray.parseObject(platCoinStr, Coin.class);
                keepNum = coin.getKeepDecimalForCoin();
                coinAccount.setName(coin.getName());
            }
            coinAccount.setCoinCode(dmAccount.getCoinCode());
            coinAccount.setColdMoney(dmAccount.getColdMoney().setScale(keepNum, BigDecimal.ROUND_HALF_DOWN));
            coinAccount.setHotMoney(dmAccount.getHotMoney().setScale(keepNum, BigDecimal.ROUND_HALF_DOWN));
            //推送账户消息
            Map<String,Object> map = new HashMap();
            map.put("destination", customerId);
            map.put("pushData", coinAccount);
            String pushUrl = NoticeTemplateUtil.getPushUrl(customerId);
            if (!StringUtils.isEmpty(pushUrl)) {
                pushUrl += "/accountPush";
                String post = HttpClientNodeUtil.sendHttpPost(pushUrl, JSON.toJSONString(map));
                System.out.println("the result of account push : " + post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package hry.listener.cloudminingv5;

import hry.cm5.remote.RemoteCmListenerService;
import hry.util.SpringUtil;

/**
 * @author zhouming
 * @date 2020/1/15 16:35
 * @Version 1.0
 */
public class Cm5Management {

    /**
     * 李金沅矿机产币---每天
     * */
    public void miner5CoinageByDay () {
        // 云矿机 矿机产币  按天
        RemoteCmListenerService remoteCmListenerService = SpringUtil.getBean("remoteCm5ListenerService");
        remoteCmListenerService.minerCoinageByDay();
    }
}

package hry.listener.cloudminingv2;

import hry.cm2.remote.RemoteCmListenerService;
import hry.util.SpringUtil;

/**
 * @auther yaozh
 * @date 2019/7/4 14:17
 * @Version 1.0
 */
public class Cm2Management {

    /**
     * 云矿机 矿机产币  按天
     * */
    public void miner2CoinageByDay () {

        // 云矿机 矿机产币  按天
    	RemoteCmListenerService remoteCmListenerService = SpringUtil.getBean("remoteCm2ListenerService");
    	remoteCmListenerService.minerCoinageByDay();

    }
    
    /**
     * 云矿机 矿机到期更新 矿机运行更新
                * 每天0-23点 整点执行
     * */
        public void miner2WaitAndCloseTiming () {
    	
    	// 矿机到期更新 矿机运行更新  每天0-23点 整点执行
    	RemoteCmListenerService remoteCmListenerService = SpringUtil.getBean("remoteCm2ListenerService");
    	remoteCmListenerService.minerWaitAndCloseTiming();
    	
    }
    
    /**
     * 矿工动态收益,统计矿场动态收益为未发放状态
     * */
    public void profit2one () {
    	RemoteCmListenerService remoteCmListenerService = SpringUtil.getBean("remoteCm2ListenerService");
    	remoteCmListenerService.profitone();
    }

}

package hry.listener.cloudmining;

import hry.cm.remote.RemoteCmListenerService;
import hry.util.SpringUtil;

/**
 * @auther yaozh
 * @date 2019/7/4 14:17
 * @Version 1.0
 */
public class CmManagement {

    /**
     * 云矿机 矿机产币  按天
     * */
    public void minerCoinageByDay () {

        // 云矿机 矿机产币  按天
    	RemoteCmListenerService remoteCmListenerService = SpringUtil.getBean("remoteCmListenerService");
    	remoteCmListenerService.minerCoinageByDay();

    }
    
    /**
     * 云矿机 矿机到期更新 矿机运行更新
                * 每天0-23点 整点执行
     * */
        public void minerWaitAndCloseTiming () {
    	
    	// 矿机到期更新 矿机运行更新  每天0-23点 整点执行
    	RemoteCmListenerService remoteCmListenerService = SpringUtil.getBean("remoteCmListenerService");
    	remoteCmListenerService.minerWaitAndCloseTiming();
    	
    }
    
    /**
     * 矿工动态收益,统计矿场动态收益为未发放状态
     * */
    public void profitone () {
    	RemoteCmListenerService remoteCmListenerService = SpringUtil.getBean("remoteCmListenerService");
    	remoteCmListenerService.profitone();
    }
    
    /**
     * 矿场动态收益
     * */
    public void grantProfitTwo () {
    	RemoteCmListenerService remoteCmListenerService = SpringUtil.getBean("remoteCmListenerService");
    	remoteCmListenerService.grantProfit();
    }
}

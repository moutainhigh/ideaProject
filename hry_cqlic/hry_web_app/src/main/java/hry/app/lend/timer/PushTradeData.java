package hry.app.lend.timer;

import hry.app.lend.websocket.TradingHallSocketHandler;
import hry.app.lend.websocket.TradingKlineSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/8/7 20:18
 * @Description: 推送总控
 * 1 后期去掉 改为消息通知式
 * 2 双模切换 保留 整合quartz4.0+
 *
 * 废弃
 */
@Configuration
@EnableScheduling
public class PushTradeData {
    /**
     * 交易大厅数据推送
     */
    /*@Resource
    private TradingHallSocketHandler tradingHallSocketHandler;
    @Scheduled(fixedRate = 500)
    public void pushTradeData(){
        tradingHallSocketHandler.sendMessageToUserAll();
    }
    @Scheduled(fixedRate = 2000)
    public void pushLoginData(){
        tradingHallSocketHandler.pushLoginData();
    }
    @Scheduled(fixedRate = 4000)
    public void calculateData(){
        tradingHallSocketHandler.calculateData();
    }



    *//**
     * 单节点持续推送
     *//*
    @Resource
    private TradingKlineSocketHandler tradingKlineSocketHandler;
    @Scheduled(fixedRate = 500)
    public void pushOneNode(){
        tradingKlineSocketHandler.pushOneNodeAll();
    }
*/

}
package hry.app.kline.timer;

import hry.app.kline.netty.controller.WebSocketClient;
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
 */
@Configuration
@EnableScheduling
public class PushTradeDataSchedule {

    @Resource
    WebSocketClient webSocketClient;

    @Scheduled(fixedRate = 500)
    public void pushTrade(){
        //webSocketClient.pushTrade();
    }




}

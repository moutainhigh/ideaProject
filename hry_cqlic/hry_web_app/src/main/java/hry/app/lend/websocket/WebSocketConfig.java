package hry.app.lend.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    /**
     * 交易大厅数据推送处理器
     */
    @Bean
    public WebSocketHandler tradingHallHandler() {
        return new TradingHallSocketHandler();
    }

    /**
     * k线数据推送处理器
     */
    @Bean
    public WebSocketHandler tradingKlineHandler() {
        return new TradingKlineSocketHandler();
    }

    /**
     * 注册连接
     *
     * @param registry 注册器，将url注册在对应的处理器上
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        /**
         *
         *          废(Fei)       弃(Qi)
         *
         *                          By:Hec
         *                          2018年11月22日11:23:07
         */



        /*registry.addHandler(tradingHallHandler(), "/ws").addInterceptors(new TradingHallSocketshake()).setAllowedOrigins("*");
        registry.addHandler(tradingHallHandler(),"/ws").addInterceptors(new TradingHallSocketshake()).withSockJS();

        registry.addHandler(tradingKlineHandler(), "/kline").addInterceptors(new TradingKlineSocketShake()).setAllowedOrigins("*");
        registry.addHandler(tradingKlineHandler(),"/kline").addInterceptors(new TradingKlineSocketShake()).withSockJS();*/
    }

}

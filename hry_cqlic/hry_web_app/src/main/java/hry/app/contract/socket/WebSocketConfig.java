package hry.app.contract.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">HeC</a>
 * @date 2018/12/21 15:48
 * 合约大厅 socket连接配置
 */
@Configuration("contractWebSocketConfig")
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    public TradingKlineSocketHandler tradingKlineSocketHandler() {
        return new TradingKlineSocketHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(tradingKlineSocketHandler(), "/ct").addInterceptors(new TradingKlineSocketShake()).setAllowedOrigins("*");
        registry.addHandler(tradingKlineSocketHandler(), "/ct").addInterceptors(new TradingKlineSocketShake()).withSockJS();
    }
}
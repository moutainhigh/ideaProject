package hry.app.socket.config;


import hry.app.socket.handler.OtcChatHallHandler;
import hry.app.socket.interceptor.OtcChatHallSocketShake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration("otcChatWebSocketConfig")
@EnableWebSocket
public class OtcChatWebSocketConfig implements WebSocketConfigurer {

    public static final Logger log = LoggerFactory.getLogger(OtcChatWebSocketConfig.class);

    @Bean("otcChatHallHandler")
    public OtcChatHallHandler tradingKlineSocketHandler() {
        return new OtcChatHallHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        log.debug("websocket handler 注册");
        registry.addHandler(tradingKlineSocketHandler(), "/otcChat").addInterceptors(new OtcChatHallSocketShake()).setAllowedOrigins("*");
        registry.addHandler(tradingKlineSocketHandler(), "/otcChat").addInterceptors(new OtcChatHallSocketShake()).withSockJS();
    }

}

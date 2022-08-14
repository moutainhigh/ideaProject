package hry.app.socket.interceptor;


import hry.util.OtcRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">HeC</a>
 * @date 2018/12/21 15:50
 * 交易大厅 & k线数据 首次握手 验证授权
 */
public class OtcChatHallSocketShake implements HandshakeInterceptor {

    public static final Logger log = LoggerFactory.getLogger(OtcChatHallSocketShake.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> sessionMap)throws Exception {
        System.out.println("OtcChat websocket连接成功");
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
        try (Jedis jedis = OtcRedis.OTC_JEDIS_POOL.getResource()) {
            log.info("[TradingHall]用户ID：[{}]已建立连接", "1111");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}

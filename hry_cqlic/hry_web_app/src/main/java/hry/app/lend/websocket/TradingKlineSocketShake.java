package hry.app.lend.websocket;

import hry.app.lend.model.LendRedis;
import hry.app.lend.websocket.model.SessionRedis;
import hry.app.lend.websocket.util.HallSessionUtil;
import org.apache.commons.lang3.StringUtils;
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
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Date 2018/11/16 13:53
 * k 线推送 任意用户都会推送
 */
public class TradingKlineSocketShake implements HandshakeInterceptor {

    public static final Logger log = LoggerFactory.getLogger(TradingKlineSocketShake.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> sessionMap) throws Exception {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
        try (Jedis jedis = LendRedis.JEDIS_POOL.getResource()) {
            SessionRedis sessionRedis = HallSessionUtil.getSession(jedis, httpServletRequest);
            if(null!=sessionRedis) {
                // 标记用户
                String marginToken = sessionRedis.getMarginToken();
                String coinCode = sessionRedis.getCoinCode();
                if (StringUtils.isNotEmpty(marginToken) && StringUtils.isNotEmpty(coinCode)) {
                    //往socket session中存值
                    sessionMap.put("coinCode", coinCode);
                    //唯一标识
                    sessionMap.put("marginToken", marginToken);
                    log.info("[TradingKline]用户ID：[{}]已建立连接", marginToken);
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
    }
}
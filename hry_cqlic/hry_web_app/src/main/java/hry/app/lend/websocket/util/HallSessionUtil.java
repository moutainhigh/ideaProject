package hry.app.lend.websocket.util;

import com.alibaba.fastjson.JSON;
import hry.app.lend.websocket.model.SessionRedis;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/11/2 17:26
 * @Description: 交易大厅伪session
 */
public class HallSessionUtil {

    public static final String TOKEN_NAME = "marginToken";

    private static final String MARGIN_TOKEN_PREFIX = "hall:session:";
    /**
     * 创建交易大厅session
     */
    public static void createSession(Jedis jedis, SessionRedis session, HttpServletResponse response){
        jedis.set(MARGIN_TOKEN_PREFIX+session.getMarginToken(),JSON.toJSONString(session));
        jedis.expire(MARGIN_TOKEN_PREFIX+session.getMarginToken(),1800);
        Cookie cookie = new Cookie(TOKEN_NAME,session.getMarginToken());
        response.addCookie(cookie);
    }

    /**
     * 获得交易大厅session
     */
    public static SessionRedis getSession(Jedis jedis, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(null!=cookies&&cookies.length>0) {
            String token = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(TOKEN_NAME)) {
                    token = cookie.getValue();
                    break;
                }
            }
            if (StringUtils.isEmpty(token)) {
                token = String.valueOf(request.getAttribute(TOKEN_NAME));
            }
            if (StringUtils.isNotEmpty(token)) {
                String sessionStr = jedis.get(MARGIN_TOKEN_PREFIX + token);
                if (StringUtils.isNotEmpty(sessionStr)) {
                    return JSON.parseObject(sessionStr, SessionRedis.class);
                }
            }
        }
        return null;
    }

    public static void delSession(Jedis jedis,String token){
        jedis.del(token);
    }
}
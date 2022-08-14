package hry.app.jwt;

import com.alibaba.fastjson.JSON;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * token工具类
 */
public class TokenUtil {

    /**
     * 获取登录用户
     *
     * @param request
     * @return
     */
    public static User getUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            return getUser(token);
        }
        return null;
    }


    /**
     * 获取登录用户
     *
     * @param token
     * @return
     */
    public static User getUser(String token) {
        if (!StringUtils.isEmpty(token)) {
            String username = JWTUtil.getUsername(token);
            if (!StringUtils.isEmpty(username)) {
                RedisService redisService = SpringUtil.getBean("redisService");
                String userStr = redisService.get("JWT:token:user:" + username);
                if (!StringUtils.isEmpty(userStr)) {
                    User user = JSON.parseObject(userStr, User.class);
                    RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
                    User seleteUser = remoteManageService.getUserInfoByUsername(username);
                    seleteUser.setNickName(user.getNickName());
                    updateUser(seleteUser);
                    return seleteUser;
                }
            }
        }
        return null;
    }


    /**
     * 获取登录用户名
     *
     * @param request
     * @return
     */
    public static String getUserName(HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = JWTUtil.getUsername(token);
        return username;
    }

    /**
     * 更新登录用户
     *
     * @return
     */
    public static void updateUser(User user) {
        if (user != null) {
            RedisService redisService = SpringUtil.getBean("redisService");
            redisService.save("JWT:token:user:" + user.getUsername(), JSON.toJSONString(user));
        }
    }


    /**
     * 杠杆获取登录用户
     */
    public static User getLendUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            Cookie[] cookies = request.getCookies();
            if (null != cookies && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        if (!StringUtils.isEmpty(token)) {
            String username = JWTUtil.getUsername(token);
            if (!StringUtils.isEmpty(username)) {
                RedisService redisService = SpringUtil.getBean("redisService");
                String userStr = redisService.get("JWT:token:user:" + username);
                if (!StringUtils.isEmpty(userStr)) {
                    User user = JSON.parseObject(userStr, User.class);
                    return user;
                }
            }
        }
        return null;
    }

}

package hry.app.jwt;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * 设置存储到redis中的RefreshToken key的前缀
     */
    public final static String PREFIX_SHIRO_REFRESH_TOKEN_SIGN = "JWT:token:";


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 这里我们详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 判断用户是否想要登入
        if (this.isLoginAttempt(request, response)) {
            try {
                // 进行Shiro的登录UserRealm
                this.executeLogin(request, response);
            } catch (Exception e) {
                // 认证出现异常跳转到/401，传递错误信息msg
                String msg = e.getMessage();
                // 获取应用异常(该Cause是导致抛出此throwable(异常)的throwable(异常))
                Throwable throwable = e.getCause();
                if(throwable != null && throwable instanceof SignatureVerificationException){
                    // 该异常为JWT的Token认证失败(Token或者密钥不正确)
                    msg = "Token或者密钥不正确(" + throwable.getMessage() + ")";
                } else if(throwable != null && throwable instanceof TokenExpiredException){
                    // 该异常为JWT的AccessToken已过期
                    // 获取当前请求中的AccessToken
                    String accessToken = this.getToken(request, response);

                    // 获取AccessToken中的用户名
                    String username = JWTUtil.getUsername(accessToken);

                    // 获取用户密码
                    RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
                    User user = remoteManageService.getUserInfoByUsername(username);
                    String password = user.getPassword();

                    // 获取AccessToken中的来源
                    String source = JWTUtil.getClaim(accessToken, "source");

                    RedisService redisService = SpringUtil.getBean("redisService");
                    // 获取当前时间戳
                    String currentTimeMillis = String.valueOf(System.currentTimeMillis());

                    // 获取RefreshToken剩余过期时间
                    Long refreshTokenExpireTimeRedis = redisService.getExpireTime(PREFIX_SHIRO_REFRESH_TOKEN_SIGN + source + ":sign:" + username);

                    if (refreshTokenExpireTimeRedis > 0) {
                        // 重新生成AccessToken
                        accessToken = JWTUtil.sign(username, source, password);

                        // 重新设置RefreshToken有效期,1天,秒
                        int accessTokenExpireTime = 24*60*60;

                        // 设置RefreshToken，且过期时间为之前剩余过期时间加上一个新的AccessToken过期时间
                        redisService.save(PREFIX_SHIRO_REFRESH_TOKEN_SIGN + source + ":sign:" + username, accessToken, refreshTokenExpireTimeRedis.intValue() + accessTokenExpireTime);

                        // 将刷新的AccessToken存放在Response的Header中的Authorization字段返回
                        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                        httpServletResponse.setHeader("Authorization", accessToken);
                        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");

                        // 进行Shiro的登录UserRealm
                        JWTToken JToken = new JWTToken(accessToken);

                        // 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获
                        this.getSubject(request, response).login(JToken);

                        // 如果没有抛出异常则代表登入成功，返回true
                        return true;
                    }
                    msg = "Token已过期(" + throwable.getMessage() + ")";
                } else{
                    // 应用异常不为空
                    if(throwable != null) {
                        // 获取应用异常msg
                        msg = throwable.getMessage();
                    }
                }
                this.forward401(request, response, msg);
                return false;
            }
            return true;
        }else{
            this.forward401(request, response, "token is null");
            return false;
        }
    }

    /**
     * 检测Header里面是否包含Authorization字段，有就进行Token登录认证授权
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("token");
        return authorization != null;
    }

    /**
     * 进行Token登录认证授权
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        JWTToken token = new JWTToken(this.getToken(request, response));
        // 提交给UserRealm进行认证，如果错误他会抛出异常并被捕获
        this.getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 获取Token
     */
    private String getToken(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");
        return token;
    }

    /**
     * 将非法请求转发到/401
     */
    private void forward401(ServletRequest req, ServletResponse resp, String msg) {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) req;
            HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
            // 传递错误信息msg
            req.setAttribute("msg", msg);
           // httpServletRequest.getRequestDispatcher("/401").forward(httpServletRequest, httpServletResponse);
            PrintWriter writer = httpServletResponse.getWriter();
            writer.print("{\"success\":false,\"msg\":\"please login\",\"obj\":null,\"code\":\"401\"}");
            writer.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));


        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me,token,showLoading");

        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}

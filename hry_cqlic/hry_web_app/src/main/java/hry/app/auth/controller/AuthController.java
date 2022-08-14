package hry.app.auth.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.TokenExpiredException;
import hry.app.gt.GTvalidate;
import hry.app.jwt.JWTToken;
import hry.app.jwt.JWTUtil;
import hry.app.jwt.TokenUtil;
import hry.app.thread.UserRedisRunnable;
import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.core.shiro.PasswordHelper;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.redis.common.utils.RedisService;
import hry.util.IpUtil;
import hry.util.LogAop;
import hry.util.ThreadPool;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nl.bitwalker.useragentutils.Browser;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/auth")
@Api(value= "登录认证类", description ="登录认证类",tags = "登录认证类")
public class AuthController {
    // 基础缓存
    private final static Logger log = Logger.getLogger(AuthController.class);

    /**
     * 注册类型属性编辑器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {

        // 系统注入的只能是基本类型，如int，char，String

        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());
        /**
         * 防止XSS攻击，并且带去左右空格功能
         */
        binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
    }

    @Resource
    private RemoteManageService remoteManageService;

    @Resource
    private RedisService redisService;

    @ApiOperation(value = "退出登录接口(必须传token)", httpMethod = "POST", notes = "退出登录接口(必须传token)")
    @PostMapping("/logout")
    @ResponseBody
    public ApiJsonResult logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = JWTUtil.getUsername(token);
        String source = JWTUtil.getClaim(token, "source");
        redisService.delete("JWT:token:" + source + ":sign:" + username);
        //redisService.delete("JWT:token:user:" + username);
        return new ApiJsonResult().setSuccess(true);
    }

    /**
     * 登录ajax方法,邮箱手机登录
     * @param username
     * @param password
     * @param country
     * @return
     */
    @ApiOperation(value = "邮箱/手机登录接口", httpMethod = "POST", notes = "登录成功,返回token值")
    @LogAop
    @PostMapping("/loginService")
    @ResponseBody
    public ApiJsonResult loginService(
            @ApiParam(name = "username", value = "用户名", required = true) @RequestParam("username") String username,
            @ApiParam(name = "password", value = "密码", required = true) @RequestParam("password") String password,
            @ApiParam(name = "country", value = "国家地区", required = true) @RequestParam("country") String country,
            @ApiParam(name = "source", value = "登录来源，电脑端：pc，手机端：app", required = true) @RequestParam(value = "source", required = true) String source,
            @ApiParam(name = "geetest_challenge", value = "极验验证返回参数，source=pc时必传", required = false) @RequestParam(value = "geetest_challenge",required = false) String geetest_challenge,
            @ApiParam(name = "geetest_validate", value = "极验验证返回参数，source=pc时必传", required = false) @RequestParam(value = "geetest_validate", required = false) String geetest_validate,
            @ApiParam(name = "geetest_seccode", value = "极验验证返回参数，source=pc时必传", required = false) @RequestParam(value = "geetest_seccode",required = false) String geetest_seccode,
            HttpServletRequest request) {
        int loginCount = 0; //登录报错次数
        ApiJsonResult jsonResult = new ApiJsonResult();
        // 用户名不能为空
        if (StringUtils.isEmpty(username)) {
            jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
            return jsonResult;
        }
        // 来源不能为空
        if (StringUtils.isEmpty(source)) {
            jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
            return jsonResult;
        }
        // 用户名转小写
        //username = username.toLowerCase();
        source = source.toLowerCase();

        //如果不包含@说明是手机号登录
        if (!username.contains("@")) {
            //国家地区不能为空
            if (StringUtils.isEmpty(country)) {
                jsonResult.setMsg(SpringUtil.diff("country_no_null"));
                return jsonResult;
            }
        }

        // 如果是电脑端，则需要进行极验验证
        if ("pc".equals(source)) {
            //验证极验
            boolean b = GTvalidate.validateGT(request);
            if (!b) {
                jsonResult.setMsg(SpringUtil.diff("gt_valid_fail"));
                return jsonResult;
            }
        }

        String user_name = "";
        try {
            //获取浏览器信息
            String ua = request.getHeader("User-Agent");
            //获取浏览器信息
            Browser browser = Browser.parseUserAgentString(ua);
            //浏览器名称
            String browserName = browser.getName();

            String ip = IpUtil.getIp(request);
            System.out.println("browserName==" + browserName + "  ip====" + ip);
            Long remainingTime = redisService.getKeyTime(username);

            String i = redisService.get("loginErrorCount:" + username);
            if (!StringUtils.isEmpty(i)) {
                loginCount = Integer.parseInt(i);
            }
            if (loginCount >= 5) {
                redisService.setTime("loginErrorCount:" + username, 24 * 60 * 60);
                log.info(username + "|登录报错5次,已禁用24小时!");
                jsonResult.setMsg(SpringUtil.diff("yonghujinyong"));
                return jsonResult;
            }

            UUID uuid = UUID.randomUUID();
            RemoteResult login = remoteManageService.login(username, password, country, uuid.toString());
            if (login != null && login.getSuccess()) {
                User user = (User) login.getObj();

                //登录验证判断
                //safeLoginType：1登录密码,2登录密码加手机，3登录密码加google或者手机 ,默认为1
                Integer safeLoginType = user.getSafeLoginType();
                if (2 == safeLoginType.intValue() || 3 == safeLoginType.intValue()) {
                    // 复杂安全策略登录第一次时加入白名单
                    // 非白名单用户，登录验证方式为复杂的方式
                    // 验证白名单中是否存在该用户，根据用户id和登录ip判断
                    // 返回true,表示第一次登录，返回false，说明已经登录过了
                    if (remoteManageService.isFirstLoginByComplexPwd(user.getCustomerId(), IpUtil.getIp(request))) {
                        remoteManageService.addWhiteList(user.getCustomerId(), IpUtil.getIp(request));
                        if (!authLogin(user, request)) {
                            jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
                            return jsonResult;
                        }
                        try {
                            String auth_login = redisService.get("Auth:login");
                            if (!"true".equals(auth_login)) {
                                //加日志输出
                                System.out.println("::::::::::登录验证判断判断未通过::::::::::");
                                jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
                                return jsonResult;
                            }
                            redisService.delete("Auth:login");
                        } catch (Exception e) {
                            //加日志输出
                            System.out.println("::::::::::登录验证判断判断未通过::::::::::");
                            e.printStackTrace();
                            jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
                            return jsonResult;
                        }
                    }
                }
                user_name = user.getUsername();

                //生成JWT
                PasswordHelper passwordHelper = new PasswordHelper();
                String encryString = passwordHelper.encryString(password, user.getSalt());
                String sign = JWTUtil.sign(user_name, source, encryString);
                // 将token存储到redis中作为刷新token的凭证，并设置key的过期时间作为刷新有效期
                redisService.save("JWT:token:" + source + ":sign:" + user_name, sign, 7*24*60*60);
                redisService.save("JWT:token:user:" + user_name, JSON.toJSONString(user));

                JWTToken token = new JWTToken(sign);
                Subject currentUser = SecurityUtils.getSubject();
                if (!currentUser.isAuthenticated()) {
                    currentUser.login(token);// 验证角色和权限
                    log.info("登录成功.....");
                }

                redisService.delete("loginErrorCount:" + username);
                redisService.delete("gt:" + ip + ":gt_server_status");
                redisService.delete("gt:" + ip + ":userid");
                TokenUtil.updateUser(user);
                log.info(username + "|登录成功!");
                //初始化缓存
                ThreadPool.exe(new UserRedisRunnable(user.getCustomerId().toString()));
                remoteManageService.savaIp(user.getUsername(), ip);

                jsonResult.setSuccess(true);
                jsonResult.setMsg(SpringUtil.diff(login.getMsg()));
                jsonResult.setObj(sign);
                return jsonResult;
            } else if ("1001".equals(login.getCode())) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff(login.getMsg()));
                return jsonResult;
            } else {
                loginCount++;
                redisService.save("loginErrorCount:" + username, String.valueOf(loginCount), 60 * 60);
            }
        } catch (Exception e) {
            if (e.getCause() instanceof TokenExpiredException) {
                redisService.delete("JWT:token:" + source + ":sign:" + user_name);
                //redisService.delete("JWT:token:user:" + user_name);
            }
            e.printStackTrace();
            log.error("token 过期");
        }
        jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
        return jsonResult;
    }
    /**
     * 登录ajax方法,邮箱手机登录（有图形验证码）
     * @param username
     * @param password
     * @param country
     * @return
     */
    @ApiOperation(value = "邮箱/手机登录接口（有图形验证码）", httpMethod = "POST", notes = "登录成功,返回token值")
    @LogAop
    @PostMapping("/loginServiceByImgCode")
    @ResponseBody
    public ApiJsonResult loginServiceByImgCode(
            @ApiParam(name = "username", value = "用户名", required = true) @RequestParam("username") String username,
            @ApiParam(name = "password", value = "密码", required = true) @RequestParam("password") String password,
            @ApiParam(name = "country", value = "国家地区", required = true) @RequestParam("country") String country,
            @ApiParam(name = "source", value = "登录来源，电脑端：pc，手机端：app", required = true) @RequestParam(value = "source", required = true) String source,
            @ApiParam(name = "imgCode", value = "图形验证码", required = true) @RequestParam(value = "imgCode") String imgCode,

            HttpServletRequest request) {
        int loginCount = 0; //登录报错次数
        ApiJsonResult jsonResult = new ApiJsonResult();
        // 用户名不能为空
        if (StringUtils.isEmpty(username)) {
            jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
            return jsonResult;
        }
        // 来源不能为空
        if (StringUtils.isEmpty(source)) {
            jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
            return jsonResult;
        }
        // 用户名转小写
        //username = username.toLowerCase();
        source = source.toLowerCase();

        //如果不包含@说明是手机号登录
        if (!username.contains("@")) {
            //国家地区不能为空
            if (StringUtils.isEmpty(country)) {
                jsonResult.setMsg(SpringUtil.diff("country_no_null"));
                return jsonResult;
            }
        }

        // 图形验证码不能为空
        if (StringUtils.isEmpty(imgCode)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tuxingyanzhengmaweikong"));
            return jsonResult;
        }
        String session_registcode = request.getSession().getAttribute("login").toString();
        // 图形验证码不能为空
        if (StringUtils.isEmpty(session_registcode)) {
            return jsonResult.setSuccess(false).setObj(imgCode).setMsg("yanzhengmayishixiao");//验证码已失效
        }
        if (!imgCode.equalsIgnoreCase(session_registcode)) {
            return jsonResult.setSuccess(false).setObj(imgCode).setMsg(SpringUtil.diff("tx_error"));
        }

        String user_name = "";
        try {
            //获取浏览器信息
            String ua = request.getHeader("User-Agent");
            //获取浏览器信息
            Browser browser = Browser.parseUserAgentString(ua);
            //浏览器名称
            String browserName = browser.getName();

            String ip = IpUtil.getIp(request);
            System.out.println("browserName==" + browserName + "  ip====" + ip);
            Long remainingTime = redisService.getKeyTime(username);

            String i = redisService.get("loginErrorCount:" + username);
            if (!StringUtils.isEmpty(i)) {
                loginCount = Integer.parseInt(i);
            }
            if (loginCount >= 5) {
                redisService.setTime("loginErrorCount:" + username, 24 * 60 * 60);
                log.info(username + "|登录报错5次,已禁用24小时!");
                jsonResult.setMsg(SpringUtil.diff("yonghujinyong"));
                return jsonResult;
            }

            UUID uuid = UUID.randomUUID();
            //
            System.out.println("去登陆===");
            RemoteResult login = remoteManageService.login(username, password, country, uuid.toString());
            if (login != null && login.getSuccess()) {
                User user = (User) login.getObj();

                //登录验证判断
                //safeLoginType：1登录密码,2登录密码加手机，3登录密码加google或者手机 ,默认为1
                Integer safeLoginType = user.getSafeLoginType();
                if (2 == safeLoginType.intValue() || 3 == safeLoginType.intValue()) {
                    // 复杂安全策略登录第一次时加入白名单
                    // 非白名单用户，登录验证方式为复杂的方式
                    // 验证白名单中是否存在该用户，根据用户id和登录ip判断
                    // 返回true,表示第一次登录，返回false，说明已经登录过了
                    if (remoteManageService.isFirstLoginByComplexPwd(user.getCustomerId(), IpUtil.getIp(request))) {
                        remoteManageService.addWhiteList(user.getCustomerId(), IpUtil.getIp(request));
                        if (!authLogin(user, request)) {
                            jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
                            return jsonResult;
                        }
                        try {
                            String auth_login = redisService.get("Auth:login");
                            if (!"true".equals(auth_login)) {
                                //加日志输出
                                System.out.println("::::::::::登录验证判断判断未通过::::::::::");
                                jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
                                return jsonResult;
                            }
                            redisService.delete("Auth:login");
                        } catch (Exception e) {
                            //加日志输出
                            System.out.println("::::::::::登录验证判断判断未通过::::::::::");
                            e.printStackTrace();
                            jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
                            return jsonResult;
                        }
                    }
                }
                user_name = user.getUsername();

                //生成JWT
                PasswordHelper passwordHelper = new PasswordHelper();
                String encryString = passwordHelper.encryString(password, user.getSalt());
                String sign = JWTUtil.sign(user_name, source, encryString);
                // 将token存储到redis中作为刷新token的凭证，并设置key的过期时间作为刷新有效期
                redisService.save("JWT:token:" + source + ":sign:" + user_name, sign, 7*24*60*60);
                redisService.save("JWT:token:user:" + user_name, JSON.toJSONString(user));

                JWTToken token = new JWTToken(sign);
                Subject currentUser = SecurityUtils.getSubject();
                if (!currentUser.isAuthenticated()) {
                    currentUser.login(token);// 验证角色和权限
                    log.info("登录成功.....");
                }

                redisService.delete("loginErrorCount:" + username);
                redisService.delete("gt:" + ip + ":gt_server_status");
                redisService.delete("gt:" + ip + ":userid");
                TokenUtil.updateUser(user);
                log.info(username + "|登录成功!");
                //初始化缓存
                ThreadPool.exe(new UserRedisRunnable(user.getCustomerId().toString()));
                remoteManageService.savaIp(user.getUsername(), ip);

                jsonResult.setSuccess(true);
                jsonResult.setMsg(SpringUtil.diff(login.getMsg()));
                jsonResult.setObj(sign);
                return jsonResult;
            } else if ("1001".equals(login.getCode())) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff(login.getMsg()));
                return jsonResult;
            } else {
                loginCount++;
                redisService.save("loginErrorCount:" + username, String.valueOf(loginCount), 60 * 60);
            }
        } catch (Exception e) {
            if (e.getCause() instanceof TokenExpiredException) {
                redisService.delete("JWT:token:" + source + ":sign:" + user_name);
                //redisService.delete("JWT:token:user:" + user_name);
            }
            e.printStackTrace();
            log.error("token 过期");
        }
        jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
        return jsonResult;
    }

    private boolean authLogin(User user, HttpServletRequest request) {
        if (user.getSafeLoginType() == 3) {
            String googleCheck = redisService.get("login:loginCheck_google" + user.getUsername());
            if (null != googleCheck && "2".equals(googleCheck)) {
                redisService.delete("login:loginCheck_google" + user.getUsername());
            } else {
                return true;
            }
        }
        return true;
    }



}

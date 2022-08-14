package hry.app.user.controller;

import com.alibaba.fastjson.JSONObject;
import hry.app.gt.GTvalidate;
import hry.app.thread.EmailRunnable;
import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.util.properties.PropertiesUtils;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.redis.common.utils.RedisService;
import hry.util.FileUploadUtils;
import hry.util.IpUtil;
import hry.util.ThreadPool;
import hry.util.common.BaseConfUtil;
import hry.util.common.Constant;
import hry.util.common.SpringUtil;
import hry.util.rsa.RSAUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/forgetpwd")
@Api(value = "忘记密码业务处理", description = "忘记密码业务处理", tags = "忘记密码业务处理")
public class ForgetpwdController {

    private static Logger logger = Logger.getLogger(ForgetpwdController.class);

    @Resource
    private RedisService redisService;

    @Resource
    private RemoteManageService remoteManageService;

    /**
     * 进入忘记密码页面，初始化数据
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "进入忘记密码页面，初始化数据", httpMethod = "GET", response = Map.class, notes = "进入忘记密码页面，初始化数据")
    @GetMapping("/toforgetpwd")
    @ResponseBody
    public Map<String, Object> toforgetpwd (
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
        if (!root.isEmpty()) {
            returnMap.put("siteLogo", root.get("siteLogo").toString());
            returnMap.put("SEOTitle", "-" + root.get("SEOTitle").toString());
            returnMap.put("siteCopyright", root.get("siteCopyright"));
        }
        return returnMap;
    }

    /**
     * 邮件找回密码第一步 ：发送修改密码链接到指定邮箱
     *
     * @param request
     */
    @ApiOperation(value = "发送修改密码链接到指定邮箱", httpMethod = "POST", response = ApiJsonResult.class, notes = "发送修改密码链接到指定邮箱")
    @PostMapping("/sendForgetEmail")
    @ResponseBody
    public ApiJsonResult sendForgetEmail (
            @ApiParam(name = "email", value = "邮箱账号", required = true) @RequestParam("email") String email,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "geetest_challenge", value = "极验返回参数", required = false) @RequestParam(value = "geetest_challenge", required = false) String geetest_challenge,
            @ApiParam(name = "geetest_validate", value = "极验返回参数", required = false) @RequestParam(value = "geetest_validate", required = false) String geetest_validate,
            @ApiParam(name = "geetest_seccode", value = "极验返回参数", required = false) @RequestParam(value = "geetest_seccode", required = false) String geetest_seccode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        String ip = IpUtil.getIp(request);

        // 邮箱
        if (StringUtils.isEmpty(email)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tel_is_not_null"));
            return jsonResult;
        }

        if (!StringUtils.isEmpty(geetest_challenge)) {
            //验证极验
            boolean b = GTvalidate.validateGT(request);
            if (!b) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("gt_valid_fail"));
                return jsonResult;
            }
        }

        email = email.trim().replace(" ", "");
        int count = 0;
        String val = redisService.get("ip:forpwd:" + ip + email);
        if (!StringUtils.isEmpty(val)) {
            String num = redisService.get("ip:forpwd:" + ip + email);
            count = Integer.parseInt(num);
        } else {
            redisService.save("ip:forpwd:" + ip + email, "1", 60 * 60 * 24);
        }
        if (count < 5) {
            if (StringUtils.isEmpty(email)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("tel_is_not_null"));
                return jsonResult;
            }
            boolean hasEmail = remoteManageService.hasEmail(email);

            if (!hasEmail) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("userisnull"));
                return jsonResult;
            }
            if (!remoteManageService.isActive(email)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("未激活不可找回");
                return jsonResult;
            }
            String random = RandomStringUtils.random(24, true, true);//安全标识码
            RemoteResult regist = remoteManageService.forget(email, random);
            if (regist != null) {
                if (regist.getSuccess()) {
                    String type = "3";
                    String vueUrl = PropertiesUtils.APP.getProperty("app.vueurl");
                    String url = vueUrl + "/resetPassword?e=" + email + "&code=" + regist.getObj() + "&lang=" + langCode;
                    ThreadPool.exe(new EmailRunnable(email, type, langCode, url));
                    // 发送邮件
                    // 计时，time秒后 找回密码链接失效
                    String config = redisService.get("configCache:all");
                    JSONObject parseObject = JSONObject.parseObject(config);
                    Integer time = parseObject.get("resetPwdTime") == null ? 2 : parseObject.getInteger("resetPwdTime");
                    redisService.save("forget:" + email, random, time * 60);
                    redisService.save("ip:forpwd:" + ip + email, count + 1 + "", 60 * 60 * 24);

                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(SpringUtil.diff("reg_success"));
                    return jsonResult;
                }
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("失败");
                return jsonResult;
            }
            jsonResult.setSuccess(true);
            jsonResult.setMsg(SpringUtil.diff("reg_success"));
            return jsonResult;
        }
        jsonResult.setSuccess(true);
        jsonResult.setMsg("找回加次数过多，请一天后再试");
        return jsonResult;
    }


    /**
     * 邮件找回密码第一步 ：发送修改密码链接到指定邮箱（有图形验证码）
     *
     * @param request
     */
    @ApiOperation(value = "发送修改密码链接到指定邮箱（有图形验证码）", httpMethod = "POST", response = ApiJsonResult.class, notes = "发送修改密码链接到指定邮箱（有图形验证码）")
    @PostMapping("/sendForgetEmailByImgCode")
    @ResponseBody
    public ApiJsonResult sendForgetEmailByImgCode (
            @ApiParam(name = "email", value = "邮箱账号", required = true) @RequestParam("email") String email,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "imgCode", value = "图形验证码", required = true) @RequestParam(value = "imgCode") String imgCode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        String ip = IpUtil.getIp(request);

        // 邮箱
        if (StringUtils.isEmpty(email)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tel_is_not_null"));
            return jsonResult;
        }

        // 图形验证码不能为空
        if (StringUtils.isEmpty(imgCode)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tuxingyanzhengmaweikong"));
            return jsonResult;
        }
        String session_registcode = request.getSession().getAttribute("mobileResetPassword").toString();
        if (StringUtils.isEmpty(session_registcode)) {
            return jsonResult.setSuccess(false).setObj(imgCode).setMsg("验证码已失效");

        }
        if (!imgCode.equalsIgnoreCase(session_registcode)) {
            return jsonResult.setSuccess(false).setObj(imgCode).setMsg(SpringUtil.diff("tx_error"));
        }

        email = email.trim().replace(" ", "");
        int count = 0;
        String val = redisService.get("ip:forpwd:" + ip + email);
        if (!StringUtils.isEmpty(val)) {
            String num = redisService.get("ip:forpwd:" + ip + email);
            count = Integer.parseInt(num);
        } else {
            redisService.save("ip:forpwd:" + ip + email, "1", 60 * 60 * 24);
        }
        if (count < 5) {
            if (StringUtils.isEmpty(email)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("tel_is_not_null"));
                return jsonResult;
            }
            boolean hasEmail = remoteManageService.hasEmail(email);

            if (!hasEmail) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("userisnull"));
                return jsonResult;
            }
            if (!remoteManageService.isActive(email)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("未激活不可找回");
                return jsonResult;
            }
            String random = RandomStringUtils.random(6, false, true);//安全标识码
            RemoteResult regist = remoteManageService.forget(email, random);
            if (regist != null) {
                if (regist.getSuccess()) {
                    String type = "3";
                  //  String vueUrl = PropertiesUtils.APP.getProperty("app.vueurl");
                   // String url = vueUrl + "/resetPassword?e=" + email + "&code=" + regist.getObj() + "&lang=" + langCode;
                 /*   String url = regist.getObj().toString();
                    ThreadPool.exe(new EmailRunnable(email, type, langCode, url));*/
                    ThreadPool.exe(new EmailRunnable(email, "3", langCode, random));
                    // 发送邮件
                    // 计时，time秒后 找回密码链接失效
                    String config = redisService.get("configCache:all");
                    JSONObject parseObject = JSONObject.parseObject(config);
                    Integer time = parseObject.get("resetPwdTime") == null ? 2 : parseObject.getInteger("resetPwdTime");
                    redisService.save("forget:" + email, random, time * 60);
                    redisService.save("ip:forpwd:" + ip + email, count + 1 + "", 60 * 60 * 24);
                    redisService.save("forgetEmail", email);
                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(SpringUtil.diff("sed_success"));
                    return jsonResult;
                }
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMsg("失败");
                return jsonResult;
            }
            jsonResult.setSuccess(true);
            jsonResult.setMsg(SpringUtil.diff("sed_success"));
            return jsonResult;
        }
        jsonResult.setSuccess(true);
        jsonResult.setMsg("找回加次数过多，请一天后再试");
        return jsonResult;
    }


    /**
     * 手机找回密码：修改密码
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "手机找回密码：修改密码", httpMethod = "POST", response = ApiJsonResult.class, notes = "手机找回密码：修改密码")
    @PostMapping("/mobileResetPassword")
    @ResponseBody
    public ApiJsonResult mobileResetPassword (
            @ApiParam(name = "newPassWord", value = "新密码", required = true) @RequestParam("newPassWord") String newPassWord,
            @ApiParam(name = "rePassWord", value = "确认密码", required = true) @RequestParam("rePassWord") String rePassWord,
            @ApiParam(name = "smsCode", value = "手机验证码", required = true) @RequestParam("smsCode") String smsCode,
            @ApiParam(name = "geetest_challenge", value = "极验返回参数", required = false) @RequestParam(value = "geetest_challenge",required = false) String geetest_challenge,
            @ApiParam(name = "geetest_validate", value = "极验返回参数", required = false) @RequestParam(value = "geetest_validate",required = false) String geetest_validate,
            @ApiParam(name = "geetest_seccode", value = "极验返回参数", required = false) @RequestParam(value = "geetest_seccode",required = false) String geetest_seccode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        // 从redis中取手机号
        String country = redisService.get("SMS:forgetCountry");
        String mobile = redisService.get("SMS:forgetMobile");
        // 如果redis过期
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(country)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("chongzhimimashibai"));
            return jsonResult;
        }
        mobile = mobile.trim().replace(" ", "");

        if (!StringUtils.isEmpty(geetest_challenge)) {
            //验证极验
            boolean b = GTvalidate.validateGT(request);
            if (!b) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("gt_valid_fail"));
                return jsonResult;
            }
        }

        Integer resetPasswordCount = 0;
        String i = redisService.get("resetPasswordCount:" + mobile);
        if (!StringUtils.isEmpty(i)) {
            resetPasswordCount = Integer.parseInt(i);
        }
        if (resetPasswordCount >= 5) {
            redisService.setTime("resetPasswordCount:" + mobile, 24 * 60 * 60);
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("smscodeerror5"));
            return jsonResult;
        }
        //密码不能为空
        if (StringUtils.isEmpty(newPassWord) || StringUtils.isEmpty(rePassWord)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
            return jsonResult;
        }

        //两次密码不一致
        if (!newPassWord.equals(rePassWord)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("twopwd_is_diff"));
            return jsonResult;
        }
        //手机验证码不正确
        String sessionSmsCode = redisService.get("SMS:smsForgetMobile:" + mobile);
        if (StringUtils.isEmpty(smsCode) || !smsCode.equals(sessionSmsCode)) {
            resetPasswordCount++;
            redisService.save("resetPasswordCount:" + mobile, String.valueOf(resetPasswordCount), 24 * 60 * 60);
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("shoujiyanzhengmacuowu"));
            return jsonResult;
        }

        RemoteResult result = remoteManageService.updatepwdMobile(country, mobile, newPassWord);
        if (!result.getSuccess()) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("chongzhimimashibai"));
            return jsonResult;
        } else {
            redisService.delete("forgetEmail");
        }
        redisService.delete("loginErrorCount:" + mobile);
        jsonResult.setSuccess(true);
        jsonResult.setMsg(SpringUtil.diff("chongzhimimachenggong"));
        return jsonResult;
    }

    /**
     * 手机找回密码：修改密码（有图形验证码）
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "手机找回密码：修改密码（有图形验证码）", httpMethod = "POST", response = ApiJsonResult.class, notes = "手机找回密码：修改密码（有图形验证码）")
    @PostMapping("/mobileResetPasswordByImgCode")
    @ResponseBody
    public ApiJsonResult mobileResetPasswordByImgCode (
            @ApiParam(name = "newPassWord", value = "新密码", required = true) @RequestParam("newPassWord") String newPassWord,
            @ApiParam(name = "rePassWord", value = "确认密码", required = true) @RequestParam("rePassWord") String rePassWord,
            @ApiParam(name = "smsCode", value = "手机验证码", required = true) @RequestParam("smsCode") String smsCode,
            @ApiParam(name = "imgCode", value = "图形验证码", required = true) @RequestParam(value = "imgCode") String imgCode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        // 从redis中取手机号
        String country = redisService.get("SMS:forgetCountry");
        String mobile = redisService.get("SMS:forgetMobile");
        // 如果redis过期
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(country)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("chongzhimimashibai"));
            return jsonResult;
        }
        mobile = mobile.trim().replace(" ", "");

        // 图形验证码不能为空
        if (StringUtils.isEmpty(imgCode)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tuxingyanzhengmaweikong"));
            return jsonResult;
        }
        String session_registcode =request.getSession().getAttribute("mobileResetPassword").toString();
        if (!imgCode.equalsIgnoreCase(session_registcode)) {
            return jsonResult.setSuccess(false).setObj(imgCode).setMsg(SpringUtil.diff("tx_error"));
        }

        Integer resetPasswordCount = 0;
        String i = redisService.get("resetPasswordCount:" + mobile);
        if (!StringUtils.isEmpty(i)) {
            resetPasswordCount = Integer.parseInt(i);
        }
        if (resetPasswordCount >= 5) {
            redisService.setTime("resetPasswordCount:" + mobile, 24 * 60 * 60);
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("smscodeerror5"));
            return jsonResult;
        }
        //密码不能为空
        if (StringUtils.isEmpty(newPassWord) || StringUtils.isEmpty(rePassWord)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
            return jsonResult;
        }

        //两次密码不一致
        if (!newPassWord.equals(rePassWord)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("twopwd_is_diff"));
            return jsonResult;
        }
        //手机验证码不正确
        String sessionSmsCode = redisService.get("SMS:smsForgetMobile:" + mobile);
        if (StringUtils.isEmpty(smsCode) || !smsCode.equals(sessionSmsCode)) {
            resetPasswordCount++;
            redisService.save("resetPasswordCount:" + mobile, String.valueOf(resetPasswordCount), 24 * 60 * 60);
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("shoujiyanzhengmacuowu"));
            return jsonResult;
        }

        RemoteResult result = remoteManageService.updatepwdMobile(country, mobile, newPassWord);
        if (!result.getSuccess()) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("chongzhimimashibai"));
            return jsonResult;
        } else {
            redisService.delete("forgetEmail");
        }
        redisService.delete("loginErrorCount:" + mobile);
        jsonResult.setSuccess(true);
        jsonResult.setMsg(SpringUtil.diff("chongzhimimachenggong"));
        return jsonResult;
    }
    /**
     * 邮件找回密码第二步：通过邮件点击进入修改密码页面
     *
     * @param request
     * @param code
     * @return
     */
    @ApiOperation(value = "邮件找回密码第二步：通过邮件点击进入修改密码页面", httpMethod = "GET", response = Map.class, notes = "邮件找回密码第二步：通过邮件点击进入修改密码页面")
    @GetMapping("/toEmailResetPassword")
    @ResponseBody
    public ApiJsonResult toEmailResetPassword (
            @ApiParam(name = "newPassWord", value = "新密码", required = true) @RequestParam("newPassWord") String newPassWord,
            @ApiParam(name = "rePassWord", value = "确认密码", required = true) @RequestParam("rePassWord") String rePassWord,
            @ApiParam(name = "imgCode", value = "图形验证码", required = true) @RequestParam(value = "imgCode") String imgCode,
            @ApiParam(name = "email", value = "邮箱账号", required = true) @RequestParam("email") String email,
            @ApiParam(name = "code", value = "email回调的时候的验证码", required = true) @RequestParam("code") String code,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult=new ApiJsonResult();
        // 图形验证码不能为空
        if (StringUtils.isEmpty(imgCode)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tuxingyanzhengmaweikong"));
            return jsonResult;
        }
        String session_registcode = request.getSession().getAttribute("mobileResetPassword").toString();
        if (StringUtils.isEmpty(session_registcode)) {
            return jsonResult.setSuccess(false).setObj(imgCode).setMsg("验证码已失效");

        }
        if (!imgCode.equalsIgnoreCase(session_registcode)) {
            return jsonResult.setSuccess(false).setObj(imgCode).setMsg(SpringUtil.diff("tx_error"));
        }
        //密码不能为空
        if (StringUtils.isEmpty(newPassWord) || StringUtils.isEmpty(rePassWord)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
            return jsonResult;
        }
        //两次密码不一致
        if (!newPassWord.equals(rePassWord)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("twopwd_is_diff"));
            return jsonResult;
        }
        // 从redis中取邮箱账号
        String email1 = redisService.get("forget:"+email);
        // 如果redis过期
        if (StringUtils.isEmpty(email1)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("chongzhimimashibai"));
            return jsonResult;
        }
        RemoteResult regist = remoteManageService.emailvail(email, code);
        if(!regist.getSuccess()){
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("email_code_error"));
            return jsonResult;
        }
        RemoteResult result = remoteManageService.updatepwdemail(newPassWord, email);
        if (!result.getSuccess()) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("chongzhimimashibai"));
            return jsonResult;
        } else {
            redisService.delete("forget");
        }
        //只删除邮箱账号，如果是手机号登陆，邮箱解禁，
        redisService.delete("loginErrorCount:" + email.toLowerCase());
        jsonResult.setSuccess(true);
        jsonResult.setMsg(SpringUtil.diff("chongzhimimachenggong"));
        return jsonResult;
    }

    /**
     * 邮件找回密码第三步 ：修改密码方法
     *
     * @param request
     * @return
     */
   /* @ApiOperation(value = "邮件找回密码第三步 ：修改密码方法", httpMethod = "GET", response = ApiJsonResult.class, notes = "邮件找回密码第三步 ：修改密码方法")
    @GetMapping("/emailResetPassword")
    @ResponseBody
    public ApiJsonResult emailResetPassword (
            @ApiParam(name = "password", value = "新密码", required = true) @RequestParam("password") String password,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();

        // 从redis中取邮箱账号
        String email = redisService.get("forgetEmail");
        // 如果redis过期
        if (StringUtils.isEmpty(email)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("chongzhimimashibai"));
            return jsonResult;
        }

        RemoteResult result = remoteManageService.updatepwdemail(password, email);
        if (!result.getSuccess()) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("chongzhimimashibai"));
            return jsonResult;
        } else {
            redisService.delete("forgetEmail");
        }
        //只删除邮箱账号，如果是手机号登陆，邮箱解禁，
        redisService.delete("loginErrorCount:" + email.toLowerCase());
        jsonResult.setSuccess(true);
        jsonResult.setMsg(SpringUtil.diff("chongzhimimachenggong"));
        return jsonResult;
    }*/

}

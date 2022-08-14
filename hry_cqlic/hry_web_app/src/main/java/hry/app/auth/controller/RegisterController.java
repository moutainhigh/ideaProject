package hry.app.auth.controller;

import hry.app.gt.GTvalidate;
import hry.app.thread.EmailRunnable;
import hry.bean.ApiJsonResult;
import hry.ico.remote.RemoteIcoService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.redis.common.utils.RedisService;
import hry.util.DrawPictureUtil;
import hry.util.StringUtil;
import hry.util.ThreadPool;
import hry.util.UUIDUtil;
import hry.util.common.BaseConfUtil;
import hry.util.common.Constant;
import hry.util.common.SpringUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reg")
@Api(value= "注册认证类", description ="注册认证类" ,tags = "注册认证类")
public class RegisterController {
    private static Logger logger = Logger.getLogger(RegisterController.class);
    private static  int emailCodeLength=6;//邮箱注册6位随机数字
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
    private RedisService redisService;

    @Resource
    private RemoteManageService remoteManageService;
    @Resource
    private RemoteIcoService remoteIcoService;

    /**
     * 注册页面数据初始化
     * @return
     */
    @ApiOperation(value = "注册页面数据初始化", httpMethod = "POST", notes = "注册页面数据初始化")
    @PostMapping("/initRegPageData")
    @ResponseBody
    public Map<String, Object> initRegPageData(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        // 查询后台参数配置
        Map<String, Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
        if (!root.isEmpty()) {
            returnMap.put("regreg", root.get("reg"));
            returnMap.put("SEOTitle", "-" + root.get("SEOTitle"));
            returnMap.put("isOpenLanguage", root.get("isOpenLanguage"));
            returnMap.put("siteLogo", root.get("siteLogo"));
            returnMap.put("siteCopyright", root.get("siteCopyright"));
        }
        return returnMap;
    }

    /**
     * 邮箱注册发送验证码
     *
     * @param request
     */
    @ApiOperation(value = "邮箱注册发送验证码", httpMethod = "POST", notes = "邮箱注册发送验证码")
    @RequestMapping("/regSendEmail")
    @ResponseBody
    public ApiJsonResult regSendEmail(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "email", value = "邮箱", required = true) @RequestParam("email") String email,
            HttpServletRequest request) {
        ApiJsonResult jsonResult=new ApiJsonResult();
        try {
            email = email.trim().replace(" ", "");
            String getRedisValue = redisService.get("reSendEmail:" + email);
            if (!"".equals(getRedisValue) && getRedisValue != null) {
                Long keyTime = redisService.getKeyTime("reSendEmail:" + email);
                jsonResult.setMsg(SpringUtil.diff("youxianghaiweichongzhi") + keyTime + SpringUtil.diff("miaohouchongxinfasong"));
                return jsonResult;
            } else {
               // String registObjCode= UUIDUtil.getUUID();//生成注册随机码

                String registObjCode = RandomStringUtils.random(emailCodeLength, false, true);
                ThreadPool.exe(new EmailRunnable(email, "2", langCode, registObjCode));
                //sendReigstEmail(langCode, email, registObjCode);
                redisService.save("reSendEmail:" + email, registObjCode, 300);
                jsonResult.setSuccess(true);
                jsonResult.setMsg(SpringUtil.diff("youjianfasongchenggong"));
                return jsonResult;
            }
        } catch (Exception e) {
            logger.error("发送邮件错误：" + e.getMessage());
        }
        jsonResult.setMsg(SpringUtil.diff("fail"));
        return jsonResult;
    }

    /**
     * 邮箱注册提交
     * @param request
     */
    @ApiOperation(value = "邮箱注册提交", httpMethod = "POST", notes = "邮箱注册提交")
    @PostMapping("/emailRegist")
    @ResponseBody
    public ApiJsonResult emailRegist(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "email", value = "邮箱账号", required = true) @RequestParam("email") String email,
            @ApiParam(name = "password", value = "密码", required = true) @RequestParam("password") String password,
            @ApiParam(name = "referralCode", value = "邀请码", required = true) @RequestParam("referralCode") String referralCode,
            @ApiParam(name = "geetest_challenge", value = "极验验证返回参数", required = false) @RequestParam(value = "geetest_challenge",required = false) String geetest_challenge,
            @ApiParam(name = "geetest_validate", value = "极验验证返回参数", required = false) @RequestParam(value = "geetest_validate",required = false) String geetest_validate,
            @ApiParam(name = "geetest_seccode", value = "极验验证返回参数", required = false) @RequestParam(value = "geetest_seccode",required = false) String geetest_seccode,
            HttpServletRequest request ) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        // 邮箱不能为空
        if (StringUtils.isEmpty(email)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("youxiangbunengweikong"));
            return jsonResult;
        }
        // 密码不能为空
        if (StringUtils.isEmpty(password)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
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

        // 判断邀请码是否存在
        if (!"".equals(referralCode) && referralCode != null) {
            if (!referralCode.startsWith("AGENT")) {
                RemoteResult selectPhone = remoteManageService.selectAgent(referralCode);
                if (!selectPhone.getSuccess()) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("dailisbucunzai"));
                    return jsonResult;
                }
            }
        }
        try {
            Boolean lock = redisService.lock(email + "emailRegist");
            if (lock) {
                RemoteResult regist = remoteManageService.regist(email, password, referralCode, langCode);
                if (regist != null) {
                    if (regist.getSuccess()) {
                        String registObjCode = regist.getObj().toString();
                        sendReigstEmail(langCode, email, registObjCode);
                        jsonResult.setSuccess(true);
                        jsonResult.setMsg(SpringUtil.diff("reg_success"));
                        redisService.unLock(email + "emailRegist");
                        return jsonResult;
                    } else {
                        jsonResult.setSuccess(false);
                        jsonResult.setMsg(SpringUtil.diff(regist.getMsg()));
                        redisService.unLock(email + "emailRegist");
                        return jsonResult;
                    }
                }
                redisService.unLock(email + "emailRegist");
            }
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("qingqiupinf"));
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("注册方法远程调用出错：" + e.getMessage());
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("zhuceshibai"));
        return jsonResult;
    }



    /**
     * 邮箱注册提交（有图形验证码）
     * @param request
     */
    @ApiOperation(value = "邮箱注册提交（有图形验证码）", httpMethod = "POST", notes = "邮箱注册提交（有图形验证码）")
    @PostMapping("/emailRegistByImgCode")
    @ResponseBody
    public ApiJsonResult emailRegistByImgCode(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "email", value = "邮箱账号", required = true) @RequestParam("email") String email,
            @ApiParam(name = "password", value = "密码", required = true) @RequestParam("password") String password,
            @ApiParam(name = "referralCode", value = "邀请码", required = false) @RequestParam(value = "referralCode",required = false) String referralCode,
            @ApiParam(name = "registObjCode", value = "邮箱验证码", required = true) @RequestParam(value = "registObjCode",required = true) String registObjCode,
            @ApiParam(name = "imgCode", value = "图形验证码", required = true) @RequestParam(value = "imgCode") String imgCode,
            HttpServletRequest request ) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        // 邮箱不能为空
        if (StringUtils.isEmpty(email)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("youxiangbunengweikong"));
            return jsonResult;
        }
        // 密码不能为空
        if (StringUtils.isEmpty(password)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
            return jsonResult;
        }
        // 图形验证码不能为空
        if (StringUtils.isEmpty(imgCode)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tuxingyanzhengmaweikong"));
            return jsonResult;
        }
        String session_registcode =request.getSession().getAttribute("mobileRegist")==null?null:request.getSession().getAttribute("mobileRegist").toString();
        if (StringUtils.isEmpty(session_registcode)) {
            return jsonResult.setSuccess(false).setObj(imgCode).setMsg("验证码已失效");
        }
        if (!imgCode.equalsIgnoreCase(session_registcode)) {
            return jsonResult.setSuccess(false).setObj(imgCode).setMsg(SpringUtil.diff("tx_error"));
        }
        String getRedisValue = redisService.get("reSendEmail:" + email);
        if(StringUtil.isNull(getRedisValue)){
                if(!getRedisValue.equals(registObjCode)){
                    return jsonResult.setSuccess(false).setMsg("regEmailCodeError");//邮箱验证码不正确
                }
        }else{
            return jsonResult.setSuccess(false).setMsg("youxiangyanzhengmayiguoqi");//邮箱验证码已过期
        }
        // 判断邀请码是否存在
        if (!"".equals(referralCode) && referralCode != null) {
            if (!referralCode.startsWith("AGENT")) {
                RemoteResult selectPhone = remoteManageService.selectAgent(referralCode);
                if (!selectPhone.getSuccess()) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("dailisbucunzai"));
                    return jsonResult;
                }
            }
        }
        try {
            Boolean lock = redisService.lock(email + "emailRegist");
            if (lock) {
                RemoteResult regist = remoteManageService.regist(email, password, referralCode, langCode);
                if (regist != null) {
                    if (regist.getSuccess()) {
                        /*String registObjCode = regist.getObj().toString();
                        sendReigstEmail(langCode, email, registObjCode);*/
                        jsonResult.setSuccess(true);
                        jsonResult.setMsg(SpringUtil.diff("reg_success"));
                        redisService.unLock(email + "emailRegist");
                        return jsonResult;
                    } else {
                        jsonResult.setSuccess(false);
                        jsonResult.setMsg(SpringUtil.diff(regist.getMsg()));
                        redisService.unLock(email + "emailRegist");
                        return jsonResult;
                    }
                }
                redisService.unLock(email + "emailRegist");
            }
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("qingqiupinf"));
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("注册方法远程调用出错：" + e.getMessage());
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("zhuceshibai"));
        return jsonResult;
    }

    /**
     * 发送注册邮箱
     * @param langCode
     * @param email
     * @param registObjCode
     */
    private void sendReigstEmail (String langCode, String email, String registObjCode) {
        String vueUrl = PropertiesUtils.APP.getProperty("app.vueurl");
        //String url = vueUrl + "/reg/activation/" + email + "/" + registObjCode + "/" + langCode;
        String url = vueUrl + "/successmail?e=" + email + "&code=" + registObjCode + "&lang=" + langCode;
        // 发送邮件
        String type = "1";
        ThreadPool.exe(new EmailRunnable(email, type, langCode, url));
    }

    /**
     * 邮件发送成功激活页面数据初始化
     * @return
     */
    @ApiOperation(value = "邮件发送成功激活页面数据初始化", httpMethod = "POST", notes = "邮件发送成功激活页面数据初始化")
    @RequestMapping("/toSendRegistEmail")
    @ResponseBody
    public Map<String, Object> toSendRegistEmail(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "email", value = "邮箱", required = true) @RequestParam("email") String email,
            @ApiParam(name = "registObjCode", value = "注册码", required = false) @RequestParam(value = "registObjCode", required = false) String registObjCode,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("email", email);
        returnMap.put("registObjCode", registObjCode);
        Map<String, Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
        if (root != null) {
            returnMap.put("SEOTitle", "-" + root.get("SEOTitle"));
            returnMap.put("siteLogo", root.get("siteLogo"));
            returnMap.put("siteCopyright", root.get("siteCopyright"));
        }
        return returnMap;
    }

    /**
     * 重新发送邮件
     *
     * @param request
     */
    @ApiOperation(value = "重新发送邮件", httpMethod = "POST", notes = "重新发送邮件")
    @RequestMapping("/reSendEmail")
    @ResponseBody
    public ApiJsonResult reSendEmail(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "email", value = "邮箱", required = true) @RequestParam("email") String email,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        //String registObjCode = remoteManageService.getEmailCode(email);

        // 邮箱不能为空
        if (StringUtils.isEmpty(email)) {
            jsonResult.setMsg(SpringUtil.diff("tel_is_not_null"));
            return jsonResult;
        }
      /*  // 邮箱账号不存在
        if (StringUtils.isEmpty(registObjCode)) {
            jsonResult.setMsg(SpringUtil.diff("tel_is_not_null"));
            return jsonResult;
        }*/
        try {
            email = email.trim().replace(" ", "");
            String getRedisValue = redisService.get("reSendEmail:" + email);
            if (!"".equals(getRedisValue) && getRedisValue != null) {
                Long keyTime = redisService.getKeyTime("reSendEmail:" + email);
                jsonResult.setMsg(SpringUtil.diff("youxianghaiweichongzhi") + keyTime + SpringUtil.diff("miaohouchongxinfasong"));
                return jsonResult;
            } else {
                String registObjCode = RandomStringUtils.random(emailCodeLength, false, true);
                sendReigstEmail(langCode, email, registObjCode);
                redisService.save("reSendEmail:" + email, email, 300);
                jsonResult.setSuccess(true);
                jsonResult.setMsg(SpringUtil.diff("youjianfasongchenggong"));
                return jsonResult;
            }
        } catch (Exception e) {
            logger.error("发送邮件错误：" + e.getMessage());
        }
        jsonResult.setMsg(SpringUtil.diff("fail"));
        return jsonResult;
    }

    /**
     * 发送邮件成功页面数据初始化
     * @return
     */
    @ApiOperation(value = "发送邮件成功页面数据初始化", httpMethod = "POST", notes = "发送邮件成功页面数据初始化")
    @PostMapping("/emailRegistSuc")
    @ResponseBody
    public Map<String, Object> emailRegistSuc(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "email", value = "邮箱账号", required = true) @RequestParam("email") String email,
            @ApiParam(name = "registObjCode", value = "邮箱注册码", required = false) @RequestParam(value = "registObjCode", required = false) String registObjCode,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("email", email);
        returnMap.put("registObjCode", registObjCode);
        Map<String, Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
        if (root != null) {
            returnMap.put("SEOTitle", "-" + root.get("SEOTitle"));
            returnMap.put("siteLogo", root.get("siteLogo"));
            returnMap.put("siteCopyright", root.get("siteCopyright"));
        }
        return returnMap;
    }

    /**
     * 注册激活页面
     *
     * @param email
     * @return
     */
    @ApiOperation(value = "注册激活页面", httpMethod = "GET", notes = "注册激活页面")
    @GetMapping("/activation")
    @ResponseBody
    public Map<String, Object> activation(
            @ApiParam(name = "email", value = "邮箱", required = true) @RequestParam("email") String email,
            @ApiParam(name = "emailCode", value = "邮箱激活码", required = true) @RequestParam("emailCode") String emailCode,
            @ApiParam(name = "langCode", value = "语种", required = false) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            RemoteResult regist = remoteManageService.activation(emailCode);
            if (regist != null) {
                if (regist.getSuccess()) {
                    returnMap.put("message", SpringUtil.diff("jihuochenggong"));
                    returnMap.put("email", email);
                    return returnMap;
                }
            }
        } catch (Exception e) {
            logger.error("注册方法远程调用出错");
        }
        Map<String, Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
        if (root != null) {
            returnMap.put("SEOTitle", "-" + root.get("SEOTitle"));
            returnMap.put("siteLogo", root.get("siteLogo"));
            returnMap.put("siteCopyright", root.get("siteCopyright"));
        }
        returnMap.put("message", SpringUtil.diff("jihuoshibai"));
        return returnMap;
    }

    /**
     * 手机注册提交方法
     * @param request
     */
    @ApiOperation(value = "手机注册提交方法", httpMethod = "POST", notes = "手机注册提交方法")
    @PostMapping("/mobileRegist")
    @ResponseBody
    public ApiJsonResult registServiceMobile(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "mobile", value = "手机号", required = true) @RequestParam("mobile") String mobile,
            @ApiParam(name = "country", value = "国家/区号", required = true) @RequestParam("country") String country,
            @ApiParam(name = "password", value = "密码", required = true) @RequestParam("password") String password,
            @ApiParam(name = "registSmsCode", value = "手机验证码", required = true) @RequestParam("registSmsCode") String registSmsCode,
            @ApiParam(name = "referralCode", value = "邀请码", required = true) @RequestParam("referralCode") String referralCode,
            @ApiParam(name = "geetest_challenge", value = "极验验证返回参数", required = false) @RequestParam(value = "geetest_challenge",required = false) String geetest_challenge,
            @ApiParam(name = "geetest_validate", value = "极验验证返回参数", required = false) @RequestParam(value = "geetest_validate",required = false) String geetest_validate,
            @ApiParam(name = "geetest_seccode", value = "极验验证返回参数", required = false) @RequestParam(value = "geetest_seccode",required = false) String geetest_seccode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        // 手机号不能为空
        if (StringUtils.isEmpty(mobile)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("telphone_is_not_null"));
            return jsonResult;
        }
        // 密码不能为空
        if (StringUtils.isEmpty(password)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
            return jsonResult;
        }

        int telCodeError = 0;
        String telCodeErrorStr = redisService.get("SMS:telCodeError" + mobile);
        if (!StringUtils.isEmpty(telCodeErrorStr)) {
            telCodeError = new Integer(telCodeError);
        }
        if (telCodeError > 5) {
            redisService.delete("SMS:registSmsCode" + mobile);
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("smscodeerror"));
            return jsonResult;
        }
        // 手机验证码是否正确
        String session_registSmsCode = redisService.get("SMS:registSmsCode:" + mobile);
        if (!registSmsCode.equalsIgnoreCase(session_registSmsCode)) {
            telCodeError++;
            redisService.save("SMS:telCodeError" + mobile, new Integer(telCodeError).toString());
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tel_code_error"));
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

        // 邀请码是否存在
        if (!"".equals(referralCode) && referralCode != null) {
            if (!referralCode.startsWith("AGENT")) {
                RemoteResult selectPhone = remoteManageService.selectAgent(referralCode);
                if (!selectPhone.getSuccess()) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("dailisbucunzai"));
                    return jsonResult;
                }
            }
        }
        try {
            // 手机号去空格
            mobile = mobile.trim().replace(" ", "");
            RemoteResult regist = remoteManageService.registMobile(mobile, password, referralCode, country, langCode);

            if (regist != null) {
                if (regist.getSuccess()) {
                    // 删除验证码
                    redisService.delete("SMS:registSmsCode" + mobile);
                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(SpringUtil.diff("reg_success"));
                    return jsonResult;
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff(regist.getMsg()));
                    return jsonResult;
                }
            }
        } catch (Exception e) {
            logger.error("注册方法远程调用出错");
        }
        jsonResult.setSuccess(false);
        return jsonResult;
    }

    /**
     * 手机注册提交方法（有图形验证码）
     * @param request
     */
    @ApiOperation(value = "手机注册提交方法（有图形验证码）", httpMethod = "POST", notes = "手机注册提交方法（有图形验证码）")
    @PostMapping("/mobileRegistByImgCode")
    @ResponseBody
    public ApiJsonResult registServiceMobileByImgCode(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "mobile", value = "手机号", required = true) @RequestParam("mobile") String mobile,
            @ApiParam(name = "country", value = "国家/区号", required = true) @RequestParam("country") String country,
            @ApiParam(name = "password", value = "密码", required = true) @RequestParam("password") String password,
            @ApiParam(name = "registSmsCode", value = "手机验证码", required = true) @RequestParam("registSmsCode") String registSmsCode,
            @ApiParam(name = "referralCode", value = "邀请码", required = false) @RequestParam(value = "referralCode", required = false) String referralCode,
            @ApiParam(name = "imgCode", value = "图形验证码", required = true) @RequestParam( "imgCode") String imgCode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        // 手机号不能为空
        if (StringUtils.isEmpty(mobile)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("telphone_is_not_null"));
            return jsonResult;
        }
        // 密码不能为空
        if (StringUtils.isEmpty(password)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
            return jsonResult;
        }

        int telCodeError = 0;
        String telCodeErrorStr = redisService.get("SMS:telCodeError" + mobile);
        if (!StringUtils.isEmpty(telCodeErrorStr)) {
            telCodeError = new Integer(telCodeError);
        }
        if (telCodeError > 5) {
            redisService.delete("SMS:registSmsCode" + mobile);
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("smscodeerror"));
            return jsonResult;
        }
        // 手机验证码是否正确
        String session_registSmsCode = redisService.get("SMS:registSmsCode:" + mobile);
        if (!registSmsCode.equalsIgnoreCase(session_registSmsCode)) {
            telCodeError++;
            redisService.save("SMS:telCodeError" + mobile, new Integer(telCodeError).toString());
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tel_code_error"));
            return jsonResult;
        }

        // 图形验证码不能为空
        if (StringUtils.isEmpty(imgCode)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tuxingyanzhengmaweikong"));
            return jsonResult;
        }
        String session_registcode = request.getSession().getAttribute("mobileRegist")==null?null:request.getSession().getAttribute("mobileRegist").toString();
        if (StringUtils.isEmpty(session_registcode)) {
            return jsonResult.setSuccess(false).setObj(imgCode).setMsg("验证码已失效");

        }
        if (!imgCode.equalsIgnoreCase(session_registcode)) {
            return jsonResult.setSuccess(false).setObj(imgCode).setMsg(SpringUtil.diff("tx_error"));
        }

        // 邀请码是否存在
        if (!"".equals(referralCode) && referralCode != null) {
            if (!referralCode.startsWith("AGENT")) {
                RemoteResult selectPhone = remoteManageService.selectAgent(referralCode);
                if (!selectPhone.getSuccess()) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("dailisbucunzai"));
                    return jsonResult;
                }
            }
        }
        try {
            // 手机号去空格
            mobile = mobile.trim().replace(" ", "");
            RemoteResult regist = remoteManageService.registMobile(mobile, password, referralCode, country, langCode);
            if (regist != null) {
                if (regist.getSuccess()) {

                    // 删除验证码
                    redisService.delete("SMS:registSmsCode" + mobile);
                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(SpringUtil.diff("reg_success"));
                    return jsonResult;
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff(regist.getMsg()));
                    return jsonResult;
                }
            }
        } catch (Exception e) {
            logger.error("注册方法远程调用出错");
        }
        jsonResult.setSuccess(false);
        return jsonResult;
    }


    /**
     * 获取图形验证码
     * @param use
     * @return
     */
    @ApiOperation(value = "获取图形验证码", httpMethod = "GET", notes = "获取图形验证码")
    @GetMapping("/getCode")
    @ResponseBody
    public void getCode(
            @ApiParam(name = "use", value = "用途(用户注册  mobileRegist； 找回密码 mobileResetPassword；邮箱手机登录  login)", required = true) @RequestParam("use") String use,
            HttpServletRequest request, HttpServletResponse response) {
        DrawPictureUtil drawPictureUtil = new DrawPictureUtil(use, 100, 30);
        drawPictureUtil.darw(request, response);
    }

    /**
     * 检查用户是否已注册
     * @param use
     * @return
     */
    @ApiOperation(value = "检查用户是否已注册", httpMethod = "GET", notes = "检查用户是否已注册")
    @GetMapping("/checkPhoneReg")
    @ResponseBody
    public ApiJsonResult checkPhoneReg(
            @ApiParam(name = "mobile", value = "检查用户是否已注册", required = true) @RequestParam(value = "mobile",required = true)  String mobile) {
        ApiJsonResult result=new ApiJsonResult();
        //判断手机号是否被注册
        RemoteResult remoteResult = remoteManageService.selectPhone(mobile);
        if (remoteResult.getSuccess()) {
            result.setSuccess(false);
            result.setMsg(SpringUtil.diff("user_reg"));
        }else{
            result.setSuccess(true);
        }
        return result;
    }

}

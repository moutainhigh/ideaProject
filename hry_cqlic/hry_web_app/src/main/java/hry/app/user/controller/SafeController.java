package hry.app.user.controller;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.app.remote.RemoteUserService;
import hry.app.thread.EmailRunnable;
import hry.bean.ApiJsonResult;
import hry.core.annotation.CommonLog;
import hry.core.shiro.PasswordHelper;
import hry.manage.remote.RemoteAppTransactionManageService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.AppCustomer;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.manage.remote.model.UserInfo;
import hry.redis.common.utils.RedisService;
import hry.util.*;
import hry.util.common.SpringUtil;
import hry.util.sms.SmsParam;
import hry.util.sms.SmsSendUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import nl.bitwalker.useragentutils.Browser;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/safe")
@Api(value = "个人中心-安全认证(必须传token)", description = "个人中心-安全认证(必须传token)", tags = "个人中心-安全认证(必须传token)")
public class SafeController {

    private final static Logger log = Logger.getLogger(SafeController.class);

    // 系统注入的只能是基本类型，如int，char，String

    /**
     * 注册类型属性编辑器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder (ServletRequestDataBinder binder) {
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
    private RemoteAppTransactionManageService remoteAppTransactionManageService;

    /**
     * 个人中心-安全认证-实名认证-保存用户实名认证信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-安全认证-实名认证-保存用户实名认证信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-安全认证-实名认证-保存用户实名认证信息")
    @PostMapping("/user/saveUserIdentifyInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult saveUserIdentifyInfo (
            @ApiParam(name = "files", value = "身份证或港澳身份证认证图片信息", required = true) @RequestParam("file") MultipartFile[] files,
            @ApiParam(name = "type", value = "实名认证类型，1：中国大陆地区 2：其他国家及地区", required = true) @RequestParam("type") String type,
            @ApiParam(name = "trueName", value = "名字", required = true) @RequestParam("trueName") String trueName,
            @ApiParam(name = "sex", value = "性别，男、女，没有为空", required = true) @RequestParam("sex") String sex,
            @ApiParam(name = "surname", value = "姓氏", required = true) @RequestParam("surname") String surname,
            @ApiParam(name = "country", value = "国家地区信息，没有为空", required = true) @RequestParam("country") String country,
            @ApiParam(name = "cardType", value = "证件类型 0：身份证 1：护照", required = true) @RequestParam("cardType") String cardType,
            @ApiParam(name = "cardId", value = "身份证或护照ID", required = true) @RequestParam("cardId") String cardId,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        // 获取当前登录用户信息
        User user = TokenUtil.getUser(request);
        if (StringUtils.isEmpty(country)) {
            country = "+86";
        }
        try {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                String names = file.getOriginalFilename();
                if (names != null && (names.endsWith("jpg") || names.endsWith("png") || names.endsWith("gif") || names.endsWith("bmp") || names.endsWith("JPG") || names.endsWith("PNG") || names.endsWith("GIF") || names.endsWith("BMP"))) {
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("picture_error"));
                    return jsonResult;
                }

                InputStream inputStream = file.getInputStream();
                //验证流
                String fileType = FileType.getFileType(inputStream);
                if (fileType != null && (fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("png") || fileType.equalsIgnoreCase("gif") || fileType.equalsIgnoreCase("bmp"))) {
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("picture_error"));
                    return jsonResult;
                }
            }
            // 上传图片
            String[] pathImg = FileUploadUtils.upload(files);
            String config = redisService.get("configCache:all");
            JSONObject parseObject = JSONObject.parseObject(config);
            RemoteResult realname = remoteManageService.identityVerification(user.getUsername(), trueName, sex, surname, country, cardType, cardId, pathImg, type, parseObject.get("language_code").toString());
            if (realname != null) {
                if (realname.getSuccess()) {
                    user.setStates(1);
                    TokenUtil.updateUser(user);
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff(realname.getMsg()));
                    return jsonResult;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonResult.setSuccess(true);
        jsonResult.setMsg(SpringUtil.diff("tijiaochenggongshenhe"));
        return jsonResult;
    }

    @ApiOperation(value = "个人中心-安全设置-实名认证-认证完成", httpMethod = "POST", notes = "个人中心-安全设置-实名认证-认证完成")
    @PostMapping("/user/realinfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult<UserInfo> realinfo(
            @ApiParam(name = "langCode", value = "语种", required = false) @RequestParam(value = "langCode", required = false) String langCode,
            HttpServletRequest request) {
        ApiJsonResult<UserInfo> jsonResult = new ApiJsonResult<UserInfo>();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            User selectByTel = remoteManageService.selectByCustomerId(user.getCustomerId());
            if (selectByTel.getStates() == 1 || selectByTel.getStates() == 2) {
                RemoteResult result = remoteManageService.getPersonInfo(user.getUserCode());
                if (result != null && result.getSuccess()) {
                    UserInfo userInfo = (UserInfo) result.getObj();
                    if ("1".equals(userInfo.getType())) {
                        if ("en".equals(langCode)) {
                            userInfo.setPapersType("ID Card");
                        }
                    } else if ("2".equals(userInfo.getType())) {
                        if ("en".equals(langCode)) {
                            userInfo.setPapersType("Passport");
                        }
                    }
                    jsonResult.setSuccess(true);
                    jsonResult.setObj(userInfo);
                    return jsonResult;
                }
            }
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
        return jsonResult;
    }
    /**
     * 实名认证 提交-app端
     * @return
     */
    @ApiOperation(value = "实名认证 提交-app端", httpMethod = "POST", response = ApiJsonResult.class, notes = " 海内海外类型type(海内 ：0,海外:1 ),姓 firstName，名 lastName，性别 sex，国家country，证件类型cardType ，证件号码 cardId ,三张图片 正面  ，反面 ，手持， img1,img2img3")
    @PostMapping("/user/apprealname")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult apprealname (
            @ApiParam(name = "type", value = "实名认证类型，1：中国大陆地区 2：其他国家及地区", required = true) @RequestParam("type") String type,
            @ApiParam(name = "surname", value = "姓氏", required = true) @RequestParam("surname") String surname,
            @ApiParam(name = "trueName", value = "名字", required = true) @RequestParam("trueName") String trueName,
            @ApiParam(name = "cardId", value = "身份证或护照ID", required = true) @RequestParam("cardId") String cardId,
            @ApiParam(name = "sex", value = "性别，男、女，没有为空", required = true) @RequestParam("sex") String sex,
            @ApiParam(name = "country", value = "国家地区信息，没有为空", required = true) @RequestParam("country") String country,
            @ApiParam(name = "cardType", value = "证件类型 0：身份证 1：护照", required = true) @RequestParam("cardType") String cardType,
            @ApiParam(name = "img1", value = "img1", required = true) @RequestParam("img1") String img1,
            @ApiParam(name = "img2", value = "img2", required = true) @RequestParam("img2") String img2,
            @ApiParam(name = "img3", value = "img3", required = true) @RequestParam("img3") String img3,
            HttpServletRequest request) throws UnsupportedEncodingException {
        ApiJsonResult jsonResult = new ApiJsonResult();
        try {
            MultipartFile file1 = Base64Util.base64ToMultipart(img1, "img1");
            MultipartFile file2 = Base64Util.base64ToMultipart(img2, "img2");
            MultipartFile file3 = Base64Util.base64ToMultipart(img3, "img3");
            MultipartFile[] files = {file1, file2, file3};
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                String names = file.getOriginalFilename();
                if (names != null && (names.endsWith("jpg") || names.endsWith("png") || names.endsWith("gif") || names.endsWith("bmp") || names.endsWith("JPG") || names.endsWith("PNG") || names.endsWith("GIF") || names.endsWith("BMP"))) {
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("picture_error"));
                    return jsonResult;
                }
                InputStream inputStream = file.getInputStream();
                String fileType = FileType.getFileType(inputStream);
                if (fileType != null && (fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("png") || fileType.equalsIgnoreCase("gif") || fileType.equalsIgnoreCase("bmp"))) {
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("picture_error"));
                    return jsonResult;
                }
            }
            User user = TokenUtil.getUser(request);
            if (user != null) {
                String[] pathImg = FileUploadUtils.upload(files);
                String config = redisService.get("configCache:all");
                if (!StringUtils.isEmpty(config)) {
                    JSONObject parseObject = JSONObject.parseObject(config);
                    RemoteResult realname = remoteManageService.identityVerification(user.getUsername(), trueName, sex, surname, country, cardType, cardId, pathImg, type, parseObject.get("language_code").toString());
                    if (realname != null) {
                        if (realname.getSuccess()) {
                            user.setStates(1);
                            TokenUtil.updateUser(user);

                            jsonResult.setSuccess(true);
                            jsonResult.setMsg(SpringUtil.diff("shimingtijiaochenggong"));//实名认证提交成功
                            return jsonResult;
                        } else {
                            jsonResult.setSuccess(false);
                            jsonResult.setMsg(SpringUtil.diff(realname.getMsg()));
                            return jsonResult;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("实名认证远程调用出错");
            e.printStackTrace();
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("tijiaoshibai"));//提交失败
        return jsonResult;
    }

    /**
     * 实名认证 提交-app端
     * @return
     */
    @ApiOperation(value = "实名认证 提交-app端", httpMethod = "POST", response = ApiJsonResult.class, notes = " 海内海外类型type(海内 ：0,海外:1 ),姓 firstName，名 lastName，性别 sex，国家country，证件类型cardType ，证件号码 cardId ,三张图片 正面  ，反面 ，手持， img1,img2img3")
    @PostMapping("/user/apprealnameold")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult apprealnameold (
            @ApiParam(name = "type", value = "实名认证类型，1：中国大陆地区 2：其他国家及地区", required = true) @RequestParam("type") String type,
            @ApiParam(name = "surname", value = "姓氏", required = true) @RequestParam("surname") String surname,
            @ApiParam(name = "trueName", value = "名字", required = true) @RequestParam("trueName") String trueName,
            @ApiParam(name = "cardId", value = "身份证或护照ID", required = true) @RequestParam("cardId") String cardId,
            @ApiParam(name = "sex", value = "性别，男、女，没有为空", required = true) @RequestParam("sex") String sex,
            @ApiParam(name = "country", value = "国家地区信息，没有为空", required = true) @RequestParam("country") String country,
            @ApiParam(name = "cardType", value = "证件类型 0：身份证 1：护照", required = true) @RequestParam("cardType") String cardType,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        try {
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (multipartResolver.isMultipart(request)) {
                log.error("ss");
            }
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile img1 = multipartRequest.getFile("img1");
            MultipartFile img2 = multipartRequest.getFile("img2");
            MultipartFile img3 = multipartRequest.getFile("img3");
            MultipartFile[] files = {img1, img2, img3};
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                String names = file.getOriginalFilename();
                if (names != null && (names.endsWith("jpg") || names.endsWith("png") || names.endsWith("gif") || names.endsWith("bmp") || names.endsWith("JPG") || names.endsWith("PNG") || names.endsWith("GIF") || names.endsWith("BMP"))) {
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("picture_error"));
                    return jsonResult;
                }
                InputStream inputStream = file.getInputStream();
                String fileType = FileType.getFileType(inputStream);
                if (fileType != null && (fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("png") || fileType.equalsIgnoreCase("gif") || fileType.equalsIgnoreCase("bmp"))) {
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("picture_error"));
                    return jsonResult;
                }
            }
            User user = TokenUtil.getUser(request);
            if (user != null) {
                String[] pathImg = FileUploadUtils.upload(files);
                String config = redisService.get("configCache:all");
                if (!StringUtils.isEmpty(config)) {
                    JSONObject parseObject = JSONObject.parseObject(config);
                    RemoteResult realname = remoteManageService.identityVerification(user.getUsername(), trueName, sex, surname, country, cardType, cardId, pathImg, type, parseObject.get("language_code").toString());
                    if (realname != null) {
                        if (realname.getSuccess()) {
                            user.setStates(1);
                            TokenUtil.updateUser(user);

                            jsonResult.setSuccess(true);
                            jsonResult.setMsg(SpringUtil.diff("shimingtijiaochenggong"));//实名认证提交成功
                            return jsonResult;
                        } else {
                            jsonResult.setSuccess(false);
                            jsonResult.setMsg(SpringUtil.diff(realname.getMsg()));
                            return jsonResult;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("实名认证远程调用出错");
            e.printStackTrace();
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("tijiaoshibai"));//提交失败
        return jsonResult;
    }

    /**
     * 二次验证通用方法
     *
     * @return
     */
    @ApiOperation(value = "二次验证通用方法", httpMethod = "POST", response = ApiJsonResult.class, notes = "二次验证通用方法")
    @RequestMapping("/sencodVail")
    @ResponseBody
    public ApiJsonResult sencodVail (
            @ApiParam(name = "type", value = "二次验证类型（个人中心-我要提币：type=3;登录：type=1）", required = true) @RequestParam("type") String type,
            @ApiParam(name = "langCode", value = "语种", required = false) @RequestParam(value = "langCode", required = false) String langCode,
            @ApiParam(name = "username", value = "用户名，type=1", required = false) @RequestParam(value = "username", required = false) String username,
            @ApiParam(name = "password", value = "密码，type=1", required = false) @RequestParam(value = "password", required = false) String password,
            @ApiParam(name = "country", value = "国家地区，type=1", required = false) @RequestParam(value = "country", required = false) String country,
            @ApiParam(name = "oldPassWord", value = "旧密码，type=2", required = false) @RequestParam(value = "oldPassWord", required = false) String oldPassWord,
            @ApiParam(name = "newPassWord", value = "新密码，type=2", required = false) @RequestParam(value = "newPassWord", required = false) String newPassWord,
            @ApiParam(name = "reNewPassWord", value = "确认新密码，type=2", required = false) @RequestParam(value = "reNewPassWord", required = false) String reNewPassWord,
            @ApiParam(name = "coinType", value = "币种类型，type=3", required = false) @RequestParam(value = "coinType", required = false) String coinType,
            @ApiParam(name = "currencyType", value = "货币类型，type=3", required = false) @RequestParam(value = "currencyType", required = false) String currencyType,
            @ApiParam(name = "btcNum", value = "充值金额，type=3", required = false) @RequestParam(value = "btcNum", required = false) String btcNum,
            @ApiParam(name = "btcKey", value = "钱包地址，type=3", required = false) @RequestParam(value = "btcKey", required = false) String btcKey,
            @ApiParam(name = "accountPw", value = "资金密码，type=3", required = false) @RequestParam(value = "accountPw", required = false) String accountPw,
            @ApiParam(name = "verifyCode", value = "手机验证码，type=4", required = false) @RequestParam(value = "verifyCode", required = false) String verifyCode,
            @ApiParam(name = "mobile", value = "手机号，type=4", required = false) @RequestParam(value = "mobile", required = false) String mobile,
            @ApiParam(name = "codes", value = "谷歌验证码，type=5", required = false) @RequestParam(value = "codes", required = false) String codes,
            @ApiParam(name = "PassWord", value = "密码，type=5", required = false) @RequestParam(value = "PassWord", required = false) String PassWord,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        int loginCount = 0; //登录报错次数
        if ("1".equals(type)) {// 登录二次认证
            // 用户名不能为空
            if (StringUtils.isEmpty(username)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
                return jsonResult;
            }
            // 用户名转小写
            username = username.toLowerCase();

            //未开启极验验证码-暂不开启极验
            /*if(!"true".equals(PropertiesUtils.APP.getProperty("geetest_open"))) {
                // 图形验证码不能为空
                if (StringUtils.isEmpty(loginCode)) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("tx_is_not_null"));
                    return jsonResult;
                }
            }*/

            //如果不包含@说明是手机号登录
            if (!username.contains("@")) {
                // 国家地区不能为空
                if (StringUtils.isEmpty(country)) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("country_no_null"));
                    return jsonResult;
                }
            }
            try {
                //获取浏览器信息
                String ua = request.getHeader("User-Agent");
                //获取浏览器信息
                Browser browser = Browser.parseUserAgentString(ua);
                //浏览器名称
                String browserName = browser.getName();

                String ip = IpUtil.getIp(request);
                log.error("browserName==" + browserName + "  ip====" + ip);
                String i = redisService.get("loginErrorCount:" + username);//获取账号登录次数
                if (!StringUtils.isEmpty(i)) {
                    loginCount = Integer.parseInt(i);
                }
                if (loginCount >= 5) {//账号登录报错5次 放进redis禁用0.5小时
                    redisService.setTime("loginErrorCount:" + username, 30 * 60);
                    log.error(username + "|登录报错5次,已禁用24小时!");
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("yonghujinyong"));
                    return jsonResult;
                }

                // 生成tokenid
                UUID uuid = UUID.randomUUID();
                RemoteResult login = remoteManageService.login(username, password, country, uuid.toString());

                if (login != null && login.getSuccess()) {
                    User user = (User) login.getObj();
                    if (user != null) {
                        jsonResult.setSuccess(true);
                        jsonResult.setObj(user);
                        return jsonResult;
                    } else {
                        jsonResult.setSuccess(false);
                        jsonResult.setObj(user);
                        return jsonResult;
                    }
                } else {
                    if (login.getCode() != null && "000".equals(login.getCode())) {
                        User user = (User) login.getObj();
                        String sendSmsCode = "";
                        if (user.getMobile() != null) {
                            SmsParam smsParam = new SmsParam();
                            smsParam.setHryMobilephone(user.getMobile());
                            smsParam.setHrySmstype(SmsSendUtil.IPANDBROWER);
                            sendSmsCode = SmsSendUtil.sendSmsCode(smsParam);
                        } else {
                            sendSmsCode = RandomStringUtils.random(6, false, true);
                        }
                        // 发送邮件
                        StringBuffer sb = new StringBuffer();
                        sb.append(SpringUtil.diff("yanzhengma") + ":" + sendSmsCode);
                        ThreadPool.exe(new EmailRunnable(user.getUsername(), sb.toString(), type, langCode));
                        jsonResult.setSuccess(false);
                        jsonResult.setCode("000");
                        jsonResult.setObj(login.getObj());
                        return jsonResult;
                    }

                    loginCount++;
                    redisService.save("loginErrorCount:" + username, String.valueOf(loginCount), 30 * 60);
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff(login.getMsg()));
                    jsonResult.setObj(login.getObj());
                    return jsonResult;
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("login_nameorpwd_erro"));
            return jsonResult;
        } else if ("2".equals(type)) {
            if (StringUtils.isEmpty(oldPassWord)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("oldpwd_no_null"));
                return jsonResult;
            }
            if (StringUtils.isEmpty(newPassWord)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("newpwd_no_null"));
                return jsonResult;
            }
            if (StringUtils.isEmpty(reNewPassWord)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("repeatpwd_no_null"));
                return jsonResult;
            }
            if (oldPassWord.equals(newPassWord)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("newandold_no_null"));
                return jsonResult;
            }
            if (!newPassWord.equals(reNewPassWord)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("twopwd_is_diff"));
                return jsonResult;
            }

            User user = TokenUtil.getUser(request);
            RemoteResult result = remoteManageService.setVailPassWord(user.getUsername(), oldPassWord);
            if (result != null) {
                if (result.getSuccess()) {
                    if (user != null) {
                        jsonResult.setSuccess(true);
                        jsonResult.setObj(user);
                        return jsonResult;
                    } else {
                        jsonResult.setSuccess(false);
                        jsonResult.setObj(user);
                        return jsonResult;
                    }
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff(result.getMsg()));
                    return jsonResult;
                }
            }

        } else if ("3".equals(type)) {
            // 提币交易
            User user = TokenUtil.getUser(request);
            User selectByTel = remoteManageService.selectByCustomerId(user.getCustomerId());

            //验证交易密码
            PasswordHelper passwordHelper = new PasswordHelper();
            String encryAccountPw = passwordHelper.encryString(accountPw, user.getSalt());
            if (!encryAccountPw.equals(user.getAccountPassWord())) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("mimacuowu"));
                return jsonResult;
            }

            if (selectByTel != null) {
                try {
                    String userName = selectByTel.getUsername();
                    RemoteResult remoteResult = remoteAppTransactionManageService.getOrdervail(user, coinType, null, "", btcNum, currencyType, userName, btcKey);
                    if (remoteResult.getSuccess()) {
                        jsonResult.setSuccess(true);
                        jsonResult.setObj(selectByTel);
                        return jsonResult;
                    } else {
                        jsonResult.setSuccess(false);
                        jsonResult.setMsg(SpringUtil.diff(remoteResult.getMsg()));
                        return jsonResult;
                    }
                } catch (Exception e) {
                    jsonResult.setSuccess(false);
                    return jsonResult;
                }
            }
            jsonResult.setSuccess(false);
            return jsonResult;
        } else if ("4".equals(type)) {
            // 地区截取
            User user = TokenUtil.getUser(request);
            RemoteResult regist = remoteManageService.regphone(mobile);
            if (!regist.getSuccess()) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("phonechongfu"));
                return jsonResult;

            }
            if (StringUtils.isEmpty(verifyCode)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("tel_code_is_not_null"));
                return jsonResult;
            }
            String session_pwSmsCode = redisService.get("SMS:smsphone:" + mobile);
            if (!verifyCode.equals(session_pwSmsCode)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("tel_code_error"));
                return jsonResult;
            }
            jsonResult.setSuccess(true);
            jsonResult.setObj(user);
            return jsonResult;
        } else if ("5".equals(type)) {
            User user = TokenUtil.getUser(request);
            String GoogleKey = user.getGoogleKey();
            if (StringUtils.isEmpty(codes)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("gugeyanzhengusnull"));
                return jsonResult;
            }
            if (StringUtils.isEmpty(PassWord)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
                return jsonResult;
            }
            // 查看密码
            RemoteResult result = remoteManageService.setVailPassWord(user.getMobile(), PassWord);

            if (!result.getSuccess()) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("mimacuowu"));
                return jsonResult;
            }
            long code = Long.parseLong(codes);
            log.error("cococooc=" + code);
            log.error("savedSecret=" + GoogleKey);

            long t = System.currentTimeMillis();
            GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
            boolean r = ga.check_code(GoogleKey, code, t);
            if (!r) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("gugeyanzhengshibai"));
                return jsonResult;
            }
            jsonResult.setSuccess(true);
            jsonResult.setObj(user);
            return jsonResult;
        } else if ("6".equals(type)) {
            User user = TokenUtil.getUser(request);
            jsonResult.setSuccess(true);
            jsonResult.setObj(user);
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        return jsonResult;
    }

    /**
     * 个人中心-安全中心-设置安全策略
     * 参数 type
     * 参数 value
     * @param request
     * @return
     */
    @CommonLog(name = "设置安全策略")
    @ApiOperation(value = "个人中心-安全中心-设置安全策略", httpMethod = "POST", notes = "个人中心-安全中心-设置安全策略")
    @PostMapping("/user/setSecurity")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult<User> setSecurity(
            @ApiParam(name = "type", value = "验证类型，登录验证：login；提现验证：tixian", required = true) @RequestParam("type") String type,
            @ApiParam(name = "value", value = "验证方式的选项值", required = true) @RequestParam("value") String value,
            HttpServletRequest request) {
        ApiJsonResult<User> jsonResult = new ApiJsonResult<User>();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            if (StringUtils.isEmpty(type)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("yichang"));
                return jsonResult;
            }
            if (StringUtils.isEmpty(value)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("yichang"));
                return jsonResult;
            }

            //保存安全策略
            RemoteResult result = remoteManageService.setSecurity(type, Integer.valueOf(value), user.getCustomerId());
            if (result != null && result.getSuccess()) {
                if ("login".equals(type)) { // 登录安全策略
                    user.setSafeLoginType(Integer.valueOf(value));
                }
                if ("tixian".equals(type)) { // 提现
                    user.setSafeTixianType(Integer.valueOf(value));
                }
                if ("trade".equals(type)) { // 交易
                    user.setSafeTradeType(Integer.valueOf(value));
                }
                jsonResult.setSuccess(true);
                jsonResult.setObj(user);
                jsonResult.setMsg(SpringUtil.diff("success"));
                return jsonResult;
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("error"));
                return jsonResult;
            }
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 个人中心-安全设置-绑定/更换邮箱
     * @param verifyCode 邮箱验证码
     * @param loginPwd 登录密码
     * @param request
     * @return email
     */
    @CommonLog(name = "邮箱认证")
    @ApiOperation(value = "个人中心-安全设置-绑定/更换邮箱", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-安全设置-绑定/更换邮箱")
    @PostMapping("/user/setEmail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult setEmail(
            @ApiParam(name = "verifyCode", value = "邮箱验证码", required = true) @RequestParam("verifyCode") String verifyCode,
            @ApiParam(name = "loginPwd", value = "登录密码", required = true) @RequestParam("loginPwd") String loginPwd,
            @ApiParam(name = "email", value = "邮箱账号", required = true) @RequestParam("email") String email,
            @ApiParam(name = "accountPassWord", value = "交易密码", required = true) @RequestParam("accountPassWord") String accountPassWord,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            // 邮件验证码不能为空
            if (StringUtils.isEmpty(verifyCode)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("email_code_is_not_null"));
                return jsonResult;
            }

            // 邮箱不能为空
            if (StringUtils.isEmpty(email)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("email_is_not_null"));
                return jsonResult;
            }

            //密码不能为空
            if (StringUtils.isEmpty(loginPwd)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
                return jsonResult;
            }

            //判断邮箱是否被认证过
            if (remoteManageService.hasEmail(email)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("phonechongfu"));
                return jsonResult;
            } else {
                RemoteResult swapEmail = remoteManageService.swapEmail(user.getCustomerId(), email, loginPwd);
                if (!swapEmail.getSuccess()) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff(swapEmail.getMsg()));
                    return jsonResult;
                }

                String session_code = redisService.get("Valid:" + email + ":" + "setEmailCode");
                //判断验证码是否正确
                if (!verifyCode.equals(session_code)) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("email_code_error"));
                    return jsonResult;
                }
                //验证交易密码
                PasswordHelper passwordHelper = new PasswordHelper();
                String encryAccountPw = passwordHelper.encryString(accountPassWord, user.getSalt());
                if (!encryAccountPw.equals(user.getAccountPassWord())) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("mimacuowu"));
                    return jsonResult;
                }
                //保存邮箱认证
                RemoteResult setPhone = remoteManageService.setEmail(email, user.getCustomerId(), loginPwd);
                if (setPhone != null && setPhone.getSuccess()) {
                    //更新session
                    user.setNickName(email);
                    user.setEmail(email);
                    user.setHasEmail(1);
                    TokenUtil.updateUser(user);
                    redisService.delete("Valid:" + email + ":" + "setEmailCode");
                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(SpringUtil.diff("bindsuccess"));
                    return jsonResult;
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("binderror"));
                    return jsonResult;
                }
            }
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 个人中心-安全设置-绑定/更换手机号
     * @param verifyCode
     * @param mobile
     * @param country
     * @param passWord
     * @param request
     * @return
     */
    @CommonLog(name = "手机认证")
    @ApiOperation(value = "个人中心-安全设置-绑定/更换手机号", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-安全设置-绑定/更换手机号")
    @PostMapping("/user/setPhone")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult setPhone(
            @ApiParam(name = "verifyCode", value = "短信验证码", required = true) @RequestParam("verifyCode") String verifyCode,
            @ApiParam(name = "mobile", value = "手机号", required = true) @RequestParam("mobile") String mobile,
            @ApiParam(name = "country", value = "手机国际区号", required = true) @RequestParam("country") String country,
            @ApiParam(name = "passWord", value = "登录密码", required = true) @RequestParam("passWord") String passWord,
            @ApiParam(name = "accountPassWord", value = "交易密码", required = true) @RequestParam("accountPassWord") String accountPassWord,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            if (StringUtils.isEmpty(passWord)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
                return jsonResult;
            }

            // 短信验证码不能为空
            if (StringUtils.isEmpty(verifyCode)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("tel_code_is_not_null"));
                return jsonResult;
            }
            // 手机号不能为空
            if (StringUtils.isEmpty(mobile)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("telphone_is_not_null"));
                return jsonResult;
            }
            // 地区不能为空
            if (StringUtils.isEmpty(country)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("country_no_null"));
                return jsonResult;
            }

            RemoteResult result = remoteManageService.setVailPassWord(user.getUsername(), passWord);
            if (result != null) {
                if (result.getSuccess()) {
                    //判断手机号是否被认证过
                    String ip = IpUtil.getIp(request);
                    if (remoteManageService.hasMobile(country, mobile, ip)) {
                        jsonResult.setSuccess(false);
                        jsonResult.setMsg(SpringUtil.diff("phonechongfu"));
                        return jsonResult;
                    } else {
                        //获得手机验证码
                        String session_pwSmsCode = redisService.get("SMS:setPhone:" + mobile);
                        //判断验证码是否正确
                        if (!verifyCode.equals(session_pwSmsCode)) {
                            jsonResult.setSuccess(false);
                            jsonResult.setMsg(SpringUtil.diff("tel_code_error"));
                            return jsonResult;
                        }
                        if (StringUtil.isNull(user.getMobile())) {
                            if (!StringUtil.isNull(accountPassWord)) {
                                jsonResult.setSuccess(false);
                                jsonResult.setMsg(SpringUtil.diff("jypwd_no_null"));
                                return jsonResult;
                            }
                            //验证交易密码
                            PasswordHelper passwordHelper = new PasswordHelper();
                            String encryAccountPw = passwordHelper.encryString(accountPassWord, user.getSalt());
                            if (!encryAccountPw.equals(user.getAccountPassWord())) {
                                jsonResult.setSuccess(false);
                                jsonResult.setMsg(SpringUtil.diff("mimacuowu"));
                                return jsonResult;
                            }
                        }
                        //保存手机认证
                        RemoteResult setPhone = remoteManageService.setPhone(country, mobile, user.getCustomerId());
                        if (setPhone != null && setPhone.getSuccess()) {
                            user.setPhoneState(1);
                            user.setMobile(mobile);
                            TokenUtil.updateUser(user);

                            jsonResult.setSuccess(true);
                            jsonResult.setMsg(SpringUtil.diff("phonesuccess"));
                            return jsonResult;
                        } else {
                            jsonResult.setSuccess(false);
                            jsonResult.setMsg(SpringUtil.diff("phonechongfu"));
                            return jsonResult;
                        }
                    }
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff(result.getMsg()));
                    return jsonResult;
                }
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("pwd_is_not_null"));
                return jsonResult;
            }
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
    }

    /**
     * 个人中心-手机二次认证（弹窗）
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-手机二次认证（弹窗）", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-手机二次认证（弹窗）")
    @RequestMapping("/phoneAuth")
    @ResponseBody
    public ApiJsonResult phoneAuth (
            @ApiParam(name = "verifyCode", value = "短信验证码", required = true) @RequestParam("verifyCode") String verifyCode,
            @ApiParam(name = "mobile", value = "手机号", required = true) @RequestParam("mobile") String second_mobile,
            @ApiParam(name = "country", value = "手机国际区号", required = true) @RequestParam("country") String second_country,
            @ApiParam(name = "type", value = "防止非法修改参数验证（我要提币传：btcget）", required = true) @RequestParam("type") String second_type,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();

        //验证码不能为空
        if (StringUtils.isEmpty(verifyCode)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tel_code_is_not_null"));
            return jsonResult;
        }

        boolean hasMobile = remoteManageService.hasMobile(second_country, second_mobile, IpUtil.getIp(request));
        //判断手机号是否存在
        if (!hasMobile) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("telcode_is_null"));
            return jsonResult;
        }

        String session_pwSmsCode = redisService.get("SMS:smsSecondMobile:" + second_mobile);
        if (!verifyCode.equals(session_pwSmsCode)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("tel_code_error"));
            return jsonResult;
        }
        redisService.save("Auth:" + second_type, "true");

        jsonResult.setSuccess(true);
        jsonResult.setMsg(SpringUtil.diff("phonesuccess"));
        return jsonResult;
    }

    /**
     * 个人中心-安全设置-修改登录密码
     *
     * @return
     */
    @CommonLog(name = "修改登录密码")
    @ApiOperation(value = "个人中心-安全设置-修改登录密码", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-安全设置-修改登录密码")
    @PostMapping("/user/setpwd")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult setpwd(
            @ApiParam(name = "oldPassWord", value = "原始密码", required = true) @RequestParam("oldPassWord") String oldPassWord,
            @ApiParam(name = "newPassWord", value = "新密码", required = true) @RequestParam("newPassWord") String newPassWord,
            @ApiParam(name = "reNewPassWord", value = "确认新密码", required = true) @RequestParam("reNewPassWord") String reNewPassWord,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        if (StringUtils.isEmpty(oldPassWord)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("oldpwd_no_null"));
            return jsonResult;
        }
        if (StringUtils.isEmpty(newPassWord)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("newpwd_no_null"));
            return jsonResult;
        }
        if (oldPassWord.equals(newPassWord)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("newandold_no_null"));
            return jsonResult;
        }
        if (StringUtils.isEmpty(reNewPassWord)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("repeatpwd_no_null"));
            return jsonResult;
        }
        if (!newPassWord.equals(reNewPassWord)) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("twopwd_is_diff"));
            return jsonResult;
        }

        User user = TokenUtil.getUser(request);
        if (user != null) {
            try {
                RemoteResult result = remoteManageService.setpw(user.getUsername(), oldPassWord, newPassWord);
                if (result != null) {
                    if (result.getSuccess()) {
                        user.setPassDate(new Date());
                        Object obj = result.getObj();
                        user.setPassword(obj.toString());
                        TokenUtil.updateUser(user);
                        jsonResult.setSuccess(true);
                        return jsonResult;
                    } else {
                        jsonResult.setSuccess(false);
                        jsonResult.setMsg(SpringUtil.diff(result.getMsg()));
                        return jsonResult;
                    }
                } else {
                    jsonResult.setSuccess(false);
                    return jsonResult;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("yichang"));
                return jsonResult;
            }
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
    }

    /**
     * 个人中心-安全设置-谷歌认证-生成谷歌验证码
     *
     * @return
     */
    @ApiOperation(value = "个人中心-安全设置-谷歌认证-生成谷歌验证码", httpMethod = "POST", notes = "个人中心-安全设置-谷歌认证-生成谷歌验证码")
    @PostMapping("/user/createGoogleValidCode")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult<AppCustomer> createGoogleValidCode(
            HttpServletRequest request) {
        ApiJsonResult<AppCustomer> jsonResult = new ApiJsonResult<AppCustomer>();
        String secret = GoogleAuthenticatorUtil.generateSecretKey();
        User user = TokenUtil.getUser(request);
        if (user != null) {
           // System.out.println("ssss=" + secret);
            String qrBarcodeURL = GoogleAuthenticatorUtil.getQRBarcodeURL("testuser", "testhost", secret);
          //  System.out.println("ffff=" + qrBarcodeURL);
            String company = PropertiesUtils.APP.getProperty("app.googleCompany");

            String config = redisService.get("configCache:all");
            JSONObject parseObject = JSONObject.parseObject(config);
            company = parseObject.get("enName") == null ? company : parseObject.get("enName").toString();

            AppCustomer a = new AppCustomer();
            a.setGoogleKey(secret);
            a.setCompany(company);
            if (!com.alibaba.druid.util.StringUtils.isEmpty(user.getEmail())) {
                a.setUserName(user.getEmail());
            } else {
                a.setUserName(user.getMobile());
            }
            jsonResult.setSuccess(true);
            jsonResult.setObj(a);
            return jsonResult;
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
    }

    /**
     * 个人中心-安全设置-谷歌认证-启用谷歌认证
     * @param codes
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-安全设置-谷歌认证-启用谷歌认证", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-安全设置-谷歌认证-启用谷歌认证")
    @RequestMapping("/user/googleValid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult googleValid(
            @ApiParam(name = "codes", value = "谷歌验证码", required = true) @RequestParam("codes") String codes,
            @ApiParam(name = "savedSecret", value = "密文", required = true) @RequestParam("savedSecret") String savedSecret,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            if (user.getGoogleState() != 0) {
                jsonResult.setSuccess(false);
                jsonResult.setCode("error");
                jsonResult.setMsg(SpringUtil.diff("yirenzheng"));
                return jsonResult;
            }
            long code = Long.parseLong(codes);
            long t = System.currentTimeMillis();
            GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
            boolean r = ga.check_code(savedSecret, code, t);
            if (!r) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("gugeyanzhengshibai"));
                return jsonResult;
            } else {
                RemoteResult result = remoteManageService.sendgoogle(user.getUserCode(), savedSecret);
                if (result.getSuccess()) {
                    user.setGoogleState(1);
                    user.setGoogleKey(savedSecret);
                    TokenUtil.updateUser(user);
                }
                jsonResult.setSuccess(true);
                jsonResult.setMsg(SpringUtil.diff("gugeyanzhengsuccess"));
                return jsonResult;
            }
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
    }

    /**
     * 个人中心-谷歌二次验证（弹窗）
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-谷歌二次验证（弹窗）", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-谷歌二次验证（弹窗）")
    @RequestMapping("/googleAuth")
    @ResponseBody
    public ApiJsonResult googleAuth(
            @ApiParam(name = "userCode", value = "用户code", required = true) @RequestParam("userCode") String userCode,
            @ApiParam(name = "code", value = "谷歌验证码", required = true) @RequestParam("code") String code,
            @ApiParam(name = "type", value = "防止非法修改参数验证（我要提币传：btcget, 登录传：login）", required = true) @RequestParam("type") String second_type,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = remoteManageService.getUserByUserCode(userCode);
        if (user.getGoogleKey() == null) {
            user = TokenUtil.getUser(request);
        }
        long lcode = Long.parseLong(code);
        long t = System.currentTimeMillis();
        GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
        boolean r = ga.check_code(user.getGoogleKey(), lcode, t);
        if (!r) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("gugeyanzhengshibai"));
            return jsonResult;
        } else {
            if (com.alibaba.dubbo.common.utils.StringUtils.isNotEmpty(second_type)) {
                checkAuth(user, second_type, request);
            }
            // 在谷歌二次验证时，不需要添加到白名单，需要用复杂安全策略登录过一次后才添加
            redisService.save("Auth:" + second_type, "true");
            jsonResult.setSuccess(true);
            jsonResult.setMsg(SpringUtil.diff("gugeyanzhengsuccess"));
            return jsonResult;
        }
    }

    /**
     * 认证定制化操作
     *
     * @param user
     * @param type
     * @param request
     */
    private void checkAuth(User user, String type, HttpServletRequest request) {
        //登陆验证
        if (type.equals("login")) {
            redisService.save("login:loginCheck_google" + user.getUsername(), "2");
        }
    }

    /**
     * 解除谷歌认证
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-安全设置-解除谷歌认证", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-安全设置-解除谷歌认证")
    @PostMapping("/user/jcgoogle")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult jcgoogle(
            @ApiParam(name = "PassWord", value = "用户登录密码", required = true) @RequestParam("PassWord") String PassWord,
            @ApiParam(name = "codes", value = "谷歌验证码", required = true) @RequestParam("codes") String codes,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            String GoogleKey = user.getGoogleKey();
            if (StringUtils.isEmpty(PassWord)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("loginpwd_no_null"));
                return jsonResult;
            }

            // 查看密码
            RemoteResult result = remoteManageService.setVailPassWord(user.getUsername(), PassWord);

            if (!result.getSuccess()) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("mimacuowu"));
                return jsonResult;
            }
            long code = Long.parseLong(codes);
            long t = System.currentTimeMillis();
            GoogleAuthenticatorUtil ga = new GoogleAuthenticatorUtil();
            boolean r = ga.check_code(GoogleKey, code, t);
            if (!r) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("gugejiechuerror"));
                return jsonResult;
            } else {
                RemoteResult jcgoogle = remoteManageService.jcgoogle(user.getUsername(), GoogleKey);
                if (jcgoogle.getSuccess()) {
                    user.setGoogleState(0);
                    user.setPassDate(new Date());
                    user.setGoogleKey(null);
                    user.setSafeLoginType(1);
                    user.setSafeTixianType(1);
                    user.setSafeTradeType(1);
                    TokenUtil.updateUser(user);

                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(SpringUtil.diff("gugejiechusuccess"));
                    return jsonResult;
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(SpringUtil.diff("gugejiechuerror"));
                    return jsonResult;
                }
            }
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
    }

    /**
     * 个人中心-安全设置-设置交易密码提交
     *
     * @return
     */
    @CommonLog(name = "设置交易密码提交")
    @ApiOperation(value = "个人中心-安全设置-设置交易密码提交", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-安全设置-设置交易密码提交")
    @PostMapping("/user/setapwsubmit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult setapwsubmit (
            @ApiParam(name = "accountPassWord", value = "资金密码", required = true) @RequestParam("accountPassWord") String accountPassWord,
            @ApiParam(name = "reaccountPassWord", value = "确认资金密码", required = true) @RequestParam("reaccountPassWord") String reaccountPassWord,
            @ApiParam(name = "accountpwSmsCode", value = "短信验证码", required = true) @RequestParam("accountpwSmsCode") String accountpwSmsCode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            if (StringUtils.isEmpty(accountPassWord)) {//交易密码不能为空
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("jypwd_no_null"));
                return jsonResult;
            }
            if (StringUtils.isEmpty(reaccountPassWord)) {//确认密码不能为空
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("okpwd_no_null"));
                return jsonResult;
            }

            if (!accountPassWord.equals(reaccountPassWord)) {//两次密码不一致
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("twopwd_is_diff"));
                return jsonResult;
            }
            if (StringUtils.isEmpty(accountpwSmsCode)) {//手机验证码不能为空
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("tel_code_is_not_null"));
                return jsonResult;
            }
            String session_accountpwSmsCode = redisService.get("Valid:accountpwSmsCode" + user.getMobile());
            if (!accountpwSmsCode.equals(session_accountpwSmsCode)) {//手机验证码不正确
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("tel_code_error"));
                return jsonResult;
            }

            try {
                RemoteResult result = remoteManageService.setapw(user.getCustomerId(), accountPassWord);
                if (result != null) {
                    if (result.getSuccess()) {
                        // 更新session状态
                        user.setAccountPassWord(result.getObj().toString());
                        TokenUtil.updateUser(user);
                        redisService.delete("Valid:accountpwSmsCode" + user.getMobile());
                        jsonResult.setSuccess(true);
                        return jsonResult;
                    } else {
                        jsonResult.setSuccess(false);
                        jsonResult.setMsg(result.getMsg());
                        return jsonResult;
                    }
                } else {
                    jsonResult.setSuccess(false);
                    return jsonResult;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("yichang"));
                return jsonResult;
            }
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
    }

    @CommonLog(name = "人脸识别保存")
    @ApiOperation(value = "人脸识别保存", httpMethod = "POST", response = ApiJsonResult.class, notes = "人脸识别保存")
    @PostMapping("/user/updateFeature")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult updateFeature (
            @ApiParam(name = "feature", value = "人脸待验码", required = true) @RequestParam(value = "feature",required = true) String feature,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
          /*  if(user.getFeature()!=null){
                jsonResult.setSuccess(false);
                jsonResult.setMsg("yicunzai");
                return  jsonResult;
            }*/
            try {
                //System.out.println(feature);
                RemoteResult result = remoteManageService.updateFeature(user.getCustomerId().toString(), feature);
                if (result != null) {
                    if (result.getSuccess()) {
                        // 更新session状态
                        user.setFeature(feature);
                        TokenUtil.updateUser(user);
                        jsonResult.setSuccess(true);
                        return jsonResult;
                    } else {
                        jsonResult.setSuccess(false);
                        jsonResult.setMsg(result.getMsg());
                        return jsonResult;
                    }
                } else {
                    jsonResult.setSuccess(false);
                    return jsonResult;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("yichang"));
                return jsonResult;
            }
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
    }

    @ApiOperation(value = "人脸登录", httpMethod = "POST", response = ApiJsonResult.class, notes = "人脸登录")
    @PostMapping("/user/facelogin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult facelogin (
            @ApiParam(name = "img", value = "人脸照片", required = true) @RequestParam(value = "img",required = true) String img,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteUserService remoteUserService= hry.util.SpringUtil.getBean("remoteUserService");
            User  u= remoteUserService.getUser(user.getNickName());
            jsonResult.setSuccess(false);
            try {
                File file1 = Base64Util.base64ToTempFile(img, "img");
                jsonResult = FaceClient.check_face_login(file1,u.getFeature(),jsonResult);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonResult;
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
    }
}

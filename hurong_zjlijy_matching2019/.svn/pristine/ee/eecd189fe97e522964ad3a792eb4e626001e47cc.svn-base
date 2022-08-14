package hry.util.sso;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.bean.JsonResult;
import hry.util.properties.PropertiesUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class TiensShopSSO {
    // http://172.16.227.25:8321/sso/user/register
    //登录地址
    private static final String LOGINURL = "/sso/user/login";
    //注册地址
    private static final String REGISTERURL = "/sso/user/register";
    //邮箱验证地址
    private static final String VALID_EMAIL_URL = "/sso/user/validUserEmail";
    //重置密码
    private static final String RESETPASSWORD = "/sso/security/resetPassword";
    //修改密码
    private static final String MODIFYPASSWORD = "/sso/user/modifyPassword";
    // 普通会员绑定邀请码成为粉丝
    private static final String MODIFYINVITATIONCODE = "/sso/user/modifyInvitationCode";
    // 修改手机或邮箱
    private static final String MODIFY_PHONE_EMAIL_URL = "/sso/user/modifyPhoneAndMail";

    /**
     * 手机邮箱登录
     *
     * @param loginName
     * @param password
     */
    public static JsonResult login(String loginName, String password) {


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginName", loginName);
        jsonObject.put("password", password);
        System.out.println("tiens|login|params==" + jsonObject.toJSONString());
        String result = PostUtils.postJSON(PropertiesUtils.APP.getProperty("app.tiens.shop.sso") + LOGINURL, jsonObject.toJSONString());
        System.out.println("tiens|login==" + result);
        if (result == null || StringUtils.isEmpty(result)) {
            return new JsonResult().setMsg("登录失败");
        }

        JSONObject resultObject = JSON.parseObject(result);
        if (resultObject == null) {
            return new JsonResult().setMsg("登录失败");
        }

        //登录成功
        if (resultObject.getBoolean("success")) {
            JSONObject datas = resultObject.getJSONObject("datas");
            if (datas != null) {
                String loginToken = datas.getString("loginToken");
                Long userId = datas.getLong("userId");
                Long shopId = datas.getLong("shopId");
                String invitationCode = datas.getString("invitationCode");
                Integer userType = datas.getInteger("userType");

                JsonResult jsonResult = new JsonResult();
                jsonResult.setSuccess(true);
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("loginToken", loginToken);
                map.put("userId", userId);
                map.put("shopId", shopId);
                map.put("userType", userType);
                map.put("invitationCode", invitationCode);
                jsonResult.setObj(map);
                return jsonResult;
            }
        } else {
            return new JsonResult().setMsg(resultObject.getString("message"));
        }

        return new JsonResult().setMsg("登录失败");
    }

    /**
     * 电商手机注册
     *
     * @param userMobile
     * @param logindPwd
     * @param country
     * @param invitationCode
     * @return
     */
    public static JsonResult regist4Mobile(String userMobile, String logindPwd, String country, String invitationCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userMobile", userMobile);
        jsonObject.put("loginpwd", logindPwd);
        jsonObject.put("country", country);
        if (!StringUtils.isEmpty(invitationCode)) {
            jsonObject.put("invitationCode", invitationCode);
        }
        System.out.println("    ==== tiens | params  ==> : " + jsonObject.toJSONString());
        String result = PostUtils.postJSON(PropertiesUtils.APP.getProperty("app.tiens.shop.sso") + REGISTERURL, jsonObject.toJSONString());
        System.out.println("    ==== tiens | register  ==> : " + result);
        if (StringUtils.isEmpty(result)) {
            return new JsonResult().setMsg("注册失败");
        }
        JSONObject resultObject = JSON.parseObject(result);
        if (resultObject == null) {
            return new JsonResult().setMsg("登录失败");
        } else if (resultObject.getIntValue("code") == 200 && resultObject.getBoolean("success")) {//注册成功
            JSONObject datas = resultObject.getJSONObject("datas");
            Long uid = datas.getLong("uid");
            String loginname = datas.getString("loginname");
            String loginpwd = datas.getString("loginpwd");
            String passwordSalt = datas.getString("passwordSalt");
            JsonResult jsonResult = new JsonResult();
            jsonResult.setSuccess(true);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uid", uid);
            map.put("loginname", loginname);
            map.put("loginpwd", loginpwd);
            map.put("passwordSalt", passwordSalt);
            jsonResult.setObj(map);
            return jsonResult;
        } else {
            return new JsonResult().setMsg(resultObject.getString("message"));
        }
    }


    /**
     * 忘记密码，重置密码
     *
     * @param mobilePhone 新密码
     * @param password    旧密码
     */
    public static JsonResult resetPassword(String mobilePhone, String password) {


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loginInfo", mobilePhone);
        jsonObject.put("password", password);
        System.out.println("tiens|resetPassword|params==" + jsonObject.toJSONString());
        String result = PostUtils.postJSON(PropertiesUtils.APP.getProperty("app.tiens.shop.sso") + RESETPASSWORD, jsonObject.toJSONString());
        System.out.println("tiens|resetPassword==" + result);
        if (result == null || StringUtils.isEmpty(result)) {
            return new JsonResult().setMsg("修改密码失败");
        }

        JSONObject resultObject = JSON.parseObject(result);
        if (resultObject == null) {
            return new JsonResult().setMsg("修改密码失败");
        }

        //修改密码成功
        if (resultObject.getBoolean("success")) {
            JsonResult jsonResult = new JsonResult();
            jsonResult.setSuccess(true);
            return jsonResult;
        } else {
            return new JsonResult().setMsg(resultObject.getString("message"));
        }

    }


    /**
     * 修改密码
     *
     * @param token       token
     * @param password    新密码
     * @param oldPassword 旧密码
     */
    public static JsonResult modifyPassword(String token, String password, String oldPassword) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("password", password);
        jsonObject.put("oldPassword", oldPassword);
        System.out.println("tiens|modifyPassword|params==" + jsonObject.toJSONString());
        String result = PostUtils.postJSON(PropertiesUtils.APP.getProperty("app.tiens.shop.sso") + MODIFYPASSWORD, jsonObject.toJSONString());
        System.out.println("tiens|modifyPassword==" + result);
        if (result == null || StringUtils.isEmpty(result)) {
            return new JsonResult().setMsg("修改密码失败");
        }

        JSONObject resultObject = JSON.parseObject(result);
        if (resultObject == null) {
            return new JsonResult().setMsg("修改密码失败");
        }

        //修改密码成功
        if (resultObject.getBoolean("success")) {
            JsonResult jsonResult = new JsonResult();
            jsonResult.setSuccess(true);
            return jsonResult;
        } else {
            return new JsonResult().setMsg(resultObject.getString("message"));
        }

    }


    public static void validate() {
    }


    public static void main(String[] args) {
        regist4Mobile("18811118805", "qq123456", "86", "");
        login("18811118805", "qq123456");
    }

    public static JsonResult modifyInvitationCode(String shopInviteCode, Map<String,Object> ssoMap) {

        if (StringUtils.isEmpty(shopInviteCode) || "undefined".equals(shopInviteCode) || "null".equals(shopInviteCode)) {
            return new JsonResult().setMsg("邀请码错误");
        }

        String loginToken = (String) ssoMap.get("loginToken");
        Long userId = (Long) ssoMap.get("userId");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", loginToken);
        jsonObject.put("userId", userId);
        jsonObject.put("invitationCode", shopInviteCode);
        System.out.println("=================== >>  modifyInvitationCode  << ====================");
        System.out.println("                                                             ");
        System.out.println("tiens | modifyInvitationCode | params==" + jsonObject.toJSONString());
        System.out.println("----------------------------------------------------------------------");
        String result = PostUtils.postJSON(PropertiesUtils.APP.getProperty("app.tiens.shop.sso") + MODIFYINVITATIONCODE, jsonObject.toJSONString());
        System.out.println("tiens | modifyInvitationCode ==" + result);
        System.out.println("                                                             ");
        System.out.println("=================== >>  modifyInvitationCode  << ====================");
        if (result == null || StringUtils.isEmpty(result)) {
            return new JsonResult().setMsg("商城邀请绑定失败");
        }

        JSONObject resultObject = JSON.parseObject(result);
        if (resultObject == null) {
            return new JsonResult().setMsg("商城邀请绑定失败");
        }

        //邀请绑定成功
        if (resultObject.getIntValue("code") == 200) {
            JSONObject datas = resultObject.getJSONObject("datas");
            Long shopId = datas.getLong("shopId");
            JsonResult jsonResult = new JsonResult();
            jsonResult.setSuccess(true);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("inviterShopId", shopId);
            jsonResult.setObj(map);
            return jsonResult;
        } else {
            return new JsonResult().setMsg(resultObject.getString("message"));
        }
    }

    /**
     * 验证邮箱账户是否已存在
     *
     * @param email
     * @return
     */
    public static JsonResult validUserEmail(String email) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userEmail", email);
        System.out.println("tiens|validUserEmail|params==" + jsonObject.toJSONString());
        String result = PostUtils.postJSON(PropertiesUtils.APP.getProperty("app.tiens.shop.sso") + VALID_EMAIL_URL, jsonObject.toJSONString());
        System.out.println("tiens|validUserEmail==" + result);
        if (result == null || StringUtils.isEmpty(result)) {
            return new JsonResult().setMsg("该邮箱已经注册");
        }
        JSONObject resultObject = JSON.parseObject(result);
        if (resultObject == null) {
            return new JsonResult().setMsg("该邮箱已经注册");
        }
        //验证成功
        if (resultObject.getIntValue("code") == 200) {
            JsonResult jsonResult = new JsonResult();
            jsonResult.setSuccess(true);
            return jsonResult;
        } else {
            return new JsonResult().setMsg(resultObject.getString("message"));
        }
    }

    /**
     * 邮箱账户注册
     *
     * @param email
     * @param password
     * @param referralCode
     * @return
     */
    public static JsonResult regist4Email(String email, String password, String referralCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userEmail", email);
        jsonObject.put("loginpwd", password);
        if (!StringUtils.isEmpty(referralCode)) {
            jsonObject.put("invitationCode", referralCode);
        }
        System.out.println("tiens|register|params==" + jsonObject.toJSONString());
        String result = PostUtils.postJSON(PropertiesUtils.APP.getProperty("app.tiens.shop.sso") + REGISTERURL, jsonObject.toJSONString());
        System.out.println("tiens|register==" + result);
        if (result == null || StringUtils.isEmpty(result)) {
            return new JsonResult().setMsg("注册失败");
        }

        JSONObject resultObject = JSON.parseObject(result);
        if (resultObject == null) {
            return new JsonResult().setMsg("注册失败");
        }

        //注册成功
        if (resultObject.getBoolean("success")) {
            JSONObject datas = resultObject.getJSONObject("datas");
            if (datas != null) {
                Long uid = datas.getLong("uid");
                JsonResult jsonResult = new JsonResult();
                jsonResult.setSuccess(true);
                jsonResult.setObj(uid);
                return jsonResult;
            }
        } else {
            return new JsonResult().setMsg(resultObject.getString("message"));
        }
        return new JsonResult().setMsg("注册失败");
    }

    /**
     * 修改手机号 OR 邮箱
     *
     * @param token      登录tonken
     * @param changeType 修改类型：phone or mail
     * @param address    修改后的手机号 or 邮箱
     * @return
     */
    public static JsonResult modifyPphoneOrEmail(String token, String changeType, String address) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("changeType", changeType);
        jsonObject.put("address", address);

        System.out.println("tiens|modifyPphoneOrEmail|params==" + jsonObject.toJSONString());
        String result = PostUtils.postJSON(PropertiesUtils.APP.getProperty("app.tiens.shop.sso") + MODIFY_PHONE_EMAIL_URL, jsonObject.toJSONString());
        System.out.println("tiens|modifyPphoneOrEmail==" + result);
        if (result == null || StringUtils.isEmpty(result)) {
            return new JsonResult().setMsg("修改失败");
        }
        JSONObject resultObject = JSON.parseObject(result);
        if (resultObject == null) {
            return new JsonResult().setMsg("修改失败");
        }
        //修改成功
        if (resultObject.getIntValue("code") == 200) {
            JsonResult jsonResult = new JsonResult();
            jsonResult.setSuccess(true);
            return jsonResult;
        } else {
            return new JsonResult().setMsg(resultObject.getString("message"));
        }
    }
}

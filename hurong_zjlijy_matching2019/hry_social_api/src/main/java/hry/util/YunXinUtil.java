package hry.util;

import com.alibaba.fastjson.JSONObject;
import hry.bean.JsonResult;
import hry.util.oss.GetYunUtil;
import hry.util.properties.PropertiesUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class YunXinUtil {

    /**
     * 注册云信账户
     *
     * @param accid
     * @param nickName
     * @param headPortrait
     * @return
     */
    public static JsonResult register(String accid, String token, String nickName, String headPortrait) {
        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/user/create.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("accid", accid));
        if (!StringUtils.isEmpty(nickName)) {
            nvps.add(new BasicNameValuePair("name", nickName));
        }
        if (!StringUtils.isEmpty(headPortrait)) {
            headPortrait = GetYunUtil.getUrl(headPortrait, false);
            nvps.add(new BasicNameValuePair("icon", headPortrait));
        }
        if (!StringUtils.isEmpty(token)) {
            nvps.add(new BasicNameValuePair("token", token));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信注册 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            String desc = jsonObject.getString("desc");
            if (code == 200) {// 成功返回
                JSONObject info = jsonObject.getJSONObject("info");
                token = info.getString("token");
                if (!StringUtils.isEmpty(token)) {
                    jsonResult.setSuccess(true);
                    jsonResult.setObj(token);
                    jsonResult.setMsg(desc);
                    return jsonResult;
                }
            } else if (code == 414 && "already register".equalsIgnoreCase(desc)) {  // 用户已存在，刷新token
                JsonResult refreshResult = refreshToken(accid);
                if (refreshResult.getSuccess()) {
                    jsonResult.setSuccess(true);
                    jsonResult.setObj(token);
                    jsonResult.setMsg(desc);
                    return jsonResult;
                }
                return jsonResult;
            }
            return jsonResult;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 更新并获取新token
     *
     * @param accid
     * @return
     */
    public static JsonResult refreshToken(String accid) {
        JsonResult jsonResult = new JsonResult();
        String token = null;
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/user/refreshToken.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("accid", accid));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信刷新token 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            String desc = jsonObject.getString("desc");
            if (code == 200) {// 成功返回
                JSONObject info = jsonObject.getJSONObject("info");
                token = info.getString("token");
                if (!StringUtils.isEmpty(token)) {
                    jsonResult.setSuccess(true);
                    jsonResult.setObj(token);
                    jsonResult.setMsg(desc);
                    return jsonResult;
                }
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * @param accid  加好友-发起者accid
     * @param faccid 加好友-接收者accid
     * @param type   加好友-方式 ：1直接加好友，2请求加好友，3同意加好友，4拒绝加好友
     * @return
     */
    public static JsonResult addFriend(String accid, String faccid, int type) {

        System.out.println("云信添加好友参数 : ");
        System.out.println("accid : " + accid);
        System.out.println("faccid : " + faccid);
        System.out.println("type : " + type);

        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/friend/add.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("accid", accid));
        nvps.add(new BasicNameValuePair("faccid", faccid));
        nvps.add(new BasicNameValuePair("msg", null));
        nvps.add(new BasicNameValuePair("type", String.valueOf(type)));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信加好友 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * @param accid  加好友-发起者accid
     * @param faccid 加好友-接收者accid
     * @return
     */
    public static JsonResult delFriend(String accid, String faccid) {
        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/friend/delete.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("accid", accid));
        nvps.add(new BasicNameValuePair("faccid", faccid));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信删除好友 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 创建群
     *
     * @param tname           群名称，最大长度64字符
     * @param owner           群主用户帐号，最大长度32字符
     * @param members         ["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
     * @param announcement    群公告，最大长度1024字符
     * @param intro           群描述，最大长度512字符
     * @param msg             邀请发送的文字，最大长度150字符
     * @param magree          管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
     * @param joinmode        群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
     * @param custom          自定义高级群扩展属性，第三方可以跟据此属性自定义扩展自己的群属性。（建议为json）,最大长度1024字符
     * @param icon            群头像，最大长度1024字符
     * @param beinvitemode    被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414
     * @param invitemode      谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
     * @param uptinfomode     谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414
     * @param upcustommode    谁可以更新群自定义属性，0-管理员(默认),1-所有人。其它返回414
     * @param teamMemberLimit 该群最大人数(包含群主)，范围：2至应用定义的最大群人数(默认:200)。其它返回414
     * @return
     */
    public static JsonResult createGroup(String tname, String owner, String members, String announcement, String intro, String msg, String magree, String joinmode, String custom, String icon, String beinvitemode, String invitemode, String uptinfomode, String upcustommode, String teamMemberLimit) {

        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/team/create.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("tname", tname));
        nvps.add(new BasicNameValuePair("owner", owner));
        nvps.add(new BasicNameValuePair("members", members));
        nvps.add(new BasicNameValuePair("msg", msg));
        nvps.add(new BasicNameValuePair("magree", magree));
        nvps.add(new BasicNameValuePair("joinmode", joinmode));
        if (!StringUtils.isEmpty(announcement)) {
            nvps.add(new BasicNameValuePair("announcement", announcement));
        }
        if (!StringUtils.isEmpty(intro)) {
            nvps.add(new BasicNameValuePair("intro", intro));
        }
        if (!StringUtils.isEmpty(custom)) {
            nvps.add(new BasicNameValuePair("custom", custom));
        }
        if (!StringUtils.isEmpty(icon)) {
            icon = GetYunUtil.getUrl(icon, false);
            nvps.add(new BasicNameValuePair("icon", icon));
        }
        if (!StringUtils.isEmpty(beinvitemode)) {
            nvps.add(new BasicNameValuePair("beinvitemode", beinvitemode));
        }
        if (!StringUtils.isEmpty(invitemode)) {
            nvps.add(new BasicNameValuePair("invitemode", invitemode));
        }
        if (!StringUtils.isEmpty(uptinfomode)) {
            nvps.add(new BasicNameValuePair("uptinfomode", uptinfomode));
        }
        if (!StringUtils.isEmpty(upcustommode)) {
            nvps.add(new BasicNameValuePair("upcustommode", upcustommode));
        }
        if (!StringUtils.isEmpty(teamMemberLimit)) {
            nvps.add(new BasicNameValuePair("teamMemberLimit", teamMemberLimit));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信创建群 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                jsonResult.setObj(jsonObject);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 拉人入群
     *
     * @param tid     网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
     * @param owner   群主用户帐号，最大长度32字符
     * @param members ["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
     * @param msg     管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
     * @param magree  邀请发送的文字，最大长度150字符
     * @param attach  自定义扩展字段，最大长度512
     * @return
     */
    public static JsonResult addToGroup(String tid, String owner, String members, String msg, String magree, String attach) {
        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/team/add.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("tid", tid));
        nvps.add(new BasicNameValuePair("owner", owner));
        nvps.add(new BasicNameValuePair("members", members));
        nvps.add(new BasicNameValuePair("msg", msg));
        nvps.add(new BasicNameValuePair("magree", magree));
        if (!StringUtils.isEmpty(attach)) {
            nvps.add(new BasicNameValuePair("attach", attach));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信拉人入群 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                jsonResult.setObj(jsonObject);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 踢人出群
     *
     * @param tid     网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
     * @param owner   群主的accid，用户帐号，最大长度32字符
     * @param member  被移除人的accid，用户账号，最大长度32字符;注：member或members任意提供一个，优先使用member参数
     * @param members ["aaa","bbb"]（JSONArray对应的accid，如果解析出错，会报414）一次最多操作200个accid; 注：member或members任意提供一个，优先使用member参数
     * @param attach  自定义扩展字段，最大长度512
     * @return
     */
    public static JsonResult kickOutGroup(String tid, String owner, String member, String members, String attach) {
        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/team/kick.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("tid", tid));
        nvps.add(new BasicNameValuePair("owner", owner));
        if (!StringUtils.isEmpty(member)) {
            nvps.add(new BasicNameValuePair("member", member));
        }
        if (!StringUtils.isEmpty(members)) {
            nvps.add(new BasicNameValuePair("members", members));
        }
        if (!StringUtils.isEmpty(attach)) {
            nvps.add(new BasicNameValuePair("attach", attach));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信踢人出群 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                jsonResult.setObj(jsonObject);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 删除整个群，会解散该群，需要提供群主accid，谨慎操作！
     *
     * @param tid   网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
     * @param owner 群主用户帐号，最大长度32字符
     * @return
     */
    public static JsonResult removeGroup(String tid, String owner) {
        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/team/remove.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("tid", tid));
        nvps.add(new BasicNameValuePair("owner", owner));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信删除(解散)群 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                jsonResult.setObj(jsonObject);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 高级群基本信息修改
     *
     * @param tid             网易云通信服务器产生，群唯一标识，创建群时会返回
     * @param tname           群名称，最大长度64字符
     * @param owner           群主用户帐号，最大长度32字符
     * @param announcement    群公告，最大长度1024字符
     * @param intro           群描述，最大长度512字符
     * @param joinmode        群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
     * @param custom          自定义高级群扩展属性，第三方可以跟据此属性自定义扩展自己的群属性。（建议为json）,最大长度1024字符
     * @param icon            群头像，最大长度1024字符
     * @param beinvitemode    被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414
     * @param invitemode      谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
     * @param uptinfomode     谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414
     * @param upcustommode    谁可以更新群自定义属性，0-管理员(默认),1-所有人。其它返回414
     * @param teamMemberLimit 该群最大人数(包含群主)，范围：2至应用定义的最大群人数(默认:200)。其它返回414
     * @return
     */
    public static JsonResult updateGroup(String tid, String tname, String owner, String announcement, String intro, String joinmode, String custom, String icon, String beinvitemode, String invitemode, String uptinfomode, String upcustommode, String teamMemberLimit) {

        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/team/update.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("tid", tid));
        nvps.add(new BasicNameValuePair("owner", owner));
        if (!StringUtils.isEmpty(tname)) {
            nvps.add(new BasicNameValuePair("joinmode", joinmode));
        }
        if (!StringUtils.isEmpty(tname)) {
            nvps.add(new BasicNameValuePair("tname", tname));
        }
        if (!StringUtils.isEmpty(announcement)) {
            nvps.add(new BasicNameValuePair("announcement", announcement));
        }
        if (!StringUtils.isEmpty(intro)) {
            nvps.add(new BasicNameValuePair("intro", intro));
        }
        if (!StringUtils.isEmpty(custom)) {
            nvps.add(new BasicNameValuePair("custom", custom));
        }
        if (!StringUtils.isEmpty(icon)) {
            icon = GetYunUtil.getUrl(icon, false);
            nvps.add(new BasicNameValuePair("icon", icon));
        }
        if (!StringUtils.isEmpty(beinvitemode)) {
            nvps.add(new BasicNameValuePair("beinvitemode", beinvitemode));
        }
        if (!StringUtils.isEmpty(invitemode)) {
            nvps.add(new BasicNameValuePair("invitemode", invitemode));
        }
        if (!StringUtils.isEmpty(uptinfomode)) {
            nvps.add(new BasicNameValuePair("uptinfomode", uptinfomode));
        }
        if (!StringUtils.isEmpty(upcustommode)) {
            nvps.add(new BasicNameValuePair("upcustommode", upcustommode));
        }
        if (!StringUtils.isEmpty(teamMemberLimit)) {
            nvps.add(new BasicNameValuePair("teamMemberLimit", teamMemberLimit));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信群基本信息修改 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                jsonResult.setObj(jsonObject);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 查询指定群的详细信息（群信息+成员详细信息）
     *
     * @param tid 群id，群唯一标识，创建群时会返回
     * @return
     */
    public static JsonResult getGroupDetail(String tid) {
        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/team/queryDetail.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("tid", tid));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信查询指定群的详细信息 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                jsonResult.setObj(jsonObject);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 修改指定账号在群内的昵称
     *
     * @param tid    群唯一标识，创建群时网易云通信服务器产生并返回
     * @param owner  群主 accid
     * @param accid  要修改群昵称的群成员 accid
     * @param nick   accid 对应的群昵称，最大长度32字符
     * @param custom 自定义扩展字段，最大长度1024字节
     * @return
     */
    public static JsonResult updateNickInGroup(String tid, String owner, String accid, String nick, String custom) {
        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/team/updateTeamNick.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("tid", tid));
        nvps.add(new BasicNameValuePair("owner", owner));
        nvps.add(new BasicNameValuePair("accid", accid));
        nvps.add(new BasicNameValuePair("nick", nick));
        if (!StringUtils.isEmpty(custom)) {
            nvps.add(new BasicNameValuePair("custom", custom));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信修改指定账号在群内的昵称 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                jsonResult.setObj(jsonObject);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 高级群主动退群
     *
     * @param tid   网易云通信服务器产生，群唯一标识，创建群时会返回
     * @param accid 退群的accid
     * @return
     */
    public static JsonResult leaveGroup(String tid, String accid) {
        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/team/leave.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("tid", tid));
        nvps.add(new BasicNameValuePair("accid", accid));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信高级群主动退群 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                jsonResult.setObj(jsonObject);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 高级群修改消息提醒开关
     *
     * @param tid   网易云通信服务器产生，群唯一标识，创建群时会返回
     * @param accid 要操作的群成员accid
     * @param ope   1：关闭消息提醒，2：打开消息提醒，其他值无效
     * @return
     */
    public static JsonResult muteTeam(String tid, String accid, int ope) {
        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/team/leave.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("tid", tid));
        nvps.add(new BasicNameValuePair("accid", accid));
        nvps.add(new BasicNameValuePair("ope", String.valueOf(ope)));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信高级群修改消息提醒开关 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                jsonResult.setObj(jsonObject);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 获取某个用户所加入高级群的群信息
     *
     * @param accid 要查询用户的accid
     * @return
     */
    public static JsonResult getJoinGroup(String accid) {
        JsonResult jsonResult = new JsonResult();
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/team/joinTeams.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("accid", accid));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信获取某个用户所加入高级群的群信息 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            if (code == 200) {// 成功返回
                jsonResult.setSuccess(true);
                jsonResult.setObj(jsonObject);
                return jsonResult;
            } else {
                return jsonResult;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 注册云信账户
     *
     * @param accid
     * @param nickName
     * @param headPortrait
     * @return
     */
    public static JsonResult registerTow(String accid, String nickName, String headPortrait) {
        JsonResult jsonResult = new JsonResult();
        String token = null;
        String result = null;
        HttpResponse response = null;
        String apiUrl = PropertiesUtils.APP.getProperty("app.YunXin.apiUrl");
        String appKey = PropertiesUtils.APP.getProperty("app.YunXin.appKey");
        String appSecret = PropertiesUtils.APP.getProperty("app.YunXin.appSecret");
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = apiUrl + "/nimserver/user/create.action";
        HttpPost httpPost = new HttpPost(url);
        // 设置请求的header
        String nonce = "12345";
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        // 设置请求的参数
        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("accid", accid));
        if (!StringUtils.isEmpty(nickName)) {
            nvps.add(new BasicNameValuePair("name", nickName));
        }
        if (!StringUtils.isEmpty(headPortrait)) {
            headPortrait = GetYunUtil.getUrl(headPortrait, false);
            nvps.add(new BasicNameValuePair("name", headPortrait));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求，返回结果
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("    ====    云信注册 结果    ====> ：" + result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (null == jsonObject) {
                return jsonResult;
            }
            int code = jsonObject.getInteger("code");
            String desc = jsonObject.getString("desc");
            if (code == 200) {// 成功返回
                JSONObject info = jsonObject.getJSONObject("info");
                token = info.getString("token");
                if (!StringUtils.isEmpty(token)) {
                    jsonResult.setSuccess(true);
                    jsonResult.setObj(token);
                    jsonResult.setMsg(desc);
                    return jsonResult;
                }
            } else if (code == 414 && desc.contains("already register")) {  // 用户已存在，刷新token
                jsonResult.setSuccess(true);
                jsonResult.setObj(token);
                jsonResult.setMsg(desc);
                return jsonResult;
            }
            return jsonResult;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

}
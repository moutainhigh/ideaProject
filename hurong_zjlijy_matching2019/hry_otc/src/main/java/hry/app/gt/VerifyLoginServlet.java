package hry.app.gt;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import hry.redis.common.utils.RedisService;
import hry.util.IpUtil;
import hry.util.PropertiesUtils;
import hry.util.common.SpringUtil;
import hry.util.gt.GeetestLib;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


/**
 * 手机端极验验证：
 * 使用Get的方式返回challenge和capthca_id,此方式以实现前后端完全分离的开发模式
 */
public class VerifyLoginServlet extends HttpServlet {

    protected void doPost (HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        // 获取ip地址
        String Ip = IpUtil.getIp(request);

        GeetestLib gtSdk = new GeetestLib(PropertiesUtils.APP.getProperty("geetest_id"), PropertiesUtils.APP.getProperty("geetest_key"),
                true);

        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        RedisService redisService = SpringUtil.getBean("redisService");
        String s = redisService.get("gt:" + Ip + ":" + gtSdk.gtServerStatusSessionKey);
        //从session中获取gt-server状态
        int gt_server_status_code = Integer.valueOf(s);
        //从session中获取userid
        String userid = redisService.get("gt:" + Ip + ":userid");

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", Ip); //传输用户请求验证时所携带的IP

        int gtResult = 0;
        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }

        if (gtResult == 1) {
            // 验证成功
            PrintWriter out = response.getWriter();
            JSONObject data = new JSONObject();
            try {
                data.put("status", "success");
                data.put("version", gtSdk.getVersionInfo());
                data.put("challenge", challenge);
                data.put("validate", validate);
                data.put("seccode", seccode);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            out.println(data.toString());
        } else {
            // 验证失败
            JSONObject data = new JSONObject();
            try {
                data.put("status", "fail");
                data.put("version", gtSdk.getVersionInfo());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            out.println(data.toString());
        }
    }

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
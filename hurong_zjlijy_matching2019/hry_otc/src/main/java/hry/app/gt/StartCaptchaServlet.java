package hry.app.gt;

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
 * pc端极验验证：
 * 使用Get的方式返回challenge和capthca_id,此方式以实现前后端完全分离的开发模式
 */
public class StartCaptchaServlet extends HttpServlet {

    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        RedisService redisService = SpringUtil.getBean("redisService");
        // 获取ip地址
        String Ip = IpUtil.getIp(request);

        GeetestLib gtSdk = new GeetestLib(PropertiesUtils.APP.getProperty("geetest_id"), PropertiesUtils.APP.getProperty("geetest_key"),
                true);
        String resStr = "{}";
        //自定义userid
        String userid = "test";
        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", Ip); //传输用户请求验证时所携带的IP
        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);
        //将服务器状态设置到session中
        redisService.save("gt:" + Ip + ":gt_server_status", gtServerStatus + "");
        //将userid设置到session中
        redisService.save("gt:" + Ip + ":userid", userid);
        resStr = gtSdk.getResponseStr();
        PrintWriter out = response.getWriter();
        out.println(resStr);

    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

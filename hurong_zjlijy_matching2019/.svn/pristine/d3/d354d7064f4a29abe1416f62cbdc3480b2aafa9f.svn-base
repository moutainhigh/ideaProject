package hry.app.gt;

import hry.redis.common.utils.RedisService;
import hry.util.IpUtil;
import hry.util.common.SpringUtil;
import hry.util.gt.GeetestLib;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class GTvalidate {
    private static Logger logger = Logger.getLogger(GTvalidate.class);

    /**
     * 验证极验
     *
     * @param request
     * @return
     */
    public static boolean validateGT (HttpServletRequest request) {
        // 获取ip地址
        String Ip = IpUtil.getIp(request);

        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        GeetestLib gtSdk = new GeetestLib(hry.util.PropertiesUtils.APP.getProperty("geetest_id"), hry.util.PropertiesUtils.APP.getProperty("geetest_key"), true);
        RedisService redisService = SpringUtil.getBean("redisService");
        //从session中获取gt-server状态
        String status = redisService.get("gt:" + Ip + ":gt_server_status");
        if (StringUtils.isEmpty(status)) {
            logger.error("The Gt Server Status not exist in redis");
            return false;
        }
        int gt_server_status_code = new Integer(status);

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
            logger.error(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证

            logger.error("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            logger.error(gtResult);
        }

        //验证失败
        if (gtResult != 1) {
            return false;
        }
        return true;
    }
}

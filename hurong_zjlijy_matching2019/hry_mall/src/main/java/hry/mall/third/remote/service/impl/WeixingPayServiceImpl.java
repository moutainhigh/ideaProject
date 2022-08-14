package hry.mall.third.remote.service.impl;

import hry.bean.JsonResult;
import hry.mall.third.remote.service.WeixingPayService;
import hry.util.*;
import hry.util.Md5Util;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *@auther zhouming
 *@date 2019/3/19 13:36
 *@Version 1.0
 */
@Service("weixingPayService")
public class WeixingPayServiceImpl implements WeixingPayService{



    @Override
    public JsonResult getMerchantBalance() {
        String userid = PropertiesUtils.APP.getProperty("weixing.userid");
        String key = PropertiesUtils.APP.getProperty("weixing.key");
        JsonResult jsonResult = null;
        String sign = "userid=" + userid + "&key=" + key;
        sign = Md5Util.MD5(sign);
        String param = "userid=" + userid + "&sign=" + sign;
        String url = PropertiesUtils.APP.getProperty("weixing.balance");
        String result = HttpRequest.sendPost(url, param);
        Map<String, String> resultMap = TestDom4j.readStringXmlOut(result);
        if (resultMap.size() > 0) {
            jsonResult = new JsonResult().setSuccess(true).setObj(resultMap.get("balance")).setMsg(resultMap.get("msg"));
        } else {
            jsonResult = new JsonResult(false).setMsg("接口查询错误");
        }
        return jsonResult;
    }

    @Override
    public JsonResult telephoneRecharge(String mobile, String sporderid, String price) {
        String userid = PropertiesUtils.APP.getProperty("weixing.userid");
        String key = PropertiesUtils.APP.getProperty("weixing.key");
        JsonResult jsonResult = null;
        String timestamp = DateUtils.getDateStr("yyyyMMddHHmmss"); // 时间戳
        String edition = "V2.0"; // 为固定值
        String param = "userid=" + userid +
                        "&mobile=" + mobile +
                        "&price=" + price +
                        "&sporderid=" + sporderid +
                        "&Edition=" + edition +
                        "&Timestamp="+ timestamp;
        String sign = param + "&key=" + key;
        sign = Md5Util.MD5(sign);
        param = param + "&sign=" + sign;
        String url = PropertiesUtils.APP.getProperty("weixing.recharge");
        String result = HttpRequest.sendPost(url, param);
        Map<String, String> resultMap = TestDom4j.readStringXmlOut(result);
        if (resultMap.size() > 0) {
            jsonResult = new JsonResult(true).setObj(resultMap.get("status")).setMsg(resultMap.get("msg"));
        } else {
            jsonResult = new JsonResult(false).setMsg("话费充值失败");
        }
        return jsonResult;
    }

    @Override
    public JsonResult getPayOrderQuery(String sporderid) {
        String userid = PropertiesUtils.APP.getProperty("weixing.userid");
        String key = PropertiesUtils.APP.getProperty("weixing.key");
        JsonResult jsonResult = null;
        String param = "userid=" + userid +
                       "&sporderid=" + sporderid;
        String sign = param + "&key=" + key;
        sign = Md5Util.MD5(sign);
        param = param + "&sign=" + sign;
        String url = PropertiesUtils.APP.getProperty("weixing.query");
        String result = HttpRequest.sendPost(url, param);
        Map<String, String> resultMap = TestDom4j.readStringXmlOut(result);
        if (resultMap.size() > 0) {
            jsonResult = new JsonResult(true).setObj(resultMap.get("status")).setMsg(resultMap.get("msg"));
        } else {
            jsonResult = new JsonResult(false).setMsg("订单查询失败");
        }
        return jsonResult;
    }

    @Override
    public JsonResult checkParamData(String userid, String sporderid, String status, String sign) {
        String key = PropertiesUtils.APP.getProperty("weixing.key");
        JsonResult jsonResult = null;
        String param = "userid=" + userid +
                       "&sporderid=" + sporderid +
                       "&status="+ status +
                       "&key=" + key;
        String paramSign = Md5Util.MD5(param);
        if (sign.equals(paramSign)) {
            jsonResult = new JsonResult(true).setMsg("数据校验成功");
        } else {
            jsonResult = new JsonResult(false).setMsg("数据发生篡改");
        }
        return jsonResult;
    }
}

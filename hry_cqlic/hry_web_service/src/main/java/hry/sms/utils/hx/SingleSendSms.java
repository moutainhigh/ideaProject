package hry.sms.utils.hx;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import hry.sms.utils.ali.Client;
import hry.sms.utils.ali.Request;
import hry.sms.utils.ali.Response;
import hry.sms.utils.ali.constant.Constants;
import hry.sms.utils.ali.constant.HttpSchema;
import hry.sms.utils.ali.enums.Method;
import org.apache.log4j.Logger;

public class SingleSendSms {
	private static Logger logger = Logger.getLogger(SingleSendSms.class);
	//佐昊阿里云短信服务测试
	 private final static String APP_KEY = "LTAISTm2KIizOjqV"; //AppKey从控制台获取
	    private final static String APP_SECRET = "gZI3sKfNqM3LsD0wtNMp1xm85u6bpb"; //AppSecret从控制台获取
	    private final static String SIGN_NAME = "51数字资产签名"; // 签名名称从控制台获取，必须是审核通过的
	    private final static String TEMPLATE_CODE = "SMS_39200288"; //模板CODE从控制台获取，必须是审核通过的
	    private final static String HOST = "sms.market.alicloudapi.com"; //API域名从控制台获取

	    private final static String ERRORKEY = "errorMessage";  //返回错误的key

	    // @phoneNum: 目标手机号，多个手机号可以逗号分隔;
	    // @params: 短信模板中的变量，数字必须转换为字符串，如短信模板中变量为${no}",则参数params的值为{"no":"123456"}
	    public void sendMsg(String phoneNum, String params){
	        String path = "/singleSendSms";

	        Request request =  new Request(Method.GET, HttpSchema.HTTP + HOST, path, APP_KEY, APP_SECRET, Constants.DEFAULT_TIMEOUT);

	        //请求的query
	        Map<String, String> querys = new HashMap<String, String>();
	        querys.put("SignName", SIGN_NAME);
	        querys.put("TemplateCode", TEMPLATE_CODE);
	        querys.put("RecNum", phoneNum);
	        querys.put("ParamString", params);
	        request.setQuerys(querys);

	        try {
	            Map<String, String> bodymap = new HashMap<String, String>();
	            Response response = Client.execute(request);
	            //根据实际业务需要，调整对response的处理
	            if (null == response) {
	                logger.error("no response");
	            } else if (200 != response.getStatusCode()) {
	                logger.error("StatusCode:" + response.getStatusCode());
	                logger.error("ErrorMessage:"+response.getErrorMessage());
	                logger.error("RequestId:"+response.getRequestId());
	                logger.error(response.getBody());
	            } else {
	                bodymap = ReadResponseBodyContent(response.getBody());
	                if (null != bodymap.get(ERRORKEY)) {
	                    //当传入的参数不合法时，返回有错误说明
	                    logger.error(bodymap.get(ERRORKEY));
	                } else {
	                    //成功返回map，对应的key分别为：message、success等
	                    logger.error(JSON.toJSONString(bodymap));
	                }
	            }
	        }catch (Exception e){
	            logger.error(e.getMessage());
	        }
	    }

	    private Map<String, String> ReadResponseBodyContent(String body) {
	        Map<String, String> map = new HashMap<String, String>();    
	        try {
	            JSONObject jsonObject = JSON.parseObject(body);
	            if (null != jsonObject) {               
	                for(Entry<String, Object> entry : jsonObject.entrySet()){
	                    map.put(entry.getKey(), entry.getValue().toString());
	                }               
	            }
	            if ("false".equals(map.get("success"))) {
	                map.put(ERRORKEY, map.get("message"));
	            }
	        } catch (Exception e) {
	            map.put(ERRORKEY, body);
	        }
	        return map;
	    }


	    public  static void main(String agrs[]){
	        SingleSendSms app = new SingleSendSms();
	        app.sendMsg("15313152752","{'no':'12345'}");
	    }
}

/**
 * 111
 */

package hry.util;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.fabric.xmlrpc.base.Data;

import hry.bean.ApiJsonResult;

public class FaceClient {
    private static final String mAppKey = "oB2cpxYMfmrMKjd4jhrKmKNWXUq8U81qbJsADeHZrC";
    private static String url = "https://face.zhiquplus.com/api/faceid/1.1/oB2cpxYMfmrMKjd4jhrKmKNWXUq8U81qbJsADeHZrC/face_login";

    /**
     * HTTP 请求处理
     */
    private static JsonObject apiRequest(String url, HttpEntity reqEntity) {
        JsonObject resData = null;
        String res = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(reqEntity);
            HttpClient httpClient = HttpClients.createDefault();
            HttpEntity httpEntity = httpClient.execute(httpPost).getEntity();
            res = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
                //todo 调用异常：商户异常处理逻辑
        }
        if (res != null) {
            JsonObject resJson = new JsonParser().parse(res).getAsJsonObject();
//            System.out.println(String.format("result:%s", res));
            if (resJson.get("success").getAsBoolean()) {
                resData =resJson;// resJson.getAsJsonObject("data");
            } else {
                resData=resJson;
//                todo 调用异常：商户业务处理逻辑
//                String errorCode = resJson.get("error_code").getAsString();
//                String message = resJson.get("message").getAsString();
//                System.out.println(String.format("错误码：%s , 错误信息：%s", errorCode,
//                        message));
            }
        }
        return resData;
    }
    public static ApiJsonResult check_face_login(File image1, String image2_feature, ApiJsonResult apiJsonResult) {
        //trace_id 可为商户业务系统的订单号，用于后续问题跟踪
        String traceId = UUID.randomUUID().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("时间："+sdf.format(new Date())+",traceId="+traceId);
        //图片1是否检测活体
        String image1_liveness = "1";
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addTextBody("trace_id", traceId)
                .addTextBody("image1_liveness", image1_liveness)
                .addBinaryBody("image1", image1, ContentType.create("image/jpg"),
                        image1.getName())
                .addTextBody("image2_feature", image2_feature)
                .build();
        JsonObject resData = apiRequest(url, reqEntity);
        System.out.println("时间："+sdf.format(new Date())+",返回结果resData="+resData);
        if (resData != null) {
            boolean bo= resData.get("success").getAsBoolean();
            if (bo) {
                String ob=resData.getAsJsonObject("data").toString();
                //{"similarity":0.965254,"liveness_score":0.564886}
                JSONObject object=JSONObject.parseObject(ob);
                apiJsonResult.setObj(object);
            } else {
                apiJsonResult.setMsg(resData.get("message").getAsString());
            }
            apiJsonResult.setSuccess(bo);
        }else{
        	apiJsonResult.setSuccess(false);
        }
        return apiJsonResult;
    }
   

    public static void main(String[] args) {
    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         System.out.println("时间："+sdf.format(new Date())+",traceId=");
	}
}

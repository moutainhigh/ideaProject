package hry.admin.baidu.translate;

import com.alibaba.fastjson.JSONObject;
import hry.util.properties.PropertiesUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/4/26 16:39
 * @Description: 百度翻译API
 */
public class TranslateApi {

    private static final String TRANS_API_HRY = PropertiesUtils.APP.getProperty("translate_hry");

    public TranslateApi() {
    }


    public String getTransResult_HRY(String str,String to) {
        if("zh_CN".equals(to) || StringUtils.isEmpty(TRANS_API_HRY)){
            return str;
        }
        Map<String, String> params = new LinkedHashMap<>();
        params.put("from",str);
        params.put("auto",to);
        return getResult(TRANS_API_HRY, params);
    }


    public String getResult(String host, Map<String, String> params){
        try {
            Thread.sleep(1000);
            String s = doPost(host,params);
            JSONObject object = JSONObject.parseObject(s);
            JSONObject msg = JSONObject.parseObject(object.get("msg").toString());
            return msg.get("to").toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }



    public static String doPost(String url, Map params) {

        BufferedReader in = null;
        try {
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));

                //System.out.println(name +"-"+value);
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {    //请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(), "utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();

                return sb.toString();
            } else {    //
                System.out.println("状态码：" + code);
                return "{code:"+code+",success:false,msg:'error'}";
            }
        } catch (Exception e) {
            e.printStackTrace();

            return "{success:false,msg:'error'}";
        }
    }

}

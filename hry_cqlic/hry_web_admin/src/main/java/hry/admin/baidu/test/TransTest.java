package hry.admin.baidu.test;

import com.alibaba.fastjson.JSONObject;
import hry.admin.baidu.translate.TranslateApi;
import hry.util.properties.PropertiesUtils;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/4/26 16:53
 * @Description:
 */
public class TransTest {
    /**
     * en、kor、fra、jp、spa、cht
     */
    private static final String APP_ID = PropertiesUtils.APP.getProperty("baiduTranApi");
    private static final String SECURITY_KEY = PropertiesUtils.APP.getProperty("baiduTranSecurityKey");

    public static void main(String[] args) {
        TranslateApi api = new TranslateApi();
        try {
            String query = "现货指数";
                String transResult = api.getTransResult_HRY(query,"kor");
                System.out.println(transResult);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}

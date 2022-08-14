package util;

import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class MD5 {
    private static Logger logger = Logger.getLogger(MD5.class);
    /**
     * 对JSONObject的key-value按照升序排序
     *
     * @param jsonObject
     * @return
     */
    public static String sort4Sign(JSONObject jsonObject) {
        List<String> list = new ArrayList<String>(jsonObject.size());
        for (@SuppressWarnings("unchecked")
             Iterator<String> iterator = jsonObject.keys(); iterator.hasNext(); ) {


            String key = iterator.next();
            if (!("sign").equals(key)) {
                list.add(key);
            }
        }

        String[] keysArray = new String[list.size()];
        keysArray = list.toArray(keysArray);
        Arrays.sort(keysArray);
        logger.error(Arrays.toString(keysArray));

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < keysArray.length; i++) {
            buffer.append(jsonObject.getString(keysArray[i]));
        }
        return buffer.toString();
    }

    /**
     * hashmap 参数排序
     *
     * @param
     * @return
     */
    public static String sort4Sign(Map<String, String> postMap) {
        List<String> list = new ArrayList<String>(postMap.size());
        for (String key : postMap.keySet()) {
            if (!("sign").equals(key)) {
                list.add(key);
            }
        }
        String[] keysArray = new String[list.size()];
        keysArray = list.toArray(keysArray);
        Arrays.sort(keysArray);
        logger.error(Arrays.toString(keysArray));
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < keysArray.length; i++) {
            buffer.append(postMap.get(keysArray[i]));
        }
        return buffer.toString();
    }

    /**
     * 签名校验
     *
     * @param preSignText      待签名字符串,ASC排序后按照key=value的格式拼接
     * @param originSignResult 原MD5签名结果
     * @return true 签名校验通过
     */
    public static boolean verify(String preSignText,String originSignResult) {
        String mysign = sign(preSignText, MD5.todaySign(),  "UTF-16");
        if (!mysign.equals(originSignResult)) {
            return false;
        }


        return true;
    }


    /**
     * 计算MD5签名<br>
     *
     * @param preSignText 待签名字符串,ASC排序后按照key=value的格式拼接
     * @param businessKey 密钥
     * @param signCharset 计算签名的字符编码
     * @return
     */
    public static String sign(String preSignText, String businessKey, String signCharset) {
        String preSign = preSignText + businessKey;
        try {
            return DigestUtils.md5Hex(preSign.getBytes(signCharset));
        } catch (UnsupportedEncodingException e) {
            logger.error("非法的加密字符集");
        }
        return null;
    }

    /**
     * 计算MD5签名<br>
     *
     * @param preSignText 待签名字符串,ASC排序后按照key=value的格式拼接
     * @return
     */
    public static String sign(String preSignText) {
        String preSign = preSignText + MD5.todaySign();
        try {
            return DigestUtils.md5Hex(preSign.getBytes("UTF-16"));
        } catch (UnsupportedEncodingException e) {
            logger.error("非法的加密字符集");
        }
        return null;
    }


    /**
     * 签名校验
     *
     * @param preSignText      待签名字符串,ASC排序后按照key=value的格式拼接
     * @param businessKey      密钥
     * @param signCharset      计算签名的字符编码
     * @param originSignResult 原MD5签名结果
     * @return true 签名校验通过
     */
    public static boolean verify(String preSignText, String businessKey, String signCharset, String originSignResult) {
        String mysign = sign(preSignText, businessKey, signCharset);
        if (mysign.equals(originSignResult)) {
            return true;
        }
        return false;
    }


    public static String todaySign() {
        Date s = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String Today = df.format(s);
        String todaySign = sign(Today, "2acbdfe1a0946da02abcb6de9e16d14ad77c1c5e", "UTF-16");
        return todaySign;


    }

    public static String yesterdaySign() {
        Date s = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        c.setTime(s);
        c.add(Calendar.DAY_OF_MONTH, -1);// 今天-1天

        Date tomorrow = c.getTime();

        String Yesterday = df.format(tomorrow);

        String yesterdaySign = sign(Yesterday, "2acbdfe1a0946da02abcb6de9e16d14ad77c1c5e", "UTF-16");
        return yesterdaySign;


    }


}

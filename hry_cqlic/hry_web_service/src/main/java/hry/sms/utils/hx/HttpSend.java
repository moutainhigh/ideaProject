package hry.sms.utils.hx;

import hry.util.StringUtil;
import hry.util.http.HttpConnectionUtil;
import hry.util.md5.Md5Encrypt;
import hry.util.properties.PropertiesUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

public class HttpSend {
	private static Logger logger = Logger.getLogger(HttpSend.class);
	public static String getSend(String strUrl,String param){
		URL url = null;
		HttpURLConnection connection = null;
		
		try {
			url = new URL(strUrl + "?" + param);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
				}
		}
	}
	
	public static String postSend(String strUrl,String param){
		
		
		URL url = null;
		HttpURLConnection connection = null;
		
		try {
			url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();

			//POST方法时使用
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(param);
			out.flush();
			out.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
				}
		}
		
		
	}
	/**
	 * 转为16进制方法
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String paraTo16(String str) throws UnsupportedEncodingException {
			String hs = "";
			
			byte[] byStr = str.getBytes("UTF-8");
			for(int i=0;i<byStr.length;i++)
			{
				String temp = "";
				temp = (Integer.toHexString(byStr[i]&0xFF));
				if(temp.length()==1) temp = "%0"+temp;
				else temp = "%"+temp;
				hs = hs+temp;
			}
			return hs.toUpperCase();
	
		
	}
	
	
	/**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */

	public static String yunpianPost(String url, Map<String, String> paramsMap) {
		HttpClient client = new HttpClient();
		try {
			PostMethod method = new PostMethod(url);
			if (paramsMap != null) {
				NameValuePair[] namePairs = new NameValuePair[paramsMap.size()];
				int i = 0;
				for (Map.Entry<String, String> param : paramsMap.entrySet()) {
					NameValuePair pair = new NameValuePair(param.getKey(),
							param.getValue());
					namePairs[i++] = pair;
				}
				method.setRequestBody(namePairs);
				HttpMethodParams param = method.getParams();
				param.setContentCharset("utf-8");
			}
			client.executeMethod(method);
			return method.getResponseBodyAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	//模拟充值回调
	public static void main(String[] args) {
		String strUrl="http://www.200.com/coin/lmcTransferCallBack";
		String Secret = "40cdcaed5a8f58748af02aff9dc4b2af";
		//参数
		String transfer_id="T20170802221149";
		String status="2";
		String txid="tx2012318882102002";
		String request_time=System.currentTimeMillis()+"";
		String reason="充值成功";
		String symbol="LMC";
		String from="Lc2gf5PksLANJUQV5dhUGukWL7BMsBMk3n";//线下钱包地址
		String to="mn5MN7poc3atdxH1Pr3Cs7gNjZxcxGDrGG";//线上钱包地址
		String coins="100";
		String fee="0.1";
		String transfer_type="I";
		String[] s = {transfer_id,status,txid,request_time,reason,symbol,from,to,coins,fee,transfer_type,Secret};
		//数组字典排序
		String auth_code=StringUtil.stringSort(s, "_");
		auth_code=Md5Encrypt.md5(auth_code);
		Map<String,String> map=new HashMap<String, String>();
		map.put("auth_code", auth_code);
		map.put("transfer_id", transfer_id);
		map.put("status", status);
		map.put("txid", txid);
		map.put("request_time", request_time);
		map.put("reason", reason);
		map.put("symbol", symbol);
		map.put("from", from);
		map.put("to",to );
		map.put("coins",coins );
		map.put("fee", fee);
		map.put("transfer_type", transfer_type);
		String param=StringUtil.string2params(map);
		logger.error(strUrl+"?"+param);
		HttpConnectionUtil.postSend(strUrl, param);
	}
	
	
	//钱包转币接口调用测试
	public static void main22(String[] args) {
		String strUrl="http://api.lomocoin.com/wallet/transfer";
		String Secret = "40cdcaed5a8f58748af02aff9dc4b2af";
		//参数
		//app_key
		String app_key="30bbb1b174fdd43c497fab4366e77649";
		String request_time=System.currentTimeMillis()+"";
		String transfer_type="I";
		String symbol="LMC";
		String type="1";
		String coins="0.1";
		String from_wallet="mn5MN7poc3atdxH1Pr3Cs7gNjZxcxGDrGG";
		String to_wallet="mjvpLLypueP4sGjgyk6Y5cLYtS82pjGn7w";
		String transfer_id="t_2011234432";
		String validation_type="md5";
		String validation_code="hry";
		String validation_phone="18911192929";
		
		String[] s = {app_key,request_time,transfer_type,symbol,type,coins,from_wallet,to_wallet,transfer_id,
				validation_type,validation_code,validation_phone,Secret};
		//数组字典排序
		String auth_code=StringUtil.stringSort(s, "_");
		//7033701e37179453affa0a80b2c7d4ee
		auth_code=Md5Encrypt.md5(auth_code);
		Map<String,String> map=new HashMap<String, String>();
		map.put("auth_code", auth_code);
		map.put("app_key", app_key);
		map.put("request_time", request_time);
		map.put("transfer_type", transfer_type);
		map.put("symbol", symbol);
		map.put("type", type);
		map.put("coins", coins);
		map.put("from_wallet", from_wallet);
		map.put("to_wallet", to_wallet);
		map.put("transfer_id", transfer_id);
		map.put("validation_type",validation_type );
		map.put("validation_code",validation_code );
		map.put("validation_phone", validation_phone);
		String param=StringUtil.string2params(map);
		logger.error(strUrl+"?"+param);
		String result=HttpConnectionUtil.postSend(strUrl, param);
		logger.error(result);
	}
}

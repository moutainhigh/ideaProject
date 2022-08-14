/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月18日 下午3:28:29
 */
package hry.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月18日 下午3:28:29 
 */
public class HttpConnectionUtil {
	
	
	/**
	 * get请求
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param strUrl  请求地址
	 * @param:    @param param   请求参数
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年5月18日 下午3:32:07   
	 * @throws:
	 */
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
			connection.setConnectTimeout(100000);
			connection.setReadTimeout(100000);

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
	 * post请求
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param strUrl  请求地址
	 * @param:    @param param   请求参数
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年5月18日 下午3:32:34   
	 * @throws:
	 */
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
			connection.addRequestProperty("X-Requested-With","XMLHttpRequest");
			connection.setRequestProperty("Charset", "utf-8");
			//connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			connection.setRequestProperty("Content-Type","Content-Type:application/x-www-form-urlencoded; charset=UTF-8");
			connection.connect();

			//POST方法时使用
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			//以下语句在转中文时候，已经变成乱码
			//out.writeBytes(param);
			out.write(param.getBytes());
			out.flush();
			out.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
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
	
}

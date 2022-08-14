package hry.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;


/**
 * 模拟web提交
 */
public class WebClient {
	public static void SendByUrl(HttpServletResponse response, String url, String charset) {
		if (response != null) {
			try {
				// response.setContentType("text/html;charset="+charset+"");
				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding(charset);
				response.sendRedirect(url);
				// response.getWriter().write("<script
				// language=JavaScript>window.open('" + url + "'
				// ,'_blank','');</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * get方式
	 */
	public static String getWebContentByGet(String urlString, final String charset, int timeout) throws IOException {
		if (urlString == null || urlString.length() == 0) {
			return null;
		}
		urlString = (urlString.startsWith("http://") || urlString.startsWith("https://")) ? urlString
				: ("http://" + urlString).intern();
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		// 增加报头，模拟浏览器，防止屏蔽
		conn.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
		// 只接受text/html类型，当然也可以接受图片,pdf,*/*任意，就是tomcat/conf/web里面定义那些
		conn.setRequestProperty("Accept", "*/*");
		conn.setConnectTimeout(timeout);
		try {
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		InputStream input = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, charset));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\r\n");
		}
		if (reader != null) {
			reader.close();
		}
		if (conn != null) {
			conn.disconnect();
		}
		return sb.toString();

	}

	/**
	 * post 方式
	 */
	public static String getWebContentByPost(String urlString, final String charset, int timeout)
			throws IOException {
		if (urlString == null || urlString.length() == 0) {
			return null;
		}
		
		urlString = (urlString.startsWith("http://") || urlString.startsWith("https://")) ? urlString
				: ("http://" + urlString).intern();
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
		// 设置是否向connection输出，因为这个是post请求，参数要放在 http正文内，因此需要设为true
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		// Post 请求不能使用缓存
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		// connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset + "");
		// 增加报头，模拟浏览器，防止屏蔽
		connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows vista)");
		// 只接受text/html类型，当然也可以接受图片,pdf,*/*任意
		connection.setRequestProperty("Accept", "*/*");// text/html
		connection.setConnectTimeout(timeout);
		connection.connect();
		
			// 必须写在发送数据的后面
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				return null;
			}
		} catch (Exception e) {
			System.out.println("网址不通:"+urlString);
			//e.printStackTrace();
			return null;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\r\n");
		}
		if (reader != null) {
			reader.close();
		}
		if (connection != null) {
			connection.disconnect();
		}
		return sb.toString();
	}


	/**
	 * 准备中间页面所需参数 add by linyan 2014-9-22
	 * 
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 */
	public static String[] operateParameter(String url, Map<String, String> params, String charset,
			HttpServletResponse response) {
		String[] ret = new String[2];
		if (url != null && !"".equals(url)) {
			if (charset != null && !"".equals(charset)) {
				StringBuffer sb = new StringBuffer();
				String parameterUtil = getParams(params, charset);
				sb.append("<html>");
				sb.append("<head>");
				sb.append("<script type=\"text/javascript\">");
				sb.append("function redirectUrl() {");
				sb.append("document.form0.submit();");
				sb.append("}");
				sb.append("</script>");
				sb.append("</head>");
				sb.append("<body onload=\"redirectUrl()\">");
				sb.append("<form name=\"form0\" action=\"" + url + "\" method=\"post\">");
				if (parameterUtil != null) {
					sb.append(parameterUtil);
				}
				sb.append("</form>");
				sb.append("</body>");
				sb.append("</html>");
				ret[0] = "8888";
				ret[1] = sb.toString();
				reponseWrite(sb.toString(), charset, response);
			} else {
				ret[0] = "0000";
				ret[1] = "form表单编码方式不存在";
			}
		} else {
			ret[0] = "0000";
			ret[1] = "第三方url不存在";
		}
		return ret;
	}

	/**
	 * 获取中间页面的form表单参数
	 * 
	 * @param params
	 * @return
	 */
	private static String getParams(Map<String, String> params, String charset) {
		// TODO Auto-generated method stub
		String htmlParamss = null;
		try {
			if (params != null) {
				StringBuffer sb = new StringBuffer();
				Iterator iter = params.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					Object key = entry.getKey();
					Object val = entry.getValue();
					sb.append(
							"<input type=\"hidden\" name='" + key.toString() + "\' value='" + val.toString() + "' />");
				}
				htmlParamss = sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return htmlParamss;
	}

	public static void reponseWrite(String htmlParamss, String charset, HttpServletResponse response) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter pw;
			pw = response.getWriter();
			pw.write(htmlParamss);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package hry.sms.utils.ym;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;


import hry.sms.utils.hx.HttpSend;
import org.apache.log4j.Logger;

public class YiMeiUtils {
	private static Logger logger = Logger.getLogger(YiMeiUtils.class);
	public static String sn = "8SDK-EMY-6699-RFUNP";// 软件序列号,请通过亿美销售人员获取
	public static String key = "688729";// 序列号首次激活时自己设定
	public static String password = "688729";// 密码,请通过亿美销售人员获取
	//public static String baseUrl = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/";
	public static String baseUrl = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/";
	public static String sendMethod = "get";// 发送请求方式get / post
	public static String addserial="";
	
	
	
	
	public static String sendSms(String reg,String pwd,String mobilePhone, String code,String content) {
		try {
			logger.error(content);
			content = HttpSend.paraTo16(content);
			String url = baseUrl + "sendsms.action";
			long seqId = System.currentTimeMillis();
			String strSmsParam = "cdkey="  + reg + "&password=" + pwd  + "&phone=" + mobilePhone + "&message=" + content+ "&addserial=" + addserial + "&seqid=" + seqId;
		    String strRes  = new String();
			//strRes = HttpSend.postSend(url, strSmsParam);
		    strRes = SDKHttpClient.sendSMS(url, strSmsParam);
			logger.error("短信第三方返回结果:"+strRes);
			return strRes;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static void main(String[] args) {
		try {
			Properties p = GetProperties.getProperties("resource/config.properties");
			baseUrl = p.getProperty("baseUrl") != null ? p.getProperty("baseUrl") : baseUrl;
			sn = p.getProperty("sn") != null ? p.getProperty("sn") : sn;
			key = p.getProperty("key") != null ? p.getProperty("key") : key;
			password = p.getProperty("password") != null ? p.getProperty("password") : password;
			sendMethod = p.getProperty("sendMethod") != null ? p.getProperty("sendMethod") : sendMethod;
			logger.error("baseUrl=" + baseUrl + "|sn=" + sn + "|key=" + key + "|password=" + password);

			StartMenu();
			while (true) {
				logger.error("请输入序号进行操作");
				byte[] command = new byte[4];
				System.in.read(command);
				int operate = 0;
				try {
					String commandString = new String(command);
					commandString = commandString.replaceAll("\r\n", "").trim();
					operate = Integer.parseInt(commandString);
				} catch (Exception e) {
					logger.error("命令输入错误！！！");
				}
				String param = "";
				switch (operate) {
				case 1:
					String url = baseUrl + "regist.action";
					param = "cdkey=" + sn + "&password=" + password;
					String ret = SDKHttpClient.registAndLogout(url, param);
					logger.error("注册结果:" + ret);
					break;
				case 2:
					param = "cdkey=" + sn + "&password=" + key;
					url = baseUrl + "querybalance.action";
					String balance = SDKHttpClient.getBalance(url, param);
					logger.error("当前余额:" + balance);
					break;
				case 3:
					String mdn = "13718730732";
					String message = "send->" + System.currentTimeMillis();
					message = URLEncoder.encode(message, "UTF-8");
					String code = "";
					long seqId = System.currentTimeMillis();
					param = "cdkey=" + sn + "&password=" + key + "&phone=" + mdn + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
					url = baseUrl + "sendsms.action";
					if ("get".equalsIgnoreCase(sendMethod)) {
						ret = SDKHttpClient.sendSMS(url, param);
					} else {
						ret = SDKHttpClient.sendSMSByPost(url, param);
					}

					logger.error("发送结果:" + ret);
					break;
				case 4:
					param = "cdkey=" + sn + "&password=" + key;
					url = baseUrl + "getmo.action";
					List<Mo> mos = SDKHttpClient.getMos(url, sn, key);
					logger.error("获取上行数量：" + mos.size());
					break;
				case 5:
					param = "cdkey=" + sn + "&password=" + key;
					url = baseUrl + "getreport.action";
					List<StatusReport> srs = SDKHttpClient.getReports(url, sn, key);
					logger.error("获取报告数量：" + srs.size());
					break;
				case 6:
					url = baseUrl + "logout.action";
					param = "cdkey=" + sn + "&password=" + password;
					ret = SDKHttpClient.registAndLogout(url, param);
					logger.error("注销结果:" + ret);
					break;
				case 7:
					System.exit(0);
				default:
					logger.error("没有该命令 " + operate);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void StartMenu() {
		int i = 1;
		logger.error(i + "、激活序列号,初次使用、已注销后再次使用时调用该方法.");
		i += 1;
		logger.error(i + "、余额查询");
		i += 1;
		logger.error(i + "、发送带有信息ID的短信,可供查询状态报告");
		i += 1;
		logger.error(i + "、获取上行短信");
		i += 1;
		logger.error(i + "、获得下行短信状态报告");
		i += 1;
		logger.error(i + "、注销序列号");
		i += 1;
		logger.error(i + "、关闭程序");
	}
}

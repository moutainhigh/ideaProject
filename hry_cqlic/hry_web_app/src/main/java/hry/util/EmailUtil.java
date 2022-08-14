/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0
 * @Date:        2016年6月30日 下午3:11:50
 */
package hry.util;

import hry.util.properties.PropertiesUtils;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.Mail;
import hry.manage.remote.model.MailConfigAndTemplate;
import hry.util.common.SpringUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年6月30日 下午3:11:50
 *
 *  第一个参数是接收人邮箱地址
 *
 *  第二个参数是邮件标题
 *
 *  第三个参数是邮件正文
 *
 *
 */
public class EmailUtil {

	public static void multipltemail(){
		Random random=new Random();
		int result=random.nextInt(10);

		//PropertiesUtils.MULTIPLTEMAIL
	}

	/**
	 * receivUser ： 接收人
	 * mailTitle ： 邮件标题
	 * mailContent ： 邮件正文
	 *
	 * @author:    Wu Shuiming
	 * @version:   V1.0
	 * @date:      2016年8月15日 下午2:24:38
	 */
	public static Boolean sendMail(String receivUser,String mailTitle, String mailContent){
		//是否多个邮箱
		String multiple = PropertiesUtils.APP.getProperty("app.multiple");
		//端口
		String port = "";
		// 设置服务器
		String host = "";
		// 同时通过认证
		String auth = "";
		// 发送协议
		String protocol = "";
		// 发送用户
		String emailUser = "";
		// 认证用户
		String agreedUser = "";
		// 认证密码
		String agreedPwd = "";
		//自定义发件人名称
		String customName = "";
		//是否开启了ssl验证
		String sslflag = "";
		//是否开启tls
		String starttls = "";

		Random random=new Random();
		int result=random.nextInt(10);
		System.out.println("邮件内容："+mailContent);

		if("1".equals(multiple)){
			System.out.println("所选邮箱编号："+result);
			// 发送用户
			emailUser = PropertiesUtils.MULTIPLTEMAIL.getProperty("app.email.userName"+result);
			// 认证用户
			agreedUser = PropertiesUtils.MULTIPLTEMAIL.getProperty("app.email.agreedUser"+result);
			// 认证密码
			agreedPwd = PropertiesUtils.MULTIPLTEMAIL.getProperty("app.email.agreedPwd"+result);

		}else if("0".equals(multiple)){
			// 发送用户
			emailUser = PropertiesUtils.APP.getProperty("app.email.userName");
			// 认证用户
			agreedUser = PropertiesUtils.APP.getProperty("app.email.agreedUser");
			// 认证密码
			agreedPwd = PropertiesUtils.APP.getProperty("app.email.agreedPwd");
		}

		//端口
		port = PropertiesUtils.APP.getProperty("app.email.port");
		// 设置服务器
		host = PropertiesUtils.APP.getProperty("app.email.host");
		// 同时通过认证
		auth = PropertiesUtils.APP.getProperty("app.email.auth");
		// 发送协议
		protocol = PropertiesUtils.APP.getProperty("app.email.protocol");
		//自定义发件人名称
		customName = PropertiesUtils.APP.getProperty("app.email.customName");
		//是否开启了ssl验证
		sslflag = PropertiesUtils.APP.getProperty("app.email.ssl");
		//是否开启TLS
		starttls = PropertiesUtils.APP.getProperty("app.email.starttls");

		// 公司名称
		// String companyName = PropertiesUtils.APP.getProperty("app.email.companyName");

		Properties props = new Properties(); // 可以加载一个配置文件
		// 使用smtp：简单邮件传输协议
		if(null==port){
			props.put("mail.smtp.host", host);
		}
		if(sslflag==null){
			props.put("mail.smtp.ssl.enable", false);
		}else{
			props.put("mail.smtp.ssl.enable", sslflag);
		}
		props.put("mail.smtp.auth", auth);
		props.put("mail.transport.protocol", protocol);
		if(starttls!=null && !"".equals(starttls)){
			props.put("mail.smtp.starttls.enable", starttls);
		}
		Session session = Session.getDefaultInstance(props);// 根据属性新建一个邮件会话
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
		MimeMessage message = new MimeMessage(session);// 由邮件会话新建一个消息对象
		Mail mail = new Mail();
		mail.setAddress(receivUser);
		mail.setContent(mailContent);
		mail.setTitle(mailTitle);
		RemoteManageService remoteManageService = (RemoteManageService) SpringUtil.getBean("remoteManageService");



		try {

			message.setFrom(new InternetAddress(emailUser));

			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receivUser));// 设置收件人,并设置其接收类型为TO

			byte[] base64 = org.apache.commons.codec.binary.Base64.encodeBase64(mailTitle.getBytes());
	        String  mailTitle1= String.format("=?UTF-8?B?%s?=", new String(base64));
	     	message.setSubject(mailTitle, "utf-8");// 设置标题
	     	//设置自定义发件人昵称
	        String nick="";
	        try{
	              nick=javax.mail.internet.MimeUtility.encodeText(customName);
	          }catch(UnsupportedEncodingException e){
	              e.printStackTrace();
	          }
	          message.setFrom(new InternetAddress(nick+" <"+emailUser+">"));
			// 设置信件内容
			message.setText(mailContent, "utf-8"); // 发送 纯文本 邮件 todo
			message.setContent(mailContent, "text/html;charset=utf-8"); // 发送HTML邮件，内容样式比较丰富
			System.out.println(mailContent);
			message.setSentDate(new Date());// 设置发信时间
			message.saveChanges();// 存储邮件信息
			// 发送邮件
			Transport transport = session.getTransport();
			// 第一个参数  认证用户     第二个参数 认证密码
			if(null==port){
				transport.connect(agreedUser, agreedPwd);
			}else{
				transport.connect(host,Integer.valueOf(port), agreedUser,agreedPwd);
			}
	        transport.sendMessage(message, message.getAllRecipients());// 发送邮件,其中第二个参数是所有已设好的收件人地址
	        transport.close();

			mail.setErrorCode("成功");
			remoteManageService.insertMail(mail);
		} catch (Exception e) {
			System.out.println("发送邮件报错 ---- 接收人为  ： "+receivUser+  "   邮件标题是 ： "+ mailTitle);
			e.printStackTrace();
			mail.setErrorCode("未成功");
			mail.setErrorContent(e.toString());
			remoteManageService.insertMail(mail);
			return false;
		}
		return true;
	}
	/**
	 * receivUser ： 接收人
	 * mailTitle ： 邮件标题
	 * mailContent ： 邮件正文
	 *
	 * @author:    Wu Shuiming
	 * @version:   V1.0
	 * @date:      2016年8月15日 下午2:24:38
	 */
	public static Boolean sendMail(String receivUser, MailConfigAndTemplate mailConfigAndTemplate){

		String mailTitle=mailConfigAndTemplate.getPrefix()+mailConfigAndTemplate.getTempName();
		String mailContent=mailConfigAndTemplate.getTempContent();
		System.out.println("邮件标题："+mailTitle);
		System.out.println("邮件内容："+mailContent);

		// 发送用户
		String emailUser = mailConfigAndTemplate.getEmailUser();
		// 认证用户
		String agreedUser = mailConfigAndTemplate.getEmailUser();
		// 认证密码
		String agreedPwd =mailConfigAndTemplate.getAgreedPwd();

		//端口
		String port = mailConfigAndTemplate.getPort();
		// 设置服务器
		String host = mailConfigAndTemplate.getHost();
		// 同时通过认证
		String auth = (mailConfigAndTemplate.getAuth()==1)?"true":"false";
		// 发送协议
		String protocol = mailConfigAndTemplate.getProtocol();
		//自定义发件人名称
		String customName = mailConfigAndTemplate.getCustomName();
		//是否开启了ssl验证
		String sslflag = (mailConfigAndTemplate.getSslflag()==1)?"true":"false";
		//是否开启TLS
		String starttls = (mailConfigAndTemplate.getStarttls()==1)?"true":"false";



		Properties props = new Properties(); // 可以加载一个配置文件
		// 使用smtp：简单邮件传输协议
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.ssl.enable", sslflag);
		props.put("mail.smtp.auth", auth);
		props.put("mail.transport.protocol", protocol);
		props.put("mail.smtp.starttls.enable", starttls);
		Session session = Session.getInstance(props);// 根据属性新建一个邮件会话
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
		MimeMessage message = new MimeMessage(session);// 由邮件会话新建一个消息对象  ps:注意，这个session是final修饰的，只会初始化一次，相同的邮箱用户如果修改了配置，比如ssl校验，必须重启项目才可以
		Mail mail = new Mail();
		mail.setAddress(receivUser);
		mail.setContent(mailContent);
		mail.setTitle(mailTitle);
		RemoteManageService remoteManageService = (RemoteManageService) SpringUtil.getBean("remoteManageService");



		try {

			message.setFrom(new InternetAddress(emailUser));

			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receivUser));// 设置收件人,并设置其接收类型为TO

			message.setSubject(mailTitle, "utf-8");// 设置标题
            //设置自定义发件人昵称
            String nick="";
            try{
                nick=javax.mail.internet.MimeUtility.encodeText(customName);
            }catch(UnsupportedEncodingException e){
                e.printStackTrace();
            }
            message.setFrom(new InternetAddress(nick+" <"+emailUser+">"));
			// 设置信件内容
			message.setText(mailContent, "utf-8"); // 发送 纯文本 邮件 todo
			message.setContent(mailContent, "text/html;charset=utf-8"); // 发送HTML邮件，内容样式比较丰富
			System.out.println(mailContent);
			message.setSentDate(new Date());// 设置发信时间
			message.saveChanges();// 存储邮件信息
			// 发送邮件
			Transport transport = session.getTransport();
			// 第一个参数  认证用户     第二个参数 认证密码
			if(null==port||"".equals(port)){
				transport.connect(agreedUser, agreedPwd);
			}else{
				transport.connect(host,Integer.valueOf(port), agreedUser,agreedPwd);
			}
			transport.sendMessage(message, message.getAllRecipients());// 发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.close();

			mail.setErrorCode("成功");
			remoteManageService.insertMail(mail);
		} catch (Exception e) {
			System.out.println("发送邮件报错 ---- 接收人为  ： "+receivUser+  "   邮件标题是 ： "+ mailTitle);
			e.printStackTrace();
			mail.setErrorCode("未成功");
			mail.setErrorContent(e.toString());
			remoteManageService.insertMail(mail);
			return false;
		}
		return true;
	}


	/**
	 * sendcloud邮箱发送邮件
	 * receivUser ： 接收人
	 * mailContent ： 邮件正文
	 * mailTitle ： 邮件标题
	 */
	public static void sendEMail(String receivUser,String mailContent, String mailTitle){

		final String url = "http://api.sendcloud.net/apiv2/mail/send";
		//是否多个邮箱
		String multiple = PropertiesUtils.APP.getProperty("app.multiple");
		String apiUser = "";
		 String apiKey = "";
		// 发送用户
		String from = "";
		// 认证用户
		String fromName = "";
		// 认证密码
		Random random=new Random();
		int result=random.nextInt(10);

		//String subject = "2334.";
		//String html = "<p>323132</p>";
		if("1".equals(multiple)){
			System.out.println("所选邮箱编号："+result);
			//apiUser
			apiUser = PropertiesUtils.MULTIPLTEMAIL.getProperty("app.email.apiUser"+result);
			//apiKey
			apiKey = PropertiesUtils.MULTIPLTEMAIL.getProperty("app.email.apiKey"+result);
			// 发送用户
			from = PropertiesUtils.APP.getProperty("app.email.from"+result);
			// 签名
			fromName = PropertiesUtils.APP.getProperty("app.email.fromName"+result);
		}else if("0".equals(multiple)){
			//apiUser
			apiUser = PropertiesUtils.APP.getProperty("app.email.apiUser");
			//apiKey
			apiKey = PropertiesUtils.APP.getProperty("app.email.apiKey");
			// 发送用户
			from = PropertiesUtils.APP.getProperty("app.email.from");
			// 签名
			fromName = PropertiesUtils.APP.getProperty("app.email.fromName");
		}
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpClient = new DefaultHttpClient();

		HttpResponse response;
		Mail mail = new Mail();
		RemoteManageService remoteManageService = (RemoteManageService) SpringUtil.getBean("remoteManageService");
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("apiUser", apiUser));
			params.add(new BasicNameValuePair("apiKey", apiKey));
			params.add(new BasicNameValuePair("to", receivUser));
			params.add(new BasicNameValuePair("from", from));
			params.add(new BasicNameValuePair("fromName", fromName));
			params.add(new BasicNameValuePair("subject", mailTitle));
			params.add(new BasicNameValuePair("html", mailContent));

			httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

			response = httpClient.execute(httpPost);
			mail.setAddress(receivUser);
			mail.setContent(mailContent);
			mail.setTitle(mailTitle);
			// 处理响应
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 正常返回, 解析返回数据
				System.out.println(EntityUtils.toString(response.getEntity()));
				mail.setErrorCode("成功");
			} else {
				System.out.println(EntityUtils.toString(response.getEntity()));
				mail.setErrorCode("未成功");
				mail.setErrorContent(EntityUtils.toString(response.getEntity()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("发送邮件报错 ---- 接收人为  ： "+receivUser+  "   邮件标题是 ： "+ mailTitle);
			mail.setErrorCode("未成功");
			mail.setErrorContent(e.toString());
		}finally{
			httpPost.releaseConnection();
			remoteManageService.insertMail(mail);
		}

	}

	// 测试方法
	public static void main(String[] args) throws Exception {

		Boolean b = sendMail("844741036@qq.com", "Java Mail 测试邮件",
				"<a>点击确认</a>：<b>邮件内容。。。</b>");
		System.out.println(b);

		Random random=new Random();
		int result=random.nextInt(10);
		System.out.println(result);
	}



}

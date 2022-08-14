package hry.web.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hry.util.serialize.ObjectUtil;
import hry.manage.remote.RemoteManageService;

@Component
public class EmailUtil {
	private static Logger logger = Logger.getLogger(EmailUtil.class);

	
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
		String multiple = "0";
		
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
		
		if("1".equals(multiple)){
			logger.error("所选邮箱编号："+result);
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
		Session session = Session.getInstance(props);// 根据属性新建一个邮件会话
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log
		MimeMessage message = new MimeMessage(session);// 由邮件会话新建一个消息对象
		
		try {
			
			message.setFrom(new InternetAddress(emailUser));
			
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receivUser));// 设置收件人,并设置其接收类型为TO
		
			byte[] base64 = org.apache.commons.codec.binary.Base64.encodeBase64(mailTitle.getBytes());
	        String  mailTitle1= String.format("=?UTF-8?B?%s?=", new String(base64));
	     	message.setSubject(mailTitle1);// 设置标题
	     	//设置自定义发件人昵称  
	        String nick="";  
	        try{  
	              nick=javax.mail.internet.MimeUtility.encodeText(customName);  
	          }catch(UnsupportedEncodingException e){  
	              e.printStackTrace();  
	          }  
	          message.setFrom(new InternetAddress(nick+" <"+emailUser+">"));
			// 设置信件内容
			message.setText(mailContent); // 发送 纯文本 邮件 todo
			message.setContent(mailContent, "text/html;charset=gbk"); // 发送HTML邮件，内容样式比较丰富
			logger.error(mailContent);
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
		} catch (Exception e) {
			logger.error("发送邮件报错 ---- 接收人为  ： "+receivUser+  "   邮件标题是 ： "+ mailTitle);
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 测试方法 
	public static void main(String[] args) throws Exception {
		
//		Boolean b = sendMail("844741036@qq.com", "Java Mail 测试邮件",
//				"<a>点击确认</a>：<b>邮件内容。。。</b>");
//		logger.error(b);
		
		Random random=new Random();
		int result=random.nextInt(10);
		logger.error(result);
	}
	
	
	

}

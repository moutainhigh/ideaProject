package hry.util;

import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.Mail;
import hry.manage.remote.model.MailConfigAndTemplate;
import hry.util.common.SpringUtil;
import hry.util.properties.PropertiesUtils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class AmazonEmailUtil {

    public static Boolean sendMail(String receivUser, MailConfigAndTemplate mailConfigAndTemplate) throws  Exception{

        String mailTitle=mailConfigAndTemplate.getPrefix()+mailConfigAndTemplate.getTempName();
        String mailContent=mailConfigAndTemplate.getTempContent();
        System.out.println("邮件标题："+mailTitle);
        System.out.println("邮件内容："+mailContent);

        String smtp_username = PropertiesUtils.APP.getProperty("app.smtp_username");
        String smtp_password = PropertiesUtils.APP.getProperty("app.smtp_password");

        // 发送用户
        String emailUser = mailConfigAndTemplate.getEmailUser();

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


    	Properties props = System.getProperties();
    	props.put("mail.transport.protocol", protocol);
    	props.put("mail.smtp.port", port);
    	props.put("mail.smtp.starttls.enable", starttls);
    	props.put("mail.smtp.auth", auth);

    	Session session = Session.getDefaultInstance(props);
        //设置自定义发件人昵称
        String nick="";
        try{
            nick=javax.mail.internet.MimeUtility.encodeText(customName);
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailUser,nick+" <"+emailUser+">"));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receivUser));
        msg.setSubject(mailTitle);
        msg.setText(mailContent, "utf-8"); // 发送 纯文本 邮件 todo
        msg.setContent(mailContent, "text/html;charset=utf-8"); // 发送HTML邮件，内容样式比较丰富
       // msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        Transport transport = session.getTransport();

        Mail mail = new Mail();
        mail.setAddress(receivUser);
        mail.setContent(mailContent);
        mail.setTitle(mailTitle);
        RemoteManageService remoteManageService = (RemoteManageService) SpringUtil.getBean("remoteManageService");
        try
        {
            System.out.println("Sending...");
            transport.connect(host, smtp_username, smtp_password);
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
            mail.setErrorCode("成功");
            remoteManageService.insertMail(mail);
        }
        catch (Exception e) {
            System.out.println("The email was not sent.");
            System.out.println("发送邮件报错 ---- 接收人为  ： "+receivUser+  "   邮件标题是 ： "+ mailTitle);
            e.printStackTrace();
            mail.setErrorCode("未成功");
            mail.setErrorContent(e.toString());
            remoteManageService.insertMail(mail);
            return false;
        }
        finally
        {
            transport.close();
        }
        return true;
    }
}
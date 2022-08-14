package hry.util;

import hry.admin.web.model.Mail;
import hry.admin.web.model.MailConfig;
import hry.listener.ServerManagement;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {
    private static Logger logger = Logger.getLogger(EmailUtil.class);
    /**
     * mailConfig :  邮箱配置
     * mailTitle ： 邮件标题
     * mailContent ： 邮件正文
     *  * receivUser ： 接收人
     * @author:    sunyujie
     * @version:   V1.0
     * @date:      2018年7月20日 下午2:34:31
     */
    public static Boolean sendMail( MailConfig mailConfig,String mailTitle,String mailContent,String receivUser){

        logger.error("邮件标题："+mailTitle);
        logger.error("邮件内容："+mailContent);

        // 发送用户
        String emailUser = mailConfig.getEmailUser();
        // 认证用户
        String agreedUser = mailConfig.getEmailUser();
        // 认证密码
        String agreedPwd =mailConfig.getAgreedPwd();

        //端口
        String port = mailConfig.getPort();
        // 设置服务器
        String host = mailConfig.getHost();
        // 同时通过认证
        String auth = (mailConfig.getAuth()==1)?"true":"false";
        // 发送协议
        String protocol = mailConfig.getProtocol();
        //自定义发件人名称
        String customName = mailConfig.getCustomName();
        //是否开启了ssl验证
        String sslflag = (mailConfig.getSslflag()==1)?"true":"false";
        //是否开启TLS
        String starttls = (mailConfig.getStarttls()==1)?"true":"false";



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



        try {

            message.setFrom(new InternetAddress(emailUser));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receivUser));// 设置收件人,并设置其接收类型为TO

            message.setSubject(mailTitle);// 设置标题
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
            if(null==port||"".equals(port)){
                transport.connect(agreedUser, agreedPwd);
            }else{
                transport.connect(host,Integer.valueOf(port), agreedUser,agreedPwd);
            }
            transport.sendMessage(message, message.getAllRecipients());// 发送邮件,其中第二个参数是所有已设好的收件人地址
            transport.close();

            mail.setErrorCode("成功");
        } catch (Exception e) {
            logger.error("发送邮件报错 ---- 接收人为  ： "+receivUser+  "   邮件标题是 ： "+ mailTitle);
            e.printStackTrace();
            mail.setErrorCode("未成功");
            mail.setErrorContent(e.toString());
            return false;
        }
        return true;
    }
}

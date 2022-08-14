package hry.app.thread;

import hry.util.properties.PropertiesUtils;
import hry.manage.remote.RemoteMailConfigService;
import hry.manage.remote.model.MailConfigAndTemplate;
import hry.util.AmazonEmailUtil;
import hry.util.EmailUtil;
import hry.util.common.BaseConfUtil;
import hry.util.common.Constant;
import hry.util.common.SpringUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 登录后更新用户账户id保存到redis中
 *
 * @author CHINA_LSL
 */
public class EmailRunnable implements Runnable {

    private final Logger log = Logger.getLogger(EmailRunnable.class);
    private String email;
    private String langCode;
    private String type;
    private String url;

    public EmailRunnable (String email, String type, String langCode, String url) {
        // TODO Auto-generated constructor stub
        this.email = email;
        this.type = type;
        this.langCode = langCode;
        this.url = url;
    }

    @Override
    public void run () {
        RemoteMailConfigService service = SpringUtil.getBean("remoteMailConfigService");
        Map<String, Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
        Map<String, String> params = new HashMap<>();
        params.put("tempKey", type);
        params.put("languageDic", langCode);
        List<MailConfigAndTemplate> list = service.findMailConfigAndTemplateList(params);
        if (null == list || list.size() == 0) {//如果该语言没有配置模板，就使用中文的。
            params.put("languageDic", "zh_CN");
            list = service.findMailConfigAndTemplateList(params);
            if (null == list || list.size() == 0) {//如果中文的也没有配置，那就随机使用一个配置的，如果一个都没有。那么无法发送邮件
                params.put("languageDic", "");
                list = service.findMailConfigAndTemplateList(params);
            }
        }
        if (null != list && list.size() != 0) {
            StringBuffer sb = new StringBuffer();
            String tempContent = StringEscapeUtils.unescapeHtml4(list.get(0).getTempContent());
            sb.append("<div style='width:650px;height:420px;border:1px solid #285586;margin:30px auto 60px;font-size:14px;color:#333333;word-wrap: break-word; '>");
            sb.append("<h3 style='margin:0;background:#285586;height:100px;line-height:100px;padding:0 30px;font-size:20px;font-family:\"微软雅黑\";color:#FFFFFF;'>");
            sb.append(root.get("siteName").toString());
            sb.append("</h3><div style='padding:0px 60px 0'>");
            //		sb.append("<div style='clear:both;margin-left:-30px;margin-bottom:30px;'>");
            //		sb.append(SpringUtil.diff("dear") + " " + email);
            //		sb.append("</div>");
            if ("2".equals(type)) {
                tempContent = tempContent.replace("EMAIL_CODE", url);//验证码
                tempContent = tempContent.replace("EMAIL_ADDRESSEE", email);//收件人
                tempContent = tempContent.replace("EMAIL_SENDER", list.get(0).getCustomName());//发件人
                tempContent = tempContent.replace("EMAIL_SENDTIME", new Date().toString());//发件时间
                sb.append(tempContent + "<br><br>");
            } else {
                tempContent = tempContent.replace("EMAIL_LINK", "<a href='" + url + "'>" + url + "</a>");//验证码
                tempContent = tempContent.replace("EMAIL_ADDRESSEE", email);//收件人
                tempContent = tempContent.replace("EMAIL_SENDER", list.get(0).getCustomName());//发件人
                SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
                String format = sim.format(new Date());
                tempContent = tempContent.replace("EMAIL_SENDTIME", format);//发件时间
                sb.append(tempContent + "<br><br>");
            }
            sb.append("<br><br>");
            sb.append("</div>");
            sb.append("</div>");
            log.info("发送邮件内容:" + sb.toString());
            list.get(0).setTempContent(sb.toString());

            if (list.size() > 0) {
                String isRealEmail = PropertiesUtils.APP.getProperty("app.isRealEmail");
                if ("1".equals(isRealEmail)) {
                    EmailUtil.sendEMail(email, list.get(0).getTempContent(), list.get(0).getTempName());
                } else {
                    EmailUtil.sendMail(email, list.get(0));
                	//亚马逊邮箱发送
                	/*try {
						AmazonEmailUtil.sendMail(email, list.get(0));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
                }
            }
        }
    }
}

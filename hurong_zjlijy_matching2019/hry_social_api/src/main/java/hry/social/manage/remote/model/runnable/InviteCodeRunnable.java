package hry.social.manage.remote.model.runnable;

import hry.util.QrCodeUtils;
import hry.util.properties.PropertiesUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 注册用户初始化邀请二维码
 */
public class InviteCodeRunnable implements Runnable {

    private final Logger log = Logger.getLogger(InviteCodeRunnable.class);

    private String referralCode;  // 邀请码

    private String fileName;  // 二维码文件名称

    private InviteCodeRunnable() {
    }

    public InviteCodeRunnable(String referralCode, String fileName) {
        this.referralCode = referralCode;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            String content = PropertiesUtils.APP.getProperty("app.vueurl");
            if (StringUtils.isEmpty(content)) {
                return;
            }
            content += "?inviteCode=" + referralCode;
            String url = QrCodeUtils.generateCode(content, null, fileName, false, false);
            System.out.println("    ====>    二维码处理完成 content：" + content);
            System.out.println("    ====>    二维码处理完成 url：" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

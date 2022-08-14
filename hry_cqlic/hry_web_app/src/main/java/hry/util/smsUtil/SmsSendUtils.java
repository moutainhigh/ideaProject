package hry.util.smsUtil;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import hry.message.model.MessageType;
import hry.util.ThreadPool;
import hry.util.properties.PropertiesUtils;

/**
 * 发送短信工具类-新版
 */
public class SmsSendUtils {

    /**
     * 发送短信
     * @param smsSendParam
     * @return
     */
    public static String sendSms(SmsSendParam smsSendParam){
        String hryParam = "";
        String hryCode = "";
        String hrySmsCall = PropertiesUtils.APP.getProperty("app.sms.call");
        if (MessageType.MESSAGE_VERIFICATION_CODE.getKey().equals(smsSendParam.getHrySmstype())) {
            //6位短信验证码
            int length = 6;
            //生成随机码
            hryCode = RandomStringUtils.random(length, false, true);
            hryParam += "," + hryCode;
            smsSendParam.setHryCode(hryParam);
        } else {
            String param = smsSendParam.getHryParams();
            if (!StringUtils.isEmpty(param)) {
                hryParam += "," + smsSendParam.getHryParams();
            }
        }
        hryParam += "," + hrySmsCall;
        smsSendParam.setHryParams(hryParam.substring(1));
        smsSendParam.setHryCode(hryCode);
        SmsSendRunable smsSendRunable = new SmsSendRunable(smsSendParam);
        ThreadPool.exe(smsSendRunable);
        return hryCode;
    }
}

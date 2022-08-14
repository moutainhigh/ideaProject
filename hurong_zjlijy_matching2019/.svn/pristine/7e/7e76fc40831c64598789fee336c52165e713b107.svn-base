package hry.social.manage.remote.model.runnable;

import hry.manage.remote.model.RemoteResult;
import hry.social.manage.remote.api.yunxin.RemoteYunXinService;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 注册云信帐号异步处理
 * 只能在注册方法中调用，其它地方请另行编写
 */
public class YunXinRegistRunnable implements Runnable {

    private final Logger log = Logger.getLogger(YunXinRegistRunnable.class);

    private String accid;     // accid
    private String nickName;  // 昵称
    private Long customerId;  // 用户ID

    private YunXinRegistRunnable() {
    }

    public YunXinRegistRunnable(String accid, String nickName, Long customerId) {
        this.accid = accid;
        this.nickName = nickName;
        this.customerId = customerId;
    }

    @Override
    public void run() {
        try {
            RemoteYunXinService remoteYunXinService = (RemoteYunXinService) ContextUtil.getBean("remoteYunXinService");
            //1.注册云信账户------------------------------
            RemoteResult remoteResult = remoteYunXinService.registerYunXin(accid, nickName, customerId);
            Boolean success = remoteResult.getSuccess();
            if (!success) {
                System.out.println(toString() + "    ====>    注册云信帐号异步处理 失败");
                return;
            }
            System.out.println(toString() + "    ====>    注册云信帐号异步处理 成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "YunXinRegistRunnable{" + "accid='" + accid + '\'' + ", nickName='" + nickName + '\'' + ", customerId=" + customerId + '}';
    }
}

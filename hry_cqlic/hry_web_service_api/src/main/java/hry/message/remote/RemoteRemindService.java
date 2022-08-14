package hry.message.remote;
/**
 *
 * @ClassName: RemoteRemindService
 * @Description: 发送通知（短信,邮件,站内信）
 */
public interface RemoteRemindService {

    public boolean sendAllRemind(String param);

}

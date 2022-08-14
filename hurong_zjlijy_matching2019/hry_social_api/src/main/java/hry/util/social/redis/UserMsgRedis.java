package hry.util.social.redis;

import hry.social.manage.remote.model.barrage.BarrageMessage;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 用户消息实体
 */
public class UserMsgRedis implements Serializable{

	/** 用户ID */
	private String id;

	/** 弹幕消息集合 */
	private HashMap<String,BarrageMessage> messages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String,BarrageMessage> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String,BarrageMessage> messages) {
        this.messages = messages;
    }
}

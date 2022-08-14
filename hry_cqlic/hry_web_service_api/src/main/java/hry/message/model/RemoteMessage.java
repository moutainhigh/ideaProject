package hry.message.model;

import java.io.Serializable;
import java.util.Map;

public class RemoteMessage implements Serializable {
        private Map<String, String> param;// 模板替换的内容
        private String msgKey;//模板key
        private String msgType;//类别  1.站内信，2.短信,多个逗号分隔

        private String userId;//用户ID 可能空

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}

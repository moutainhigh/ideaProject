package hry.social.mq.producer.service;

import hry.manage.remote.model.User;

import java.io.Serializable;
import java.util.Map;

/**
 * @author javal
 * @title: NoticeMessage
 * @projectName hurong_matching2019
 * @description: 通知消息发送MQ实体
 * @date 2019/8/21 12:01
 */
public class NoticeMessage implements Serializable {

    /** 用户信息 **/
    private User user;

    /** 站内信触发点（业务Key）,数据字典配置 **/
    private int msgkey;

    /** 消息模版参数 **/
    private Map<String,String> paramers;

    /**
     * 用户信息
     */
    public User getUser() {
        return user;
    }

    /**
     * 用户信息
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 站内信触发点（业务Key）,数据字典配置
     */
    public int getMsgkey() {
        return msgkey;
    }

    /**
     * 站内信触发点（业务Key）,数据字典配置
     */
    public void setMsgkey(int msgkey) {
        this.msgkey = msgkey;
    }

    /**
     * 消息模版参数
     */
    public Map<String,String> getParamers() {
        return paramers;
    }

    /**
     * 消息模版参数
     */
    public void setParamers(Map<String,String> paramers) {
        this.paramers = paramers;
    }

    public NoticeMessage(User user, int msgkey, Map<String,String> paramers) {
        this.user = user;
        this.msgkey = msgkey;
        this.paramers = paramers;
    }
}

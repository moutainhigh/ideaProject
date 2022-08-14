package hry.trade.model;

import java.io.Serializable;

/**
 * @author javal
 * @title: NoticeMessage
 * @projectName hurong_matching2019
 * @description: 通知消息发送MQ实体
 * @date 2019/8/21 12:01
 */
public class AccountSyncMessage implements Serializable {

    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 币种
     */
    private String coinCode;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    @Override
    public String toString() {
        return "AccountMessage{" + "customerId=" + customerId + ", coinCode='" + coinCode + '\'' + '}';
    }
}

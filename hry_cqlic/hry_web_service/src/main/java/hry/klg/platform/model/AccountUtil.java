/**
 * 111
 */

package hry.klg.platform.model;

import java.io.Serializable;

public class AccountUtil implements Serializable {
    private  String redisAccountKey;
    private  String money;

    public AccountUtil(String redisAccountKey, String money) {
        this.redisAccountKey = redisAccountKey;
        this.money = money;
    }

    public String getRedisAccountKey() {
        return redisAccountKey;
    }

    public void setRedisAccountKey(String redisAccountKey) {
        this.redisAccountKey = redisAccountKey;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}

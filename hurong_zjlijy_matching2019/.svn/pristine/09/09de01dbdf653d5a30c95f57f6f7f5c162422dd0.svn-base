package hry.app.jedismsg;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import hry.app.account.service.OtcAccountService;
import hry.util.SpringUtil;
import hry.util.dto.Accountadd;
import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPubSub;

import java.util.List;


public class RedisToMysqlAccount extends JedisPubSub {

    private static Logger logger = Logger.getLogger(RedisToMysqlAccount.class);


    // 取得订阅的消息后的处理
    @Override
    public void onMessage(String channel, String message) {

        System.out.println("====================进入redis消息处理==================");
        System.out.println("消息内容为："+message);
        if(StringUtil.isNotEmpty(message)){ // 消息即为用户ID
          List<Accountadd> accountaddlist= JSON.parseArray(message, Accountadd.class);
            OtcAccountService otcAccountService= SpringUtil.getBean("otcAccountService");
            otcAccountService.saveAccount(accountaddlist);
            //保存到数据库
        }
    }
}

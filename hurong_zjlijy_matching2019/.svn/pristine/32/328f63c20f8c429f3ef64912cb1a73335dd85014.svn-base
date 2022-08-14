package hry.util;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/20 12:53
 * @Description: otc使用redis2
 */

public class RedisPool {

    public static JedisPool pool;
    static {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000L);
        config.setTestOnBorrow(false);
        try {
            Properties jdbc = new Properties();
            InputStream insjdbc = DbcontextHolder.class.getClassLoader().getResourceAsStream("jdbc.properties");
            jdbc.load(insjdbc);
            String url = jdbc.getProperty("redis2.url");
            int port = Integer.parseInt(jdbc.getProperty("redis2.port"));
            String password = jdbc.getProperty("redis2.password");
            // 构造池
            pool = new JedisPool(config,url,port, 100000,password); //容忍的超时时间
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}

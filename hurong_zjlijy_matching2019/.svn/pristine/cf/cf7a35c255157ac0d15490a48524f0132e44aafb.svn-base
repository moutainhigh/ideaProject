package hry.cm5.util;


import hry.util.sys.ContextUtil;
import redis.clients.jedis.JedisPool;

public class CmRedis {

    public static JedisPool JEDIS_POOL = null;

    static {

        try{
            JEDIS_POOL=   (JedisPool) ContextUtil.getBean("jedisPool");

        }catch (Exception e){
            JEDIS_POOL = null;
        }

    }

}
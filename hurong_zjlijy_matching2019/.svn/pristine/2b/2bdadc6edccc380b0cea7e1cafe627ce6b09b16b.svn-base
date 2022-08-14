package hry.util;


import hry.util.sys.ContextUtil;
import redis.clients.jedis.JedisPool;


public class OtcRedis {

    public static JedisPool OTC_JEDIS_POOL = null;

    static {

        try{
            OTC_JEDIS_POOL=   (JedisPool) ContextUtil.getBean("jedisPool");

        }catch (Exception e){
            OTC_JEDIS_POOL = null;
        }

    }

}
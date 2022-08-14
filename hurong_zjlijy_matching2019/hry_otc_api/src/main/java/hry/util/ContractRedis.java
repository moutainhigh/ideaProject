package hry.util;

import hry.contract.pojo.RedisSource;
import hry.util.sys.ContextUtil;
import redis.clients.jedis.JedisPool;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">HeC</a>
 * @date 2018/12/25 11:25
 */
public class ContractRedis {


    public static final JedisPool JEDIS_POOL = (JedisPool) ContextUtil.getBean(RedisSource.BEAN_NAME);


}
package hry.app.lend.model;

import hry.util.sys.ContextUtil;
import redis.clients.jedis.JedisPool;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/10/25 11:39
 * @Description: 杠杆redis源
 */
public class LendRedis {

    public static final JedisPool JEDIS_POOL = (JedisPool) ContextUtil.getBean("jedisPool");

}
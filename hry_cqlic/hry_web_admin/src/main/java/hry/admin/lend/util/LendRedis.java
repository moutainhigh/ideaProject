package hry.admin.lend.util;

import hry.util.sys.ContextUtil;
import redis.clients.jedis.JedisPool;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/7/27 10:21
 * @Description: 杠杆redis配置中心
 */
public class LendRedis {

    public static final JedisPool JEDIS_POOL = (JedisPool) ContextUtil.getBean("jedisPool");


}
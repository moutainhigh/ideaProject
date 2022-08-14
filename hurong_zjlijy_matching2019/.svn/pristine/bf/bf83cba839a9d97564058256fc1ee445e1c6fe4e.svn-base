/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: Liu Shilei
 * @version: V1.0
 * @Date: 2016年4月11日 下午4:42:00
 */
package hry.util;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.nutz.lang.Mirror;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Liu Shilei
 * @Date : 2016年10月27日 上午10:53:39
 */
public class RedisUtil<T> {

    private JedisPool jedisPool;

    private Class<T> clazz;

    private String clazzName;

    private final String DB = "RedisDB:";

    private RuntimeSchema<T> schema;

    public RedisUtil(Class<T> clazz) {
        this.clazz = clazz;
        this.clazzName = DB + this.clazz.getName().replace(".", ":");
        this.schema = RuntimeSchema.createFrom(clazz);
        this.jedisPool = (JedisPool) SpringUtil.getBean("jedisPool");
    }

    public RedisUtil(String ip, int port) {
        try {

            if (jedisPool == null) {
                JedisPoolConfig config = new JedisPoolConfig();
                // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
                // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
                config.setMaxTotal(10000);
                // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
                config.setMaxIdle(2000);
                // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
                config.setMaxWaitMillis(1000 * 100);
                config.setTestOnBorrow(true);
                jedisPool = new JedisPool(config, ip, port, 100000, "Credit2016Admin");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    /**
     * 获得一个对象
     * <p>
     * TODO
     * </p>
     *
     * @author: Liu Shilei
     * @param: @param id
     * @param: @return
     * @return: T
     * @Date : 2016年10月27日 上午11:21:49
     * @throws:
     */
    public T get(String id) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String key = this.clazzName + ":" + id;
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                T t = clazz.newInstance();
                ProtostuffIOUtil.mergeFrom(bytes, t, schema);
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 存入一个对象
     * <p>
     * TODO
     * </p>
     *
     * @author: Liu Shilei
     * @param: @param t
     * @param: @param id
     * @return: void
     * @Date : 2016年10月27日 上午11:14:10
     * @throws:
     */
    public void put(T t, String id) {

        String key;
        if (id == null) {
            Mirror<?> mirror = Mirror.me(t.getClass());
            //如果创建时间存在就不能再次修改
            Object obj = mirror.getValue(t, "id");
            key = this.clazzName + ":" + obj;
        } else {
            key = this.clazzName + ":" + id;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key.getBytes());
            byte[] bytes = ProtostuffIOUtil.toByteArray(t, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            String set = jedis.set(key.getBytes(), bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    /**
     * <p>
     * 返回同类的所有对象List<T> list
     * </p>
     *
     * @author: Liu Shilei
     * @param:
     * @return: void
     * @Date : 2016年10月27日 上午11:20:00
     * @throws:
     */
    public List<T> list() {
        Jedis jedis = null;
        List<T> list = new ArrayList<T>();
        try {
            jedis = jedisPool.getResource();

            Set<String> keys = jedis.keys(this.clazzName + "*");
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                byte[] bytes = jedis.get(iterator.next().getBytes());
                if (bytes != null) {
                    T t = clazz.newInstance();
                    ProtostuffIOUtil.mergeFrom(bytes, t, schema);
                    list.add(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return list;
    }

    /**
     * <p>
     * 清空同类的所有对象
     * </p>
     *
     * @author: Liu Shilei
     * @param:
     * @return: void
     * @Date : 2016年10月27日 上午11:20:00
     * @throws:
     */
    public void clear() {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            Set<String> keys = jedis.keys(this.clazzName + "*");
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                jedis.del(iterator.next());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

    }

    public void delete(String id) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String key = this.clazzName + ":" + id;
            Set<String> keys = jedis.keys(key);
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                jedis.del(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }


    }

}

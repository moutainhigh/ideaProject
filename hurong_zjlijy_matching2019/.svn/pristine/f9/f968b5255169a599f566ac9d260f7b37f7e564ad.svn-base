/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Yuan Zhicheng
 * @version:      V1.0
 * @Date:        2015年9月16日 上午11:04:39
 */
package hry.app.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.app.jedismsg.RedisToMysqlAccount;
import hry.redis.common.utils.RedisService;
import hry.util.*;
import hry.util.common.SpringUtil;
import hry.util.constant.DealConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoaderListener;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import java.util.Map;

/**
 *
 * @author CHINA_LSL
 *
 */
public class StartupListener extends ContextLoaderListener {

	private static Log log=LogFactory.getLog(StartupListener.class);

	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				/**
				 * 订阅redis消息
				 */
				subscribe("cacheLanguage");
			}
		});

		initLanguage();

		initConfig();
		//启动
		t1.setDaemon(true);
		ThreadPool.exe(t1);

		// 订阅OTC完成订单之后的消息
		new Thread(() -> {
			log.info("..............................OTC订阅启动..............................");
			try (Jedis jedis = OtcRedis.OTC_JEDIS_POOL.getResource()) {
				RedisConsumer r = new RedisConsumer();
				jedis.subscribe(r, "otcCompletionRate");
			}catch(Exception e){

			}
		}).start();

	}

	// 初始化Otc币种信息
	private static void initConfig() {
		//账户入库
		ThreadPoolOtc.exe(() -> {
			try (Jedis jedis = OtcRedis.OTC_JEDIS_POOL.getResource()) {
				jedis.subscribe(new RedisToMysqlAccount(), DealConstant.JedisPubSub_SAVEACCONT);
			}catch(Exception e){

			}

		});
	}

	private void initLanguage() {
		log.info("同步国际化词条..........");
		RedisService redisService =  SpringUtil.getBean("redisService");
		String sysLanguage = redisService.hget("new_app_dic", "sysLanguage");
		if(!StringUtils.isEmpty(sysLanguage)) {
			JSONArray parseArray = JSON.parseArray(sysLanguage);
			if(parseArray!=null) {
				for(int i =0 ; i<parseArray.size();i++) {
					JSONObject jsonObject = parseArray.getJSONObject(i);
					String key = jsonObject.getString("value");
					//  电脑端词汇加载
					//查询语言包
					Map<String, String> hgetall_pc = redisService.hgetall("language:"+key);
					//添加到缓存中
					HryCache.pc_cache_language.put(key, hgetall_pc);

					// 手机端词汇加载
					//查询语言包
					Map<String, String> hgetall_app = redisService.hgetall("app_language:"+key);
					//添加到缓存中
					HryCache.app_cache_language.put(key, hgetall_app);
				}
			}
		}

	}

	/**
	 * 订阅redis消息
	 */
	private void subscribe (String channel) {
		RedisService redisService = SpringUtil.getBean("redisService");
		redisService.subscribe(new SubLanguageCacheListener(), channel);
	}


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}



}

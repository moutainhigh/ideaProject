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
import hry.app.kline.netty.controller.StartWebSocket;
import hry.redis.common.utils.RedisService;
import hry.util.HryCache;
import hry.util.ThreadPool;
import hry.util.common.SpringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoaderListener;

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
				subscribe("websocketLogin");
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				/**
				 * 订阅redis消息
				 */
				subscribeLanguage("cacheLanguage");

			}
		});
		initLanguage();
		//启动
		t1.setDaemon(true);
		t1.setDaemon(true);
		ThreadPool.exe(t1);
		ThreadPool.exe(t2);

		ThreadPool.exe(new StartWebSocket());
	}

	/**
	 * 订阅redis消息---登录
	 */
	private void subscribe (String channel) {
		RedisService redisService = SpringUtil.getBean("redisService");
		redisService.subscribe(new SubLoginCacheListener(), channel);
	}
	/**
	 * 订阅redis消息---多语言缓存
	 */
	private void subscribeLanguage (String channel) {
		RedisService redisService = SpringUtil.getBean("redisService");
		redisService.subscribe(new SubLanguageCacheListener(), channel);
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
					if(!hgetall_pc.isEmpty()){
						//添加到缓存中
						HryCache.pc_cache_language.put(key, hgetall_pc);
					}
					// 手机端词汇加载
					//查询语言包
					Map<String, String> hgetall_app = redisService.hgetall("app_language:"+key);
					if(!hgetall_app.isEmpty()){
						//添加到缓存中
						HryCache.app_cache_language.put(key, hgetall_app);
					}
				}
			}
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}



}

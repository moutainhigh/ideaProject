package hry.otc.listener;

import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;

import hry.core.constant.StartInitConstant;
import hry.core.listener.StartLoad;
import hry.core.listener.StartupService;
import util.RedisConsumer;

import javax.annotation.Resource;

public class StartupManage implements StartupService {
	
	public final static String AppName = "hurong_otc";
	public final static String AppKey = "otc";

	private final static Logger log = Logger.getLogger(StartupManage.class);
	@Override
	public void start() {
		//加载noLogin注解方法
		//StartLoad.loadNoLoginAnnotations(StartInitConstant.noLoginSet,AppKey);

		// 订阅OTC完成订单之后的消息
		new Thread(() -> {
			RedisService redisService = SpringUtil.getBean("redisService");
			RedisConsumer r = new RedisConsumer();
			redisService.subscribe(r, "otcCompletionRate");
		}).start();
	}
}

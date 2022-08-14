package hry.web.listener;

import hry.core.listener.StartupService;
import hry.ico.service.IDelayedOrderService;
import hry.util.sys.ContextUtil;
import hry.web.app.service.AppHolidayConfigService;
import hry.web.cache.CacheManageCallBack;
import hry.web.cache.CacheManageService;
import hry.web.quartz.QuartzEngine;
import org.apache.log4j.Logger;

public class StartupManage implements StartupService {
	
	public final static String AppName = "hurong_web";
	public final static String AppKey = "web";

	private final static Logger log = Logger.getLogger(StartupManage.class);
	@Override
	public void start() {


		CacheManageCallBack callback=(CacheManageCallBack)ContextUtil.getBean("cacheManageCallBack");
		//初始化系统配置信息缓存
		CacheManageService appConfigService=(CacheManageService)ContextUtil.getBean("appConfigService");
		appConfigService.initCache(callback);
		//初始化杠杆配置信息缓存
		CacheManageService appLendConfigService=(CacheManageService)ContextUtil.getBean("appLendConfigService");
		appLendConfigService.initCache(callback);
		//初始化 网站配置信息缓存
		CacheManageService appCaheAreaService=(CacheManageService)ContextUtil.getBean("appCaheAreaServiceImpl");
		//初始化地区数字字典缓
		//appCaheAreaService.initCache(callback);
		//初始化银行网站配置信息缓存
		CacheManageService appDicMultilevelService=(CacheManageService) ContextUtil.getBean("appDicMultilevelService");
		appDicMultilevelService.initCache(callback);
		//初始化银行网站配置信息缓存
		CacheManageService appDicOnelevelService=(CacheManageService) ContextUtil.getBean("appDicOnelevelService");
		appDicOnelevelService.initCache(callback);
		//初始化banner缓存   新版banner缓存不需要初始化
		/*CacheManageService appBannerService=(CacheManageService) ContextUtil.getBean("appBannerService");
		appBannerService.initCache(callback);*/
		//初始化自动交易缓存
		CacheManageService exRobotService=(CacheManageService) ContextUtil.getBean("exRobotService");
		exRobotService.initCache(callback);
		//初始化ex_product缓存
		/*CacheManageService exProductService=(CacheManageService) ContextUtil.getBean("exProductService");
		exProductService.initCache(callback);*/
		
		//加载定时器
		QuartzEngine.startUp();
		
		log.info("初始华节假日配置信息..................");
		AppHolidayConfigService appHolidayConfigService = (AppHolidayConfigService) ContextUtil.getBean("appHolidayConfigService");
		appHolidayConfigService.initCache();

		IDelayedOrderService iDelayedOrder= (IDelayedOrderService)ContextUtil.getBean("iDelayedOrderService");
		iDelayedOrder.initDelayOrder();//初始化待释放的入队
		log.info("初始化待释放订单入队================================================");
	}
	
	
	
	
}

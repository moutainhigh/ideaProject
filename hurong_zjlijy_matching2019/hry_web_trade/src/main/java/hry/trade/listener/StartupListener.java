/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Yuan Zhicheng
 * @version:      V1.0 
 * @Date:        2015年9月16日 上午11:04:39
 */
package hry.trade.listener;

import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.exchange.product.model.ExCointoCoin;
import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.entrust.dao.CommonDao;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.model.TradeRedis;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.AppUtils;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public class StartupListener extends ContextLoaderListener {
	private final static Logger log = Logger.getLogger(StartupListener.class);

	public void contextInitialized(ServletContextEvent event) {

		super.contextInitialized(event);
		// 初始化应用程序工具类
		AppUtils.init(event.getServletContext());
		log.info("--------------------------------------------------------------------------------");
		log.info("---------------------------------加载应用app----------------------------------------");
		log.info("------------------" + PropertiesUtils.APP.getProperty("app.loadApp") + "-----------------");
		// 加载每个应用的启动方法
		 initImportJob();
		 

		String isStartExRobot4 = PropertiesUtils.APP.getProperty("app.isStartExRobot4");
		if (!StringUtils.isEmpty(isStartExRobot4) && isStartExRobot4.equals("true")) {
			conmonApiJob();
			klineRobotJob();
			log.info("------------------深度机器人定时器开启-----------------");
			deepRobotJob();
		}



		// 定时调用定时向redis报告自己的是否存活
		ScheduleJob jobRunTimeTradeState= new ScheduleJob();
		jobRunTimeTradeState.setSpringId("checkTradeService");
		jobRunTimeTradeState.setMethodName("testTradeIsLiving");
		QuartzManager.addJob("jobRunTimeTradeState", jobRunTimeTradeState, QuartzJob.class, "0/5 * * * * ?");// 每小时

		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		redisService.delete("deal:stop");


		String isStartExRobotmingRobot = PropertiesUtils.APP.getProperty("app.isStartExRobotmingRobot");
		if (!StringUtils.isEmpty(isStartExRobotmingRobot) && isStartExRobotmingRobot.equals("true")) {

			mingRobotJob();
		}
	}
    public void initImportJob(){
    	// 缓存定时入库
		ScheduleJob reidsToMysql = new ScheduleJob();
		reidsToMysql.setSpringId("exOrderInfoService");
		reidsToMysql.setMethodName("reidsToMysqlmq");
		QuartzManager.addJob("reidsToMysql", reidsToMysql, QuartzJob.class, "0/2 * * * * ?");// 两秒
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 缓存定时入reids日志，委托定时入库
		ScheduleJob reidsToredisLog = new ScheduleJob();
		reidsToredisLog.setSpringId("exOrderInfoService");
		reidsToredisLog.setMethodName("reidsToredisLogmq");
		QuartzManager.addJob("reidsToredisLog", reidsToredisLog, QuartzJob.class, "0/2 * * * * ?");// 两秒
																									

		// 委托单
		ScheduleJob jobRunTimepushMarket = new ScheduleJob();
		jobRunTimepushMarket.setSpringId("webSocketScheduleService");
		jobRunTimepushMarket.setMethodName("pushMarket");
		QuartzManager.addJob("jobRunTimepushMarket", jobRunTimepushMarket, QuartzJob.class, "0/1 * * * * ?");// 两秒

		ExEntrustService exEntrustService = (ExEntrustService) ContextUtil.getBean("exEntrustService");
		long start1 = System.currentTimeMillis();
		exEntrustService.tradeInit();
		long end = System.currentTimeMillis();
		System.out.println("初始化交易数据：");
		System.out.println(end - start1);

		// 启动的时候要把这个设置成1，不然刚启动完之后，所有的数据都没了，必须下一单才会出来
		CommonDao commonDao = (CommonDao) ContextUtil.getBean("commonDao");
		List<ExCointoCoin> listExCointoCoin = commonDao.getExointocoinAll();
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
		for (ExCointoCoin exCointoCoin : listExCointoCoin) {
			redisTradeService.save(TradeRedis.getEntrustTimeFlag(exCointoCoin.getCoinCode(), exCointoCoin.getFixPriceCoinCode()), "1");
		}
    	
    	
    	
    }
	public void conmonApiJob() {

		// 定时去第三方拿最新价
		ScheduleJob thirdApiPrice = new ScheduleJob();
		thirdApiPrice.setSpringId("conmonApiService");
		thirdApiPrice.setMethodName("getCurrentPriceByApi");
		QuartzManager.addJob("thirdApiPrice", thirdApiPrice, QuartzJob.class, "0/5 * * * * ?");// 两秒

	}

	public void klineRobotJob() {
		// K线机器人下单定时器
		String addRobotFrequency = PropertiesUtils.APP.getProperty("app.addRobotFrequency");
		if (StringUtils.isEmpty(addRobotFrequency)) {
			addRobotFrequency = "0/5 * * * * ?";
		}
		ScheduleJob klineRobotExEntrust = new ScheduleJob();
		klineRobotExEntrust.setSpringId("robotKlineService");
		klineRobotExEntrust.setMethodName("klineAutoAddExEntrust4");
		QuartzManager.addJob("klineAutoAddExEntrust4", klineRobotExEntrust, QuartzJob.class, addRobotFrequency);// 两秒

	}

	public void deepRobotJob() {
		// K线机器人下单定时器
		String addRobotFrequency = PropertiesUtils.APP.getProperty("app.addRobotFrequency");
		if (StringUtils.isEmpty(addRobotFrequency)) {
			addRobotFrequency = "0/5 * * * * ?";
		}
		// 深度机器人下单和撤销差值率的单子
		ScheduleJob deepRobotExEntrustJob = new ScheduleJob();
		deepRobotExEntrustJob.setSpringId("robotDepthService");// 下单定时器
		deepRobotExEntrustJob.setMethodName("deepRobot");
		QuartzManager.addJob("deepRobotExEntrustJob", deepRobotExEntrustJob, QuartzJob.class, addRobotFrequency);// 两秒
		
		// 深度机器人定时撤单
		ScheduleJob deepCancelAutoAddExEntrustJob = new ScheduleJob();
		deepCancelAutoAddExEntrustJob.setSpringId("robotDepthService");// 下单定时器
		deepCancelAutoAddExEntrustJob.setMethodName("deepCancelAutoAddExEntrust");
		QuartzManager.addJob("deepCancelAutoAddExEntrustJob", deepCancelAutoAddExEntrustJob, QuartzJob.class, addRobotFrequency = "0/47 * * * * ?");// 两秒
		// 深度机器人定时修复redis交易大厅的委托单
		ScheduleJob xiufudeepRobotExEntrustJob = new ScheduleJob();
		xiufudeepRobotExEntrustJob.setSpringId("robotDepthService");// 下单定时器
		xiufudeepRobotExEntrustJob.setMethodName("repairdeep");
		QuartzManager.addJob("xiufudeepRobotExEntrustJob", xiufudeepRobotExEntrustJob, QuartzJob.class, addRobotFrequency = "0/59 * * * * ?");// 两秒

	}
	public void mingRobotJob(){
		String classJob = "hry.minigrobot.robot.MiningRobotJob";
		ScheduleJob miningRobotJobJob = new ScheduleJob();
		miningRobotJobJob.setBeanClass(classJob);
		miningRobotJobJob.setMethodName("executeRobotmq");
		QuartzManager.addJob("miningRobotJobJob", miningRobotJobJob, QuartzJob.class,  "0/30 * * * * ?");// 两秒

		}
}

/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: Yuan Zhicheng
 * @version: V1.0
 * @Date: 2015年9月16日 上午11:04:39
 */
package hry.admin.listener;

import com.alibaba.fastjson.JSONObject;
import hry.admin.c2c.service.C2cCoinService;
import hry.admin.dic.service.AppDicService;
import hry.admin.exchange.service.ExCointoCoinService;
import hry.admin.exchange.service.ExCointoMoneyService;
import hry.admin.exchange.service.ExLawcoinService;
import hry.admin.index.service.AppYesterdayDataService;
import hry.admin.mining.service.MingTimerService;
import hry.admin.web.service.AppBannerService;
import hry.admin.web.service.AppConfigService;
import hry.admin.web.service.AppFileUploadService;
import hry.admin.web.service.AppLanguageService;
import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.listener.redis.SubRedisCacheListener;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import hry.util.mybatis.AnnotationUtil;
import hry.util.mybatis.ClassUtil;
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

    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        try {

            // 获取特定包下所有的类(包括接口和类)
            List<Class<?>> clsList = ClassUtil.getAllClassByPackageName("hry");

            //检查表字段
            AnnotationUtil.validAnnotation(clsList);

            //监听cache更新
            Thread subRedisCache = new Thread(new Runnable() {
                @Override
                public void run() {
                    RedisService redisService = SpringUtil.getBean("redisService");
                    //发送前台初始化消息
                    redisService.publish("cacheLanguage","frontLangeInit");
                    //监听config消息
                    redisService.subscribe(new SubRedisCacheListener(),"cache");
                }
            });
            subRedisCache.start();


            //初始化超级管理员账号
//            AppUserService appUserService = SpringUtil.getBean("appUserService");
//            appUserService.initAdmin();

            //初始化缓存app_config
            AppConfigService appConfigService = SpringUtil.getBean("appConfigService");
            appConfigService.initCache();

            //初始化new_app_dic缓存
            AppDicService appDicService = SpringUtil.getBean("appDicService");
            appDicService.flushRedis();
            appDicService.initAreaCache();

            // 多语种操作
            AppLanguageService appLanguageService = SpringUtil.getBean("appLanguageService");
            // 初始化多语种-以简体中文为基础，向其他语种同步数据
            appLanguageService.synchronizedLangData();
            // 初始化app_language
            appLanguageService.initCache();

            // 初始化banner数据
            AppBannerService bannerService = SpringUtil.getBean("appBannerService");
            bannerService.initCache();

            //初始化C2C币种配置
            C2cCoinService c2cCoinService = SpringUtil.getBean("c2cCoinService");
            c2cCoinService.flushRedis();

            //初始化兑换汇率
            ExCointoMoneyService exCointoMoneyService = SpringUtil.getBean("exCointoMoneyService");
            exCointoMoneyService.initRedis();

            //初始化币种缓存
            ExCointoCoinService exCointoCoinService = SpringUtil.getBean("exCointoCoinService");
            exCointoCoinService.initRedisCode();

            //初始化法币缓存
            ExLawcoinService exLawcoinService = SpringUtil.getBean("exLawcoinService");
            exLawcoinService.initRedis();

            //初始化挖矿定时器
            MingTimerService mingTimerService = SpringUtil.getBean("mingTimerService");
            mingTimerService.initRedis();

            // 初始化后台桌面数据
            AppYesterdayDataService yesterdayDataService = SpringUtil.getBean("appYesterdayDataService");
            yesterdayDataService.initRedis();

            //初始化app版本管理
            AppFileUploadService appFileUploadService = SpringUtil.getBean("appFileUploadService");
            appFileUploadService.initRedis();

            //启动定时任务
            ScheduleJob job30 = new ScheduleJob();
            job30.setBeanClass("hry.listener.ServerManagement");
            job30.setMethodName("heartbeatConnection");
            QuartzManager.addJob("heartbeatConnection", job30, QuartzJob.class, "0/10 * * * * ?");

            // 锁仓定时释放功能
            RedisService redisService = SpringUtil.getBean("redisService");
            String miningTimer = redisService.get("Mining:Timer2");
            if (!StringUtils.isEmpty(miningTimer)) {
                JSONObject object = JSONObject.parseObject(miningTimer);
                Integer unlockIfStart = object.getInteger("unlockIfStart");
                if (unlockIfStart == 1) {
                    ScheduleJob timingUnlockAccountCold = new ScheduleJob();
                    timingUnlockAccountCold.setSpringId("exDmUnlockHistoryService");
                    timingUnlockAccountCold.setMethodName("timingUnlockAccountCold");
                    QuartzManager.addJob("timingUnlockAccountCold", timingUnlockAccountCold, QuartzJob.class, "0 3 0 * * ?"); // 每天晚上12点3分执行
                } else {
                    QuartzManager.removeJob("timingUnlockAccountCold");
                }
            }

            //后台桌面统计启动定时任务
            ScheduleJob adminIndex = new ScheduleJob();
            adminIndex.setBeanClass("hry.listener.HomeManagement");
            adminIndex.setMethodName("getYesterdayData");
            QuartzManager.addJob("getYesterdayData", adminIndex, QuartzJob.class, "0 2 0 * * ?"); // 每天12点2分执行一次执行一次

            /**
             * 拉取实时价格
             */
            ScheduleJob pullCoinPrice = new ScheduleJob();
            pullCoinPrice.setSpringId("exProductService");
            pullCoinPrice.setMethodName("pullCoinPrice");
            QuartzManager.addJob("pullCoinPrice", pullCoinPrice, QuartzJob.class,"0/10 * * * * ?");

            /*//互融云SaaS------币种详情
            ScheduleJob hrySaaSCoinInfo = new ScheduleJob();
            hrySaaSCoinInfo.setSpringId("exProductService");
            hrySaaSCoinInfo.setMethodName("hrySaaSCoinInfo");
            QuartzManager.addJob("hrySaaSCoinInfo", hrySaaSCoinInfo, QuartzJob.class,"0/10 * * * * ?");*/

            ScheduleJob jobRunTimetimingUnlockAccountCold = new ScheduleJob();
            jobRunTimetimingUnlockAccountCold.setSpringId("appGloballogService");
            jobRunTimetimingUnlockAccountCold.setMethodName("deleteOnTime");
            QuartzManager.addJob("deleteOnTime", jobRunTimetimingUnlockAccountCold, QuartzJob.class,"0 5 3 * * ?");// 每天3点5分触发

            /**
             * 给排单超出得订单添加糖果
             * 每天0点执行
             */
            /*ScheduleJob klgSellAddCandy = new ScheduleJob();
            klgSellAddCandy.setBeanClass("hry.listener.KlgManagement");
            klgSellAddCandy.setMethodName("klgSellAddCandy");
            QuartzManager.addJob("klgSellAddCandy", klgSellAddCandy, QuartzJob.class,"0 0 0 * * ?");*/
            //QuartzManager.addJob("klgSellAddCandy", klgSellAddCandy, QuartzJob.class,"0 */20 * * * ?");

            /**
             * 修改超时未支付的买单状态为已作废4
             * 每5分钟执行
             */
//            ScheduleJob updateOverdueOrder = new ScheduleJob();
//            updateOverdueOrder.setBeanClass("hry.listener.KlgManagement");
//            updateOverdueOrder.setMethodName("updateOverdueOrder");
//            //QuartzManager.addJob("updateOverdueOrder", updateOverdueOrder, QuartzJob.class,"0 */30 * * * ?");
//            QuartzManager.addJob("updateOverdueOrder", updateOverdueOrder, QuartzJob.class,"0 */5 * * * ?");
            
            /**
             * 预测数据
             * 每天23点执行
             */
//            ScheduleJob forecast = new ScheduleJob();
//            forecast.setBeanClass("hry.listener.KlgManagement");
//            forecast.setMethodName("forecast");
//            QuartzManager.addJob("forecast", forecast, QuartzJob.class,"0 0 23 * * ?");
            
            /**
             * 更新抽奖期数
             * 每周一0点执行
             */
//            ScheduleJob updateDrawing = new ScheduleJob();
//            updateDrawing.setBeanClass("hry.listener.KlgManagement");
//            updateDrawing.setMethodName("updateDrawing");
//            QuartzManager.addJob("updateDrawing", updateDrawing, QuartzJob.class,"0 0 0 ? * MON");
//            //QuartzManager.addJob("updateDrawing", updateDrawing, QuartzJob.class,"0 */5 * * * ?");
            
            /**
             * 计算开奖号码 
             * 每周一下午6点执行
             */
//            ScheduleJob CalculationWinningNumbers = new ScheduleJob();
//            CalculationWinningNumbers.setBeanClass("hry.listener.KlgManagement");
//            CalculationWinningNumbers.setMethodName("CalculationWinningNumbers");
//            QuartzManager.addJob("CalculationWinningNumbers", CalculationWinningNumbers, QuartzJob.class,"0 0 18 ? * MON");
//            //QuartzManager.addJob("CalculationWinningNumbers", CalculationWinningNumbers, QuartzJob.class,"0 */6 * * * ?");
            
            /**
             * 对比抽奖号码，添加中奖信息 
             * 每周一下午6点30执行
             */
//            ScheduleJob winningThePrize = new ScheduleJob();
//            winningThePrize.setBeanClass("hry.listener.KlgManagement");
//            winningThePrize.setMethodName("winningThePrize");
//            QuartzManager.addJob("winningThePrize", winningThePrize, QuartzJob.class,"0 30 18 ? * MON");
//            //QuartzManager.addJob("winningThePrize", winningThePrize, QuartzJob.class,"0 */5 * * * ?");
            
            /**
             * 对比抽奖号码，添加中奖信息 
             * 每周一下午7点执行
             */
//            ScheduleJob bonus = new ScheduleJob();
//            bonus.setBeanClass("hry.listener.KlgManagement");
//            bonus.setMethodName("bonus");
//            QuartzManager.addJob("bonus", bonus, QuartzJob.class,"0 0 19 ? * MON");
//            //QuartzManager.addJob("bonus", bonus, QuartzJob.class,"0 */5 * * * ?");
            
            
            
            
            
            /**
             * 排队超时的买单增加利息
             * 每天凌晨1点执行
             * 
             * 需求改动 ，利息去掉
             */
            /*ScheduleJob klgAddInterestBuyOrder = new ScheduleJob();
            klgAddInterestBuyOrder.setBeanClass("hry.listener.KlgManagement");
            klgAddInterestBuyOrder.setMethodName("klgAddInterestBuyOrder");
            QuartzManager.addJob("klgAddInterestBuyOrder", klgAddInterestBuyOrder, QuartzJob.class,"0 0 1 * * ?");*/
            //QuartzManager.addJob("klgAddInterestBuyOrder", klgAddInterestBuyOrder, QuartzJob.class,"0 */20 * * * ?");
            
            
            
            
            /*   //定时同步数据库 平台币信息
            ScheduleJob jobTimingPlatformCurrencySyn = new ScheduleJob();
            jobTimingPlatformCurrencySyn.setSpringId("icoRuleDetailedService");
            jobTimingPlatformCurrencySyn.setMethodName("timingPlatformCurrencySyn");
            QuartzManager.addJob("timingPlatformSyn", jobTimingPlatformCurrencySyn, QuartzJob.class,"0 0/1 * * * ?");// 每分钟
*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

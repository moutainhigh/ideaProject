package hry.listener;

import javax.servlet.ServletContextEvent;

import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import org.springframework.web.context.ContextLoaderListener;

import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.util.sys.AppUtils;

import java.util.Map;

/**
 * @auther zhouming
 * @date 2019/7/4 13:54
 * @Version 1.0
 *
 */
//  Cron表达式范例
//  每隔5秒执行一次：   */5 * * * * ?
//  每隔1分钟执行一次：  0 */1 * * * ?
//  每天23点执行一次：  0 0 23 * * ?
//  每天凌晨1点执行一次： 0 0 1 * * ?
//  每月1号凌晨1点执行一次：   0 0 1 1 * ?
//  每月最后一天23点执行一次：  0 0 23 L * ?
//  每周星期天凌晨1点实行一次：  0 0 1 ? * L
//  在26分、29分、33分执行一次：   0 26,29,33 * * * ?
//  每天的0点、13点、18点、21点都执行一次： 0 0 0,13,18,21 * * ?
//  每隔5分钟执行一次：    0 0/5 * * * ?
public class StartupListener extends ContextLoaderListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        //初始化应用程序工具类
        AppUtils.init(event.getServletContext());
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("---------------------------------加载Listener----------------------------------------");

        RedisService redisService = SpringUtil.getBean("redisService");
        Map<String,String> moduleMap = redisService.getMap("APP:MODULE");
        //是否开启商城业务
        if (moduleMap != null && "true".equalsIgnoreCase(moduleMap.get("app.module.mall"))) {
            //生成积分账户
            ScheduleJob registerListener = new ScheduleJob();
            registerListener.setBeanClass("hry.listener.mall.MallManagement");
            registerListener.setMethodName("registerListener");
            QuartzManager.addJob("registerListener", registerListener, QuartzJob.class,"0 */1 * * * ?");

            /**
             * 胡一茗-----充值电话费微星订单查询
             */
            ScheduleJob payWeiOrderListener = new ScheduleJob();
            payWeiOrderListener.setBeanClass("hry.listener.mall.MallManagement");
            payWeiOrderListener.setMethodName("payWeiOrderListener");
            QuartzManager.addJob("payWeiOrderListener", payWeiOrderListener, QuartzJob.class,"0 */5 * * * ?");

        }

        /**
         * 清除过期缓存奖励
         * 每天0点执行
         */
        ScheduleJob cleanupRewardJob = new ScheduleJob();
        cleanupRewardJob.setBeanClass("hry.listener.miningreward.CleanupRewardJob");
        cleanupRewardJob.setMethodName("cleanupCacheReward");
        QuartzManager.addJob("cleanupCacheReward", cleanupRewardJob, QuartzJob.class,"0 0 1 * * ?");

        //是否开启矿机业务
        //if (moduleMap != null && "true".equalsIgnoreCase(moduleMap.get("app.module.cloudmining"))) {
        if (false) {
            /**
             * 云矿机 矿机产币  按天
             * 每天0点10分执行
             */
            /*ScheduleJob minerCoinageByDay = new ScheduleJob();
            minerCoinageByDay.setBeanClass("hry.listener.cloudmining.CmManagement");
            minerCoinageByDay.setMethodName("minerCoinageByDay");
            QuartzManager.addJob("minerCoinageByDay", minerCoinageByDay, QuartzJob.class,"0 15 0 * * ?");*/
            //QuartzManager.addJob("minerCoinageByDay", minerCoinageByDay, QuartzJob.class,"0 0 0-23 * * ?");
            //QuartzManager.addJob("minerCoinageByDay", minerCoinageByDay, QuartzJob.class,"0 */5 * * * ?");
            /**
             * 云矿机 矿机状态更新
             * 每天0-23点 整点执行
             */
            /*ScheduleJob minerWaitAndCloseTiming = new ScheduleJob();
            minerWaitAndCloseTiming.setBeanClass("hry.listener.cloudmining.CmManagement");
            minerWaitAndCloseTiming.setMethodName("minerWaitAndCloseTiming");
            QuartzManager.addJob("minerWaitAndCloseTiming", minerWaitAndCloseTiming, QuartzJob.class,"0 5 0-23 * * ?");*/
            //QuartzManager.addJob("minerWaitAndCloseTiming", minerWaitAndCloseTiming, QuartzJob.class,"0 */5 * * * ?");

            /**
             * 矿工动态收益
             * 每天1点执行
             */
            /*ScheduleJob profitone = new ScheduleJob();
            profitone.setBeanClass("hry.listener.cloudmining.CmManagement");
            profitone.setMethodName("profitone");
            QuartzManager.addJob("profitone", profitone, QuartzJob.class,"0 0 1 * * ?");*/
            //QuartzManager.addJob("profitone", profitone, QuartzJob.class,"0 30 0-23 * * ?");


            /**
             * 矿场动态收益
             * 每天3点执行
             */
            /*ScheduleJob grantProfitTwo = new ScheduleJob();
            grantProfitTwo.setBeanClass("hry.listener.cloudmining.CmManagement");
            grantProfitTwo.setMethodName("grantProfitTwo");
            QuartzManager.addJob("grantProfitTwo", grantProfitTwo, QuartzJob.class,"0 0 3 * * ?");*/
            //QuartzManager.addJob("grantProfitTwo", grantProfitTwo, QuartzJob.class,"0 */5 * * * ?");


        }

        //是否开启矿机业务
        if (moduleMap != null && "true".equalsIgnoreCase(moduleMap.get("app.module.cloudminingv2"))) {
            /**
             * 云矿机 矿机产币  按天
             * 每天0点10分执行
             */
            ScheduleJob miner2CoinageByDay = new ScheduleJob();
            miner2CoinageByDay.setBeanClass("hry.listener.cloudminingv2.Cm2Management");
            miner2CoinageByDay.setMethodName("miner2CoinageByDay");
            QuartzManager.addJob("miner2CoinageByDay", miner2CoinageByDay, QuartzJob.class,"0 15 0 * * ?");
            //QuartzManager.addJob("minerCoinageByDay", minerCoinageByDay, QuartzJob.class,"0 0 0-23 * * ?");
            //QuartzManager.addJob("minerCoinageByDay", minerCoinageByDay, QuartzJob.class,"0 */5 * * * ?");
            /**
             * 云矿机 矿机状态更新
             * 每天0-23点 整点执行
             */
            ScheduleJob miner2WaitAndCloseTiming = new ScheduleJob();
            miner2WaitAndCloseTiming.setBeanClass("hry.listener.cloudminingv2.Cm2Management");
            miner2WaitAndCloseTiming.setMethodName("miner2WaitAndCloseTiming");
            QuartzManager.addJob("miner2WaitAndCloseTiming", miner2WaitAndCloseTiming, QuartzJob.class,"0 5 0-23 * * ?");
            //QuartzManager.addJob("minerWaitAndCloseTiming", minerWaitAndCloseTiming, QuartzJob.class,"0 */5 * * * ?");

            /**
             * 矿工动态收益
             * 每天1点执行
             */
            ScheduleJob profit2one = new ScheduleJob();
            profit2one.setBeanClass("hry.listener.cloudminingv2.Cm2Management");
            profit2one.setMethodName("profit2one");
            QuartzManager.addJob("profit2one", profit2one, QuartzJob.class,"0 0 1 * * ?");
            //QuartzManager.addJob("profitone", profitone, QuartzJob.class,"0 30 0-23 * * ?");

        }
        //是否开启V4矿机业务
        if (moduleMap != null && "true".equalsIgnoreCase(moduleMap.get("app.module.cloudminingv4"))) {
            /**
             * 云矿机 矿机产币  按天
             * 每天0点10分执行
             */
            ScheduleJob miner2CoinageByDay = new ScheduleJob();
            miner2CoinageByDay.setBeanClass("hry.listener.cloudminingv4.Cm4Management");
            miner2CoinageByDay.setMethodName("miner2CoinageByDay");
            QuartzManager.addJob("miner2CoinageByDay", miner2CoinageByDay, QuartzJob.class,"0 15 0 * * ?");
            /**
             * 云矿机 矿机状态更新
             * 每天0-23点 整点执行
             */
            ScheduleJob miner2WaitAndCloseTiming = new ScheduleJob();
            miner2WaitAndCloseTiming.setBeanClass("hry.listener.cloudminingv4.Cm4Management");
            miner2WaitAndCloseTiming.setMethodName("miner2WaitAndCloseTiming");
            QuartzManager.addJob("miner2WaitAndCloseTiming", miner2WaitAndCloseTiming, QuartzJob.class,"0 5 0-23 * * ?");

            /**
             * 矿工动态收益
             * 每天1点执行
             */
            ScheduleJob profit2one = new ScheduleJob();
            profit2one.setBeanClass("hry.listener.cloudminingv4.Cm4Management");
            profit2one.setMethodName("profit2one");
            QuartzManager.addJob("profit2one", profit2one, QuartzJob.class,"0 0 1 * * ?");

        }

        //是否开启矿机业务---李金元
        if (moduleMap != null && "true".equalsIgnoreCase(moduleMap.get("app.module.cloudminingv5"))) {
            /**
             * 云矿机 矿机产币  按天
             * 每天0点10分执行
             */
            ScheduleJob miner5CoinageByDay = new ScheduleJob();
            miner5CoinageByDay.setBeanClass("hry.listener.cloudminingv5.Cm5Management");
            miner5CoinageByDay.setMethodName("miner5CoinageByDay");
            QuartzManager.addJob("miner5CoinageByDay", miner5CoinageByDay, QuartzJob.class,"0 10 0 * * ?");
        }

    }
}

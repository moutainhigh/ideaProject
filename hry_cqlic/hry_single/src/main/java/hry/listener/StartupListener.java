package hry.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.util.sys.AppUtils;

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

		/*
		* 更新释放周期
		* 每天23点30分执行一次
		* */
        ScheduleJob teamAwardRuleListener = new ScheduleJob();
        teamAwardRuleListener.setBeanClass("hry.listener.mall.HomeManagement");
        teamAwardRuleListener.setMethodName("teamAwardRuleListener");
        QuartzManager.addJob("teamAwardRuleListener", teamAwardRuleListener, QuartzJob.class,"0 30 23 * * ? ");

        /*
		* 静态收益发放
		* 每天凌晨0点执行一次
		* */
        ScheduleJob staticAwardListener = new ScheduleJob();
        staticAwardListener.setBeanClass("hry.listener.mall.HomeManagement");
        staticAwardListener.setMethodName("staticAwardListener");
        QuartzManager.addJob("staticAwardListener", staticAwardListener, QuartzJob.class,"0 0 0 * * ?");

        /*
         * 见点奖发放
         * 每天凌晨0点 30 执行一次
         * */
        ScheduleJob seeAwardListener = new ScheduleJob();
        seeAwardListener.setBeanClass("hry.listener.mall.HomeManagement");
        seeAwardListener.setMethodName("seeAwardListener");
        QuartzManager.addJob("seeAwardListener", seeAwardListener, QuartzJob.class,"0 30 0 * * ?");

        /*
         * 管理奖发放
         * 每天凌晨0点50执行一次
         * */
        ScheduleJob manageAwardListener = new ScheduleJob();
        manageAwardListener.setBeanClass("hry.listener.mall.HomeManagement");
        manageAwardListener.setMethodName("manageAwardListener");
        QuartzManager.addJob("manageAwardListener", manageAwardListener, QuartzJob.class,"0 50 0 * * ?");

        /*
         * 级别奖发放
         * 每天凌晨3点执行一次
         * */
        ScheduleJob gradeAwardListener = new ScheduleJob();
        gradeAwardListener.setBeanClass("hry.listener.mall.HomeManagement");
        gradeAwardListener.setMethodName("gradeAwardListener");
        QuartzManager.addJob("gradeAwardListener", gradeAwardListener, QuartzJob.class,"0 0 3 * * ?");
        /*
         * 周释放
         * 每天凌晨2点执行一次
         * */
        ScheduleJob weekTeamAwardListener = new ScheduleJob();
        weekTeamAwardListener.setBeanClass("hry.listener.mall.HomeManagement");
        weekTeamAwardListener.setMethodName("weekTeamAwardListener");
        QuartzManager.addJob("weekTeamAwardListener", weekTeamAwardListener, QuartzJob.class,"0 0 2 ? * MON");

        /*
        * 月释放
        * 每月1号凌晨2点30执行一次
        * */
        ScheduleJob monthTeamAwardListener = new ScheduleJob();
        monthTeamAwardListener.setBeanClass("hry.listener.mall.HomeManagement");
        monthTeamAwardListener.setMethodName("monthTeamAwardListener");
        QuartzManager.addJob("monthTeamAwardListener", monthTeamAwardListener, QuartzJob.class,"0 30 2 1 * ?");

        /*
         * 年释放
         * 每年1号凌晨2点30执行一次
         * */
        ScheduleJob yearTeamAwardListener = new ScheduleJob();
        yearTeamAwardListener.setBeanClass("hry.listener.mall.HomeManagement");
        yearTeamAwardListener.setMethodName("yearTeamAwardListener");
        QuartzManager.addJob("yearTeamAwardListener", yearTeamAwardListener, QuartzJob.class,"0 30 2 1 1 ? *");

        /*
         * 统计平台资产
         * 每天凌晨3点执行一次
         * */
        ScheduleJob platformTotalListener = new ScheduleJob();
        platformTotalListener.setBeanClass("hry.listener.mall.HomeManagement");
        platformTotalListener.setMethodName("platformTotalListener");
        QuartzManager.addJob("platformTotalListener", platformTotalListener, QuartzJob.class,"0 10 3 * * ?");

        /*
         * 定时统计昨天平台充值USDT数量及提币数量
         * 每天凌晨0点5分执行
         * */
        ScheduleJob payAndExtractListener = new ScheduleJob();
        payAndExtractListener.setBeanClass("hry.listener.mall.HomeManagement");
        payAndExtractListener.setMethodName("payAndExtractListener");
        QuartzManager.addJob("payAndExtractListener", payAndExtractListener, QuartzJob.class,"0 5 0 * * ?");
        /*
         * 定时统计昨天平台兑换提币数量
         * 每天凌晨0点8分执行
         * */
        ScheduleJob payAndExtractExchangeListener = new ScheduleJob();
        payAndExtractExchangeListener.setBeanClass("hry.listener.mall.HomeManagement");
        payAndExtractExchangeListener.setMethodName("payAndExtractExchangeListener");
        QuartzManager.addJob("payAndExtractExchangeListener", payAndExtractExchangeListener, QuartzJob.class,"0 8 0 * * ?");

        /*
         * 抓取提币审核记录级流水--改为提币成功
         * 每一分钟执行一次
         * */
        ScheduleJob saveTiBiRecord = new ScheduleJob();
        saveTiBiRecord.setBeanClass("hry.listener.mall.HomeManagement");
        saveTiBiRecord.setMethodName("saveTiBiRecord");
        QuartzManager.addJob("saveTiBiRecord", saveTiBiRecord, QuartzJob.class,"0 */1 * * * ?");

        /*
         * 抓取手动充币
         * 每40s执行一次
         * */
        ScheduleJob saveChargeRecord = new ScheduleJob();
        saveChargeRecord.setBeanClass("hry.listener.mall.HomeManagement");
        saveChargeRecord.setMethodName("saveChargeRecord");
        QuartzManager.addJob("saveChargeRecord", saveChargeRecord, QuartzJob.class,"*/40 * * * * ?");

        /**
         * 更新推荐人数
         * 每30分钟执行一次
         * */
        ScheduleJob addNumber = new ScheduleJob();
        addNumber.setBeanClass("hry.listener.mall.HomeManagement");
        addNumber.setMethodName("addNumber");
        QuartzManager.addJob("addNumber", addNumber, QuartzJob.class,"0 */30 * * * ?");

        /*
         * 校验入驻订单是否过期
         * 每天凌晨0点执行一次
         * */
        ScheduleJob ecologEnterListener = new ScheduleJob();
        ecologEnterListener.setBeanClass("hry.listener.mall.HomeManagement");
        ecologEnterListener.setMethodName("ecologEnterListener");
        QuartzManager.addJob("ecologEnterListener", ecologEnterListener, QuartzJob.class,"0 0 0 * * ?");
    }
}

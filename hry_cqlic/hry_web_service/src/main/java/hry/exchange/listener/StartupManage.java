package hry.exchange.listener;

import hry.core.listener.StartupService;
import hry.core.quartz.QuartzJob;
import hry.core.quartz.QuartzManager;
import hry.core.quartz.ScheduleJob;
import hry.util.mybatis.AnnotationUtil;
import hry.util.mybatis.ClassUtil;
import hry.util.properties.PropertiesUtils;
import org.apache.log4j.Logger;

import java.util.List;

public class StartupManage implements StartupService {
    private final static Logger logger = Logger.getLogger(StartupManage.class);

    public final static String AppName = "hurong_exchange";
    public final static String AppKey = "exchange";

    @Override
    public void start() {
        String saasId = PropertiesUtils.APP.getProperty("app.saasId");
        try {
            logger.info("exchange启动定时器中................................................................");
            //StartupEntrust.Entrustinit();  新版在admin里初始化

            // 获取特定包下所有的类(包括接口和类)
            List<Class<?>> clsList = ClassUtil.getAllClassByPackageName("hry");

            AnnotationUtil.validAnnotation(clsList);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        String isStartAllJob = PropertiesUtils.APP.getProperty("app.isStartAllJob"); //是否开启定时器，为部署多台manage
//        if (isStartAllJob != null && !"".equals(isStartAllJob) && isStartAllJob.equals("true")) {
//        //每天凌晨12点触发 清楚限制额度=================================
//        /*try {
//            ScheduleJob addNumberJob1 = new ScheduleJob();
//            addNumberJob1.setSpringId("klgAmountLimitationService");
//            addNumberJob1.setMethodName("resetToZero");      //0 0/1 * * * ?  0 0 0 * * ?
//            QuartzManager.addJob("klgAmountLimitationServiceResetToZero", addNumberJob1, QuartzJob.class, "0 0 0 * * ?");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }*/
//        /**
//         * 定时清除 超过15天未预约的用户额度
//         */
//        /*try {
//            ScheduleJob resetRewardJob = new ScheduleJob();
//            resetRewardJob.setSpringId("klgCustomerLevelService");
//            resetRewardJob.setMethodName("resetRewardQuota");      //0 0/1 * * * ?  0 0 0 * * ?
//            QuartzManager.addJob("klgCustomerLevelServiceResetReward", resetRewardJob, QuartzJob.class, "0 0 0 * * ?");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }*/
//        }

        // 更新用户推荐人数 注掉原因： 因部署多个service 更新移至single项目
        /*try {
            ScheduleJob addNumberJob = new ScheduleJob();
            addNumberJob.setSpringId("appCommendUserService");
            addNumberJob.setMethodName("addNumber");
            //测试时先设置cron表达式为2分钟
            QuartzManager.addJob("appCommendUserServiceAddNumber", addNumberJob, QuartzJob.class, "0 0/30 * * * ?");
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        /**
         * 定时抓取提币审核通过的流水记录流水
         */
        /*try {
            ScheduleJob tiBiRecordJob = new ScheduleJob();
            tiBiRecordJob.setSpringId("klgAssetsRecordService");
            tiBiRecordJob.setMethodName("saveTiBiRecord");
            QuartzManager.addJob("klgAssetsRecordServiceSaveTiBiRecord", tiBiRecordJob, QuartzJob.class, "0 0/30 * * * ?");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}

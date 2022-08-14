package hry.financail.financing.timer;

import hry.financail.financing.service.AppFinancialRecommendRefactorService;
import hry.financail.financing.service.AppFinancialRecommendService;
import hry.util.SpringUtil;
import hry.util.properties.PropertiesUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/5/10 11:38
 * @Description:
 */
@Component
@EnableScheduling
public class FinancialTimer {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FinancialTimer.class);

    //0 33 23,12 * * ?
    @Scheduled(cron = "0 0/5 * * * ?")
    public static void InitFinancialRecommend(){
        String ifAirdrop= PropertiesUtils.APP.getProperty("app.financialRecommend");
        if(!StringUtils.isEmpty(ifAirdrop) && "true".equals(ifAirdrop)) {
            AppFinancialRecommendService appFinancialRecommendService = SpringUtil.getBean("appFinancialRecommendService");
            appFinancialRecommendService.statisVipLevel();
        }
    }

    //0 44 0 * * ?
//    @Scheduled(cron = "0 44 0 * * ?")
//    public static void InitFinancialRecommendIssue(){
//        String ifAirdrop= PropertiesUtils.APP.getProperty("app.financialRecommend");
//        if(!StringUtils.isEmpty(ifAirdrop) && "true".equals(ifAirdrop)) {
//            AppFinancialRecommendService appFinancialRecommendService = SpringUtil.getBean("appFinancialRecommendService");
//            appFinancialRecommendService.issueFinancial();
//        }
//    }

    @Scheduled(cron = "0 0/9 * * * ?")
    public static void InitFinancialRecommendIssueRefactor(){
        String ifAirdrop= PropertiesUtils.APP.getProperty("app.financialRecommendRefactor");
        if(!StringUtils.isEmpty(ifAirdrop) && "true".equals(ifAirdrop)) {
            AppFinancialRecommendRefactorService appFinancialRecommendRefactorService = SpringUtil.getBean("appFinancialRecommendRefactorService");
            appFinancialRecommendRefactorService.issueFinancialRefactoring();
        }
    }

//    @Scheduled(cron = "0 0/10 * * * ?")
//    public static void renewFinancialRecommend(){
//        AppFinancialRecommendService appFinancialRecommendService = SpringUtil.getBean("appFinancialRecommendService");
//        appFinancialRecommendService.renewFinancialRecommend();
//    }

//    @Scheduled(cron = "0 0/2 * * * ?")
//    public static void InitAppCommendToFinancial(){
//        String ifAirdrop= PropertiesUtils.APP.getProperty("app.generateFinancialRecommend");
//        if(!StringUtils.isEmpty(ifAirdrop) && "true".equals(ifAirdrop)) {
//            System.out.println("=============开启生成推荐人关系定时器");
//            AppFinancialRecommendService appFinancialRecommendService = SpringUtil.getBean("appFinancialRecommendService");
//            appFinancialRecommendService.generateFinancialRecommendationRelationship();
//        }
//    }
}

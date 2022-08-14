package hry.listener.mall;

import hry.app.remote.RemoteUserService;
import hry.licqb.remote.RemoteAwardService;
/*import hry.licqb.remote.RemoteEcologyService;*/
import hry.licqb.remote.RemoteEcologyService;
import hry.licqb.remote.RemoteExchangeService;
import hry.util.SpringUtil;
import hry.util.sys.ContextUtil;

/**
 * @auther zhouming
 * @date 2019/7/4 14:17
 * @Version 1.0
 */
public class HomeManagement {

    /**
     * 更新释放周期
     * */
    public void teamAwardRuleListener() {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.giveOutTeamAwardRule();
    }

    /**
     * 周释放
     * */
    public void weekTeamAwardListener() {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.giveOutWeekTeamAward();
    }
    /**
     * 月释放
     * */
    public void monthTeamAwardListener() {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.giveOutMonthTeamAward();
    }
    /**
     * 年释放
     * */
    public void yearTeamAwardListener() {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.giveOutYearTeamAward();
    }

    /**
     * 静态收益发放监听
     * */
    public void staticAwardListener () {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.giveOutStaticAward();
    }

    /**
     * 见点奖发放监听
     * */
    public void seeAwardListener () {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.giveOutSeeAward();
    }

    /**
     * 管理奖发放监听
     * */
    public void manageAwardListener () {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.giveOutManageAward();
    }

    /**
     * 级别奖发放监听
     * */
    public void gradeAwardListener () {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.giveOutGradeAward();
    }

    /**
     * 平台资产统计监听
     * */
    public void platformTotalListener () {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.statsPlatformTotal();
    }

    /**
     * 抓取提币审核记录级流水--改为提币成功
     */
    public void saveTiBiRecord () {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.saveTiBiRecord();
    }

    /**
     * 抓取手动充币记录
     * */
    public void saveChargeRecord () {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.saveChargeRecord();
    }

    /**
     * 更新推荐人数
     * */
    public void addNumber() {
        RemoteUserService remoteUserService = SpringUtil.getBean("remoteUserService");
        remoteUserService.addNumber();
    }

    /**
     * 校验入驻订单是否过期
     * */
    public void ecologEnterListener () {
        RemoteEcologyService remoteEcologyService = SpringUtil.getBean("remoteEcologyService");
        remoteEcologyService.updateEcologEnter();
    }
    /*
     * 定时统计昨天平台充值USDT数量及提币数量
     * */
    public void payAndExtractListener () {
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.payAndExtractTotal();
    }

    /*
     * 定时统计昨天平台兑换的冲提币数
     * */
    public void payAndExtractExchangeListener () {
        RemoteExchangeService remoteExchangeService = SpringUtil.getBean("remoteExchangeService");
        remoteExchangeService.payAndExtractExchangeTotal();
    }

}

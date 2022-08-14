package hry.licqb.remote;

import java.util.Map;

/**
 * @author zhouming
 * @date 2019/8/16 15:27
 * @Version 1.0
 */
public interface RemoteLevelService {

    /**
     * 用户个人等级升级
     * */
    public void upgradeUserGrade(Long customerId) throws Exception;

    /**
     * 用户社区等级升级
     * */
    public void upgradeUserTeamGrade(Long customerId);

    /**
     * 更新用户释放规则
     * */
    public void updateReleaseRule(Long customerId);

    /**
     * 手动出局
     * */
    public void customerOut(Long customerId);

    public void changeAccount(Map<String, Object> map);

    /**
     * 账号负数平账
     * */
    public void findPingzhang();

    /**
     * USDT平账
     * */
    public void usdtPingzhang();

    /**
     * 等级平账
     * */
    public void levelPingZhang();

    /**
     * 个人资产与冻结不一致平账
     * */
    public void coldBusinessPingZhang();

    /**
     * 兑换平账
     * */
    public void exchangeColdPingZhang();

    /***
     * 兑换平账2
     * */
    public void exchangeColdPingZhangTwo();
}

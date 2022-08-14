package hry.licqb.remote;

import com.alibaba.fastjson.JSONObject;
import hry.bean.FrontPage;
import hry.bean.JsonResult;

import java.util.Map;

/**
 * @author zhouming
 * @date 2019/8/14 15:30
 * @Version 1.0
 */
public interface RemoteAwardService {
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~定时器调用接口（奖励发放）~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /**
     * 发放用户静态奖励
     * */
    public void giveOutStaticAward();

    /**
     * 发放用户见点奖励
     * */
    public void giveOutSeeAward();

    /**
     * 发放用户管理奖励
     * */
    public void giveOutManageAward();

    /**
     * 指定用户发送管理奖
     * */
    public void giveOutManageAward(String startTime);

    /**
     * 发放级别奖
     * */
    public void giveOutGradeAward();

    /**
     * 社区奖励周释放
     * */
    public void giveOutWeekTeamAward();

    /**
     * 指定用户发周释放
     * */
    public void giveOutWeekTeamAwardTwo(String customerIds);

    /**
     * 社区奖励月释放
     * */
    public void giveOutMonthTeamAward();

    /**
     * 社区奖励年释放
     * */
    public void giveOutYearTeamAward();

    /**
     * 更新社区奖励是否释放---亮灯规则
     * */
    public void giveOutTeamAwardRule();

    /**
     * 抓取提币审核记录级流水
     * */
    public void saveTiBiRecord();

    /**
     * 抓取手动充币流水
     * */
    public void saveChargeRecord();

    /*
    * 统计平台BBGO资产
    * */
    public void statsPlatformTotal();

    /**
     * 收益统计（app统计）
     * */
    public JSONObject awardStatistics(Long customerId);

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~app查询接口（奖励查询）~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /**
     * 收益统计---静态收益，动态收益，和共建社区奖励
     * */
    public JsonResult findAwardStatistics(Long customerId);

    /**
     * 收益统计明细
     * */
    public FrontPage findAwardDetails(Map<String, String> map);

    /**
     * 管理奖指定发放（通过mq）
     * */
    public void mqOutManageAward(String jsonMap);

    /**
     * outinfo额度奖励修改
     * */
    public void mqUpdateOutInfo(String jsonMap);

    /**
     * 见点奖发放（通过mq）
     * */
    public void mqOutSeeAward(String jsonMap);

    /**
     * 静态奖励发放（通过mq）
     * */
    public void mqOutStaticAward(String jsonMap);

    /**
     * 更新释放周期（通过mq）
     * */
    public void mqTeamAwardRule(String jsonMap);

    /**
     * 定时统计昨天平台充值USDT数量及提币数量
     * */
    public void payAndExtractTotal();

    /***
     * 剩余USDT兑换
     * */
    public void surplusUSDTExchange();

    /***
     * 剩余社区奖励兑换
     * */
    public void surplusBBGOExchange(Map<String, String> map);

    /***
     * 剩余USDT兑换--测试
     * */
    public void surplusUSDTExchange(String customerId);

    /***
     * 剩余社区奖励兑换--测试
     * */
    public void surplusBBGOExchange(String customerId, String exchangeCode);

    /**
     * 剩余USDT奖励发放（通过mq）
     * */
    public void mqSurplusUSDTExchange(String jsonMap);

    /**
     * 剩余BBGO奖励发放（通过mq）
     * */
    public void mqSurplusBBGOExchange(String jsonMap);


    /***
     * 剩余USDT兑换--撤回
     * */
    public void surplusUSDTExchangeTwo();

    /***
     * 剩余社区奖励兑换--撤回
     * */
    public void surplusBBGOExchangeTwo(Map<String, String> map);

    /**
     * 剩余USDT奖励发放（通过mq）--撤回
     * */
    public void mqSurplusUSDTExchangeTwo(String jsonMap);

    /**
     * 剩余BBGO奖励发放（通过mq）-- 撤回
     * */
    public void mqSurplusBBGOExchangeTwo(String jsonMap);


    /***
     * 剩余USDT兑换--测试
     * */
    public void surplusUSDTExchangeTwo(String customerId);

    /***
     * 剩余社区奖励兑换--测试
     * */
    public void surplusBBGOExchangeTwo(String customerId, String exchangeCode);

}

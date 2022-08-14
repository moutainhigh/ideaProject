package hry.ico.remote;

import hry.bean.JsonResult;

import java.util.HashMap;

public interface RemoteIcoRewardService {
    /**
     *持仓奖励经验
     *
     * @param msgText
     * @return
     */
    public boolean experienceAward(String msgText);


    /**
     * 购买平台币交易推荐与首持奖励
     */
    public JsonResult recommendReward(String msgText);

    /**
     * 只用一次发送经验
     * @return
     */
    public  JsonResult sendExp();

    //发放分红
    //
    public  JsonResult  grantDividend(HashMap<String,String> hashMap);


    /**
     * 数据修复
     * @return
     */
    public  JsonResult repairData();
}

package hry.klg.remote;

import java.util.Map;

import hry.bean.JsonResult;
import hry.manage.remote.model.base.FrontPage;

/**
 * 奖励
 */
public interface RemoteKlgRewardService {

    /**
     * 卖出奖励发放
     * @param message
     * @return
     */
    public JsonResult sellRewardMQ(String message);
    
    /**
     * 查询奖励记录
     * @param message
     * @return
     */
    public FrontPage findRewardList(Map<String, String> params);
    
    /**
     * 我的奖励页面信息
     * @param params
     * @return
     */
    public JsonResult myReward(Map<String, String> params);
    
}

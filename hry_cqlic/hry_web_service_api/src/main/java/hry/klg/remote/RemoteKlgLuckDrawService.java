package hry.klg.remote;

import java.util.Map;

import hry.bean.JsonResult;
import hry.manage.remote.model.base.FrontPage;

/**
 * 大奖
 */
public interface RemoteKlgLuckDrawService {
	
    /**
     * 查询奖励记录
     * @param message
     * @return
     */
    public FrontPage findSelectionList(Map<String, String> params);
    
    /**
     * 查询上一期开奖信息
     * @param params
     * @return
     */
    public JsonResult getLastIssue(Map<String, String> params);
    
    /**
     * 查询我的抽奖号码
     * @param message
     * @return
     */
    public FrontPage findMyLuckDrawList(Map<String, String> params);
    
    /**
     * 查询我的中奖纪录
     * @param message
     * @return
     */
    public FrontPage findMyprizeList(Map<String, String> params);
    
    /**
     * 查询我的本期抽奖号码
     * @param message
     * @return
     */
    public FrontPage findMyThisLuckDrawList(Map<String, String> params);
    
    /**
     * 查询本周剩余抽奖次数
     * @param params
     * @return
     */
    public JsonResult getMySurplusLuckdrawCount(Map<String, String> params);

    /**
     * 添加抽奖号码
     * @param params
     * @return
     */
    public JsonResult addLuckDrawNum(Map<String, String> params);
    
    /**
     * 第一个下单的用户，添加开奖信息
     * @param params
     * @return
     */
    public void appFirstBuyCustomer(Long customerId);
    
    
    
    
}

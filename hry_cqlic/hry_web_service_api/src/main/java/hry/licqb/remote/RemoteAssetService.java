package hry.licqb.remote;

import hry.bean.FrontPage;
import hry.bean.JsonResult;
import java.util.Map;

/**
 * @author zhouming
 * @date 2019/8/30 14:19
 * @Version 1.0
 */
public interface RemoteAssetService {

    /**
     * 获取平台配置信息
     * */
    public JsonResult getPlatformConfig();

    /**
     * 根据用户Id、币种 获取信息
     * */
    public JsonResult getAccountByCoinCode(Long customerId, String coinCode);

    /**
     * 我的资产
     * */
    public JsonResult myAccount(Map<String, String> paramMap);

    /**
     * 用户理财
     * */
    public JsonResult againInvest(Map<String, String> paramMap);

    /**
     * 兑换页面所需数据
     * */
    public JsonResult findexchangeData(Long customerId);


    /**
     * 用户兑换
     * */
    public JsonResult exchangeCode(Map<String, String> paramMap);

    /**
     * 财务记录
     * */
    public FrontPage findDealDetails(Map<String, String> paramMap);

    /**
     * 用户剩余实际投资数量
     * */
    public JsonResult findUserAssetNum(Long customerId);
}

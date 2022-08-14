package hry.licqb.remote;

import hry.bean.FrontPage;
import hry.bean.JsonResult;

import java.util.Map;

/**
 * @author zhouming
 * @date 2020/12/22 14:28
 * @Version 1.0
 */
public interface RemoteExchangeService {
    /**
     * 项目列表
     * */
    FrontPage findExchangeConfigList(Map<String, String> map);

    /**
     * 通过项目id获取项目配置详情
     * */
    JsonResult getExchangeConfig(String configId, String locale);

    /**
     * 用户兑换
     * */
    JsonResult toExchange(Map<String, String> map);

    /**
     * 用户兑换记录
     * */
    FrontPage findUserToExchangeRecord(Map<String, String> map);

    /**
     * 平台统计新兑换数量
     * */
    void payAndExtractExchangeTotal();
}

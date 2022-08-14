package hry.mall.integral.remote;

import hry.bean.JsonResult;

import java.util.Map;

public interface RemoteIntegralAccountService {

    /**
     * 数据中心
     * @param
     * @return
     */
    public JsonResult dataCenter();

    public JsonResult initializationCurrencyAccount (Map<String, Object> params);

//    /**
//     * 为导入的账户添加购买记录
//     * @return
//     */
//    JsonResult processingImportedData();
//
    JsonResult updateTeamLevel();


}

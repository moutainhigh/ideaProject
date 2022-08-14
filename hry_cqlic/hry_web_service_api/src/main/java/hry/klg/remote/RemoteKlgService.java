/**
 * 111
 */

package hry.klg.remote;

import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.klg.model.RemoteLevelConfig;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 快乐购
 */
public interface RemoteKlgService {

    /**
     * 获取平台规则
     * @return
     */
    public Object getPlatformRule1sConfig(String key);


    /**
     * 验证是否开盘时间
     * @return
     */
    public JsonResult isCheckOpenTime();

    /**
     * 验证是否金额正确
     * @param customerId
     * @param number
     * @return
     */
    public  Boolean isCheckLimitMoney(Long customerId, BigDecimal number);


    /**
     * 平台账户转账
     * @param hMap
     * @return
     */
    public  JsonResult platformTransfer(Map<String,String> hMap);


    /**
     * 转账接口
     * @param hashMap
     * @return
     */
    public JsonResult transferAccounts(HashMap<String, String> hashMap);


    /**
     * 获取指定平台类型账户金额
     * @param accountType
     * @return
     */
    public Object getPlatformAccount(String accountType);


    /**
     * 平台币资金队列
     * @param message
     * @return
     */
    public JsonResult PlatformAccountAddQueue(String message);


    /**
     * 预约购买
     * @param hMap
     * @return
     */
    public JsonResult appointmentPurchase(Map<String,String > hMap);


    /**
     * 预约卖出
     * @param hMap
     * @return
     */
    public  JsonResult appointmentSell(Map<String,String > hMap);

    /**
     * 根据等级获取等级配置
     * @param leveId
     * @return
     */
    public RemoteLevelConfig getLevelConfigByLevelId(Long leveId);


    /**
     * 获取预约购买信息
     * @param hMap
     * @return
     */
    public JsonResult getMyPurchaseInfo(Map<String,String > hMap);

    /**
     * 获取上次预约购买数量
     * @return
     */
    public String getOldPurchaseNum(Long customerId);

    /**
     * 获取预约卖出信息
     * @param hMap
     * @return
     */
    public JsonResult getMySellInfo(Map<String,String > hMap);


    /**
     * 获取可升等级列表
     * @param hMap
     * @return
     */
    public JsonResult getUpgradeInfoList(Map<String,String > hMap);


    /**
     * 会员升级操作
     * @param hMap
     * @return
     */
    public JsonResult upgradeUserLevel(Map<String,String > hMap);

    /**
     * 我的交易流水
     * @param hashMap
     * @return
     */
    public FrontPage getMyTransactionflow(HashMap<String, String> hashMap);


    /**
     * 获取买单记录列表
     * @param hashMap
     * @return
     */
    public FrontPage getBuyInfoList(HashMap<String, String> hashMap);


    /**
     * 获取卖单记录列表
     * @param hashMap
     * @return
     */
    public FrontPage getSellInfoList(HashMap<String, String> hashMap);
    
    /**
     * 用户买单记录
     * @param hashMap
     * @return
     */
    public FrontPage fingBuyListByCustomerId(Map<String, String> hashMap);
    /**
     * 用户卖单记录
     * @param hashMap
     * @return
     */
    public FrontPage fingSellListByCustomerId(Map<String, String> hashMap);

    /**
     * 获取用户当前等级信息
     * @return
     */
    public JsonResult getCustomerLevelInfo(Map<String, String> hashMap);

    /**
     * 我的资产
     * @param hMap
     * @return
     */
    public JsonResult myAccount(Map<String,String > hMap);
    
    /**
     * 获取平台币价格
     * @param hMap
     * @return
     */
    public JsonResult getPlatformCoinPrice();
    /**
     * 获取平台币code
     * @param hMap
     * @return
     */
    public JsonResult getPlatformCode();
    
    /**
     * 根据coinCode查询货币信息
     * @param hMap
     * @return
     */
    public JsonResult getAccountByCoinCode(Map<String,String > hMap);
    
    /**
     * 获取排队时间间隔
     * @param hMap
     * @return
     */
    public JsonResult getLineUpTime();
    
    /**
     * 获取排队时间间隔
     * @param hMap
     * @return
     */
    public JsonResult getKlgConfig();
}

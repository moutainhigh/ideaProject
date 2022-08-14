package hry.ico.remote;

import hry.bean.FrontPage;
import hry.bean.JsonResult;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public interface RemoteIcoService {

    /**
     * 获取平台规则
     * @return
     */
    public JsonResult getPlatformRule(String rulekeyStr);

    /**
     * 获取平台币余额等信息
     * @return
     */
    public JsonResult getPlatformCurrencyInfo();

    /**
     * 当前是否ico阶段
     * @return
     */
    public JsonResult isCheckICOTime();

    /**
     * 获取等级配置
     * @return
     */
    public JsonResult getUpgradeConfigList();
    /**
     * 计算购买平台币
     * @param hashMap
     * @return
     */
    public  JsonResult calculatePaymentAmount(HashMap<String, String> hashMap);

    /**
     *购买平台币
     */
    public JsonResult purchasePlatformAccount(HashMap<String, String> hashMap);

    /**
     * 购买平台记录
     * @param hashMap
     * @return
     */
    public FrontPage getIcoBuyOrderRecord(HashMap<String, String> hashMap);

    /**
     * 我的推荐列表
     * @param hashMap
     * @return
     */
    public FrontPage getMyRecommendationList(HashMap<String, String> hashMap);

    /**
     * 我的交易流水
     * @param hashMap
     * @return
     */
    public FrontPage getMyTransactionflow(HashMap<String, String> hashMap);

    /**
     * 我的账单明细
     * @param hashMap
     * @return
     */
    public  JsonResult getTransactionDetail(HashMap<String, String> hashMap);
    /**
     * 我的推广明细（团队资产等）
     * @param hashMap
     * @return
     */
    public JsonResult getMyRecommendationDetails(HashMap<String, String> hashMap);

    /**
     * 流通转存储（锁仓）
     * @param customerId  用户Id
     * @param lockDay   锁仓天数
     * @param number    锁仓数量
     * @param coinCode  平台币种
     * @param isIco  是否ICO阶段
     * @return
     */
    public JsonResult toLockStorage(Long customerId , Integer lockDay, BigDecimal number,String coinCode,boolean isIco);

    //锁仓操作
    public JsonResult toLockDepot(HashMap<String, String> hashMap);

    //追加锁仓天数
    public JsonResult appendLockDepot(HashMap<String, String> hashMap);
    /**
     * 手动释放操作
     * @param hashMap
     * @return
     */
    public JsonResult releaseOperation(HashMap<String, String> hashMap);

    /**
     * 定时器释放任务
     * @param msgText
     * @return
     */
    public JsonResult releaseMQ(String msgText);

    /**
     * 获取释放扣除信息
     * @param customerId
     * @return
     */
    public JsonResult getReleaseDeductionInfo(HashMap<String, String> hashMap);

    /**
     * 获取锁仓明细
     * @param hashMap
     * @return
     */
    public FrontPage getLockDetailed(HashMap<String, String> hashMap);

    /**
     * 锁仓扣除信息(优先扣经验，经验不足扣币)
     * @param hashMap
     * @return
     */
    public  JsonResult getLockDeductionInfo(Long customerId);


    /**
     * 获取用户Ico账户资产
     * @param hashMap
     * @return
     */
    public JsonResult getIcoAccountInfo(HashMap<String,String> hashMap);


    /**
     * 转账接口
     * @param hashMap
     * @return
     */
    public JsonResult transferAccounts(HashMap<String,String> hashMap);

    /**
     * 添加用户等级账户

     * @return
     */
    public  JsonResult addCustomerLevelAccount(Long customerId);

    /**
     * 查看用户推荐等级
     * @return
     */
    public  JsonResult seeCustomerLevelAccount(Long customerId);

    /**
     * 获取经验明细
     * @param hashMap
     * @return
     */
    public FrontPage queryExperiencesDetail(HashMap<String, String> hashMap);

    /**
     * 获取分红记录
     * @param hashMap
     * @return
     */
    public FrontPage queryDividendRecord(HashMap<String, String> hashMap);

    /**
     * 获取用户预测等级和当前等级
     * @param hashMap
     * @return
     */
    public JsonResult predictUserLevel(Map<String, Object> hashMap);



}

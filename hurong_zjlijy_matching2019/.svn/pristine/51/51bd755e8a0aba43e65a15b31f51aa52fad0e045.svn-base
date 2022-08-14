package hry.mall.integral.remote;

import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.mall.integral.remote.model.IntegralDetailVo;

import java.math.BigDecimal;
import java.util.Map;

public interface RemoteIntegralDetailService {

	public  JsonResult  addIntegralDetai(IntegralDetailVo integralDetailVo);

    /**
     * 积分币HRC详情页
     * @param memberId
     * @return
     */
    public JsonResult integralDetails(long memberId);

    /**
     * HRC交易记录
     * @param map
     * @return
     */
    public FrontPage tradingRecord(Map<String, String> map);
 /**
     * HRC冻结记录
     * @param map
     * @return
     */
    public FrontPage frozenRecord(Map<String, String> map);

    /**
     * 根据任务发放积分
     *
     * @param orderId   没有订单业务传 (long)0
     * @param memberId
     * @param accountKey 账户key
     * @param taskKey    任务Key
     * @param rewardType 奖励类型 0 购物挖矿1分销挖矿 2 任务挖矿3算力挖矿
     * @return
     */
    public  JsonResult  addIntegralDetail(long orderId, long memberId, String accountKey, String taskKey, Integer rewardType);

    /**
     * 电子券转账
     * @param customerId      当前登录用户Id
     * @param amount           转账数量
     * @param mobilePhone     接收人手机号
     * @param accountPwd      支付密码
     * @return
     */
    public  JsonResult  toAccounts(String customerId,String amount,String mobilePhone,String accountPwd);
    /**
     * 电子券转账手续费计算
     * @param memberId  用户ID
     * @param amount    转账数量
     * @return
     */
    public BigDecimal getFee(Long memberId, String amount);


    /**
     *  执行推荐注册奖励
     * @param memberId
     * @return
     */
    JsonResult performingTaskRecommendKey(Long memberId,String taskKey);


}

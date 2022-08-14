/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-08 17:32:03 
 */
package hry.mall.integral.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.integral.model.IntegralDetail;
import hry.util.QueryFilter;

import java.math.BigDecimal;
import java.util.Map;


/**
 * <p> IntegralDetailService </p>
 * @author:         kongdb
 * @Date :          2019-01-08 17:32:03 
 */
public interface IntegralDetailService  extends BaseService<IntegralDetail, Long> {

    public PageResult findPageBySql(QueryFilter filter);

    public Map<String,Object> findbyKey(Map<String, Object> map);

    //获取昨天的购物、分销、任务产出
    public Map<String,Object> findByRewardType(Map<String, Object> map);

    public JsonResult addIntegralDetai(IntegralDetail integralDetail);


    /**
     * 根据任务发放积分
     *
     * @param orderId   没有订单业务传 (long)0
     * @param memberId
     * @param accountKey 账户key
     * @param taskKey    任务Key
     * @param rewardType 奖励类型 0 购物挖矿1分销挖矿 2 任务挖矿3算力挖矿 5购物分销返积分
     * @return
     */
	public JsonResult addIntegralDetail1(Long orderId, Long memberId, String accountKey, String taskKey, Integer rewardType);

    /**
     * 任务处理发积分 （不牵扯积分以外的业务）
     * @param paramMap
     *        integralCount 积分数
     *        computingCount 算力数
     *        taskName 任务名称
     *        taskId 任务ID
     *        type 任务类型
     *        memberId 用户id
     *        taskKey 任务Key
     * */
    public JsonResult issueIntegral(Map<String,String> paramMap);
    
    /**
     * 社交领取奖励后消息处理
     * @param message
     */
    public void  handleIntegral(String message);


    // 查询用户单月已使用电子券
    public Boolean getThisMonthRoll(String memberId, BigDecimal price);

}

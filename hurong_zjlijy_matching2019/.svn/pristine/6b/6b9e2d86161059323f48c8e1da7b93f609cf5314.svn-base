/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-01-16 15:41:31 
 */
package hry.mall.integral.service;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.integral.model.CustomerIntegral;
import hry.mall.integral.model.IntegralDetail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p> CustomerIntegralService </p>
 * @author:         zhouming
 * @Date :          2019-01-16 15:41:31 
 */
public interface CustomerIntegralService extends BaseService<CustomerIntegral, Long> {

    List<Map<String,Object>>  findByTotal();

    /**
     * 操作积分账户并生成流水
     * @param map
     * Map内参数为：
     * id：用户积分账户Id        必填
     * type ：操作类型           必填    常量在CustomerIntegral中
     * detailId ：积分明细ID     选填  当type为冻结转成功、冻结转失败时 必填
     * integralCount ：积分数量  选填  当type为加、减、冻结时 必填
     * requestNo ：流水号        非必填
     * tradingDetail：交易详情   非必填
     * businessType：业务类型    非必填 1：兑换 2：话费充值 3：转出 4：转入 5：手续费  6:返利(shop_integral_commission)7、会员升级(shop_level_record) 8.账户提现（包括后台充值扣减）（shop_withdraw_record）   99:初始化可用积分
     * @return
     */
    public boolean updateInteger(Map<String,String> map);
    
    /**
     * 积分推送到社交或者直接操作币账户添加
     * @param detail
     */
    public void handIntegralCoin(IntegralDetail integralDetail);
    /**
     * 根据积分计算对应平台币数量
     * @param count
     * @return
     */
    public BigDecimal platCoinCount(BigDecimal count);
    /**
     * 验证币账户余额
     * @param map
     * @return
     */
    public JsonResult validateExaccount(Map<String, String> map);
    /**
     * 扣除平台币
     * @param integralDetail
     */
    public void handSubIntegralCoin(IntegralDetail integralDetail);

    /**
     * 冻结平台币
     * @param integralDetail
     */
    public void freezeIntegralCoin(IntegralDetail integralDetail);


    /**
     * 数据导入初始化币账户(只支持初始化可用余额)
     * @param integralDetail
     */
    public void initializationCurrencyAccount(IntegralDetail integralDetail);


    /**
     * 查询出没有默认会员的用户
     * @return
     */
    List<CustomerIntegral> findNoDefaultLevel();
    
    /**
     * 保存交易所流水并发消息给trade操作币账户
     * @param map
     */
    public void handCoin(Map<String,String> map);


}

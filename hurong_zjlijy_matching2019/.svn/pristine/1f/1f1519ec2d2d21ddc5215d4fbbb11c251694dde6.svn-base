package hry.mall.integral.remote;

import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.mall.integral.remote.model.ThirdpayRecord;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @auther zhouming
 * @date 2019/3/22 16:17
 * @Version 1.0
 */
public interface RemoteRollService {

    /**
     * 电子券充值话费
     * **/
    public JsonResult rollCharge(String memberId, String mobile, String rechargeId, String password);

    /**
     * 根据第三方流水记录处理相关业务
     * */
    public JsonResult updateIntegralBus(ThirdpayRecord record);

    /**
     * 通过邀请码更新用户的积分账户信息
     * referralCode : 推荐码
     * */
    public void updateToUserIntegral(String referralCode);

    /**
     * 获得兑换电子券的初始化信息
     * @param map
     * @return
     */
    public JsonResult coinchangeInfo(Map<String, String> map);
    
    public BigDecimal getCoinRate(String coinCode);
    
    /**
     * 兑换点券提交确认方法
     * @param map
     * @return
     */
    public JsonResult coinchangeConfirm(Map<String, String> map);

    /**
     * 获取用户电子券消费明细
     * */
    public FrontPage findRollDetail(Map<String, String> map);

    /**
     * 根据条件获取用户账户信息
     * */
    public JsonResult findUserRollAccount(Map<String, String> map);

    /**
     * 获取当前用户本月的消费额度
     * */
    public BigDecimal findUserThismonthRoll(String memberId);
    
    public JsonResult calculateCount(Map<String, String> map);
    /**
     *会员详情页---胡一茗二期新增
     * @param map
     * @return
     */
    public JsonResult memberDetail(Map<String, String> map);
    /**
     *奖励记录---胡一茗二期新增
     * @param map
     * @return
     */
    public FrontPage commissionRecord(Map<String, String> map);
    
    

    /**
     * 分页查看一级推荐个数，二级推荐个数，三级推荐个数，四级推荐个数
     * @param pid
     * 注：原先接口调用的是service中发布的方法，
     * 因后期原型字段显示需要积分等级信息，
     * 则现在改为调用integral的实现方式
     * @return
     */
    public FrontPage findConmmendForntPageBySql(Map<String, String> params)throws Exception;

}

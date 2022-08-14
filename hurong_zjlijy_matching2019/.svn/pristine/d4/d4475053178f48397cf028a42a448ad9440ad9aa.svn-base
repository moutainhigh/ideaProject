package hry.mall.third.remote.service;

import hry.bean.JsonResult;

/**
 * @auther zhouming
 * @date 2019/3/19 12:06
 * @Version 1.0
 */
public interface WeixingPayService {

    /**账户余额查询接口*/
    public JsonResult getMerchantBalance();

    /**充值请求接口
     * mobile : 手机号
     * sporderid : 充值流水号
     * price : 面值 （0.1-1000）
     * */
    public JsonResult telephoneRecharge(String mobile, String sporderid, String price);


    /**订单查询接口
     * sporderid : 订单号（流水号）
     * */
    public JsonResult getPayOrderQuery(String sporderid);

    /**
     * 检查回调参数数据是否被串改
     * */
    public JsonResult checkParamData(String userid, String sporderid, String status, String sign);

}

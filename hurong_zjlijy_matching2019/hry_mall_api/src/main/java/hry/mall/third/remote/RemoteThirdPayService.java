package hry.mall.third.remote;

import hry.bean.JsonResult;
import hry.lend.model.page.FrontPage;

import java.util.Map;

public interface RemoteThirdPayService {

    /**账户余额查询接口*/
    public JsonResult getMerchantBalance();

    /***话费充值*/
    public JsonResult telephoneRecharge(String memberId, String sporderid, String mobile, String price);

    /**订单查询接口
     * sporderid : 订单号（流水号）
     * */
    public JsonResult getPayOrderQuery(String sporderid);

    /**
     * 微星充值回调接口
     * */
    public JsonResult payBallBack(String userid, String sporderid, String status, String sign);

    /**
     *根据参数获取交易记录
     * */
    public FrontPage findPayOrderRecord(Map<String, String> paramMap);
}

package hry.mall.order.model.vo;

import hry.bean.BaseModel;

import javax.persistence.Transient;

/**
 * @author luyue
 * @date 2019/12/12 17:53
 */
public class OrderBalanceVo extends BaseModel {

    private Long id;  //订单索引id

    private Long merchantId;  //商户ID

    private String merchantName;  //商户名称

    private String orderSn;  //订单编号

    private String goodsName;  //商品名称

    private String payCoin;  //币支付概况

    private String payType;  //支付方式，2单币种，5组合支付

    private Integer balanceState;  //结算状态:0,未结算,1已结算
    private String balanceTime;  //结算时间

    private String userName;  //买家姓名
    private String  balanceCount;//商户结算币种+数量
    private String platCount; //平台分成数量
    private String oneLevelCount; //一级返佣数量
    private String twoLevelCount; //二级返佣数量
    private String  balanceCode;//结算币种
    private String  goodsNum;//商品数量
    private String  goodsPrice;//商品价钱---usdt

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPayCoin() {
        return payCoin;
    }

    public void setPayCoin(String payCoin) {
        this.payCoin = payCoin;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getBalanceState() {
        return balanceState;
    }

    public void setBalanceState(Integer balanceState) {
        this.balanceState = balanceState;
    }

    public String getBalanceTime() {
        return balanceTime;
    }

    public void setBalanceTime(String balanceTime) {
        this.balanceTime = balanceTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBalanceCount() {
        return balanceCount;
    }

    public void setBalanceCount(String balanceCount) {
        this.balanceCount = balanceCount;
    }

    public String getPlatCount() {
        return platCount;
    }

    public void setPlatCount(String platCount) {
        this.platCount = platCount;
    }

    public String getOneLevelCount() {
        return oneLevelCount;
    }

    public void setOneLevelCount(String oneLevelCount) {
        this.oneLevelCount = oneLevelCount;
    }

    public String getTwoLevelCount() {
        return twoLevelCount;
    }

    public void setTwoLevelCount(String twoLevelCount) {
        this.twoLevelCount = twoLevelCount;
    }

    public String getBalanceCode() {
        return balanceCode;
    }

    public void setBalanceCode(String balanceCode) {
        this.balanceCode = balanceCode;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}

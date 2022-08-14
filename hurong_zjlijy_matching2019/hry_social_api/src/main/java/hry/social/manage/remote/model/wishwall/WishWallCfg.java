/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2018-12-18 15:46:48
 */
package hry.social.manage.remote.model.wishwall;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p> SocialWishWall </p>
 *
 * @author: lixin
 * @Date :          2018-12-18 15:46:48
 */
public class WishWallCfg implements Serializable {

    private String coinCode;  //缴费币种

    private String fee;  //费用

    private BigDecimal rmbPrice;  //人民币定价

    private BigDecimal rewardNum;  //免密打赏数量

    private BigDecimal feeRatio;  //打赏手续费

    public BigDecimal getRewardNum() {
        return rewardNum;
    }

    public void setRewardNum(BigDecimal rewardNum) {
        this.rewardNum = rewardNum;
    }

    public BigDecimal getFeeRatio() {
        return feeRatio;
    }

    public void setFeeRatio(BigDecimal feeRatio) {
        this.feeRatio = feeRatio;
    }

    public BigDecimal getRmbPrice() {
        return rmbPrice;
    }

    public void setRmbPrice(BigDecimal rmbPrice) {
        this.rmbPrice = rmbPrice;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}

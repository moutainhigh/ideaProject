/**
 * 111
 */

package hry.klg.remote.model;

import java.io.Serializable;
import java.util.List;

public class RemotePurchaseInfo implements Serializable {

    private String purchasePrice;//买入价

    private String purchaseNum;//买入量

    private String[] buyNums; //可以购买数量

    private String purchaseMoney;//买入额

    private String marginMoney;//保证金

    private String marginRatio;//保证金比率

    public String getMarginRatio() {
        return marginRatio;
    }

    public void setMarginRatio(String marginRatio) {
        this.marginRatio = marginRatio;
    }

    public String[] getBuyNums() {
        return buyNums;
    }

    public void setBuyNums(String[] buyNums) {
        this.buyNums = buyNums;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(String purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getPurchaseMoney() {
        return purchaseMoney;
    }

    public void setPurchaseMoney(String purchaseMoney) {
        this.purchaseMoney = purchaseMoney;
    }

    public String getMarginMoney() {
        return marginMoney;
    }

    public void setMarginMoney(String marginMoney) {
        this.marginMoney = marginMoney;
    }
}

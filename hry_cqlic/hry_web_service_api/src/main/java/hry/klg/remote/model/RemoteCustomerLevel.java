/**
 * 111
 */

package hry.klg.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class RemoteCustomerLevel implements Serializable {

    private Long customerId;  //用户id

    private Long levelId;  //等级id

    private String levelName;  //等级名称

    private Integer sort;  //等级排序

    private BigDecimal gradation;  //级别差

    private Integer pointAlgebra;  //见点代数

    private BigDecimal rewardNum;  //奖金额度

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public BigDecimal getGradation() {
        return gradation;
    }

    public void setGradation(BigDecimal gradation) {
        this.gradation = gradation;
    }

    public Integer getPointAlgebra() {
        return pointAlgebra;
    }

    public void setPointAlgebra(Integer pointAlgebra) {
        this.pointAlgebra = pointAlgebra;
    }

    public BigDecimal getRewardNum() {
        return rewardNum;
    }

    public void setRewardNum(BigDecimal rewardNum) {
        this.rewardNum = rewardNum;
    }
}

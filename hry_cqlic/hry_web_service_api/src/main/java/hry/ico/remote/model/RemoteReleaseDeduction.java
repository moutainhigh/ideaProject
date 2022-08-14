package hry.ico.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class RemoteReleaseDeduction implements Serializable{

    private Long releaseDeduct;  //释放扣除

    private Integer releaseDeductType;  //释放扣除类型（1.经验扣除2.itx扣除）

    private BigDecimal subtractNum;// 计算出扣除的数量

    public BigDecimal getSubtractNum() {
        return subtractNum;
    }

    public void setSubtractNum(BigDecimal subtractNum) {
        this.subtractNum = subtractNum;
    }

    public Long getReleaseDeduct() {
        return releaseDeduct;
    }

    public void setReleaseDeduct(Long releaseDeduct) {
        this.releaseDeduct = releaseDeduct;
    }

    public Integer getReleaseDeductType() {
        return releaseDeductType;
    }

    public void setReleaseDeductType(Integer releaseDeductType) {
        this.releaseDeductType = releaseDeductType;
    }
}

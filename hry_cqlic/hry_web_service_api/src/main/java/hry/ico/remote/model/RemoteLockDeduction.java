package hry.ico.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class RemoteLockDeduction implements Serializable{

    private BigDecimal lockDeduct;  //锁仓扣除

    private Integer lockDeductType;  //锁仓扣除类型（1.经验扣除2.itx扣除）

    public BigDecimal getLockDeduct() {
        return lockDeduct;
    }

    public void setLockDeduct(BigDecimal lockDeduct) {
        this.lockDeduct = lockDeduct;
    }

    public Integer getLockDeductType() {
        return lockDeductType;
    }

    public void setLockDeductType(Integer lockDeductType) {
        this.lockDeductType = lockDeductType;
    }
}

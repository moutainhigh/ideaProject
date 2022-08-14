package hry.ico.remote.model;

import hry.bean.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RemoteIcoLockRecord extends BaseModel implements Serializable{
    private Long id; //锁仓操作Id
    private String coinCode;  //币种Code

    private Integer type;  //是否ICO阶段(1.ico阶段0.非ico)

    private BigDecimal number;  //数量

    private Integer lockDay;  //锁仓天数

    private Integer state;  //状态(0.待释放1.已释放)

    private BigDecimal lockDeduct;  //锁仓扣除

    private Integer lockDeductType;  //锁仓扣除类型（1.经验扣除2.itx扣除）

    private BigDecimal releaseDeduct;  //释放扣除

    private Integer releaseDeductType;  //释放扣除类型（1.经验扣除2.itx扣除）

    private Date releaseDate;  //释放时间

    private Integer releaseType;  //释放类型（1为提前释放0正常）

    private BigDecimal currentLockSum;//当前锁仓总数

    private BigDecimal actualReleaseNum;  //实际释放数量

    private Date actualReleaseTime;  //实际释放时间

    private BigDecimal releaseDeductNum;  //释放扣币数量

    public BigDecimal getReleaseDeductNum() {
        return releaseDeductNum;
    }

    public void setReleaseDeductNum(BigDecimal releaseDeductNum) {
        this.releaseDeductNum = releaseDeductNum;
    }

    public BigDecimal getActualReleaseNum() {
        return actualReleaseNum;
    }

    public void setActualReleaseNum(BigDecimal actualReleaseNum) {
        this.actualReleaseNum = actualReleaseNum;
    }

    public Date getActualReleaseTime() {
        return actualReleaseTime;
    }

    public void setActualReleaseTime(Date actualReleaseTime) {
        this.actualReleaseTime = actualReleaseTime;
    }

    public BigDecimal getCurrentLockSum() {
        return currentLockSum;
    }

    public void setCurrentLockSum(BigDecimal currentLockSum) {
        this.currentLockSum = currentLockSum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public Integer getLockDay() {
        return lockDay;
    }

    public void setLockDay(Integer lockDay) {
        this.lockDay = lockDay;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

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

    public BigDecimal getReleaseDeduct() {
        return releaseDeduct;
    }

    public void setReleaseDeduct(BigDecimal releaseDeduct) {
        this.releaseDeduct = releaseDeduct;
    }

    public Integer getReleaseDeductType() {
        return releaseDeductType;
    }

    public void setReleaseDeductType(Integer releaseDeductType) {
        this.releaseDeductType = releaseDeductType;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}

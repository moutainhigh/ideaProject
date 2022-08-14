/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 14:44:12
 */
package hry.social.manage.remote.model.force;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> SocialCalculateForce </p>
 * @author: javalx
 * @Date :          2019-06-11 14:44:12  
 */
@Table(name = "social_calculate_force")
public class SocialCalculateForce extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "customerId")
    private Long customerId;  //用户ID

    @Column(name = "perpetualForce")
    private BigDecimal perpetualForce;  //永久算力值

    @Column(name = "terminableForce")
    private BigDecimal terminableForce;  //时效算力值

    @Column(name = "additionForce")
    private BigDecimal additionForce;  //加成算力值

    @Column(name = "totalForce")
    private BigDecimal totalForce;  //个人总算力值

    @Transient
    private BigDecimal allTotal;  //全网总算力

    @Transient
    private BigDecimal firstForce;  //榜首算力

    @Transient
    private BigDecimal percent;  //个人算力占比

    @Transient
    private BigDecimal alloutput;  //全网日产出

    @Transient
    private BigDecimal output;  //个人总产出

    @Transient
    private int rankNum;  //个人算力排名

    @Transient
    private String nickName;  //昵称

    @Transient
    private String headPortrait;  //头像

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public int getRankNum() {
        return rankNum;
    }

    public void setRankNum(int rankNum) {
        this.rankNum = rankNum;
    }

    public BigDecimal getAllTotal() {
        return allTotal;
    }

    public void setAllTotal(BigDecimal allTotal) {
        this.allTotal = allTotal;
    }

    public BigDecimal getFirstForce() {
        return firstForce;
    }

    public void setFirstForce(BigDecimal firstForce) {
        this.firstForce = firstForce;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public BigDecimal getAlloutput() {
        return alloutput;
    }

    public void setAlloutput(BigDecimal alloutput) {
        this.alloutput = alloutput;
    }

    public BigDecimal getOutput() {
        return output;
    }

    public void setOutput(BigDecimal output) {
        this.output = output;
    }

    /**
     * <p>主键</p>
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-11 14:44:12
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>主键</p>
     * @author: javalx
     * @param:   @param id
     * @return: void
     * @Date :   2019-06-11 14:44:12
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>用户ID</p>
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-11 14:44:12
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>用户ID</p>
     * @author: javalx
     * @param:   @param customerId
     * @return: void
     * @Date :   2019-06-11 14:44:12
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>永久算力值</p>
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-11 14:44:12
     */
    public BigDecimal getPerpetualForce() {
        return perpetualForce;
    }

    /**
     * <p>永久算力值</p>
     * @author: javalx
     * @param:   @param perpetualForce
     * @return: void
     * @Date :   2019-06-11 14:44:12
     */
    public void setPerpetualForce(BigDecimal perpetualForce) {
        this.perpetualForce = perpetualForce;
    }


    /**
     * <p>时效算力值</p>
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-11 14:44:12
     */
    public BigDecimal getTerminableForce() {
        return terminableForce;
    }

    /**
     * <p>时效算力值</p>
     * @author: javalx
     * @param:   @param terminableForce
     * @return: void
     * @Date :   2019-06-11 14:44:12
     */
    public void setTerminableForce(BigDecimal terminableForce) {
        this.terminableForce = terminableForce;
    }


    /**
     * <p>加成算力值</p>
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-11 14:44:12
     */
    public BigDecimal getAdditionForce() {
        return additionForce;
    }

    /**
     * <p>加成算力值</p>
     * @author: javalx
     * @param:   @param additionForce
     * @return: void
     * @Date :   2019-06-11 14:44:12
     */
    public void setAdditionForce(BigDecimal additionForce) {
        this.additionForce = additionForce;
    }


    /**
     * <p>总算力值</p>
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-11 14:44:12
     */
    public BigDecimal getTotalForce() {
        return totalForce;
    }

    /**
     * <p>总算力值</p>
     * @author: javalx
     * @param:   @param totalForce
     * @return: void
     * @Date :   2019-06-11 14:44:12
     */
    public void setTotalForce(BigDecimal totalForce) {
        this.totalForce = totalForce;
    }


}

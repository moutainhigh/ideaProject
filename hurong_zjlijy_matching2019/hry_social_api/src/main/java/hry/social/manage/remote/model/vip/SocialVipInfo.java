/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-12 11:24:44
 */
package hry.social.manage.remote.model.vip;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> SocialVipInfo </p>
 *
 * @author: javalx
 * @Date :          2019-06-12 11:24:44
 */
@Table(name = "social_vip_info")
public class SocialVipInfo extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //序号

    @Column(name = "vipName")
    private String vipName;  //会员等级

    @Column(name = "serialNum")
    private String serialNum;  //会员编号

    @Column(name = "additionRatio")
    private BigDecimal additionRatio;  //加成比例（%）

    @Column(name = "timeNum")
    private Integer timeNum;  //会员期限

    @Column(name = "price")
    private BigDecimal price;  //价格(CNY)

    @Column(name = "img")
    private String img;  //图标

    @Column(name = "vipSort")
    private Integer vipSort;  //等级排序

    @Column(name = "states")
    private Integer states;  //开启状态 0：关闭，1：开启

    @Transient
    private BigDecimal taskForceOldAdd;    // 当前任务算力值

    @Transient
    private BigDecimal millForceOldAdd;    // 当前矿机算力值

    @Transient
    private BigDecimal millCoinOldAdd;     // 当前矿机数币值

    @Transient
    private BigDecimal taskForceNewAdd; // 任务算力加成后值

    @Transient
    private BigDecimal millForceNewAdd; // 矿机算力加成后值

    @Transient
    private BigDecimal millCoinNewAdd;  // 矿机数币加成后值

    @Transient
    private BigDecimal taskForceUp;  // 任务算力提升值

    @Transient
    private BigDecimal millForceUp;  // 矿机算力提升值

    @Transient
    private BigDecimal millCoinUp;   // 矿机数币提升值

    public BigDecimal getTaskForceOldAdd() {
        return taskForceOldAdd;
    }

    public void setTaskForceOldAdd(BigDecimal taskForceOldAdd) {
        this.taskForceOldAdd = taskForceOldAdd;
    }

    public BigDecimal getMillForceOldAdd() {
        return millForceOldAdd;
    }

    public void setMillForceOldAdd(BigDecimal millForceOldAdd) {
        this.millForceOldAdd = millForceOldAdd;
    }

    public BigDecimal getMillCoinOldAdd() {
        return millCoinOldAdd;
    }

    public void setMillCoinOldAdd(BigDecimal millCoinOldAdd) {
        this.millCoinOldAdd = millCoinOldAdd;
    }

    public BigDecimal getTaskForceNewAdd() {
        return taskForceNewAdd;
    }

    public void setTaskForceNewAdd(BigDecimal taskForceNewAdd) {
        this.taskForceNewAdd = taskForceNewAdd;
    }

    public BigDecimal getMillForceNewAdd() {
        return millForceNewAdd;
    }

    public void setMillForceNewAdd(BigDecimal millForceNewAdd) {
        this.millForceNewAdd = millForceNewAdd;
    }

    public BigDecimal getMillCoinNewAdd() {
        return millCoinNewAdd;
    }

    public void setMillCoinNewAdd(BigDecimal millCoinNewAdd) {
        this.millCoinNewAdd = millCoinNewAdd;
    }

    public BigDecimal getTaskForceUp() {
        return taskForceUp;
    }

    public void setTaskForceUp(BigDecimal taskForceUp) {
        this.taskForceUp = taskForceUp;
    }

    public BigDecimal getMillForceUp() {
        return millForceUp;
    }

    public void setMillForceUp(BigDecimal millForceUp) {
        this.millForceUp = millForceUp;
    }

    public BigDecimal getMillCoinUp() {
        return millCoinUp;
    }

    public void setMillCoinUp(BigDecimal millCoinUp) {
        this.millCoinUp = millCoinUp;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * <p>序号</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-12 11:24:44
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>序号</p>
     *
     * @author: javalx
     * @param: @param id
     * @return: void
     * @Date :   2019-06-12 11:24:44
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>会员等级</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-12 11:24:44
     */
    public String getVipName() {
        return vipName;
    }

    /**
     * <p>会员等级</p>
     *
     * @author: javalx
     * @param: @param vipName
     * @return: void
     * @Date :   2019-06-12 11:24:44
     */
    public void setVipName(String vipName) {
        this.vipName = vipName;
    }


    /**
     * <p>加成比例（%）</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-12 11:24:44
     */
    public BigDecimal getAdditionRatio() {
        return additionRatio;
    }

    /**
     * <p>加成比例（%）</p>
     *
     * @author: javalx
     * @param: @param additionRatio
     * @return: void
     * @Date :   2019-06-12 11:24:44
     */
    public void setAdditionRatio(BigDecimal additionRatio) {
        this.additionRatio = additionRatio;
    }


    /**
     * <p>会员期限</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-06-12 11:24:44
     */
    public Integer getTimeNum() {
        return timeNum;
    }

    /**
     * <p>会员期限</p>
     *
     * @author: javalx
     * @param: @param timeNum
     * @return: void
     * @Date :   2019-06-12 11:24:44
     */
    public void setTimeNum(Integer timeNum) {
        this.timeNum = timeNum;
    }


    /**
     * <p>价格(CNY)</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-12 11:24:44
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * <p>价格(CNY)</p>
     *
     * @author: javalx
     * @param: @param price
     * @return: void
     * @Date :   2019-06-12 11:24:44
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    /**
     * <p>图标</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-12 11:24:44
     */
    public String getImg() {
        return img;
    }

    /**
     * <p>图标</p>
     *
     * @author: javalx
     * @param: @param img
     * @return: void
     * @Date :   2019-06-12 11:24:44
     */
    public void setImg(String img) {
        this.img = img;
    }


    /**
     * <p>等级排序</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-06-12 11:24:44
     */
    public Integer getVipSort() {
        return vipSort;
    }

    /**
     * <p>等级排序</p>
     *
     * @author: javalx
     * @param: @param vipSort
     * @return: void
     * @Date :   2019-06-12 11:24:44
     */
    public void setVipSort(Integer vipSort) {
        this.vipSort = vipSort;
    }


    /**
     * <p>开启状态 0：关闭，1：开启</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-06-12 11:24:44
     */
    public Integer getStates() {
        return states;
    }

    /**
     * <p>开启状态 0：关闭，1：开启</p>
     *
     * @author: javalx
     * @param: @param states
     * @return: void
     * @Date :   2019-06-12 11:24:44
     */
    public void setStates(Integer states) {
        this.states = states;
    }


}

package hry.exchange.tradingArea.model;

import hry.bean.BaseModel;

import javax.persistence.*;
/**
 * 交易区接口使用实体
 */

@Table(name="ex_tradingarea")
public class ExTradingArea extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //

    @Column(name= "tradingArea")
    private String tradingArea;  // 交易区

    @Column(name= "struts")
    private Integer struts;  // 开启关闭

    @Column(name= "sort")
    private Integer sort;  //排序

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getTradingArea () {
        return tradingArea;
    }

    public void setTradingArea (String tradingArea) {
        this.tradingArea = tradingArea;
    }

    public Integer getStruts () {
        return struts;
    }

    public void setStruts (Integer struts) {
        this.struts = struts;
    }

    public Integer getSort () {
        return sort;
    }

    public void setSort (Integer sort) {
        this.sort = sort;
    }
}

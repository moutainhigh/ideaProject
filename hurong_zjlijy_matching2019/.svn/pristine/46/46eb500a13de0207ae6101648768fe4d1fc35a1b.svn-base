/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-07-04 11:47:40
 */
package hry.social.manage.remote.model.SocialPayConf;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> SocialPayConf </p>
 *
 * @author: javalx
 * @Date :          2019-07-04 11:47:40
 */
@Table(name = "social_pay_conf")
public class SocialPayConf extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //序号

    @Column(name = "coinCode")
    private String coinCode;  //币种

    @Column(name = "type")
    private Integer type;  //类型 ： 1:矿机，2:会员

    @Transient
    private BigDecimal HotMoney;//可用余额

    public BigDecimal getHotMoney() {
        return HotMoney;
    }

    public void setHotMoney(BigDecimal hotMoney) {
        HotMoney = hotMoney;
    }

    /**
     * <p>序号</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-07-04 11:47:40
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
     * @Date :   2019-07-04 11:47:40
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>币种</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-07-04 11:47:40
     */
    public String getCoinCode() {
        return coinCode;
    }

    /**
     * <p>币种</p>
     *
     * @author: javalx
     * @param: @param coinCode
     * @return: void
     * @Date :   2019-07-04 11:47:40
     */
    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }


    /**
     * <p>类型 ： 1:矿机，2:会员</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-07-04 11:47:40
     */
    public Integer getType() {
        return type;
    }

    /**
     * <p>类型 ： 1:矿机，2:会员</p>
     *
     * @author: javalx
     * @param: @param type
     * @return: void
     * @Date :   2019-07-04 11:47:40
     */
    public void setType(Integer type) {
        this.type = type;
    }


}

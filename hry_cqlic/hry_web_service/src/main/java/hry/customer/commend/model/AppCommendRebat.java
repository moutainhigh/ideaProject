package hry.customer.commend.model;

import hry.core.mvc.model.BaseModel;
import hry.customer.person.model.AppPersonInfo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author <a href="mailto:17610171876@163.com">Mr_He</a>
 * @Copyright (c)</ b> 何川<br/>
 * @createTime 2018/2/8 10:56
 * @Description:
 */
@Table(name="app_commend_rebat")
public class AppCommendRebat extends BaseModel{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name= "customerId")
    private Integer customerId;
	// 编号
	@Column(name = "userCode")
	private String userCode;
	
    //派发金额
    @Column(name= "rebatMoney")
    private BigDecimal rebatMoney;
    
    @Column(name= "coinCode")
    private String coinCode;

    @Column(name = "status")
    private Integer status;
	//  业务单号
	@Column(name = "transactionNum")
	private String transactionNum;
	
	@Transient   //不与数据库映射的字段
	private AppPersonInfo appPersonInfo;
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }


    public BigDecimal getRebatMoney() {
        return rebatMoney;
    }

    public void setRebatMoney(BigDecimal rebatMoney) {
        this.rebatMoney = rebatMoney;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

	public String getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}


    
    
}

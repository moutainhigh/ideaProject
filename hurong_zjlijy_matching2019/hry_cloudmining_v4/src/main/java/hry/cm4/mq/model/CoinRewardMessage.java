package hry.cm4.mq.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CoinRewardMessage implements Serializable {

    private String coinCode;  //奖励币种

    private Long customerId;  //奖励用户ID

    private Integer rewardType;  //奖励类型 1:算力奖励 2:任务奖励 3:矿机奖励 4:消费奖励 5:分销奖励

    private BigDecimal rewardNum;  //奖励数量
    
    private String businessNum;//业务编号(id)
    
    private String businessType;//业务类别
    

    public String getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	/**
     * 奖励币种
     *
     * @return
     */
    public String getCoinCode() {
        return coinCode;
    }

    /**
     * 奖励币种
     *
     * @return
     */
    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    /**
     * 奖励用户ID（被收取人ID）
     *
     * @return
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 奖励用户ID（被收取人ID）
     *
     * @return
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 奖励类型
     *
     * @return
     */
    public Integer getRewardType() {
        return rewardType;
    }

    /**
     * 奖励类型
     *
     * @return
     */
    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    /**
     * 奖励数量
     *
     * @return
     */
    public BigDecimal getRewardNum() {
        return rewardNum;
    }

    /**
     * 奖励数量
     *
     * @return
     */
    public void setRewardNum(BigDecimal rewardNum) {
        this.rewardNum = rewardNum;
    }
}

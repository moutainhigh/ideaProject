package hry.mall.integral.remote.model;

import hry.bean.BaseModel;

import java.math.BigDecimal;

public class CustomerIntegralVo extends BaseModel {
	private Long id;  //主键
	
	private Long memberId;  //用户Id
	
	private String integralType;  //积分类型
	
	private String integralName;  //积分名称
	
	private BigDecimal integralPrice;  //积分价值
	
	private BigDecimal totalIntegral;  //总积分值
	
	private BigDecimal availableIntegral;  //可用积分值
	
	private BigDecimal freezeIntegral;  //冻结积分值
	
	private String remark;  //
	
	private String saasId;  //saasId

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getIntegralType() {
		return integralType;
	}

	public void setIntegralType(String integralType) {
		this.integralType = integralType;
	}

	public String getIntegralName() {
		return integralName;
	}

	public void setIntegralName(String integralName) {
		this.integralName = integralName;
	}

	public BigDecimal getIntegralPrice() {
		return integralPrice;
	}

	public void setIntegralPrice(BigDecimal integralPrice) {
		this.integralPrice = integralPrice;
	}

	public BigDecimal getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(BigDecimal totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public BigDecimal getAvailableIntegral() {
		return availableIntegral;
	}

	public void setAvailableIntegral(BigDecimal availableIntegral) {
		this.availableIntegral = availableIntegral;
	}

	public BigDecimal getFreezeIntegral() {
		return freezeIntegral;
	}

	public void setFreezeIntegral(BigDecimal freezeIntegral) {
		this.freezeIntegral = freezeIntegral;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
}

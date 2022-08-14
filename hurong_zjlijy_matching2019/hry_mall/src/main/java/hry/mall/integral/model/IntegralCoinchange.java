/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-03-22 18:27:36 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> IntegralCoinchange </p>
 * @author:         luyue
 * @Date :          2019-03-22 18:27:36  
 */
@Table(name="shop_integral_coinchange")
public class IntegralCoinchange extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "memberId")
	private Long memberId;  //用户Id
	
	@Column(name= "count")
	private BigDecimal count;  //兑换数量
	
	@Column(name= "coinCode")
	private String coinCode;  //兑换币种
	
	@Column(name= "discountRate")
	private BigDecimal discountRate;  //电子券兑换折扣
	
	@Column(name= "coinCount")
	private BigDecimal coinCount;  //数字币支付数量
	
	@Column(name= "coinRate")
	private BigDecimal coinRate;  //对数字币比例
	
	@Column(name= "status")
	private Short status;  //状态：1成功
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "changeDate")
	private String changeDate;  //兑换日期
	
	@Column(name= "number")
	private String number;  //编号
	
    @Transient
    private BigDecimal totalMoney; //账户中币的总额
    
    @Transient
    private BigDecimal totalCount; //账户中币的总额可以兑换数量
    
    @Transient
    private BigDecimal oneCount; //一个币可以兑换数量
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(BigDecimal totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getOneCount() {
		return oneCount;
	}

	public void setOneCount(BigDecimal oneCount) {
		this.oneCount = oneCount;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-03-22 18:27:36    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-03-22 18:27:36   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-03-22 18:27:36    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-03-22 18:27:36   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>兑换数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-03-22 18:27:36    
	 */
	public BigDecimal getCount() {
		return count;
	}
	
	/**
	 * <p>兑换数量</p>
	 * @author:  luyue
	 * @param:   @param count
	 * @return:  void 
	 * @Date :   2019-03-22 18:27:36   
	 */
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	
	
	/**
	 * <p>兑换币种</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-03-22 18:27:36    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>兑换币种</p>
	 * @author:  luyue
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-03-22 18:27:36   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>电子券兑换折扣</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-03-22 18:27:36    
	 */
	public BigDecimal getDiscountRate() {
		return discountRate;
	}
	
	/**
	 * <p>电子券兑换折扣</p>
	 * @author:  luyue
	 * @param:   @param discountRate
	 * @return:  void 
	 * @Date :   2019-03-22 18:27:36   
	 */
	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}
	
	
	/**
	 * <p>数字币支付数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-03-22 18:27:36    
	 */
	public BigDecimal getCoinCount() {
		return coinCount;
	}
	
	/**
	 * <p>数字币支付数量</p>
	 * @author:  luyue
	 * @param:   @param coinCount
	 * @return:  void 
	 * @Date :   2019-03-22 18:27:36   
	 */
	public void setCoinCount(BigDecimal coinCount) {
		this.coinCount = coinCount;
	}
	
	
	/**
	 * <p>对数字币比例</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-03-22 18:27:36    
	 */
	public BigDecimal getCoinRate() {
		return coinRate;
	}
	
	/**
	 * <p>对数字币比例</p>
	 * @author:  luyue
	 * @param:   @param coinRate
	 * @return:  void 
	 * @Date :   2019-03-22 18:27:36   
	 */
	public void setCoinRate(BigDecimal coinRate) {
		this.coinRate = coinRate;
	}
	
	

}

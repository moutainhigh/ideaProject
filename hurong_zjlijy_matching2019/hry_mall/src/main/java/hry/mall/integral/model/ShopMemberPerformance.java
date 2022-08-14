/**
 * Copyright:   
 * @author:      houzhen
 * @version:     V1.0 
 * @Date:        2019-06-26 21:03:56 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> ShopMemberPerformance </p>
 * @author:         houzhen
 * @Date :          2019-06-26 21:03:56  
 */
@Table(name="shop_member_performance")
public class ShopMemberPerformance extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "memberId")
	private Long memberId;  //用户Id
	
	@Column(name= "newPerformance")
	private BigDecimal newPerformance;  //昨日新增
	
	@Column(name= "allPerformance")
	private BigDecimal allPerformance;  //全部业绩
	
	@Column(name= "teamNewPerformance")
	private BigDecimal teamNewPerformance;  //团队昨日新增
	
	@Column(name= "teamAllPerformance")
	private BigDecimal teamAllPerformance;  //团队全部业绩
	
	@Column(name= "remark")
	private String remark;  //备注

	@Column(name= "teamIncome")
	private BigDecimal teamIncome;  //团队收益

	@Column(name= "income")
	private BigDecimal income;  //个人收益




	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getTeamIncome() {
		return teamIncome;
	}

	public void setTeamIncome(BigDecimal teamIncome) {
		this.teamIncome = teamIncome;
	}



	/**
	 * <p>主键id</p>
	 * @author:  houzhen
	 * @return:  Long 
	 * @Date :   2019-06-26 21:03:56    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  houzhen
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-26 21:03:56   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  houzhen
	 * @return:  Long 
	 * @Date :   2019-06-26 21:03:56    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  houzhen
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-06-26 21:03:56   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>昨日新增</p>
	 * @author:  houzhen
	 * @return:  BigDecimal 
	 * @Date :   2019-06-26 21:03:56    
	 */
	public BigDecimal getNewPerformance() {
		return newPerformance;
	}
	
	/**
	 * <p>昨日新增</p>
	 * @author:  houzhen
	 * @param:   @param newPerformance
	 * @return:  void 
	 * @Date :   2019-06-26 21:03:56   
	 */
	public void setNewPerformance(BigDecimal newPerformance) {
		this.newPerformance = newPerformance;
	}
	
	
	/**
	 * <p>全部业绩</p>
	 * @author:  houzhen
	 * @return:  BigDecimal 
	 * @Date :   2019-06-26 21:03:56    
	 */
	public BigDecimal getAllPerformance() {
		return allPerformance;
	}
	
	/**
	 * <p>全部业绩</p>
	 * @author:  houzhen
	 * @param:   @param allPerformance
	 * @return:  void 
	 * @Date :   2019-06-26 21:03:56   
	 */
	public void setAllPerformance(BigDecimal allPerformance) {
		this.allPerformance = allPerformance;
	}
	
	
	/**
	 * <p>团队昨日新增</p>
	 * @author:  houzhen
	 * @return:  BigDecimal 
	 * @Date :   2019-06-26 21:03:56    
	 */
	public BigDecimal getTeamNewPerformance() {
		return teamNewPerformance;
	}
	
	/**
	 * <p>团队昨日新增</p>
	 * @author:  houzhen
	 * @param:   @param teamNewPerformance
	 * @return:  void 
	 * @Date :   2019-06-26 21:03:56   
	 */
	public void setTeamNewPerformance(BigDecimal teamNewPerformance) {
		this.teamNewPerformance = teamNewPerformance;
	}
	
	
	/**
	 * <p>团队全部业绩</p>
	 * @author:  houzhen
	 * @return:  BigDecimal 
	 * @Date :   2019-06-26 21:03:56    
	 */
	public BigDecimal getTeamAllPerformance() {
		return teamAllPerformance;
	}
	
	/**
	 * <p>团队全部业绩</p>
	 * @author:  houzhen
	 * @param:   @param teamAllPerformance
	 * @return:  void 
	 * @Date :   2019-06-26 21:03:56   
	 */
	public void setTeamAllPerformance(BigDecimal teamAllPerformance) {
		this.teamAllPerformance = teamAllPerformance;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  houzhen
	 * @return:  String 
	 * @Date :   2019-06-26 21:03:56    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  houzhen
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-06-26 21:03:56   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

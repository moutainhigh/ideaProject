/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-18 19:00:43 
 */
package hry.admin.klg.limit.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> KlgDesignatedRewardecord </p>
 * @author:         lzy
 * @Date :          2019-04-18 19:00:43  
 */
@Table(name="klg_designated_reward_record")
public class KlgDesignatedRewardecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "gradation")
	private BigDecimal gradation;  //级别差
	
	@Column(name= "pointAlgebra")
	private Integer pointAlgebra;  //见点代数
	
	@Column(name= "rewardNum")
	private BigDecimal rewardNum;  //奖金额度
	
	@Column(name= "operatorId")
	private Long operatorId;  //操作人
	
	@Column(name= "operatorIdName")
	private String operatorIdName;  //操作人名

	@Transient // 不与数据库映射的字段
	private String mobilePhone;   //用户手机号

	@Transient // 不与数据库映射的字段
	private String email;      	//用户email

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-18 19:00:43    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-18 19:00:43   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-18 19:00:43    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-04-18 19:00:43   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>级别差</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 19:00:43    
	 */
	public BigDecimal getGradation() {
		return gradation;
	}
	
	/**
	 * <p>级别差</p>
	 * @author:  lzy
	 * @param:   @param gradation
	 * @return:  void 
	 * @Date :   2019-04-18 19:00:43   
	 */
	public void setGradation(BigDecimal gradation) {
		this.gradation = gradation;
	}
	
	
	/**
	 * <p>见点代数</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-04-18 19:00:43    
	 */
	public Integer getPointAlgebra() {
		return pointAlgebra;
	}
	
	/**
	 * <p>见点代数</p>
	 * @author:  lzy
	 * @param:   @param pointAlgebra
	 * @return:  void 
	 * @Date :   2019-04-18 19:00:43   
	 */
	public void setPointAlgebra(Integer pointAlgebra) {
		this.pointAlgebra = pointAlgebra;
	}
	
	
	/**
	 * <p>奖金额度</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 19:00:43    
	 */
	public BigDecimal getRewardNum() {
		return rewardNum;
	}
	
	/**
	 * <p>奖金额度</p>
	 * @author:  lzy
	 * @param:   @param rewardNum
	 * @return:  void 
	 * @Date :   2019-04-18 19:00:43   
	 */
	public void setRewardNum(BigDecimal rewardNum) {
		this.rewardNum = rewardNum;
	}
	
	
	/**
	 * <p>操作人</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-04-18 19:00:43    
	 */
	public Long getOperatorId() {
		return operatorId;
	}
	
	/**
	 * <p>操作人</p>
	 * @author:  lzy
	 * @param:   @param operatorId
	 * @return:  void 
	 * @Date :   2019-04-18 19:00:43   
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	
	
	/**
	 * <p>操作人名</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-04-18 19:00:43    
	 */
	public String getOperatorIdName() {
		return operatorIdName;
	}
	
	/**
	 * <p>操作人名</p>
	 * @author:  lzy
	 * @param:   @param operatorIdName
	 * @return:  void 
	 * @Date :   2019-04-18 19:00:43   
	 */
	public void setOperatorIdName(String operatorIdName) {
		this.operatorIdName = operatorIdName;
	}
	
	

}

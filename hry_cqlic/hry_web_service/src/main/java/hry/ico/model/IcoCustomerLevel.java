/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-16 21:09:20 
 */
package hry.ico.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> IcoCustomerLevel </p>
 * @author:         houz
 * @Date :          2019-01-16 21:09:20  
 */
@Table(name="ico_customer_level")
public class IcoCustomerLevel extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customer_id")
	private Long customer_id;  //用户id
	
	@Column(name= "level_id")
	private Long level_id;  //
	
	@Column(name= "gradeName")
	private String gradeName;  //等级名称
	
	@Column(name= "experience")
	private Long experience;  //经验值
	
	@Column(name= "vesion")
	private Integer vesion;  //版本

	@Column(name= "deductExperience")
	private Long deductExperience;  //总扣除经验值

	@Column(name= "awardExperience")
	private Long awardExperience;  //总奖励经验值

	@Column(name= "sort")
	private Integer sort;//等级排序

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  Long
	 * @Date :   2019-01-16 21:09:20
	 */
	public Long getDeductExperience() {
		return deductExperience;
	}

	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param deductExperience
	 * @return:  void
	 * @Date :   2019-01-16 21:09:20
	 */
	public void setDeductExperience(Long deductExperience) {
		this.deductExperience = deductExperience;
	}


	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  Long
	 * @Date :   2019-01-16 21:09:20
	 */
	public Long getAwardExperience() {
		return awardExperience;
	}

	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param awardExperience
	 * @return:  void
	 * @Date :   2019-01-16 21:09:20
	 */
	public void setAwardExperience(Long awardExperience) {
		this.awardExperience = awardExperience;
	}


	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-16 21:09:20    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-16 21:09:20   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-16 21:09:20    
	 */
	public Long getCustomer_id() {
		return customer_id;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  houz
	 * @param:   @param customer_id
	 * @return:  void 
	 * @Date :   2019-01-16 21:09:20   
	 */
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-16 21:09:20    
	 */
	public Long getLevel_id() {
		return level_id;
	}
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param level_id
	 * @return:  void 
	 * @Date :   2019-01-16 21:09:20   
	 */
	public void setLevel_id(Long level_id) {
		this.level_id = level_id;
	}
	
	
	/**
	 * <p>等级id</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-16 21:09:20    
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * <p>等级id</p>
	 * @author:  houz
	 * @param:   @param gradeName
	 * @return:  void 
	 * @Date :   2019-01-16 21:09:20   
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	/**
	 * <p>经验值</p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-16 21:09:20    
	 */
	public Long getExperience() {
		return experience;
	}
	
	/**
	 * <p>经验值</p>
	 * @author:  houz
	 * @param:   @param experience
	 * @return:  void 
	 * @Date :   2019-01-16 21:09:20   
	 */
	public void setExperience(Long experience) {
		this.experience = experience;
	}
	
	
	/**
	 * <p>版本</p>
	 * @author:  houz
	 * @return:  Integer 
	 * @Date :   2019-01-16 21:09:20    
	 */
	public Integer getVesion() {
		return vesion;
	}
	
	/**
	 * <p>版本</p>
	 * @author:  houz
	 * @param:   @param vesion
	 * @return:  void 
	 * @Date :   2019-01-16 21:09:20   
	 */
	public void setVesion(Integer vesion) {
		this.vesion = vesion;
	}
	
	

}

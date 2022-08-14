/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-24 16:33:40 
 */
package hry.ico.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IcoLockReward </p>
 * @author:         lzy
 * @Date :          2019-01-24 16:33:40  
 */
@Table(name="ico_lock_reward")
public class IcoLockReward extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "isFirst")
	private Integer isFirst;  //是否第一次
	
	@Column(name= "countingTime")
	private Long countingTime;  //锁仓结算奖励时间
	
	
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-24 16:33:40    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-24 16:33:40   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-24 16:33:40    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-01-24 16:33:40   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>是否第一次</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-01-24 16:33:40    
	 */
	public Integer getIsFirst() {
		return isFirst;
	}
	
	/**
	 * <p>是否第一次</p>
	 * @author:  lzy
	 * @param:   @param isFirst
	 * @return:  void 
	 * @Date :   2019-01-24 16:33:40   
	 */
	public void setIsFirst(Integer isFirst) {
		this.isFirst = isFirst;
	}
	
	
	/**
	 * <p>锁仓结算奖励时间</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-24 16:33:40    
	 */
	public Long getCountingTime() {
		return countingTime;
	}
	
	/**
	 * <p>锁仓结算奖励时间</p>
	 * @author:  lzy
	 * @param:   @param countingTime
	 * @return:  void 
	 * @Date :   2019-01-24 16:33:40   
	 */
	public void setCountingTime(Long countingTime) {
		this.countingTime = countingTime;
	}
	
	

}

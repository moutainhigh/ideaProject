/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-16 15:20:22 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> RobotConfig </p>
 * @author:         zhouming
 * @Date :          2019-05-16 15:20:22  
 */
@Table(name="shop_robot_config")
public class RobotConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //订单索引id
	
	@Column(name= "medleyTime")
	private Integer medleyTime;  //凑团时间（不足多长时间）
	
	@Column(name= "medleyNum")
	private Integer medleyNum;  //凑团差多少人
	
	
	
	
	/**
	 * <p>订单索引id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-16 15:20:22    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>订单索引id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-16 15:20:22   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>凑团时间（不足多长时间）</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-16 15:20:22    
	 */
	public Integer getMedleyTime() {
		return medleyTime;
	}
	
	/**
	 * <p>凑团时间（不足多长时间）</p>
	 * @author:  zhouming
	 * @param:   @param medleyTime
	 * @return:  void 
	 * @Date :   2019-05-16 15:20:22   
	 */
	public void setMedleyTime(Integer medleyTime) {
		this.medleyTime = medleyTime;
	}
	
	
	/**
	 * <p>凑团差多少人</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-16 15:20:22    
	 */
	public Integer getMedleyNum() {
		return medleyNum;
	}
	
	/**
	 * <p>凑团差多少人</p>
	 * @author:  zhouming
	 * @param:   @param medleyNum
	 * @return:  void 
	 * @Date :   2019-05-16 15:20:22   
	 */
	public void setMedleyNum(Integer medleyNum) {
		this.medleyNum = medleyNum;
	}
	
	

}

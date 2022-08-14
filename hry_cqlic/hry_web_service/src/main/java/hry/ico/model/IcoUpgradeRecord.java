/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-17 10:48:13 
 */
package hry.ico.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> IcoUpgradeRecord </p>
 * @author:         houz
 * @Date :          2019-01-17 10:48:13  
 */
@Table(name="ico_upgrade_record")
public class IcoUpgradeRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customer_id")
	private Long customer_id;  //用户id
	
	@Column(name= "oldLevel_id")
	private Long oldLevel_id;  //
	
	@Column(name= "oldLevel")
	private String oldLevel;  //旧等级名称
	
	@Column(name= "newLevel_id")
	private Long newLevel_id;  //
	
	@Column(name= "newLevel")
	private String newLevel;  //新等级名称
	
	@Column(name= "upgradeNote")
	private String upgradeNote;  //升级备注
	
	@Column(name= "type")
	private String type;  //等级变化类型（1，升级，2 降级）
	
	
	
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-17 10:48:13    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-17 10:48:13   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-17 10:48:13    
	 */
	public Long getCustomer_id() {
		return customer_id;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  houz
	 * @param:   @param customer_id
	 * @return:  void 
	 * @Date :   2019-01-17 10:48:13   
	 */
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-17 10:48:13    
	 */
	public Long getOldLevel_id() {
		return oldLevel_id;
	}
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param oldLevel_id
	 * @return:  void 
	 * @Date :   2019-01-17 10:48:13   
	 */
	public void setOldLevel_id(Long oldLevel_id) {
		this.oldLevel_id = oldLevel_id;
	}
	
	
	/**
	 * <p>旧等级名称</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-17 10:48:13    
	 */
	public String getOldLevel() {
		return oldLevel;
	}
	
	/**
	 * <p>旧等级名称</p>
	 * @author:  houz
	 * @param:   @param oldLevel
	 * @return:  void 
	 * @Date :   2019-01-17 10:48:13   
	 */
	public void setOldLevel(String oldLevel) {
		this.oldLevel = oldLevel;
	}
	
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-17 10:48:13    
	 */
	public Long getNewLevel_id() {
		return newLevel_id;
	}
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param newLevel_id
	 * @return:  void 
	 * @Date :   2019-01-17 10:48:13   
	 */
	public void setNewLevel_id(Long newLevel_id) {
		this.newLevel_id = newLevel_id;
	}
	
	
	/**
	 * <p>新等级名称</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-17 10:48:13    
	 */
	public String getNewLevel() {
		return newLevel;
	}
	
	/**
	 * <p>新等级名称</p>
	 * @author:  houz
	 * @param:   @param newLevel
	 * @return:  void 
	 * @Date :   2019-01-17 10:48:13   
	 */
	public void setNewLevel(String newLevel) {
		this.newLevel = newLevel;
	}
	
	
	/**
	 * <p>升级备注</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-17 10:48:13    
	 */
	public String getUpgradeNote() {
		return upgradeNote;
	}
	
	/**
	 * <p>升级备注</p>
	 * @author:  houz
	 * @param:   @param upgradeNote
	 * @return:  void 
	 * @Date :   2019-01-17 10:48:13   
	 */
	public void setUpgradeNote(String upgradeNote) {
		this.upgradeNote = upgradeNote;
	}
	
	
	/**
	 * <p>等级变化类型（1，升级，2 降级）</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-17 10:48:13    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>等级变化类型（1，升级，2 降级）</p>
	 * @author:  houz
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-01-17 10:48:13   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	

}

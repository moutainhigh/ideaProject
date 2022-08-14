/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:12:38 
 */
package hry.cm5.customer.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm5CustomerLevelRecord </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:12:38  
 */
@Table(name="cm5_customer_level_record")
public class Cm5CustomerLevelRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "oldLevelId")
	private Long oldLevelId;  //旧等级Id
	
	@Column(name= "oldLevelSort")
	private Integer oldLevelSort;  //旧等级级别
	
	@Column(name= "oldLevelName")
	private String oldLevelName;  //旧等级名称
	
	@Column(name= "levelId")
	private Long levelId;  //升级后等级Id
	
	@Column(name= "levelSort")
	private Integer levelSort;  //升级后等级级别
	
	@Column(name= "levelName")
	private String levelName;  //升级后等级名称
	
	@Column(name= "type")
	private Integer type;  //升级原因（1.自动升级 2.手动修改）
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:12:38    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-01-08 14:12:38   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:12:38    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2020-01-08 14:12:38   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>旧等级Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:12:38    
	 */
	public Long getOldLevelId() {
		return oldLevelId;
	}
	
	/**
	 * <p>旧等级Id</p>
	 * @author:  zhouming
	 * @param:   @param oldLevelId
	 * @return:  void 
	 * @Date :   2020-01-08 14:12:38   
	 */
	public void setOldLevelId(Long oldLevelId) {
		this.oldLevelId = oldLevelId;
	}
	
	
	/**
	 * <p>旧等级级别</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-01-08 14:12:38    
	 */
	public Integer getOldLevelSort() {
		return oldLevelSort;
	}
	
	/**
	 * <p>旧等级级别</p>
	 * @author:  zhouming
	 * @param:   @param oldLevelSort
	 * @return:  void 
	 * @Date :   2020-01-08 14:12:38   
	 */
	public void setOldLevelSort(Integer oldLevelSort) {
		this.oldLevelSort = oldLevelSort;
	}
	
	
	/**
	 * <p>旧等级名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:12:38    
	 */
	public String getOldLevelName() {
		return oldLevelName;
	}
	
	/**
	 * <p>旧等级名称</p>
	 * @author:  zhouming
	 * @param:   @param oldLevelName
	 * @return:  void 
	 * @Date :   2020-01-08 14:12:38   
	 */
	public void setOldLevelName(String oldLevelName) {
		this.oldLevelName = oldLevelName;
	}
	
	
	/**
	 * <p>升级后等级Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:12:38    
	 */
	public Long getLevelId() {
		return levelId;
	}
	
	/**
	 * <p>升级后等级Id</p>
	 * @author:  zhouming
	 * @param:   @param levelId
	 * @return:  void 
	 * @Date :   2020-01-08 14:12:38   
	 */
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	
	/**
	 * <p>升级后等级级别</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-01-08 14:12:38    
	 */
	public Integer getLevelSort() {
		return levelSort;
	}
	
	/**
	 * <p>升级后等级级别</p>
	 * @author:  zhouming
	 * @param:   @param levelSort
	 * @return:  void 
	 * @Date :   2020-01-08 14:12:38   
	 */
	public void setLevelSort(Integer levelSort) {
		this.levelSort = levelSort;
	}
	
	
	/**
	 * <p>升级后等级名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:12:38    
	 */
	public String getLevelName() {
		return levelName;
	}
	
	/**
	 * <p>升级后等级名称</p>
	 * @author:  zhouming
	 * @param:   @param levelName
	 * @return:  void 
	 * @Date :   2020-01-08 14:12:38   
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	
	/**
	 * <p>升级原因（1.自动升级 2.手动修改）</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-01-08 14:12:38    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>升级原因（1.自动升级 2.手动修改）</p>
	 * @author:  zhouming
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2020-01-08 14:12:38   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:12:38    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2020-01-08 14:12:38   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:12:38    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-01-08 14:12:38   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

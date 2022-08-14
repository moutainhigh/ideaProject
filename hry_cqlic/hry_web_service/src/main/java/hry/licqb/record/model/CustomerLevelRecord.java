/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-20 13:53:53 
 */
package hry.licqb.record.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p> CustomerLevelRecord </p>
 * @author:         zhouming
 * @Date :          2019-08-20 13:53:53  
 */
@Table(name="lc_customer_level_record")
public class CustomerLevelRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户Id
	
	@Column(name= "oldLevelId")
	private Long oldLevelId;  //旧等级Id
	
	@Column(name= "oldLevelName")
	private String oldLevelName;  //旧等级名称
	
	@Column(name= "oldSort")
	private Integer oldSort;  //旧等级排序
	
	@Column(name= "newLevelId")
	private Long newLevelId;  //新等级Id
	
	@Column(name= "newLevelName")
	private String newLevelName;  //新等级名称
	
	@Column(name= "newSort")
	private Integer newSort;  //新等级排序
	
	@Column(name= "levelType")
	private Integer levelType;  //等级类别 1：个人等级 2：社区等级

	@Column(name= "teamAwardNum")
	private BigDecimal teamAwardNum; // 社区奖励记录

	@Column(name= "saasId")
	private String saasId;  //

	public BigDecimal getTeamAwardNum() {
		return teamAwardNum;
	}

	public void setTeamAwardNum(BigDecimal teamAwardNum) {
		this.teamAwardNum = teamAwardNum;
	}

	public String getOldLevelName() {
		return oldLevelName;
	}

	public void setOldLevelName(String oldLevelName) {
		this.oldLevelName = oldLevelName;
	}

	public String getNewLevelName() {
		return newLevelName;
	}

	public void setNewLevelName(String newLevelName) {
		this.newLevelName = newLevelName;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-20 13:53:53    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-20 13:53:53   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-20 13:53:53    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-08-20 13:53:53   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>旧等级Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-20 13:53:53    
	 */
	public Long getOldLevelId() {
		return oldLevelId;
	}
	
	/**
	 * <p>旧等级Id</p>
	 * @author:  zhouming
	 * @param:   @param oldLevelId
	 * @return:  void 
	 * @Date :   2019-08-20 13:53:53   
	 */
	public void setOldLevelId(Long oldLevelId) {
		this.oldLevelId = oldLevelId;
	}

	/**
	 * <p>旧等级排序</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-20 13:53:53    
	 */
	public Integer getOldSort() {
		return oldSort;
	}
	
	/**
	 * <p>旧等级排序</p>
	 * @author:  zhouming
	 * @param:   @param oldSort
	 * @return:  void 
	 * @Date :   2019-08-20 13:53:53   
	 */
	public void setOldSort(Integer oldSort) {
		this.oldSort = oldSort;
	}
	
	
	/**
	 * <p>新等级Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-20 13:53:53    
	 */
	public Long getNewLevelId() {
		return newLevelId;
	}
	
	/**
	 * <p>新等级Id</p>
	 * @author:  zhouming
	 * @param:   @param newLevelId
	 * @return:  void 
	 * @Date :   2019-08-20 13:53:53   
	 */
	public void setNewLevelId(Long newLevelId) {
		this.newLevelId = newLevelId;
	}
	
	/**
	 * <p>新等级排序</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-20 13:53:53    
	 */
	public Integer getNewSort() {
		return newSort;
	}
	
	/**
	 * <p>新等级排序</p>
	 * @author:  zhouming
	 * @param:   @param newSort
	 * @return:  void 
	 * @Date :   2019-08-20 13:53:53   
	 */
	public void setNewSort(Integer newSort) {
		this.newSort = newSort;
	}
	
	
	/**
	 * <p>等级类别 1：个人等级 2：社区等级</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-20 13:53:53    
	 */
	public Integer getLevelType() {
		return levelType;
	}
	
	/**
	 * <p>等级类别 1：个人等级 2：社区等级</p>
	 * @author:  zhouming
	 * @param:   @param levelType
	 * @return:  void 
	 * @Date :   2019-08-20 13:53:53   
	 */
	public void setLevelType(Integer levelType) {
		this.levelType = levelType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-20 13:53:53    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-08-20 13:53:53   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-05-17 13:37:41 
 */
package hry.klg.level.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> KlgUpgradeRecord </p>
 * @author:         lzy
 * @Date :          2019-05-17 13:37:41  
 */
@Table(name="klg_upgrade_record")
public class KlgUpgradeRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "oldLevelId")
	private Long oldLevelId;  //
	
	@Column(name= "oldLevel")
	private String oldLevel;  //旧等级名称
	
	@Column(name= "newLevelId")
	private Long newLevelId;  //
	
	@Column(name= "newLevel")
	private String newLevel;  //新等级名称
	
	@Column(name= "upgradeNote")
	private String upgradeNote;  //升级备注
	
	@Column(name= "type")
	private String type;  //等级变化类型（1，升级，2 降级）
	
	@Column(name= "oldGradation")
	private BigDecimal oldGradation;  //老的级别差
	
	@Column(name= "oldPointAlgebra")
	private Integer oldPointAlgebra;  //老的见点代数
	
	@Column(name= "nowGradation")
	private BigDecimal nowGradation;  //新的级别差
	
	@Column(name= "nowPointAlgebra")
	private Integer nowPointAlgebra;  //新的见点代数
	
	
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public Long getOldLevelId() {
		return oldLevelId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param oldLevelId
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setOldLevelId(Long oldLevelId) {
		this.oldLevelId = oldLevelId;
	}
	
	
	/**
	 * <p>旧等级名称</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public String getOldLevel() {
		return oldLevel;
	}
	
	/**
	 * <p>旧等级名称</p>
	 * @author:  lzy
	 * @param:   @param oldLevel
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setOldLevel(String oldLevel) {
		this.oldLevel = oldLevel;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public Long getNewLevelId() {
		return newLevelId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param newLevelId
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setNewLevelId(Long newLevelId) {
		this.newLevelId = newLevelId;
	}
	
	
	/**
	 * <p>新等级名称</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public String getNewLevel() {
		return newLevel;
	}
	
	/**
	 * <p>新等级名称</p>
	 * @author:  lzy
	 * @param:   @param newLevel
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setNewLevel(String newLevel) {
		this.newLevel = newLevel;
	}
	
	
	/**
	 * <p>升级备注</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public String getUpgradeNote() {
		return upgradeNote;
	}
	
	/**
	 * <p>升级备注</p>
	 * @author:  lzy
	 * @param:   @param upgradeNote
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setUpgradeNote(String upgradeNote) {
		this.upgradeNote = upgradeNote;
	}
	
	
	/**
	 * <p>等级变化类型（1，升级，2 降级）</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>等级变化类型（1，升级，2 降级）</p>
	 * @author:  lzy
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p>老的级别差</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public BigDecimal getOldGradation() {
		return oldGradation;
	}
	
	/**
	 * <p>老的级别差</p>
	 * @author:  lzy
	 * @param:   @param oldGradation
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setOldGradation(BigDecimal oldGradation) {
		this.oldGradation = oldGradation;
	}
	
	
	/**
	 * <p>老的见点代数</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public Integer getOldPointAlgebra() {
		return oldPointAlgebra;
	}
	
	/**
	 * <p>老的见点代数</p>
	 * @author:  lzy
	 * @param:   @param oldPointAlgebra
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setOldPointAlgebra(Integer oldPointAlgebra) {
		this.oldPointAlgebra = oldPointAlgebra;
	}
	
	
	/**
	 * <p>新的级别差</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public BigDecimal getNowGradation() {
		return nowGradation;
	}
	
	/**
	 * <p>新的级别差</p>
	 * @author:  lzy
	 * @param:   @param nowGradation
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setNowGradation(BigDecimal nowGradation) {
		this.nowGradation = nowGradation;
	}
	
	
	/**
	 * <p>新的见点代数</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-05-17 13:37:41    
	 */
	public Integer getNowPointAlgebra() {
		return nowPointAlgebra;
	}
	
	/**
	 * <p>新的见点代数</p>
	 * @author:  lzy
	 * @param:   @param nowPointAlgebra
	 * @return:  void 
	 * @Date :   2019-05-17 13:37:41   
	 */
	public void setNowPointAlgebra(Integer nowPointAlgebra) {
		this.nowPointAlgebra = nowPointAlgebra;
	}
	
	

}

/**
 * Copyright:   
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-12 14:21:24 
 */
package hry.ico.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> IcoExperience </p>
 * @author:         houz
 * @Date :          2019-01-12 14:21:24  
 */
@Table(name="ico_experience")
public class IcoExperience extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customer_id")
	private Long customer_id;  //账户id
	
	@Column(name= "customer_email")
	private String customer_email;  //账户邮箱
	
	@Column(name= "customer_mobile")
	private String customer_mobile;  //账户手机
	
	@Column(name= "type")
	private String type;  //1收入 ，2 支出
	
	@Column(name= "account_type")
	private String account_type;  //交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除）
	
	@Column(name= "coinNum")
	private BigDecimal coinNum;  //持币数量
	
	@Column(name= "experience")
	private Long experience;  //经验值（无正负）
	
	@Column(name= "experienceNum")
	private String experienceNum;  //流水号
	
	@Column(name= "oldLevel_id")
	private Long oldLevel_id;  //
	
	@Column(name= "oldLevel")
	private String oldLevel;  //原等级
	
	@Column(name= "newLevel_id")
	private Long newLevel_id;  //
	
	@Column(name= "newLevel")
	private String newLevel;  //
	
	@Column(name= "upgradeNote")
	private String upgradeNote;  //升级备注
	
	
	
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>账户id</p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public Long getCustomer_id() {
		return customer_id;
	}
	
	/**
	 * <p>账户id</p>
	 * @author:  houz
	 * @param:   @param customer_id
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	
	
	/**
	 * <p>账户邮箱</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public String getCustomer_email() {
		return customer_email;
	}
	
	/**
	 * <p>账户邮箱</p>
	 * @author:  houz
	 * @param:   @param customer_email
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	
	
	/**
	 * <p>账户手机</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public String getCustomer_mobile() {
		return customer_mobile;
	}
	
	/**
	 * <p>账户手机</p>
	 * @author:  houz
	 * @param:   @param customer_mobile
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setCustomer_mobile(String customer_mobile) {
		this.customer_mobile = customer_mobile;
	}
	
	
	/**
	 * <p>1收入 ，2 支出</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>1收入 ，2 支出</p>
	 * @author:  houz
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p>交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除）</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public String getAccount_type() {
		return account_type;
	}
	
	/**
	 * <p>交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除）</p>
	 * @author:  houz
	 * @param:   @param account_type
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	
	
	/**
	 * <p>持币数量</p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public BigDecimal getCoinNum() {
		return coinNum;
	}
	
	/**
	 * <p>持币数量</p>
	 * @author:  houz
	 * @param:   @param coinNum
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setCoinNum(BigDecimal coinNum) {
		this.coinNum = coinNum;
	}
	
	
	/**
	 * <p>经验值（无正负）</p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public Long getExperience() {
		return experience;
	}
	
	/**
	 * <p>经验值（无正负）</p>
	 * @author:  houz
	 * @param:   @param experience
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setExperience(Long experience) {
		this.experience = experience;
	}
	
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public String getExperienceNum() {
		return experienceNum;
	}
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param experienceNum
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setExperienceNum(String experienceNum) {
		this.experienceNum = experienceNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public Long getOldLevel_id() {
		return oldLevel_id;
	}
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param oldLevl_id
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setOldLevel_id(Long oldLevel_id) {
		this.oldLevel_id = oldLevel_id;
	}
	
	
	/**
	 * <p>原等级</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public String getOldLevel() {
		return oldLevel;
	}
	
	/**
	 * <p>原等级</p>
	 * @author:  houz
	 * @param:   @param oldLevel
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setOldLevel(String oldLevel) {
		this.oldLevel = oldLevel;
	}
	
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  Long 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public Long getNewLevel_id() {
		return newLevel_id;
	}
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param newLevel_id
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setNewLevel_id(Long newLevel_id) {
		this.newLevel_id = newLevel_id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public String getNewLevel() {
		return newLevel;
	}
	
	/**
	 * <p></p>
	 * @author:  houz
	 * @param:   @param newLevel
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setNewLevel(String newLevel) {
		this.newLevel = newLevel;
	}
	
	
	/**
	 * <p>升级备注</p>
	 * @author:  houz
	 * @return:  String 
	 * @Date :   2019-01-12 14:21:24    
	 */
	public String getUpgradeNote() {
		return upgradeNote;
	}
	
	/**
	 * <p>升级备注</p>
	 * @author:  houz
	 * @param:   @param upgradeNote
	 * @return:  void 
	 * @Date :   2019-01-12 14:21:24   
	 */
	public void setUpgradeNote(String upgradeNote) {
		this.upgradeNote = upgradeNote;
	}
	
	

}

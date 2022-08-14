/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-09-25 13:58:51 
 */
package hry.admin.mining.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> MingTimer </p>
 * @author:         sunyujie
 * @Date :          2018-09-25 13:58:51  
 */
@Table(name="app_mining_timer")
public class MingTimer extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;  //
	
	@Column(name= "platformType")
	private String platformType;  //
	
	@Column(name= "platformAvgTimer")
	private String platformAvgTimer;  //
	
	@Column(name= "platformAvgTimerType")
	private String platformAvgTimerType;  //
	
	@Column(name= "platformReturnTimer")
	private String platformReturnTimer;  //
	
	@Column(name= "platformReturnTimerType")
	private String platformReturnTimerType;  //
	
	@Column(name= "platformStartTimer")
	private String platformStartTimer;  //
	
	@Column(name= "bonusRecordTimer")
	private String bonusRecordTimer;  //
	
	@Column(name= "bonusRecordTimerType")
	private String bonusRecordTimerType;  //
	
	@Column(name= "bonusReturnTimer")
	private String bonusReturnTimer;  //
	
	@Column(name= "bonusReturnTimerType")
	private String bonusReturnTimerType;  //
	
	@Column(name= "bonusReturnStart")
	private String bonusReturnStart;  //
	
	@Column(name= "bonusReturnType")
	private String bonusReturnType;  //
	
	@Column(name= "lockfrequency")
	private String lockfrequency;  //
	
	@Column(name= "lockfrequencyType")
	private String lockfrequencyType;  //
	
	@Column(name= "lockfrequencyScale")
	private String lockfrequencyScale;  //
	
	@Column(name= "bonusIfStart")
	private String bonusIfStart;  //
	
	@Column(name= "miningIfStart")
	private String miningIfStart;  //
	
	@Column(name= "unlockIfStart")
	private String unlockIfStart;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getPlatformType() {
		return platformType;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param platformType
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getPlatformAvgTimer() {
		return platformAvgTimer;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param platformAvgTimer
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setPlatformAvgTimer(String platformAvgTimer) {
		this.platformAvgTimer = platformAvgTimer;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getPlatformAvgTimerType() {
		return platformAvgTimerType;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param platformAvgTimerType
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setPlatformAvgTimerType(String platformAvgTimerType) {
		this.platformAvgTimerType = platformAvgTimerType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getPlatformReturnTimer() {
		return platformReturnTimer;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param platformReturnTimer
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setPlatformReturnTimer(String platformReturnTimer) {
		this.platformReturnTimer = platformReturnTimer;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getPlatformReturnTimerType() {
		return platformReturnTimerType;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param platformReturnTimerType
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setPlatformReturnTimerType(String platformReturnTimerType) {
		this.platformReturnTimerType = platformReturnTimerType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getPlatformStartTimer() {
		return platformStartTimer;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param platformStartTimer
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setPlatformStartTimer(String platformStartTimer) {
		this.platformStartTimer = platformStartTimer;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getBonusRecordTimer() {
		return bonusRecordTimer;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param bonusRecordTimer
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setBonusRecordTimer(String bonusRecordTimer) {
		this.bonusRecordTimer = bonusRecordTimer;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getBonusRecordTimerType() {
		return bonusRecordTimerType;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param bonusRecordTimerType
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setBonusRecordTimerType(String bonusRecordTimerType) {
		this.bonusRecordTimerType = bonusRecordTimerType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getBonusReturnTimer() {
		return bonusReturnTimer;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param bonusReturnTimer
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setBonusReturnTimer(String bonusReturnTimer) {
		this.bonusReturnTimer = bonusReturnTimer;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getBonusReturnTimerType() {
		return bonusReturnTimerType;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param bonusReturnTimerType
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setBonusReturnTimerType(String bonusReturnTimerType) {
		this.bonusReturnTimerType = bonusReturnTimerType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getBonusReturnStart() {
		return bonusReturnStart;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param bonusReturnStart
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setBonusReturnStart(String bonusReturnStart) {
		this.bonusReturnStart = bonusReturnStart;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getBonusReturnType() {
		return bonusReturnType;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param bonusReturnType
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setBonusReturnType(String bonusReturnType) {
		this.bonusReturnType = bonusReturnType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getLockfrequency() {
		return lockfrequency;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param lockfrequency
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setLockfrequency(String lockfrequency) {
		this.lockfrequency = lockfrequency;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getLockfrequencyType() {
		return lockfrequencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param lockfrequencyType
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setLockfrequencyType(String lockfrequencyType) {
		this.lockfrequencyType = lockfrequencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getLockfrequencyScale() {
		return lockfrequencyScale;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param lockfrequencyScale
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setLockfrequencyScale(String lockfrequencyScale) {
		this.lockfrequencyScale = lockfrequencyScale;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getBonusIfStart() {
		return bonusIfStart;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param bonusIfStart
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setBonusIfStart(String bonusIfStart) {
		this.bonusIfStart = bonusIfStart;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getMiningIfStart() {
		return miningIfStart;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param miningIfStart
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setMiningIfStart(String miningIfStart) {
		this.miningIfStart = miningIfStart;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-09-25 13:58:51    
	 */
	public String getUnlockIfStart() {
		return unlockIfStart;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param unlockIfStart
	 * @return:  void 
	 * @Date :   2018-09-25 13:58:51   
	 */
	public void setUnlockIfStart(String unlockIfStart) {
		this.unlockIfStart = unlockIfStart;
	}
	
	

}

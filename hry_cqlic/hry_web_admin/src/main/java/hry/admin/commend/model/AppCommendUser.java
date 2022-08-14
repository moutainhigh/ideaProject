/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 20:10:54 
 */
package hry.admin.commend.model;


import hry.admin.customer.model.AppPersonInfo;
import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> AppCommendUser </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 20:10:54  
 */
@Table(name="app_commend_user")
public class AppCommendUser extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "pid")
	private Long pid;  //
	
	@Column(name= "pname")
	private String pname;  //
	
	@Column(name= "uid")
	private Long uid;  //
	
	@Column(name= "username")
	private String username;  //
	
	@Column(name= "receCode")
	private String receCode;  //
	
	@Column(name= "sid")
	private String sid;  //
	
	@Column(name= "maxId")
	private Long maxId;  //
	
	@Column(name= "isFrozen")
	private Integer isFrozen;  //
	
	@Column(name= "aloneProportion")
	private BigDecimal aloneProportion;  //
	
	@Column(name= "oneNumber")
	private Integer oneNumber;  //
	
	@Column(name= "twoNumber")
	private Integer twoNumber;  //
	
	@Column(name= "threeNumber")
	private Integer threeNumber;  //
	
	@Column(name= "laterNumber")
	private Integer laterNumber;  //
	
	@Column(name= "isCulCommend")
	private Integer isCulCommend;  //
	
	@Column(name= "saasId")
	private String saasId;  //


	@Transient
	private AppPersonInfo appPersonInfo;
	
	@Transient
	private String commendCode;  //
	@Transient
	private String levelName;  //级别名称
	@Transient
	private BigDecimal gradation;  //级差
	@Transient
	private BigDecimal rewardNum;  //奖金额度
	@Transient
	private Integer pointAlgebra;  //见点代数
	
	
	
	public String getCommendCode() {
		return commendCode;
	}

	public void setCommendCode(String commendCode) {
		this.commendCode = commendCode;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public BigDecimal getGradation() {
		return gradation;
	}

	public void setGradation(BigDecimal gradation) {
		this.gradation = gradation;
	}

	public BigDecimal getRewardNum() {
		return rewardNum;
	}

	public void setRewardNum(BigDecimal rewardNum) {
		this.rewardNum = rewardNum;
	}

	public Integer getPointAlgebra() {
		return pointAlgebra;
	}

	public void setPointAlgebra(Integer pointAlgebra) {
		this.pointAlgebra = pointAlgebra;
	}

	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public Long getPid() {
		return pid;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param pid
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public String getPname() {
		return pname;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param pname
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public Long getUid() {
		return uid;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param uid
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param username
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public String getReceCode() {
		return receCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param receCode
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setReceCode(String receCode) {
		this.receCode = receCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public String getSid() {
		return sid;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param sid
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public Long getMaxId() {
		return maxId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param maxId
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setMaxId(Long maxId) {
		this.maxId = maxId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public Integer getIsFrozen() {
		return isFrozen;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param isFrozen
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setIsFrozen(Integer isFrozen) {
		this.isFrozen = isFrozen;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public BigDecimal getAloneProportion() {
		return aloneProportion;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param aloneProportion
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setAloneProportion(BigDecimal aloneProportion) {
		this.aloneProportion = aloneProportion;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public Integer getOneNumber() {
		return oneNumber;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param oneNumber
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setOneNumber(Integer oneNumber) {
		this.oneNumber = oneNumber;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public Integer getTwoNumber() {
		return twoNumber;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param twoNumber
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setTwoNumber(Integer twoNumber) {
		this.twoNumber = twoNumber;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public Integer getThreeNumber() {
		return threeNumber;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param threeNumber
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setThreeNumber(Integer threeNumber) {
		this.threeNumber = threeNumber;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public Integer getLaterNumber() {
		return laterNumber;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param laterNumber
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setLaterNumber(Integer laterNumber) {
		this.laterNumber = laterNumber;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public Integer getIsCulCommend() {
		return isCulCommend;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param isCulCommend
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setIsCulCommend(Integer isCulCommend) {
		this.isCulCommend = isCulCommend;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 20:10:54    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-25 20:10:54   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}
}

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 15:30:37 
 */
package hry.customer.commend.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import hry.core.mvc.model.BaseModel;
import hry.customer.person.model.AppPersonInfo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> AppCommendUser </p>
 * @author:         menwei
 * @Date :          2017-11-28 15:30:37  
 */
@Table(name="app_commend_user")
	
public class AppCommendUser extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "username")
	private String username;  //username
	@Column(name= "pid")
	private Long pid;  //直接父级的customerId
	@Column(name= "pname")
	private String pname;  //直接父级的username
	
	@Column(name= "uid")
	private Long uid;  //推荐人的customerId（app_customer）
	
	@Column(name= "sid")
	private String sid;  //父级树
	
	@Column(name= "isFrozen")
	private Integer isFrozen;  //0被冻结，就是不能给上级返佣
	
	
	@Column(name= "receCode")  //自己得注册推荐码
	private String receCode;
	
	@Column(name= "aloneProportion")
	private BigDecimal aloneProportion;//单独奖励比例
	
	@Column(name= "maxId")//父级推荐最高级
	private Long maxId;
	
	@Column(name= "oneNumber") //一级推荐个数
	private Integer oneNumber;
	
	@Column(name= "twoNumber")//二级推荐个数
	private Integer twoNumber;
	
	@Column(name= "threeNumber")//三级推荐个数
	private Integer threeNumber;
	
	@Column(name= "laterNumber")//四级推荐个数
	private Integer laterNumber;
	
	@Column(name= "isCulCommend")//这条注册是否被计算过推荐个数
	private Integer isCulCommend;
	
	@Transient
	private Integer comLeven;
	@Transient
	private BigDecimal commendMoney;
	@Transient
	private BigDecimal onecommendMoney;
	@Transient
	private Integer exorderCount;
	@Transient
	private BigDecimal moneyNum;
	@Transient
	private String coinCode;
	@Transient
	private String Ratetype;
	@Transient
	private BigDecimal userMoney;
	@Transient
	private BigDecimal moneydic;  //平台奖励
	@Transient
	private AppPersonInfo appPersonInfo;

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	public BigDecimal getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
	}

	public BigDecimal getOnecommendMoney() {
		return onecommendMoney;
	}

	public void setOnecommendMoney(BigDecimal onecommendMoney) {
		this.onecommendMoney = onecommendMoney;
	}

	public String getRatetype() {
		return Ratetype;
	}

	public void setRatetype(String ratetype) {
		Ratetype = ratetype;
	}


	public BigDecimal getAloneProportion() {
		return aloneProportion;
	}

	public void setAloneProportion(BigDecimal aloneProportion) {
		this.aloneProportion = aloneProportion;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public BigDecimal getMoneyNum() {
		return moneyNum;
	}

	public void setMoneyNum(BigDecimal moneyNum) {
		this.moneyNum = moneyNum;
	}

	public Integer getExorderCount() {
		return exorderCount;
	}

	public void setExorderCount(Integer exorderCount) {
		this.exorderCount = exorderCount;
	}

	public BigDecimal getCommendMoney() {
		return commendMoney;
	}

	public void setCommendMoney(BigDecimal commendMoney) {
		this.commendMoney = commendMoney;
	}

	public Integer getComLeven() {
		return comLeven;
	}

	public void setComLeven(Integer comLeven) {
		this.comLeven = comLeven;
	}

	public Long getMaxId() {
		return maxId;
	}

	public void setMaxId(Long maxId) {
		this.maxId = maxId;
	}

	public String getReceCode() {
		return receCode;
	}

	public void setReceCode(String receCode) {
		this.receCode = receCode;
	}

	public Integer getIsFrozen() {
		return isFrozen;
	}

	public void setIsFrozen(Integer isFrozen) {
		this.isFrozen = isFrozen;
	}

	public Integer getOneNumber() {
		return oneNumber;
	}

	public void setOneNumber(Integer oneNumber) {
		this.oneNumber = oneNumber;
	}

	public Integer getTwoNumber() {
		return twoNumber;
	}

	public void setTwoNumber(Integer twoNumber) {
		this.twoNumber = twoNumber;
	}

	public Integer getThreeNumber() {
		return threeNumber;
	}

	public void setThreeNumber(Integer threeNumber) {
		this.threeNumber = threeNumber;
	}

	public Integer getLaterNumber() {
		return laterNumber;
	}

	public void setLaterNumber(Integer laterNumber) {
		this.laterNumber = laterNumber;
	}

	
	
	
	


	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public BigDecimal getMoneydic() {
		return moneydic;
	}

	public void setMoneydic(BigDecimal moneydic) {
		this.moneydic = moneydic;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * <p>id</p>
	 * @author:  menwei
	 * @return:  Long 
	 * @Date :   2017-11-28 15:30:37    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  menwei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2017-11-28 15:30:37   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>pid</p>
	 * @author:  menwei
	 * @return:  Long 
	 * @Date :   2017-11-28 15:30:37    
	 */
	public Long getPid() {
		return pid;
	}
	
	/**
	 * <p>pid</p>
	 * @author:  menwei
	 * @param:   @param pid
	 * @return:  void 
	 * @Date :   2017-11-28 15:30:37   
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}
	
	
	/**
	 * <p>username</p>
	 * @author:  menwei
	 * @return:  String 
	 * @Date :   2017-11-28 15:30:37    
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * <p>username</p>
	 * @author:  menwei
	 * @param:   @param username
	 * @return:  void 
	 * @Date :   2017-11-28 15:30:37   
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	/**
	 * <p>pname</p>
	 * @author:  menwei
	 * @return:  String 
	 * @Date :   2017-11-28 15:30:37    
	 */
	public String getPname() {
		return pname;
	}
	
	/**
	 * <p>pname</p>
	 * @author:  menwei
	 * @param:   @param pname
	 * @return:  void 
	 * @Date :   2017-11-28 15:30:37   
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	

}

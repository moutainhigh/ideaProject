/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 15:30:37 
 */
package hry.manage.remote.model;

import hry.bean.BaseModel;

import java.math.BigDecimal;

/**
 * <p> AppCommendUser </p>
 * @author:         menwei
 * @Date :          2017-11-28 15:30:37  
 */
	
public class AppCommendUserManage extends BaseModel {
	private Long id;  //id
	
	private String username;  //username
	private Long pid;  //直接父级的customerId
	private String pname;  //直接父级的username
	private Long uid;  //推荐人的customerId（app_customer）
	
	private String sid;  //父级树
	
	private Integer isFrozen;  //0被冻结，就是不能给上级返佣
	
	
	private String receCode;
	
	private BigDecimal aloneProportion;//单独奖励比例
	
	private Long maxId;
	
	private int oneNumber;
	
	private int twoNumber;
	
	private int threeNumber;
	
	private int laterNumber;
	
	private int isCulCommend;
	
	private Integer comLeven;
	private BigDecimal commendMoney;
	private BigDecimal onecommendMoney;
	private Integer exorderCount;
	private BigDecimal moneyNum;
	private String coinCode;
	private String Ratetype;
	private BigDecimal userMoney;
	private BigDecimal moneydic;  //平台奖励
	private AppPersonInfoManage appPersonInfo;

	public AppPersonInfoManage getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfoManage appPersonInfo) {
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

	public int getOneNumber() {
		return oneNumber;
	}

	public void setOneNumber(int oneNumber) {
		this.oneNumber = oneNumber;
	}

	public int getTwoNumber() {
		return twoNumber;
	}

	public void setTwoNumber(int twoNumber) {
		this.twoNumber = twoNumber;
	}

	public int getThreeNumber() {
		return threeNumber;
	}

	public void setThreeNumber(int threeNumber) {
		this.threeNumber = threeNumber;
	}

	public int getLaterNumber() {
		return laterNumber;
	}

	public void setLaterNumber(int laterNumber) {
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

/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-15 14:34:14 
 */
package hry.ico.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IcoLockRecord </p>
 * @author:         lzy
 * @Date :          2019-01-15 14:34:14  
 */
@Table(name="ico_lock_record")
public class IcoLockRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //币种Code
	
	@Column(name= "type")
	private Integer type;  //是否ICO阶段(1.ico阶段0.非ico)
	
	@Column(name= "number")
	private BigDecimal number;  //数量
	
	@Column(name= "lockDay")
	private Integer lockDay;  //锁仓天数
	
	@Column(name= "state")
	private Integer state;  //状态(0.待释放1.已释放)
	
	@Column(name= "lockDeduct")
	private BigDecimal lockDeduct;  //锁仓扣除
	
	@Column(name= "lockDeductType")
	private Integer lockDeductType;  //锁仓扣除类型（1.经验扣除2.itx扣除）
	
	@Column(name= "releaseDeduct")
	private BigDecimal releaseDeduct;  //释放扣除

	@Column(name= "releaseDeductType")
	private Integer releaseDeductType;  //释放扣除类型（1.经验扣除2.itx扣除）

	@Column(name= "releaseDeductNum")
	private BigDecimal releaseDeductNum;  //释放扣币数量

	@Column(name= "releaseDate")
	private Date releaseDate;  //释放时间

	@Column(name= "currentLockSum")
	private BigDecimal currentLockSum;  //当前锁仓总数

	@Column(name= "releaseType")
	private Integer releaseType;  //释放类型（1为提前释放0正常）

	@Column(name= "actualReleaseNum")
	private BigDecimal actualReleaseNum;  //实际释放数量

	@Column(name= "actualReleaseTime")
	private Date actualReleaseTime;  //实际释放时间


	public BigDecimal getReleaseDeductNum() {
		return releaseDeductNum;
	}

	public void setReleaseDeductNum(BigDecimal releaseDeductNum) {
		this.releaseDeductNum = releaseDeductNum;
	}

	public BigDecimal getActualReleaseNum() {
		return actualReleaseNum;
	}

	public void setActualReleaseNum(BigDecimal actualReleaseNum) {
		this.actualReleaseNum = actualReleaseNum;
	}

	public Date getActualReleaseTime() {
		return actualReleaseTime;
	}

	public void setActualReleaseTime(Date actualReleaseTime) {
		this.actualReleaseTime = actualReleaseTime;
	}

	public BigDecimal getCurrentLockSum() {
		return currentLockSum;
	}

	public void setCurrentLockSum(BigDecimal currentLockSum) {
		this.currentLockSum = currentLockSum;
	}

	public Integer getReleaseType() {
		return releaseType;
	}

	public void setReleaseType(Integer releaseType) {
		this.releaseType = releaseType;
	}

	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  Long 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>币种Code</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种Code</p>
	 * @author:  lzy
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>是否ICO阶段(1.ico阶段0.非ico)</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>是否ICO阶段(1.ico阶段0.非ico)</p>
	 * @author:  lzy
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public BigDecimal getNumber() {
		return number;
	}
	
	/**
	 * <p>数量</p>
	 * @author:  lzy
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setNumber(BigDecimal number) {
		this.number = number;
	}
	
	
	/**
	 * <p>锁仓天数</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public Integer getLockDay() {
		return lockDay;
	}
	
	/**
	 * <p>锁仓天数</p>
	 * @author:  lzy
	 * @param:   @param lockDay
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setLockDay(Integer lockDay) {
		this.lockDay = lockDay;
	}
	
	
	/**
	 * <p>状态(0.待释放101.已释放102提前释放)</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>状态(0.待释放101.已释放102提前释放)</p>
	 * @author:  lzy
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>锁仓扣除</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public BigDecimal getLockDeduct() {
		return lockDeduct;
	}
	
	/**
	 * <p>锁仓扣除</p>
	 * @author:  lzy
	 * @param:   @param lockDeduct
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setLockDeduct(BigDecimal lockDeduct) {
		this.lockDeduct = lockDeduct;
	}
	
	
	/**
	 * <p>锁仓扣除类型（1.经验扣除2.itx扣除）</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public Integer getLockDeductType() {
		return lockDeductType;
	}
	
	/**
	 * <p>锁仓扣除类型（1.经验扣除2.itx扣除）</p>
	 * @author:  lzy
	 * @param:   @param lockDeductType
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setLockDeductType(Integer lockDeductType) {
		this.lockDeductType = lockDeductType;
	}
	
	
	/**
	 * <p>释放扣除</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public BigDecimal getReleaseDeduct() {
		return releaseDeduct;
	}
	
	/**
	 * <p>释放扣除</p>
	 * @author:  lzy
	 * @param:   @param releaseDeduct
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setReleaseDeduct(BigDecimal releaseDeduct) {
		this.releaseDeduct = releaseDeduct;
	}
	
	
	/**
	 * <p>释放扣除类型（1.经验扣除2.itx扣除）</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public Integer getReleaseDeductType() {
		return releaseDeductType;
	}
	
	/**
	 * <p>释放扣除类型（1.经验扣除2.itx扣除）</p>
	 * @author:  lzy
	 * @param:   @param releaseDeductType
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setReleaseDeductType(Integer releaseDeductType) {
		this.releaseDeductType = releaseDeductType;
	}
	
	
	/**
	 * <p>释放时间</p>
	 * @author:  lzy
	 * @return:  Date 
	 * @Date :   2019-01-15 14:34:14    
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	/**
	 * <p>释放时间</p>
	 * @author:  lzy
	 * @param:   @param releaseDate
	 * @return:  void 
	 * @Date :   2019-01-15 14:34:14   
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	

}

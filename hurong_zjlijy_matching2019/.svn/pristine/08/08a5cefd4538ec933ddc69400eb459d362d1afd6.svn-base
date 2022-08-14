/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-31 14:56:41 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> LevelRecord </p>
 * @author:         luyue
 * @Date :          2019-05-31 14:56:41  
 */
@Table(name="shop_level_record")
public class LevelRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "number")
	private String number;  //编号
	
	@Column(name= "memberId")
	private Long memberId;  //用户Id
	
	@Column(name= "oldLevelId")
	private Long oldLevelId;  //旧等级Id
	
	@Column(name= "oldLevelNumber")
	private Integer oldLevelNumber;  //旧等级级别
	
	@Column(name= "levelId")
	private Long levelId;  //升级后等级Id
	
	@Column(name= "levelNumber")
	private Integer levelNumber;  //升级后等级级别
	
	@Column(name= "oldMoney")
	private BigDecimal oldMoney;  //旧等级金额
	
	@Column(name= "promoteMoney")
	private BigDecimal promoteMoney;  //新等级金额
	
	@Column(name= "money")
	private BigDecimal money;  //激活金额
	
	@Column(name= "status")
	private Integer status;  //1成功
	
	@Column(name= "remark")
	private String remark;  //备注

	@Column(name= "startDate")
	private Date startDate;  //开始日期
	@Column(name= "endDate")
	private Date endDate;  //结束日期
	@Column(name= "distributeDate")
	private Date distributeDate;  //分红计划分发时间

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDistributeDate() {
		return distributeDate;
	}

	public void setDistributeDate(Date distributeDate) {
		this.distributeDate = distributeDate;
	}

	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>编号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * <p>编号</p>
	 * @author:  luyue
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>旧等级Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public Long getOldLevelId() {
		return oldLevelId;
	}
	
	/**
	 * <p>旧等级Id</p>
	 * @author:  luyue
	 * @param:   @param oldLevelId
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setOldLevelId(Long oldLevelId) {
		this.oldLevelId = oldLevelId;
	}
	
	
	/**
	 * <p>旧等级级别</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public Integer getOldLevelNumber() {
		return oldLevelNumber;
	}
	
	/**
	 * <p>旧等级级别</p>
	 * @author:  luyue
	 * @param:   @param oldLevelNumber
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setOldLevelNumber(Integer oldLevelNumber) {
		this.oldLevelNumber = oldLevelNumber;
	}
	
	
	/**
	 * <p>升级后等级Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public Long getLevelId() {
		return levelId;
	}
	
	/**
	 * <p>升级后等级Id</p>
	 * @author:  luyue
	 * @param:   @param levelId
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	
	/**
	 * <p>升级后等级级别</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public Integer getLevelNumber() {
		return levelNumber;
	}
	
	/**
	 * <p>升级后等级级别</p>
	 * @author:  luyue
	 * @param:   @param levelNumber
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setLevelNumber(Integer levelNumber) {
		this.levelNumber = levelNumber;
	}
	
	
	/**
	 * <p>旧等级金额</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public BigDecimal getOldMoney() {
		return oldMoney;
	}
	
	/**
	 * <p>旧等级金额</p>
	 * @author:  luyue
	 * @param:   @param oldMoney
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setOldMoney(BigDecimal oldMoney) {
		this.oldMoney = oldMoney;
	}
	
	
	/**
	 * <p>新等级金额</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public BigDecimal getPromoteMoney() {
		return promoteMoney;
	}
	
	/**
	 * <p>新等级金额</p>
	 * @author:  luyue
	 * @param:   @param promoteMoney
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setPromoteMoney(BigDecimal promoteMoney) {
		this.promoteMoney = promoteMoney;
	}
	
	
	/**
	 * <p>激活金额</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public BigDecimal getMoney() {
		return money;
	}
	
	/**
	 * <p>激活金额</p>
	 * @author:  luyue
	 * @param:   @param money
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
	/**
	 * <p>1成功</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>1成功</p>
	 * @author:  luyue
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-31 14:56:41    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-05-31 14:56:41   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

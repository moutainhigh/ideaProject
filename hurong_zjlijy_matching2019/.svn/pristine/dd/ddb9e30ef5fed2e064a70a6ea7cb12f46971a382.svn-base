/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 18:29:25 
 */
package hry.social.manage.remote.model.mill;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> SocialMillInfo </p>
 * @author:         javalx
 * @Date :          2019-06-11 18:29:25  
 */
@Table(name="social_mill_info")
public class SocialMillInfo extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //序号
	
	@Column(name= "name")
	private String name;  //矿机名称
	
	@Column(name= "serialNum")
	private String serialNum;  //矿机型号
	
	@Column(name= "rewardType")
	private Integer rewardType;  //奖励方式 1：奖励算力，2：奖励数币
	
	@Column(name= "rewardNum")
	private BigDecimal rewardNum;  //奖励数量
	
	@Column(name= "validNum")
	private Integer validNum;  //时效数
	
	@Column(name= "timeUnit")
	private Integer timeUnit;  //时效单位 1：天，2：月，3年
	
	@Column(name= "price")
	private BigDecimal price;  //单价（CNY）
	
	@Column(name= "img")
	private String img;  //矿机图标
	
	@Column(name= "millSort")
	private Integer millSort;  //等级排序
	
	@Column(name= "states")
	private Integer states;  //开启状态 0：关闭，1：开启

	@Column(name= "coinCode")
	private String coinCode;  //奖励币种

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 18:29:25    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-11 18:29:25   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>矿机名称</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 18:29:25    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>矿机名称</p>
	 * @author:  javalx
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-06-11 18:29:25   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>矿机型号</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 18:29:25    
	 */
	public String getSerialNum() {
		return serialNum;
	}
	
	/**
	 * <p>矿机型号</p>
	 * @author:  javalx
	 * @param:   @param serialNum
	 * @return:  void 
	 * @Date :   2019-06-11 18:29:25   
	 */
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	
	
	/**
	 * <p>奖励方式 1：奖励算力，2：奖励数币</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 18:29:25    
	 */
	public Integer getRewardType() {
		return rewardType;
	}
	
	/**
	 * <p>奖励方式 1：奖励算力，2：奖励数币</p>
	 * @author:  javalx
	 * @param:   @param rewardType
	 * @return:  void 
	 * @Date :   2019-06-11 18:29:25   
	 */
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}
	
	
	/**
	 * <p>奖励数量</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 18:29:25    
	 */
	public BigDecimal getRewardNum() {
		return rewardNum;
	}
	
	/**
	 * <p>奖励数量</p>
	 * @author:  javalx
	 * @param:   @param rewardNum
	 * @return:  void 
	 * @Date :   2019-06-11 18:29:25   
	 */
	public void setRewardNum(BigDecimal rewardNum) {
		this.rewardNum = rewardNum;
	}
	
	
	/**
	 * <p>时效数</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 18:29:25    
	 */
	public Integer getValidNum() {
		return validNum;
	}
	
	/**
	 * <p>时效数</p>
	 * @author:  javalx
	 * @param:   @param validNum
	 * @return:  void 
	 * @Date :   2019-06-11 18:29:25   
	 */
	public void setValidNum(Integer validNum) {
		this.validNum = validNum;
	}
	
	
	/**
	 * <p>时效单位 1：天，2：月，3年</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 18:29:25    
	 */
	public Integer getTimeUnit() {
		return timeUnit;
	}
	
	/**
	 * <p>时效单位 1：天，2：月，3年</p>
	 * @author:  javalx
	 * @param:   @param timeUnit
	 * @return:  void 
	 * @Date :   2019-06-11 18:29:25   
	 */
	public void setTimeUnit(Integer timeUnit) {
		this.timeUnit = timeUnit;
	}
	
	
	/**
	 * <p>单价（CNY）</p>
	 * @author:  javalx
	 * @return:  BigDecimal 
	 * @Date :   2019-06-11 18:29:25    
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * <p>单价（CNY）</p>
	 * @author:  javalx
	 * @param:   @param price
	 * @return:  void 
	 * @Date :   2019-06-11 18:29:25   
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	/**
	 * <p>矿机图标</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 18:29:25    
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * <p>矿机图标</p>
	 * @author:  javalx
	 * @param:   @param img
	 * @return:  void 
	 * @Date :   2019-06-11 18:29:25   
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	
	/**
	 * <p>等级排序</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 18:29:25    
	 */
	public Integer getMillSort() {
		return millSort;
	}
	
	/**
	 * <p>等级排序</p>
	 * @author:  javalx
	 * @param:   @param millSort
	 * @return:  void 
	 * @Date :   2019-06-11 18:29:25   
	 */
	public void setMillSort(Integer millSort) {
		this.millSort = millSort;
	}
	
	
	/**
	 * <p>开启状态 0：关闭，1：开启</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-11 18:29:25    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p>开启状态 0：关闭，1：开启</p>
	 * @author:  javalx
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2019-06-11 18:29:25   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	

}

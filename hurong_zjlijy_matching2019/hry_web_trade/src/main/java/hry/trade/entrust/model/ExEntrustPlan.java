/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月25日 下午4:09:31
 */
package hry.trade.entrust.model;



import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import hry.core.mvc.model.BaseExModel;
import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年7月5日 下午1:53:28
 */
@SuppressWarnings("serial")
@Table(name = "ex_entrust_plan")
public class ExEntrustPlan extends BaseExModel {
	// 主键
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// TYPE 类型 1 ： 已经成功委托 0： 还没委托3取消
	@Column(name = "isValid")
	private Integer isValid;
	//浮动的价格,可以为负数
	@Column(name = "floatPrice")
	private BigDecimal floatPrice;
	//委托数量
	@Column(name = "entrustCount")
	private BigDecimal entrustCount;
	// TYPE 类型 1 ： 买 2 ： 卖
	@Column(name = "type")
	private Integer type;
	// 委托单号
	@Column(name = "entrustNum")
	private String entrustNum;
	// 委托人
	@Column(name = "customerId")
	private Long customerId;
	@Column(name = "trueName")
	private String trueName;
	@Column(name = "userName")
	private String userName;
	// 委托人的编号
	@Column(name = "userCode")
	private String userCode;
	@Column(name = "customerType")
	private Integer customerType;// 客户类型customerType甲类账户1(普通的，默认)，乙类账号2，丙类账户3
	// 币的代码
	@Column(name = "coinCode")
	private String coinCode;
	
	

	//1,选中的条件0,没被选中的条件
	@Column(name = "ispriceType")
	private Boolean ispriceType;
	// 1,最新价2，委买一3，委卖一
	@Column(name = "priceType")
	private Integer priceType;
	// 1>,  2<,  3=,  4<=  ,5>=
	@Column(name = "priceCompare")
	private Integer priceCompare;
	//价格的值
	@Column(name = "priceValue")
	private BigDecimal priceValue;
	
	
	//1,选中的条件0,没被选中的条件
	@Column(name = "iscommonCountType")
	private Boolean iscommonCountType;
	// 1,甲委买量，2甲委卖量
	@Column(name = "commonCountType")
	private Integer commonCountType;
	// 1>,  2<,  3=,  4<=  ,5>=
	@Column(name = "commonCountCompare")
	private Integer commonCountCompare;
	//值
	@Column(name = "commonCountValue")
	private BigDecimal commonCountValue;
	
	
	//1,选中的条件0,没被选中的条件
	@Column(name = "ismakerCountType")
	private Boolean ismakerCountType;
	// 1,乙委买量，2乙委卖量
	@Column(name = "makerCountType")
	private Integer makerCountType;
	// 1>,  2<,  3=,  4<=  ,5>=
	@Column(name = "makerCountCompare")
	private Integer makerCountCompare;
	//值
	@Column(name = "makerCountValue")
	private BigDecimal makerCountValue;
	
	
	//1,选中的条件0,没被选中的条件
	@Column(name = "iscommonProportionType")
	private Boolean iscommonProportionType;
	// 1,甲委比
	@Column(name = "commonProportionType")
	private Integer commonProportionType;
	// 1>,  2<,  3=,  4<=  ,5>=
	@Column(name = "commonProportionCompare")
	private Integer commonProportionCompare;
	//值
	@Column(name = "commonProportionValue")
	private BigDecimal commonProportionValue;
	

	//1,选中的条件0,没被选中的条件
	@Column(name = "ismakerProportionType")
	private Boolean ismakerProportionType;
	// 1,乙委比
	@Column(name = "makerProportionType")
	private Integer makerProportionType;
	// 1>,  2<,  3=,  4<=  ,5>=
	@Column(name = "makerProportionCompare")
	private Integer makerProportionCompare;
	//值
	@Column(name = "makerProportionValue")
	private BigDecimal makerProportionValue;
	
	//1,选中的条件0,没被选中的条件
	@Column(name = "istimeType")
	private Boolean istimeType;
	//1时间
	@Column(name = "timeType")
	private Integer timeType;
	// 1>,  2<,  3=,  4<=  ,5>=
	@Column(name = "timeCompare")
	private Integer timeCompare;
	//值
	@Column(name = "timeValue")
	private String timeValue;
	
	
	//1 预埋委托，0预埋撤销
	@Column(name = "isPreEntrust")
	private Integer isPreEntrust;
	
	//1 是否选中委托号
	@Column(name = "isEntrustNums")
	private Boolean isEntrustNums;
	//委托号的
	@Column(name = "entrustNums")
	private String entrustNums;
	//所有的卖委托
	@Column(name = "allSellEntrust")
	private Boolean allSellEntrust;
	//所有的买
	@Column(name = "allBuyEntrust")
	private Boolean allBuyEntrust;
	
	//1 是否选中委托量
	@Column(name = "isEntrustCount")
	private Boolean isEntrustCount;
	@Column(name = "entrustCountl")
	private BigDecimal entrustCountl;
	@Column(name = "entrustCountg")
	private BigDecimal entrustCountg;
	//1 是否选择委托价格
	@Column(name = "isEntrustPrice")
	private Boolean isEntrustPrice;
	@Column(name = "entrustPriceg")
	private BigDecimal entrustPriceg;
	@Column(name = "entrustPricel")
	private BigDecimal entrustPricel;
	//拼接的条件
	@Column(name = "pingCondition")
	private String pingCondition;
	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getId() {
		return id;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsValid() {
		return isValid;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getFloatPrice() {
		return floatPrice;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setFloatPrice(BigDecimal floatPrice) {
		this.floatPrice = floatPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getEntrustCount() {
		return entrustCount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setEntrustCount(BigDecimal entrustCount) {
		this.entrustCount = entrustCount;
	}
	
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getCustomerType() {
		return customerType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getType() {
		return type;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getEntrustNum() {
		return entrustNum;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setEntrustNum(String entrustNum) {
		this.entrustNum = entrustNum;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getCustomerId() {
		return customerId;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserCode() {
		return userCode;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCoinCode() {
		return coinCode;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Boolean
	 */
	public Boolean getIspriceType() {
		return ispriceType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Boolean
	 */
	public void setIspriceType(Boolean ispriceType) {
		this.ispriceType = ispriceType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getPriceType() {
		return priceType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getPriceCompare() {
		return priceCompare;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setPriceCompare(Integer priceCompare) {
		this.priceCompare = priceCompare;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getPriceValue() {
		return priceValue;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setPriceValue(BigDecimal priceValue) {
		this.priceValue = priceValue;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Boolean
	 */
	public Boolean getIscommonCountType() {
		return iscommonCountType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Boolean
	 */
	public void setIscommonCountType(Boolean iscommonCountType) {
		this.iscommonCountType = iscommonCountType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getCommonCountType() {
		return commonCountType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setCommonCountType(Integer commonCountType) {
		this.commonCountType = commonCountType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getCommonCountCompare() {
		return commonCountCompare;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setCommonCountCompare(Integer commonCountCompare) {
		this.commonCountCompare = commonCountCompare;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getCommonCountValue() {
		return commonCountValue;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setCommonCountValue(BigDecimal commonCountValue) {
		this.commonCountValue = commonCountValue;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Boolean
	 */
	public Boolean getIsmakerCountType() {
		return ismakerCountType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Boolean
	 */
	public void setIsmakerCountType(Boolean ismakerCountType) {
		this.ismakerCountType = ismakerCountType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getMakerCountType() {
		return makerCountType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setMakerCountType(Integer makerCountType) {
		this.makerCountType = makerCountType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getMakerCountCompare() {
		return makerCountCompare;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setMakerCountCompare(Integer makerCountCompare) {
		this.makerCountCompare = makerCountCompare;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getMakerCountValue() {
		return makerCountValue;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setMakerCountValue(BigDecimal makerCountValue) {
		this.makerCountValue = makerCountValue;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Boolean
	 */
	public Boolean getIscommonProportionType() {
		return iscommonProportionType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Boolean
	 */
	public void setIscommonProportionType(Boolean iscommonProportionType) {
		this.iscommonProportionType = iscommonProportionType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getCommonProportionType() {
		return commonProportionType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setCommonProportionType(Integer commonProportionType) {
		this.commonProportionType = commonProportionType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getCommonProportionCompare() {
		return commonProportionCompare;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setCommonProportionCompare(Integer commonProportionCompare) {
		this.commonProportionCompare = commonProportionCompare;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getCommonProportionValue() {
		return commonProportionValue;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setCommonProportionValue(BigDecimal commonProportionValue) {
		this.commonProportionValue = commonProportionValue;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Boolean
	 */
	public Boolean getIsmakerProportionType() {
		return ismakerProportionType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Boolean
	 */
	public void setIsmakerProportionType(Boolean ismakerProportionType) {
		this.ismakerProportionType = ismakerProportionType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getMakerProportionType() {
		return makerProportionType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setMakerProportionType(Integer makerProportionType) {
		this.makerProportionType = makerProportionType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getMakerProportionCompare() {
		return makerProportionCompare;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setMakerProportionCompare(Integer makerProportionCompare) {
		this.makerProportionCompare = makerProportionCompare;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getMakerProportionValue() {
		return makerProportionValue;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setMakerProportionValue(BigDecimal makerProportionValue) {
		this.makerProportionValue = makerProportionValue;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Boolean
	 */
	public Boolean getIstimeType() {
		return istimeType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Boolean
	 */
	public void setIstimeType(Boolean istimeType) {
		this.istimeType = istimeType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getTimeType() {
		return timeType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getTimeCompare() {
		return timeCompare;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setTimeCompare(Integer timeCompare) {
		this.timeCompare = timeCompare;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTimeValue() {
		return timeValue;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTimeValue(String timeValue) {
		this.timeValue = timeValue;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsPreEntrust() {
		return isPreEntrust;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsPreEntrust(Integer isPreEntrust) {
		this.isPreEntrust = isPreEntrust;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Boolean
	 */
	public Boolean getIsEntrustNums() {
		return isEntrustNums;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Boolean
	 */
	public void setIsEntrustNums(Boolean isEntrustNums) {
		this.isEntrustNums = isEntrustNums;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getEntrustNums() {
		return entrustNums;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setEntrustNums(String entrustNums) {
		this.entrustNums = entrustNums;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Boolean
	 */
	public Boolean getAllSellEntrust() {
		return allSellEntrust;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Boolean
	 */
	public void setAllSellEntrust(Boolean allSellEntrust) {
		this.allSellEntrust = allSellEntrust;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Boolean
	 */
	public Boolean getAllBuyEntrust() {
		return allBuyEntrust;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Boolean
	 */
	public void setAllBuyEntrust(Boolean allBuyEntrust) {
		this.allBuyEntrust = allBuyEntrust;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Boolean
	 */
	public Boolean getIsEntrustCount() {
		return isEntrustCount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Boolean
	 */
	public void setIsEntrustCount(Boolean isEntrustCount) {
		this.isEntrustCount = isEntrustCount;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getEntrustCountl() {
		return entrustCountl;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setEntrustCountl(BigDecimal entrustCountl) {
		this.entrustCountl = entrustCountl;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getEntrustCountg() {
		return entrustCountg;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setEntrustCountg(BigDecimal entrustCountg) {
		this.entrustCountg = entrustCountg;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Boolean
	 */
	public Boolean getIsEntrustPrice() {
		return isEntrustPrice;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Boolean
	 */
	public void setIsEntrustPrice(Boolean isEntrustPrice) {
		this.isEntrustPrice = isEntrustPrice;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getEntrustPriceg() {
		return entrustPriceg;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setEntrustPriceg(BigDecimal entrustPriceg) {
		this.entrustPriceg = entrustPriceg;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getEntrustPricel() {
		return entrustPricel;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setEntrustPricel(BigDecimal entrustPricel) {
		this.entrustPricel = entrustPricel;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getPingCondition() {
		return pingCondition;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setPingCondition(String pingCondition) {
		this.pingCondition = pingCondition;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTrueName() {
		return trueName;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserName() {
		return userName;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
				
	
	
} 

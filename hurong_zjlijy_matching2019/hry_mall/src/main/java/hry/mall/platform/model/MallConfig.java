/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2018-12-17 16:50:31 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;


import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> MallConfig </p>
 * @author:         kongdb
 * @Date :          2018-12-17 16:50:31  
 */
@Table(name="shop_mallconfig")
public class MallConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "hotSearch")
	private String hotSearch;  //热门搜索关键字
	
	@Column(name= "returnEvidenceCount")
	private Long returnEvidenceCount;  //退货上传图片凭证：
	
	@Column(name= "isEvaluate")
	private Integer isEvaluate;  //商品评价是否需要审核，1是0否
	
	@Column(name= "orderTime")
	private Integer orderTime;  // 正常订单超过：单位分
	
	@Column(name= "goodsConfirm")
	private Integer goodsConfirm;  // 收货确认超时：单位天
	
	@Column(name= "orderFinish")
	private Integer orderFinish;  // 自动结束交易，不能申请售后：单位天
	
	@Column(name= "orderEvaluate")
	private Integer orderEvaluate;  // 订单完成超过，五星好评 ：单位天
	
	@Column(name= "isGoodsConfirm")
	private Integer isGoodsConfirm;  //是否开启自动确认收货，1是0否

	@Column(name= "isBuyRate")
	private Integer isBuyRate;  //'退货计算数币比率 1 购买比率 ，2 当前比率',
	
	@Column(name= "isInvoice")
	private Integer isInvoice;  //能否开发票，1是0否
	
	@Column(name= "invoiceContent")
	private String invoiceContent;  //发票内容

	@Column(name= "packageMail")
	private String packageMail;  //订单满多少元包邮

	@Column(name= "advertiseCoinCode")
	private String advertiseCoinCode;  //商城广告奖励币种

	@Column(name= "coinCode")
	private String coinCode;  //电子券支持币种

	@Column(name = "discountRate")
	private BigDecimal discountRate;  //电子券兑换折扣

	@Column(name= "startDate")
	private String startDate;  //电子券兑换开始时间

	@Column(name= "endDate")
	private String endDate;  //电子券兑换结束时间

	@Transient
	private Boolean isChangeTime; //是否电子券兑换时间

	@Column(name = "withdrawalFee")
	private BigDecimal withdrawalFee;  //电子券提现手续费

	@Column(name = "singleMinWithdrawalAmount")
	private BigDecimal singleMinWithdrawalAmount;  //单次最小提现金额

	@Column(name = "oneDayMaxWithdrawalAmount")
	private BigDecimal oneDayMaxWithdrawalAmount;  //单日最大提现金额


	@Column(name = "singleMinTransferAmount")
	private BigDecimal singleMinTransferAmount;  //单次最小转让额度

	@Column(name = "oneDayMaxTransferAmount")
	private BigDecimal oneDayMaxTransferAmount;  //单日最大转让额度


	public BigDecimal getSingleMinTransferAmount() {
		return singleMinTransferAmount;
	}

	public void setSingleMinTransferAmount(BigDecimal singleMinTransferAmount) {
		this.singleMinTransferAmount = singleMinTransferAmount;
	}

	public BigDecimal getOneDayMaxTransferAmount() {
		return oneDayMaxTransferAmount;
	}

	public void setOneDayMaxTransferAmount(BigDecimal oneDayMaxTransferAmount) {
		this.oneDayMaxTransferAmount = oneDayMaxTransferAmount;
	}

	public BigDecimal getSingleMinWithdrawalAmount() {
		return singleMinWithdrawalAmount;
	}

	public void setSingleMinWithdrawalAmount(BigDecimal singleMinWithdrawalAmount) {
		this.singleMinWithdrawalAmount = singleMinWithdrawalAmount;
	}

	public BigDecimal getOneDayMaxWithdrawalAmount() {
		return oneDayMaxWithdrawalAmount;
	}

	public void setOneDayMaxWithdrawalAmount(BigDecimal oneDayMaxWithdrawalAmount) {
		this.oneDayMaxWithdrawalAmount = oneDayMaxWithdrawalAmount;
	}

	public BigDecimal getWithdrawalFee() {
		return withdrawalFee;
	}

	public void setWithdrawalFee(BigDecimal withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
	}

	public Boolean getIsChangeTime() {
		return isChangeTime;
	}

	public void setIsChangeTime(Boolean isChangeTime) {
		this.isChangeTime = isChangeTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}




	public String getAdvertiseCoinCode() {
		return advertiseCoinCode;
	}

	public void setAdvertiseCoinCode(String advertiseCoinCode) {
		this.advertiseCoinCode = advertiseCoinCode;
	}

	public String getPackageMail() {
		return packageMail;
	}

	public void setPackageMail(String packageMail) {
		this.packageMail = packageMail;
	}

	/**
	 * <p>主键id</p>
	 * @author:  kongdb
	 * @return:  Long 
	 * @Date :   2018-12-17 16:50:31    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  kongdb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-17 16:50:31   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>热门搜索关键字</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2018-12-17 16:50:31    
	 */
	public String getHotSearch() {
		return hotSearch;
	}
	
	/**
	 * <p>热门搜索关键字</p>
	 * @author:  kongdb
	 * @param:   @param hotSearch
	 * @return:  void 
	 * @Date :   2018-12-17 16:50:31   
	 */
	public void setHotSearch(String hotSearch) {
		this.hotSearch = hotSearch;
	}
	
	
	/**
	 * <p>退货上传图片凭证：</p>
	 * @author:  kongdb
	 * @return:  Long 
	 * @Date :   2018-12-17 16:50:31    
	 */
	public Long getReturnEvidenceCount() {
		return returnEvidenceCount;
	}
	
	/**
	 * <p>退货上传图片凭证：</p>
	 * @author:  kongdb
	 * @param:   @param provinceId
	 * @return:  void 
	 * @Date :   2018-12-17 16:50:31   
	 */
	public void setReturnEvidenceCount(Long returnEvidenceCount) {
		this.returnEvidenceCount = returnEvidenceCount;
	}
	
	
	/**
	 * <p>商品评价是否需要审核，1是0否</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2018-12-17 16:50:31    
	 */
	public Integer getIsEvaluate() {
		return isEvaluate;
	}
	
	/**
	 * <p>商品评价是否需要审核，1是0否</p>
	 * @author:  kongdb
	 * @param:   @param isEvaluate
	 * @return:  void 
	 * @Date :   2018-12-17 16:50:31   
	 */
	public void setIsEvaluate(Integer isEvaluate) {
		this.isEvaluate = isEvaluate;
	}
	
	
	/**
	 * <p> 正常订单超过：单位分</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2018-12-17 16:50:31    
	 */
	public Integer getOrderTime() {
		return orderTime;
	}
	
	/**
	 * <p> 正常订单超过：单位分</p>
	 * @author:  kongdb
	 * @param:   @param orderTime
	 * @return:  void 
	 * @Date :   2018-12-17 16:50:31   
	 */
	public void setOrderTime(Integer orderTime) {
		this.orderTime = orderTime;
	}
	
	
	/**
	 * <p> 收货确认超时：单位天</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2018-12-17 16:50:31    
	 */
	public Integer getGoodsConfirm() {
		return goodsConfirm;
	}
	
	/**
	 * <p> 收货确认超时：单位天</p>
	 * @author:  kongdb
	 * @param:   @param goodsConfirm
	 * @return:  void 
	 * @Date :   2018-12-17 16:50:31   
	 */
	public void setGoodsConfirm(Integer goodsConfirm) {
		this.goodsConfirm = goodsConfirm;
	}
	
	
	/**
	 * <p> 自动结束交易，不能申请售后：单位天</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2018-12-17 16:50:31    
	 */
	public Integer getOrderFinish() {
		return orderFinish;
	}
	
	/**
	 * <p> 自动结束交易，不能申请售后：单位天</p>
	 * @author:  kongdb
	 * @param:   @param orderFinish
	 * @return:  void 
	 * @Date :   2018-12-17 16:50:31   
	 */
	public void setOrderFinish(Integer orderFinish) {
		this.orderFinish = orderFinish;
	}
	
	
	/**
	 * <p> 订单完成超过，五星好评 ：单位天</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2018-12-17 16:50:31    
	 */
	public Integer getOrderEvaluate() {
		return orderEvaluate;
	}
	
	/**
	 * <p> 订单完成超过，五星好评 ：单位天</p>
	 * @author:  kongdb
	 * @param:   @param orderEvaluate
	 * @return:  void 
	 * @Date :   2018-12-17 16:50:31   
	 */
	public void setOrderEvaluate(Integer orderEvaluate) {
		this.orderEvaluate = orderEvaluate;
	}
	
	
	/**
	 * <p>是否开启自动确认收货，1是0否</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2018-12-17 16:50:31    
	 */
	public Integer getIsGoodsConfirm() {
		return isGoodsConfirm;
	}
	
	/**
	 * <p>是否开启自动确认收货，1是0否</p>
	 * @author:  kongdb
	 * @param:   @param isGoodsConfirm
	 * @return:  void 
	 * @Date :   2018-12-17 16:50:31   
	 */
	public void setIsGoodsConfirm(Integer isGoodsConfirm) {
		this.isGoodsConfirm = isGoodsConfirm;
	}
	
	
	/**
	 * <p>能否开发票，1是0否</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2018-12-17 16:50:31    
	 */
	public Integer getIsInvoice() {
		return isInvoice;
	}
	
	/**
	 * <p>能否开发票，1是0否</p>
	 * @author:  kongdb
	 * @param:   @param isInvoice
	 * @return:  void 
	 * @Date :   2018-12-17 16:50:31   
	 */
	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}

	public Integer getIsBuyRate() {
		return isBuyRate;
	}

	public void setIsBuyRate(Integer isBuyRate) {
		this.isBuyRate = isBuyRate;
	}

	/**
	 * <p>发票内容</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2018-12-17 16:50:31    
	 */
	public String getInvoiceContent() {
		return invoiceContent;
	}
	
	/**
	 * <p>发票内容</p>
	 * @author:  kongdb
	 * @param:   @param invoiceContent
	 * @return:  void 
	 * @Date :   2018-12-17 16:50:31   
	 */
	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}
	
	

}

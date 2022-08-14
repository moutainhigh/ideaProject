/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 15:44:36 
 */
package hry.admin.exchange.model;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import hry.bean.BaseModel;

/**
 * <p> ExProduct </p>
 * @author:         liushilei
 * @Date :          2018-06-12 15:44:36  
 */
@Table(name="ex_product")
public class ExProduct extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "name")
	private String name;  //
	
	@Column(name= "totalNum")
	private Long totalNum;  //

	@Column(name= "issueTotalMoney")
	private BigDecimal issueTotalMoney;  //

	@Column(name= "issuePrice")
	private BigDecimal issuePrice;  //

	@Column(name= "openTibi")
	private String openTibi;  //

	@Column(name= "issueTime")
	private Date issueTime;  //

	@Column(name= "issueState")
	private Integer issueState;  //

	@Column(name= "coinCode")
	private String coinCode;  //

	@Column(name= "issueId")
	private Long issueId;  //

	@Column(name= "issueName")
	private String issueName;  //

	@Column(name= "splitMinCoin")
	private BigDecimal splitMinCoin;  //

	@Column(name= "stock")
	private Long stock;  //

	@Column(name= "sort")
	private Integer sort;  //

	@Column(name= "pamState")
	private Integer pamState;  //

	@Column(name= "circulation")
	private BigDecimal circulation;  //

	@Column(name= "openBell")
	private Integer openBell;  //

	@Column(name= "open_c2c")
	private Integer open_c2c;  //

	@Column(name= "c2cBuyPrice")
	private BigDecimal c2cBuyPrice;  //

	@Column(name= "c2cSellPrice")
	private BigDecimal c2cSellPrice;  //

	@Column(name= "proveMode")
	private String proveMode;  //

	@Column(name= "productReferral")
	private String productReferral;  //

	@Column(name= "arithmetic")
	private String arithmetic;  //

	@Column(name= "blockspeed")
	private String blockspeed;  //

	@Column(name= "minBlockSize")
	private String minBlockSize;  //

	@Column(name= "maxBlockSize")
	private String maxBlockSize;  //

	@Column(name= "walletDownload")
	private String walletDownload;  //

	@Column(name= "soundDownload")
	private String soundDownload;  //

	@Column(name= "blockBrowser")
	private String blockBrowser;  //

	@Column(name= "officialWebsite")
	private String officialWebsite;  //

	@Column(name= "officialForum")
	private String officialForum;  //

	@Column(name= "miningAddress")
	private String miningAddress;  //

	@Column(name= "isRecommend")
	private Integer isRecommend;  //

	@Column(name= "openingTime")
	private String openingTime;  //

	@Column(name= "closeTime")
	private String closeTime;  //

	@Column(name= "openAndclosePlateTime")
	private String openAndclosePlateTime;  //

	@Column(name= "transactionType")
	private Integer transactionType;  //

	@Column(name= "picturePath")
	private String picturePath;  //

	@Column(name= "prepaidFeeRate")
	private BigDecimal prepaidFeeRate;  //

	@Column(name= "paceFeeRate")
	private BigDecimal paceFeeRate;  //

	@Column(name= "oneTimePaceNum")
	private BigDecimal oneTimePaceNum;  //

	@Column(name= "leastPaceNum")
	private BigDecimal leastPaceNum;  //

	@Column(name= "oneDayPaceNum")
	private BigDecimal oneDayPaceNum;  //

	@Column(name= "keepDecimalForCoin")
	private Integer keepDecimalForCoin;  //

	@Column(name= "giveCoin")
	private BigDecimal giveCoin;  //

	@Column(name= "commendCoin")
	private BigDecimal commendCoin;  //

	@Column(name= "paceType")
	private String paceType;  //

	@Column(name= "paceCurrecy")
	private String paceCurrecy;

	@Column(name= "operator") //操作人
	private String operator;

	@Column(name= "addCoinType") //上币类型 //内部， 外部
	private String addCoinType;

	@Column(name= "myprecision") //精确度
	private String precision;

	@Column(name= "contractAddress")
	private String contractAddress;  //合约地址

	@Column(name= "isERC20")
	private String isERC20;  // 是否是erc20代币

	//币种全称
	@Column(name= "allName")
	private String allName;  //全称

	@Column(name= "crowdfundingPrice")
	private String crowdfundingPrice;  //众筹价格
	// 白皮书
	@Column(name= "writeBook")
	private String writeBook;  //

	@Column(name= "keepDecimalForCurrency")
	private Integer keepDecimalForCurrency;  //钱的保留几位小数

	@Column(name= "coinPercent", columnDefinition= "varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '币价格百分比'")
	private String coinPercent;

	//--------------------------OTC-----------------------------
	@Column(name = "eatFee", columnDefinition = "decimal(20,10) DEFAULT '0.0000000000' COMMENT '吃单交易手续费'")
	private BigDecimal eatFee;

	@Column(name = "eatFeeType", columnDefinition = "int(2) DEFAULT NULL COMMENT '吃单交易手续费类型 0固定费率 1百分比费率'")
	private Integer eatFeeType;

	@Column(name = "putFee", columnDefinition = "decimal(20,10) DEFAULT '0.0000000000' COMMENT '挂单交易手续费'")
	private BigDecimal putFee;

	@Column(name = "putFeeType", columnDefinition = "int(2) DEFAULT NULL COMMENT '挂单手续费类型 0固定费率 1百分比费率'")
	private Integer putFeeType;

	@Column(name = "tradeType", columnDefinition = "int(2) DEFAULT NULL COMMENT '交易手续费类型 0固定费率 1百分比费率'")
	private Integer tradeType;
	
	@Column(name = "otcState", columnDefinition = "int(2) DEFAULT 0 COMMENT '是否开启 0否 1是'")
	private Integer otcState;
	
	@Column(name = "otcCoinCode", columnDefinition = "int(2) DEFAULT 0 COMMENT '是否为OTC字段 0否 1是'")
	private Integer otcCoinCode;

	/**
	 * otc广告价格的最低百分比
	 */
	@Column(name= "otcMinPercent", columnDefinition= "varchar(50)  COLLATE utf8_bin DEFAULT NULL COMMENT 'otc广告价格的最低百分比'")
	private String otcMinPercent;

	/**
	 * otc广告价格的最高百分比
	 */
	@Column(name= "otcMaxPercent", columnDefinition= "varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'otc广告价格的最高百分比'")
	private String otcMaxPercent;
	
	
	public Integer getOtcCoinCode() {
		return otcCoinCode;
	}

	public void setOtcCoinCode(Integer otcCoinCode) {
		this.otcCoinCode = otcCoinCode;
	}

	public String getCoinPercent() {
		return coinPercent;
	}

	public void setCoinPercent(String coinPercent) {
		this.coinPercent = coinPercent;
	}

	public BigDecimal getEatFee() {
		return eatFee;
	}

	public void setEatFee(BigDecimal eatFee) {
		this.eatFee = eatFee;
	}

	public Integer getEatFeeType() {
		return eatFeeType;
	}

	public void setEatFeeType(Integer eatFeeType) {
		this.eatFeeType = eatFeeType;
	}

	public BigDecimal getPutFee() {
		return putFee;
	}

	public void setPutFee(BigDecimal putFee) {
		this.putFee = putFee;
	}

	public Integer getPutFeeType() {
		return putFeeType;
	}

	public void setPutFeeType(Integer putFeeType) {
		this.putFeeType = putFeeType;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Integer getOtcState() {
		return otcState;
	}

	public void setOtcState(Integer otcState) {
		this.otcState = otcState;
	}

	public String getOtcMinPercent() {
		return otcMinPercent;
	}

	public void setOtcMinPercent(String otcMinPercent) {
		this.otcMinPercent = otcMinPercent;
	}

	public String getOtcMaxPercent() {
		return otcMaxPercent;
	}

	public void setOtcMaxPercent(String otcMaxPercent) {
		this.otcMaxPercent = otcMaxPercent;
	}

	public String getCrowdfundingPrice() {
		return crowdfundingPrice;
	}

	public void setCrowdfundingPrice(String crowdfundingPrice) {
		this.crowdfundingPrice = crowdfundingPrice;
	}

	public String getWriteBook() {
		return writeBook;
	}

	public void setWriteBook(String writeBook) {
		this.writeBook = writeBook;
	}

	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	public Integer getKeepDecimalForCurrency() {
		return keepDecimalForCurrency;
	}

	public void setKeepDecimalForCurrency(Integer keepDecimalForCurrency) {
		this.keepDecimalForCurrency = keepDecimalForCurrency;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAddCoinType() {
		return addCoinType;
	}

	public void setAddCoinType(String addCoinType) {
		this.addCoinType = addCoinType;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getIsERC20() {
		return isERC20;
	}

	public void setIsERC20(String isERC20) {
		this.isERC20 = isERC20;
	}

	/*@Column(name= "buyFeeRate")
        private BigDecimal buyFeeRate;  //

        @Column(name= "sellFeeRate")
        private BigDecimal sellFeeRate;  //

        @Column(name= "buyMinMoney")
        private BigDecimal buyMinMoney;  //

        @Column(name= "sellMinCoin")
        private BigDecimal sellMinCoin;  //

        @Column(name= "saasId")
        private String saasId;  //

        @Column(name= "closePlateTime")
        private String closePlateTime;  //

        @Column(name= "priceLimits")
        private BigDecimal priceLimits;  //



        @Column(name= "decline")
        private BigDecimal decline;  //

        @Column(name= "rose")
        private BigDecimal rose;  //

        @Column(name= "averagePrice")
        private BigDecimal averagePrice;  //

        @Column(name= "oneTimeOrderNum")
        private BigDecimal oneTimeOrderNum;  //

        @Column(name= "orderNo")
        private Integer orderNo;  //

        @Column(name= "isOpenCheck")
        private Integer isOpenCheck;  //
    */
	@Transient
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name= "openChongbi")
	private String openChongbi;  //是否可充币

	public String getOpenChongbi() {
		return openChongbi;
	}

	public void setOpenChongbi(String openChongbi) {
		this.openChongbi = openChongbi;
	}

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getIssueTotalMoney() {
		return issueTotalMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param issueTotalMoney
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setIssueTotalMoney(BigDecimal issueTotalMoney) {
		this.issueTotalMoney = issueTotalMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Long getTotalNum() {
		return totalNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param totalNum
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getIssuePrice() {
		return issuePrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param issuePrice
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setIssuePrice(BigDecimal issuePrice) {
		this.issuePrice = issuePrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Date getIssueTime() {
		return issueTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param issueTime
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Integer getIssueState() {
		return issueState;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param issueState
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setIssueState(Integer issueState) {
		this.issueState = issueState;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getSplitMinCoin() {
		return splitMinCoin;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param splitMinCoin
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setSplitMinCoin(BigDecimal splitMinCoin) {
		this.splitMinCoin = splitMinCoin;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Long getIssueId() {
		return issueId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param issueId
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getIssueName() {
		return issueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param issueName
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Long getStock() {
		return stock;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param stock
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setStock(Long stock) {
		this.stock = stock;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getArithmetic() {
		return arithmetic;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param arithmetic
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setArithmetic(String arithmetic) {
		this.arithmetic = arithmetic;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getProveMode() {
		return proveMode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param proveMode
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setProveMode(String proveMode) {
		this.proveMode = proveMode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getProductReferral() {
		return productReferral;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param productReferral
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setProductReferral(String productReferral) {
		this.productReferral = productReferral;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Integer getPamState() {
		return pamState;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param pamState
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setPamState(Integer pamState) {
		this.pamState = pamState;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getBlockspeed() {
		return blockspeed;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param blockspeed
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setBlockspeed(String blockspeed) {
		this.blockspeed = blockspeed;
	}

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getMinBlockSize() {
		return minBlockSize;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param minBlockSize
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setMinBlockSize(String minBlockSize) {
		this.minBlockSize = minBlockSize;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getMaxBlockSize() {
		return maxBlockSize;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param maxBlockSize
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setMaxBlockSize(String maxBlockSize) {
		this.maxBlockSize = maxBlockSize;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getWalletDownload() {
		return walletDownload;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param walletDownload
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setWalletDownload(String walletDownload) {
		this.walletDownload = walletDownload;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getSoundDownload() {
		return soundDownload;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param soundDownload
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setSoundDownload(String soundDownload) {
		this.soundDownload = soundDownload;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getBlockBrowser() {
		return blockBrowser;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param blockBrowser
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setBlockBrowser(String blockBrowser) {
		this.blockBrowser = blockBrowser;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getOfficialWebsite() {
		return officialWebsite;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param officialWebsite
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setOfficialWebsite(String officialWebsite) {
		this.officialWebsite = officialWebsite;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getOfficialForum() {
		return officialForum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param officialForum
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setOfficialForum(String officialForum) {
		this.officialForum = officialForum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getMiningAddress() {
		return miningAddress;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param miningAddress
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setMiningAddress(String miningAddress) {
		this.miningAddress = miningAddress;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Integer getIsRecommend() {
		return isRecommend;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isRecommend
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getOpeningTime() {
		return openingTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param openingTime
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getCloseTime() {
		return closeTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param closeTime
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getPicturePath() {
		return picturePath;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param picturePath
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getOpenAndclosePlateTime() {
		return openAndclosePlateTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param openAndclosePlateTime
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setOpenAndclosePlateTime(String openAndclosePlateTime) {
		this.openAndclosePlateTime = openAndclosePlateTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Integer getTransactionType() {
		return transactionType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionType
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getPrepaidFeeRate() {
		return prepaidFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param prepaidFeeRate
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setPrepaidFeeRate(BigDecimal prepaidFeeRate) {
		this.prepaidFeeRate = prepaidFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getPaceFeeRate() {
		return paceFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param paceFeeRate
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setPaceFeeRate(BigDecimal paceFeeRate) {
		this.paceFeeRate = paceFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getOneTimePaceNum() {
		return oneTimePaceNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param oneTimePaceNum
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setOneTimePaceNum(BigDecimal oneTimePaceNum) {
		this.oneTimePaceNum = oneTimePaceNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getOneDayPaceNum() {
		return oneDayPaceNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param oneDayPaceNum
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setOneDayPaceNum(BigDecimal oneDayPaceNum) {
		this.oneDayPaceNum = oneDayPaceNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getLeastPaceNum() {
		return leastPaceNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param leastPaceNum
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setLeastPaceNum(BigDecimal leastPaceNum) {
		this.leastPaceNum = leastPaceNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getCirculation() {
		return circulation;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param circulation
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setCirculation(BigDecimal circulation) {
		this.circulation = circulation;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Integer getKeepDecimalForCoin() {
		return keepDecimalForCoin;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param keepDecimalForCoin
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setKeepDecimalForCoin(Integer keepDecimalForCoin) {
		this.keepDecimalForCoin = keepDecimalForCoin;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Integer getOpenBell() {
		return openBell;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param openBell
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setOpenBell(Integer openBell) {
		this.openBell = openBell;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getGiveCoin() {
		return giveCoin;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param giveCoin
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setGiveCoin(BigDecimal giveCoin) {
		this.giveCoin = giveCoin;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getPaceCurrecy() {
		return paceCurrecy;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param paceCurrecy
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setPaceCurrecy(String paceCurrecy) {
		this.paceCurrecy = paceCurrecy;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getPaceType() {
		return paceType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param paceType
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setPaceType(String paceType) {
		this.paceType = paceType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getC2cBuyPrice() {
		return c2cBuyPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param c2cBuyPrice
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setC2cBuyPrice(BigDecimal c2cBuyPrice) {
		this.c2cBuyPrice = c2cBuyPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getC2cSellPrice() {
		return c2cSellPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param c2cSellPrice
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setC2cSellPrice(BigDecimal c2cSellPrice) {
		this.c2cSellPrice = c2cSellPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public Integer getOpen_c2c() {
		return open_c2c;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param open_c2c
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setOpen_c2c(Integer open_c2c) {
		this.open_c2c = open_c2c;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public String getOpenTibi() {
		return openTibi;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param openTibi
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setOpenTibi(String openTibi) {
		this.openTibi = openTibi;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:44:36    
	 */
	public BigDecimal getCommendCoin() {
		return commendCoin;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param commendCoin
	 * @return:  void 
	 * @Date :   2018-06-12 15:44:36   
	 */
	public void setCommendCoin(BigDecimal commendCoin) {
		this.commendCoin = commendCoin;
	}
	
}

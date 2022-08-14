/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0
 * @Date:      2015年11月06日  14:57:13
 */
package hry.exchange.product.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.util.HtmlUtils;

/**
 *
 * <p>
 * TODO
 * </p>
 *
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午1:32:55
 */
/**
 * @author hurongyun
 *
 */
@SuppressWarnings("serial")
@Table(name = "ex_product")
public class ExProduct extends BaseModel {

	// Id 唯一
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	// 产品的名称
	@Column(name = "name")
	private String name;

	// 发行的总数量
	@Column(name = "totalNum")
	private Long totalNum;

	// 发行时的总价值
	@Column(name = "issueTotalMoney")
	private BigDecimal issueTotalMoney;

	// 发行时的单价
	@Column(name = "issuePrice")
	private BigDecimal issuePrice;

	@Column(name = "openTibi")
	private  String openTibi;//是否开启提币


	// 发行的时间
	@Column(name = "issueTime")
	private Date issueTime;

	// 产品的状态 0 ： 准备状态
	// 1 ： 正在发行
	// 2 ： 停牌
	// 3 ： 退市'z
	@Column(name = "issueState")
	private Integer issueState;

	// 币的代码 (股票的代码) 不能为空
	@Column(name = "coinCode")
	private String coinCode;

	// 发行方id
	@Column(name = "issueId")
	private Long issueId;

	// 发行方名称
	@Column(name = "issueName")
	private String issueName;


	// 最小拆分的数量
	@Column(name = "splitMinCoin")
	private BigDecimal splitMinCoin;

	// 产品的存量
	@Column(name = "stock")
	private Long stock;

	// 产品的排序字段
	@Column(name = "sort")
	private Integer sort;


	// 产品参数状态
	@Column(name = "pamState")
	private Integer pamState;

	// 融资手续费率
	@Column(name = "circulation")
	private BigDecimal circulation;

	//  '1表示开市   2表示闭市'
	@Column(name = "openBell")
	private Integer openBell;

	//是否开通c2c交易
	@Column(name = "open_c2c")
	private  Integer open_c2c;//默认0不开通，1开通c2c


	@Column(name = "c2cBuyPrice")
	private  BigDecimal c2cBuyPrice;//c2c买价格

	@Column(name = "c2cSellPrice")
	private  BigDecimal c2cSellPrice;//c2c卖价格

	@Column(name= "openChongbi")
	private String openChongbi;  //是否可充币

	public String getOpenChongbi() {
		return openChongbi;
	}

	public void setOpenChongbi(String openChongbi) {
		this.openChongbi = openChongbi;
	}

	public BigDecimal getC2cBuyPrice() {
		return c2cBuyPrice;
	}

	public void setC2cBuyPrice(BigDecimal c2cBuyPrice) {
		this.c2cBuyPrice = c2cBuyPrice;
	}

	public BigDecimal getC2cSellPrice() {
		return c2cSellPrice;
	}

	public void setC2cSellPrice(BigDecimal c2cSellPrice) {
		this.c2cSellPrice = c2cSellPrice;
	}

	@Transient
	private String language;





	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public BigDecimal getCirculation() {
		return circulation;
	}



	public String getOpenTibi() {
		return openTibi;
	}

	public void setOpenTibi(String openTibi) {
		this.openTibi = openTibi;
	}

	public void setCirculation(BigDecimal circulation) {
		this.circulation = circulation;
	}

	// 证明方式
	@Column(name = "proveMode")
	private String proveMode;
	// 产品介绍
	@Column(name = "productReferral")
	private String productReferral;
	// 算法说明
	@Column(name = "arithmetic")
	private String arithmetic;  //
	// 区块的速(多少时间产生一个区块)
	@Column(name = "blockspeed")
	private String blockspeed;
	// 区块大小(最小值)
	@Column(name = "minBlocksize")
	private String minBlocksize;
	// 区块大小(最大值)
	@Column(name = "maxBlocksize")
	private String maxBlocksize;
	// 钱包下载地址
	@Column(name = "walletDownload")
	private String walletDownload;
	// 源码下载地址
	@Column(name = "soundDownload")
	private String soundDownload;
	// 区块浏览器
	@Column(name = "blockBrowser")
	private String blockBrowser;
	// 官方网站
	@Column(name = "officialWebsite")
	private String officialWebsite;
	// 官方论坛
	@Column(name = "officialForum")
	private String officialForum;
	// 挖矿地址
	@Column(name = "miningAddress")
	private String miningAddress;

	// 是否置顶显示
	@Column(name = "isRecommend")
	private Integer isRecommend;

	//开市时间格式 00:00:00
	@Column(name = "openingTime")
	private String openingTime;

	//闭市市时间格式 00:00:00，
	@Column(name = "closeTime")
	private String closeTime;
	//(开盘，闭盘)时间格式 09:00:00,12:00:00,14:00:00,17:00:00;
	@Column(name = "openAndclosePlateTime")
	private String openAndclosePlateTime;
	//1竞价（也就是正常的）2，坐市
	@Column(name = "transactionType")
	private Integer transactionType;


	//图片路径
	@Column(name = "picturePath")
	private String picturePath;

	// 充值手续费
	@Column(name = "prepaidFeeRate")
	private BigDecimal prepaidFeeRate;

	// 提现手续费
	@Column(name = "paceFeeRate")
	private BigDecimal paceFeeRate;

	// 每次提币的最大数量
	@Column(name = "oneTimePaceNum")
	private BigDecimal oneTimePaceNum;

	// 每次提币的最小数量
	@Column(name = "leastPaceNum")
	private BigDecimal leastPaceNum;

	// 每天提币的最大数量
	@Column(name = "oneDayPaceNum")
	private BigDecimal oneDayPaceNum;

	@Column(name = "keepDecimalForCoin")
	private Integer keepDecimalForCoin;
/*	// 币的保留几位小数
	@Column(name = "keepDecimalForCoin")
	private Integer keepDecimalForCoin;
	
	// 钱的保留几位小数
	@Column(name = "keepDecimalForCurrency")
	private Integer keepDecimalForCurrency;*/

	//送币数量
	@Column(name = "giveCoin")
	private BigDecimal giveCoin;

	//邀请送币
	@Column(name = "commendCoin")
	private BigDecimal commendCoin;


	//提币类型
	@Column(name = "paceType")
	private String paceType;  //1固定，  2手动配置



	//提币手续费
	@Column(name = "paceCurrecy")
	private String paceCurrecy;







		public String getPaceCurrecy() {
		return paceCurrecy;
	}



	public void setPaceCurrecy(String paceCurrecy) {
		this.paceCurrecy = paceCurrecy;
	}



		public String getPaceType() {
		return paceType;
	}



	public void setPaceType(String paceType) {
		this.paceType = paceType;
	}



		public BigDecimal getGiveCoin() {
		return giveCoin;
	}



	public void setGiveCoin(BigDecimal giveCoin) {
		this.giveCoin = giveCoin;
	}



		/**
		 * <p> TODO</p>
		 * @return:     BigDecimal
		 */
		public BigDecimal getPrepaidFeeRate() {
			return prepaidFeeRate;
		}



		public Integer getKeepDecimalForCoin() {
			return keepDecimalForCoin;
		}



		public void setKeepDecimalForCoin(Integer keepDecimalForCoin) {
			this.keepDecimalForCoin = keepDecimalForCoin;
		}



		/**
		 * <p> TODO</p>
		 * @return:     BigDecimal
		 */
		public BigDecimal getPaceFeeRate() {
			return paceFeeRate;
		}



		/**
		 * <p> TODO</p>
		 * @return:     BigDecimal
		 */
		public BigDecimal getOneTimePaceNum() {
			return oneTimePaceNum;
		}







		public Integer getOpenBell() {
			return openBell;
		}



		public void setOpenBell(Integer openBell) {
			this.openBell = openBell;
		}



		/**
		 * <p> TODO</p>
		 * @return:     BigDecimal
		 */
		public BigDecimal getOneDayPaceNum() {
			return oneDayPaceNum;
		}



		/**
		 * <p> TODO</p>
		 * @return:     BigDecimal
		 */
		public BigDecimal getLeastPaceNum() {
			return leastPaceNum;
		}



		/**
		 * <p> TODO</p>
		 * @return: BigDecimal
		 */
		public void setPrepaidFeeRate(BigDecimal prepaidFeeRate) {
			this.prepaidFeeRate = prepaidFeeRate;
		}



		/**
		 * <p> TODO</p>
		 * @return: BigDecimal
		 */
		public void setPaceFeeRate(BigDecimal paceFeeRate) {
			this.paceFeeRate = paceFeeRate;
		}



		/**
		 * <p> TODO</p>
		 * @return: BigDecimal
		 */
		public void setOneTimePaceNum(BigDecimal oneTimePaceNum) {
			this.oneTimePaceNum = oneTimePaceNum;
		}



		/**
		 * <p> TODO</p>
		 * @return: BigDecimal
		 */
		public void setOneDayPaceNum(BigDecimal oneDayPaceNum) {
			this.oneDayPaceNum = oneDayPaceNum;
		}


		/**
		 * <p> TODO</p>
		 * @return: BigDecimal
		 */
		public void setLeastPaceNum(BigDecimal leastPaceNum) {
			this.leastPaceNum = leastPaceNum;
		}




	public String getPicturePath() {
		return picturePath;
	}



	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getTransactionType() {
		return transactionType;
	}



	/**
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}



	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOpeningTime() {
		return openingTime;
	}



	/**
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCloseTime() {
		return closeTime;
	}



	/**
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOpenAndclosePlateTime() {
		return openAndclosePlateTime;
	}



	/**
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOpenAndclosePlateTime(String openAndclosePlateTime) {
		this.openAndclosePlateTime = openAndclosePlateTime;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public BigDecimal getIssueTotalMoney() {
		return issueTotalMoney;
	}

	public String getProductReferral() {
		return HtmlUtils.htmlUnescape(productReferral);
	}

	public void setProductReferral(String productReferral) {
		this.productReferral = productReferral;
	}

	public String getProveMode() {
		return HtmlUtils.htmlUnescape(proveMode);
	}

	public void setProveMode(String proveMode) {
		this.proveMode = proveMode;
	}

	public void setIssueTotalMoney(BigDecimal issueTotalMoney) {
		this.issueTotalMoney = issueTotalMoney;
	}

	public BigDecimal getIssuePrice() {
		return issuePrice;
	}

	public void setIssuePrice(BigDecimal issuePrice) {
		this.issuePrice = issuePrice;
	}

	public Date getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}

	public Integer getIssueState() {
		return issueState;
	}

	public void setIssueState(Integer issueState) {
		this.issueState = issueState;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public Long getIssueId() {
		return issueId;
	}

	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}



	public BigDecimal getSplitMinCoin() {
		return splitMinCoin;
	}

	public void setSplitMinCoin(BigDecimal splitMinCoin) {
		this.splitMinCoin = splitMinCoin;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getArithmetic() {
		return HtmlUtils.htmlUnescape(arithmetic);
	}

	public void setArithmetic(String arithmetic) {
		this.arithmetic = arithmetic;
	}

	public String getWalletDownload() {
		return walletDownload;
	}

	public void setWalletDownload(String walletDownload) {
		this.walletDownload = walletDownload;
	}

	public String getSoundDownload() {
		return soundDownload;
	}

	public void setSoundDownload(String soundDownload) {
		this.soundDownload = soundDownload;
	}

	public String getBlockBrowser() {
		return blockBrowser;
	}

	public void setBlockBrowser(String blockBrowser) {
		this.blockBrowser = blockBrowser;
	}

	public String getOfficialWebsite() {
		return officialWebsite;
	}

	public void setOfficialWebsite(String officialWebsite) {
		this.officialWebsite = officialWebsite;
	}

	public String getOfficialForum() {
		return officialForum;
	}

	public void setOfficialForum(String officialForum) {
		this.officialForum = officialForum;
	}

	public String getMiningAddress() {
		return miningAddress;
	}

	public void setMiningAddress(String miningAddress) {
		this.miningAddress = miningAddress;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getPamState() {
		return pamState;
	}

	public void setPamState(Integer pamState) {
		this.pamState = pamState;
	}



	public String getBlockspeed() {
		return blockspeed;
	}



	public void setBlockspeed(String blockspeed) {
		this.blockspeed = blockspeed;
	}



	public String getMinBlocksize() {
		return minBlocksize;
	}



	public void setMinBlocksize(String minBlocksize) {
		this.minBlocksize = minBlocksize;
	}



	public String getMaxBlocksize() {
		return maxBlocksize;
	}



	public void setMaxBlocksize(String maxBlocksize) {
		this.maxBlocksize = maxBlocksize;
	}



	public Integer getOpen_c2c() {
		return open_c2c;
	}



	public void setOpen_c2c(Integer open_c2c) {
		this.open_c2c = open_c2c;
	}

	public BigDecimal getCommendCoin() {
		return commendCoin;
	}

	public void setCommendCoin(BigDecimal commendCoin) {
		this.commendCoin = commendCoin;
	}







}

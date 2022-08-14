/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2015年11月06日  14:57:13
 */
package hry.manage.remote.model;

import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月24日 下午1:32:55
 */
public class ExProductManage extends BaseModel {

	// Id 唯一
	private Long id;

	// 产品的名称
	private String name;

	// 发行的总数量
	private Long totalNum;

	// 发行时的总价值
	private BigDecimal issueTotalMoney;

	// 发行时的单价
	private BigDecimal issuePrice;

	// 发行的时间
	private Date issueTime;

	// 产品的状态 0 ： 准备状态
	// 1 ： 正在发行
	// 2 ： 停牌
	// 3 ： 退市'z
	private int issueState;

	// 币的代码 (股票的代码) 不能为空
	private String coinCode;

	// 发行方id
	private Long issueId;

	// 发行方名称
	private String issueName;
	// 买方手续费 率
	private BigDecimal buyFeeRate;

	// 卖方手续费 率
	private BigDecimal sellFeeRate;

	// 买方 的成交 金额
	private BigDecimal buyMinMoney;

	// 卖方 最小的成交 数量
	private BigDecimal sellMinCoin;
	
	// 最小拆分的数量
	private BigDecimal splitMinCoin;
	
	// 产品的存量
	private Long stock;
	
	// 产品的排序字段
	private Integer sort;
	
	// 价格限制
	private BigDecimal priceLimits;
	
	// 产品参数状态
	private Integer pamState;
	
	// 融资手续费率
	private BigDecimal circulation;
	
	//  '1表示开市   2表示闭市'
	private Integer openBell;
	
	
	
	public BigDecimal getCirculation() {
		return circulation;
	}



	public void setCirculation(BigDecimal circulation) {
		this.circulation = circulation;
	}

	// 证明方式
	private String proveMode;
	// 产品介绍
	private String productReferral;
	// 算法说明
	private String arithmetic;  // 
	// 区块的速(多少时间产生一个区块)
	private String blockspeed;
	// 区块大小(最小值)
	private String minBlocksize;
	// 区块大小(最大值)
	private String maxBlocksize;
	// 钱包下载地址
	private String walletDownload;
	// 源码下载地址
	private String soundDownload;
	// 区块浏览器
	private String blockBrowser;
	// 官方网站
	private String officialWebsite;
	// 官方论坛
	private String officialForum;
	// 挖矿地址
	private String miningAddress;
	
	// 是否置顶显示
	private Integer isRecommend;
	
	//开市时间格式 00:00:00
	private String openingTime; 
	
	//闭市市时间格式 00:00:00，
	private String closeTime;
	//(开盘，闭盘)时间格式 09:00:00,12:00:00,14:00:00,17:00:00;
	private String openAndclosePlateTime;
	//1竞价（也就是正常的）2，坐市
	private Integer transactionType;


	//图片路径
	private String picturePath; 
	
	// 充值手续费
	private BigDecimal prepaidFeeRate;
	
	// 提现手续费
	private BigDecimal paceFeeRate;
	
	// 每次提现的最大数量
	private BigDecimal oneTimePaceNum;
	
	// 币的保留几位小数
	private Integer keepDecimalForCoin;
	
	// 钱的保留几位小数
	private Integer keepDecimalForCurrency;
	
	//涨幅
	private BigDecimal rose;
	
	//跌幅
	private BigDecimal decline;
	
	//均价（收盘价）
	private BigDecimal averagePrice;
		
	
	// 每次下单的最大数量(20170207币银网添加)
	private BigDecimal oneTimeOrderNum;
	
	
	
	private String paceCurrcey;
	
	
	
	
		public String getPaceCurrcey() {
		return paceCurrcey;
	}



	public void setPaceCurrcey(String paceCurrcey) {
		this.paceCurrcey = paceCurrcey;
	}



		public BigDecimal getOneTimeOrderNum() {
		return oneTimeOrderNum;
	}



	public void setOneTimeOrderNum(BigDecimal oneTimeOrderNum) {
		this.oneTimeOrderNum = oneTimeOrderNum;
	}



		public Integer getKeepDecimalForCoin() {
			return keepDecimalForCoin;
		}



		public void setKeepDecimalForCoin(Integer keepDecimalForCoin) {
			this.keepDecimalForCoin = keepDecimalForCoin;
		}



		public Integer getKeepDecimalForCurrency() {
			return keepDecimalForCurrency;
		}



		public void setKeepDecimalForCurrency(Integer keepDecimalForCurrency) {
			this.keepDecimalForCurrency = keepDecimalForCurrency;
		}



		/**
		 * <p> TODO</p>
		 * @return:     BigDecimal
		 */
		public BigDecimal getPrepaidFeeRate() {
			return prepaidFeeRate;
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

		

		public BigDecimal getPriceLimits() {
			return priceLimits;
		}



		public void setPriceLimits(BigDecimal priceLimits) {
			this.priceLimits = priceLimits;
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

		// 每天提现的最大数量
		private BigDecimal oneDayPaceNum;
		
		// 提现的最小金额
		private BigDecimal leastPaceNum;
	
	
	
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


	public void setProductReferral(String productReferral) {
		this.productReferral = productReferral;
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

	public int getIssueState() {
		return issueState;
	}

	public void setIssueState(int issueState) {
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

	public BigDecimal getBuyFeeRate() {
		return buyFeeRate;
	}

	public void setBuyFeeRate(BigDecimal buyFeeRate) {
		this.buyFeeRate = buyFeeRate;
	}

	public BigDecimal getSellFeeRate() {
		return sellFeeRate;
	}

	public void setSellFeeRate(BigDecimal sellFeeRate) {
		this.sellFeeRate = sellFeeRate;
	}

	public BigDecimal getBuyMinMoney() {
		return buyMinMoney;
	}

	public void setBuyMinMoney(BigDecimal buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
	}

	public BigDecimal getSellMinCoin() {
		return sellMinCoin;
	}

	public void setSellMinCoin(BigDecimal sellMinCoin) {
		this.sellMinCoin = sellMinCoin;
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



	public BigDecimal getRose() {
		return rose;
	}



	public void setRose(BigDecimal rose) {
		this.rose = rose;
	}



	public BigDecimal getDecline() {
		return decline;
	}



	public void setDecline(BigDecimal decline) {
		this.decline = decline;
	}



	public BigDecimal getAveragePrice() {
		return averagePrice;
	}



	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

}

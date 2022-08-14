/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:44:41 
 */
package hry.mall.goods.model;


import hry.bean.BaseModel;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> Goods </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:44:41  
 */
@Table(name="shop_goods")
public class Goods extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //商品索引id
	
	@Column(name= "gcId")
	private Long gcId;  //商品分类id
	
	@Column(name= "gcName")
	private String gcName;  //商品分类名称
	
	@Column(name= "goodsName")
	private String goodsName;  //商品名称
	
	@Column(name= "goodsSubtitle")
	private String goodsSubtitle;  //商品副标题
	
	@Column(name= "brandId")
	private Long brandId;  //商品品牌id
	
	@Column(name= "brandName")
	private String brandName;  //商品品牌名称
	
	@Column(name= "goodsImageMore")
	private String goodsImageMore;  //商品多图（第一个为主图）
	
	@Column(name= "goodsVideo")
	private String goodsVideo;  //商品视频路径
	
	@Column(name= "goodsShow")
	private Integer goodsShow;  //商品是否上架：1已上架; 2已下架; 3：放入仓库
	
	@Column(name= "goodsIsComplete")
	private Integer goodsIsComplete;  //商品是否添加完整 0：未完成 1：已完成
	
	@Column(name= "goodsStaus")
	private Integer goodsStaus;  //商品添加步骤 1:第一步（基本信息）2:第二步（详细信息）3:完成
	
	@Column(name= "isAudit")
	private Integer isAudit;  //商品是否审核 1:待审核 2:审核通过 3:审核拒绝

	@Column(name= "refusalCause")
	private String refusalCause; //审核拒绝原因

	@Column(name= "goodsClick")
	private Integer goodsClick;  //商品浏览数
	
	@Column(name= "saleNum")
	private Integer saleNum;  //初始销售数量
	
	@Column(name= "goodsBodyPc")
	private String goodsBodyPc;  //商品详细内容(pc端)
	
	@Column(name= "goodsBodyApp")
	private String goodsBodyApp;  //商品详细内容(移动端)
	
	@Column(name= "goodsKeywords")
	private String goodsKeywords;  //商品关键字
	
	@Column(name= "transportId")
	private Long transportId;  //运费模板ID，不使用运费模板值为0
	
	@Column(name= "transportName")
	private String transportName;  //运费模板名称，不使用运费模板值为0
	
	@Column(name= "goodsWoCodeUrl")
	private String goodsWoCodeUrl;  //商品二维码路径
	
	@Column(name= "commentNum")
	private Integer commentNum;  //评论次数
	
	@Column(name= "isDel")
	private Integer isDel;  //是否删除 0:未删除  1:已删除

	@Column(name= "goodsCommend")
	private Integer goodsCommend;//商品推荐 1：推荐 0 不推荐

	@Column(name= "goodsSpec")
	private String goodsSpec;//说选商品规格集

	@Column(name= "specialType")
	private Short specialType; //商品的类别，1普通商品，2积分商品 3新人专享, 4进阶商品,7会员商品，8创新商品

	@Column(name= "isGroup")
	private Integer isGroup; // 是否是团购商品 0非团购 1是团购

	@Column(name= "isVie")
	private Integer isVie; // 是否是抢购商品 0非抢购 1是抢购

	@Column(name= "rebateType")
	private Integer rebateType; // 返佣类型 1：跟随系统配置 2：独立返佣

	@Column(name= "rebatePrice")
	private BigDecimal rebatePrice; // 独立返佣价格

	@Column(name= "rebateCoin")
	private String rebateCoin; // 返佣币种

	@Column(name= "isCochain")
	private Integer isCochain; // 是否上链

	@Column(name= "remark")
	private String remark; //备注

	@Column(name= "merchantId")
	private Long merchantId; // 商户Id

	@Column(name= "merchantName")
	private String merchantName; // 商户名称

	@Column(name= "isAdvance")
	private Integer isAdvance; // 是否是预售商品 0 否 1是

	@Transient
	private BigDecimal specGoodsPrice; //规格商品价格（注：此价格为规格中最低价格）

	@Transient
	private Integer specGoodsStorage;//商品库存（注：此价格为规格中最低价格的库存）

	@Transient
	private Integer specStorageWarning; //库存预警值

	@Transient
	private String goodsImg;//单图片

	@Transient
	private Long specId;//规格商品Id

	@Transient
	private String specName;//规格种类json {"1":"长宽","2":"种类"}

	@Transient
	private String specNameValue;//规格值json {"3":"1.8*2.0","4":"单人床"}
	
	@Transient
	private String  isFavorite;//是否收藏，1是0否
	
	@Transient
	private BigDecimal  integralCount;//积分兑换数量
	
	@Transient
	private String integralCode;//积分兑换币的代码


	/***--新添商品活动字段--****/
	@Transient
	private Long activityId; // 活动Id
	@Transient
	private Long activityGoodsId; // 活动商品Id
	@Transient
	private BigDecimal activityPrice;// 活动价格
	@Transient
	private Integer peopleCount; // 成团人数
	@Transient
	private Integer activityType; // 活动类型 1： 团购 2：抢购 0:普通商品
	@Transient
	private Date activitySTime;// 活动开始时间
	@Transient
	private Date activityETime;// 活动结束时间
	@Transient
	private String startTime; // // 抢购活动中 时段 开始时间
	@Transient
	private String endTime; // 抢购活动中 时段 结束时间

	/***--用户店铺商品相关字段--****/
	@Transient
	private Integer storeGoodsShow; //店铺商品是否上架：0(不显示上架下架按钮) 1已上架; 2已下架

	public Integer getIsAdvance() {
		return isAdvance;
	}

	public void setIsAdvance(Integer isAdvance) {
		this.isAdvance = isAdvance;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStoreGoodsShow() {
		return storeGoodsShow;
	}

	public void setStoreGoodsShow(Integer storeGoodsShow) {
		this.storeGoodsShow = storeGoodsShow;
	}

	public String getIntegralCode() {
		return integralCode;
	}

	public void setIntegralCode(String integralCode) {
		this.integralCode = integralCode;
	}

	public BigDecimal getIntegralCount() {
		return integralCount;
	}

	public void setIntegralCount(BigDecimal integralCount) {
		this.integralCount = integralCount;
	}

	public Short getSpecialType() {
		return specialType;
	}

	public void setSpecialType(Short specialType) {
		this.specialType = specialType;
	}

	public String getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(String isFavorite) {
		this.isFavorite = isFavorite;
	}

	public String getGoodsSpec() {
		return goodsSpec;
	}

	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}

	public Long getSpecId() {
		return specId;
	}

	public Integer getGoodsCommend() {
		return goodsCommend;
	}

	public void setGoodsCommend(Integer goodsCommend) {
		this.goodsCommend = goodsCommend;
	}

	public void setSpecId(Long specId) {
		this.specId = specId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSpecNameValue() {
		return specNameValue;
	}

	public void setSpecNameValue(String specNameValue) {
		this.specNameValue = specNameValue;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public BigDecimal getSpecGoodsPrice() {
		return specGoodsPrice;
	}

	public void setSpecGoodsPrice(BigDecimal specGoodsPrice) {
		this.specGoodsPrice = specGoodsPrice;
	}

	public Integer getSpecGoodsStorage() {
		return specGoodsStorage;
	}

	public void setSpecGoodsStorage(Integer specGoodsStorage) {
		this.specGoodsStorage = specGoodsStorage;
	}

	public Integer getSpecStorageWarning() {
		return specStorageWarning;
	}

	public void setSpecStorageWarning(Integer specStorageWarning) {
		this.specStorageWarning = specStorageWarning;
	}


	public String getRebateCoin() {
		return rebateCoin;
	}

	public void setRebateCoin(String rebateCoin) {
		this.rebateCoin = rebateCoin;
	}

	public Integer getIsCochain() {
		return isCochain;
	}

	public void setIsCochain(Integer isCochain) {
		this.isCochain = isCochain;
	}

	/**
	 * <p>商品索引id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>商品索引id</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>商品分类id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Long getGcId() {
		return gcId;
	}
	
	/**
	 * <p>商品分类id</p>
	 * @author:  kongdebiao
	 * @param:   @param gcId
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGcId(Long gcId) {
		this.gcId = gcId;
	}
	
	
	/**
	 * <p>商品分类名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public String getGcName() {
		return gcName;
	}
	
	/**
	 * <p>商品分类名称</p>
	 * @author:  kongdebiao
	 * @param:   @param gcName
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGcName(String gcName) {
		this.gcName = gcName;
	}
	
	
	/**
	 * <p>商品名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * <p>商品名称</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsName
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
	/**
	 * <p>商品副标题</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public String getGoodsSubtitle() {
		return goodsSubtitle;
	}
	
	/**
	 * <p>商品副标题</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsSubtitle
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsSubtitle(String goodsSubtitle) {
		this.goodsSubtitle = goodsSubtitle;
	}
	
	
	/**
	 * <p>商品品牌id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Long getBrandId() {
		return brandId;
	}
	
	/**
	 * <p>商品品牌id</p>
	 * @author:  kongdebiao
	 * @param:   @param brandId
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	
	
	/**
	 * <p>商品品牌名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public String getBrandName() {
		return brandName;
	}
	
	/**
	 * <p>商品品牌名称</p>
	 * @author:  kongdebiao
	 * @param:   @param brandName
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
	/**
	 * <p>商品多图（第一个为主图）</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public String getGoodsImageMore() {
		return goodsImageMore;
	}
	
	/**
	 * <p>商品多图（第一个为主图）</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsImageMore
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsImageMore(String goodsImageMore) {
		this.goodsImageMore = goodsImageMore;
	}
	
	
	/**
	 * <p>商品视频路径</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public String getGoodsVideo() {
		return goodsVideo;
	}
	
	/**
	 * <p>商品视频路径</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsVideo
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsVideo(String goodsVideo) {
		this.goodsVideo = goodsVideo;
	}
	
	
	/**
	 * <p>商品是否上架：1已上架; 2已下架; 3：放入仓库</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Integer getGoodsShow() {
		return goodsShow;
	}
	
	/**
	 * <p>商品是否上架：1已上架; 2已下架; 3：放入仓库</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsShow
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsShow(Integer goodsShow) {
		this.goodsShow = goodsShow;
	}
	
	
	/**
	 * <p>商品是否添加完整 0：未完成 1：已完成</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Integer getGoodsIsComplete() {
		return goodsIsComplete;
	}
	
	/**
	 * <p>商品是否添加完整 0：未完成 1：已完成</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsIsComplete
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsIsComplete(Integer goodsIsComplete) {
		this.goodsIsComplete = goodsIsComplete;
	}
	
	
	/**
	 * <p>商品添加步骤 1:第一步（基本信息）2:第二步（详细信息）3:完成</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Integer getGoodsStaus() {
		return goodsStaus;
	}
	
	/**
	 * <p>商品添加步骤 1:第一步（基本信息）2:第二步（详细信息）3:完成</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsStaus
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsStaus(Integer goodsStaus) {
		this.goodsStaus = goodsStaus;
	}
	
	
	/**
	 * <p>商品是否审核 1:待审核 2:审核通过 3:审核拒绝</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Integer getIsAudit() {
		return isAudit;
	}

	/**
	 * <p>商品是否审核 1:待审核 2:审核通过 3:审核拒绝</p>
	 * @author:  kongdebiao
	 * @param:   @param isAudit
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	public String getRefusalCause() {
		return refusalCause;
	}

	public void setRefusalCause(String refusalCause) {
		this.refusalCause = refusalCause;
	}

	/**
	 * <p>商品浏览数</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Integer getGoodsClick() {
		return goodsClick;
	}
	
	/**
	 * <p>商品浏览数</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsClick
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsClick(Integer goodsClick) {
		this.goodsClick = goodsClick;
	}
	
	
	/**
	 * <p>初始销售数量</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Integer getSaleNum() {
		return saleNum;
	}
	
	/**
	 * <p>初始销售数量</p>
	 * @author:  kongdebiao
	 * @param:   @param saleNum
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}
	
	
	/**
	 * <p>商品详细内容(pc端)</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public String getGoodsBodyPc() {
		return goodsBodyPc;
	}
	
	/**
	 * <p>商品详细内容(pc端)</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsBodyPc
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsBodyPc(String goodsBodyPc) {
		this.goodsBodyPc = goodsBodyPc;
	}
	
	
	/**
	 * <p>商品详细内容(移动端)</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public String getGoodsBodyApp() {
		return goodsBodyApp;
	}
	
	/**
	 * <p>商品详细内容(移动端)</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsBodyApp
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsBodyApp(String goodsBodyApp) {
		this.goodsBodyApp = goodsBodyApp;
	}
	
	
	/**
	 * <p>商品关键字</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public String getGoodsKeywords() {
		return goodsKeywords;
	}
	
	/**
	 * <p>商品关键字</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsKeywords
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsKeywords(String goodsKeywords) {
		this.goodsKeywords = goodsKeywords;
	}
	
	
	/**
	 * <p>运费模板ID，不使用运费模板值为0</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Long getTransportId() {
		return transportId;
	}
	
	/**
	 * <p>运费模板ID，不使用运费模板值为0</p>
	 * @author:  kongdebiao
	 * @param:   @param transportId
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}
	
	
	/**
	 * <p>运费模板名称，不使用运费模板值为0</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public String getTransportName() {
		return transportName;
	}
	
	/**
	 * <p>运费模板名称，不使用运费模板值为0</p>
	 * @author:  kongdebiao
	 * @param:   @param transportName
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}
	
	
	/**
	 * <p>商品二维码路径</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public String getGoodsWoCodeUrl() {
		return goodsWoCodeUrl;
	}
	
	/**
	 * <p>商品二维码路径</p>
	 * @author:  kongdebiao
	 * @param:   @param goodsWoCodeUrl
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setGoodsWoCodeUrl(String goodsWoCodeUrl) {
		this.goodsWoCodeUrl = goodsWoCodeUrl;
	}
	
	
	/**
	 * <p>评论次数</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Integer getCommentNum() {
		return commentNum;
	}
	
	/**
	 * <p>评论次数</p>
	 * @author:  kongdebiao
	 * @param:   @param commentNum
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	
	
	/**
	 * <p>是否删除 0:未删除  1:已删除</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:44:41    
	 */
	public Integer getIsDel() {
		return isDel;
	}
	
	/**
	 * <p>是否删除 0:未删除  1:已删除</p>
	 * @author:  kongdebiao
	 * @param:   @param isDel
	 * @return:  void 
	 * @Date :   2018-11-16 10:44:41   
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getIsGroup() {
		return isGroup;
	}

	public void setIsGroup(Integer isGroup) {
		this.isGroup = isGroup;
	}

	public Integer getIsVie() {
		return isVie;
	}

	public void setIsVie(Integer isVie) {
		this.isVie = isVie;
	}

	public Integer getRebateType() {
		return rebateType;
	}

	public void setRebateType(Integer rebateType) {
		this.rebateType = rebateType;
	}

	public BigDecimal getRebatePrice() {
		return rebatePrice;
	}

	public void setRebatePrice(BigDecimal rebatePrice) {
		this.rebatePrice = rebatePrice;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getActivityGoodsId() {
		return activityGoodsId;
	}

	public void setActivityGoodsId(Long activityGoodsId) {
		this.activityGoodsId = activityGoodsId;
	}

	public BigDecimal getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}

	public Integer getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Date getActivitySTime() {
		return activitySTime;
	}

	public void setActivitySTime(Date activitySTime) {
		this.activitySTime = activitySTime;
	}

	public Date getActivityETime() {
		return activityETime;
	}

	public void setActivityETime(Date activityETime) {
		this.activityETime = activityETime;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
}

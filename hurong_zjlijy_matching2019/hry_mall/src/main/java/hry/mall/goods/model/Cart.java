/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2018-12-20 10:54:36 
 */
package hry.mall.goods.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> Cart </p>
 * @author:         zhouming
 * @Date :          2018-12-20 10:54:36  
 */
@Table(name="shop_cart")
public class Cart extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "memberId")
	private Long memberId;  //用户Id
	
	@Column(name= "goodsId")
	private Long goodsId;  //商品Id
	
	@Column(name= "goodsName")
	private String goodsName;  //商品名称
	
	@Column(name= "specId")
	private Long specId;  //商品规格Id
	
	@Column(name= "specInfo")
	private String specInfo;  //规格内容
	
	@Column(name= "goodsStorePrice")
	private BigDecimal goodsStorePrice;  //商品价格
	
	@Column(name= "goodsNum")
	private Integer goodsNum;  //购买商品数量
	
	@Column(name= "goodsImagesOne")
	private String goodsImagesOne;  //商品图片首图
	
	@Column(name= "saasId")
	private String saasId;  //saasId

	@Column(name= "activityGoodsId")
	private Long activityGoodsId;// 商品活动id --- 抢购活动

	public Long getActivityGoodsId() {
		return activityGoodsId;
	}

	public void setActivityGoodsId(Long activityGoodsId) {
		this.activityGoodsId = activityGoodsId;
	}

	/**
	 * <p>主键</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2018-12-20 10:54:36    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-20 10:54:36   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2018-12-20 10:54:36    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2018-12-20 10:54:36   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>商品Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2018-12-20 10:54:36    
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * <p>商品Id</p>
	 * @author:  zhouming
	 * @param:   @param goodsId
	 * @return:  void 
	 * @Date :   2018-12-20 10:54:36   
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	
	/**
	 * <p>商品名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-12-20 10:54:36    
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * <p>商品名称</p>
	 * @author:  zhouming
	 * @param:   @param goodsName
	 * @return:  void 
	 * @Date :   2018-12-20 10:54:36   
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
	/**
	 * <p>商品规格Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2018-12-20 10:54:36    
	 */
	public Long getSpecId() {
		return specId;
	}
	
	/**
	 * <p>商品规格Id</p>
	 * @author:  zhouming
	 * @param:   @param specId
	 * @return:  void 
	 * @Date :   2018-12-20 10:54:36   
	 */
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	
	
	/**
	 * <p>规格内容</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-12-20 10:54:36    
	 */
	public String getSpecInfo() {
		return specInfo;
	}
	
	/**
	 * <p>规格内容</p>
	 * @author:  zhouming
	 * @param:   @param specInfo
	 * @return:  void 
	 * @Date :   2018-12-20 10:54:36   
	 */
	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}
	
	
	/**
	 * <p>商品价格</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2018-12-20 10:54:36    
	 */
	public BigDecimal getGoodsStorePrice() {
		return goodsStorePrice;
	}
	
	/**
	 * <p>商品价格</p>
	 * @author:  zhouming
	 * @param:   @param goodsStorePrice
	 * @return:  void 
	 * @Date :   2018-12-20 10:54:36   
	 */
	public void setGoodsStorePrice(BigDecimal goodsStorePrice) {
		this.goodsStorePrice = goodsStorePrice;
	}
	
	
	/**
	 * <p>购买商品数量</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2018-12-20 10:54:36    
	 */
	public Integer getGoodsNum() {
		return goodsNum;
	}
	
	/**
	 * <p>购买商品数量</p>
	 * @author:  zhouming
	 * @param:   @param goodsNum
	 * @return:  void 
	 * @Date :   2018-12-20 10:54:36   
	 */
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	
	/**
	 * <p>商品图片首图</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-12-20 10:54:36    
	 */
	public String getGoodsImagesOne() {
		return goodsImagesOne;
	}
	
	/**
	 * <p>商品图片首图</p>
	 * @author:  zhouming
	 * @param:   @param goodsImagesOne
	 * @return:  void 
	 * @Date :   2018-12-20 10:54:36   
	 */
	public void setGoodsImagesOne(String goodsImagesOne) {
		this.goodsImagesOne = goodsImagesOne;
	}
	
	
	/**
	 * <p>saasId</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-12-20 10:54:36    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p>saasId</p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-12-20 10:54:36   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

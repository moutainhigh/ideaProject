/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-12-24 15:03:17 
 */
package hry.mall.goods.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Favorite </p>
 * @author:         luyue
 * @Date :          2018-12-24 15:03:17  
 */
@Table(name="shop_favorite")
public class Favorite extends BaseModel {
	
	
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
	
	@Column(name= "goodsImagesOne")
	private String goodsImagesOne;  //商品图片首图
	
	@Column(name= "saasId")
	private String saasId;  //saasId
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-12-24 15:03:17    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-24 15:03:17   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-12-24 15:03:17    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2018-12-24 15:03:17   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>商品Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-12-24 15:03:17    
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * <p>商品Id</p>
	 * @author:  luyue
	 * @param:   @param goodsId
	 * @return:  void 
	 * @Date :   2018-12-24 15:03:17   
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	
	/**
	 * <p>商品名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-24 15:03:17    
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * <p>商品名称</p>
	 * @author:  luyue
	 * @param:   @param goodsName
	 * @return:  void 
	 * @Date :   2018-12-24 15:03:17   
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
	/**
	 * <p>商品规格Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-12-24 15:03:17    
	 */
	public Long getSpecId() {
		return specId;
	}
	
	/**
	 * <p>商品规格Id</p>
	 * @author:  luyue
	 * @param:   @param specId
	 * @return:  void 
	 * @Date :   2018-12-24 15:03:17   
	 */
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	
	
	/**
	 * <p>规格内容</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-24 15:03:17    
	 */
	public String getSpecInfo() {
		return specInfo;
	}
	
	/**
	 * <p>规格内容</p>
	 * @author:  luyue
	 * @param:   @param specInfo
	 * @return:  void 
	 * @Date :   2018-12-24 15:03:17   
	 */
	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}
	
	
	/**
	 * <p>商品价格</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2018-12-24 15:03:17    
	 */
	public BigDecimal getGoodsStorePrice() {
		return goodsStorePrice;
	}
	
	/**
	 * <p>商品价格</p>
	 * @author:  luyue
	 * @param:   @param goodsStorePrice
	 * @return:  void 
	 * @Date :   2018-12-24 15:03:17   
	 */
	public void setGoodsStorePrice(BigDecimal goodsStorePrice) {
		this.goodsStorePrice = goodsStorePrice;
	}
	
	
	/**
	 * <p>商品图片首图</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-24 15:03:17    
	 */
	public String getGoodsImagesOne() {
		return goodsImagesOne;
	}
	
	/**
	 * <p>商品图片首图</p>
	 * @author:  luyue
	 * @param:   @param goodsImagesOne
	 * @return:  void 
	 * @Date :   2018-12-24 15:03:17   
	 */
	public void setGoodsImagesOne(String goodsImagesOne) {
		this.goodsImagesOne = goodsImagesOne;
	}
	
	
	/**
	 * <p>saasId</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-24 15:03:17    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p>saasId</p>
	 * @author:  luyue
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-12-24 15:03:17   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

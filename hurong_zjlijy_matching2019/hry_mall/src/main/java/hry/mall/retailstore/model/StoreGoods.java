/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-09 17:40:23 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> StoreGoods </p>
 * @author:         zhouming
 * @Date :          2019-05-09 17:40:23  
 */
@Table(name="shop_store_goods")
public class StoreGoods extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "storeId")
	private Long storeId;  //店铺id
	
	@Column(name= "memberId")
	private Long memberId;  //用户id
	
	@Column(name= "goodsId")
	private Long goodsId;  //商品id
	
	@Column(name= "goodsShow")
	private Integer goodsShow;  //商品是否上架：1已上架; 2已下架; 
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:40:23    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-09 17:40:23   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>店铺id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:40:23    
	 */
	public Long getStoreId() {
		return storeId;
	}
	
	/**
	 * <p>店铺id</p>
	 * @author:  zhouming
	 * @param:   @param storeId
	 * @return:  void 
	 * @Date :   2019-05-09 17:40:23   
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:40:23    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-05-09 17:40:23   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>商品id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:40:23    
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * <p>商品id</p>
	 * @author:  zhouming
	 * @param:   @param goodsId
	 * @return:  void 
	 * @Date :   2019-05-09 17:40:23   
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	
	/**
	 * <p>商品是否上架：1已上架; 2已下架; </p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-09 17:40:23    
	 */
	public Integer getGoodsShow() {
		return goodsShow;
	}
	
	/**
	 * <p>商品是否上架：1已上架; 2已下架; </p>
	 * @author:  zhouming
	 * @param:   @param goodsShow
	 * @return:  void 
	 * @Date :   2019-05-09 17:40:23   
	 */
	public void setGoodsShow(Integer goodsShow) {
		this.goodsShow = goodsShow;
	}
	
	

}

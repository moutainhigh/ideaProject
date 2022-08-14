/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-24 18:39:42 
 */
package hry.mall.retailstore.model;
import hry.bean.BaseModel;
import javax.persistence.*;

/**
 * <p> CouponGoods </p>
 * @author:         zhouming
 * @Date :          2019-05-24 18:39:42  
 */
@Table(name="shop_coupon_goods")
public class CouponGoods extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "couponId")
	private Long couponId;  //优惠券id
	
	@Column(name= "goodsId")
	private Long goodsId;  //商品id
	
	@Column(name= "goodsName")
	private String goodsName;  //商品名称
	
	@Column(name= "useType")
	private Integer useType;  //2、指定商品，3指定分类
	
	@Column(name= "couponGcId")
	private Long couponGcId;  //优惠券指定分类id
	
	@Column(name= "couponGcName")
	private String couponGcName;  //优惠券指定分类名称
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-24 18:39:42    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:42   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>优惠券id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-24 18:39:42    
	 */
	public Long getCouponId() {
		return couponId;
	}
	
	/**
	 * <p>优惠券id</p>
	 * @author:  zhouming
	 * @param:   @param couponId
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:42   
	 */
	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}
	
	
	/**
	 * <p>商品id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-24 18:39:42    
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * <p>商品id</p>
	 * @author:  zhouming
	 * @param:   @param goodsId
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:42   
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	
	/**
	 * <p>商品名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-24 18:39:42    
	 */
	public String getGoodsName() {
		return goodsName;
	}
	
	/**
	 * <p>商品名称</p>
	 * @author:  zhouming
	 * @param:   @param goodsName
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:42   
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
	/**
	 * <p>2、指定商品，3指定分类</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-24 18:39:42    
	 */
	public Integer getUseType() {
		return useType;
	}
	
	/**
	 * <p>2、指定商品，3指定分类</p>
	 * @author:  zhouming
	 * @param:   @param useType
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:42   
	 */
	public void setUseType(Integer useType) {
		this.useType = useType;
	}
	
	
	/**
	 * <p>优惠券指定分类id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-24 18:39:42    
	 */
	public Long getCouponGcId() {
		return couponGcId;
	}
	
	/**
	 * <p>优惠券指定分类id</p>
	 * @author:  zhouming
	 * @param:   @param couponGcId
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:42   
	 */
	public void setCouponGcId(Long couponGcId) {
		this.couponGcId = couponGcId;
	}
	
	
	/**
	 * <p>优惠券指定分类名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-24 18:39:42    
	 */
	public String getCouponGcName() {
		return couponGcName;
	}
	
	/**
	 * <p>优惠券指定分类名称</p>
	 * @author:  zhouming
	 * @param:   @param couponGcName
	 * @return:  void 
	 * @Date :   2019-05-24 18:39:42   
	 */
	public void setCouponGcName(String couponGcName) {
		this.couponGcName = couponGcName;
	}
	
	

}

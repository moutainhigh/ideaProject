/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-11-26 16:44:25 
 */
package hry.mall.goods.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> CoinPay </p>
 * @author:         zhouming
 * @Date :          2019-11-26 16:44:25  
 */
@Table(name="shop_coin_pay")
public class CoinPay extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "goodsSpecId")
	private Long goodsSpecId;  //商品规格Id
	
	@Column(name= "singlePayMent")
	private String singlePayMent;  //单币种支付方式 例：币种_个数,币种_个数,....
	
	@Column(name= "isMorePayMent")
	private Integer isMorePayMent;  //是否开启混合支付 1:开启、2:关闭
	
	@Column(name= "morePayMent")
	private String morePayMent;  //混合支付方式 例：币种_个数,币种_个数,....
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-11-26 16:44:25    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-11-26 16:44:25   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>商品规格Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-11-26 16:44:25    
	 */
	public Long getGoodsSpecId() {
		return goodsSpecId;
	}
	
	/**
	 * <p>商品规格Id</p>
	 * @author:  zhouming
	 * @param:   @param goodsSpecId
	 * @return:  void 
	 * @Date :   2019-11-26 16:44:25   
	 */
	public void setGoodsSpecId(Long goodsSpecId) {
		this.goodsSpecId = goodsSpecId;
	}
	
	
	/**
	 * <p>单币种支付方式 例：币种_个数,币种_个数,....</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:44:25    
	 */
	public String getSinglePayMent() {
		return singlePayMent;
	}
	
	/**
	 * <p>单币种支付方式 例：币种_个数,币种_个数,....</p>
	 * @author:  zhouming
	 * @param:   @param singlePayMent
	 * @return:  void 
	 * @Date :   2019-11-26 16:44:25   
	 */
	public void setSinglePayMent(String singlePayMent) {
		this.singlePayMent = singlePayMent;
	}
	
	
	/**
	 * <p>是否开启混合支付 1:开启、2:关闭</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-11-26 16:44:25    
	 */
	public Integer getIsMorePayMent() {
		return isMorePayMent;
	}
	
	/**
	 * <p>是否开启混合支付 1:开启、2:关闭</p>
	 * @author:  zhouming
	 * @param:   @param isMorePayMent
	 * @return:  void 
	 * @Date :   2019-11-26 16:44:25   
	 */
	public void setIsMorePayMent(Integer isMorePayMent) {
		this.isMorePayMent = isMorePayMent;
	}
	
	
	/**
	 * <p>混合支付方式 例：币种_个数,币种_个数,....</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:44:25    
	 */
	public String getMorePayMent() {
		return morePayMent;
	}
	
	/**
	 * <p>混合支付方式 例：币种_个数,币种_个数,....</p>
	 * @author:  zhouming
	 * @param:   @param morePayMent
	 * @return:  void 
	 * @Date :   2019-11-26 16:44:25   
	 */
	public void setMorePayMent(String morePayMent) {
		this.morePayMent = morePayMent;
	}
	
	

}

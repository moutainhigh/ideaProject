/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-11-26 16:44:58 
 */
package hry.mall.goods.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p> CoinRebate </p>
 * @author:         zhouming
 * @Date :          2019-11-26 16:44:58  
 */
@Table(name="shop_coin_rebate")
public class CoinRebate extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "goodsSpecId")
	private Long goodsSpecId;  //商品规格Id
	
	@Column(name= "morePayRebateCoin")
	private String morePayRebateCoin;  //混合支付抽成/返佣币种
	
	@Column(name= "takeCoinNum")
	private BigDecimal takeCoinNum;  //混合支付平台抽成数量
	
	@Column(name= "oneRebateCoinNum")
	private BigDecimal oneRebateCoinNum;  //混合支付一级返佣数量
	
	@Column(name= "twoRebateCoinNum")
	private BigDecimal twoRebateCoinNum;  //混合支付二级返佣数量
	
	@Column(name= "singleTake")
	private String singleTake;  //单币种支付抽成 例：币种_个数,币种_个数,....
	
	@Column(name= "singleOneRebate")
	private String singleOneRebate;  //单币种支付一级返佣 例：币种_个数,币种_个数,....
	
	@Column(name= "singleTwoRebate")
	private String singleTwoRebate;  //单币种支付二级级返佣 例：币种_个数,币种_个数,....

	@Column(name= "innovateRebateCoin")
	private String innovateRebateCoin; // 创新商品返佣币种

	@Column(name= "innovateRebateSum")
	private BigDecimal innovateRebateSum; // 创新商品返佣数量
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-11-26 16:44:58    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-11-26 16:44:58   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>商品规格Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-11-26 16:44:58    
	 */
	public Long getGoodsSpecId() {
		return goodsSpecId;
	}
	
	/**
	 * <p>商品规格Id</p>
	 * @author:  zhouming
	 * @param:   @param goodsSpecId
	 * @return:  void 
	 * @Date :   2019-11-26 16:44:58   
	 */
	public void setGoodsSpecId(Long goodsSpecId) {
		this.goodsSpecId = goodsSpecId;
	}
	
	
	/**
	 * <p>混合支付抽成/返佣币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:44:58    
	 */
	public String getMorePayRebateCoin() {
		return morePayRebateCoin;
	}
	
	/**
	 * <p>混合支付抽成/返佣币种</p>
	 * @author:  zhouming
	 * @param:   @param morePayRebateCoin
	 * @return:  void 
	 * @Date :   2019-11-26 16:44:58   
	 */
	public void setMorePayRebateCoin(String morePayRebateCoin) {
		this.morePayRebateCoin = morePayRebateCoin;
	}

	public BigDecimal getTakeCoinNum() {
		return takeCoinNum;
	}

	public void setTakeCoinNum(BigDecimal takeCoinNum) {
		this.takeCoinNum = takeCoinNum;
	}

	public BigDecimal getOneRebateCoinNum() {
		return oneRebateCoinNum;
	}

	public void setOneRebateCoinNum(BigDecimal oneRebateCoinNum) {
		this.oneRebateCoinNum = oneRebateCoinNum;
	}

	public BigDecimal getTwoRebateCoinNum() {
		return twoRebateCoinNum;
	}

	public void setTwoRebateCoinNum(BigDecimal twoRebateCoinNum) {
		this.twoRebateCoinNum = twoRebateCoinNum;
	}

	/**
	 * <p>单币种支付抽成 例：币种_个数,币种_个数,....</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:44:58    
	 */
	public String getSingleTake() {
		return singleTake;
	}
	
	/**
	 * <p>单币种支付抽成 例：币种_个数,币种_个数,....</p>
	 * @author:  zhouming
	 * @param:   @param singleTake
	 * @return:  void 
	 * @Date :   2019-11-26 16:44:58   
	 */
	public void setSingleTake(String singleTake) {
		this.singleTake = singleTake;
	}
	
	
	/**
	 * <p>单币种支付一级返佣 例：币种_个数,币种_个数,....</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:44:58    
	 */
	public String getSingleOneRebate() {
		return singleOneRebate;
	}
	
	/**
	 * <p>单币种支付一级返佣 例：币种_个数,币种_个数,....</p>
	 * @author:  zhouming
	 * @param:   @param singleOneRebate
	 * @return:  void 
	 * @Date :   2019-11-26 16:44:58   
	 */
	public void setSingleOneRebate(String singleOneRebate) {
		this.singleOneRebate = singleOneRebate;
	}
	
	
	/**
	 * <p>单币种支付二级级返佣 例：币种_个数,币种_个数,....</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:44:58    
	 */
	public String getSingleTwoRebate() {
		return singleTwoRebate;
	}
	
	/**
	 * <p>单币种支付二级级返佣 例：币种_个数,币种_个数,....</p>
	 * @author:  zhouming
	 * @param:   @param singleTwoRebate
	 * @return:  void 
	 * @Date :   2019-11-26 16:44:58   
	 */
	public void setSingleTwoRebate(String singleTwoRebate) {
		this.singleTwoRebate = singleTwoRebate;
	}

	public String getInnovateRebateCoin() {
		return innovateRebateCoin;
	}

	public void setInnovateRebateCoin(String innovateRebateCoin) {
		this.innovateRebateCoin = innovateRebateCoin;
	}

	public BigDecimal getInnovateRebateSum() {
		return innovateRebateSum;
	}

	public void setInnovateRebateSum(BigDecimal innovateRebateSum) {
		this.innovateRebateSum = innovateRebateSum;
	}
}

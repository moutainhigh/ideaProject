/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2019-01-08 14:51:14 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> IntegralRebateConfig </p>
 * @author:         kongdb
 * @Date :          2019-01-08 14:51:14  
 */
@Table(name="shop_integral_rebate_config")
public class IntegralRebateConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //索引id
	
	@Column(name= "shopRebateRate")
	private BigDecimal shopRebateRate;  //购物返利比例
	
	@Column(name= "shopIntegralRate")
	private BigDecimal shopIntegralRate;  //购物返算力
	
	@Column(name= "shopPrice")
	private BigDecimal shopPrice;  //订单实际支付金额低于多少不返算力
	
	@Column(name= "shopMostintegral")
	private BigDecimal shopMostintegral;  //单件商品最高可获得多少算力
	
	@Column(name= "shopAfter")
	private Integer shopAfter;  //订单支付多少天后解冻
	
	@Column(name= "invitationIntegral")
	private BigDecimal invitationIntegral;  //邀请奖励积分
	
	@Column(name= "invitationComputing")
	private BigDecimal invitationComputing;  //邀请奖励算力
	
	@Column(name= "status")
	private Short status;  //任务是否开启，1是0否
	 
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * <p>索引id</p>
	 * @author:  kongdb
	 * @return:  Long 
	 * @Date :   2019-01-08 14:51:14    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>索引id</p>
	 * @author:  kongdb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-08 14:51:14   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>购物返利比例</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-08 14:51:14    
	 */
	public BigDecimal getShopRebateRate() {
		return shopRebateRate;
	}
	
	/**
	 * <p>购物返利比例</p>
	 * @author:  kongdb
	 * @param:   @param shopRebateRate
	 * @return:  void 
	 * @Date :   2019-01-08 14:51:14   
	 */
	public void setShopRebateRate(BigDecimal shopRebateRate) {
		this.shopRebateRate = shopRebateRate;
	}
	
	
	/**
	 * <p>购物返算力</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-08 14:51:14    
	 */
	public BigDecimal getShopIntegralRate() {
		return shopIntegralRate;
	}
	
	/**
	 * <p>购物返算力</p>
	 * @author:  kongdb
	 * @param:   @param shopIntegralRate
	 * @return:  void 
	 * @Date :   2019-01-08 14:51:14   
	 */
	public void setShopIntegralRate(BigDecimal shopIntegralRate) {
		this.shopIntegralRate = shopIntegralRate;
	}
	
	
	/**
	 * <p>订单实际支付金额低于多少不返算力</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-08 14:51:14    
	 */
	public BigDecimal getShopPrice() {
		return shopPrice;
	}
	
	/**
	 * <p>订单实际支付金额低于多少不返算力</p>
	 * @author:  kongdb
	 * @param:   @param shopPrice
	 * @return:  void 
	 * @Date :   2019-01-08 14:51:14   
	 */
	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}
	
	
	/**
	 * <p>单件商品最高可获得多少算力</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-08 14:51:14    
	 */
	public BigDecimal getShopMostintegral() {
		return shopMostintegral;
	}
	
	/**
	 * <p>单件商品最高可获得多少算力</p>
	 * @author:  kongdb
	 * @param:   @param shopMostintegral
	 * @return:  void 
	 * @Date :   2019-01-08 14:51:14   
	 */
	public void setShopMostintegral(BigDecimal shopMostintegral) {
		this.shopMostintegral = shopMostintegral;
	}
	
	
	/**
	 * <p>订单支付多少天后解冻</p>
	 * @author:  kongdb
	 * @return:  Integer 
	 * @Date :   2019-01-08 14:51:14    
	 */
	public Integer getShopAfter() {
		return shopAfter;
	}
	
	/**
	 * <p>订单支付多少天后解冻</p>
	 * @author:  kongdb
	 * @param:   @param shopAfter
	 * @return:  void 
	 * @Date :   2019-01-08 14:51:14   
	 */
	public void setShopAfter(Integer shopAfter) {
		this.shopAfter = shopAfter;
	}
	
	
	/**
	 * <p>邀请奖励积分</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-08 14:51:14    
	 */
	public BigDecimal getInvitationIntegral() {
		return invitationIntegral;
	}
	
	/**
	 * <p>邀请奖励积分</p>
	 * @author:  kongdb
	 * @param:   @param invitationIntegral
	 * @return:  void 
	 * @Date :   2019-01-08 14:51:14   
	 */
	public void setInvitationIntegral(BigDecimal invitationIntegral) {
		this.invitationIntegral = invitationIntegral;
	}
	
	
	/**
	 * <p>邀请奖励算力</p>
	 * @author:  kongdb
	 * @return:  BigDecimal 
	 * @Date :   2019-01-08 14:51:14    
	 */
	public BigDecimal getInvitationComputing() {
		return invitationComputing;
	}
	
	/**
	 * <p>邀请奖励算力</p>
	 * @author:  kongdb
	 * @param:   @param invitationComputing
	 * @return:  void 
	 * @Date :   2019-01-08 14:51:14   
	 */
	public void setInvitationComputing(BigDecimal invitationComputing) {
		this.invitationComputing = invitationComputing;
	}
	
	

}

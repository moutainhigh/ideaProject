/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-12-01 12:19:24 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> BlendPay </p>
 * @author:         kongdebiao
 * @Date :          2018-12-01 12:19:24  
 */
@Table(name="shop_blend_pay")
public class BlendPay extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //支付索引id
	
	@Column(name= "mainPay")
	private String mainPay;  //主支付通道
	
	@Column(name= "supportCurrency")
	private String supportCurrency;  //支持币种用,隔开
	
	@Column(name= "isBlend")
	private Integer isBlend;  //是否开启混合支付0禁用1启用
	
	@Column(name= "blendRateMax")
	private String blendRateMax;  //最大混合比例%
	
	@Column(name= "isRmbBlend")
	private Short isRmbBlend;  //人民币支付是否开启，1是0否
	
	@Column(name= "isCoinBlend")
	private Short isCoinBlend;  //数币支付是否开启，1是0否
	
	@Column(name= "isConsumeBlend")
	private Short isConsumeBlend;  //消费账户支付是否开启，1是0否
	
	public Short getIsConsumeBlend() {
		return isConsumeBlend;
	}

	public void setIsConsumeBlend(Short isConsumeBlend) {
		this.isConsumeBlend = isConsumeBlend;
	}

	public Short getIsRmbBlend() {
		return isRmbBlend;
	}

	public void setIsRmbBlend(Short isRmbBlend) {
		this.isRmbBlend = isRmbBlend;
	}

	public Short getIsCoinBlend() {
		return isCoinBlend;
	}

	public void setIsCoinBlend(Short isCoinBlend) {
		this.isCoinBlend = isCoinBlend;
	}

	/**
	 * <p>支付索引id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-12-01 12:19:24    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>支付索引id</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-01 12:19:24   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>主支付通道</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:19:24    
	 */
	public String getMainPay() {
		return mainPay;
	}
	
	/**
	 * <p>主支付通道</p>
	 * @author:  kongdebiao
	 * @param:   @param mainPay
	 * @return:  void 
	 * @Date :   2018-12-01 12:19:24   
	 */
	public void setMainPay(String mainPay) {
		this.mainPay = mainPay;
	}
	
	
	/**
	 * <p>支持币种用,隔开</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:19:24    
	 */
	public String getSupportCurrency() {
		return supportCurrency;
	}
	
	/**
	 * <p>支持币种用,隔开</p>
	 * @author:  kongdebiao
	 * @param:   @param supportCurrency
	 * @return:  void 
	 * @Date :   2018-12-01 12:19:24   
	 */
	public void setSupportCurrency(String supportCurrency) {
		this.supportCurrency = supportCurrency;
	}
	
	
	/**
	 * <p>是否开启混合支付0禁用1启用</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-12-01 12:19:24    
	 */
	public Integer getIsBlend() {
		return isBlend;
	}
	
	/**
	 * <p>是否开启混合支付0禁用1启用</p>
	 * @author:  kongdebiao
	 * @param:   @param isBlend
	 * @return:  void 
	 * @Date :   2018-12-01 12:19:24   
	 */
	public void setIsBlend(Integer isBlend) {
		this.isBlend = isBlend;
	}
	
	
	/**
	 * <p>最大混合比例%</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-01 12:19:24    
	 */
	public String getBlendRateMax() {
		return blendRateMax;
	}
	
	/**
	 * <p>最大混合比例%</p>
	 * @author:  kongdebiao
	 * @param:   @param blendRateMax
	 * @return:  void 
	 * @Date :   2018-12-01 12:19:24   
	 */
	public void setBlendRateMax(String blendRateMax) {
		this.blendRateMax = blendRateMax;
	}
	
	

}

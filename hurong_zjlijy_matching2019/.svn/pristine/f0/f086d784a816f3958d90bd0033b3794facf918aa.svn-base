/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-07-31 11:50:50 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AdvertisementRecord </p>
 * @author:         luyue
 * @Date :          2019-07-31 11:50:50  
 */
@Table(name="shop_advertisement_record")
public class AdvertisementRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "advertisementId")
	private Long advertisementId;  //广告id
	
	@Column(name= "memberId")
	private Long memberId;  //用户id
	
	@Column(name= "type")
	private Integer type;  //奖励类型，1点击，2浏览
	
	@Column(name= "coinCount")
	private BigDecimal coinCount;  //奖励数量
	
	@Column(name= "coinCode")
	private String coinCode;  //虚拟币币种
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-07-31 11:50:50    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-31 11:50:50   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>广告id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-07-31 11:50:50    
	 */
	public Long getAdvertisementId() {
		return advertisementId;
	}
	
	/**
	 * <p>广告id</p>
	 * @author:  luyue
	 * @param:   @param advertisementId
	 * @return:  void 
	 * @Date :   2019-07-31 11:50:50   
	 */
	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-07-31 11:50:50    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-07-31 11:50:50   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>奖励类型，1点击，2浏览</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-07-31 11:50:50    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>奖励类型，1点击，2浏览</p>
	 * @author:  luyue
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-07-31 11:50:50   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>奖励数量</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-07-31 11:50:50    
	 */
	public BigDecimal getCoinCount() {
		return coinCount;
	}
	
	/**
	 * <p>奖励数量</p>
	 * @author:  luyue
	 * @param:   @param coinCount
	 * @return:  void 
	 * @Date :   2019-07-31 11:50:50   
	 */
	public void setCoinCount(BigDecimal coinCount) {
		this.coinCount = coinCount;
	}
	
	
	/**
	 * <p>虚拟币币种</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-07-31 11:50:50    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>虚拟币币种</p>
	 * @author:  luyue
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-07-31 11:50:50   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	

}

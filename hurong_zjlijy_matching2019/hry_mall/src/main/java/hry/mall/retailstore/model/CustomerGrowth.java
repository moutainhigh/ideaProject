/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-16 20:33:22 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> CustomerGrowth </p>
 * @author:         luyue
 * @Date :          2019-05-16 20:33:22  
 */
@Table(name="shop_customer_growth")
public class CustomerGrowth extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "memberId")
	private Long memberId;  //用户Id
	
	@Column(name= "totalGrowth")
	private BigDecimal totalGrowth;  //总成长值
	
	@Column(name= "availablelGrowth")
	private BigDecimal availablelGrowth;  //可用成长值
	
	@Column(name= "freezeGrowth")
	private BigDecimal freezeGrowth;  //冻结成长值
	
	@Column(name= "growthName")
	private String growthName;  //成长值名称
	
	@Column(name= "levelId")
	private Long levelId;  //等级Id
	
	@Column(name= "levelName")
	private String levelName;  //等级名称
	
	@Column(name= "saasId")
	private String saasId;  //saasId
	
	@Column(name= "isStore")
	private Short isStore;  //是否店主权限，1是0否

	@Transient
	private Long storeId; // 店铺Id

	@Transient
	private String storeName; // 店铺名称

	@Transient
	private String storeImg; // 店铺头像

	@Transient
	private String description; // 店铺说明

	@Transient
	private Long nextLevelId; // 下个等级Id

	@Transient
	private String nextLevelName; // 下个等级名称

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreImg() {
		return storeImg;
	}

	public void setStoreImg(String storeImg) {
		this.storeImg = storeImg;
	}

	public Long getNextLevelId() {
		return nextLevelId;
	}

	public void setNextLevelId(Long nextLevelId) {
		this.nextLevelId = nextLevelId;
	}

	public String getNextLevelName() {
		return nextLevelName;
	}

	public void setNextLevelName(String nextLevelName) {
		this.nextLevelName = nextLevelName;
	}

	public Short getIsStore() {
		return isStore;
	}

	public void setIsStore(Short isStore) {
		this.isStore = isStore;
	}

	/**
	 * <p>主键</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-16 20:33:22    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-16 20:33:22   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-16 20:33:22    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-05-16 20:33:22   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>总成长值</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-16 20:33:22    
	 */
	public BigDecimal getTotalGrowth() {
		return totalGrowth;
	}
	
	/**
	 * <p>总成长值</p>
	 * @author:  luyue
	 * @param:   @param totalGrowth
	 * @return:  void 
	 * @Date :   2019-05-16 20:33:22   
	 */
	public void setTotalGrowth(BigDecimal totalGrowth) {
		this.totalGrowth = totalGrowth;
	}
	
	
	/**
	 * <p>可用成长值</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-16 20:33:22    
	 */
	public BigDecimal getAvailablelGrowth() {
		return availablelGrowth;
	}
	
	/**
	 * <p>可用成长值</p>
	 * @author:  luyue
	 * @param:   @param availablelGrowth
	 * @return:  void 
	 * @Date :   2019-05-16 20:33:22   
	 */
	public void setAvailablelGrowth(BigDecimal availablelGrowth) {
		this.availablelGrowth = availablelGrowth;
	}
	
	
	/**
	 * <p>冻结成长值</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-16 20:33:22    
	 */
	public BigDecimal getFreezeGrowth() {
		return freezeGrowth;
	}
	
	/**
	 * <p>冻结成长值</p>
	 * @author:  luyue
	 * @param:   @param freezeGrowth
	 * @return:  void 
	 * @Date :   2019-05-16 20:33:22   
	 */
	public void setFreezeGrowth(BigDecimal freezeGrowth) {
		this.freezeGrowth = freezeGrowth;
	}
	
	
	/**
	 * <p>成长值名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-16 20:33:22    
	 */
	public String getGrowthName() {
		return growthName;
	}
	
	/**
	 * <p>成长值名称</p>
	 * @author:  luyue
	 * @param:   @param growthName
	 * @return:  void 
	 * @Date :   2019-05-16 20:33:22   
	 */
	public void setGrowthName(String growthName) {
		this.growthName = growthName;
	}
	
	
	/**
	 * <p>等级Id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-16 20:33:22    
	 */
	public Long getLevelId() {
		return levelId;
	}
	
	/**
	 * <p>等级Id</p>
	 * @author:  luyue
	 * @param:   @param levelId
	 * @return:  void 
	 * @Date :   2019-05-16 20:33:22   
	 */
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-16 20:33:22    
	 */
	public String getLevelName() {
		return levelName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  luyue
	 * @param:   @param levelName
	 * @return:  void 
	 * @Date :   2019-05-16 20:33:22   
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	
	/**
	 * <p>saasId</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-16 20:33:22    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p>saasId</p>
	 * @author:  luyue
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-05-16 20:33:22   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

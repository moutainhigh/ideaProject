/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-15 11:32:09 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> MemberLevel </p>
 * @author:         luyue
 * @Date :          2019-05-15 11:32:09  
 */
@Table(name="shop_member_level")
public class MemberLevel extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "name")
	private String name;  //等级名称
	
	@Column(name= "minGrowth")
	private BigDecimal minGrowth;  //成长值满足值
	
	@Column(name= "isDefault")
	private Integer isDefault;  //是否默认,1是0否
	
	@Column(name= "isStore")
	private Integer isStore;  //是否店主权限,1是0否
	
	@Column(name= "returnType")
	private Integer returnType;  //下单返红包方式
	
	@Column(name= "fixedMoney")
	private BigDecimal fixedMoney;  //固定金额
	
	@Column(name= "returnRate")
	private BigDecimal returnRate;  //百分比
	
	@Column(name= "remark")
	private String remark;  //备注
	
	
	
	
	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-15 11:32:09    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-15 11:32:09   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-15 11:32:09    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  luyue
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-05-15 11:32:09   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>成长值满足值</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-15 11:32:09    
	 */
	public BigDecimal getMinGrowth() {
		return minGrowth;
	}
	
	/**
	 * <p>成长值满足值</p>
	 * @author:  luyue
	 * @param:   @param minGrowth
	 * @return:  void 
	 * @Date :   2019-05-15 11:32:09   
	 */
	public void setMinGrowth(BigDecimal minGrowth) {
		this.minGrowth = minGrowth;
	}
	
	
	/**
	 * <p>是否店主权限,1是0否</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-15 11:32:09    
	 */
	public Integer getIsStore() {
		return isStore;
	}
	
	/**
	 * <p>是否店主权限,1是0否</p>
	 * @author:  luyue
	 * @param:   @param isStore
	 * @return:  void 
	 * @Date :   2019-05-15 11:32:09   
	 */
	public void setIsStore(Integer isStore) {
		this.isStore = isStore;
	}
	
	
	/**
	 * <p>下单返红包方式</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2019-05-15 11:32:09    
	 */
	public Integer getReturnType() {
		return returnType;
	}
	
	/**
	 * <p>下单返红包方式</p>
	 * @author:  luyue
	 * @param:   @param returnType
	 * @return:  void 
	 * @Date :   2019-05-15 11:32:09   
	 */
	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}
	
	
	/**
	 * <p>固定金额</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-15 11:32:09    
	 */
	public BigDecimal getFixedMoney() {
		return fixedMoney;
	}
	
	/**
	 * <p>固定金额</p>
	 * @author:  luyue
	 * @param:   @param fixedMoney
	 * @return:  void 
	 * @Date :   2019-05-15 11:32:09   
	 */
	public void setFixedMoney(BigDecimal fixedMoney) {
		this.fixedMoney = fixedMoney;
	}
	
	
	/**
	 * <p>百分比</p>
	 * @author:  luyue
	 * @return:  BigDecimal 
	 * @Date :   2019-05-15 11:32:09    
	 */
	public BigDecimal getReturnRate() {
		return returnRate;
	}
	
	/**
	 * <p>百分比</p>
	 * @author:  luyue
	 * @param:   @param returnRate
	 * @return:  void 
	 * @Date :   2019-05-15 11:32:09   
	 */
	public void setReturnRate(BigDecimal returnRate) {
		this.returnRate = returnRate;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-15 11:32:09    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  luyue
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-05-15 11:32:09   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

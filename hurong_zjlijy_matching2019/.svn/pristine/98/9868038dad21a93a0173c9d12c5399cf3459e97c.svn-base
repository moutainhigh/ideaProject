/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-12-04 16:17:57 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> TransportExtend </p>
 * @author:         kongdebiao
 * @Date :          2018-12-04 16:17:57  
 */
@Table(name="shop_transport_extend")
public class TransportExtend extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //运费模板扩展ID
	
	@Column(name= "areaId")
	private String areaId;  //省级地区ID组成的串，以，隔开，两端也有，
	
	@Column(name= "areaName")
	private String areaName;  //地区name组成的串，以，隔开
	
	@Column(name= "snum")
	private Integer snum;  //首件数量
	
	@Column(name= "sprice")
	private BigDecimal sprice;  //首件运费
	
	@Column(name= "xnum")
	private Integer xnum;  //续件数量
	
	@Column(name= "xprice")
	private BigDecimal xprice;  //续件运费
	
	@Column(name= "type")
	private String type;  //模板类型2卖家承担运费1买家承担运费
	
	@Column(name= "transportId")
	private Long transportId;  //运费模板ID
	
	@Column(name= "transportTitle")
	private String transportTitle;  //运费模板
	
	@Column(name= "isDel")
	private Integer isDel;  //是否删除0:未删除;1:已删除



	@Transient
	private String updateTime;
	@Transient
	private Integer isDefault;  //是否默认模板1是2否

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * <p>运费模板扩展ID</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-12-04 16:17:57    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>运费模板扩展ID</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:57   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>省级地区ID组成的串，以，隔开，两端也有，</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-04 16:17:57    
	 */
	public String getAreaId() {
		return areaId;
	}
	
	/**
	 * <p>省级地区ID组成的串，以，隔开，两端也有，</p>
	 * @author:  kongdebiao
	 * @param:   @param AreaId
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:57   
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	
	/**
	 * <p>地区name组成的串，以，隔开</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-04 16:17:57    
	 */
	public String getAreaName() {
		return areaName;
	}
	
	/**
	 * <p>地区name组成的串，以，隔开</p>
	 * @author:  kongdebiao
	 * @param:   @param areaName
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:57   
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
	/**
	 * <p>首件数量</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-12-04 16:17:57    
	 */
	public Integer getSnum() {
		return snum;
	}
	
	/**
	 * <p>首件数量</p>
	 * @author:  kongdebiao
	 * @param:   @param snum
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:57   
	 */
	public void setSnum(Integer snum) {
		this.snum = snum;
	}
	
	
	/**
	 * <p>首件运费</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-12-04 16:17:57    
	 */
	public BigDecimal getSprice() {
		return sprice;
	}
	
	/**
	 * <p>首件运费</p>
	 * @author:  kongdebiao
	 * @param:   @param sprice
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:57   
	 */
	public void setSprice(BigDecimal sprice) {
		this.sprice = sprice;
	}
	
	
	/**
	 * <p>续件数量</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-12-04 16:17:57    
	 */
	public Integer getXnum() {
		return xnum;
	}
	
	/**
	 * <p>续件数量</p>
	 * @author:  kongdebiao
	 * @param:   @param xnum
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:57   
	 */
	public void setXnum(Integer xnum) {
		this.xnum = xnum;
	}
	
	
	/**
	 * <p>续件运费</p>
	 * @author:  kongdebiao
	 * @return:  BigDecimal 
	 * @Date :   2018-12-04 16:17:57    
	 */
	public BigDecimal getXprice() {
		return xprice;
	}
	
	/**
	 * <p>续件运费</p>
	 * @author:  kongdebiao
	 * @param:   @param xprice
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:57   
	 */
	public void setXprice(BigDecimal xprice) {
		this.xprice = xprice;
	}
	
	
	/**
	 * <p>模板类型1卖家承担运费2买家承担运费</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-12-04 16:17:57    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>模板类型1卖家承担运费2买家承担运费</p>
	 * @author:  kongdebiao
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:57   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p>运费模板ID</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-04 16:17:57    
	 */
	public Long getTransportId() {
		return transportId;
	}
	
	/**
	 * <p>运费模板ID</p>
	 * @author:  kongdebiao
	 * @param:   @param transportId
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:57   
	 */
	public void setTransportId(Long transportId) {
		this.transportId = transportId;
	}
	
	
	/**
	 * <p>运费模板</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-04 16:17:57    
	 */
	public String getTransportTitle() {
		return transportTitle;
	}
	
	/**
	 * <p>运费模板</p>
	 * @author:  kongdebiao
	 * @param:   @param transportTitle
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:57   
	 */
	public void setTransportTitle(String transportTitle) {
		this.transportTitle = transportTitle;
	}
	
	
	/**
	 * <p>是否删除0:未删除;1:已删除</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-12-04 16:17:57    
	 */
	public Integer getIsDel() {
		return isDel;
	}
	
	/**
	 * <p>是否删除0:未删除;1:已删除</p>
	 * @author:  kongdebiao
	 * @param:   @param isDel
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:57   
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	

}

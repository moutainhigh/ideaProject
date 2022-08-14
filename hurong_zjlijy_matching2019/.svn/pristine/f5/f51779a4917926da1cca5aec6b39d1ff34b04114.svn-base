/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2018-12-14 11:32:11 
 */
package hry.mall.goods.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> ShopFloor </p>
 * @author:         zhouming
 * @Date :          2018-12-14 11:32:11  
 */
@Table(name="shop_floor")
public class ShopFloor extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "floorName")
	private String floorName;  //楼层标题
	
	@Column(name= "floorSubName")
	private String floorSubName;  //楼层副标题
	
	@Column(name= "floorInfo")
	private String floorInfo;  //楼层内容
	
	@Column(name= "isShow")
	private Integer isShow;  //是否显示1：显示；0：不显示
	
	@Column(name= "saasId")
	private String saasId;  //saasId
	
	@Column(name = "sort")
	private Integer sort; //排序字段

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * <p>主键</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2018-12-14 11:32:11    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-14 11:32:11   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>楼层标题</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-12-14 11:32:11    
	 */
	public String getFloorName() {
		return floorName;
	}
	
	/**
	 * <p>楼层标题</p>
	 * @author:  zhouming
	 * @param:   @param floorName
	 * @return:  void 
	 * @Date :   2018-12-14 11:32:11   
	 */
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	
	
	/**
	 * <p>楼层副标题</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-12-14 11:32:11    
	 */
	public String getFloorSubName() {
		return floorSubName;
	}
	
	/**
	 * <p>楼层副标题</p>
	 * @author:  zhouming
	 * @param:   @param floorSubName
	 * @return:  void 
	 * @Date :   2018-12-14 11:32:11   
	 */
	public void setFloorSubName(String floorSubName) {
		this.floorSubName = floorSubName;
	}
	
	
	/**
	 * <p>楼层内容</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-12-14 11:32:11    
	 */
	public String getFloorInfo() {
		return floorInfo;
	}
	
	/**
	 * <p>楼层内容</p>
	 * @author:  zhouming
	 * @param:   @param floorInfo
	 * @return:  void 
	 * @Date :   2018-12-14 11:32:11   
	 */
	public void setFloorInfo(String floorInfo) {
		this.floorInfo = floorInfo;
	}
	
	
	/**
	 * <p>是否显示1：显示；0：不显示</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2018-12-14 11:32:11    
	 */
	public Integer getIsShow() {
		return isShow;
	}
	
	/**
	 * <p>是否显示1：显示；0：不显示</p>
	 * @author:  zhouming
	 * @param:   @param isShow
	 * @return:  void 
	 * @Date :   2018-12-14 11:32:11   
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	
	
	/**
	 * <p>saasId</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-12-14 11:32:11    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p>saasId</p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-12-14 11:32:11   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

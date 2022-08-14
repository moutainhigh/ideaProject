/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:43:59 
 */
package hry.mall.goods.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> Brand </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:43:59  
 */
@Table(name="shop_brand")
public class Brand extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //索引ID
	
	@Column(name= "brandName")
	private String brandName;  //品牌名称

	@Column(name= "enBrandName")
	private String enBrandName; //英文品牌名称

	@Column(name= "brandPic")
	private String brandPic;  //品牌logo
	
	@Column(name= "brandBigPic")
	private String brandBigPic;  //品牌专区大图
	
	@Column(name= "brandDescription")
	private String brandDescription;  //品牌描述
	
	@Column(name= "sort")
	private Integer sort;  //排序
	
	@Column(name= "brandShow")
	private Integer brandShow;  //前台显示，0为否，1为是，默认为1
	
	
	
	
	/**
	 * <p>索引ID</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:43:59    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>索引ID</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 10:43:59   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>品牌名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:43:59    
	 */
	public String getBrandName() {
		return brandName;
	}
	
	/**
	 * <p>品牌名称</p>
	 * @author:  kongdebiao
	 * @param:   @param brandName
	 * @return:  void 
	 * @Date :   2018-11-16 10:43:59   
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getEnBrandName() {
		return enBrandName;
	}

	public void setEnBrandName(String enBrandName) {
		this.enBrandName = enBrandName;
	}

	/**
	 * <p>品牌logo</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:43:59    
	 */
	public String getBrandPic() {
		return brandPic;
	}
	
	/**
	 * <p>品牌logo</p>
	 * @author:  kongdebiao
	 * @param:   @param brandPic
	 * @return:  void 
	 * @Date :   2018-11-16 10:43:59   
	 */
	public void setBrandPic(String brandPic) {
		this.brandPic = brandPic;
	}
	
	
	/**
	 * <p>品牌专区大图</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:43:59    
	 */
	public String getBrandBigPic() {
		return brandBigPic;
	}
	
	/**
	 * <p>品牌专区大图</p>
	 * @author:  kongdebiao
	 * @param:   @param brandBigPic
	 * @return:  void 
	 * @Date :   2018-11-16 10:43:59   
	 */
	public void setBrandBigPic(String brandBigPic) {
		this.brandBigPic = brandBigPic;
	}
	
	
	/**
	 * <p>品牌描述</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:43:59    
	 */
	public String getBrandDescription() {
		return brandDescription;
	}
	
	/**
	 * <p>品牌描述</p>
	 * @author:  kongdebiao
	 * @param:   @param brandDescription
	 * @return:  void 
	 * @Date :   2018-11-16 10:43:59   
	 */
	public void setBrandDescription(String brandDescription) {
		this.brandDescription = brandDescription;
	}
	
	
	/**
	 * <p>排序</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:43:59    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>排序</p>
	 * @author:  kongdebiao
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-11-16 10:43:59   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p>前台显示，0为否，1为是，默认为1</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:43:59    
	 */
	public Integer getBrandShow() {
		return brandShow;
	}
	
	/**
	 * <p>前台显示，0为否，1为是，默认为1</p>
	 * @author:  kongdebiao
	 * @param:   @param brandShow
	 * @return:  void 
	 * @Date :   2018-11-16 10:43:59   
	 */
	public void setBrandShow(Integer brandShow) {
		this.brandShow = brandShow;
	}
	
	

}

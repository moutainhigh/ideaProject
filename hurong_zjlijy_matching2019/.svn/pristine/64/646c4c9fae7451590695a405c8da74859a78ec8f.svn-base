/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:46:43 
 */
package hry.mall.goods.model;


import hry.bean.BaseModel;


import javax.persistence.*;

/**
 * <p> SpecValue </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:46:43  
 */
@Table(name="shop_spec_value")
public class SpecValue extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //规格值id
	
	@Column(name= "spValueName")
	private String spValueName;  //规格值名称
	
	@Column(name= "spId")
	private Long spId;  //所属规格id
	
	@Column(name= "spValueImage")
	private String spValueImage;  //规格图片
	
	@Column(name= "sort")
	private Integer sort;  //排序
	
	@Transient
	private boolean pitchOn;//是否被选中

	public boolean isPitchOn() {
		return pitchOn;
	}

	public void setPitchOn(boolean pitchOn) {
		this.pitchOn = pitchOn;
	}

	/**
	 * <p>规格值id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:46:43    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>规格值id</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 10:46:43   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>规格值名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:46:43    
	 */
	public String getSpValueName() {
		return spValueName;
	}
	
	/**
	 * <p>规格值名称</p>
	 * @author:  kongdebiao
	 * @param:   @param spValueName
	 * @return:  void 
	 * @Date :   2018-11-16 10:46:43   
	 */
	public void setSpValueName(String spValueName) {
		this.spValueName = spValueName;
	}
	
	
	/**
	 * <p>所属规格id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:46:43    
	 */
	public Long getSpId() {
		return spId;
	}
	
	/**
	 * <p>所属规格id</p>
	 * @author:  kongdebiao
	 * @param:   @param spId
	 * @return:  void 
	 * @Date :   2018-11-16 10:46:43   
	 */
	public void setSpId(Long spId) {
		this.spId = spId;
	}
	
	
	/**
	 * <p>规格图片</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:46:43    
	 */
	public String getSpValueImage() {
		return spValueImage;
	}
	
	/**
	 * <p>规格图片</p>
	 * @author:  kongdebiao
	 * @param:   @param spValueImage
	 * @return:  void 
	 * @Date :   2018-11-16 10:46:43   
	 */
	public void setSpValueImage(String spValueImage) {
		this.spValueImage = spValueImage;
	}
	
	
	/**
	 * <p>排序</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:46:43    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>排序</p>
	 * @author:  kongdebiao
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-11-16 10:46:43   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	

}

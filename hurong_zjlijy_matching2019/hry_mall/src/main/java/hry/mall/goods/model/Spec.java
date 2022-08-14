/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:46:10 
 */
package hry.mall.goods.model;


import hry.bean.BaseModel;


import javax.persistence.*;
import java.util.List;

/**
 * <p> Spec </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:46:10  
 */
@Table(name="shop_spec")
public class Spec extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //规格id
	
	@Column(name= "spName")
	private String spName;  //规格名称
	
	@Column(name= "gcId")
	private Long gcId;  //商品分类id
	
	@Column(name= "spFormat")
	private Integer spFormat;  //规格类型 0:text; 1:image
	
	@Column(name= "sort")
	private Integer sort;  //排序

	@Transient
	private List<SpecValue> specValues;

	public List<SpecValue> getSpecValues() {
		return specValues;
	}

	public void setSpecValues(List<SpecValue> specValues) {
		this.specValues = specValues;
	}

	/**
	 * <p>规格id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:46:10    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>规格id</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 10:46:10   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>规格名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:46:10    
	 */
	public String getSpName() {
		return spName;
	}
	
	/**
	 * <p>规格名称</p>
	 * @author:  kongdebiao
	 * @param:   @param spName
	 * @return:  void 
	 * @Date :   2018-11-16 10:46:10   
	 */
	public void setSpName(String spName) {
		this.spName = spName;
	}
	
	
	/**
	 * <p>商品分类id</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:46:10    
	 */
	public Long getGcId() {
		return gcId;
	}
	
	/**
	 * <p>商品分类id</p>
	 * @author:  kongdebiao
	 * @param:   @param gcId
	 * @return:  void 
	 * @Date :   2018-11-16 10:46:10   
	 */
	public void setGcId(Long gcId) {
		this.gcId = gcId;
	}
	
	
	/**
	 * <p>规格类型 0:text; 1:image</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:46:10    
	 */
	public Integer getSpFormat() {
		return spFormat;
	}
	
	/**
	 * <p>规格类型 0:text; 1:image</p>
	 * @author:  kongdebiao
	 * @param:   @param spFormat
	 * @return:  void 
	 * @Date :   2018-11-16 10:46:10   
	 */
	public void setSpFormat(Integer spFormat) {
		this.spFormat = spFormat;
	}
	
	
	/**
	 * <p>排序</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:46:10    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>排序</p>
	 * @author:  kongdebiao
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-11-16 10:46:10   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	

}

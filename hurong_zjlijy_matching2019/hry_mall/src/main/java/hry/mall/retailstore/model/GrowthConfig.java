/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-07 11:07:06 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> GrowthConfig </p>
 * @author:         luyue
 * @Date :          2019-05-07 11:07:06  
 */
@Table(name="shop_growth_config")
public class GrowthConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "name")
	private String name;  //名称：
	
	@Column(name= "remark")
	private String remark;  //说明
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2019-05-07 11:07:06    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-07 11:07:06   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>名称：</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-07 11:07:06    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>名称：</p>
	 * @author:  luyue
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-05-07 11:07:06   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>说明</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2019-05-07 11:07:06    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>说明</p>
	 * @author:  luyue
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-05-07 11:07:06   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-11-26 16:41:38 
 */
package hry.mall.merchant.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> MerchantGrade </p>
 * @author:         zhouming
 * @Date :          2019-11-26 16:41:38  
 */
@Table(name="shop_merchant_grade")
public class MerchantGrade extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "gradeName")
	private String gradeName;  //等级名称
	
	@Column(name= "gradeLogo")
	private String gradeLogo;  //等级logo
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-11-26 16:41:38    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-11-26 16:41:38   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>等级名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:41:38    
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  zhouming
	 * @param:   @param gradeName
	 * @return:  void 
	 * @Date :   2019-11-26 16:41:38   
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	/**
	 * <p>等级logo</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-11-26 16:41:38    
	 */
	public String getGradeLogo() {
		return gradeLogo;
	}
	
	/**
	 * <p>等级logo</p>
	 * @author:  zhouming
	 * @param:   @param gradeLogo
	 * @return:  void 
	 * @Date :   2019-11-26 16:41:38   
	 */
	public void setGradeLogo(String gradeLogo) {
		this.gradeLogo = gradeLogo;
	}
	
	

}

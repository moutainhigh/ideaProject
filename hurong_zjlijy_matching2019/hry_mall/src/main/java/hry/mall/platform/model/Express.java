/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-12-04 19:08:23 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Express </p>
 * @author:         luyue
 * @Date :          2018-12-04 19:08:23  
 */
@Table(name="shop_express")
public class Express extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "name")
//	@Words(field = "快递名称", message = "快递名称包含敏感词")
	private String name;  //快递名称
	
	@Column(name= "number")
	private String number;  //编号
	
	@Column(name= "logourl")
	private String logourl;  //Logo图片
	
	@Column(name= "isValid")
	private Integer isValid;  //是否有效，1是0否
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-12-04 19:08:23    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-04 19:08:23   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>快递名称</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-04 19:08:23    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>快递名称</p>
	 * @author:  luyue
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-12-04 19:08:23   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>编号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-04 19:08:23    
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * <p>编号</p>
	 * @author:  luyue
	 * @param:   @param number
	 * @return:  void 
	 * @Date :   2018-12-04 19:08:23   
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	/**
	 * <p>Logo图片</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-12-04 19:08:23    
	 */
	public String getLogourl() {
		return logourl;
	}
	
	/**
	 * <p>Logo图片</p>
	 * @author:  luyue
	 * @param:   @param logourl
	 * @return:  void 
	 * @Date :   2018-12-04 19:08:23   
	 */
	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}
	
	
	/**
	 * <p>是否有效，1是0否</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2018-12-04 19:08:23    
	 */
	public Integer getIsValid() {
		return isValid;
	}
	
	/**
	 * <p>是否有效，1是0否</p>
	 * @author:  luyue
	 * @param:   @param isValid
	 * @return:  void 
	 * @Date :   2018-12-04 19:08:23   
	 */
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	
	

}

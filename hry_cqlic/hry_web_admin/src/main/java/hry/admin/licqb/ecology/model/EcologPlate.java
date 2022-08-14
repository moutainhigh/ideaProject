/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:37:08 
 */
package hry.admin.licqb.ecology.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> EcologPlate </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:37:08  
 */
@Table(name="lc_ecolog_plate")
public class EcologPlate extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name= "languageKey")
	private String languageKey; // 全球化key

	@Column(name= "plateName")
	private String plateName;  //板块名称
	
	@Column(name= "sort")
	private Integer sort;  //排序字段 越小越靠前
	
	@Column(name= "isOpen")
	private Integer isOpen;  //1 开启 2 关闭
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "saasId")
	private String saasId;  //


	public String getLanguageKey() {
		return languageKey;
	}

	public void setLanguageKey(String languageKey) {
		this.languageKey = languageKey;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-06-05 16:37:08    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:08   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>板块名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:37:08    
	 */
	public String getPlateName() {
		return plateName;
	}
	
	/**
	 * <p>板块名称</p>
	 * @author:  zhouming
	 * @param:   @param plateName
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:08   
	 */
	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}
	
	
	/**
	 * <p>排序字段 越小越靠前</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-05 16:37:08    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>排序字段 越小越靠前</p>
	 * @author:  zhouming
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:08   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p>1 开启 2 关闭</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-05 16:37:08    
	 */
	public Integer getIsOpen() {
		return isOpen;
	}
	
	/**
	 * <p>1 开启 2 关闭</p>
	 * @author:  zhouming
	 * @param:   @param isOpen
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:08   
	 */
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:37:08    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:08   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:37:08    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-06-05 16:37:08   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

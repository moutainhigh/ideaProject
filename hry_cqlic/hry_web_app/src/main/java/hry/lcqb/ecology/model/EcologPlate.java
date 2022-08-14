/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-06-05 16:39:38 
 */
package hry.lcqb.ecology.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> EcologPlate </p>
 * @author:         zhouming
 * @Date :          2020-06-05 16:39:38  
 */
public class EcologPlate{
	private Long id;  //

	private String languageKey; // 全球化key

	private String plateName;  //板块名称

	private Integer sort;  //排序字段 越小越靠前

	private Integer isOpen;  //1 开启 2 关闭

	private String remark;  //备注
	
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
	 * @Date :   2020-06-05 16:39:38    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-06-05 16:39:38   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>板块名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:39:38    
	 */
	public String getPlateName() {
		return plateName;
	}
	
	/**
	 * <p>板块名称</p>
	 * @author:  zhouming
	 * @param:   @param plateName
	 * @return:  void 
	 * @Date :   2020-06-05 16:39:38   
	 */
	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}
	
	
	/**
	 * <p>排序字段 越小越靠前</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-05 16:39:38    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>排序字段 越小越靠前</p>
	 * @author:  zhouming
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2020-06-05 16:39:38   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p>1 开启 2 关闭</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-06-05 16:39:38    
	 */
	public Integer getIsOpen() {
		return isOpen;
	}
	
	/**
	 * <p>1 开启 2 关闭</p>
	 * @author:  zhouming
	 * @param:   @param isOpen
	 * @return:  void 
	 * @Date :   2020-06-05 16:39:38   
	 */
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:39:38    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2020-06-05 16:39:38   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-06-05 16:39:38    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-06-05 16:39:38   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

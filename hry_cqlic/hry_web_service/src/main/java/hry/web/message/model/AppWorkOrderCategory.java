/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-25 17:35:24 
 */
package hry.web.message.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppWorkOrderCategory </p>
 * @author:         sunyujie
 * @Date :          2018-04-25 17:35:24  
 */
@Table(name="app_workorder_category")
public class AppWorkOrderCategory extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "typeName")
	private String typeName;  //类别名称
	
	@Column(name= "describes")
	private String describes;  //描述
	
	@Column(name= "sort")
	private Integer sort;  //排序字段
	
	@Column(name= "state")
	private Integer state;  //状态  0 不可编辑  1可编辑
	
	@Column(name= "language")
	private Integer language;  //语言  0中文   1英文
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-04-25 17:35:24    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-04-25 17:35:24   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>类别名称</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-04-25 17:35:24    
	 */
	public String getTypeName() {
		return typeName;
	}
	
	/**
	 * <p>类别名称</p>
	 * @author:  sunyujie
	 * @param:   @param typeName
	 * @return:  void 
	 * @Date :   2018-04-25 17:35:24   
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	/**
	 * <p>描述</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-04-25 17:35:24    
	 */
	public String getDescribes() {
		return describes;
	}
	
	/**
	 * <p>描述</p>
	 * @author:  sunyujie
	 * @param:   @param describes
	 * @return:  void 
	 * @Date :   2018-04-25 17:35:24   
	 */
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	
	/**
	 * <p>排序字段</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-04-25 17:35:24    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>排序字段</p>
	 * @author:  sunyujie
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-04-25 17:35:24   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p>0 不可编辑  1可编辑</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-04-25 17:35:24    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>0 不可编辑  1可编辑</p>
	 * @author:  sunyujie
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-04-25 17:35:24   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>语言  0中文   1英文</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-04-25 17:35:24    
	 */
	public Integer getLanguage() {
		return language;
	}
	
	/**
	 * <p>语言  0中文   1英文</p>
	 * @author:  sunyujie
	 * @param:   @param language
	 * @return:  void 
	 * @Date :   2018-04-25 17:35:24   
	 */
	public void setLanguage(Integer language) {
		this.language = language;
	}
	
	

}

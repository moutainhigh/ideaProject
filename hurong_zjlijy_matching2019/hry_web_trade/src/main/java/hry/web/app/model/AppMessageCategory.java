/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年5月30日 下午2:57:00
 */
package hry.web.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年5月30日 下午2:57:00 
 */
@SuppressWarnings("serial")
@Table(name = "app_message_category")
public class AppMessageCategory extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 类型名称
	@Column(name = "name")
	private String name;
	// 描述
	@Column(name = "describes")
	private String describes;
	// 排序字段
	@Column(name = "sort")
	private Integer sort;
	// 状态(0表示不可用     1表示可用)
	@Column(name = "state")
	private Integer state;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public AppMessageCategory(Long id, String name, String describes,
			Integer sort, Integer state) {
		super();
		this.id = id;
		this.name = name;
		this.describes = describes;
		this.sort = sort;
		this.state = state;
	}
	
	public AppMessageCategory() {
		super();
	}
	
	@Override
	public String toString() {
		return "AppMessageCategory [id=" + id + ", name=" + name
				+ ", describes=" + describes + ", sort=" + sort + ", state="
				+ state + "]";
	}
	

}





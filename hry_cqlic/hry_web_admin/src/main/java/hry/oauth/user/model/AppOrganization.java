/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年12月2日 上午11:14:48
 */
package hry.oauth.user.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.bean.BaseModel;
import io.swagger.models.auth.In;


/**
 * 
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年12月2日 上午11:14:48
 */
@Table(name = "new_app_organization")
public class AppOrganization extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	/**  
	 *   root  集团   --根节点 
	 *   company 公司
	 *   shop  门店
	 *   department 部门
	 *   
	 */
	@Column(name="type")
	private String type;
	
	//父级组织机构
	@Column(name="pid")
	private Long pid;
	
	//排序号
	@Column(name="orderno")
	private Integer orderno ;
	
	//集团名称/分公司名称/部门名称
	@Column(name="name")
	private String name ;
	
	//备注
	@Column(name="remark")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}
	

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
	
}

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年8月20日 下午5:00:48
 */
package hry.web.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年8月20日 下午5:00:48 
 */
@SuppressWarnings("serial")
@Table(name = "app_banner")
public class AppBanner extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// banner名称
	@Column(name = "name")
	private String name;
	// banner地址
	@Column(name = "picturePath")
	private String picturePath;
	
	// 状态
	@Column(name = "status")
	private Integer status;
	// 是否是图片
	@Column(name = "isTop")
	private Integer isTop;

	@Column(name = "isShow")
	private Integer isShow;
	
	@Column(name = "sort")
	private Integer sort = 0;
	
	@Column(name = "applicationType")
	private Integer applicationType;
	
	
	
	public Integer getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(Integer applicationType) {
		this.applicationType = applicationType;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark1() {
		return remark1;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark2() {
		return remark2;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark3() {
		return remark3;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}


	@Column(name = "remark1")
	private String remark1;
	
	
	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getPicturePath() {
		return picturePath;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsTop() {
		return isTop;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsShow() {
		return isShow;
	}


	

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}


	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getSort() {
		return sort;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}


	@Column(name = "remark2")
	private String remark2;
	
	@Column(name = "remark3")
	private String remark3;

	
	
}

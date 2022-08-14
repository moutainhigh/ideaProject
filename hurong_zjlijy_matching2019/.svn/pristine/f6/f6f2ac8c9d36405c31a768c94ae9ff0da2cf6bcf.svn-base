/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年8月20日 下午5:00:48
 */
package hry.web.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年8月20日 下午5:00:48 
 */
@SuppressWarnings("serial")
@Table(name = "app_api")
public class AppApi extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	//标题
	@Column(name = "title")
	private String title;
	
	//描述
	@Column(name = "description")
	private String  description;

	//是否显示
	@Column(name = "isShow")
	private Integer isShow;
	
	
	//排序
	@Column(name = "sort")
	private Integer sort;
	
	//请求地址
	@Column(name = "requestUrl")
	private String requestUrl;
	
	
	//版本号
	@Column(name = "version")
	private String version;
	
	
	@Column(name = "remark1")
	private String remark1;
	
	@Column(name = "remark2")
	private String remark2;
	
	@Column(name = "remark3")
	private String remark3;
	
	
	/**
	 * <p> TODO</p>
	 * @return:     List<AppApiParam>
	 */
	public List<AppApiParam> getList() {
		return list;
	}



	/** 
	 * <p> TODO</p>
	 * @return: List<AppApiParam>
	 */
	public void setList(List<AppApiParam> list) {
		this.list = list;
	}

	@Transient
	private List<AppApiParam> list;
	
	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getId() {
		return id;
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
	 * @return:     String
	 */
	public String getDescription() {
		return description;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTitle() {
		return title;
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
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRequestUrl() {
		return requestUrl;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getVersion() {
		return version;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setVersion(String version) {
		this.version = version;
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



	
	
}

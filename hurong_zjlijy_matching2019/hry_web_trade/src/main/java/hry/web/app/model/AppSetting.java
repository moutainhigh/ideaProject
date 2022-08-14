/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年3月30日 上午11:30:26
 */
package hry.web.app.model;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年3月30日 上午11:30:26 
 */

@Table(name="app_setting")
public class AppSetting  extends BaseModel{
	
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id",unique=true,nullable=false)
   protected Long id;
   
   @Column(name="propertiesName")
   protected String  propertiesName;
   
   @Column(name="isOpen")
   protected Integer isOpen;
   
   @Column(name="propertiesUrl")
   protected String propertiesUrl;
   
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
public String getPropertiesUrl() {
	return propertiesUrl;
}

/** 
 * <p> TODO</p>
 * @return: String
 */
public void setPropertiesUrl(String propertiesUrl) {
	this.propertiesUrl = propertiesUrl;
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
public String getPropertiesName() {
	return propertiesName;
}

/** 
 * <p> TODO</p>
 * @return: String
 */
public void setPropertiesName(String propertiesName) {
	this.propertiesName = propertiesName;
}

/**
 * <p> TODO</p>
 * @return:     Integer
 */
public Integer getIsOpen() {
	return isOpen;
}

/** 
 * <p> TODO</p>
 * @return: Integer
 */
public void setIsOpen(Integer isOpen) {
	this.isOpen = isOpen;
}

/**
 * <p> TODO</p>
 * @return:     String
 */
public String getRemark() {
	return remark;
}

/** 
 * <p> TODO</p>
 * @return: String
 */
public void setRemark(String remark) {
	this.remark = remark;
}

@Column(name="remark")
   protected  String  remark;
}

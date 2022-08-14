/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年8月20日 下午5:00:48
 */
package hry.manage.remote.model;

import hry.bean.BaseModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * <p> TODO</p>
 * @author:         Shangxl 
 * @Date :          2017年7月13日 下午1:33:39
 */
public class AppBanner extends BaseModel implements Comparable<AppBanner>{
	
	/**  
	 * @Fields : TODO   
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@ApiModelProperty(value = "1为手机端,0为电脑端" )
	private Integer applicationType;

	@ApiModelProperty(value = "图片相对路径" )
	private String picturePath;
	
	private Integer status;
	// 是否是图片
	private Integer isTop;

	private Integer isShow;
	
	private Integer sort;
	
	private String remark2;

	private String langCode;

	private String languageDic;

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getLanguageDic() {
		return languageDic;
	}

	public void setLanguageDic(String languageDic) {
		this.languageDic = languageDic;
	}

	private String whereUse;

	public String getWhereUse() {
		return whereUse;
	}

	public void setWhereUse(String whereUse) {
		this.whereUse = whereUse;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(Integer applicationType) {
		this.applicationType = applicationType;
	}

	@Override
	public int compareTo(AppBanner o) {
		 return this.getSort().compareTo(o.getSort());
	}
	
	
}

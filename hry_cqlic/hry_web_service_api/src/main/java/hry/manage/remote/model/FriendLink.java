package hry.manage.remote.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(value = "友情链接对象")
public class FriendLink implements Serializable{

	private Long id;
	@ApiModelProperty(value = "友情链接名称" )
	private String name;
	@ApiModelProperty(value = "友情链接地址" )
	private String linkUrl;
	@ApiModelProperty(value = "图片地址" )
	private String picturePath;
	@ApiModelProperty(value = "状态" )
	private Integer status;
	@ApiModelProperty(value = "是否是图片" )
	private Integer isPicture;
	@ApiModelProperty(value = "区分中国站(cn表示中国站  en表示国际站)" )
	private String website;

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

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
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

	public Integer getIsPicture() {
		return isPicture;
	}

	public void setIsPicture(Integer isPicture) {
		this.isPicture = isPicture;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}

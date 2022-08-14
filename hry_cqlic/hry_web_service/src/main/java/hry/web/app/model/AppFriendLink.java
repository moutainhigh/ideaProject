/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年5月17日 上午10:40:48
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
 * @Date :          2016年5月17日 上午10:40:48
 * app_friendlink 
 */
@SuppressWarnings("serial")
@Table(name = "app_friendlink")
public class AppFriendLink extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 友情链接名称
	@Column(name = "name")
	private String name;
	// 友情链接地址
	@Column(name = "linkUrl")
	private String linkUrl;
	// 图片地址
	@Column(name = "picturePath")
	private String picturePath;
	
	// 状态
	@Column(name = "status")
	private Integer status;
	// 是否是图片
	@Column(name = "isPicture")
	private Integer isPicture;
	
	// 区分中国站(cn表示中国站  en表示国际站)
	@Column(name = "website")
	private String website;

	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public AppFriendLink() {
		super();
	}
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
	@Override
	public String toString() {
		return "AppFrientLink [id=" + id + ", name=" + name + ", linkUrl="
				+ linkUrl + ", picturePath=" + picturePath + ", status="
				+ status + ", isPicture=" + isPicture + "]";
	}
	public AppFriendLink(Long id, String name, String linkUrl,
			String picturePath, Integer status, Integer isPicture) {
		super();
		this.id = id;
		this.name = name;
		this.linkUrl = linkUrl;
		this.picturePath = picturePath;
		this.status = status;
		this.isPicture = isPicture;
	}
	
	
}

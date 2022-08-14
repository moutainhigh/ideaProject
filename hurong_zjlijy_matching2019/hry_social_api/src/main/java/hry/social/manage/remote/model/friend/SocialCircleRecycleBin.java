/**
 * Copyright:   
 * @author:      yjl
 * @version:     V1.0 
 * @Date:        2018-12-10 18:05:54 
 */
package hry.social.manage.remote.model.friend;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * <p> SocialCircleRecycleBin </p>
 * @author:         yjl
 * @Date :          2018-12-10 18:05:54  
 */
@Table(name="social_friend_circle")
public class SocialCircleRecycleBin extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "content")
	private String content;  //文章正文
	
	@Column(name= "link")
	private String link;  //链接（只能存在一个）
	
	@Column(name= "picture")
	private String picture;  //图片（图片之间用竖杠隔开）
	
	@Column(name= "video")
	private String video;  //视频
	
	@Column(name= "music")
	private String music;  //音乐
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "device")
	private String device;  //发布设备
	
	@Column(name= "site")
	private String site;  //发布地点（位置）
	
	@Column(name= "likeCustomerIds")
	private String likeCustomerIds;  //点赞人
	
	@Column(name= "issued")
	private Integer issued;  //发布状态（1，已发布；2，已删除；）
	
	@Column(name= "issuedTime")
	private Date issuedTime;  //发布时间
	
	@Column(name= "deleteTime")
	private Date deleteTime;  //删除时间

	@Transient
	private String phone; //发布人手机号

	@Transient
	private String name; //发布人昵称

	@Transient
	private String email; //发布人邮箱

	@Transient
	private String likeNum;  //点赞数

	public String getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(String likeNum) {
		this.likeNum = likeNum;
	}

	private Long total;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <p>主键</p>
	 * @author:  yjl
	 * @return:  Long 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  yjl
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>文章正文</p>
	 * @author:  yjl
	 * @return:  String 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>文章正文</p>
	 * @author:  yjl
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>链接（只能存在一个）</p>
	 * @author:  yjl
	 * @return:  String 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * <p>链接（只能存在一个）</p>
	 * @author:  yjl
	 * @param:   @param link
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	
	/**
	 * <p>图片（图片之间用竖杠隔开）</p>
	 * @author:  yjl
	 * @return:  String 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public String getPicture() {
		return picture;
	}
	
	/**
	 * <p>图片（图片之间用竖杠隔开）</p>
	 * @author:  yjl
	 * @param:   @param picture
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	/**
	 * <p>视频</p>
	 * @author:  yjl
	 * @return:  String 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public String getVideo() {
		return video;
	}
	
	/**
	 * <p>视频</p>
	 * @author:  yjl
	 * @param:   @param video
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setVideo(String video) {
		this.video = video;
	}
	
	
	/**
	 * <p>音乐</p>
	 * @author:  yjl
	 * @return:  String 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public String getMusic() {
		return music;
	}
	
	/**
	 * <p>音乐</p>
	 * @author:  yjl
	 * @param:   @param music
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setMusic(String music) {
		this.music = music;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yjl
	 * @return:  Long 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  yjl
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>发布设备</p>
	 * @author:  yjl
	 * @return:  String 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public String getDevice() {
		return device;
	}
	
	/**
	 * <p>发布设备</p>
	 * @author:  yjl
	 * @param:   @param device
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setDevice(String device) {
		this.device = device;
	}
	
	
	/**
	 * <p>发布地点（位置）</p>
	 * @author:  yjl
	 * @return:  String 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public String getSite() {
		return site;
	}
	
	/**
	 * <p>发布地点（位置）</p>
	 * @author:  yjl
	 * @param:   @param site
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setSite(String site) {
		this.site = site;
	}
	
	
	/**
	 * <p>点赞人</p>
	 * @author:  yjl
	 * @return:  String 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public String getLikeCustomerIds() {
		return likeCustomerIds;
	}
	
	/**
	 * <p>点赞人</p>
	 * @author:  yjl
	 * @param:   @param likeCustomerIds
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setLikeCustomerIds(String likeCustomerIds) {
		this.likeCustomerIds = likeCustomerIds;
	}
	
	
	/**
	 * <p>发布状态（1，已发布；2，已删除；）</p>
	 * @author:  yjl
	 * @return:  Integer 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public Integer getIssued() {
		return issued;
	}
	
	/**
	 * <p>发布状态（1，已发布；2，已删除；）</p>
	 * @author:  yjl
	 * @param:   @param issued
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setIssued(Integer issued) {
		this.issued = issued;
	}
	
	
	/**
	 * <p>发布时间</p>
	 * @author:  yjl
	 * @return:  Date 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public Date getIssuedTime() {
		return issuedTime;
	}
	
	/**
	 * <p>发布时间</p>
	 * @author:  yjl
	 * @param:   @param issuedTime
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setIssuedTime(Date issuedTime) {
		this.issuedTime = issuedTime;
	}
	
	
	/**
	 * <p>删除时间</p>
	 * @author:  yjl
	 * @return:  Date 
	 * @Date :   2018-12-10 18:05:54    
	 */
	public Date getDeleteTime() {
		return deleteTime;
	}
	
	/**
	 * <p>删除时间</p>
	 * @author:  yjl
	 * @param:   @param deleteTime
	 * @return:  void 
	 * @Date :   2018-12-10 18:05:54   
	 */
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	
	

}

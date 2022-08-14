/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-31 16:05:22 
 */
package hry.mall.retailstore.model;
import hry.bean.BaseModel;
import javax.persistence.*;

/**
 * <p> Adv </p>
 * @author:         zhouming
 * @Date :          2019-05-31 16:05:22  
 */
@Table(name="shop_adv")
public class Adv extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "siteKey")
	private String siteKey;  //位置key
	
	@Column(name= "title")
	private String title;  //广告标题

	@Column(name= "linkMark")
	private String linkMark;  //内部跳转标识、外部链接地址、活动id
	
	@Column(name= "img")
	private String img;  //广告图片
	
	@Column(name= "sort")
	private Integer sort;  //排序

	@Column(name= "status")
	private Integer status; // 是否启动 0：关闭 1： 启动

	@Column(name = "type")
	private Integer type; // 类型 1:内部跳转，2:外部链接 3:活动

	@Column(name = "activityType")
	private Integer activityType;// 活动类型 1:拼团 2:抢购

	@Column(name = "langKey")
	private String langKey; // 语种key

	@Column(name = "advKey")
	private String advKey; // 导航唯一标识key

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-31 16:05:22    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-31 16:05:22   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>位置key</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-31 16:05:22    
	 */
	public String getSiteKey() {
		return siteKey;
	}
	
	/**
	 * <p>位置key</p>
	 * @author:  zhouming
	 * @param:   @param siteKey
	 * @return:  void 
	 * @Date :   2019-05-31 16:05:22   
	 */
	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
	}
	
	
	/**
	 * <p>广告标题</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-31 16:05:22    
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * <p>广告标题</p>
	 * @author:  zhouming
	 * @param:   @param title
	 * @return:  void 
	 * @Date :   2019-05-31 16:05:22   
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * <p>内部跳转标识、外部链接地址、活动id</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-31 16:05:22    
	 */
	public String getLinkMark() {
		return linkMark;
	}
	
	/**
	 * <p>内部跳转标识、外部链接地址、活动id</p>
	 * @author:  zhouming
	 * @param:   @param url
	 * @return:  void 
	 * @Date :   2019-05-31 16:05:22   
	 */
	public void setLinkMark(String linkMark) {
		this.linkMark = linkMark;
	}
	
	
	/**
	 * <p>广告图片</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-31 16:05:22    
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * <p>广告图片</p>
	 * @author:  zhouming
	 * @param:   @param img
	 * @return:  void 
	 * @Date :   2019-05-31 16:05:22   
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	
	/**
	 * <p>序号</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-05-31 16:05:22    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>序号</p>
	 * @author:  zhouming
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2019-05-31 16:05:22   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getLangKey() {
		return langKey;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	public String getAdvKey() {
		return advKey;
	}

	public void setAdvKey(String advKey) {
		this.advKey = advKey;
	}
}

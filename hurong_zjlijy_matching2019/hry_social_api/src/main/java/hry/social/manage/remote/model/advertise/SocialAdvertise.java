/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-07-08 17:52:28 
 */
package hry.social.manage.remote.model.advertise;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialAdvertise </p>
 * @author:         javalx
 * @Date :          2019-07-08 17:52:28  
 */
@Table(name="social_advertise")
public class SocialAdvertise extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "advName")
	private String advName;  //广告名称
	
	@Column(name= "linkMark")
	private String linkMark;  //外部链接地址(内部跳转标识)
	
	@Column(name= "headline")
	private String headline;  //顶部标题
	
	@Column(name= "img")
	private String img;  //广告图片
	
	@Column(name= "advDesc")
	private String advDesc;  //底部描述
	
	@Column(name= "sortNum")
	private Integer sortNum;  //序号
	
	@Column(name= "type")
	private Integer type;  //类型 1:内部跳转，2:外部链接
	
	@Column(name= "advKey")
	private String advKey;  //唯一标识
	
	@Column(name= "langKey")
	private String langKey;  //语种key
	
	@Column(name= "states")
	private Integer states;  //状态（0：关闭，1：开启）
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-07-08 17:52:28    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-08 17:52:28   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>广告名称</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-07-08 17:52:28    
	 */
	public String getAdvName() {
		return advName;
	}
	
	/**
	 * <p>广告名称</p>
	 * @author:  javalx
	 * @param:   @param advName
	 * @return:  void 
	 * @Date :   2019-07-08 17:52:28   
	 */
	public void setAdvName(String advName) {
		this.advName = advName;
	}
	
	
	/**
	 * <p>外部链接地址(内部跳转标识)</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-07-08 17:52:28    
	 */
	public String getLinkMark() {
		return linkMark;
	}
	
	/**
	 * <p>外部链接地址(内部跳转标识)</p>
	 * @author:  javalx
	 * @param:   @param linkMark
	 * @return:  void 
	 * @Date :   2019-07-08 17:52:28   
	 */
	public void setLinkMark(String linkMark) {
		this.linkMark = linkMark;
	}
	
	
	/**
	 * <p>顶部标题</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-07-08 17:52:28    
	 */
	public String getHeadline() {
		return headline;
	}
	
	/**
	 * <p>顶部标题</p>
	 * @author:  javalx
	 * @param:   @param headline
	 * @return:  void 
	 * @Date :   2019-07-08 17:52:28   
	 */
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	
	
	/**
	 * <p>广告图片</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-07-08 17:52:28    
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * <p>广告图片</p>
	 * @author:  javalx
	 * @param:   @param img
	 * @return:  void 
	 * @Date :   2019-07-08 17:52:28   
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	
	/**
	 * <p>底部描述</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-07-08 17:52:28    
	 */
	public String getAdvDesc() {
		return advDesc;
	}
	
	/**
	 * <p>底部描述</p>
	 * @author:  javalx
	 * @param:   @param advDesc
	 * @return:  void 
	 * @Date :   2019-07-08 17:52:28   
	 */
	public void setAdvDesc(String advDesc) {
		this.advDesc = advDesc;
	}
	
	
	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-07-08 17:52:28    
	 */
	public Integer getSortNum() {
		return sortNum;
	}
	
	/**
	 * <p>序号</p>
	 * @author:  javalx
	 * @param:   @param sortNum
	 * @return:  void 
	 * @Date :   2019-07-08 17:52:28   
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	
	/**
	 * <p>类型 1:内部跳转，2:外部链接</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-07-08 17:52:28    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型 1:内部跳转，2:外部链接</p>
	 * @author:  javalx
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-07-08 17:52:28   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>唯一标识</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-07-08 17:52:28    
	 */
	public String getAdvKey() {
		return advKey;
	}
	
	/**
	 * <p>唯一标识</p>
	 * @author:  javalx
	 * @param:   @param advKey
	 * @return:  void 
	 * @Date :   2019-07-08 17:52:28   
	 */
	public void setAdvKey(String advKey) {
		this.advKey = advKey;
	}
	
	
	/**
	 * <p>语种key</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-07-08 17:52:28    
	 */
	public String getLangKey() {
		return langKey;
	}
	
	/**
	 * <p>语种key</p>
	 * @author:  javalx
	 * @param:   @param langKey
	 * @return:  void 
	 * @Date :   2019-07-08 17:52:28   
	 */
	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}
	
	
	/**
	 * <p>状态（0：关闭，1：开启）</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-07-08 17:52:28    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p>状态（0：关闭，1：开启）</p>
	 * @author:  javalx
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2019-07-08 17:52:28   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	

}

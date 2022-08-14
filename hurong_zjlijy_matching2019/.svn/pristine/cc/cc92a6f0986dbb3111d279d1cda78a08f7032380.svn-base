/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-04 09:48:29 
 */
package hry.social.manage.remote.model.friend;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialFriendPicture </p>
 * @author:         javalx
 * @Date :          2019-06-04 09:48:29  
 */
@Table(name="social_friend_picture")
public class SocialFriendPicture extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "circleId")
	private Long circleId;  //帖子ID(social_friend_circle)
	
	@Column(name= "name")
	private String name;  //图片名称
	
	@Column(name= "path")
	private String path;  //图片路径
	
	@Column(name= "states")
	private Integer states;  //是否删除 0:否 1:是
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-04 09:48:29    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-04 09:48:29   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>帖子ID(social_friend_circle)</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-04 09:48:29    
	 */
	public Long getCircleId() {
		return circleId;
	}
	
	/**
	 * <p>帖子ID(social_friend_circle)</p>
	 * @author:  javalx
	 * @param:   @param circleId
	 * @return:  void 
	 * @Date :   2019-06-04 09:48:29   
	 */
	public void setCircleId(Long circleId) {
		this.circleId = circleId;
	}
	
	
	/**
	 * <p>图片名称</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-04 09:48:29    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>图片名称</p>
	 * @author:  javalx
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-06-04 09:48:29   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>图片路径</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-04 09:48:29    
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * <p>图片路径</p>
	 * @author:  javalx
	 * @param:   @param path
	 * @return:  void 
	 * @Date :   2019-06-04 09:48:29   
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	
	/**
	 * <p>是否删除 0:否 1:是</p>
	 * @author:  javalx
	 * @return:  Integer 
	 * @Date :   2019-06-04 09:48:29    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p>是否删除 0:否 1:是</p>
	 * @author:  javalx
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2019-06-04 09:48:29   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	

}

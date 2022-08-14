/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-03 19:54:58 
 */
package hry.social.manage.remote.model.barrage;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> SocialBarrage </p>
 * @author:         javalx
 * @Date :          2019-06-03 19:54:58  
 */
@Table(name="social_barrage")
public class SocialBarrage extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "content")
	private String content;  //弹幕内容
	
	@Column(name= "pictures")
	private String pictures;  //小图片
	
	
	
	
	/**
	 * <p></p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-03 19:54:58    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-03 19:54:58   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>弹幕内容</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 19:54:58    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>弹幕内容</p>
	 * @author:  javalx
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2019-06-03 19:54:58   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>小图片</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-03 19:54:58    
	 */
	public String getPictures() {
		return pictures;
	}
	
	/**
	 * <p>小图片</p>
	 * @author:  javalx
	 * @param:   @param pictures
	 * @return:  void 
	 * @Date :   2019-06-03 19:54:58   
	 */
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	
	

}

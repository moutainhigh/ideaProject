/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 17:44:45 
 */
package hry.social.manage.remote.model.mood;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> SocialMoodRecord </p>
 * @author:         javalx
 * @Date :          2019-06-11 17:44:45  
 */
@Table(name="social_mood_record")
public class SocialMoodRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "mood")
	private String mood;  //心情内容
	
	@Column(name= "customerId")
	private Long customerId;  //用户ID
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 17:44:45    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-11 17:44:45   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>心情内容</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 17:44:45    
	 */
	public String getMood() {
		return mood;
	}
	
	/**
	 * <p>心情内容</p>
	 * @author:  javalx
	 * @param:   @param mood
	 * @return:  void 
	 * @Date :   2019-06-11 17:44:45   
	 */
	public void setMood(String mood) {
		this.mood = mood;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 17:44:45    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-11 17:44:45   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	

}

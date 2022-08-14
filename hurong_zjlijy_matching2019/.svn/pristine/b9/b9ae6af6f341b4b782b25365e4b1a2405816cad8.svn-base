/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 17:43:48 
 */
package hry.social.manage.remote.model.task;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> SocialDownloadRecord </p>
 * @author:         javalx
 * @Date :          2019-06-11 17:43:48  
 */
@Table(name="social_download_record")
public class SocialDownloadRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "taskMark")
	private String taskMark;  //任务标识
	
	@Column(name= "customerId")
	private String customerId;  //用户ID
	
	@Column(name= "packageName")
	private String packageName;  //app标识
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 17:43:48    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-11 17:43:48   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>任务标识</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 17:43:48    
	 */
	public String getTaskMark() {
		return taskMark;
	}
	
	/**
	 * <p>任务标识</p>
	 * @author:  javalx
	 * @param:   @param taskMark
	 * @return:  void 
	 * @Date :   2019-06-11 17:43:48   
	 */
	public void setTaskMark(String taskMark) {
		this.taskMark = taskMark;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 17:43:48    
	 */
	public String getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-11 17:43:48   
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>app标识</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 17:43:48    
	 */
	public String getPackageName() {
		return packageName;
	}
	
	/**
	 * <p>app标识</p>
	 * @author:  javalx
	 * @param:   @param packageName
	 * @return:  void 
	 * @Date :   2019-06-11 17:43:48   
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	

}

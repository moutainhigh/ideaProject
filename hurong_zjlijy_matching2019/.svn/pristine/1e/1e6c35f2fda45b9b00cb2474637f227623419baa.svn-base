/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 11:08:54 
 */
package hry.social.manage.remote.model.feedback;


import hry.bean.BaseModel;


import javax.persistence.*;
import java.util.Date;

/**
 * <p> SocialFeedback </p>
 * @author:         javalx
 * @Date :          2019-06-11 11:08:54  
 */
@Table(name="social_feedback")
public class SocialFeedback extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "orderNum")
	private String orderNum;  //工单编号
	
	@Column(name= "content")
	private String content;  //反馈内容
	
	@Column(name= "customerId")
	private Long customerId;  //用户ID
	
	@Column(name= "mobilePhone")
	private String mobilePhone;  //联系电话

	@Column(name= "created")
	private Date created;  //反馈时间

	@Transient
	private String name;//用户昵称

	@Transient
	private String mobile;//注册电话

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 11:08:54    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  javalx
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-11 11:08:54   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>工单编号</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 11:08:54    
	 */
	public String getOrderNum() {
		return orderNum;
	}
	
	/**
	 * <p>工单编号</p>
	 * @author:  javalx
	 * @param:   @param orderNum
	 * @return:  void 
	 * @Date :   2019-06-11 11:08:54   
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	/**
	 * <p>反馈内容</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 11:08:54    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>反馈内容</p>
	 * @author:  javalx
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2019-06-11 11:08:54   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @return:  Long 
	 * @Date :   2019-06-11 11:08:54    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  javalx
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-11 11:08:54   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>联系电话</p>
	 * @author:  javalx
	 * @return:  String 
	 * @Date :   2019-06-11 11:08:54    
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	/**
	 * <p>联系电话</p>
	 * @author:  javalx
	 * @param:   @param mobilePhone
	 * @return:  void 
	 * @Date :   2019-06-11 11:08:54   
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}


}

/**
 * Copyright:   
 * @author:      kongdb
 * @version:     V1.0 
 * @Date:        2018-12-24 19:50:50 
 */
package hry.mall.order.model;
import hry.bean.BaseModel;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Feedback </p>
 * @author:         kongdb
 * @Date :          2018-12-24 19:50:50  
 */
@Table(name="shop_feedback")
public class Feedback extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //意见反馈
	
	@Column(name= "memberId")
	private Long memberId;  //用户Id
	
	@Column(name= "feedbackType")
	private String feedbackType;  //反馈类型
	
	@Column(name= "content")
	private String content;  //反馈内容
	
	@Column(name= "mobile")
	private String mobile;  //联系电话，QQ, 邮箱
	
	@Column(name= "image")
	private String image;  //图片
	
	
	
	
	/**
	 * <p>意见反馈</p>
	 * @author:  kongdb
	 * @return:  Long 
	 * @Date :   2018-12-24 19:50:50    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>意见反馈</p>
	 * @author:  kongdb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-24 19:50:50   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  kongdb
	 * @return:  Long 
	 * @Date :   2018-12-24 19:50:50    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  kongdb
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2018-12-24 19:50:50   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>反馈类型</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2018-12-24 19:50:50    
	 */
	public String getFeedbackType() {
		return feedbackType;
	}
	
	/**
	 * <p>反馈类型</p>
	 * @author:  kongdb
	 * @param:   @param feedbackType
	 * @return:  void 
	 * @Date :   2018-12-24 19:50:50   
	 */
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	
	
	/**
	 * <p>反馈内容</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2018-12-24 19:50:50    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>反馈内容</p>
	 * @author:  kongdb
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-12-24 19:50:50   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>联系电话，QQ, 邮箱</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2018-12-24 19:50:50    
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * <p>联系电话，QQ, 邮箱</p>
	 * @author:  kongdb
	 * @param:   @param mobile
	 * @return:  void 
	 * @Date :   2018-12-24 19:50:50   
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	/**
	 * <p>图片</p>
	 * @author:  kongdb
	 * @return:  String 
	 * @Date :   2018-12-24 19:50:50    
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * <p>图片</p>
	 * @author:  kongdb
	 * @param:   @param image
	 * @return:  void 
	 * @Date :   2018-12-24 19:50:50   
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	

}

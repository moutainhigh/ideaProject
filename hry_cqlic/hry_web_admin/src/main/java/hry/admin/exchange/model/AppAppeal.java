/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-29 13:41:12 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppAppeal </p>
 * @author:         denghf
 * @Date :          2018-10-29 13:41:12  
 */
@Table(name="app_appeal")
public class AppAppeal extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "appeal")
	private String appeal;  //诉求
	
	@Column(name= "content")
	private String content;  //详细描述
	
	@Column(name= "thingUrl")
	private String thingUrl;  //附件url
	
	@Column(name= "transactionNum")
	private String transactionNum;  //交易订单号
	
	@Column(name= "userId")
	private Long userId;  //申述人ID
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "platFormContent")
	private String platFormContent;  //平台意见
	
	@Column(name= "releaseAdvertisementId")
	private Long releaseAdvertisementId;  //广告Id
	
	@Column(name= "appealSell")
	private String appealSell;  //卖方 - 诉求
	
	@Column(name= "contentSell")
	private String contentSell;  //卖方 - 详细描述
	
	@Column(name= "thingUrlSell")
	private String thingUrlSell;  //卖方 - 附件url
	
	
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>诉求</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public String getAppeal() {
		return appeal;
	}
	
	/**
	 * <p>诉求</p>
	 * @author:  denghf
	 * @param:   @param appeal
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setAppeal(String appeal) {
		this.appeal = appeal;
	}
	
	
	/**
	 * <p>详细描述</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>详细描述</p>
	 * @author:  denghf
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>附件url</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public String getThingUrl() {
		return thingUrl;
	}
	
	/**
	 * <p>附件url</p>
	 * @author:  denghf
	 * @param:   @param thingUrl
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setThingUrl(String thingUrl) {
		this.thingUrl = thingUrl;
	}
	
	
	/**
	 * <p>交易订单号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>交易订单号</p>
	 * @author:  denghf
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>申述人ID</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p>申述人ID</p>
	 * @author:  denghf
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  denghf
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>平台意见</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public String getPlatFormContent() {
		return platFormContent;
	}
	
	/**
	 * <p>平台意见</p>
	 * @author:  denghf
	 * @param:   @param platFormContent
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setPlatFormContent(String platFormContent) {
		this.platFormContent = platFormContent;
	}
	
	
	/**
	 * <p>广告Id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public Long getReleaseAdvertisementId() {
		return releaseAdvertisementId;
	}
	
	/**
	 * <p>广告Id</p>
	 * @author:  denghf
	 * @param:   @param releaseAdvertisementId
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setReleaseAdvertisementId(Long releaseAdvertisementId) {
		this.releaseAdvertisementId = releaseAdvertisementId;
	}
	
	
	/**
	 * <p>卖方 - 诉求</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public String getAppealSell() {
		return appealSell;
	}
	
	/**
	 * <p>卖方 - 诉求</p>
	 * @author:  denghf
	 * @param:   @param appealSell
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setAppealSell(String appealSell) {
		this.appealSell = appealSell;
	}
	
	
	/**
	 * <p>卖方 - 详细描述</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public String getContentSell() {
		return contentSell;
	}
	
	/**
	 * <p>卖方 - 详细描述</p>
	 * @author:  denghf
	 * @param:   @param contentSell
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setContentSell(String contentSell) {
		this.contentSell = contentSell;
	}
	
	
	/**
	 * <p>卖方 - 附件url</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-10-29 13:41:12    
	 */
	public String getThingUrlSell() {
		return thingUrlSell;
	}
	
	/**
	 * <p>卖方 - 附件url</p>
	 * @author:  denghf
	 * @param:   @param thingUrlSell
	 * @return:  void 
	 * @Date :   2018-10-29 13:41:12   
	 */
	public void setThingUrlSell(String thingUrlSell) {
		this.thingUrlSell = thingUrlSell;
	}
	
	

}

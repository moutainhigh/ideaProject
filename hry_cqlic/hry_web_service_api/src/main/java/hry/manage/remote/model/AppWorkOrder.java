/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-25 17:55:39 
 */
package hry.manage.remote.model;


import hry.bean.BaseModel;

import java.util.Date;

/**
 * <p> AppWorkOrder </p>
 * @author:         sunyujie
 * @Date :          2018-04-25 17:55:39  
 */
public class AppWorkOrder extends BaseModel {
	
	
	private Long id;  //id
	
	private String workOrderNo;  //工单编号
	
	private Long categoryId;  //工单类型id
	
	private Long customerId;  //用户id
	
	private String categoryName;  //管理类型名称
	
	private String workContent;  //工单内容
	
	private Integer state;  //0未受理  1 已受理
	
	private Integer replyMode;  //回复方式  0邮件回复  1系统消息  2短信回复  3电话回复
	
	private Integer sort;  //排序
	
	private String replyContent;  //回复内容
	
	private Integer language;  //语言  0中文   1英文
	
	private Date processTime;  //工单受理时间

	private String languageDic;  //数据字典中的中英文
	
	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-04-25 17:55:39    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-04-25 17:55:39   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>工单编号</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-04-25 17:55:39    
	 */
	public String getWorkOrderNo() {
		return workOrderNo;
	}
	
	/**
	 * <p>工单编号</p>
	 * @author:  sunyujie
	 * @param:   @param workOrderNo
	 * @return:  void 
	 * @Date :   2018-04-25 17:55:39   
	 */
	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}
	
	
	/**
	 * <p>工单类型id</p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-04-25 17:55:39    
	 */
	public Long getCategoryId() {
		return categoryId;
	}
	
	/**
	 * <p>工单类型id</p>
	 * @author:  sunyujie
	 * @param:   @param categoryId
	 * @return:  void 
	 * @Date :   2018-04-25 17:55:39   
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	
	/**
	 * <p>管理类型名称</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-04-25 17:55:39    
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
	 * <p>管理类型名称</p>
	 * @author:  sunyujie
	 * @param:   @param categoryName
	 * @return:  void 
	 * @Date :   2018-04-25 17:55:39   
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	/**
	 * <p>工单内容</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-04-25 17:55:39    
	 */
	public String getWorkContent() {
		return workContent;
	}
	
	/**
	 * <p>工单内容</p>
	 * @author:  sunyujie
	 * @param:   @param workContent
	 * @return:  void 
	 * @Date :   2018-04-25 17:55:39   
	 */
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	
	
	/**
	 * <p>0未受理  1 已受理</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-04-25 17:55:39    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>0未受理  1 已受理</p>
	 * @author:  sunyujie
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-04-25 17:55:39   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>回复方式  0邮件回复  1系统消息  2短信回复  3电话回复</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-04-25 17:55:39    
	 */
	public Integer getReplyMode() {
		return replyMode;
	}
	
	/**
	 * <p>回复方式  0邮件回复  1系统消息  2短信回复  3电话回复</p>
	 * @author:  sunyujie
	 * @param:   @param replyMode
	 * @return:  void 
	 * @Date :   2018-04-25 17:55:39   
	 */
	public void setReplyMode(Integer replyMode) {
		this.replyMode = replyMode;
	}
	
	
	/**
	 * <p>排序</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-04-25 17:55:39    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>排序</p>
	 * @author:  sunyujie
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-04-25 17:55:39   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p>回复内容</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-04-25 17:55:39    
	 */
	public String getReplyContent() {
		return replyContent;
	}
	
	/**
	 * <p>回复内容</p>
	 * @author:  sunyujie
	 * @param:   @param replyContent
	 * @return:  void 
	 * @Date :   2018-04-25 17:55:39   
	 */
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	
	/**
	 * <p>语言  0中文   1英文</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-04-25 17:55:39    
	 */
	public Integer getLanguage() {
		return language;
	}
	
	/**
	 * <p>语言  0中文   1英文</p>
	 * @author:  sunyujie
	 * @param:   @param language
	 * @return:  void 
	 * @Date :   2018-04-25 17:55:39   
	 */
	public void setLanguage(Integer language) {
		this.language = language;
	}
	
	
	/**
	 * <p>工单受理时间</p>
	 * @author:  sunyujie
	 * @return:  Date 
	 * @Date :   2018-04-25 17:55:39    
	 */
	public Date getProcessTime() {
		return processTime;
	}
	
	/**
	 * <p>工单受理时间</p>
	 * @author:  sunyujie
	 * @param:   @param processTime
	 * @return:  void 
	 * @Date :   2018-04-25 17:55:39   
	 */
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getLanguageDic() {
		return languageDic;
	}

	public void setLanguageDic(String languageDic) {
		this.languageDic = languageDic;
	}
}

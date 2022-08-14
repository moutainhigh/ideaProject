/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年6月12日 下午4:30:48
 */
package hry.web.app.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年6月12日 下午4:30:48 
 */
public class MessageAndCustromerVo implements Serializable {
	
	private Long id;  // 消息id
	private String content; // 消息内容
	private String title; // 消息标题
	private String sortTitle;  // 消息短标题
	private Date sendDate;  // 发送时间
	private Long cid; // 中间表的id
	private Integer state; // 状态 是否已读 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSortTitle() {
		return sortTitle;
	}
	public void setSortTitle(String sortTitle) {
		this.sortTitle = sortTitle;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	
	
	
}

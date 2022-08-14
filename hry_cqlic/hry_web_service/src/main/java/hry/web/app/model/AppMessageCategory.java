/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0
 * @Date:        2016年5月30日 下午2:57:00
 */
package hry.web.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Wu Shuiming
 * @Date : 2016年5月30日 下午2:57:00
 */
@SuppressWarnings("serial")
@Table(name = "app_message_category")
public class AppMessageCategory extends BaseModel {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 类型名称
	@Column(name = "name")
	private String name;
	// 描述
	@Column(name = "describes")
	private String describes;
	// 排序字段
	@Column(name = "sort")
	private Integer sort;
	// 状态(0表示已删除   1 可编辑  2  不可编辑)
	@Column(name = "state")
	private Integer state;
	// 是否开启 0关闭 1开启
	@Column(name = "isOpen")
	private Integer isOpen;
	// 消息类别 0 中文 1 英文
	@Column(name = "messageType")
	private Integer messageType;

	@Column(name= "triggerPoint")
	private String trigger; // 触发点

	@Column(name= "messageCategory")
	private String messageCategory; // 语种简称

	@Column(name= "triggerPointLan")
	private String triggerPointLan; // 触发点名称

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessageCategory () {
		return messageCategory;
	}

	public void setMessageCategory (String messageCategory) {
		this.messageCategory = messageCategory;
	}

	public String getTriggerPointLan () {
		return triggerPointLan;
	}

	public void setTriggerPointLan (String triggerPointLan) {
		this.triggerPointLan = triggerPointLan;
	}

	public AppMessageCategory(Long id, String name, String describes,
							  Integer sort, Integer state) {
		super();
		this.id = id;
		this.name = name;
		this.describes = describes;
		this.sort = sort;
		this.state = state;
	}

	public AppMessageCategory() {
		super();
	}

	@Override
	public String toString() {
		return "AppMessageCategory [id=" + id + ", name=" + name
				+ ", describes=" + describes + ", sort=" + sort + ", state="
				+ state + "]";
	}

}

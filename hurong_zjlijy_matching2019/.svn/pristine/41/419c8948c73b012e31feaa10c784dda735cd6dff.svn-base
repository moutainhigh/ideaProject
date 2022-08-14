/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-01-14 19:15:07 
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> IntegralSign </p>
 * @author:         zhouming
 * @Date :          2019-01-14 19:15:07  
 */
@Table(name="shop_integral_sign")
public class IntegralSign extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "memberId")
	private Long memberId;  //用户Id
	
	@Column(name= "taskKey")
	private String taskKey;  //任务key
	
	@Column(name= "isdel")
	private Integer isdel;  //是否删除0:未删除;1:已删除
	
	@Column(name= "straceType")
	private Integer straceType;  //累积次数
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "saasId")
	private String saasId;  //saasId
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-01-14 19:15:07    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-14 19:15:07   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-01-14 19:15:07    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户Id</p>
	 * @author:  zhouming
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-01-14 19:15:07   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>任务key</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-01-14 19:15:07    
	 */
	public String getTaskKey() {
		return taskKey;
	}
	
	/**
	 * <p>任务key</p>
	 * @author:  zhouming
	 * @param:   @param taskKey
	 * @return:  void 
	 * @Date :   2019-01-14 19:15:07   
	 */
	public void setTaskKey(String taskKey) {
		this.taskKey = taskKey;
	}
	
	
	/**
	 * <p>是否删除0:未删除;1:已删除</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-01-14 19:15:07    
	 */
	public Integer getIsdel() {
		return isdel;
	}
	
	/**
	 * <p>是否删除0:未删除;1:已删除</p>
	 * @author:  zhouming
	 * @param:   @param isdel
	 * @return:  void 
	 * @Date :   2019-01-14 19:15:07   
	 */
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	
	
	/**
	 * <p>累积次数</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-01-14 19:15:07    
	 */
	public Integer getStraceType() {
		return straceType;
	}
	
	/**
	 * <p>累积次数</p>
	 * @author:  zhouming
	 * @param:   @param straceType
	 * @return:  void 
	 * @Date :   2019-01-14 19:15:07   
	 */
	public void setStraceType(Integer straceType) {
		this.straceType = straceType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-01-14 19:15:07    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-01-14 19:15:07   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>saasId</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-01-14 19:15:07    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p>saasId</p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-01-14 19:15:07   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

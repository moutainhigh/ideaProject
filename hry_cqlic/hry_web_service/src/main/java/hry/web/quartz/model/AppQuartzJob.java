/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2016-11-10 20:31:53 
 */
package hry.web.quartz.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppQuartzJob </p>
 * @author:         liushilei
 * @Date :          2016-11-10 20:31:53  
 */
@Table(name="app_quartz_job")
public class AppQuartzJob extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "name")
	private String name;  //定时器名称
	
	@Column(name= "beanClass")
	private String beanClass;  //类名
	
	@Column(name= "methodName")
	private String methodName;  //方法名
	
	@Column(name= "quarzTime")
	private String quarzTime;  //定时时间
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "start")
	private Integer start;  //是否启动
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2016-11-10 20:31:53    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2016-11-10 20:31:53   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>定时器名称</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2016-11-10 20:31:53    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>定时器名称</p>
	 * @author:  liushilei
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2016-11-10 20:31:53   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>类名</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2016-11-10 20:31:53    
	 */
	public String getBeanClass() {
		return beanClass;
	}
	
	/**
	 * <p>类名</p>
	 * @author:  liushilei
	 * @param:   @param beanClass
	 * @return:  void 
	 * @Date :   2016-11-10 20:31:53   
	 */
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	
	
	/**
	 * <p>方法名</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2016-11-10 20:31:53    
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * <p>方法名</p>
	 * @author:  liushilei
	 * @param:   @param methodName
	 * @return:  void 
	 * @Date :   2016-11-10 20:31:53   
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	/**
	 * <p>定时时间</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2016-11-10 20:31:53    
	 */
	public String getQuarzTime() {
		return quarzTime;
	}
	
	/**
	 * <p>定时时间</p>
	 * @author:  liushilei
	 * @param:   @param quarzTime
	 * @return:  void 
	 * @Date :   2016-11-10 20:31:53   
	 */
	public void setQuarzTime(String quarzTime) {
		this.quarzTime = quarzTime;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2016-11-10 20:31:53    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  liushilei
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2016-11-10 20:31:53   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>是否启动</p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2016-11-10 20:31:53    
	 */
	public Integer getStart() {
		return start;
	}
	
	/**
	 * <p>是否启动</p>
	 * @author:  liushilei
	 * @param:   @param start
	 * @return:  void 
	 * @Date :   2016-11-10 20:31:53   
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	
	

}

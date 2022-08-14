/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月19日 下午4:09:05
 */
package hry.web.log.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月19日 下午4:09:05 
 */
@Table(name = "student")
public class Student extends BaseModel{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="sex")
	private Integer  sex;
	
	@Column(name="age")
	private Integer age;
	
	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getId() {
		return id;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getName() {
		return name;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getSex() {
		return sex;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getAge() {
		return age;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	

}

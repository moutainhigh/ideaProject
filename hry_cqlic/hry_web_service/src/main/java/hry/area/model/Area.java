package hry.area.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.account.fund.model.AppBankCard;
import hry.core.mvc.model.BaseModel;
import hry.customer.agents.model.AppAgentsCustromer;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年3月17日 上午11:31:28
 */
@Table(name="base_area")
public class Area extends BaseModel {
	/**  
	 * @Fields : TODO   
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "name")
	private String name;  //地区名称
	
	@Column(name= "parentid")
	private Long parentid;  //父地区id
	
	@Column(name= "vieworder")
	private Integer vieworder;  //顺序

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
	 * @return:     Long
	 */
	public Long getParentid() {
		return parentid;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getVieworder() {
		return vieworder;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setVieworder(Integer vieworder) {
		this.vieworder = vieworder;
	}
	
	
}

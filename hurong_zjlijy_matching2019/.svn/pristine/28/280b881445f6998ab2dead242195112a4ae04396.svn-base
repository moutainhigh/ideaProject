/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年10月27日  17:57:57
 */
package hry.web.app.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Gao Mimi
 * @Date : 2015年10月27日 17:57:57
 */

@SuppressWarnings("serial")
@Table(name = "app_dictionary")
public class AppDictionary extends BaseModel {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Long id;

	@Column(name = "pid")
	protected Long pid;
    @Column(name="pDicKey")
   	protected String pDicKey;
    @Column(name="path")
	protected String path;
	@Column(name = "dicKey")
	protected String dicKey;
    
    @Column(name="itemName")
	protected String itemName;
    
    @Column(name="itemValue")
  	protected String itemValue;
   

	@Column(name="orderNo")

	protected Integer orderNo;

	@Column(name = "leaf")
	protected Integer leaf;

	@Column(name = "isOld")
	protected Integer isOld;

	@Column(name = "dicDescrption")
	protected String dicDescrption;

	@Column(name = "dicType")
	protected Integer dicType;



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getpDicKey() {
		return pDicKey;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setpDicKey(String pDicKey) {
		this.pDicKey = pDicKey;
	}

	/**
	 * 	 * @return Long
       
     * @hibernate.id column="id" type="java.lang.Long" generator-class="native"
=======
	 * * @return Long
	 * 
	 * @hibernate.id column="id" type="java.lang.Long" generator-class="native"
>>>>>>> .r1273
	 */

	public Long getId() {
		return this.id;
	}

	/**
	 * Set the id
	 */
	public void setId(Long aValue) {
		this.id = aValue;
	}

	/**
	 * 父节点ID * @return Integer
	 * 
	 * @hibernate.property column="pid" type="java.lang.Integer" length="10"
	 *                     not-null="false" unique="false"
	 */

	/**
	 * 全路径 * @return String
	 * 
	 * @hibernate.property column="path" type="java.lang.String" length="255"
	 *                     not-null="false" unique="false"
	 */

	public String getPath() {
		return this.path;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: Long
	 */
	public Long getPid() {
		return pid;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: Long
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}

	/**
	 * Set the path
	 */
	public void setPath(String aValue) {
		this.path = aValue;
	}

	/**
	 * 唯一标识 * @return String
	 * 
	 * @hibernate.property column="diKey" type="java.lang.String" length="255"
	 *                     not-null="false" unique="false"
	 */

	/**
<<<<<<< .mine
	 * <p> TODO</p>
	 * @return:     String
=======
	 * 标题 * @return String
	 * 
	 * @hibernate.property column="text" type="java.lang.String" length="255"
	 *                     not-null="false" unique="false"
>>>>>>> .r1273
	 */
	public String getDicKey() {
		return dicKey;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setDicKey(String dicKey) {
		this.dicKey = dicKey;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getItemValue() {
		return itemValue;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	/**
	 * 排序 * @return Integer
	 * 
	 * @hibernate.property column="orderNo" type="java.lang.Integer" length="10"
	 *                     not-null="false" unique="false"
	 */

	public Integer getOrderNo() {
		return this.orderNo;
	}

	/**
	 * Set the orderNo
	 */
	public void setOrderNo(Integer aValue) {
		this.orderNo = aValue;
	}

	/**
	 * 叶子节点0非叶子节点，1叶子节点 * @return Integer
	 * 
	 * @hibernate.property column="leaf" type="java.lang.Integer" length="10"
	 *                     not-null="false" unique="false"
	 */

	public Integer getLeaf() {
		return this.leaf;
	}

	/**
	 * Set the leaf
	 */
	public void setLeaf(Integer aValue) {
		this.leaf = aValue;
	}

	/**
	 * 是否删除0正常，1过期 * @return Integer
	 * 
	 * @hibernate.property column="isOld" type="java.lang.Integer" length="10"
	 *                     not-null="false" unique="false"
	 */

	public Integer getIsOld() {
		return this.isOld;
	}

	/**
	 * Set the isOld
	 */
	public void setIsOld(Integer aValue) {
		this.isOld = aValue;
	}

	/**
	 * 描述 * @return String
	 * 
	 * @hibernate.property column="dicDescrption" type="java.lang.String"
	 *                     length="255" not-null="false" unique="false"
	 */

	public String getDicDescrption() {
		return this.dicDescrption;
	}

	/**
	 * Set the dicDescrption
	 */
	public void setDicDescrption(String aValue) {
		this.dicDescrption = aValue;
	}

	/**
	 * 0key不可更改，在代码里用来做唯一标识的，1，单级数据字典，2，多级数据字典 * @return Integer
	 * 
	 * @hibernate.property column="dicType" type="java.lang.Integer" length="10"
	 *                     not-null="false" unique="false"
	 */

	public Integer getDicType() {
		return this.dicType;
	}

	/**
	 * Set the dicType
	 */
	public void setDicType(Integer aValue) {
		this.dicType = aValue;
	}

}

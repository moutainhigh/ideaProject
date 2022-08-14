/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年10月27日  17:57:57
 */
package hry.web.dictionary.model;

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

public class AppDicBase extends BaseModel {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Long id;
    @Column(name="itemName")
	protected String itemName;
	@Column(name="orderNo")
	protected Integer orderNo;
	@Column(name = "leaf")
	protected Integer leaf;
	@Column(name = "isOld")
	protected Integer isOld;
	@Column(name = "dicDescrption")
	protected String dicDescrption;
    @Column(name="pDicKey")
   	protected String pDicKey;
	@Column(name = "dicKey")
	protected String dicKey;

	
	
	
	
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
	 * <p> TODO</p>
	 * @return:     String
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
	public String getItemName() {
		return itemName;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getOrderNo() {
		return orderNo;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getLeaf() {
		return leaf;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsOld() {
		return isOld;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsOld(Integer isOld) {
		this.isOld = isOld;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getDicDescrption() {
		return dicDescrption;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setDicDescrption(String dicDescrption) {
		this.dicDescrption = dicDescrption;
	}




}

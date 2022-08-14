/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2015年11月6日 下午6:26:53
 */
package hry.core.mvc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2015年11月6日 下午6:26:53 
 */
@MappedSuperclass
public class BaseExModel implements Serializable {
	 private static final long serialVersionUID = -4825890686624512635L;
	 /**
     * SassId
     */
	 @Column(name="saasId")
     private String saasId;
     
     /**
      * 创建时间
      */
	 @Column(name="created")
	 private Date created;
     /**
      * 修改时间
      */
	 @Column(name="modified")
	 private Date modified;
	 
	@Column(name="currencyType")
	private String currencyType;  //交易类型
	
	
	@Column(name="website")
	private String website;//站点类别默认cn

	
	@Transient //不与数据库映射字段
	@Column(name = "keepDecimalForCoin")
	private Integer keepDecimalForCoin;
	
	@Transient //不与数据库映射字段
	@Column(name = "keepDecimalForCurrency")
	private Integer keepDecimalForCurrency;
	
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getKeepDecimalForCoin() {
		return keepDecimalForCoin;
	}


	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setKeepDecimalForCoin(Integer keepDecimalForCoin) {
		this.keepDecimalForCoin = keepDecimalForCoin;
	}


	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getKeepDecimalForCurrency() {
		return keepDecimalForCurrency;
	}


	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setKeepDecimalForCurrency(Integer keepDecimalForCurrency) {
		this.keepDecimalForCurrency = keepDecimalForCurrency;
	}
	public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getCreated() {
		return created;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getModified() {
		return modified;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	


     
}

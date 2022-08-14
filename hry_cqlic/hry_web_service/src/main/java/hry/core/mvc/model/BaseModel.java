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

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2015年11月6日 下午6:26:53 
 */
@MappedSuperclass
public class BaseModel implements Serializable {
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
     
}

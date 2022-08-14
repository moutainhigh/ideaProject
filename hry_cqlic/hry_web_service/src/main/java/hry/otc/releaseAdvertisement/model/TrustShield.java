/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-27 14:52:56 
 */
package hry.otc.releaseAdvertisement.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;

/**
 * <p> TrustShield </p>
 * @author:         denghf
 * @Date :          2018-06-27 14:52:56  
 */
@Table(name="trust_shield")
public class TrustShield extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "trust")
	private Long trust;  //信任者_屏蔽者
	
	@Column(name= "isTrust")
	private Long isTrust;  //被信任者_被屏蔽者
	
	@Column(name= "status")
	private Integer status;  //1信任 2屏蔽
	
	
	
	public Long getId() {
		return id;
	}

	public Long getTrust() {
		return trust;
	}

	public void setTrust(Long trust) {
		this.trust = trust;
	}

	public Long getIsTrust() {
		return isTrust;
	}

	public void setIsTrust(Long isTrust) {
		this.isTrust = isTrust;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p>1信任 2屏蔽</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-27 14:52:56    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>1信任 2屏蔽</p>
	 * @author:  denghf
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-27 14:52:56   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}

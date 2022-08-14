/**
 * 
 */
package hry.customer.agents.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;

/**
 * @author lvna
 *
 */

@SuppressWarnings("serial")
@Table(name="customer_as_agents")
public class CustomerAsAgents extends BaseModel {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="oneParentId")
	private Long oneParentId;
	
	@Column(name="oneParentName")
	private String oneParentName;
	
	@Column(name="twoParentId")
	private Long twoParentId;
	
	@Column(name="twoParentName")
	private String twoParentName;
	
	@Column(name="threeParentId")
	private Long threeParentId;
	
	@Column(name="threeParentName")
	private String threeParentName;
	
	@Column(name="customerId")
	private Long customerId;
	
	@Column(name="agentsCustromerId")
	private Long agentsCustromerId;

	public Long getAgentsCustromerId() {
		return agentsCustromerId;
	}

	public void setAgentsCustromerId(Long agentsCustromerId) {
		this.agentsCustromerId = agentsCustromerId;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the oneParentId
	 */
	public Long getOneParentId() {
		return oneParentId;
	}

	/**
	 * @param oneParentId the oneParentId to set
	 */
	public void setOneParentId(Long oneParentId) {
		this.oneParentId = oneParentId;
	}

	/**
	 * @return the oneParentName
	 */
	public String getOneParentName() {
		return oneParentName;
	}

	/**
	 * @param oneParentName the oneParentName to set
	 */
	public void setOneParentName(String oneParentName) {
		this.oneParentName = oneParentName;
	}

	/**
	 * @return the twoParentId
	 */
	public Long getTwoParentId() {
		return twoParentId;
	}

	/**
	 * @param twoParentId the twoParentId to set
	 */
	public void setTwoParentId(Long twoParentId) {
		this.twoParentId = twoParentId;
	}

	/**
	 * @return the twoparentName
	 */
	public String getTwoParentName() {
		return twoParentName;
	}

	/**
	 * @param twoparentName the twoparentName to set
	 */
	public void setTwoParentName(String twoParentName) {
		this.twoParentName = twoParentName;
	}

	/**
	 * @return the threeParentId
	 */
	public Long getThreeParentId() {
		return threeParentId;
	}

	/**
	 * @param threeParentId the threeParentId to set
	 */
	public void setThreeParentId(Long threeParentId) {
		this.threeParentId = threeParentId;
	}

	/**
	 * @return the threeParentName
	 */
	public String getThreeParentName() {
		return threeParentName;
	}

	/**
	 * @param threeParentName the threeParentName to set
	 */
	public void setThreeParentName(String threeParentName) {
		this.threeParentName = threeParentName;
	}

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public CustomerAsAgents(Long id, Long oneParentId, String oneParentName,
			Long twoParentId, String twoparentName, Long threeParentId,
			String threeParentName, Long agentsId) {
		super();
		this.id = id;
		this.oneParentId = oneParentId;
		this.oneParentName = oneParentName;
		this.twoParentId = twoParentId;
		this.twoParentName = twoparentName;
		this.threeParentId = threeParentId;
		this.threeParentName = threeParentName;
		this.customerId = agentsId;
	}
	
	public CustomerAsAgents() {
		super();
	}
	
	@Override
	public String toString() {
		return "CustomerAsAgents [id=" + id + ", oneParentId=" + oneParentId
				+ ", oneParentName=" + oneParentName + ", twoParentId="
				+ twoParentId + ", twoParentName=" + twoParentName
				+ ", threeParentId=" + threeParentId + ", threeParentName="
				+ threeParentName + ", agentsId=" + customerId + "]";
	}
	

}

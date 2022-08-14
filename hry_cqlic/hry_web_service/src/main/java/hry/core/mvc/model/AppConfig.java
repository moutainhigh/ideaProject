/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年10月10日  18:41:55
 */
package hry.core.mvc.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * <p> TODO</p>
 * @author:       Gao Mimi    
 * @Date :        2015年10月10日  18:41:55
 */

@Entity
@Table(name="app_config")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AppConfig  extends BaseModel {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long configid;
	protected String configkey;
	protected String value;
	protected String configdescription;
	protected String typename;
	protected String typekey;
	protected String configname;
	protected String datatype;
	protected int ishidden;



    
	
	public int getIshidden() {
		return ishidden;
	}

	public void setIshidden(int ishidden) {
		this.ishidden = ishidden;
	}
	
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTypename() {
		return typename;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTypekey() {
		return typekey;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTypekey(String typekey) {
		this.typekey = typekey;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getConfigname() {
		return configname;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setConfigname(String configname) {
		this.configname = configname;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getDatatype() {
		return datatype;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	/**
	 * 	 * @return Long
       
     * @hibernate.id column="configid" type="java.lang.Long" generator-class="native"
	 */

	public Long getConfigid() {
		return this.configid;
	}
	
	/**
	 * Set the configid
	 */	
	public void setConfigid(Long aValue) {
		this.configid = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="key" type="java.lang.String" length="255" not-null="false" unique="false"
	 */



	/**
	 * 	 * @return String
	 * @hibernate.property column="value" type="java.lang.String" length="255" not-null="false" unique="false"
	 */

	public String getValue() {
		return this.value;
	}
	
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getConfigkey() {
		return configkey;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setConfigkey(String configkey) {
		this.configkey = configkey;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getConfigdescription() {
		return configdescription;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setConfigdescription(String configdescription) {
		this.configdescription = configdescription;
	}

	/**
	 * Set the value
	 */	
	public void setValue(String aValue) {
		this.value = aValue;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */


	/**
	 * 	 * @return Long
	 * @hibernate.property column="description" type="java.lang.String" length="19" not-null="false" unique="false"
	 */

	
	




}

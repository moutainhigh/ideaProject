/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-03-01 14:17:02 
 */
package hry.manage.remote.model;

import hry.bean.BaseModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <p> SysDic </p>
 * @author:         liushilei
 * @Date :          2017-03-01 14:17:02  
 */
public class AppDic extends BaseModel implements Comparable<AppDic> {

	private Long id;  //id

	@ApiModelProperty(value = "字典标识" )
	private String mkey;  //mkey

	@ApiModelProperty(value = "父类标识" )
	private String pkey;  //pkey

	@ApiModelProperty(value = "字典项名称" )
	private String name;  //name

	@ApiModelProperty(value = "字典项值" )
	private String value;  //value

	@ApiModelProperty(value = "" )
	private String type;  //type

	@ApiModelProperty(value = "" )
	private String remark1;  //remark1

	@ApiModelProperty(value = "" )
	private String remark2;  //remark2

	@ApiModelProperty(value = "" )
	private String remark3;  //remark3
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p>mkey</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-03-01 14:17:02    
	 */
	public String getMkey() {
		return mkey;
	}
	
	/**
	 * <p>mkey</p>
	 * @author:  liushilei
	 * @param:   @param mkey
	 * @return:  void 
	 * @Date :   2017-03-01 14:17:02   
	 */
	public void setMkey(String mkey) {
		this.mkey = mkey;
	}
	
	
	/**
	 * <p>pkey</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-03-01 14:17:02    
	 */
	public String getPkey() {
		return pkey;
	}
	
	/**
	 * <p>pkey</p>
	 * @author:  liushilei
	 * @param:   @param pkey
	 * @return:  void 
	 * @Date :   2017-03-01 14:17:02   
	 */
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	
	
	/**
	 * <p>name</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-03-01 14:17:02    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>name</p>
	 * @author:  liushilei
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2017-03-01 14:17:02   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>value</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-03-01 14:17:02    
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * <p>value</p>
	 * @author:  liushilei
	 * @param:   @param value
	 * @return:  void 
	 * @Date :   2017-03-01 14:17:02   
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
	/**
	 * <p>type</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-03-01 14:17:02    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>type</p>
	 * @author:  liushilei
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2017-03-01 14:17:02   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p>remark1</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-03-01 14:17:02    
	 */
	public String getRemark1() {
		return remark1;
	}
	
	/**
	 * <p>remark1</p>
	 * @author:  liushilei
	 * @param:   @param remark1
	 * @return:  void 
	 * @Date :   2017-03-01 14:17:02   
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	
	
	/**
	 * <p>remark2</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-03-01 14:17:02    
	 */
	public String getRemark2() {
		return remark2;
	}
	
	/**
	 * <p>remark2</p>
	 * @author:  liushilei
	 * @param:   @param remark2
	 * @return:  void 
	 * @Date :   2017-03-01 14:17:02   
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	
	
	/**
	 * <p>remark3</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-03-01 14:17:02    
	 */
	public String getRemark3() {
		return remark3;
	}
	
	/**
	 * <p>remark3</p>
	 * @author:  liushilei
	 * @param:   @param remark3
	 * @return:  void 
	 * @Date :   2017-03-01 14:17:02   
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	@Override
	public String toString() {
		return "AppDic{" +
				"id=" + id +
				", mkey='" + mkey + '\'' +
				", pkey='" + pkey + '\'' +
				", name='" + name + '\'' +
				", value='" + value + '\'' +
				", type='" + type + '\'' +
				", remark1='" + remark1 + '\'' +
				", remark2='" + remark2 + '\'' +
				", remark3='" + remark3 + '\'' +
				'}';
	}

	@Override
	public int compareTo (AppDic o) {
		return 0;
	}
}

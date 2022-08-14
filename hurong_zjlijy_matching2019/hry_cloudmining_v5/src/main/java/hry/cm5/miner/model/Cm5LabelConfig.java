/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:08:27 
 */
package hry.cm5.miner.model;


import hry.bean.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm5LabelConfig </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:08:27  
 */
@Table(name="cm5_label_config")
public class Cm5LabelConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "labelName")
	private String labelName;  //标签名称
	
	@Column(name= "labelKey")
	private String labelKey;  //标签key
	
	@Column(name= "labelImg")
	private String labelImg;  //标签图片
	
	@Column(name= "isUse")
	private Integer isUse;  //是否使用 0 未使用 1已使用
	
	@Column(name= "useTime")
	private Date useTime;  //使用时间
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:08:27    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-01-08 14:08:27   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>标签名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:08:27    
	 */
	public String getLabelName() {
		return labelName;
	}
	
	/**
	 * <p>标签名称</p>
	 * @author:  zhouming
	 * @param:   @param labelName
	 * @return:  void 
	 * @Date :   2020-01-08 14:08:27   
	 */
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	
	
	/**
	 * <p>标签key</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:08:27    
	 */
	public String getLabelKey() {
		return labelKey;
	}
	
	/**
	 * <p>标签key</p>
	 * @author:  zhouming
	 * @param:   @param labelKey
	 * @return:  void 
	 * @Date :   2020-01-08 14:08:27   
	 */
	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
	
	
	/**
	 * <p>标签图片</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:08:27    
	 */
	public String getLabelImg() {
		return labelImg;
	}
	
	/**
	 * <p>标签图片</p>
	 * @author:  zhouming
	 * @param:   @param labelImg
	 * @return:  void 
	 * @Date :   2020-01-08 14:08:27   
	 */
	public void setLabelImg(String labelImg) {
		this.labelImg = labelImg;
	}
	
	
	/**
	 * <p>是否使用 0 未使用 1已使用</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-01-08 14:08:27    
	 */
	public Integer getIsUse() {
		return isUse;
	}
	
	/**
	 * <p>是否使用 0 未使用 1已使用</p>
	 * @author:  zhouming
	 * @param:   @param isUse
	 * @return:  void 
	 * @Date :   2020-01-08 14:08:27   
	 */
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	
	
	/**
	 * <p>使用时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2020-01-08 14:08:27    
	 */
	public Date getUseTime() {
		return useTime;
	}
	
	/**
	 * <p>使用时间</p>
	 * @author:  zhouming
	 * @param:   @param useTime
	 * @return:  void 
	 * @Date :   2020-01-08 14:08:27   
	 */
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:08:27    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-01-08 14:08:27   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

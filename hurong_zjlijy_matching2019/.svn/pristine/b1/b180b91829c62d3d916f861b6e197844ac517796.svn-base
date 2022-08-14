/**
 * Copyright:   
 * @author:      songbing
 * @version:     V1.0 
 * @Date:        2019-07-29 14:42:17 
 */
package hry.otc.manage.remote.model.message;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> AppOtcTemplate </p>
 * @author:         songbing
 * @Date :          2019-07-29 14:42:17  
 */
@Table(name="app_otc_template")
public class AppOtcTemplate extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "tempName")
	private String tempName;  //模板名称
	
	@Column(name= "tempType")
	private Integer tempType;  //模板类型 1-买方模板，2-卖方模板, 3敏感词汇（如果是3,则tempState、tempLang、tempName不使用）
	
	@Column(name= "tempContent")
	private String tempContent;  //模板内容
	
	@Column(name= "tempState")
	private Integer tempState;  //模板状态 0-关闭，1-开启
	
	@Column(name= "tempLang")
	private String tempLang;  //模板语种
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  songbing
	 * @return:  Integer 
	 * @Date :   2019-07-29 14:42:17    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  songbing
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-29 14:42:17   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>模板名称</p>
	 * @author:  songbing
	 * @return:  String 
	 * @Date :   2019-07-29 14:42:17    
	 */
	public String getTempName() {
		return tempName;
	}
	
	/**
	 * <p>模板名称</p>
	 * @author:  songbing
	 * @param:   @param tempName
	 * @return:  void 
	 * @Date :   2019-07-29 14:42:17   
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	
	
	/**
	 * <p>模板类型 0-买方，1-卖方</p>
	 * @author:  songbing
	 * @return:  Integer 
	 * @Date :   2019-07-29 14:42:17    
	 */
	public Integer getTempType() {
		return tempType;
	}
	
	/**
	 * <p>模板类型 0-买方，1-卖方</p>
	 * @author:  songbing
	 * @param:   @param tempType
	 * @return:  void 
	 * @Date :   2019-07-29 14:42:17   
	 */
	public void setTempType(Integer tempType) {
		this.tempType = tempType;
	}
	
	
	/**
	 * <p>模板内容</p>
	 * @author:  songbing
	 * @return:  String 
	 * @Date :   2019-07-29 14:42:17    
	 */
	public String getTempContent() {
		return tempContent;
	}
	
	/**
	 * <p>模板内容</p>
	 * @author:  songbing
	 * @param:   @param tempContent
	 * @return:  void 
	 * @Date :   2019-07-29 14:42:17   
	 */
	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}
	
	
	/**
	 * <p>模板状态 0-关闭，1-开启</p>
	 * @author:  songbing
	 * @return:  Integer 
	 * @Date :   2019-07-29 14:42:17    
	 */
	public Integer getTempState() {
		return tempState;
	}
	
	/**
	 * <p>模板状态 0-关闭，1-开启</p>
	 * @author:  songbing
	 * @param:   @param tempState
	 * @return:  void 
	 * @Date :   2019-07-29 14:42:17   
	 */
	public void setTempState(Integer tempState) {
		this.tempState = tempState;
	}
	
	
	/**
	 * <p>模板语种</p>
	 * @author:  songbing
	 * @return:  String 
	 * @Date :   2019-07-29 14:42:17    
	 */
	public String getTempLang() {
		return tempLang;
	}
	
	/**
	 * <p>模板语种</p>
	 * @author:  songbing
	 * @param:   @param tempLang
	 * @return:  void 
	 * @Date :   2019-07-29 14:42:17   
	 */
	public void setTempLang(String tempLang) {
		this.tempLang = tempLang;
	}
	
	
	/**
	 * <p></p>
	 * @author:  songbing
	 * @return:  String 
	 * @Date :   2019-07-29 14:42:17    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  songbing
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-07-29 14:42:17   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

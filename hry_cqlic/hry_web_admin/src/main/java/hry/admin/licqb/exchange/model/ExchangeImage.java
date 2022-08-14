/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-12-17 16:38:08 
 */
package hry.admin.licqb.exchange.model;


import hry.bean.BaseModel;


import javax.persistence.*;

/**
 * <p> ExchangeImage </p>
 * @author:         zhouming
 * @Date :          2020-12-17 16:38:08  
 */
@Table(name="lc_exchange_image")
public class ExchangeImage extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "configId")
	private Long configId;  //兑换配置id
	
	@Column(name= "languagetype")
	private String languagetype;  //语种编码
	
	@Column(name= "image")
	private String image;  //图片路径

	@Column(name= "imageTwo")
	private String imageTwo; //图片路径2

	@Column(name= "imageThree")
	private String imageThree; //图片路径3

	@Column(name= "saasId")
	private String saasId;  //
	
	@Transient
	private String langName; //语种名称

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	public String getImageTwo() {
		return imageTwo;
	}

	public void setImageTwo(String imageTwo) {
		this.imageTwo = imageTwo;
	}

	public String getImageThree() {
		return imageThree;
	}

	public void setImageThree(String imageThree) {
		this.imageThree = imageThree;
	}

	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-12-17 16:38:08    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-12-17 16:38:08   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>兑换配置id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-12-17 16:38:08    
	 */
	public Long getConfigId() {
		return configId;
	}
	
	/**
	 * <p>兑换配置id</p>
	 * @author:  zhouming
	 * @param:   @param configId
	 * @return:  void 
	 * @Date :   2020-12-17 16:38:08   
	 */
	public void setConfigId(Long configId) {
		this.configId = configId;
	}
	
	
	/**
	 * <p>语种编码</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:38:08    
	 */
	public String getLanguagetype() {
		return languagetype;
	}
	
	/**
	 * <p>语种编码</p>
	 * @author:  zhouming
	 * @param:   @param languagetype
	 * @return:  void 
	 * @Date :   2020-12-17 16:38:08   
	 */
	public void setLanguagetype(String languagetype) {
		this.languagetype = languagetype;
	}
	
	
	/**
	 * <p>图片路径</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:38:08    
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * <p>图片路径</p>
	 * @author:  zhouming
	 * @param:   @param image
	 * @return:  void 
	 * @Date :   2020-12-17 16:38:08   
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-12-17 16:38:08    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-12-17 16:38:08   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}

/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-05-09 17:38:54 
 */
package hry.mall.retailstore.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> CutomerStore </p>
 * @author:         zhouming
 * @Date :          2019-05-09 17:38:54  
 */
@Table(name="shop_cutomer_store")
public class CutomerStore extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "memberId")
	private Long memberId;  //用户id
	
	@Column(name= "name")
	private String name;  //店铺名称
	
	@Column(name= "image")
	private String image;  //会员图标
	
	@Column(name= "description")
	private String description;  //说明


	@Column(name= "isLogin")
	private Integer isLogin;  //是否允许登录 0: 允许 1: 禁用


	public Integer getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Integer isLogin) {
		this.isLogin = isLogin;
	}
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:38:54    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:54   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-05-09 17:38:54    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  zhouming
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:54   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>店铺名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-09 17:38:54    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>店铺名称</p>
	 * @author:  zhouming
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:54   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>会员图标</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-09 17:38:54    
	 */
	public String getImage() {
		return image;
	}
	
	/**
	 * <p>会员图标</p>
	 * @author:  zhouming
	 * @param:   @param image
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:54   
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	
	/**
	 * <p>说明</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-05-09 17:38:54    
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * <p>说明</p>
	 * @author:  zhouming
	 * @param:   @param description
	 * @return:  void 
	 * @Date :   2019-05-09 17:38:54   
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}

/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-12-04 16:17:26 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;


import javax.persistence.*;
import java.util.List;

/**
 * <p> Transport </p>
 * @author:         kongdebiao
 * @Date :          2018-12-04 16:17:26  
 */
@Table(name="shop_transport")
public class Transport extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //运费模板ID
	
	@Column(name= "title")
	private String title;  //运费模板名称
	
	@Column(name= "isDel")
	private Integer isDel;  //是否删除0:未删除;1:已删除
	
	@Column(name= "isDefault")
	private Integer isDefault;  //是否默认模板1是0否

	@Column(name= "type")
	private Integer type;  //模板类型2卖家承担运费1买家承担运费

	@Transient

	private List<TransportExtend> transportExtendList;

	public List<TransportExtend> getTransportExtendList() {
		return transportExtendList;
	}

	public void setTransportExtendList(List<TransportExtend> transportExtendList) {
		this.transportExtendList = transportExtendList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * <p>运费模板ID</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-12-04 16:17:26    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>运费模板ID</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:26   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>运费模板名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-12-04 16:17:26    
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * <p>运费模板名称</p>
	 * @author:  kongdebiao
	 * @param:   @param title
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:26   
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * <p>是否删除0:未删除;1:已删除</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-12-04 16:17:26    
	 */
	public Integer getIsDel() {
		return isDel;
	}
	
	/**
	 * <p>是否删除0:未删除;1:已删除</p>
	 * @author:  kongdebiao
	 * @param:   @param isDel
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:26   
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	
	/**
	 * <p>是否默认模板1是2否</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-12-04 16:17:26    
	 */
	public Integer getIsDefault() {
		return isDefault;
	}
	
	/**
	 * <p>是否默认模板1是2否</p>
	 * @author:  kongdebiao
	 * @param:   @param isDefault
	 * @return:  void 
	 * @Date :   2018-12-04 16:17:26   
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	

}

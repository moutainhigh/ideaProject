/**
 * Copyright:   
 * @author:      kongdebiao
 * @version:     V1.0 
 * @Date:        2018-11-16 10:45:09 
 */
package hry.mall.goods.model;


import hry.bean.BaseModel;


import javax.persistence.*;
import java.util.List;

/**
 * <p> GoodsClass </p>
 * @author:         kongdebiao
 * @Date :          2018-11-16 10:45:09  
 */
@Table(name="shop_goods_class")
public class GoodsClass extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //索引ID
	
	@Column(name= "gcName")
	private String gcName;  //分类名称
	
	@Column(name= "gcPic")
	private String gcPic;  //分类图片
	
	@Column(name= "spName")
	private String spName;  //规格名称
	
	@Column(name= "gcParentId")
	private Long gcParentId;  //父ID
	
	@Column(name= "sort")
	private Integer sort;  //排序
	
	@Column(name= "gcShow")
	private Integer gcShow;  //前台显示，0为否，1为是，默认为1
	
	@Column(name= "gcDescription")
	private String gcDescription;  //描述
	
	@Column(name= "gcIdpath")
	private String gcIdpath;  //层级path
	
	@Transient
	private List<GoodsClass> childrenClass;//子分类

	public List<GoodsClass> getChildrenClass() {
		return childrenClass;
	}

	public void setChildrenClass(List<GoodsClass> childrenClass) {
		this.childrenClass = childrenClass;
	}

	/**
	 * <p>索引ID</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:45:09    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>索引ID</p>
	 * @author:  kongdebiao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:09   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>分类名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:45:09    
	 */
	public String getGcName() {
		return gcName;
	}
	
	/**
	 * <p>分类名称</p>
	 * @author:  kongdebiao
	 * @param:   @param gcName
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:09   
	 */
	public void setGcName(String gcName) {
		this.gcName = gcName;
	}
	
	
	/**
	 * <p>分类图片</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:45:09    
	 */
	public String getGcPic() {
		return gcPic;
	}
	
	/**
	 * <p>分类图片</p>
	 * @author:  kongdebiao
	 * @param:   @param gcPic
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:09   
	 */
	public void setGcPic(String gcPic) {
		this.gcPic = gcPic;
	}
	
	
	/**
	 * <p>规格名称</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:45:09    
	 */
	public String getSpName() {
		return spName;
	}
	
	/**
	 * <p>规格名称</p>
	 * @author:  kongdebiao
	 * @param:   @param spName
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:09   
	 */
	public void setSpName(String spName) {
		this.spName = spName;
	}
	
	
	/**
	 * <p>父ID</p>
	 * @author:  kongdebiao
	 * @return:  Long 
	 * @Date :   2018-11-16 10:45:09    
	 */
	public Long getGcParentId() {
		return gcParentId;
	}
	
	/**
	 * <p>父ID</p>
	 * @author:  kongdebiao
	 * @param:   @param gcParentId
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:09   
	 */
	public void setGcParentId(Long gcParentId) {
		this.gcParentId = gcParentId;
	}
	
	
	/**
	 * <p>排序</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:45:09    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>排序</p>
	 * @author:  kongdebiao
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:09   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p>前台显示，0为否，1为是，默认为1</p>
	 * @author:  kongdebiao
	 * @return:  Integer 
	 * @Date :   2018-11-16 10:45:09    
	 */
	public Integer getGcShow() {
		return gcShow;
	}
	
	/**
	 * <p>前台显示，0为否，1为是，默认为1</p>
	 * @author:  kongdebiao
	 * @param:   @param gcShow
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:09   
	 */
	public void setGcShow(Integer gcShow) {
		this.gcShow = gcShow;
	}
	
	
	/**
	 * <p>描述</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:45:09    
	 */
	public String getGcDescription() {
		return gcDescription;
	}
	
	/**
	 * <p>描述</p>
	 * @author:  kongdebiao
	 * @param:   @param gcDescription
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:09   
	 */
	public void setGcDescription(String gcDescription) {
		this.gcDescription = gcDescription;
	}
	
	
	/**
	 * <p>层级path</p>
	 * @author:  kongdebiao
	 * @return:  String 
	 * @Date :   2018-11-16 10:45:09    
	 */
	public String getGcIdpath() {
		return gcIdpath;
	}
	
	/**
	 * <p>层级path</p>
	 * @author:  kongdebiao
	 * @param:   @param gcIdpath
	 * @return:  void 
	 * @Date :   2018-11-16 10:45:09   
	 */
	public void setGcIdpath(String gcIdpath) {
		this.gcIdpath = gcIdpath;
	}
	
	

}

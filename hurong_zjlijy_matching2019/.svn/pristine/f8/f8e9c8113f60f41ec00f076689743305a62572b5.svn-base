/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-16 15:01:37 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Invoice </p>
 * @author:         luyue
 * @Date :          2018-11-16 15:01:37  
 */
@Table(name="shop_invoice")
public class Invoice extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "memberId")
	private Long memberId;  //前台账号id
	
	@Column(name= "type")
	private String type;  //发票类型
	
	@Column(name= "title")
	private String title;  //发票抬头
	
	@Column(name= "content")
	private String content;  //发票内容
	
	@Column(name= "cellphone")
	private String cellphone;  //手机号
	
	@Column(name= "taxnumber")
	private String taxnumber;  //纳税人识别号
	
	@Column(name= "isDefault")
	private Integer isDefault;  //是否默认，1是0否
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-16 15:01:37    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 15:01:37   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>前台账号id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-16 15:01:37    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>前台账号id</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2018-11-16 15:01:37   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>发票类型</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 15:01:37    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>发票类型</p>
	 * @author:  luyue
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-11-16 15:01:37   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p>发票抬头</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 15:01:37    
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * <p>发票抬头</p>
	 * @author:  luyue
	 * @param:   @param title
	 * @return:  void 
	 * @Date :   2018-11-16 15:01:37   
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * <p>发票内容</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 15:01:37    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>发票内容</p>
	 * @author:  luyue
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-11-16 15:01:37   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 15:01:37    
	 */
	public String getCellphone() {
		return cellphone;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @param:   @param cellphone
	 * @return:  void 
	 * @Date :   2018-11-16 15:01:37   
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
	/**
	 * <p>纳税人识别号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 15:01:37    
	 */
	public String getTaxnumber() {
		return taxnumber;
	}
	
	/**
	 * <p>纳税人识别号</p>
	 * @author:  luyue
	 * @param:   @param taxnumber
	 * @return:  void 
	 * @Date :   2018-11-16 15:01:37   
	 */
	public void setTaxnumber(String taxnumber) {
		this.taxnumber = taxnumber;
	}
	
	
	/**
	 * <p>是否默认，1是0否</p>
	 * @author:  luyue
	 * @return:  Integer 
	 * @Date :   2018-11-16 15:01:37    
	 */
	public Integer getIsDefault() {
		return isDefault;
	}
	
	/**
	 * <p>是否默认，1是0否</p>
	 * @author:  luyue
	 * @param:   @param isDefault
	 * @return:  void 
	 * @Date :   2018-11-16 15:01:37   
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	

}

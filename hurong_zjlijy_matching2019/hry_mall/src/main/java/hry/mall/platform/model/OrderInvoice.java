/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2018-11-16 15:10:14 
 */
package hry.mall.platform.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> OrderInvoice </p>
 * @author:         luyue
 * @Date :          2018-11-16 15:10:14  
 */
@Table(name="shop_order_invoice")
public class OrderInvoice extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "memberId")
	private Long memberId;  //前台账号id
	
	@Column(name= "orderId")
	private Long orderId;  //订单id
	
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
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-16 15:10:14    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  luyue
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-11-16 15:10:14   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>前台账号id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-16 15:10:14    
	 */
	public Long getMemberId() {
		return memberId;
	}
	
	/**
	 * <p>前台账号id</p>
	 * @author:  luyue
	 * @param:   @param memberId
	 * @return:  void 
	 * @Date :   2018-11-16 15:10:14   
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @return:  Long 
	 * @Date :   2018-11-16 15:10:14    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单id</p>
	 * @author:  luyue
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2018-11-16 15:10:14   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>发票类型</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 15:10:14    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>发票类型</p>
	 * @author:  luyue
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-11-16 15:10:14   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p>发票抬头</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 15:10:14    
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * <p>发票抬头</p>
	 * @author:  luyue
	 * @param:   @param title
	 * @return:  void 
	 * @Date :   2018-11-16 15:10:14   
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * <p>发票内容</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 15:10:14    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>发票内容</p>
	 * @author:  luyue
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-11-16 15:10:14   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 15:10:14    
	 */
	public String getCellphone() {
		return cellphone;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  luyue
	 * @param:   @param cellphone
	 * @return:  void 
	 * @Date :   2018-11-16 15:10:14   
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
	/**
	 * <p>纳税人识别号</p>
	 * @author:  luyue
	 * @return:  String 
	 * @Date :   2018-11-16 15:10:14    
	 */
	public String getTaxnumber() {
		return taxnumber;
	}
	
	/**
	 * <p>纳税人识别号</p>
	 * @author:  luyue
	 * @param:   @param taxnumber
	 * @return:  void 
	 * @Date :   2018-11-16 15:10:14   
	 */
	public void setTaxnumber(String taxnumber) {
		this.taxnumber = taxnumber;
	}
	
	

}

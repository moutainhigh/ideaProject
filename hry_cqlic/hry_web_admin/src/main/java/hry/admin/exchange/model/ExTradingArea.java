/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-10-23 11:12:53 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExTradingArea </p>
 * @author:         sunyujie
 * @Date :          2018-10-23 11:12:53  
 */
@Table(name="ex_tradingarea")
public class ExTradingArea extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "tradingArea")
	private String tradingArea;  // 交易区
	
	@Column(name= "struts")
	private Integer struts;  //
	
	@Column(name= "sort")
	private Integer sort;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-10-23 11:12:53    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-10-23 11:12:53   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-10-23 11:12:53    
	 */
	public String getTradingArea() {
		return tradingArea;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param tradingArea
	 * @return:  void 
	 * @Date :   2018-10-23 11:12:53   
	 */
	public void setTradingArea(String tradingArea) {
		this.tradingArea = tradingArea;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-10-23 11:12:53    
	 */
	public Integer getStruts() {
		return struts;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param struts
	 * @return:  void 
	 * @Date :   2018-10-23 11:12:53   
	 */
	public void setStruts(Integer struts) {
		this.struts = struts;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-10-23 11:12:53    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-10-23 11:12:53   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	

}

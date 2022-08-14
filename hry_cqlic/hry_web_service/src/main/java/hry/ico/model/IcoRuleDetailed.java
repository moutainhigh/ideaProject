/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-12 18:38:38 
 */
package hry.ico.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> IcoRuleDetailed </p>
 * @author:         lzy
 * @Date :          2019-01-12 18:38:38  
 */
@Table(name="ico_rule_detailed")
public class IcoRuleDetailed extends BaseModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "periods")
	private String periods;  //{name:'期数'}
	
	@Column(name= "coinCode")
	private String coinCode;  //{name:'币种code'}
	
	@Column(name= "totalNum")
	private BigDecimal totalNum;  //
	
	@Column(name= "saleNum")
	private BigDecimal saleNum;  //{name:'可售数量'}
	
	@Column(name= "saleSurplusNum")
	private BigDecimal saleSurplusNum;  //可售剩余数量
	
	@Column(name= "state")
	private String state;  //{name:'状态  0未发行  1已发行'}
	
	
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-12 18:38:38    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-01-12 18:38:38   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>{name:'期数'}</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-12 18:38:38    
	 */
	public String getPeriods() {
		return periods;
	}
	
	/**
	 * <p>{name:'期数'}</p>
	 * @author:  lzy
	 * @param:   @param periods
	 * @return:  void 
	 * @Date :   2019-01-12 18:38:38   
	 */
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	
	
	/**
	 * <p>{name:'币种code'}</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-12 18:38:38    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>{name:'币种code'}</p>
	 * @author:  lzy
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-01-12 18:38:38   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-12 18:38:38    
	 */
	public BigDecimal getTotalNum() {
		return totalNum;
	}
	
	/**
	 * <p></p>
	 * @author:  lzy
	 * @param:   @param totalNum
	 * @return:  void 
	 * @Date :   2019-01-12 18:38:38   
	 */
	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}
	
	
	/**
	 * <p>{name:'可售数量'}</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-12 18:38:38    
	 */
	public BigDecimal getSaleNum() {
		return saleNum;
	}
	
	/**
	 * <p>{name:'可售数量'}</p>
	 * @author:  lzy
	 * @param:   @param saleNum
	 * @return:  void 
	 * @Date :   2019-01-12 18:38:38   
	 */
	public void setSaleNum(BigDecimal saleNum) {
		this.saleNum = saleNum;
	}
	
	
	/**
	 * <p>可售剩余数量</p>
	 * @author:  lzy
	 * @return:  BigDecimal 
	 * @Date :   2019-01-12 18:38:38    
	 */
	public BigDecimal getSaleSurplusNum() {
		return saleSurplusNum;
	}
	
	/**
	 * <p>可售剩余数量</p>
	 * @author:  lzy
	 * @param:   @param saleSurplusNum
	 * @return:  void 
	 * @Date :   2019-01-12 18:38:38   
	 */
	public void setSaleSurplusNum(BigDecimal saleSurplusNum) {
		this.saleSurplusNum = saleSurplusNum;
	}
	
	
	/**
	 * <p>{name:'状态  0未发行  1已发行'}</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-12 18:38:38    
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * <p>{name:'状态  0未发行  1已发行'}</p>
	 * @author:  lzy
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2019-01-12 18:38:38   
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	

}

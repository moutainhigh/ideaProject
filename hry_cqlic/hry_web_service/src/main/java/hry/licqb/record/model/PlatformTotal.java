/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-04-02 18:11:17 
 */
package hry.licqb.record.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> PlatformTotal </p>
 * @author:         zhouming
 * @Date :          2020-04-02 18:11:17  
 */
@Table(name="lc_platform_total")
public class PlatformTotal extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代码
	
	@Column(name= "todayAddNum")
	private BigDecimal todayAddNum;  //今日新增量
	
	@Column(name= "ayerAllNum")
	private BigDecimal ayerAllNum;  //昨日总量
	
	@Column(name= "ayerConvertNum")
	private BigDecimal ayerConvertNum;  //昨日兑换量
	
	@Column(name= "ayerSurplusNum")
	private BigDecimal ayerSurplusNum;  //昨日剩余量（昨日总量-昨日已兑换量）
	
	@Column(name= "todayAllNum")
	private BigDecimal todayAllNum;  //今日总量（今日新增+昨日剩余量）
	
	
	
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-04-02 18:11:17    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-04-02 18:11:17   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-04-02 18:11:17    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  zhouming
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2020-04-02 18:11:17   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>今日新增量</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-04-02 18:11:17    
	 */
	public BigDecimal getTodayAddNum() {
		return todayAddNum;
	}
	
	/**
	 * <p>今日新增量</p>
	 * @author:  zhouming
	 * @param:   @param todayAddNum
	 * @return:  void 
	 * @Date :   2020-04-02 18:11:17   
	 */
	public void setTodayAddNum(BigDecimal todayAddNum) {
		this.todayAddNum = todayAddNum;
	}
	
	
	/**
	 * <p>昨日总量</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-04-02 18:11:17    
	 */
	public BigDecimal getAyerAllNum() {
		return ayerAllNum;
	}
	
	/**
	 * <p>昨日总量</p>
	 * @author:  zhouming
	 * @param:   @param ayerAllNum
	 * @return:  void 
	 * @Date :   2020-04-02 18:11:17   
	 */
	public void setAyerAllNum(BigDecimal ayerAllNum) {
		this.ayerAllNum = ayerAllNum;
	}
	
	
	/**
	 * <p>昨日兑换量</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-04-02 18:11:17    
	 */
	public BigDecimal getAyerConvertNum() {
		return ayerConvertNum;
	}
	
	/**
	 * <p>昨日兑换量</p>
	 * @author:  zhouming
	 * @param:   @param ayerConvertNum
	 * @return:  void 
	 * @Date :   2020-04-02 18:11:17   
	 */
	public void setAyerConvertNum(BigDecimal ayerConvertNum) {
		this.ayerConvertNum = ayerConvertNum;
	}
	
	
	/**
	 * <p>昨日剩余量（昨日总量-昨日已兑换量）</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-04-02 18:11:17    
	 */
	public BigDecimal getAyerSurplusNum() {
		return ayerSurplusNum;
	}
	
	/**
	 * <p>昨日剩余量（昨日总量-昨日已兑换量）</p>
	 * @author:  zhouming
	 * @param:   @param ayerSurplusNum
	 * @return:  void 
	 * @Date :   2020-04-02 18:11:17   
	 */
	public void setAyerSurplusNum(BigDecimal ayerSurplusNum) {
		this.ayerSurplusNum = ayerSurplusNum;
	}
	
	
	/**
	 * <p>今日总量（今日新增+昨日剩余量）</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-04-02 18:11:17    
	 */
	public BigDecimal getTodayAllNum() {
		return todayAllNum;
	}
	
	/**
	 * <p>今日总量（今日新增+昨日剩余量）</p>
	 * @author:  zhouming
	 * @param:   @param todayAllNum
	 * @return:  void 
	 * @Date :   2020-04-02 18:11:17   
	 */
	public void setTodayAllNum(BigDecimal todayAllNum) {
		this.todayAllNum = todayAllNum;
	}
	
	

}

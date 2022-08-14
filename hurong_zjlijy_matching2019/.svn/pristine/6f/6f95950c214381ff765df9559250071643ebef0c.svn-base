/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:21:31 
 */
package hry.cm2.dividend.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm2DividendConfig </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:21:31  
 */
@Table(name="cm2_dividend_config")
public class Cm2DividendConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "ratio")
	private String ratio;  //分红比例
	
	@Column(name= "feeType")
	private String feeType;  //手续费参与类型 1.OTC   2.提币  3.币币交易 4: c2c
	
	@Column(name= "type")
	private Integer type;  //分红类型 1：果树领取 2：自动发放
	
	@Column(name= "remark")
	private String remark;  //说明
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-10-14 16:21:31    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-10-14 16:21:31   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>分红比例</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-14 16:21:31    
	 */
	public String getRatio() {
		return ratio;
	}
	
	/**
	 * <p>分红比例</p>
	 * @author:  yaozh
	 * @param:   @param ratio
	 * @return:  void 
	 * @Date :   2019-10-14 16:21:31   
	 */
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	
	
	/**
	 * <p>手续费参与类型 1.OTC   2.提币  3.币币交易 4: c2c</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-14 16:21:31    
	 */
	public String getFeeType() {
		return feeType;
	}
	
	/**
	 * <p>手续费参与类型 1.OTC   2.提币  3.币币交易 4: c2c</p>
	 * @author:  yaozh
	 * @param:   @param feeType
	 * @return:  void 
	 * @Date :   2019-10-14 16:21:31   
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	
	
	/**
	 * <p>分红类型 1：果树领取 2：自动发放</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-10-14 16:21:31    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>分红类型 1：果树领取 2：自动发放</p>
	 * @author:  yaozh
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-10-14 16:21:31   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>说明</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-10-14 16:21:31    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>说明</p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-10-14 16:21:31   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

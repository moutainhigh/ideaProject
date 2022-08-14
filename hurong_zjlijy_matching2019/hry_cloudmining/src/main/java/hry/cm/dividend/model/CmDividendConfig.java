/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-01 13:39:10 
 */
package hry.cm.dividend.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> CmDividendConfig </p>
 * @author:         zhouming
 * @Date :          2019-08-01 13:39:10  
 */
@Table(name="cm_dividend_config")
public class CmDividendConfig extends BaseModel {
	
	
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

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	/**
	 * <p>id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2019-08-01 13:39:10    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-08-01 13:39:10   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>分红比例</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-01 13:39:10    
	 */
	public String getRatio() {
		return ratio;
	}
	
	/**
	 * <p>分红比例</p>
	 * @author:  zhouming
	 * @param:   @param ratio
	 * @return:  void 
	 * @Date :   2019-08-01 13:39:10   
	 */
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	/**
	 * <p>分红类型 1：果树领取 2：自动发放</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2019-08-01 13:39:10    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>分红类型 1：果树领取 2：自动发放</p>
	 * @author:  zhouming
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-08-01 13:39:10   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>说明</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2019-08-01 13:39:10    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>说明</p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-08-01 13:39:10   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}

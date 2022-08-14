/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-08-22 09:57:59 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p> ExLawcoin </p>
 * @author:         tianpengyu
 * @Date :          2018-08-22 09:57:59  
 */
@Table(name="ex_lawcoin")
public class ExLawcoin extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "name")
	private String name;  //
	
	@Column(name= "coinSymbol")
	private String coinSymbol;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //

	@Column(name= "creator")
	private String creator;  //
	
	@Column(name= "coinDecimal")
	private Integer coinDecimal;  //

	@Column(name= "created")
	private Date created;

	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-08-22 09:57:59    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-08-22 09:57:59   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-22 09:57:59    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-08-22 09:57:59   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-22 09:57:59    
	 */
	public String getCoinSymbol() {
		return coinSymbol;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinSymbol
	 * @return:  void 
	 * @Date :   2018-08-22 09:57:59   
	 */
	public void setCoinSymbol(String coinSymbol) {
		this.coinSymbol = coinSymbol;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-08-22 09:57:59    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-08-22 09:57:59   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-08-22 09:57:59    
	 */
	public Integer getCoinDecimal() {
		return coinDecimal;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinDecimal
	 * @return:  void 
	 * @Date :   2018-08-22 09:57:59   
	 */
	public void setCoinDecimal(Integer coinDecimal) {
		this.coinDecimal = coinDecimal;
	}
	
	

}

/**
 * Copyright:
 * @author:      hec
 * @version:     V1.0
 * @Date:        2018-12-27 16:37:41
 */
package hry.admin.contract.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> ContractScheme </p>
 * @author:         hec
 * @Date :          2018-12-27 16:37:41  
 */
@Table(name="contract_scheme")
public class ContractScheme extends BaseModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name= "name")
	private String name;  //合约名称

	@Column(name= "cycle")
	private Integer cycle;  //周期 1当周 2次周 3季度

	@Column(name= "multiple")
	private String multiple;  //倍数

	@Column(name= "calculateWeek")
	private Integer calculateWeek;  //清算/交割时间  星期几

	@Column(name= "calculateTime")
	private String calculateTime;  //具体 清算/交割 时间




	/**
	 * <p></p>
	 * @author:  hec
	 * @return:  Long
	 * @Date :   2018-12-27 16:37:41
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p></p>
	 * @author:  hec
	 * @param:   @param id
	 * @return:  void
	 * @Date :   2018-12-27 16:37:41
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p>合约名称</p>
	 * @author:  hec
	 * @return:  String
	 * @Date :   2018-12-27 16:37:41
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>合约名称</p>
	 * @author:  hec
	 * @param:   @param name
	 * @return:  void
	 * @Date :   2018-12-27 16:37:41
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * <p>周期 1当周 2次周 3季度</p>
	 * @author:  hec
	 * @return:  Integer
	 * @Date :   2018-12-27 16:37:41
	 */
	public Integer getCycle() {
		return cycle;
	}

	/**
	 * <p>周期 1当周 2次周 3季度</p>
	 * @author:  hec
	 * @param:   @param cycle
	 * @return:  void
	 * @Date :   2018-12-27 16:37:41
	 */
	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	/**
	 * <p>清算/交割时间  星期几</p>
	 * @author:  hec
	 * @return:  Integer
	 * @Date :   2018-12-27 16:37:41
	 */
	public Integer getCalculateWeek() {
		return calculateWeek;
	}

	/**
	 * <p>清算/交割时间  星期几</p>
	 * @author:  hec
	 * @param:   @param calculateWeek
	 * @return:  void
	 * @Date :   2018-12-27 16:37:41
	 */
	public void setCalculateWeek(Integer calculateWeek) {
		this.calculateWeek = calculateWeek;
	}


    public String getCalculateTime() {
        return calculateTime;
    }

    public void setCalculateTime(String calculateTime) {
        this.calculateTime = calculateTime;
    }
}

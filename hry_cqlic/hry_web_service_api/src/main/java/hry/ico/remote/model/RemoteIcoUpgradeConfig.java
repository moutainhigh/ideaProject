/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-12 17:58:41 
 */
package hry.ico.remote.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> IcoUpgradeConfig </p>
 * @author:         lzy
 * @Date :          2019-01-12 17:58:41  
 */
public class RemoteIcoUpgradeConfig extends BaseModel {

	private String gradeName;  //等级名称
	
	private Integer upConditions;  //升级条件:1.大于等于
	
	private Long conditionPara;  //条件参数
	
	private String additionRatio;  //加成比例
	

	/**
	 * <p>等级名称</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-12 17:58:41    
	 */
	public String getGradeName() {
		return gradeName;
	}
	
	/**
	 * <p>等级名称</p>
	 * @author:  lzy
	 * @param:   @param gradeName
	 * @return:  void 
	 * @Date :   2019-01-12 17:58:41   
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
	/**
	 * <p>升级条件:1.大于等于</p>
	 * @author:  lzy
	 * @return:  Integer 
	 * @Date :   2019-01-12 17:58:41    
	 */
	public Integer getUpConditions() {
		return upConditions;
	}
	
	/**
	 * <p>升级条件:1.大于等于</p>
	 * @author:  lzy
	 * @param:   @param upConditions
	 * @return:  void 
	 * @Date :   2019-01-12 17:58:41   
	 */
	public void setUpConditions(Integer upConditions) {
		this.upConditions = upConditions;
	}
	
	
	/**
	 * <p>条件参数</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-12 17:58:41    
	 */
	public Long getConditionPara() {
		return conditionPara;
	}
	
	/**
	 * <p>条件参数</p>
	 * @author:  lzy
	 * @param:   @param conditionPara
	 * @return:  void 
	 * @Date :   2019-01-12 17:58:41   
	 */
	public void setConditionPara(Long conditionPara) {
		this.conditionPara = conditionPara;
	}
	
	
	/**
	 * <p>加成比例</p>
	 * @author:  lzy
	 * @return:  String 
	 * @Date :   2019-01-12 17:58:41    
	 */
	public String getAdditionRatio() {
		return additionRatio;
	}
	
	/**
	 * <p>加成比例</p>
	 * @author:  lzy
	 * @param:   @param additionRatio
	 * @return:  void 
	 * @Date :   2019-01-12 17:58:41   
	 */
	public void setAdditionRatio(String additionRatio) {
		this.additionRatio = additionRatio;
	}
	
	

}

/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年10月27日  17:57:57
 */
package hry.web.dictionary.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Gao Mimi
 * @Date : 2015年10月27日 17:57:57
 */

@SuppressWarnings("serial")
@Table(name = "app_dic_multilevel")
public class AppDicMultilevel extends AppDicBase {
	
	
	@Column(name="path")
	protected String path;
	@Column(name = "rootKey")
	protected String rootKey;
    @Column(name="remark1")
 	protected String remark1;
    
    // 如果是银行 此字段表示银行log
    @Column(name="remark2")
 	protected String remark2;
    @Column(name="remark3")
 	protected String remark3;
    @Column(name="remark4")
 	protected String remark4;

    
    
    
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getPath() {
		return path;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRootKey() {
		return rootKey;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRootKey(String rootKey) {
		this.rootKey = rootKey;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark1() {
		return remark1;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark2() {
		return remark2;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark3() {
		return remark3;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark4() {
		return remark4;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}
	
  




}

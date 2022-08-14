/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月3日 下午11:04:29
 */
package hry.customer.agents.model.vo;

import java.io.Serializable;

/**
 * 
 * 查询代理商的子用户的详细 信息 
 * 
 * @author Wu shuiming
 * @date 2016年9月3日 下午11:04:29
 */
public class CustromerInfo implements Serializable{
	
	private String trueName;
	private String userName;
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}

/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月1日 下午2:07:26
 */
package hry.customer.agents.model.vo;

import hry.customer.person.model.AppPersonInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Wu shuiming
 * @date 2016年8月1日 下午2:07:26
 */
@SuppressWarnings("serial")
public class CustomerInfoForAgents implements Serializable{
	
	public List<AppPersonInfo> oneList;
	public List<AppPersonInfo> twoList;
	public List<AppPersonInfo> threeList;
	public List<AppPersonInfo> getOneList() {
		return oneList;
	}
	public void setOneList(List<AppPersonInfo> oneList) {
		this.oneList = oneList;
	}
	public List<AppPersonInfo> getTwoList() {
		return twoList;
	}
	public void setTwoList(List<AppPersonInfo> twoList) {
		this.twoList = twoList;
	}
	public List<AppPersonInfo> getThreeList() {
		return threeList;
	}
	public void setThreeList(List<AppPersonInfo> threeList) {
		this.threeList = threeList;
	}
	public CustomerInfoForAgents(List<AppPersonInfo> oneList,
			List<AppPersonInfo> twoList, List<AppPersonInfo> threeList) {
		super();
		this.oneList = oneList;
		this.twoList = twoList;
		this.threeList = threeList;
	}
	public CustomerInfoForAgents() {
		super();
	}
	
	
	
	
}

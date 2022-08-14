/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年7月16日 下午1:44:42
 */
package hry.customer.agents.model.vo;

import java.io.Serializable;

/**
 * @author Wu shuiming
 * @date 2016年7月16日 下午1:44:42
 * 
 * 此类是将统计某个用户的一级二级 三级推荐人的数量
 * 
 * 
 */


@SuppressWarnings("serial")
public class CustromerToAgentsNum implements Serializable {
	
	public Integer oneAgentsNum;
	public Integer twoAgentsNum;
	public Integer threeAgentsNum;
	
	public Integer getOneAgentsNum() {
		return oneAgentsNum;
	}
	public void setOneAgentsNum(Integer oneAgentsNum) {
		this.oneAgentsNum = oneAgentsNum;
	}
	public Integer getTwoAgentsNum() {
		return twoAgentsNum;
	}
	public void setTwoAgentsNum(Integer twoAgentsNum) {
		this.twoAgentsNum = twoAgentsNum;
	}
	public Integer getThreeAgentsNum() {
		return threeAgentsNum;
	}
	public void setThreeAgentsNum(Integer threeAgentsNum) {
		this.threeAgentsNum = threeAgentsNum;
	}
	public CustromerToAgentsNum(Integer oneAgentsNum, Integer twoAgentsNum,
			Integer threeAgentsNum) {
		super();
		this.oneAgentsNum = oneAgentsNum;
		this.twoAgentsNum = twoAgentsNum;
		this.threeAgentsNum = threeAgentsNum;
	}
	public CustromerToAgentsNum() {
		super();
	}
	

	
 }

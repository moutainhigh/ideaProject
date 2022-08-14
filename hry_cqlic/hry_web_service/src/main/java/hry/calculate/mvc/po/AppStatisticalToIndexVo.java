/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月12日 下午1:58:52
 */
package hry.calculate.mvc.po;

import java.io.Serializable;

/**
 * 后台首页用户统计图里用户的数量
 * 
 * @author Wu shuiming
 * @date 2016年9月12日 下午1:58:52
 */
@SuppressWarnings("serial")
public class AppStatisticalToIndexVo implements Serializable{
	
	private Integer countToRegist;  // 仅注册的数量  
	private Integer countToReal; // 仅实名的数量 包括仅注册的数量  
	private Integer countToPost; // 仅充值的数量  包括仅实名
	private Integer countToClinch; // 已成交   
	public Integer getCountToRegist() {
		return countToRegist;
	}
	public void setCountToRegist(Integer countToRegist) {
		this.countToRegist = countToRegist;
	}
	public Integer getCountToReal() {
		return countToReal;
	}
	public void setCountToReal(Integer countToReal) {
		this.countToReal = countToReal;
	}
	public Integer getCountToPost() {
		return countToPost;
	}
	public void setCountToPost(Integer countToPost) {
		this.countToPost = countToPost;
	}
	public Integer getCountToClinch() {
		return countToClinch;
	}
	public void setCountToClinch(Integer countToClinch) {
		this.countToClinch = countToClinch;
	}
	
	

}

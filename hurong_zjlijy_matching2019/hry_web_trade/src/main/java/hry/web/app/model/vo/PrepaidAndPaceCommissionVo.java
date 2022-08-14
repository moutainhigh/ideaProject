/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年7月28日 下午4:56:41
 */
package hry.web.app.model.vo;

import java.io.Serializable;

/**
 * @author Wu shuiming
 * @date 2016年7月28日 下午4:56:41
 */
@SuppressWarnings("serial")                      
public class PrepaidAndPaceCommissionVo implements Serializable {
	
	// 充值手续费
	public String prepaidCommission;
	// 提现手续费 
	public String paceCommission;
	
	public String getPrepaidCommission() {
		return prepaidCommission;
	}
	public void setPrepaidCommission(String prepaidCommission) {
		this.prepaidCommission = prepaidCommission;
	}
	public String getPaceCommission() {
		return paceCommission;
	}
	public void setPaceCommission(String paceCommission) {
		this.paceCommission = paceCommission;
	}
	
	
	
}

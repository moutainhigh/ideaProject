/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.change.remote.exEntrust;
/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */

public interface  RemoteExOrderInfoServiceNoTr {
	/**
	 * 
	 * <p> </p>
	 * @author:         Gao Mimi
	 * @param:    @param exEntrust
	 * @return: void 
	 * @Date :          2016年4月15日 下午2:56:57   
	 * @throws:
	 * 
	 *买家:限价（1000元，1个）市价（1500元）
	 *卖家:限价（1000元，1个）市价（2个）
	 *

	 */
	public void matchExtrustToOrder(String id);

}

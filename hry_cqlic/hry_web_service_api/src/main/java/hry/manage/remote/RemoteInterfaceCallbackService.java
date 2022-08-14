package hry.manage.remote;

import hry.manage.remote.model.LmcTransfer;

/**
 * 第三方回调接口
 * <p> TODO</p>
 * @author:         Shangxl 
 * @Date :          2017年7月31日 上午10:31:41
 */
public interface RemoteInterfaceCallbackService {
	/**
	 * lmc转账回调
	 * <p> TODO</p>
	 * @author:         Shangxl
	 * @param:    @param transfer
	 * @param:    @return
	 * @return: String 
	 * @Date :          2017年7月31日 上午10:36:36   
	 * @throws:
	 */
	public String lmcTransferCallBack(LmcTransfer transfer);
}

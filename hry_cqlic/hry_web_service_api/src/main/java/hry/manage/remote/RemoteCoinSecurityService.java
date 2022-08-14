package hry.manage.remote;

import hry.manage.remote.model.RemoteResult;

/**
 * RemoteCoinSecurityService.java
 * @author denghf
 * 2018年5月10日 下午7:48:34
 */
public interface RemoteCoinSecurityService {

	/**
	 * 登录
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public RemoteResult loginCoinSecurity(String userName, String passWord);
}

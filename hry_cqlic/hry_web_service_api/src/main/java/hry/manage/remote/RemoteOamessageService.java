package hry.manage.remote;

import java.util.Map;

import hry.bean.JsonResult;
import hry.manage.remote.model.Oamessage;
import hry.manage.remote.model.base.FrontPage;

/**
 * 站内信消息接口
 * @author tzw
 *
 * 2017年7月19日
 */
public interface RemoteOamessageService {

	/**
	 * 获取当前登录用户所有站内信
	 * @param params
	 * @return
	 * 2017年7月19日
	 * tzw
	 */
	FrontPage findOamessage(Map<String, String> params);

	/**
	 * 根据id获取站内信并且设置已读
	 * @param sid
	 * @return
	 * 2017年7月21日
	 * tzw
	 */
	Oamessage read(Long sid);

	/**
	 * 根据信件id集合批量处理站内信
	 * */
	JsonResult update(Long customerId, String type, String ids);

	/**
	 * 查看用户未读信件总数
	 * */
	JsonResult count(Long customerId);

	/**
	 * 根据id获取消息
	 * (有用户id以及用户名，用户操作时校验是否本人操作)
	 * @param sid
	 * @return
	 * 2017年7月21日
	 * tzw
	 */
	Oamessage get(Long sid);

}

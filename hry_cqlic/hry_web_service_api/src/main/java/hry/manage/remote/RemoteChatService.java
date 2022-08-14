package hry.manage.remote;

import hry.manage.remote.model.RemoteResult;

import java.util.List;
import java.util.Map;

public interface RemoteChatService {

	/**
	 * 同步数据库 是否管理员和禁言
	 * @param id
	 * @param type
	 * @return
	 */
	RemoteResult isAdmin(Long id, String type);

	/**
	 * 获取近三天的聊天记录
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> get3daychat(Long id);

	/**
	 * 禁言时间到，解除禁言
	 * @param customerId
	 */
	void releaseGag(Long customerId);

	/**
	 * 敏感词查询
	 * @return
	 */
	RemoteResult sensitiveWords();

	/**
	 * 存储聊天记录
	 * @param id
	 * @param username
	 * @param content
	 * @return
	 */
	void saveChatRecord(Long id, String username, String content);

}

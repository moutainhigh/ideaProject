package hry.otc.manage.remote.api;

import hry.manage.remote.model.RemoteResult;
import hry.otc.manage.remote.model.OtcChatMessageRemote;
import hry.otc.manage.remote.model.message.AppOtcTemplate;
import hry.otc.manage.remote.model.message.OtcChatMessage;
import hry.util.QueryFilter;

import java.util.List;

public interface RemoteOtcChatMessageService {

	/**
	 * 存储聊天记录
	 * @param otcChatMessage
	 * @return
	 */
	RemoteResult saveOtcChatMessageRecord(OtcChatMessageRemote otcChatMessage);

	RemoteResult initChatData(Long id, Long customerId);

	List<OtcChatMessage> getNoReadRecords(Long id, Long customerId);

	RemoteResult readRecords(Long id, Long customerId);

	List<AppOtcTemplate> findOtcTemplate(String type, String lang);
}

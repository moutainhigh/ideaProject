/**
 * Copyright:   
 * @author:      xubb
 * @version:     V1.0 
 * @Date:        2019-07-03 10:07:13 
 */
package hry.app.chatmessage.service.impl;

import hry.app.chatmessage.service.OtcChatMessageService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.manage.remote.model.message.OtcChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> OtcChatMessageService </p>
 * @author:         xubb
 * @Date :          2019-07-03 10:07:13  
 */
@Service("otcChatMessageService")
public class OtcChatMessageServiceImpl extends BaseServiceImpl<OtcChatMessage, Long> implements OtcChatMessageService {
	
	@Resource(name="otcChatMessageDao")
	@Override
	public void setDao(BaseDao<OtcChatMessage, Long> dao) {
		super.dao = dao;
	}
	

}

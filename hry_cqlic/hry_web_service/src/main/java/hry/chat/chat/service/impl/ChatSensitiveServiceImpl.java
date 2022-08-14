/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-27 18:13:22 
 */
package hry.chat.chat.service.impl;

import hry.chat.chat.model.ChatSensitive;
import hry.chat.chat.service.ChatSensitiveService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ChatSensitiveService </p>
 * @author:         sunyujie
 * @Date :          2018-04-27 18:13:22  
 */
@Service("chatSensitiveService")
public class ChatSensitiveServiceImpl extends BaseServiceImpl<ChatSensitive, Long> implements ChatSensitiveService{
	
	@Resource(name="chatSensitiveDao")
	@Override
	public void setDao(BaseDao<ChatSensitive, Long> dao) {
		super.dao = dao;
	}
	

}

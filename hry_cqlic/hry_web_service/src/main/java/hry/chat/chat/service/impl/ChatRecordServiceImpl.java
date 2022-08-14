/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-27 18:12:15 
 */
package hry.chat.chat.service.impl;

import hry.chat.chat.dao.ChatRecordDao;
import hry.chat.chat.model.ChatRecord;
import hry.chat.chat.service.ChatRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> ChatRecordService </p>
 * @author:         sunyujie
 * @Date :          2018-04-27 18:12:15  
 */
@Service("chatRecordService")
public class ChatRecordServiceImpl extends BaseServiceImpl<ChatRecord, Long> implements ChatRecordService{
	
	@Resource(name="chatRecordDao")
	@Override
	public void setDao(BaseDao<ChatRecord, Long> dao) {
		super.dao = dao;
	}
	public List<ChatRecord> get3daychat(Long id){
		return ((ChatRecordDao)dao).get3daychat(id);
	}

}

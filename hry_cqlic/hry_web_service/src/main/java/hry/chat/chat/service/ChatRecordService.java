/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-27 18:12:15 
 */
package hry.chat.chat.service;

import hry.core.mvc.service.base.BaseService;

import java.util.List;

import hry.chat.chat.model.ChatRecord;



/**
 * <p> ChatRecordService </p>
 * @author:         sunyujie
 * @Date :          2018-04-27 18:12:15  
 */
public interface ChatRecordService  extends BaseService<ChatRecord, Long>{

	List<ChatRecord> get3daychat(Long id);
}

/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-27 18:12:15 
 */
package hry.chat.chat.dao;

import hry.core.mvc.dao.base.BaseDao;

import java.util.List;

import hry.chat.chat.model.ChatRecord;
import org.apache.ibatis.annotations.Param;


/**
 * <p> ChatRecordDao </p>
 * @author:         sunyujie
 * @Date :          2018-04-27 18:12:15  
 */
public interface ChatRecordDao extends  BaseDao<ChatRecord, Long> {

	List<ChatRecord> get3daychat(@Param("id") Long id);

}

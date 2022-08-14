/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:21:54 
 */
package hry.admin.web.dao;

import hry.admin.web.model.AppMessage;
import hry.admin.web.model.MessageAsCustomer;
import hry.admin.web.model.MessageListVo;
import hry.core.mvc.dao.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> AppMessageDao </p>
 * @author:         liuchenghui
 * @Date :          2018-07-05 10:21:54  
 */
public interface AppMessageDao extends  BaseDao<AppMessage, Long> {

    List<MessageListVo> selectMessageVo (Map<String, Object> map);

    List<MessageAsCustomer> selectMessageAsCustromer (@Param("messageId") Long messageId);
}

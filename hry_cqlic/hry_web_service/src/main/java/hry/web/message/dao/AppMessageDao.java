/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2015年09月28日  18:10:04
 */
package hry.web.message.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.web.app.model.AppMessage;
import hry.web.app.model.MessageAsCustomer;
import hry.web.app.model.vo.MessageAndCustromerVo;
import hry.web.app.model.vo.MessageListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:  Wu Shuiming       
 * @Date :   2015年09月28日  18:10:04     
 */
public interface AppMessageDao extends BaseDao<AppMessage,Long>{
	
	// 查询所有发送或者未发送的消息 以及对应的用户信息
	public List<MessageListVo> findMessageListVoList(@Param(value = "state") int state);
	
	//根据消息id查询指定状态下的所有用户信息
	public List<MessageListVo> findMessageListVo(@Param("state") int state,@Param("messageId") Long messageId);
	
	/**
	 *  根据消息的状态以及用户的id查询所有的消息
	 * <p> TODO</p>
	 * @author:         Wu Shuiming 
	 * @param:    @param isSend   表示消息已发送(建议传1)1 表示已发送  0 表示未发送
	 * @param:    @param customerId  用户id  
	 * @param:    @param state   消息的状态 表示 已读或未读  1 表示未读 0 表示已读 
	 * @param:    @return
	 * @return: List<AppMessage> 
	 * @Date :          2016年6月12日 下午3:30:38   
	 * @throws:
	 */
	public List<MessageAndCustromerVo> findCustumerById(@Param(value = "isSend") int isSend,
                                                        @Param(value = "customerId") Long customerId, @Param(value = "state") int state);
	
	
	/**
	 * 查询出一对多中的一对象
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月17日 下午6:52:00
	 */
	public List<MessageListVo> selectMessageVo(Map<String, Object> map);
	
	/**
	 * 根据消息id 查询所有的用户
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月17日 下午7:02:38
	 */
	public List<MessageAsCustomer> selectMessageAsCustromer(@Param(value = "messageId") Long messageId);
	
	
}
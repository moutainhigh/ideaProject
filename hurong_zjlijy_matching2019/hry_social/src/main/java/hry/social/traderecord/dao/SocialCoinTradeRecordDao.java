/**
 * Copyright:    
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-28 11:56:37 
 */
package hry.social.traderecord.dao;


import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.traderecord.SocialCoinTradeRecord;

import java.util.List;
import java.util.Map;

/**
 * <p> SocialCoinTradeRecordDao </p>
 * @author:         javalx
 * @Date :          2019-06-28 11:56:37  
 */
public interface SocialCoinTradeRecordDao extends BaseDao<SocialCoinTradeRecord, Long> {

    List<SocialCoinTradeRecord> pageList(Map<String, String> params);
}

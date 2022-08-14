/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-28 11:56:37 
 */
package hry.social.traderecord.service.impl;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.traderecord.SocialCoinTradeRecord;
import hry.social.manage.remote.model.transfer.SocialTransferRecord;
import hry.social.traderecord.dao.SocialCoinTradeRecordDao;
import hry.social.traderecord.service.SocialCoinTradeRecordService;
import hry.util.PageFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p> SocialCoinTradeRecordService </p>
 * @author:         javalx
 * @Date :          2019-06-28 11:56:37  
 */
@Service("socialCoinTradeRecordService")
public class SocialCoinTradeRecordServiceImpl extends BaseServiceImpl<SocialCoinTradeRecord, Long> implements SocialCoinTradeRecordService {
	
	@Resource(name="socialCoinTradeRecordDao")
	@Override
	public void setDao(BaseDao<SocialCoinTradeRecord, Long> dao) {
		super.dao = dao;
	}


	@Override
	public FrontPage coinTraderList(Map<String,String> params) {
		Page<SocialTransferRecord> page = PageFactory.getPage(params);
		//----------------------查询开始------------------------------
		List<SocialCoinTradeRecord> pageList = ((SocialCoinTradeRecordDao) dao).pageList(params);
		return new FrontPage(pageList, page.getTotal(), page.getPages(), page.getPageSize());
	}
}

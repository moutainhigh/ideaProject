/**
 * Copyright:   
 * @author:      xubb
 * @version:     V1.0 
 * @Date:        2019-07-04 10:06:17 
 */
package hry.app.account.service.impl;

import com.github.pagehelper.Page;
import hry.app.account.dao.OtcAccountRecordDao;
import hry.app.account.service.OtcAccountRecordService;
import hry.bean.FrontPage;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.manage.remote.model.account.OtcAccountRecord;
import hry.util.PageFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p> OtcAccountRecordService </p>
 * @author:         xubb
 * @Date :          2019-07-04 10:06:17  
 */
@Service("otcAccountRecordService")
public class OtcAccountRecordServiceImpl extends BaseServiceImpl<OtcAccountRecord, Long> implements OtcAccountRecordService {
	
	@Resource(name="otcAccountRecordDao")
	@Override
	public void setDao(BaseDao<OtcAccountRecord, Long> dao) {
		super.dao = dao;
	}


	@Override
	public FrontPage getAccountRecord (Map<String, String> params) {
		Page page = PageFactory.getPage(params);
		List<OtcAccountRecord> recordList = ((OtcAccountRecordDao)dao).finePageAccountRecord(params);
		return new FrontPage(recordList, page.getTotal(), page.getPages(), page.getPageSize());
	}

}

/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-20 15:43:03 
 */
package hry.cm4.account.service.impl;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.cm4.account.dao.Cm4AccountRecordDao;
import hry.cm4.account.model.Cm4AccountRecord;
import hry.cm4.account.service.Cm4AccountRecordService;
import hry.cm4.remote.model.CmAccountRecordRemote;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p> Cm4AccountRecordService </p>
 * @author:         yaozh
 * @Date :          2019-11-20 15:43:03  
 */
@Service("cm4AccountRecordService")
public class Cm4AccountRecordServiceImpl extends BaseServiceImpl<Cm4AccountRecord, Long> implements Cm4AccountRecordService{
	
	@Resource(name="cm4AccountRecordDao")
	@Override
	public void setDao(BaseDao<Cm4AccountRecord, Long> dao) {
		super.dao = dao;
	}
	@Override
	public List<Cm4AccountRecord> getProfitStatistics(Long customerId, String coinCode) {
		// TODO Auto-generated method stub
		return ((Cm4AccountRecordDao)dao).getProfitStatistics(customerId, coinCode);
	}

	@Override
	public FrontPage getAccountRecord(Map<String, String> params) {
		// TODO Auto-generated method stub
		Page page = PageFactory.getPage(params);
		List<Cm4AccountRecord> recordList = ((Cm4AccountRecordDao)dao).finePageAccountRecord(params);
		List<CmAccountRecordRemote> beanList = ObjectUtil.beanList(recordList, CmAccountRecordRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}


}

/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-04 10:12:46 
 */
package hry.cmson.account.service.impl;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.cmson.account.dao.CmSonAccountRecordDao;
import hry.cmson.account.model.CmSonAccountRecord;
import hry.cmson.account.service.CmSonAccountRecordService;
import hry.cmson.remote.model.CmAccountRecordRemote;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p> CmSonAccountRecordService </p>
 * @author:         yaozh
 * @Date :          2019-11-04 10:12:46  
 */
@Service("cmSonAccountRecordService")
public class CmSonAccountRecordServiceImpl extends BaseServiceImpl<CmSonAccountRecord, Long> implements CmSonAccountRecordService{
	
	@Resource(name="cmSonAccountRecordDao")
	@Override
	public void setDao(BaseDao<CmSonAccountRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<CmSonAccountRecord> getProfitStatistics(Long customerId, String coinCode) {
		// TODO Auto-generated method stub
		return ((CmSonAccountRecordDao)dao).getProfitStatistics(customerId, coinCode);
	}

	@Override
	public FrontPage getAccountRecord(Map<String, String> params) {
		// TODO Auto-generated method stub
		Page page = PageFactory.getPage(params);
		List<CmSonAccountRecord> recordList = ((CmSonAccountRecordDao)dao).finePageAccountRecord(params);
		List<CmAccountRecordRemote> beanList = ObjectUtil.beanList(recordList, CmAccountRecordRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}


}

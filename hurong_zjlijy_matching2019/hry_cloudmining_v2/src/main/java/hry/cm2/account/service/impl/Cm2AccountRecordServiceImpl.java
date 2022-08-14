/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 13:57:48 
 */
package hry.cm2.account.service.impl;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.cm2.account.dao.Cm2AccountRecordDao;
import hry.cm2.account.model.Cm2AccountRecord;
import hry.cm2.account.service.Cm2AccountRecordService;
import hry.cm2.remote.model.CmAccountRecordRemote;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.PageFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p> Cm2AccountRecordService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 13:57:48  
 */
@Service("cm2AccountRecordService")
public class Cm2AccountRecordServiceImpl extends BaseServiceImpl<Cm2AccountRecord, Long> implements Cm2AccountRecordService{
	
	@Resource(name="cm2AccountRecordDao")
	@Override
	public void setDao(BaseDao<Cm2AccountRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<Cm2AccountRecord> getProfitStatistics(Long customerId, String coinCode) {
		// TODO Auto-generated method stub
		return ((Cm2AccountRecordDao)dao).getProfitStatistics(customerId, coinCode);
	}

	@Override
	public FrontPage getAccountRecord(Map<String, String> params) {
		// TODO Auto-generated method stub
		Page page = PageFactory.getPage(params);
		List<Cm2AccountRecord> recordList = ((Cm2AccountRecordDao)dao).finePageAccountRecord(params);
		List<CmAccountRecordRemote> beanList = ObjectUtil.beanList(recordList, CmAccountRecordRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}
	

}

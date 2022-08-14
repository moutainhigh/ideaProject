/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-26 15:51:49 
 */
package hry.cm.account.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;

import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.cm.account.dao.CmAccountRecordDao;
import hry.cm.account.model.CmAccountRecord;
import hry.cm.account.service.CmAccountRecordService;
import hry.cm.remote.model.CmAccountRecordRemote;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;

/**
 * <p> CmAccountRecordService </p>
 * @author:         yaozh
 * @Date :          2019-07-26 15:51:49  
 */
@Service("cmAccountRecordService")
public class CmAccountRecordServiceImpl extends BaseServiceImpl<CmAccountRecord, Long> implements CmAccountRecordService{
	
	@Resource(name="cmAccountRecordDao")
	@Override
	public void setDao(BaseDao<CmAccountRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public List<CmAccountRecord> getProfitStatistics(Long customerId, String coinCode) {
		// TODO Auto-generated method stub
		return ((CmAccountRecordDao)dao).getProfitStatistics(customerId, coinCode);
	}

	@Override
	public FrontPage getAccountRecord(Map<String, String> params) {
		// TODO Auto-generated method stub
		Page page = PageFactory.getPage(params);
		List<CmAccountRecord> recordList = ((CmAccountRecordDao)dao).finePageAccountRecord(params);
		List<CmAccountRecordRemote> beanList = ObjectUtil.beanList(recordList, CmAccountRecordRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}
	

}

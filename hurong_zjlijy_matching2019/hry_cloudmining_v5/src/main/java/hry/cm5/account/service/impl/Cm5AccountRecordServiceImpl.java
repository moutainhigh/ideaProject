/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-12-24 14:58:03 
 */
package hry.cm5.account.service.impl;

import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.cm5.account.dao.Cm5AccountRecordDao;
import hry.cm5.account.model.Cm5AccountRecord;
import hry.cm5.account.service.Cm5AccountRecordService;
import hry.cm5.remote.model.CmAccountRecordRemote;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.PageFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p> Cm5AccountRecordService </p>
 * @author:         zhouming
 * @Date :          2019-12-24 14:58:03  
 */
@Service("cm5AccountRecordService")
public class Cm5AccountRecordServiceImpl extends BaseServiceImpl<Cm5AccountRecord, Long> implements Cm5AccountRecordService{
	
	@Resource(name="cm5AccountRecordDao")
	@Override
	public void setDao(BaseDao<Cm5AccountRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public FrontPage getAccountRecord(Map<String, String> params) {
		// TODO Auto-generated method stub
		Page page = PageFactory.getPage(params);
		List<Cm5AccountRecord> recordList = ((Cm5AccountRecordDao)dao).finePageAccountRecord(params);
		List<CmAccountRecordRemote> beanList = ObjectUtil.beanList(recordList, CmAccountRecordRemote.class);
		return new FrontPage(beanList, page.getTotal(), page.getPages(), page.getPageSize());
	}
}

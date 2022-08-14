/**
 * Copyright:
 * @author:      denghf
 * @version:     V1.0
 * @Date:        2019-01-15 14:33:22
 */
package hry.ico.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.bean.FrontPage;
import hry.bean.ObjectUtil;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.ico.dao.IcoTransactionRecordDao;
import hry.ico.model.IcoTransactionRecord;
import hry.ico.remote.model.RemoteIcoTransactionRecord;
import hry.ico.service.IcoTransactionRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> IcoTransactionRecordService </p>
 * @author:         denghf
 * @Date :          2019-01-15 14:33:22
 */
@Service("icoTransactionRecordService")
public class IcoTransactionRecordServiceImpl extends BaseServiceImpl<IcoTransactionRecord, Long> implements IcoTransactionRecordService{

	@Resource
	private ExDmTransactionService exDmTransactionService;

	@Resource(name="icoTransactionRecordDao")
	@Override
	public void setDao(BaseDao<IcoTransactionRecord, Long> dao) {
		super.dao = dao;
	}
	//定时充币记录同步
	@Override
	public void timingSynChargeCoinRecord() {
		/*QueryFilter queryFilter=new QueryFilter(ExDmTransaction.class);
		queryFilter.setOrderby("id asc");
		queryFilter.addFilter("id >","");
		List<ExDmTransaction> list=exDmTransactionService.find(queryFilter);
		if(list!=null&&list.size()>0){
			for (ExDmTransaction l:list) {
				
			}
		}*/

	}
	@Override
	public FrontPage findPageBySql(Map<String, String> hashMap) {
		String offset=hashMap.get("page")==null?"0":hashMap.get("page");
			String limit=hashMap.get("pageSize")==null?"10":hashMap.get("pageSize");
		if(Integer.parseInt(limit)>100){
					limit="10";
			}
		Page<IcoTransactionRecord> page = PageHelper.startPage(Integer.parseInt(offset), Integer.parseInt(limit));
		List<IcoTransactionRecord> icoExperiences = ((IcoTransactionRecordDao) dao).findPageBySql(hashMap);
		List<RemoteIcoTransactionRecord> result= ObjectUtil.beanList(icoExperiences,RemoteIcoTransactionRecord.class);
		return new FrontPage(result, page.getTotal(), page.getPages(), page.getPageSize());

	}

	@Override
	public IcoTransactionRecord getCBTransaction(Map<String,String> map) {
		return ((IcoTransactionRecordDao)dao).getCBTransaction(map);
	}
}

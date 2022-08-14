/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-02-26 13:46:47 
 */
package hry.admin.ico.account.service.impl;

import com.github.pagehelper.Page;
import hry.admin.ico.account.dao.IcoTransactionRecordDao;
import hry.admin.ico.account.dao.IcoTransferAccountsDao;
import hry.admin.ico.account.model.IcoTransactionRecord;
import hry.admin.ico.account.model.IcoTransferAccounts;
import hry.admin.ico.account.service.IcoTransferAccountsService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> IcoTransferAccountsService </p>
 * @author:         lzy
 * @Date :          2019-02-26 13:46:47  
 */
@Service("icoTransferAccountsService")
public class IcoTransferAccountsServiceImpl extends BaseServiceImpl<IcoTransferAccounts, Long> implements IcoTransferAccountsService{
	
	@Resource(name="icoTransferAccountsDao")
	@Override
	public void setDao(BaseDao<IcoTransferAccounts, Long> dao) {
		super.dao = dao;
	}


	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		Page<IcoTransactionRecord> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------
		//----------------------查询开始------------------------------
		String email = filter.getRequest().getParameter("email");
		String toEmail = filter.getRequest().getParameter("toEmail");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String toMobilePhone = filter.getRequest().getParameter("toMobilePhone");
		String customerId = filter.getRequest().getParameter("customerId");
		String coinCode = filter.getRequest().getParameter("coinCode");

		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");

		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(toEmail)){
			map.put("toEmail", "%"+toEmail+"%");
		}
		if(!StringUtils.isEmpty(toMobilePhone)){
			map.put("toMobilePhone", "%"+toMobilePhone+"%");
		}
		if(!StringUtils.isEmpty(customerId)){
			map.put("customerId", customerId);
		}if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", coinCode);
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime",endTime);
		}if(!StringUtils.isEmpty(startTime)){
			map.put("startTime",startTime);
		}
		((IcoTransferAccountsDao)dao).findPageBySql(map);
		//----------------------分页查询底部外壳------------------------------
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
}

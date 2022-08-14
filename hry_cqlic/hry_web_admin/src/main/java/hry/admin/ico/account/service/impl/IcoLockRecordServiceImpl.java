/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 19:47:55 
 */
package hry.admin.ico.account.service.impl;

import com.github.pagehelper.Page;
import hry.admin.ico.account.dao.IcoLockRecordDao;
import hry.admin.ico.account.model.IcoLockRecord;
import hry.admin.ico.account.service.IcoLockRecordService;
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
 * <p> IcoLockRecordService </p>
 * @author:         lzy
 * @Date :          2019-01-14 19:47:55  
 */
@Service("icoLockRecordService")
public class IcoLockRecordServiceImpl extends BaseServiceImpl<IcoLockRecord, Long> implements IcoLockRecordService{
	
	@Resource(name="icoLockRecordDao")
	@Override
	public void setDao(BaseDao<IcoLockRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {

		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		Page<IcoLockRecord> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------
		//----------------------查询开始------------------------------
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String lockDeductType = filter.getRequest().getParameter("lockDeductType");

		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");

		String coinCode = filter.getRequest().getParameter("coinCode");

		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(lockDeductType)){
			map.put("lockDeductType", lockDeductType);
		}
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", coinCode);
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime",endTime);
		}if(!StringUtils.isEmpty(startTime)){
			map.put("startTime",startTime);
		}
		((IcoLockRecordDao)dao).findPageBySql(map);
		//----------------------分页查询底部外壳------------------------------
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
}

/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 16:50:30 
 */
package hry.admin.ico.account.service.impl;

import com.github.pagehelper.Page;
import hry.admin.ico.account.dao.IcoTransactionRecordDao;
import hry.admin.ico.account.model.IcoTransactionRecord;
import hry.admin.ico.account.service.IcoTransactionRecordService;
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
 * <p> IcoTransactionRecordService </p>
 * @author:         lzy
 * @Date :          2019-01-14 16:50:30  
 */
@Service("icoTransactionRecordService")
public class IcoTransactionRecordServiceImpl extends BaseServiceImpl<IcoTransactionRecord, Long> implements IcoTransactionRecordService{
	
	@Resource(name="icoTransactionRecordDao")
	@Override
	public void setDao(BaseDao<IcoTransactionRecord, Long> dao) {
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
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String customerId = filter.getRequest().getParameter("customerId");
		String coinCode = filter.getRequest().getParameter("coinCode");
		String type = filter.getRequest().getParameter("type");
		String state = filter.getRequest().getParameter("state");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");

		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
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
		if(!StringUtils.isEmpty(state)){
			map.put("state", state);
		}if(!StringUtils.isEmpty(type)){
			map.put("type", type);
		}

		((IcoTransactionRecordDao)dao).findPageBySql(map);
		//----------------------分页查询底部外壳------------------------------
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
}

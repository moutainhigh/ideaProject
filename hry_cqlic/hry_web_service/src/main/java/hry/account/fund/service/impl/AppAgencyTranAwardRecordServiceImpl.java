package hry.account.fund.service.impl;

import java.util.HashMap;
import java.util.Map;

import hry.account.fund.dao.AppAgencyTranAwardRecordDao;
import hry.account.fund.model.AppAgencyTranAwardRecord;
import hry.account.fund.service.AppAgencyTranAwardRecordService;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.customer.user.dao.AppCustomerDao;
import hry.customer.user.model.AppCustomer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年3月9日 下午1:45:43
 */
@Service("appAgencyTranAwardRecordService")
public class AppAgencyTranAwardRecordServiceImpl extends BaseServiceImpl<AppAgencyTranAwardRecord, Long> implements AppAgencyTranAwardRecordService{

	@Resource(name="appAgencyTranAwardRecordDao")
	@Override
	public void setDao(BaseDao<AppAgencyTranAwardRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCustomer> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		String agentName = filter.getRequest().getParameter("agentName_like");
		String agentMobile = filter.getRequest().getParameter("agentMobile_like");
		String orderNum = filter.getRequest().getParameter("orderNum_like");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(agentName)){
			map.put("agentName", "%"+agentName+"%");
		}
		if(!StringUtils.isEmpty(agentMobile)){
			map.put("agentMobile", "%"+agentMobile+"%");
		}
		if(!StringUtils.isEmpty(orderNum)){
			map.put("orderNum", "%"+orderNum+"%");
		}
		
		((AppAgencyTranAwardRecordDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	}
	
}

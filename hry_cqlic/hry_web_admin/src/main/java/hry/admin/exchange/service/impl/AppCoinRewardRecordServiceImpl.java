/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-02 11:44:17 
 */
package hry.admin.exchange.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.admin.customer.model.AppCustomer;
import hry.admin.exchange.dao.AppCoinRewardRecordDao;
import hry.admin.exchange.model.AppCoinRewardRecord;
import hry.admin.exchange.service.AppCoinRewardRecordService;
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
 * <p> AppCoinRewardRecordService </p>
 * @author:         tianpengyu
 * @Date :          2018-07-02 11:44:17  
 */
@Service("appCoinRewardRecordService")
public class AppCoinRewardRecordServiceImpl extends BaseServiceImpl<AppCoinRewardRecord, Long> implements AppCoinRewardRecordService{
	
	@Resource(name="appCoinRewardRecordDao")
	@Override
	public void setDao(BaseDao<AppCoinRewardRecord, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {

		Page<AppCustomer> page = PageFactory.getPage(filter);

		String trueName = filter.getRequest().getParameter("trueName");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");

		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}

		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		((AppCoinRewardRecordDao)dao).findPageBySql(map);
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
}
